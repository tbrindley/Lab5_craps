/**
 * Created by Travis Brindley on 6/28/2017.
 *
 * Assignment:  User gets to play Craps
 */

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static void firstPrompt() {
        System.out.print("Press 1 for instructions \n Press 2 to play  ");
        Scanner scan = new Scanner(System.in);
        int selection = scan.nextInt();
        while (selection > 2 || selection < 1) {
            System.out.println("I'm sorry, this is not a valid input. Press 1 to play \n Press 2 to read the instructions");
            selection = scan.nextInt();
        }
        if (selection == 1) {
            instructions();
        }
    }
    private static void instructions() {
        System.out.println("Craps Instructions");
        System.out.println("===================");
        System.out.println("Any Craps: Wins if a 2, 3 or 12 is thrown. Payoff 8:1");
        System.out.println("Any Seven: Wins if a 7 is rolled. Payoff 5:1");
        System.out.println("Eleven: Wins if a 11 is thrown. Payoff 16:1");
        System.out.println("Ace Duece: Wins if a 3 is rolled. Payoff 16:1");
        System.out.println("Aces or Boxcars: Wins if a 2 or 12 is thrown. Payoff 30:1");
        System.out.println("Hardways: The bet on a hardway number wins if it's thrown hard (sum of pairs: 1-1, 3-3, 4-4...) before it's rolled easy and a 7 is thrown. Payoffs: Hard 4 and 10, 8:1; Hard 6 and 8, 10:1");

    }
    private static boolean anyCrap(int dice1, int dice2, boolean anycrap) {
        boolean status = anycrap;

        if (status != false) {  //if the player has already shot a "craps", this if statement will not run.
            int total = dice1 + dice2;
            if (total == 3 || total == 11) {
                status = false;
            }
        } else {
            status = anycrap;
        }
        return status;
    }
    private static boolean shotSeven(int dice1, int dice2, boolean seven) {
        boolean status = seven;
        if (status != false) { //if the player has already shot a seven, this if statement will not run
            int total = dice1 + dice2;
            if (total == 7) {
                status = false;
            }
        } else {
            return status;
        }
        return status;
    }
    private static boolean stillHardway(boolean anycrap, boolean seven) {
        boolean status;
        if (anycrap == false && seven == false) { //checks to see if player can still win, "the hard way"
            status = false;
        } else {
            status = true;
        }
        return status;
    }
    private static String didIWin(int dice1, int dice2, boolean hardway) {

        String output;
        int total = dice1 + dice2;

        if (total == 2) {
            output = "aces!";
        }
        else if (total == 12) {
            output = "boxcars!";
        }
        else if (total == 3) {
            output = "Ace Duece";
        }
        else if (total == 11) {
            output = "Eleven";
        }
        else if(total == 7) {
            output = "Seven!";
        }
        else {
            output = " ";
        }
        if (dice1 == dice2 && hardway) {
            if (total == 4 || total == 10 || total == 6 || total == 8) {
                    output = output + "The hard way!";
                }
            }

        return output;
    }
    private static int rollIt() {
        int dice = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        return dice;
    }
    private static void play() {
        Scanner scan = new Scanner(System.in);
        boolean keepPlaying = true;
        boolean hardway;
        boolean seven = true;
        boolean anycrap = true;

        while (keepPlaying) {

            int dice1 = rollIt();
            int dice2 = rollIt();

            anycrap = anyCrap(dice1, dice2, anycrap);
            seven = shotSeven(dice1, dice2, seven);
            hardway = stillHardway(anycrap, seven);

            String winner = didIWin(dice1, dice2, hardway);


            System.out.printf("\n You rolled a %d and a %d.  %s", dice1, dice2, winner);

            System.out.println("\n Want to roll again?(Y/N)");
            String loop = scan.nextLine();

            while (!loop.equalsIgnoreCase("y") && !loop.equalsIgnoreCase("n")){
                System.out.println("\n Invalid input.  Want to roll again?(Y/N)");
                loop = scan.nextLine();
            }
            
            if(loop.equalsIgnoreCase("n")){
                keepPlaying = false;
            }
        }

    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Grand Circus Casino!");
        firstPrompt();
        System.out.println("Let's play some craps!");

        play();
        System.out.println("Thanks for playing!");

    }
}
