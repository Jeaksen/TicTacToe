package com.awsome.tictactoe.gameLogic.statisticsRepository;

import com.awsome.tictactoe.gameLogic.PlayerResultStatus;

public class ConsoleStatisticsRepository implements IStatisticsRepository {
    @Override
    public void saveResult(String playerName, PlayerResultStatus playerResultStatus) {
        System.out.println(playerName + " " + playerResultStatus);
    }
}
