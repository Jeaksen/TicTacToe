package com.awsome.tictactoe.model;

import com.awsome.tictactoe.gameLogic.Board;
import com.awsome.tictactoe.gameLogic.FieldStatus;
import com.awsome.tictactoe.gameLogic.player.HumanPlayer;
import com.awsome.tictactoe.gameLogic.player.IPlayer;
import com.awsome.tictactoe.gameLogic.player.RandomAIPlayer;

public class SessionData {

    private IPlayer player1;
    private IPlayer player2;
    private IPlayer currentPlayer;
    private Board board;
    private int sessionID;

    public SessionData() {
    }

    public SessionData(String player1, String player2, String currentPlayer, FieldStatus[][] board, int sessionID) {
        this.player1 = new HumanPlayer(player1);
        this.player2 = new RandomAIPlayer(player2);
        this.currentPlayer = currentPlayer.equals(player1)? this.player1:this.player2;
        this.board = new Board(board);
        this.sessionID = sessionID;
    }

    public IPlayer getPlayer1() {
        return player1;
    }

    public void setPlayer1(IPlayer player1) {
        this.player1 = player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = new HumanPlayer(player1);
    }

    public IPlayer getPlayer2() {
        return player2;
    }

    public void setPlayer2(IPlayer player2) {
        this.player2 = player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = new HumanPlayer(player2);
    }

    public IPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(IPlayer currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer.equals(this.player1.getName())? this.player1:this.player2;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setBoard(FieldStatus[][] board) {
        this.board = new Board(board);
    }

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }
}


