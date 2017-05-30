/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author apprentice
 */
public class VendingItem {
    
    private String name;
    private int inventory;
    private BigDecimal cost;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = new BigDecimal(cost);
    }
    
    
    
}
