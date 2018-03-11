package com.awsome.tictactoe.view;

import com.awsome.tictactoe.gameLogic.Board;
import com.awsome.tictactoe.gameLogic.IPlayer;

import java.awt.*;

public interface IView {

    void updateView(IPlayer currentPlayer, Board board);

    Point promptForNewMove();
}
