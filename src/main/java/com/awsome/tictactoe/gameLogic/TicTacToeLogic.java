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

        for (int i = 0; i < gameBoard.length; i++) {
            if (gameBoard[0][i] == gameBoard[1][i] && gameBoard[1][i] == gameBoard[2][i] && gameBoard[2][i] != FieldStatus.Empty) {
                if (gameBoard[1][i] == FieldStatus.TakenByPlayer2) {
                    return GameStatus.Player2Won;
                } else return GameStatus.Player1Won;
            }
        }

        for (int i = 0; i < gameBoard.length; i++) {
            if (gameBoard[i][0] == gameBoard[i][1] && gameBoard[i][1] == gameBoard[i][2] && gameBoard[i][2] != FieldStatus.Empty) {
                if (gameBoard[i][1] == FieldStatus.TakenByPlayer2) {
                    return GameStatus.Player2Won;
                } else return GameStatus.Player1Won;
            }
        }
        return GameStatus.InProgress;

    }

}
