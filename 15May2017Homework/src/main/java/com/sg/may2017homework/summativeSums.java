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
public class summativeSums {
    
    public static void main(String[] args) {
        
        int[][] listOfNumbers = {
            {1, 90, -33, -55, 67, -16, 28, -55, 15},
            {999, -60, -77, 14, 160, 301},
            {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130,
            140, 150, 160, 170, 180, 190, 200, -99}
        };
        
        int sum1 = addNumbers(listOfNumbers[0]);
        int sum2 = addNumbers(listOfNumbers[1]);
        int sum3 = addNumbers(listOfNumbers[2]);
        
        System.out.println("#1 Array Sum: " + sum1);
        System.out.println("#2 Array Sum: " + sum2);
        System.out.println("#3 Array Sum " + sum3);
        
    }
    
    public static int addNumbers (int[] list) {
        int sum = 0;
        for (int x : list){
            sum = sum + x;
        }
        return sum;
    }
}
