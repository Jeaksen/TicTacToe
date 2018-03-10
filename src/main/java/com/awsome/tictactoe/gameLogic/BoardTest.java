package com.awsome.tictactoe.gameLogic;



public class BoardTest {

    @org.junit.Test
    public void getBoard() {
        Board board = new Board();
        FieldStatus[][] gameBoard = board.getBoard();
        org.junit.Assert.assertNotNull(gameBoard);
    }
}
