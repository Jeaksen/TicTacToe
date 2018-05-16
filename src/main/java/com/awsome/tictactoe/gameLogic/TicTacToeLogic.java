package com.awsome.tictactoe.gameLogic;

import com.awsome.tictactoe.gameLogic.player.IPlayer;
import com.awsome.tictactoe.gameLogic.statisticsRepository.IStatisticsRepository;
import com.awsome.tictactoe.view.IView;

import java.awt.*;

public class TicTacToeLogic {

    private IStatisticsRepository statisticsRepository;

    public TicTacToeLogic(IStatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    public GameStatus getGameStatus(Board board){
        FieldStatus[][] gameBoard = board.getBoard();

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

    /**
     * This methods calls a methods from IStatisticsRepository to save result when game is finished
     * @throws Exception when there was an error during saving the result
     */
    private void saveGameResult(Board gameBoard, int sessionID) throws Exception {
        statisticsRepository.saveResult(this.getGameStatus(gameBoard), sessionID);
    }

    /**
     * @param point X and Y coordinates of field that should be occupied
     * @throws Exception When saving result fails
     */
    public void runStep(Point point, IPlayer currentPlayer, IPlayer player1, IPlayer player2, Board gameBoard, int sessionID) throws Exception {
        gameBoard.getBoard()[point.x][point.y] = currentPlayer == player1?FieldStatus.TakenByPlayer1:FieldStatus.TakenByPlayer2;
        if (this.getGameStatus(gameBoard) != GameStatus.InProgress) {this.saveGameResult(gameBoard, sessionID);}
        }
}
