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
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class VendingMachineDaoTest {

    static VendingMachineDao dao;

    public VendingMachineDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        dao = new VendingMachineDaoMemImpl();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of stockVendingMachine method, of class VendingMachineDao.
     */
    @Test
    public void testStockVendingMachine() throws Exception {

        List<VendingItem> items = dao.stockVendingMachine();
        BigDecimal waterCost = new BigDecimal("3.00");
        assertEquals(items.size(), 6);
        assertEquals(items.get(0).getName(), "Water");
        assertEquals(items.get(0).getInventory(), 3);
        assertTrue(items.get(0).getCost().compareTo(waterCost) == 0);
        dao.getItem(items.get(0));
    }

    /**
     * Test of getVendingMachineItems method, of class VendingMachineDao.
     */
    @Test
    public void testGetVendingMachineItems() throws Exception {
        List<VendingItem> items = dao.getVendingMachineItems();
        BigDecimal waterCost = new BigDecimal("3.00");
        assertEquals(items.size(), 6);
        assertEquals(items.get(0).getName(), "Water");
        assertEquals(items.get(0).getInventory(), 2);
        assertTrue(items.get(0).getCost().compareTo(waterCost) == 0);
    }

    /**
     * Test of addToTotal method, of class VendingMachineDao.
     */
    @Test
    public void testAddToTotal() {
        BigDecimal moneyStarted = new BigDecimal("0.00");

        BigDecimal test1 = new BigDecimal("5.00");
        BigDecimal sum1 = dao.addToTotal(test1);

        BigDecimal test2 = new BigDecimal("0.01");
        BigDecimal sum2 = dao.addToTotal(test2);

        BigDecimal test3 = new BigDecimal("0.006");
        BigDecimal sum3 = dao.addToTotal(test3);
        //moneyStarted = moneyStarted.subtract(sum3);

        assertEquals(moneyStarted.add(test1).setScale(2, RoundingMode.HALF_UP), sum1);
        assertEquals(moneyStarted.add(test2).add(test1).setScale(2, RoundingMode.HALF_UP), sum2);
        assertEquals(moneyStarted.add(test3).add(test2).add(test1).setScale(2, RoundingMode.HALF_UP), sum3);
    }

    /**
     * Test of getChange method, of class VendingMachineDao.
     */
    @Test
    public void testGetChange() throws Exception {
        List<VendingItem> items = dao.getVendingMachineItems();

        BigDecimal total1 = new BigDecimal("10.00");
        BigDecimal total2 = new BigDecimal("0.01");
        BigDecimal total3 = new BigDecimal("0.00");
        BigDecimal total4 = new BigDecimal("3.67");

//        VendingItem water = items.get(0);
        VendingItem noItem = new VendingItem();
        noItem.setName("No Name");
        noItem.setInventory(3);
        noItem.setCost("0.00");

        Map<String, Integer> test1 = dao.getChange(items.get(0), total1);
        Map<String, Integer> expected1 = new HashMap();
        expected1.put("Quarter", 28);
        expected1.put("Dime", 0);
        expected1.put("Nickel", 0);
        expected1.put("Penny", 0);
        Map<String, Integer> test2 = dao.getChange(items.get(0), total2);
        Map<String, Integer> expected2 = new HashMap();
        expected2.put("Quarter", 0);
        expected2.put("Dime", 0);
        expected2.put("Nickel", 0);
        expected2.put("Penny", 1);
        Map<String, Integer> test3 = dao.getChange(items.get(0), total3);
        Map<String, Integer> expected3 = new HashMap();
        expected3.put("Quarter", 0);
        expected3.put("Dime", 0);
        expected3.put("Nickel", 0);
        expected3.put("Penny", 0);
        Map<String, Integer> test4 = dao.getChange(items.get(0), total4);
        Map<String, Integer> expected4 = new HashMap();
        expected4.put("Quarter", 2);
        expected4.put("Dime", 1);
        expected4.put("Nickel", 1);
        expected4.put("Penny", 2);

        Map<String, Integer> test5 = dao.getChange(noItem, total1);
        Map<String, Integer> expected5 = new HashMap();
        expected5.put("Quarter", 40);
        expected5.put("Dime", 0);
        expected5.put("Nickel", 0);
        expected5.put("Penny", 0);

        assertEquals(expected1, test1);
        assertEquals(expected2, test2);
        assertEquals(expected3, test3);
        assertEquals(expected4, test4);
        assertEquals(expected5, test5);
    }

    /**
     * Test of getItem method, of class VendingMachineDao.
     */
    @Test
    public void testGetItem() throws Exception {
        VendingItem test1 = new VendingItem();
        test1.setInventory(3);
        test1 = dao.getItem(test1);
        VendingItem expected1 = new VendingItem();
        expected1.setInventory(2);

        VendingItem test2 = new VendingItem();
        test2.setInventory(0);
        test2 = dao.getItem(test2);
        VendingItem expected2 = new VendingItem();
        expected2.setInventory(0);

        assertTrue(test1.getInventory() == expected1.getInventory());
        assertFalse(test2.getInventory() == expected2.getInventory());
    }

}
