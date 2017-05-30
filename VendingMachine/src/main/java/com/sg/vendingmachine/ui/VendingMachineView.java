/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Currency;
import com.sg.vendingmachine.dto.VendingItem;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class VendingMachineView {
    
    private UserIO io;
    
    public VendingMachineView (UserIO io){
        this.io = io;
    }
    
    public void displayWelcome() {
        io.print("Welcome to Tommy's Vending Machine!!!");
        io.print("=====================================\n");
    }
    
    public void displayGoodBye() {
        io.print("GOOD-BYE");
    }
    
    public void displayUnkownCommand() {
        io.print("UNKNOWN COMMAND!!!");
    }
    
    public void displayErrorMessage(String message) {
        io.print(message);
    }
    
    public void displayMenu(List<VendingItem> items) {
        io.print("Snacks and Drinks");
        io.print("=================");
        int count = 0;
        for (VendingItem snack : items){
            count++;
            io.print(count+ ". " +snack.getName() + ": " + snack.getCost());
        }
    }
    
    public void displayMachineMenu() {
        io.print("\nThese are the following Options");
        io.print("=================================");
        io.print("1. Add a penny");
        io.print("2. Add a Nickel");
        io.print("3. Add a Dime");
        io.print("4. Add a Quarter");
        io.print("5. Add a Dollar");
        io.print("6. Add Five Dollars");
        io.print("7. Add Ten Dollars");
        io.print("8. Select an Item");
        io.print("9. Quit and Recieve Change Back");
        io.print("10. Administrator ReStock Machine");
        io.print("==================================");
    }
    
    public int getMenuSelection() {
        return io.readInt("What is your selection? ", 1, 10);
    }
    
    public void displayCurrentTotal(BigDecimal total) {
        io.print("You currently have $" + total);
    }
    
    public VendingItem getItemSelection(List<VendingItem> items) {
        int selection = io.readInt("Which item would you like? ", 1, items.size());
        return items.get(selection - 1);       
        
    }
    
    public void displayReturnedChange (Map<String, Integer> changeMap) {
        int count = 0;
        for (int x : changeMap.values()){
            count = count + x;
        }
        if (count == 0){
            io.print("There was no change deposited");
        } else {
            io.print("Here is your item and change.");
            for (String change : changeMap.keySet()) {
                io.print("You got "+ changeMap.get(change) + " " 
                + change + "s");
            }
        }
    }
    
}
