package com.awsome.tictactoe.gameLogic.statisticsRepository;

import com.awsome.tictactoe.gameLogic.PlayerResultStatus;

public interface IStatisticsRepository {

    void saveResult(String playerName, PlayerResultStatus playerResultStatus);
}
