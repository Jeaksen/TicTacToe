package com.awsome.tictactoe.controller;

import com.awsome.tictactoe.gameLogic.Board;
import com.awsome.tictactoe.gameLogic.GameStatus;
import com.awsome.tictactoe.gameLogic.TicTacToeLogic;
import com.awsome.tictactoe.gameLogic.player.HumanPlayer;
import com.awsome.tictactoe.gameLogic.player.IPlayer;
import com.awsome.tictactoe.gameLogic.player.RandomAIPlayer;
import com.awsome.tictactoe.gameLogic.statisticsRepository.DBStatisticsRepository;
import com.awsome.tictactoe.gameLogic.statisticsRepository.IStatisticsRepository;
import com.awsome.tictactoe.gameLogic.statisticsRepository.SessionResults;
import com.awsome.tictactoe.model.SessionData;
import com.awsome.tictactoe.model.User;
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

    private TicTacToeLogic gameLogic;
    private IStatisticsRepository statisticsRepository;
    private IUsersRepository usersRepository;
    private IPlayer player2;
    private Map<Integer, SessionResults> sessionResultsMap;


    public GameController() throws SQLException {
        this.statisticsRepository = new DBStatisticsRepository();
        this.usersRepository = new DBUsersRepository();
        this.player2 = new RandomAIPlayer("Dummie");
        this.gameLogic = new TicTacToeLogic(statisticsRepository);
        this.sessionResultsMap = new HashMap<>();
    }


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/log_in")
    public String logIn(@ModelAttribute User user, Model model, HttpServletRequest request) {
        User savedUser;
        try {
            savedUser = usersRepository.findUser(user.getUsername());
            if (!user.getPassword().isEmpty() && user.getPassword().equals(savedUser.getPassword())) {
                SessionData sessionData = this.startSession(user.getUsername());
                request.getSession().setAttribute("data", sessionData);
                this.clearMap(sessionData.getPlayer1().getName(), null, sessionData.getSessionID());
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
    public String register(@ModelAttribute User user, Model model, HttpServletRequest request) {
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
                SessionData sessionData = this.startSession(user.getUsername());
                request.getSession().setAttribute("data", sessionData);
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
    public String play(HttpServletRequest request, Model model) {
        SessionData sessionData = (SessionData)request.getSession().getAttribute("data");
        model.addAttribute("classes", WebView.getBoardClasses(sessionData.getBoard()));
        //create new
        model.addAttribute("game_stats", sessionResultsMap.get(sessionData.getSessionID()));
        model.addAttribute("session_data", sessionData);
        return "play";
    }

    @GetMapping("/first_move")
    public String startGame(HttpServletRequest request, Model model) {
        SessionData sessionData = (SessionData)request.getSession().getAttribute("data");
        sessionData.getBoard().resetBoard();
        if (!sessionData.getCurrentPlayer().shouldWait()) {
            try {
                gameLogic.runStep(sessionData.getCurrentPlayer().makeMove(sessionData.getBoard()), sessionData.getCurrentPlayer(), sessionData.getPlayer1(), sessionData.getPlayer2(), sessionData.getBoard(), sessionData.getSessionID());
                this.switchPlayers(sessionData);
            } catch (Exception e) {e.printStackTrace();}
        }
        return this.play(request, model);
    }

    @PostMapping("/move")
    public String move(Model model, @RequestParam("x") int x, @RequestParam("y") int y, HttpServletRequest request) {
        SessionData sessionData = (SessionData)request.getSession().getAttribute("data");
        try {
            gameLogic.runStep(new Point(x,y), sessionData.getCurrentPlayer(), sessionData.getPlayer1(), sessionData.getPlayer2(), sessionData.getBoard(), sessionData.getSessionID());
            this.switchPlayers(sessionData);

            if (this.gameLogic.getGameStatus(sessionData.getBoard()) == GameStatus.InProgress && !sessionData.getCurrentPlayer().shouldWait()) {
                gameLogic.runStep(sessionData.getCurrentPlayer().makeMove(sessionData.getBoard()), sessionData.getCurrentPlayer(), sessionData.getPlayer1(), sessionData.getPlayer2(), sessionData.getBoard(), sessionData.getSessionID());
                this.switchPlayers(sessionData);
            } else {
                this.gameLogic.getGameStatus(sessionData.getBoard());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        this.checkForWin(model, sessionData);
        return this.play(request, model);
    }

    @GetMapping("/log_out")
    public String logout(HttpServletRequest request){
        SessionData sessionData = (SessionData)request.getSession().getAttribute("data");
        this.sessionResultsMap.remove(sessionData.getSessionID());
        request.removeAttribute("data");
        return "redirect:/";
    }

    private SessionData startSession(String player1) throws Exception {
        int sessionID;
        sessionID = this.statisticsRepository.startSession(player1, this.player2.getName());
        this.sessionResultsMap.put(sessionID, new SessionResults(0,0,0,player1,this.player2.getName()));
        //add wins and ties to session data
        SessionData sessionData = new SessionData();
        sessionData.setPlayer1(new HumanPlayer(player1));
        sessionData.setPlayer2(this.player2);
        sessionData.setCurrentPlayer(sessionData.getPlayer2());
        sessionData.setBoard(new Board());
        sessionData.setSessionID(sessionID);
        return sessionData;
    }

    private void switchPlayers(SessionData sessionData){
        sessionData.setCurrentPlayer(sessionData.getCurrentPlayer() == sessionData.getPlayer1()? sessionData.getPlayer2():sessionData.getPlayer1());
    }

    private void checkForWin(Model model, SessionData sessionData){
        GameStatus gameStatus = this.gameLogic.getGameStatus(sessionData.getBoard());
        if (gameStatus != GameStatus.InProgress) {
            String message;
            model.addAttribute("game_finished", true);
            switch (gameStatus) {
                case Tie:
                    message = "It's a tie!";
                    break;
                default:
                    message = sessionData.getCurrentPlayer() == sessionData.getPlayer1() ? sessionData.getPlayer2().getName() : sessionData.getPlayer1().getName();
                    message += " wins!!!";
            }
            model.addAttribute("finish_status", message);
            try {
                //update sessionData wins and ties
                sessionResultsMap.put(sessionData.getSessionID(), this.statisticsRepository.getSessionResults(sessionData.getSessionID()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void clearMap(String player1, String player2, int currentSessionID){
        SessionResults results;
        for (int i = 0; i < currentSessionID; i++) {
            results = this.sessionResultsMap.get(i);
            if (results != null){
                if (player1 != null && results.getName_player1().equals(player1)){
                    this.sessionResultsMap.remove(i);
                    System.out.println("I delete");
                }
                if (player2 != null && results.getName_player1().equals(player2)){
                    this.sessionResultsMap.remove(i);
                    System.out.println("I delete");
                }
            }
        }
    }

}
