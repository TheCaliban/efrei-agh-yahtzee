package pl.edu.agh.gameSide;

import pl.edu.agh.playerSide.Player;
import pl.edu.agh.playerSide.Scores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Game {

    private ArrayList<Player> players;
    private ArrayList<Die> dice;
    private int turn;

    public Game(int numPlayers) {
        this.dice = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            this.dice.add(new Die());
        }
        this.turn = 1;

        this.players = new ArrayList<>(numPlayers);
        System.out.println("Please enter the name of the players");
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < numPlayers; i++) {
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

    public void nextTurn() {
        System.out.println("Beginning of the turn " + this.turn);
        System.out.println(this.toString()); //Display the entire table of the game
        Scanner sc = new Scanner(System.in);

        for (Player p : this.players) {
            System.out.println("\nRounds of player " + p.getName());
            boolean[] hand = new boolean[5];
            boolean[] play = new boolean[5];

            for (Die d : this.dice) {
                hand[d.getIdDie() - 1] = false;
                play[d.getIdDie() - 1] = true;
            }

            for (int round = 0; round < 3; round++) { //For loop for the 3 possible round

                for (int i = 0; i < 5; i++) {
                    if (play[i]) {
                        this.dice.get(i).rollTheDie();
                    }
                }

                System.out.println("Round number " + (round + 1));

                String choice = "y";

                if (round != 2) {

                    System.out.println("Dice in the game are:");
                    for (int i = 0; i < 5; i++) {
                        if (play[i]) {
                            System.out.println(this.dice.get(i));
                        }
                    }

                    System.out.println("\nDice in your hand are:");
                    for (int i = 0; i < 5; i++) {
                        if (hand[i]) {
                            System.out.println(this.dice.get(i));
                        }
                    }

                    while (!choice.equals("n")) { //Beginning of the intern decisions into a round

                        System.out.println("Do you want to keep or repose a die ?");
                        System.out.println("Yes [y] or No [n]");
                        String result = sc.nextLine();

                        while (!result.equals("y") && !result.equals("n")) {
                            System.out.println("Please enter y or n");
                            result = sc.nextLine();
                        }

                        choice = result;

                        if (choice.equals("y")) {

                            System.out.println("Do you want to keep [k] or repose [r] a die ?");
                            result = sc.nextLine();

                            while (!result.equals("k") && !result.equals("r")) {//Choice of keep or repose die
                                System.out.println("Please enter k or r");
                                result = sc.nextLine();
                            }

                            switch (result) {
                                case "k": //Case if you want to keep a die
                                    System.out.println("Please enter the number of the die you want to keep");
                                    int kee = sc.nextInt();

                                    if (kee > 5 || kee < 1) {
                                        System.out.println("Please enter a number between 1 and 5 included");
                                        kee = sc.nextInt();
                                    }

                                    hand[kee - 1] = true;
                                    play[kee - 1] = false;
                                    break;
                                case "r": //Case if you want to repose a die
                                    System.out.println("Please enter the number of the die you want to repose");
                                    int rep = sc.nextInt();

                                    if (rep > 5 && rep < 1) {
                                        System.out.println("Please enter a number between 1 and 5 included");
                                        rep = sc.nextInt();
                                    }

                                    hand[rep - 1] = false;
                                    play[rep - 1] = true;
                                    break;
                            }
                        }
                    }
                    System.out.println("All dice are:\n" + this.dice);
                    System.out.println("Do you want to stop here ?");
                    System.out.println("Yes [y] or No [n]");
                    String result = sc.nextLine();

                    if (!result.equals("y") && !result.equals("n")) {
                        System.out.println("\nPlease enter y or n");
                        result = sc.nextLine();
                    }

                    if (result.equals("y")) break; //Case if you want to stop before the third round
                }
                System.out.println("All the dice are:\n" + this.dice);
            }

            dice = sortDice();

            boolean scored = false;
            System.out.println("Where do you want to score ?");

            String avaiValues = Scores.getValues().replace("TOTAL, BONUS, ", "").replace(",SCORE", "");

            System.out.println("Please type one of this: \n" + avaiValues + "\n");

            while (!scored) {
                String score = sc.nextLine().toUpperCase();
                Scores finalScore = Scores.ONES; //Definition of finalScore to prevent error warning

                while (!avaiValues.contains(score)) {
                    System.out.println("Please enter a correct score type: ");
                    score = sc.nextLine().toUpperCase();
                }

                for (Scores aScore : Scores.values()) {
                    if (score.equalsIgnoreCase(aScore.name())) {
                        finalScore = aScore;
                    }
                }

                while (!p.getScoresTab().get(finalScore).equals(-1)) {
                    System.out.println("Please enter an unscored score type: ");
                    score = sc.nextLine();
                    for (Scores aScore : Scores.values()) {
                        if (score.equalsIgnoreCase(aScore.name())) {
                            finalScore = aScore;
                        }
                    }
                }

                int count = 0, ref, ref1, ref2, i = 0;
                switch (finalScore) {
                    case ONES:
                        for (Die d : this.dice) {
                            if (d.getValue() == 1) i++;
                        }
                        p.getScoreboard().setOnes(i * 1);
                        break;
                    case TWOS:
                        for (Die d : this.dice) {
                            if (d.getValue() == 2) i++;
                        }
                        p.getScoreboard().setTwos(i * 2);
                        break;
                    case THREES:
                        for (Die d : this.dice) {
                            if (d.getValue() == 3) i++;
                        }
                        p.getScoreboard().setThrees(i * 3);
                        break;
                    case FOURS:
                        for (Die d : this.dice) {
                            if (d.getValue() == 4) i++;
                        }
                        p.getScoreboard().setFours(i * 4);
                        break;
                    case FIVES:
                        for (Die d : this.dice) {
                            if (d.getValue() == 5) i++;
                        }
                        p.getScoreboard().setFives(i * 5);
                        break;
                    case SIXES:
                        for (Die d : this.dice) {
                            if (d.getValue() == 6) i++;
                        }
                        p.getScoreboard().setSixes(i * 6);
                        break;
                    case THREE_OA_KIND:
                        for (Die d : this.dice) {
                            count += d.getValue();
                        }

                        ref = this.dice.get(0).getValue();
                        for (Die d : this.dice) {
                            if (d.getValue() == ref) i++;
                        }
                        if (i >= 3) p.getScoreboard().setThree_oa_kind(count);

                        else {
                            i = 0;
                            int j = 1;
                            while (this.dice.get(j).getValue() != ref) j++;
                            ref1 = this.dice.get(j).getValue();
                            for (Die d : this.dice) {
                                if (d.getValue() == ref1) i++;
                            }
                            if (i >= 3) p.getScoreboard().setThree_oa_kind(count);

                            else {
                                i = 0;
                                j = 2;
                                while (this.dice.get(j).getValue() != ref && this.dice.get(j).getValue() != ref1) j++;
                                ref2 = this.dice.get(j).getValue();
                                for (Die d : this.dice) {
                                    if (d.getValue() == ref2) i++;
                                }
                                if (i >= 3) p.getScoreboard().setThree_oa_kind(count);

                                else p.getScoreboard().setThree_oa_kind(0);
                            }
                        }
                        break;
                    case FOUR_OA_KIND:
                        for (Die d : this.dice) {
                            count += d.getValue();
                        }

                        ref = this.dice.get(0).getValue();
                        for (Die d : this.dice) {
                            if (d.getValue() == ref) i++;
                        }
                        if (i >= 4) p.getScoreboard().setFour_oa_kind(count);

                        else {
                            i = 0;
                            int j = 1;
                            while (this.dice.get(j).getValue() == ref) j++;
                            ref1 = this.dice.get(j).getValue();
                            for (Die d : this.dice) {
                                if (d.getValue() == ref1) i++;
                            }
                            if (i >= 4) p.getScoreboard().setFour_oa_kind(count);

                            else p.getScoreboard().setFour_oa_kind(0);
                        }
                        break;
                    case FULL_HOUSE:
                        ref = this.dice.get(0).getValue();
                        int j = 1;
                        while (this.dice.get(j).getValue() == ref) j++;
                        ref1 = this.dice.get(j).getValue();

                        for (Die d : this.dice) {
                            if (d.getValue() == ref || d.getValue() == ref1) i++;
                        }
                        if (i != 5) {
                            p.getScoreboard().setFull_house(false);
                            break;
                        } else {
                            i = 0;
                            j = 0;
                            for (Die d : this.dice) {
                                if (d.getValue() == ref) i++;
                                if (d.getValue() == ref1) j++;
                            }
                            if ((i == 2 && j == 3) || (i == 3 && j == 2)) p.getScoreboard().setFull_house(true);
                            else p.getScoreboard().setFull_house(false);
                        }
                        break;
                    case SMALL_STRAIGHT:
                        int v1 = 0, v2 = 0, v3 = 0, v4 = 0, v5 = 0, v6 = 0;
                        for (Die d : this.dice) {
                            if (d.getValue() == 1) v1++;
                            if (d.getValue() == 2) v2++;
                            if (d.getValue() == 3) v3++;
                            if (d.getValue() == 4) v4++;
                            if (d.getValue() == 5) v5++;
                            if (d.getValue() == 6) v6++;
                        }
                        if (v1 >= 1 && v2 >= 1 && v3 >= 1 && v4 >= 1)
                            p.getScoreboard().setSmall_straight(true);
                        else if (v2 >= 1 && v3 >= 1 && v4 >= 1 && v5 >= 1)
                            p.getScoreboard().setSmall_straight(true);
                        else if (v3 >= 1 && v4 >= 1 && v5 >= 1 && v6 >= 1)
                            p.getScoreboard().setSmall_straight(true);
                        else
                            p.getScoreboard().setSmall_straight(false);
                        break;
                    case LARGE_STRAIGHT:
                        if (dice.get(0).getValue() == 1 && dice.get(1).getValue() == 2 && dice.get(2).getValue() == 3 && dice.get(3).getValue() == 4 && dice.get(4).getValue() == 5)
                            p.getScoreboard().setLarge_straight(true);
                        else if (dice.get(0).getValue() == 2 && dice.get(1).getValue() == 3 && dice.get(2).getValue() == 4 && dice.get(3).getValue() == 5 && dice.get(4).getValue() == 6)
                            p.getScoreboard().setLarge_straight(true);
                        else
                            p.getScoreboard().setLarge_straight(false);
                        break;
                    case CHANCE:
                        for (Die d : this.dice) {
                            count += d.getValue();
                        }
                        p.getScoreboard().setChance(i);
                        break;
                    case YAHTZEE:
                        ref = this.dice.get(0).getValue();
                        for (Die d : this.dice) {
                            if (d.getValue() == ref) i++;
                        }
                        if (i == 5) {
                            p.getScoreboard().setYahtzee(true);
                        } else p.getScoreboard().setYahtzee(false);
                        break;
                }

                scored = true;

                System.out.println("Scoreboard scored: ");
                System.out.println(p);
            }
        }

        turn += 1;
    }

    public ArrayList<Die> sortDice() {
        for (int i = 0; i < dice.size() - 1; i++) {
            for (int j = i + 1; j < dice.size(); j++) {
                if (dice.get(i).getValue() > dice.get(j).getValue()) {
                    Collections.swap(dice, i, j);
                }
            }
        }
        return dice;
    }

    public Player getWinner() {
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
        String[][] game = new String[Scores.values().length + 1][this.players.size()];
        int t = 0;

        for (Player p : this.players) {
            String[] b = p.toString().split("\n");
            for (int j = 0; j < Scores.values().length; j++) {
                game[j][t] = b[j];
            }
            t += 1;
        }

        for (int i = 0; i < Scores.values().length; i++) {
            for (int j = 0; j < this.players.size(); j++) {
                builder.append(game[i][j] + "\t\t");
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}
