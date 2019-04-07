package pl.edu.agh.playerSide;

import pl.edu.agh.playerSide.Scoreboard;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Player
{
    private String name;
    private Scoreboard sc;


    public Player(String name)
    {
        this.name = name;
        sc = new Scoreboard();
    }

    public void storeHistory() throws IOException
    {
        FileOutputStream fileOutput = new FileOutputStream("game_history.yahtzee");
        ObjectOutputStream oos = new ObjectOutputStream(fileOutput);

        oos.writeObject(sc);

        oos.close();
    }

    public ArrayList<Scoreboard> readHistory() throws IOException, ClassNotFoundException {

        FileInputStream fileInput = new FileInputStream("game_history.yahtzee");
        ObjectInputStream ois = new ObjectInputStream(fileInput);

        ArrayList<Scoreboard> history = new ArrayList<>();

        boolean is_object = true;

        while(is_object)
        {

            /*
             * Fonctionne pas je sais pas pourquoi ....
             * Je verrais ca plus tard
             */
            Scoreboard tmpSb = (Scoreboard) ois.readObject();
            if(tmpSb != null)
            {
                history.add(tmpSb);
            }
            else
            {
                is_object = false;
            }
        }
        ois.close();

        return history;
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

    public Scoreboard getScoreboard()
    {
        return this.sc;
    }

    @Override
    public String toString() {
        return "Player: " + this.name + "\n" + this.sc.toString();
    }
}