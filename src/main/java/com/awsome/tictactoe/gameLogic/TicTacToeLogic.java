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

//    public void runGame(){
//        IPlayer currentPlayer = player1;
//        Point chosenPoint;
//        while (this.getGameStatus() == GameStatus.InProgress){
//            chosenPoint = currentPlayer.makeMove(this.gameBoard);
//            if (chosenPoint == null){
//                this.view.updateView(currentPlayer, gameBoard);
//                return;
//            }
//            while (gameBoard.getBoard()[chosenPoint.x][chosenPoint.y] != FieldStatus.Empty){
//                this.view.displayMessage("This field is already taken, please retry");
//                chosenPoint =  currentPlayer.makeMove(this.gameBoard);
//            }
//            if (currentPlayer == player1){
//                FieldStatus chosenField = FieldStatus.TakenByPlayer1;
//                gameBoard.getBoard()[chosenPoint.x][chosenPoint.y] = chosenField;
//            } else {
//                FieldStatus chosenField = FieldStatus.TakenByPlayer2;
//                gameBoard.getBoard()[chosenPoint.x][chosenPoint.y] = chosenField;
//            }
//            if(currentPlayer == player1){
//                currentPlayer = player2;
//            } else {
//                currentPlayer = player1;
//            }
//            this.view.updateView(currentPlayer, gameBoard);
//        }
//    }

    /**
     * This methods calls a methods from IStatisticsRepository to save result when game is finished
     * @throws Exception when there was an error during saving the result
     */
    private void saveGameResult() throws Exception {
        statisticsRepository.saveResult(this.getGameStatus());
    }

    /**
     *
     * @param point X and Y coordinates of field that should be occupied
     * @throws Exception When saving result fails
     */
    public void runStep(Point point) throws Exception {
        FieldStatus chosenField = currentPlayer == player1?FieldStatus.TakenByPlayer1:FieldStatus.TakenByPlayer2;
        gameBoard.getBoard()[point.x][point.y] = chosenField;
        currentPlayer = currentPlayer == player1?player2:player1;
        this.view.updateView(this.currentPlayer, this.gameBoard);
        if (this.getGameStatus() != GameStatus.InProgress) {this.saveGameResult();}

    }

    public IPlayer getCurrentPlayer() {
        return this.currentPlayer;
    }

    public void setCurrentPlayer(IPlayer currentPlayer){
        this.currentPlayer = currentPlayer;
    }
}
