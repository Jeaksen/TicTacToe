package com.awsome.tictactoe.gameLogic;

public class Board {

    private FieldStatus[][] board;
    public static final int size = 3;


    public Board() {
        this.board = new FieldStatus[size][size];
        this.resetBoard();
    }

    public Board(FieldStatus[][] board){
        this.board = board;
    }

    public FieldStatus[][] getBoard() {
        return board;
    }

    public void resetBoard(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                this.board[i][j] = FieldStatus.Empty;
            }
        }
    }
}
