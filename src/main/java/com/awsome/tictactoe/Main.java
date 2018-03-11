package com.awsome.tictactoe;

import com.awsome.tictactoe.gameLogic.*;
import com.awsome.tictactoe.view.ConsoleView;
import com.awsome.tictactoe.view.IView;

public class Main {
    public static void main(String[] args) {
        IStatisticsRepository statsRepo  = new ConsoleStatisticsRepository();
        IView view = new ConsoleView();
        IPlayer player1 = new RandomAIPlayer("AI_1");
        IPlayer player2 = new HumanPlayer(view, "Bob");
        Board board = new Board();
        TicTacToeLogic gameLogic = new TicTacToeLogic(board, player1, player2, statsRepo, view );
        gameLogic.runGame();
    }
}
