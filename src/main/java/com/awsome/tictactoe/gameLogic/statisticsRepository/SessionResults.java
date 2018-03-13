package com.awsome.tictactoe.gameLogic.statisticsRepository;

public class SessionResults {

    private int wins_player1;
    private int wins_player2;
    private int ties;
    private String name_player1;
    private String name_player2;

    public SessionResults(int wins_player1, int wins_player2, int ties, String name_player1, String name_player2) {
        this.wins_player1 = wins_player1;
        this.wins_player2 = wins_player2;
        this.ties = ties;
        this.name_player1 = name_player1;
        this.name_player2 = name_player2;
    }

    public int getWins_player1() {
        return wins_player1;
    }

    public void setWins_player1(int wins_player1) {
        this.wins_player1 = wins_player1;
    }

    public int getWins_player2() {
        return wins_player2;
    }

    public void setWins_player2(int wins_player2) {
        this.wins_player2 = wins_player2;
    }

    public int getTies() {
        return ties;
    }

    public void setTies(int ties) {
        this.ties = ties;
    }

    public String getName_player1() {
        return name_player1;
    }

    public void setName_player1(String name_player1) {
        this.name_player1 = name_player1;
    }

    public String getName_player2() {
        return name_player2;
    }

    public void setName_player2(String name_player2) {
        this.name_player2 = name_player2;
    }
}
