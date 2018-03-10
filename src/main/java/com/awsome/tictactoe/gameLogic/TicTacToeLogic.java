package com.awsome.tictactoe.gameLogic;

public class TicTacToeLogic {

    private Board gameBoard;

    public TicTacToeLogic(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public GameStatus getGameStatus(){
        return GameStatus.Player1Won;
    }

}
