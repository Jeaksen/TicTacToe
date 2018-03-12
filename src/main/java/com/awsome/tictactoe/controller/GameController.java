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
    IPlayer player1;
    IPlayer player2;


    public GameController(){
        this.view = new WebView();
        statsRepo = new ConsoleStatisticsRepository();
        this.player1 = new RandomAIPlayer("AI_1");
        this.player2 = new HumanPlayer(view, "Bob");
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
        GameStatus gameStatus = this.gameLogic.getGameStatus();
        if (gameStatus != GameStatus.InProgress){
            String message;
            model.addAttribute("game_finished", true);
            switch (gameStatus){
                case Tie:
                 message="It's a tie!";
                 break;
                default:
                    message = this.gameLogic.getCurrentPlayer()==player1?player2.getName():player1.getName();
                    message += " wins!!!";
            }
            model.addAttribute("finish_status",message);
        }
        return "play";
    }

    @GetMapping("/first_move")
    public String startGame(){
        this.resetBoard();
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

    private void resetBoard(){
        this.gameBoard.resetBoard();
        //gameLogic = new TicTacToeLogic(this.gameBoard, this.player1, this.player2,this.statsRepo, this.view);
    }

}
