/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.VendingItem;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface VendingMachineDao {
    
    List<VendingItem> stockVendingMachine() throws VendingMachineDaoPersistenceException;
    List<VendingItem> getVendingMachineItems() throws VendingMachineDaoPersistenceException;
    BigDecimal addToTotal(BigDecimal amount);
    Map<String, Integer> getChange(VendingItem item, BigDecimal total) throws
            VendingMachineDaoPersistenceException;
    VendingItem getItem(VendingItem item) throws VendingMachineDaoPersistenceException;
}
