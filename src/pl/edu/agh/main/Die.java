package pl.edu.agh.main;

import java.util.Random;

public class Die
{

    private int value;

    public Die()
    {
        this.value = 0;
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

}