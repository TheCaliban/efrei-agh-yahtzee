package pl.edu.agh.gameSide;

import pl.edu.agh.playerSide.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private ArrayList<Player> players;
    private ArrayList<Die> dice;

    public Game(int numPlayers)
    {
        this.dice = new ArrayList<>(5);
        for (int i = 0; i < 5; i++)
        {
            this.dice.add(new Die());
        }

        this.players = new ArrayList<>(numPlayers);
        System.out.println("Please enter the name of the players");
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < numPlayers; i++)
        {
            System.out.println("Player number " + (i + 1));
            String name = sc.nextLine();
            this.players.add(new Player(name));
            System.out.println("The player " + name + " has been added to the game");
            if (i + 1 == numPlayers) {
                System.out.println("All players has been added, have a nice game");
            }
        }
    }

    public Game(ArrayList<Player> players) {
        this.dice = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            this.dice.add(new Die());
        }

        this.players = players;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Die> getDice() {
        return dice;
    }

}
