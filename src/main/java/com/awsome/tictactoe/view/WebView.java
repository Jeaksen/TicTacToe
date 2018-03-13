package com.awsome.tictactoe.view;

import com.awsome.tictactoe.gameLogic.Board;
import com.awsome.tictactoe.gameLogic.player.IPlayer;

import java.awt.*;

public class WebView implements IView {

    String[][] boardClasses;

    public WebView() {
        boardClasses = new String[Board.size][Board.size];
        for (int i = 0; i < Board.size; i++) {
            for (int j = 0; j < Board.size; j++) {
                boardClasses[i][j] = "empty_field";
            }
        }
    }

    @Override
    public void updateView(IPlayer currentPlayer, Board board) {
        for (int i = 0; i < Board.size; i++) {
            for (int j = 0; j < Board.size; j++) {
                switch (board.getBoard()[i][j]){
                    case TakenByPlayer2:
                        boardClasses[i][j] = "o_field";
                        break;
                    case TakenByPlayer1:
                        boardClasses[i][j] = "x_field";
                        break;
                    case Empty:
                        boardClasses[i][j] = "empty_field";
                        break;
                }
            }
        }
    }

    @Override
    public Point promptForNewMove() {
        return null;
    }

    @Override
    public void displayMessage(String message) {

    }

    public String[][] getBoardClasses(){
        return boardClasses;
    }
}
