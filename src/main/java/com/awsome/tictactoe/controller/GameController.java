package com.awsome.tictactoe.controller;

import com.awsome.tictactoe.gameLogic.*;
import com.awsome.tictactoe.model.User;
import com.awsome.tictactoe.repository.ConsoleUsersRepository;
import com.awsome.tictactoe.repository.IUsersRepository;
import com.awsome.tictactoe.view.WebView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;

@Controller
public class GameController {

    TicTacToeLogic gameLogic;
    IStatisticsRepository statsRepo;
    IUsersRepository repository = new ConsoleUsersRepository();
    WebView view;
    Board gameBoard;


    public GameController(){
        this.view = new WebView();
        statsRepo = new ConsoleStatisticsRepository();
        IPlayer player1 = new RandomAIPlayer("AI_1");
        IPlayer player2 = new HumanPlayer(view, "Bob");
        this.gameBoard = new Board();
        gameLogic = new TicTacToeLogic(gameBoard, player1, player2,statsRepo, view);
    }


    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/log_in")
    public String logIn(@ModelAttribute User user){
        User savedUser = repository.findUser(user.getUsername());
        if (user.getPassword().equals(savedUser.getPassword())){
            return "redirect:/first_move";
        }else {
            return "redirect:/";
        }
    }

    @GetMapping("/play")
    public String play(Model model){
        model.addAttribute("classes", this.view.getBoardClasses());
        if (this.gameLogic.getGameStatus() != GameStatus.InProgress){
            model.addAttribute("game_finished", true);
        }
        return "play";
    }

    @GetMapping("/first_move")
    public String startGame(){
        if (!gameLogic.getCurrentPlayer().shouldWait()) {
            gameLogic.runStep(gameLogic.getCurrentPlayer().makeMove(this.gameBoard));
        }
        return "redirect:/play";
    }

    @GetMapping("/move")
    public String move(@RequestParam("x") int x, @RequestParam("y") int y){
        Point point = new Point(x,y);
        gameLogic.runStep(point);
        if (this.gameLogic.getGameStatus() == GameStatus.InProgress && !gameLogic.getCurrentPlayer().shouldWait()) {
            gameLogic.runStep(gameLogic.getCurrentPlayer().makeMove(this.gameBoard));
        }
        return "redirect:/play";
    }

}
