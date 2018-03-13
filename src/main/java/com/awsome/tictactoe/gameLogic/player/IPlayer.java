package com.awsome.tictactoe.gameLogic.player;

import com.awsome.tictactoe.gameLogic.Board;

import java.awt.*;

public interface IPlayer {

    Point makeMove(Board board);
    String getName();
    boolean shouldWait();
    void setName(String name);
}
