/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.may2017homework;

/**
 *
 * @author apprentice
 */
import java.util.Scanner;
import java.util.Random;

public class rockPaperScissors {

    public static void main(String[] args) {

        Random rGen = new Random();
        int player1Score;
        int player2Score;
        int ties;
        int rounds;
        int count;

        String[] validStrAnswers = {"rock", "paper", "scissors"};

        System.out.println("Let's play Rock, Scissors, Paper!!!!");
        
        for (int redo = 1; redo < 2; redo++){
            player1Score = 0;
            player2Score = 0;
            ties = 0;
            rounds = 0;
            count = 0;
            do {
                String stringRounds = askUser("How many rounds do you want to play? "
                        + "Choose between 1 and 10. ");
                rounds = Integer.parseInt(stringRounds);
                if (checkIntIfValid(rounds) == false) {
                    System.out.println("You did not enter the number of rounds correctly =P");
                    continue;
                }
                System.out.println("Ready? Let's Go!");
                break;
            } while (true);

            do{

                String player1Answer = askUser("Player 1, what is your selection? ");
                if (checkStrIfValid(validStrAnswers, player1Answer) == false) {
                    System.out.println("You selected incorrectly =P");
                    continue;
                }
                int randomChoice = rGen.nextInt(3);
                String player2Answer = validStrAnswers[randomChoice];
                System.out.println("Player 2 chose: " + player2Answer);

                
                int result = playGame(player1Answer, player2Answer);
                if (result == 1){
                    player1Score++;
                }
                else if(result == 2){
                    player2Score++;
                }
                else if(result == 3){
                    ties++;
                }
                else {
                    System.out.println("There was an error: round "+(count +1));
                }
                count++;

                } while (count < rounds);

                System.out.println("Game is finished!!!!");
                if (player1Score > player2Score) {
                    System.out.println("Congrats player 1! You are the winner. You beat a computer.");
                }
                else if (player1Score < player2Score) {
                    System.out.println("Congrats player 2! You are the winner. Too bad computers can't party.");
                }
                else{
                    System.out.println("There was a tie!!!!! that sucks =P");
                }

                System.out.println("Player 1 had " + player1Score + " wins");
                System.out.println("Player 2 had " + player2Score + " wins");
                System.out.println("There were " + ties + " ties");

                int k = 0;
                while (k < 1) {
                    String again = askUser("Would you like to play again? Enter y for 'Yes' and n for 'No'. ");
                    if (again.equals("y")) {
                        System.out.println("Really? OK let's Go!");
                        redo = 0;
                        k++;
                    }
                    else if (again.equals("n")) {
                        System.out.println("Thanks for playing my game, now get out!");
                        k++;

                    }
                    else {
                        System.out.println("You didn't enter a correct answer. It's just y or n buddy.");

                    }
                
            }
            
        }
        
    }
    
    public static String askUser (String prompt) {
        Scanner myScanner = new Scanner(System.in);
        System.out.print(prompt);
        return myScanner.nextLine();
    }
    
    public static boolean checkStrIfValid (String[] input, String answer) {
        for (String str : input) {
            if (answer.equals(str)){
                return true;
            }
        }
        return false;
    }
    
    public static boolean checkIntIfValid (int answer){
        if (answer > 0 && answer < 11){
            return true;
        }
        return false;
    }
    
    public static int playGame (String firstPlayer, String secondPlayer) {
        int player1 = 1;
        int player2 = 2;
        int tie = 3;
        String result = "";
        if (firstPlayer.equals(secondPlayer)){
            return tie;
        }
        switch (firstPlayer) {
            case "rock":
                if (secondPlayer.equals("paper")){
                    return player2;
                }
                else if (secondPlayer.equals("scissors")){
                    return player1;
                }
            case "paper": 
                if (secondPlayer.equals("rock")){
                    return player2;
                }
                else if (secondPlayer.equals("scissors")){
                    return player1;
                }
            case "scissors":
                if (secondPlayer.equals("rock")){
                    return player2;
                }
                else if (secondPlayer.equals("paper")){
                    return player1;
                }
        }
        return 0;
    }
}
    
    

