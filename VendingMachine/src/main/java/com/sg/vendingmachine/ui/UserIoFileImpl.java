/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class UserIoFileImpl implements UserIO{
    
    @Override
    public void print(String message) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        String answer = stringScanner(prompt);
        String checkAnswer = isValidDecimal(answer);
        double doubleAnswer = Double.parseDouble(checkAnswer);
        return doubleAnswer;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String answer = stringScanner(prompt);
        String checkAnswer = isValidDecimal(answer);
        double doubleAnswer = Double.parseDouble(checkAnswer);
        if (doubleAnswer < min || doubleAnswer > max) {
            print("Invalid Input!!!");
            return readDouble(prompt, min, max);
        }
        return doubleAnswer;
    }

    @Override
    public float readFloat(String prompt) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String answer = stringScanner(prompt);
        String checkAnswer = isValidDecimal(answer);
        float floatAnswer = Float.parseFloat(checkAnswer);
        return floatAnswer;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String answer = stringScanner(prompt);
        String checkAnswer = isValidDecimal(answer);
        float floatAnswer = Float.parseFloat(checkAnswer);
        if (floatAnswer < min || floatAnswer > max) {
            print("Invalid Input!!!");
            return readFloat(prompt, min, max);
        }
        return floatAnswer;
    }

    @Override
    public int readInt(String prompt) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String answer = stringScanner(prompt);
        String checkAnswer = isValidInt(answer);
        int intAnswer = Integer.parseInt(checkAnswer);
        return intAnswer;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String answer = stringScanner(prompt);
        String checkAnswer = isValidInt(answer);
        int intAnswer = Integer.parseInt(checkAnswer);
        if (intAnswer < min || intAnswer > max) {
            print("Invalid Input!!!");
            return readInt(prompt, min, max);
        }
        return intAnswer;
    }

    @Override
    public long readLong(String prompt) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String answer = stringScanner(prompt);
        long longAnswer = Long.parseLong(answer);
        return longAnswer;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String answer = stringScanner(prompt);
        long longAnswer = Long.parseLong(answer);
        if (longAnswer < min || longAnswer > max) {
            readLong(prompt, min, max);
        }
        return longAnswer;
    }

    @Override
    public String readString(String prompt) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String answer = stringScanner(prompt);
        return answer;
    }
    
    @Override
    public String stringScanner (String prompt) {
        Scanner myScanner = new Scanner(System.in);
        System.out.print(prompt + " ");
        String answer = myScanner.nextLine();
        return answer;
    }
    
    public String isValidInt(String input) {
        if (input.equals("")){
            return "-1";
        }
        for (int i = 0; i < input.length(); i++) {
            Character x = input.charAt(i);
            if (!Character.isDigit(x)) {
                return "-1";
            }
        }
        return input;
    }
    
    public String isValidDecimal(String input) {
        if (input.equals("")){
            return "-1";
        }
        for (int i = 0; i < input.length(); i++) {
            Character x = input.charAt(i);
            if (!Character.isDigit(x) || !x.equals('.')) {
                return "-1.0";
            }
        }
        return input;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, int scale) {
        String answer = stringScanner(prompt);
        String checkAnswer = isValidDecimal(answer);
        BigDecimal myBD = new BigDecimal(checkAnswer);
        return myBD.setScale(scale, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, int scale, String min, String max) {
        String answer = stringScanner(prompt);
        String checkAnswer = isValidDecimal(answer);
        if(checkAnswer.compareTo(min) < 0 || checkAnswer.compareTo(max) > 0) {
            print("Invalid Input!!!");
            return readBigDecimal(prompt, scale, min, max);
        }
        BigDecimal myBD = new BigDecimal(checkAnswer);
        return myBD.setScale(scale, RoundingMode.HALF_UP);
    }
    
}
