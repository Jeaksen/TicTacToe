package com.awsome.tictactoe.gameLogic;

public class ConsoleStatisticsRepository implements IStatisticsRepository {
    @Override
    public void saveResult(String playerName, PlayerResultStatus playerResultStatus) {
        System.out.println(playerName + " " + playerResultStatus);
    }
}
