package com.awsome.tictactoe.controller;

import com.awsome.tictactoe.gameLogic.*;
import com.awsome.tictactoe.model.User;
import com.awsome.tictactoe.repositories.ConsoleUsersRepository;
import com.awsome.tictactoe.repositories.IUsersRepository;
import com.awsome.tictactoe.view.IView;
import com.awsome.tictactoe.view.WebView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GameController {

    TicTacToeLogic gameLogic;
    IStatisticsRepository statsRepo;
    IUsersRepository repository = new ConsoleUsersRepository();
    WebView view;


    public GameController(){
        this.view = new WebView();
        statsRepo = new ConsoleStatisticsRepository();
        IPlayer player1 = new RandomAIPlayer("AI_1");
        IPlayer player2 = new RandomAIPlayer("AI_1");
        Board gameBoard = new Board();
        gameBoard.getBoard()[0][1]=FieldStatus.TakenByPlayer2;
        gameBoard.getBoard()[1][1]=FieldStatus.TakenByPlayer2;
        gameBoard.getBoard()[2][1]=FieldStatus.TakenByPlayer2;
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
            return "redirect:/play";
        }else {
            return "redirect:/";
        }
    }

    @GetMapping("/play")
    public String play(Model model){
        model.addAttribute("classes", this.view.getBoardClasses());
        return "play";
    }

}
