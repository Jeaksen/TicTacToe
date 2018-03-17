package com.awsome.tictactoe.controller;

import com.awsome.tictactoe.gameLogic.*;
import com.awsome.tictactoe.gameLogic.player.HumanPlayer;
import com.awsome.tictactoe.gameLogic.player.IPlayer;
import com.awsome.tictactoe.gameLogic.player.RandomAIPlayer;
import com.awsome.tictactoe.gameLogic.statisticsRepository.ConsoleStatisticsRepository;
import com.awsome.tictactoe.gameLogic.statisticsRepository.DBStatisticsRepository;
import com.awsome.tictactoe.gameLogic.statisticsRepository.IStatisticsRepository;
import com.awsome.tictactoe.gameLogic.statisticsRepository.SessionResults;
import com.awsome.tictactoe.model.User;
import com.awsome.tictactoe.repository.ConsoleUsersRepository;
import com.awsome.tictactoe.repository.DBUsersRepository;
import com.awsome.tictactoe.repository.IUsersRepository;
import com.awsome.tictactoe.view.WebView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class GameController {

    TicTacToeLogic gameLogic;
    IStatisticsRepository statisticsRepository;
    IUsersRepository usersRepository;
    WebView view;
    Board gameBoard;
    IPlayer player1;
    IPlayer player2;
    Map<String, SessionResults> sessionResultsMap;


    public GameController() throws SQLException {
        this.view = new WebView();
        //this.statisticsRepository = new ConsoleStatisticsRepository();
        this.statisticsRepository = new DBStatisticsRepository();
        this.player1 = new HumanPlayer(view, "Bob");
        this.player2 = new RandomAIPlayer("Dummie");
        this.gameBoard = new Board();
        //this.usersRepository = new ConsoleUsersRepository();
        this.usersRepository = new DBUsersRepository();
        this.gameLogic = new TicTacToeLogic(gameBoard, player1, player2, statisticsRepository, view);
        this.sessionResultsMap = new HashMap<>();
    }


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/log_in")
    public String logIn(@ModelAttribute User user, Model model) {
        User savedUser;
        try {
            savedUser = usersRepository.findUser(user.getUsername());
            if (user.getPassword().equals(savedUser.getPassword())) {
                this.player1.setName(user.getUsername());
                this.statisticsRepository.startSession(player1.getName(), player2.getName());
                this.sessionResultsMap.put(player1.getName(), new SessionResults(0,0,0,player1.getName(),player2.getName()));
                return "redirect:/first_move";
            } else {
                model.addAttribute("message", "Log in failed! Try again or register.");
            }
        } catch (Exception e) {
            model.addAttribute("message", "Error while getting data, please retry");
            e.printStackTrace();
        }
        return "index";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        if (user.getUsername().isEmpty()) {
            model.addAttribute("message", "Enter username!");
        } else if (user.getPassword().isEmpty()) {
            model.addAttribute("message", "Please enter your password.");
        } else if (user.getUsername().length() > 20){
            model.addAttribute("message", "Please enter a shorter username: max 20 signs");
        } else if (user.getPassword().length() > 20){
            model.addAttribute("message", "Please enter a shorter password: max 20 signs");
        } else try {
            if (usersRepository.findUser(user.getUsername()) == null) {
                usersRepository.saveUser(user);
                player1.setName(user.getUsername());
                this.statisticsRepository.startSession(player1.getName(), player2.getName());
                this.sessionResultsMap.put(player1.getName(), new SessionResults(0,0,0,player1.getName(),player2.getName()));
                return "redirect:/first_move";
            } else {
                model.addAttribute("message", "This username is already taken.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Error occurred while saving data, please retry");
        }
        return "index";
    }

    @GetMapping("/play")
    public String play(Model model) {
        GameStatus gameStatus = this.gameLogic.getGameStatus();
        if (gameStatus != GameStatus.InProgress) {
            String message;
            model.addAttribute("game_finished", true);
            switch (gameStatus) {
                case Tie:
                    message = "It's a tie!";
                    break;
                default:
                    message = this.gameLogic.getCurrentPlayer() == player1 ? player2.getName() : player1.getName();
                    message += " wins!!!";
            }
            model.addAttribute("finish_status", message);
            try {
                sessionResultsMap.put(player1.getName(), this.statisticsRepository.getSessionResults(player1.getName()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("classes", this.view.getBoardClasses());
        model.addAttribute("game_stats", sessionResultsMap.get(player1.getName()));
        return "play";
    }

    @GetMapping("/first_move")
    public String startGame() {
        this.resetBoard();
        if (!gameLogic.getCurrentPlayer().shouldWait()) {
            try {
                gameLogic.runStep(gameLogic.getCurrentPlayer().makeMove(this.gameBoard));
            } catch (Exception e) {e.printStackTrace();}
        }
        return "redirect:/play";
    }

    @GetMapping("/move")
    public String move(@RequestParam("x") int x, @RequestParam("y") int y) {
        Point point = new Point(x, y);
        try {
            gameLogic.runStep(point);
            if (this.gameLogic.getGameStatus() == GameStatus.InProgress && !gameLogic.getCurrentPlayer().shouldWait()) {
                gameLogic.runStep(gameLogic.getCurrentPlayer().makeMove(this.gameBoard));
            }
        } catch (Exception e){

        }
        return "redirect:/play";
    }

    private void resetBoard() {
        this.gameBoard.resetBoard();
        this.view.resetBoard();
    }

}
