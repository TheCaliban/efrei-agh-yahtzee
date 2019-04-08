package pl.edu.agh.gameSide;

import pl.edu.agh.playerSide.Player;
import pl.edu.agh.playerSide.Scores;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private ArrayList<Player> players;
    private ArrayList<Die> dice;
    private int turn;

    public Game(int numPlayers)
    {
        this.dice = new ArrayList<>(5);
        for (int i = 0; i < 5; i++)
        {
            this.dice.add(new Die());
        }
        this.turn = 0;

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

    public void nextTurn(){

    }

    public Player getWinner(){
        return null;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Die> getDice() {
        return dice;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String[][] game =  new String[Scores.values().length+1][this.players.size()];
        int t = 0;

        for (Player p: this.players){
            String[] b = p.toString().split("\n");
            for (int j = 0; j < Scores.values().length; j++){
                game[j][t] = b[j];
            }
            t += 1;
        }

        for (int i = 0; i < Scores.values().length; i++){
            for (int j = 0; j < this.players.size(); j++){
                builder.append(game[i][j] + "\t\t");
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}
