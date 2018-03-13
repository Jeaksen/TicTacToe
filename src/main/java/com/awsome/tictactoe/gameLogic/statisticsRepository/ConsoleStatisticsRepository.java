package com.awsome.tictactoe.gameLogic.statisticsRepository;


import com.awsome.tictactoe.gameLogic.GameStatus;

import java.sql.SQLException;

public class ConsoleStatisticsRepository implements IStatisticsRepository {

    @Override
    public void saveResult(GameStatus playerResultStatus) throws SQLException{

    }

    @Override
    public void startSession(String player1Name, String player2Name) throws SQLException{

    }

    @Override
    public SessionResults getSessionResults(String playerName)throws SQLException {
        return null;
    }
}
