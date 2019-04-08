package pl.edu.agh.main;

import pl.edu.agh.gameSide.Die;
import pl.edu.agh.gameSide.Game;
import pl.edu.agh.playerSide.Player;
import pl.edu.agh.playerSide.Scores;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private Main()
    {
        menu();
    }

    public static void main(String[] args)
    {
        new Main();

//        Player p1 = new Player("Jean");
//        Player p2 = new Player("John");
//
//        p1.getScoreboard().setOnes(6);
//        p1.getScoreboard().setFives(10);
//
//        p2.getScoreboard().setYahtzee();
//
//
//        System.out.println(p1.getScoreboard());
//
//        ArrayList<Player> players = new ArrayList<>();
//        players.add(new Player("John"));
//        players.add(new Player("Jean"));
//
//        Game g = new Game(players);
//
//        for (Die d:g.getDice()) {
//            d.rollTheDie();
//            System.out.println(d.getIdDie());
//        }
//
//        System.out.println(g.getDice());
//
//        System.out.println(g);
//
//        try {
//            p1.storeHistory();
//            p1.setScoresTab(null);
//            System.out.println(p1.readHistory());
//
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//            System.out.println("Try serialization error");
//        }
    }

    private void menu()
    {
        System.out.println("Welcome on our Yahtzee Game");

        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while(choice != 3)
        {

            System.out.println("Pick an option below:");
            System.out.println("1 - Play the game\n2 - Check my history\n3 - Exit");

            choice = sc.nextInt();

            switch (choice)
            {
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

    private void startTheGame()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the number of players in the game, up to 10: ");
        int n = sc.nextInt();

        while (n > 10){
            System.out.println("Number up to 10 please: ");
            n = sc.nextInt();
        }
        while (n < 2){
            System.out.println("Give at least 2 players");
            n = sc.nextInt();
        }

        Game game = new Game(n);
//
//        for (int i = 0; i < 13; i++) {
//            game.nextTurn();
//        }

        game.nextTurn();

    }

    private void checkHistory()
    {

    }
}
