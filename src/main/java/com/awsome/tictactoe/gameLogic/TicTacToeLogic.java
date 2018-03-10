package com.awsome.tictactoe.gameLogic;

public class TicTacToeLogic {

    private Board gameBoard;

    public TicTacToeLogic(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public GameStatus getGameStatus(){
        FieldStatus[][] gameBoard = this.gameBoard.getBoard();
        if (gameBoard[0][0] == FieldStatus.TakenByPlayer1 &&
        gameBoard[1][1]== FieldStatus.TakenByPlayer1 &&
        gameBoard[2][2] == FieldStatus.TakenByPlayer1 ){
            return GameStatus.Player1Won;
        } else {
            return GameStatus.Player2Won;
        }
    }

}
