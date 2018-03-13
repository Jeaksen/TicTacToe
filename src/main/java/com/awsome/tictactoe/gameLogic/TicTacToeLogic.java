package com.awsome.tictactoe.gameLogic;

import com.awsome.tictactoe.gameLogic.player.IPlayer;
import com.awsome.tictactoe.gameLogic.statisticsRepository.IStatisticsRepository;
import com.awsome.tictactoe.view.IView;

import java.awt.*;

public class TicTacToeLogic {

    private Board gameBoard;
    private IPlayer player1;
    private IPlayer player2;
    private IStatisticsRepository statisticsRepository;
    private IView view;
    private IPlayer currentPlayer;


    public TicTacToeLogic(Board gameBoard, IPlayer player1, IPlayer player2,
                          IStatisticsRepository statisticsRepository, IView view) {
        this.gameBoard = gameBoard;
        this.player1 = player1;
        this.player2 = player2;
        this.statisticsRepository = statisticsRepository;
        this.view = view;
        this.currentPlayer = player1;
    }

    public GameStatus getGameStatus(){
        FieldStatus[][] gameBoard = this.gameBoard.getBoard();

        if(gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2]  && gameBoard[2][2] != FieldStatus.Empty){
            if (gameBoard[0][0] == FieldStatus.TakenByPlayer1){
                return GameStatus.Player1Won;
            } else return GameStatus.Player2Won;
        }

        if(gameBoard[2][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[0][2] && gameBoard[0][2] != FieldStatus.Empty){
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

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                if (gameBoard[i][j] == FieldStatus.Empty){
                    return GameStatus.InProgress;
                }
            }
        }
        return GameStatus.Tie;
    }

    public void runGame(){
        IPlayer currentPlayer = player1;
        Point chosenPoint;
        while (this.getGameStatus() == GameStatus.InProgress){
            chosenPoint = currentPlayer.makeMove(this.gameBoard);
            if (chosenPoint == null){
                this.view.updateView(currentPlayer, gameBoard);
                return;
            }
            while (gameBoard.getBoard()[chosenPoint.x][chosenPoint.y] != FieldStatus.Empty){
                this.view.displayMessage("This field is already taken, please retry");
                chosenPoint =  currentPlayer.makeMove(this.gameBoard);
            }
            if (currentPlayer == player1){
                FieldStatus chosenField = FieldStatus.TakenByPlayer1;
                gameBoard.getBoard()[chosenPoint.x][chosenPoint.y] = chosenField;
            } else {
                FieldStatus chosenField = FieldStatus.TakenByPlayer2;
                gameBoard.getBoard()[chosenPoint.x][chosenPoint.y] = chosenField;
            }
            if(currentPlayer == player1){
                currentPlayer = player2;
            } else {
                currentPlayer = player1;
            }
            this.view.updateView(currentPlayer, gameBoard);
        }
    }

    private void saveStatistics(){
        if (this.getGameStatus() == GameStatus.Tie){
            statisticsRepository.saveResult(player1.getName(), PlayerResultStatus.Tie);
            statisticsRepository.saveResult(player2.getName(), PlayerResultStatus.Tie);
        } else if(this.getGameStatus() == GameStatus.Player1Won){
            statisticsRepository.saveResult(player1.getName(), PlayerResultStatus.Won);
            statisticsRepository.saveResult(player2.getName(), PlayerResultStatus.Lost);
        } else {
            statisticsRepository.saveResult(player2.getName(), PlayerResultStatus.Won);
            statisticsRepository.saveResult(player1.getName(), PlayerResultStatus.Lost);
        }
    }

    public GameStatus runStep(Point point){
        FieldStatus chosenField = currentPlayer == player1?FieldStatus.TakenByPlayer1:FieldStatus.TakenByPlayer2;
        gameBoard.getBoard()[point.x][point.y] = chosenField;
        currentPlayer = currentPlayer == player1?player2:player1;
        this.view.updateView(this.currentPlayer, this.gameBoard);
        if (this.getGameStatus() != GameStatus.InProgress) {this.saveStatistics();}
        return this.getGameStatus();
    }

    public IPlayer getCurrentPlayer() {
        return this.currentPlayer;
    }

    public void setCurrentPlayer(IPlayer currentPlayer){
        this.currentPlayer = currentPlayer;
    }
}
