/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDaoPersistenceException;
import com.sg.vendingmachine.dto.VendingItem;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface VendingMachineService {
    
    List<VendingItem> getVendingMachineItems() throws VendingMachineDaoPersistenceException;
    BigDecimal addToTotal(BigDecimal amount);
    VendingItem getItem(VendingItem item, BigDecimal total) throws
            VendingMachineInventoryValidationException,
            VendingMachineCostValidationException,
            VendingMachineDaoPersistenceException;
    Map<String,Integer> getChange(VendingItem item, BigDecimal total) throws
            VendingMachineDaoPersistenceException;
    void validateAdmin(String passowrd)throws VendingMachineDaoPersistenceException;
    VendingItem checkItem(VendingItem item);
    BigDecimal checkTotal(BigDecimal total);
    
}
