/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Currency;
import com.sg.vendingmachine.dto.VendingItem;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class VendingMachineDaoMemImpl implements VendingMachineDao {

    
    private List<VendingItem> items = new ArrayList();
    private BigDecimal moneyInserted = new BigDecimal("0.00");
    private Map<String, String> itemAndCost = new HashMap();
    private Currency currency = new Currency();
    private LocalDate stockDate;
    public static final String COLLECTION_FILE = "vendingstock.txt";
    public static final String DELIMITER = "::";
    
    

    @Override
    public List<VendingItem> stockVendingMachine() throws VendingMachineDaoPersistenceException {
        if (itemAndCost.isEmpty()){
            createMap();
        }
        items.clear();
        for (String itemName : itemAndCost.keySet()) {
            VendingItem item = new VendingItem();
            item.setName(itemName);
            item.setInventory(3);
            item.setCost(itemAndCost.get(itemName));
            items.add(item);
        }
        stockDate = LocalDate.now();
        writeStock();
        return items;
    }

    @Override
    public List<VendingItem> getVendingMachineItems() throws VendingMachineDaoPersistenceException {
        loadStock();
        
        return items;
    }

    @Override
    public BigDecimal addToTotal(BigDecimal amount) {
        moneyInserted = moneyInserted.add(amount).setScale(2, RoundingMode.HALF_UP);
        return moneyInserted;
    }

    @Override
    public Map<String, Integer> getChange(VendingItem item, BigDecimal total)
            throws VendingMachineDaoPersistenceException {
        Map<String, Integer> changeMap = new HashMap();
        BigDecimal zeroSum = new BigDecimal("0.00");
        if (total.compareTo(item.getCost()) >= 0) {
            total = total.subtract(item.getCost().setScale(2, RoundingMode.HALF_UP));
        }
        if (changeMap.isEmpty()) {
            changeMap.put("Quarter", 0);
            changeMap.put("Dime", 0);
            changeMap.put("Nickel", 0);
            changeMap.put("Penny", 0);
        }
        while (total.compareTo(zeroSum) > 0) {

            if (total.setScale(2, RoundingMode.HALF_UP).compareTo(currency.getQuarter()) >= 0) {
                int appendValue = changeMap.get("Quarter");
                appendValue++;
                changeMap.put("Quarter", appendValue);
                total = total.subtract(currency.getQuarter().setScale(2, RoundingMode.HALF_UP));
            } else if (total.setScale(2, RoundingMode.HALF_UP).compareTo(currency.getDime()) >= 0) {
                int appendValue = changeMap.get("Dime");
                appendValue++;
                changeMap.put("Dime", appendValue);
                total = total.subtract(currency.getDime().setScale(2, RoundingMode.HALF_UP));
            } else if (total.setScale(2, RoundingMode.HALF_UP).compareTo(currency.getNickel()) >= 0) {
                int appendValue = changeMap.get("Nickel");
                appendValue++;
                changeMap.put("Nickel", appendValue);
                total = total.subtract(currency.getNickel());
            } else if (total.setScale(2, RoundingMode.HALF_UP).compareTo(currency.getPenny().setScale(2, RoundingMode.HALF_UP)) >= 0) {
                int appendValue = changeMap.get("Penny");
                appendValue++;
                changeMap.put("Penny", appendValue);
                total = total.subtract(currency.getPenny().setScale(2, RoundingMode.HALF_UP));
            } else if (total.setScale(2, RoundingMode.HALF_UP).compareTo(zeroSum) < 0) {
                throw new VendingMachineDaoPersistenceException("ERROR: "
                        + "Change was miscalculated");
            }
        }
        return changeMap;
    }

    @Override
    public VendingItem getItem(VendingItem item) throws VendingMachineDaoPersistenceException {
        int inventoryTotal = item.getInventory();
        int newTotal = inventoryTotal - 1;
        item.setInventory(newTotal);
        writeStock();
        return item;
    }

    private void createMap() {
        itemAndCost.put("Water", "3.00");
        itemAndCost.put("Chips", "1.50");
        itemAndCost.put("Soda", "2.79");
        itemAndCost.put("Candy Bar", "0.75");
        itemAndCost.put("Mystery Ball", "1.85");
        itemAndCost.put("Sandwich", "6.49");
    }

    private void loadStock() throws VendingMachineDaoPersistenceException {
        Scanner myScanner;
        items.clear();

        try {
            myScanner = new Scanner(new BufferedReader(
                    new FileReader(COLLECTION_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachineDaoPersistenceException("-_- Could not load stock "
                    + "data into memory.", e);
        }

        String currentLine;
        String[] currentToken;
        while (myScanner.hasNextLine()) {
            currentLine = myScanner.nextLine();
            currentToken = currentLine.split(DELIMITER);
            VendingItem currentItem = new VendingItem();
            if (currentLine.startsWith("Last Used")){
                stockDate = LocalDate.parse(currentToken[1]);
            }
            if (!currentLine.startsWith("Last Used")) {
                currentItem.setName(currentToken[0]);
                int inventoryStock = Integer.parseInt(currentToken[1]);
                currentItem.setInventory(inventoryStock);
                currentItem.setCost(currentToken[2]);
                items.add(currentItem);
            }
        }
        myScanner.close();
    }

    private void writeStock() throws VendingMachineDaoPersistenceException {
        
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(COLLECTION_FILE));
        } catch (IOException e) {
            throw new VendingMachineDaoPersistenceException("Could not save stock data.", e);
        }

      
        stockDate = LocalDate.now();
        out.println("Last Used" + DELIMITER + stockDate);
        
        for (VendingItem i : items) {
            out.println(i.getName() + DELIMITER + i.getInventory()
                    + DELIMITER + i.getCost());

            out.flush();
        }
        out.close();
    }
    
    


}
