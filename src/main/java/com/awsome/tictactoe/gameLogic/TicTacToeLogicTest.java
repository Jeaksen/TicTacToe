package com.awsome.tictactoe.gameLogic;


import com.awsome.tictactoe.view.ConsoleView;
import com.awsome.tictactoe.view.IView;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class TicTacToeLogicTest {


    private TicTacToeLogic logic;

    @Test
    public void getGameStatusShouldReturnPlayer1WonWhenHas3FieldsLeftDiagonal() {
        Board board = new Board();
        FieldStatus[][] gameBoard = board.getBoard();
        gameBoard[0][0]=FieldStatus.TakenByPlayer1;
        gameBoard[1][1]=FieldStatus.TakenByPlayer1;
        gameBoard[2][2]=FieldStatus.TakenByPlayer1;
        TicTacToeLogic logic = new TicTacToeLogic(board, null, null, null, null);
        Assert.assertEquals(GameStatus.Player1Won,logic.getGameStatus());
    }

    @Test
    public void getGameStatusShouldReturnInProgressWhenBoardEmpty() {
        Board board = new Board();
        TicTacToeLogic logic = new TicTacToeLogic(board, null, null, null, null);
        Assert.assertEquals(GameStatus.InProgress,logic.getGameStatus());
    }

    @Test
    public void getGameStatusShouldReturnPlayer2WonWhenHas3FieldsLeftDiagonal() {
        Board board = new Board();
        FieldStatus[][] gameBoard = board.getBoard();
        gameBoard[0][0]=FieldStatus.TakenByPlayer2;
        gameBoard[1][1]=FieldStatus.TakenByPlayer2;
        gameBoard[2][2]=FieldStatus.TakenByPlayer2;
        TicTacToeLogic logic = new TicTacToeLogic(board, null, null, null, null);
        Assert.assertEquals(GameStatus.Player2Won,logic.getGameStatus());
    }

    @Test
    public void getGameStatusShouldReturnPlayer1WonWhenHas3FieldsRightDiagonal() {
        Board board = new Board();
        FieldStatus[][] gameBoard = board.getBoard();
        gameBoard[2][0]=FieldStatus.TakenByPlayer1;
        gameBoard[1][1]=FieldStatus.TakenByPlayer1;
        gameBoard[0][2]=FieldStatus.TakenByPlayer1;
        TicTacToeLogic logic = new TicTacToeLogic(board,null, null, null, null);
        Assert.assertEquals(GameStatus.Player1Won,logic.getGameStatus());
    }

    @Test
    public void getGameStatusShouldReturnPlayer2WonWhenHas3FieldsRightDiagonal() {
        Board board = new Board();
        FieldStatus[][] gameBoard = board.getBoard();
        gameBoard[2][0]=FieldStatus.TakenByPlayer2;
        gameBoard[1][1]=FieldStatus.TakenByPlayer2;
        gameBoard[0][2]=FieldStatus.TakenByPlayer2;
        TicTacToeLogic logic = new TicTacToeLogic(board, null, null, null, null);
        Assert.assertEquals(GameStatus.Player2Won,logic.getGameStatus());
    }

    @Test
    public void getGameStatusShouldReturnPlayer2WonWhenHas3FieldsColumn1() {
        Board board = new Board();
        FieldStatus[][] gameBoard = board.getBoard();
        gameBoard[0][0]=FieldStatus.TakenByPlayer2;
        gameBoard[1][0]=FieldStatus.TakenByPlayer2;
        gameBoard[2][0]=FieldStatus.TakenByPlayer2;
        TicTacToeLogic logic = new TicTacToeLogic(board, null, null, null, null);
        Assert.assertEquals(GameStatus.Player2Won,logic.getGameStatus());
    }

    @Test
    public void getGameStatusShouldReturnPlayer2WonWhenHas3FieldsColumn2() {
        Board board = new Board();
        FieldStatus[][] gameBoard = board.getBoard();
        gameBoard[0][1]=FieldStatus.TakenByPlayer2;
        gameBoard[1][1]=FieldStatus.TakenByPlayer2;
        gameBoard[2][1]=FieldStatus.TakenByPlayer2;
        TicTacToeLogic logic = new TicTacToeLogic(board, null, null, null, null);
        Assert.assertEquals(GameStatus.Player2Won,logic.getGameStatus());
    }

    @Test
    public void getGameStatusShouldReturnPlayer2WonWhenHas3FieldsRow1() {
        Board board = new Board();
        FieldStatus[][] gameBoard = board.getBoard();
        gameBoard[0][0]=FieldStatus.TakenByPlayer2;
        gameBoard[0][1]=FieldStatus.TakenByPlayer2;
        gameBoard[0][2]=FieldStatus.TakenByPlayer2;
        TicTacToeLogic logic = new TicTacToeLogic(board, null, null, null, null);
        Assert.assertEquals(GameStatus.Player2Won,logic.getGameStatus());
    }

    @Test
    public void getGameStatusShouldReturnPlayer2WonWhenHas3FieldsRow2() {
        Board board = new Board();
        FieldStatus[][] gameBoard = board.getBoard();
        gameBoard[1][0]=FieldStatus.TakenByPlayer2;
        gameBoard[1][1]=FieldStatus.TakenByPlayer2;
        gameBoard[1][2]=FieldStatus.TakenByPlayer2;
        TicTacToeLogic logic = new TicTacToeLogic(board,null, null, null, null);
        Assert.assertEquals(GameStatus.Player2Won,logic.getGameStatus());
    }

    @Test
    public void getGameStatusShouldReturnTieWhenAllFieldsAreNotEmpty() {
        Board board = new Board();
        FieldStatus[][] gameBoard = board.getBoard();
        gameBoard[0][0]=FieldStatus.TakenByPlayer2;
        gameBoard[0][1]=FieldStatus.TakenByPlayer1;
        gameBoard[0][2]=FieldStatus.TakenByPlayer2;
        gameBoard[1][0]=FieldStatus.TakenByPlayer2;
        gameBoard[1][1]=FieldStatus.TakenByPlayer1;
        gameBoard[1][2]=FieldStatus.TakenByPlayer2;
        gameBoard[2][0]=FieldStatus.TakenByPlayer1;
        gameBoard[2][1]=FieldStatus.TakenByPlayer2;
        gameBoard[2][2]=FieldStatus.TakenByPlayer1;

        TicTacToeLogic logic = new TicTacToeLogic(board,null, null, null, null);
        Assert.assertEquals(GameStatus.Tie,logic.getGameStatus());
    }

    @Test
    public void runGameForRandomPlayers(){
        IPlayer player1 = new RandomAIPlayer("AI_1");
        IPlayer player2 = new RandomAIPlayer("AI_2");
        Board board = new Board();
        ConsoleStatisticsRepository consoleStatisticsRepository  = new ConsoleStatisticsRepository();
        IView view = new ConsoleView();
        TicTacToeLogic gameLogic = new TicTacToeLogic(board, player1, player2, consoleStatisticsRepository, view );
        gameLogic.runGame();

    }
}
