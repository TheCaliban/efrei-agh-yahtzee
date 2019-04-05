package pl.edu.agh.main;

import java.util.Scanner;

public class Main {

    private Main()
    {
        menu();
    }

    public static void main(String[] args)
    {
        new Main();
        Die d = new Die();
        System.out.println(d.rollTheDie());
        System.out.println(d.rollTheDie());
        System.out.println(d.rollTheDie());
        System.out.println(d.rollTheDie());
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

    }

    private void checkHistory()
    {

    }
}
