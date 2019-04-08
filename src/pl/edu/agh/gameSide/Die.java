package pl.edu.agh.gameSide;

import java.util.Random;

public class Die
{

    private int value;
    private static int idDie = 0;

    public Die()
    {
        this.value = 0;
        this.idDie = idDie + 1;
    }

    public int rollTheDie()
    {
        Random rng = new Random();
        this.value = rng.nextInt(6) + 1;
        return value;

    }

    public int getValue()
    {
        return this.value;
    }


    @Override
    public String toString() {
        return "Die{" +
                "value=" + value +
                '}';
    }
}