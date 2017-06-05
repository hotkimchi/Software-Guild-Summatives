/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.VendingItem;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class VendingMachineStubDaoImpl implements VendingMachineDao{
    
    private VendingItem onlyItem = new VendingItem();
    private List<VendingItem> oneItem = new ArrayList();
    private BigDecimal dummyTotal = new BigDecimal("0.00");
    private Map<String, Integer> dummyMap = new HashMap();
    
    public VendingMachineStubDaoImpl () {
        onlyItem.setName("Water");
        onlyItem.setInventory(3);
        onlyItem.setCost("5.00");
        oneItem.add(onlyItem);
        
        dummyMap.put("Quarter", 6);
        dummyMap.put("Dime", 0);
        dummyMap.put("Nickel", 1);
        dummyMap.put("Penny", 0);
    }

    @Override
    public List<VendingItem> stockVendingMachine() throws VendingMachineDaoPersistenceException {
        return oneItem;
    }

    @Override
    public List<VendingItem> getVendingMachineItems() throws VendingMachineDaoPersistenceException {
        return oneItem;
    }

    @Override
    public BigDecimal addToTotal(BigDecimal amount) {
        BigDecimal total = dummyTotal;
        total = total.add(amount).setScale(2, RoundingMode.HALF_UP);
        return total;
    }

    @Override
    public Map<String, Integer> getChange(VendingItem item, BigDecimal total) throws VendingMachineDaoPersistenceException {
        return dummyMap;
    }

    @Override
    public VendingItem getItem(VendingItem item) throws VendingMachineDaoPersistenceException {
        int x = item.getInventory() - 1;
        item.setInventory(x);
        return item;
    }
    
}
