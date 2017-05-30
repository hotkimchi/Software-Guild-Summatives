/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachineDaoPersistenceException;
import com.sg.vendingmachine.dto.Currency;
import com.sg.vendingmachine.dto.VendingItem;
import com.sg.vendingmachine.service.VendingMachineCostValidationException;
import com.sg.vendingmachine.service.VendingMachineInventoryValidationException;
import com.sg.vendingmachine.service.VendingMachineService;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class Controller {
    
    private VendingMachineView view;
    private VendingMachineService service;
    private Currency currency = new Currency();
    private boolean returnToMenu = true;
    private int choice;
    List<VendingItem> items;
    
    public Controller (VendingMachineService service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }
    public void execute() throws VendingMachineDaoPersistenceException {
        service.stockVendingMachine();
        items = service.getVendingMachineItems();

        view.displayWelcome();

        view.displayMenu(items);
        view.displayMachineMenu();
        
        BigDecimal penny = currency.getPenny();
        BigDecimal nickel = currency.getNickel();
        BigDecimal dime = currency.getDime();
        BigDecimal quarter = currency.getQuarter();
        BigDecimal dollar = currency.getDollar();
        BigDecimal fiveDollars = currency.getFiveDollar();
        BigDecimal tenDollars = currency.getTenDollar();
        
        while (returnToMenu) {


            choice = menuSelection();
            switch (choice) {
                case 1:
                    addCurrency(penny);
                    break;
                case 2:
                    addCurrency(nickel);
                    break;
                case 3:
                    addCurrency(dime);
                    break;
                case 4:
                    addCurrency(quarter);
                    break;
                case 5:
                    addCurrency(dollar);
                    break;
                case 6:
                    addCurrency(fiveDollars);
                    break;
                case 7:
                    addCurrency(tenDollars);
                    break;
                case 8:
                    enterItemSelection();
                    returnToMenu = false;
                    break;
                case 9:
                    returnCurrency(null, currency.getTotal());
                    returnToMenu = false;
                    break;
                case 10:
                    validateAdmin();
                    break;
                default:
                    unknownCommand();
                    break;
            }
        }
        view.displayGoodBye();
    }
    
    public void addCurrency(BigDecimal amount) {
        BigDecimal currentTotal = service.addToTotal(amount);
        currency.setTotal(currentTotal.toString());
        view.displayCurrentTotal(currentTotal);
    }
    
    public int menuSelection() {
        return view.getMenuSelection(); 
    }
    
    public void enterItemSelection() throws 
            VendingMachineDaoPersistenceException{
        BigDecimal total = currency.getTotal();
        view.displayCurrentTotal(total);
        view.displayMenu(items);
        VendingItem selection = view.getItemSelection(items);
        try {
            service.getItem(selection, total);
        } catch (VendingMachineInventoryValidationException |
            VendingMachineCostValidationException e) {
            view.displayErrorMessage(e.getMessage());
        }
        returnCurrency(selection, total);
        
    }
    
    public void returnCurrency(VendingItem item, BigDecimal total) 
        throws VendingMachineDaoPersistenceException{
        item = service.checkItem(item);
        total = service.checkTotal(total);
        Map<String, Integer> changeMap= new HashMap<>();
        try{
            changeMap = service.getChange(item, total);
        } catch (VendingMachineDaoPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        view.displayReturnedChange(changeMap);
    }
    
    public void validateAdmin() {
        service.validateAdmin();
    }
    
    public void unknownCommand () {
        view.displayUnkownCommand();
    }
}
