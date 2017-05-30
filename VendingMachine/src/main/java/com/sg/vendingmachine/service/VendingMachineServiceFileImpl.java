/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDaoPersistenceException;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dto.Currency;
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

    public VendingMachineServiceFileImpl(VendingMachineDao dao) {
        this.dao = dao;
    }

    @Override
    public void stockVendingMachine() {
        dao.stockVendingMachine();
    }

    @Override
    public List<VendingItem> getVendingMachineItems() {
        return dao.getVendingMachineItems();
    }

    @Override
    public BigDecimal addToTotal(BigDecimal amount) {
        BigDecimal total = dao.addToTotal(amount);
        return total;
    }

    @Override
    public void getItem(VendingItem item, BigDecimal total) throws
            VendingMachineInventoryValidationException,
            VendingMachineCostValidationException,
            VendingMachineDaoPersistenceException {

        validateItem(item);
        validateCost(item, total);
        dao.getItem(item);
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
    public void validateAdmin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void validateItem(VendingItem item) throws
            VendingMachineInventoryValidationException {
        int howMany = item.getInventory();
        if (howMany == 0) {
            throw new VendingMachineInventoryValidationException(
                    "The selected item is out of stock");
        }

    }

    private void validateCost(VendingItem item, BigDecimal total) throws
            VendingMachineCostValidationException {
        BigDecimal cost = item.getCost();
        if (total == null || total.compareTo(cost) < 0) {
            throw new VendingMachineCostValidationException(
                    "You did not deposit enough money for selected item");
        }
    }

}
