package com.awsome.tictactoe.view;

import com.awsome.tictactoe.gameLogic.Board;
import com.awsome.tictactoe.gameLogic.player.IPlayer;

import java.awt.*;

public class WebView implements IView {

    private String[][] boardClasses;
    private String player2FieldValue;
    private String player1FieldValue;

    public WebView() {
        boardClasses = new String[Board.size][Board.size];
        this.resetBoard();
        player1FieldValue = "o_field";
        player2FieldValue = "x_field";
    }

    @Override
    public void updateView(IPlayer currentPlayer, Board board) {
        for (int i = 0; i < Board.size; i++) {
            for (int j = 0; j < Board.size; j++) {
                switch (board.getBoard()[i][j]){
                    case TakenByPlayer2:
                        boardClasses[i][j] = player2FieldValue;
                        break;
                    case TakenByPlayer1:
                        boardClasses[i][j] = player1FieldValue;
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

    public void resetBoard(){
        for (int i = 0; i < Board.size; i++) {
            for (int j = 0; j < Board.size; j++) {
                boardClasses[i][j] = "empty_field";
            }
        }
        String temp = this.player1FieldValue;
        player1FieldValue = player2FieldValue;
        player2FieldValue = temp;
    }
}
