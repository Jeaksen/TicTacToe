package com.awsome.tictactoe.gameLogic.statisticsRepository;

import com.awsome.tictactoe.gameLogic.GameStatus;

public interface IStatisticsRepository {

    void saveResult(GameStatus playerResultStatus) throws Exception;

    void startSession(String player1Name, String player2Name) throws Exception;

    SessionResults getSessionResults(String playerName) throws Exception;
}
