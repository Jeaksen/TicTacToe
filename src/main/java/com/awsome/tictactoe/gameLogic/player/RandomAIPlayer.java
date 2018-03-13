package com.awsome.tictactoe.gameLogic.player;

import com.awsome.tictactoe.gameLogic.Board;
import com.awsome.tictactoe.gameLogic.FieldStatus;

import java.awt.*;
import java.util.Random;

public class RandomAIPlayer implements IPlayer {

    private String name;

    public RandomAIPlayer(String name) {
        this.name = name;
    }

    @Override
    public Point makeMove(Board board) {
        Random rand = new Random();
        int x,y;
        do{
            x = rand.nextInt(board.getBoard().length);
            y = rand.nextInt(board.getBoard().length);
        }while (board.getBoard()[x][y] != FieldStatus.Empty);
        return new Point(x,y);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean shouldWait(){
        return false;
    }
}
