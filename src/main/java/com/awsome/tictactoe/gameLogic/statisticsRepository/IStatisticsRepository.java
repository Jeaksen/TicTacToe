package com.awsome.tictactoe.gameLogic.statisticsRepository;

import com.awsome.tictactoe.gameLogic.GameStatus;

public interface IStatisticsRepository {

    void saveResult(GameStatus playerResultStatus, int sessionID) throws Exception;

    int startSession(String player1Name, String player2Name) throws Exception;

    SessionResults getSessionResults(int sessionID) throws Exception;
}
