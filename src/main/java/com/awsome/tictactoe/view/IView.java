package com.awsome.tictactoe.view;

import com.awsome.tictactoe.gameLogic.Board;
import com.awsome.tictactoe.gameLogic.player.IPlayer;

import java.awt.*;

public interface IView {

    void updateView(IPlayer currentPlayer, Board board);

    Point promptForNewMove();

    void displayMessage(String message);
}
