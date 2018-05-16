package com.awsome.tictactoe.gameLogic.player;

import com.awsome.tictactoe.gameLogic.Board;
import com.awsome.tictactoe.view.ConsoleView;
import com.awsome.tictactoe.view.IView;

import java.awt.*;

public class HumanPlayer implements IPlayer {


    private String name;

    public HumanPlayer(String name) {
        this.name = name;
    }

    @Override
    public Point makeMove(Board board) {
        return null;
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
