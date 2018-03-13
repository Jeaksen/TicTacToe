package com.awsome.tictactoe.view;

import com.awsome.tictactoe.gameLogic.Board;
import com.awsome.tictactoe.gameLogic.FieldStatus;
import com.awsome.tictactoe.gameLogic.player.IPlayer;

import java.awt.*;
import java.util.Scanner;

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
                if (j<2) System.out.print("|");
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public Point promptForNewMove() {
        int x,y;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Choose your field");
            try {
                System.out.println("Row: ");
                x = Integer.parseInt(sc.nextLine()) - 1;
                if (x > 2 || x < 0) throw new Exception();
                System.out.print("Column: ");
                y = Integer.parseInt(sc.nextLine()) - 1;
                if (y > 2 || y < 0) throw new Exception();
                break;
            } catch (Exception e){
                System.out.println("Not valid input, enter number from 1-3");
                continue;
            }
        }

        return new Point(x,y);
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }
}
