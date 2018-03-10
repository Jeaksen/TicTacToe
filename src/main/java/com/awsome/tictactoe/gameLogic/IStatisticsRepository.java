package com.awsome.tictactoe.gameLogic;

public interface IStatisticsRepository {

    void saveResult(String playerName, PlayerResultStatus playerResultStatus);
}
