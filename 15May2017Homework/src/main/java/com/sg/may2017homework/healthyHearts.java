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

public class healthyHearts {
    
    public static void main(String[] args) {
        
        Scanner myScanner = new Scanner(System.in);
        System.out.print("What is your age? ");
        String stringAnswer = myScanner.nextLine();
        int intAnswer = Integer.parseInt(stringAnswer);
        double dubAnswer = Double.parseDouble(stringAnswer);
        
        
        int maxHeartRate = 220 - intAnswer;
        float tempMaxHeartRate = (float)(maxHeartRate);
        float tempEndingHRZone = tempMaxHeartRate*0.85f;
        float tempStartingHRZone = tempMaxHeartRate*0.50f;
        int endingHRZone = Math.round(tempEndingHRZone);
        int startingHRZone = Math.round(tempStartingHRZone);
        
        System.out.println("Your maximum heart rate should be "
            + maxHeartRate + " beats per minute");
        System.out.println("Your target HR Zone is " + startingHRZone 
            + " - " + endingHRZone + " beats per minute");
        
    }
    
}
