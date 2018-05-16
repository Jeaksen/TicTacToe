package com.awsome.tictactoe.view;

import com.awsome.tictactoe.gameLogic.Board;
import com.awsome.tictactoe.gameLogic.player.IPlayer;

import java.awt.*;

public class WebView implements IView {

    private String[][] boardClasses;
    private static String player2FieldValue = "x_field";
    private static String player1FieldValue = "o_field";
    private static String EMPTY = "empty_field";

    /**
     * Array of boardClasses is used to store CSS class names which are assigned to fields n the website
     * @param board object which keep the values of fields on the game board
     * @return Array of Strings with CSS classes names assigned to fields chosen by players
     */
    public static String[][] getBoardClasses(Board board) {
        String [][] boardClasses = new String[Board.size][Board.size];
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
                        boardClasses[i][j] = EMPTY;
                        break;
                }
            }
        }
        return boardClasses;
    }

    public WebView() {
        boardClasses = new String[Board.size][Board.size];
        this.resetBoard();
    }

    @Override
    public void updateView(IPlayer currentPlayer, Board board) {
        for (int i = 0; i < Board.size; i++) {
            for (int j = 0; j < Board.size; j++) {
                switch (board.getBoard()[i][j]){
                    case TakenByPlayer2:
                        this.boardClasses[i][j] = player2FieldValue;
                        break;
                    case TakenByPlayer1:
                        this.boardClasses[i][j] = player1FieldValue;
                        break;
                    case Empty:
                        this.boardClasses[i][j] = EMPTY;
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

    public void resetBoard(){
        for (int i = 0; i < Board.size; i++) {
            for (int j = 0; j < Board.size; j++) {
                boardClasses[i][j] = "empty_field";
            }
        }
        String temp = player1FieldValue;
        player1FieldValue = player2FieldValue;
        player2FieldValue = temp;
    }
}
