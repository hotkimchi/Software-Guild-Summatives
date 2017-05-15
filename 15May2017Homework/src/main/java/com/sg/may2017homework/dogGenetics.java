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

import java.util.Random;
import java.util.Scanner;

public class dogGenetics {
    
    public static void main(String[] args) {
        
        Scanner myScanner = new Scanner(System.in);
        System.out.println("We can find out the DNA background of your dog with just it's name!");
        System.out.print("What is your dog's name? ");
        String dogName = myScanner.nextLine();
        
        String[][] randomDogBreeds = {
            {"Common Cur", "St.Bernard", "Chihuaha", "King Doberman", "RedNosed Asian Pug"},
            {"Jindo", "poodle", "German Shephard", "Brown Lab", "Grey Hound"},
            {"Wiener Dog", "Bull Dog", "Border Collie", "Golden Retriever", "Mut"},
            {"Chocolate Lab", "Purse Dog", "Mastif", "Black Lab", "Pomeranium"},
            {"Great Dane", "Husky", "Wolf", "Blood Hound", "Seeing-Eye-Dog"}
        };
        
        int[] randomPercentages = assignRandomPercentage();
        String[] randomBreeds = assignRandomBreed(randomDogBreeds);
        
        System.out.println("Well then, I have this highly reliable report on "+ dogName + "'s prestigious "
                + "background right here.");
        System.out.println("\n" + dogName + " is:");
        
        System.out.println("\n" + randomPercentages[0] + "% " + randomBreeds[0]);
        System.out.println(randomPercentages[1] + "% " + randomBreeds[1]);
        System.out.println(randomPercentages[2] + "% " + randomBreeds[2]);
        System.out.println(randomPercentages[3] + "% " + randomBreeds[3]);
        System.out.println(randomPercentages[4] + "% " + randomBreeds[4]);
        
        System.out.println("\nWow, that's QUITE the dog!");
    }
    
    public static int[] assignRandomPercentage () {
        
        Random rGen = new Random();
        int max = 100;
        
        int[] collection = new int[5];
        
        for (int i = 0; i < collection.length; i++) {
            if (i == collection.length-1){
                collection[i] = max;
            }
            else {
                int percentage = rGen.nextInt(max) + 1;
                collection[i] = percentage;
                max = max - percentage;
            }
        }
        return collection;
    }
    
    public static String [] assignRandomBreed (String[][] inputCollection){
        
        Random rGen = new Random();
        String[] newCollection = new String[5];
        
        for (int i =0; i < inputCollection.length; i++){
            int range = inputCollection[i].length;
            newCollection[i] = inputCollection[i][rGen.nextInt(range)];
        }
        return newCollection;
    }
    
    
    
}
