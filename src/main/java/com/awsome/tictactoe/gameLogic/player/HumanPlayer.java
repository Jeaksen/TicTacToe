package com.awsome.tictactoe.gameLogic.player;

import com.awsome.tictactoe.gameLogic.Board;
import com.awsome.tictactoe.view.IView;

import java.awt.*;

public class HumanPlayer implements IPlayer {

    private IView view;
    String name;

    public HumanPlayer(IView view, String name) {
        this.view = view;
        this.name = name;
    }

    @Override
    public Point makeMove(Board board) {
        return view.promptForNewMove();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean shouldWait(){
        return true;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
