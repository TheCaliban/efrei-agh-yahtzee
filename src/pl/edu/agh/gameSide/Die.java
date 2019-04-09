package pl.edu.agh.gameSide;

import java.util.Random;

public class Die {

    private int value;
    private final int idDie;
    private static int c = 0;

    public Die() {
        if (c >= 5) c = 0;
        c += 1;
        this.value = 0;
        this.idDie = c;
    }

    public int rollTheDie() {
        Random rng = new Random();
        this.value = rng.nextInt(6) + 1;
        return value;

    }

    public int getValue() {
        return this.value;
    }

    public int getIdDie() {
        return idDie;
    }

    @Override
    public String toString() {
        return "Die number " + this.idDie +
                "{value=" + value +
                '}';
    }
}