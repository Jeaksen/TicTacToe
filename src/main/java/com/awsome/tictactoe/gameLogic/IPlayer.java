package com.awsome.tictactoe.gameLogic;

import java.awt.*;

public interface IPlayer {

    Point makeMove(Board board);
    String getName();
}
