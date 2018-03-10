package com.awsome.tictactoe.gameLogic;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class TicTacToeLogicTest {


    @Test
    public void getGameStatusShouldReturnPlayer1WonWhenHas3FieldsLeftDiagonal() {
        Board board = new Board();
        FieldStatus[][] gameBoard = board.getBoard();
        gameBoard[0][0]=FieldStatus.TakenByPlayer1;
        gameBoard[1][1]=FieldStatus.TakenByPlayer1;
        gameBoard[2][2]=FieldStatus.TakenByPlayer1;
        TicTacToeLogic logic = new TicTacToeLogic(board);
        Assert.assertEquals(GameStatus.Player1Won,logic.getGameStatus());
    }

    @Test
    public void getGameStatusShouldReturnPlayer2WonWhenHas3FieldsLeftDiagonal() {
        Board board = new Board();
        FieldStatus[][] gameBoard = board.getBoard();
        gameBoard[0][0]=FieldStatus.TakenByPlayer2;
        gameBoard[1][1]=FieldStatus.TakenByPlayer2;
        gameBoard[2][2]=FieldStatus.TakenByPlayer2;
        TicTacToeLogic logic = new TicTacToeLogic(board);
        Assert.assertEquals(GameStatus.Player2Won,logic.getGameStatus());
    }
}
