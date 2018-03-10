package com.awsome.tictactoe.gameLogic;



public class BoardTest {

    @org.junit.Test
    public void getBoardShouldNeverReturnNull() {
        Board board = new Board();
        FieldStatus[][] gameBoard = board.getBoard();
        org.junit.Assert.assertNotNull(gameBoard);
    }

    @org.junit.Test
    public void whenBoardIsCreatedAllFieldsAreEmpty(){
        Board board = new Board();
        FieldStatus[][] gameBoard = board.getBoard();

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                FieldStatus fieldStatus = gameBoard[i][j];
                org.junit.Assert.assertEquals(fieldStatus, FieldStatus.Empty);
            }
        }
    }

    @org.junit.Test
    public void whenBoardIsCreatedItHasSizeOf3(){
        Board board = new Board();
        FieldStatus[][] gameBoard = board.getBoard();
        org.junit.Assert.assertEquals(3, gameBoard.length);
    }
}
