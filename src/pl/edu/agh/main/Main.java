package pl.edu.agh.main;

import pl.edu.agh.gameSide.Die;
import pl.edu.agh.gameSide.Game;
import pl.edu.agh.playerSide.Player;
import pl.edu.agh.playerSide.Scores;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Main {

    private Main() {
        menu();
    }

    public static void main(String[] args) {
        new Main();
    }

    private void menu() {
        System.out.println("Welcome on our Yahtzee Game");

        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (choice != 3) {

            System.out.println("Pick an option below:");
            System.out.println("1 - Play the game\n2 - Check my history\n3 - Exit");

            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                choice = 0;
                sc.next();
            }


            switch (choice) {
                case 1:
                    System.out.println("Start the game\n");
                    startTheGame();
                    break;
                case 2:
                    System.out.println("You check your history\n");
                    checkHistory();
                    break;
                case 3:
                    System.out.println("You leave the game\n");
                    break;
                default:
                    System.out.println("I have not understand what you wrote. Please try again !\n");
                    break;
            }
        }

    }

    private void startTheGame() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the number of players in the game, up to 10: ");

        int n = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                n = sc.nextInt();
                validInput = true;
            } catch (Exception e) {
                System.out.println("Please enter a number.");
                sc.next();
            }
        }

        while ((n < 2) || (n > 10)) {
            System.out.println("Number between 2 and 10 please: ");
            validInput = false;
            while (!validInput) {
                try {
                    n = sc.nextInt();
                    validInput = true;
                } catch (Exception e) {
                    System.out.println("Please enter a number.");
                    sc.next();
                }
            }
        }

        Game game = new Game(n);
        for (int i = 0; i < 13; i++) {
            game.nextTurn();
        }
    }

    private void checkHistory() {

    }
}
