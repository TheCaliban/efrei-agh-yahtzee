package pl.edu.agh.playerSide;

import pl.edu.agh.playerSide.Scoreboard;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Player {
    private String name;
    private Scoreboard sc;


    public Player(String name) {
        this.name = name;
        sc = new Scoreboard();
    }

    public void storeHistory() throws IOException {

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("game_history.yahtzee"));
        oos.writeObject(sc);

        oos.close();
    }

    public Scoreboard readHistory() throws IOException, ClassNotFoundException {

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("game_history.yahtzee"));

        Scoreboard tmp_sc = (Scoreboard) ois.readObject();

        ois.close();

        return tmp_sc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap getScoresTab() {
        return this.sc.getScoresTab();
    }

    public void setScoresTab(HashMap scores) {
        sc.setScoresTab(scores);
    }

    public Scoreboard getScoreboard() {
        return this.sc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) &&
                Objects.equals(sc, player.sc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sc);
    }

    @Override
    public String toString() {
        return "Player: " + this.name + "\n" + this.sc.toString();
    }
}