/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDaoPersistenceException;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dto.VendingItem;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class VendingMachineServiceFileImpl implements VendingMachineService {

    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;

    public VendingMachineServiceFileImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public List<VendingItem> getVendingMachineItems() throws VendingMachineDaoPersistenceException{
        return dao.getVendingMachineItems();
    }

    @Override
    public BigDecimal addToTotal(BigDecimal amount) {
        BigDecimal total = dao.addToTotal(amount);
        return total;
    }

    @Override
    public VendingItem getItem(VendingItem item, BigDecimal total) throws
            VendingMachineInventoryValidationException,
            VendingMachineCostValidationException,
            VendingMachineDaoPersistenceException {
        
        validateItem(item);
        validateCost(item, total);
        
        return dao.getItem(item);
    }

    @Override
    public Map<String, Integer> getChange(VendingItem item, BigDecimal total)
            throws VendingMachineDaoPersistenceException {
        Map<String, Integer> daoChangeMap = new HashMap();
        daoChangeMap = dao.getChange(item, total);
        return daoChangeMap;
    }
    
    @Override
    public VendingItem checkItem(VendingItem item) {
        VendingItem nullItem = new VendingItem();
        if (item == null){
            item = nullItem;
            item.setName("notInInventory");
            item.setInventory(0);
            item.setCost("0.00");
        }
        return item;
    }
    
    @Override
    public BigDecimal checkTotal(BigDecimal total) {
        if (total == null){
            total = new BigDecimal("0.00");
        }
        return total.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public void validateAdmin(String password) throws VendingMachineDaoPersistenceException{
        if (password.equals("stockItUp123")){
            dao.stockVendingMachine();
        } else {
            throw new VendingMachineDaoPersistenceException(
                "Password was incorrect!!!");
        }
    }

    private void validateItem(VendingItem item) throws
            VendingMachineInventoryValidationException {
        int howMany = 0;
        if (item != null){
            howMany = item.getInventory();
        } else {
            throw new VendingMachineInventoryValidationException(
                    "There are no items in the vending machine");
        }
        if (howMany == 0) {
            throw new VendingMachineInventoryValidationException(
                    "The selected item is out of stock");
            
        }

    }

    private void validateCost(VendingItem item, BigDecimal total) throws
            VendingMachineCostValidationException {
        BigDecimal cost = item.getCost();
        
        if (total == null || total.compareTo(cost) < 0) {
           throw new  VendingMachineCostValidationException(
                    "Not enough money was deposited for selected item");
           
        }
        
    }
    
//    @Override
//    public String toString(){
//        return exceptionMessage;
//    }
}
