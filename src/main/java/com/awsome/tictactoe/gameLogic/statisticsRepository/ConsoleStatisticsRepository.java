package com.awsome.tictactoe.gameLogic.statisticsRepository;


import com.awsome.tictactoe.gameLogic.GameStatus;

import java.sql.SQLException;

public class ConsoleStatisticsRepository implements IStatisticsRepository {


    @Override
    public void saveResult(GameStatus playerResultStatus, int sessionID) throws Exception {

    }

    @Override
    public int startSession(String player1Name, String player2Name) throws Exception {
        return 0;
    }

    @Override
    public SessionResults getSessionResults(int sessionID) throws Exception {
        return null;
    }
}
