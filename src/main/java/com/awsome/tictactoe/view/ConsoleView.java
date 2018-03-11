package com.awsome.tictactoe.view;

import com.awsome.tictactoe.gameLogic.Board;
import com.awsome.tictactoe.gameLogic.FieldStatus;
import com.awsome.tictactoe.gameLogic.IPlayer;

import java.awt.*;

public class ConsoleView implements IView {
    @Override
    public void updateView(IPlayer currentPlayer, Board board) {
        System.out.println(currentPlayer.getName());
        FieldStatus[][] gameBoard = board.getBoard();
        for (int i =0; i < gameBoard.length; i++){
            for (int j=0; j < gameBoard[i].length; j++){
                if (gameBoard[i][j] == FieldStatus.TakenByPlayer2){
                    System.out.print("O");}
                else {
                    if(gameBoard[i][j] == FieldStatus.TakenByPlayer1) {
                        System.out.print("X");
                    }else {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
        }
    }

    @Override
    public Point promptForNewMove() {
        return null;
    }
}
