package com.awsome.tictactoe.gameLogic;

public class Board {

    private FieldStatus[][] board;
    private final int size = 2;

    public Board() {
        this.board = new FieldStatus[size][size];
    }

    public FieldStatus[][] getBoard() {
        return board;
    }
}
