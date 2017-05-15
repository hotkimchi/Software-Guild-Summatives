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
import java.lang.Number;

public class healthyHearts {
    
    public static void main(String[] args) {
        
        Scanner myScanner = new Scanner(System.in);
        System.out.print("What is your age? ");
        String stringAnswer = myScanner.nextLine();
        int intAnswer = Integer.parseInt(stringAnswer);
        double dubAnswer = Double.parseDouble(stringAnswer);
        
        
        int maxHeartRate = 220 - intAnswer;
        int endingHRZone = (int)(maxHeartRate*0.85f); //rounds down
        int startingHRZone = endingHRZone - 50;
        
        System.out.println("Your maximum heart rate should be "
            + maxHeartRate + " beats per minute");
        System.out.println("Your target HR Zone is " + startingHRZone 
            + " - " + endingHRZone + " beats per minute");
        
    }
    
}
