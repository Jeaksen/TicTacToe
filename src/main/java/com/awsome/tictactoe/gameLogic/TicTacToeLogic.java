package com.awsome.tictactoe.gameLogic;

public class TicTacToeLogic {

    private Board gameBoard;

    public TicTacToeLogic(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public GameStatus getGameStatus(){
        FieldStatus[][] gameBoard = this.gameBoard.getBoard();

        if(gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2]){
            if (gameBoard[0][0] == FieldStatus.TakenByPlayer1){
                return GameStatus.Player1Won;
            } else return GameStatus.Player2Won;
        }

        if(gameBoard[2][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[0][2]){
            if (gameBoard[2][0] == FieldStatus.TakenByPlayer1){
                return GameStatus.Player1Won;
            } else return GameStatus.Player2Won;
        }

        return GameStatus.InProgress;

    }

}
