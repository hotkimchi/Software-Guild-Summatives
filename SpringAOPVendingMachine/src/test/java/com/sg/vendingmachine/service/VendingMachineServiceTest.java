/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDaoPersistenceException;
import com.sg.vendingmachine.dto.VendingItem;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class VendingMachineServiceTest {
    
    VendingMachineService service;
    
    public VendingMachineServiceTest() {
//        VendingMachineDao dao = new VendingMachineStubDaoImpl();
//        service = new VendingMachineServiceFileImpl(dao);

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("service", VendingMachineServiceFileImpl.class);
    }
    
    @BeforeClass
    public static void setUpClass() {
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
     * Test of getVendingMachineItems method, of class VendingMachineService.
     */
    @Test
    public void testGetVendingMachineItems() throws Exception {
        List<VendingItem> items = service.getVendingMachineItems();
        
        assertTrue(items.size() == 1);
        assertEquals("Water", items.get(0).getName());
    }

    /**
     * Test of addToTotal method, of class VendingMachineService.
     */
    @Test
    public void testAddToTotal() {
        BigDecimal testAmount = new BigDecimal("5.99");
        BigDecimal testSum = new BigDecimal("5.99");
        testAmount = service.addToTotal(testAmount);
        
        assertEquals(testSum, testAmount);
    }

    /**
     * Test of getItem method, of class VendingMachineService.
     */
    @Test
    public void testGetItem() throws Exception {
        VendingItem test1 = new VendingItem();
        test1.setName("Juice");
        test1.setInventory(1);
        test1.setCost("3.45");
        
        BigDecimal testTotal1 = new BigDecimal("5.00");
        
        VendingItem expected1 = service.getItem(test1, testTotal1);
        
        test1.setInventory(test1.getInventory() - 1);
        
        assertEquals(expected1, test1);
    }
    
    @Test
    public void testNullItem() throws Exception{
        
        VendingItem test2 = new VendingItem();
        BigDecimal testTotal1 = new BigDecimal("5.00");
        
        try{
            service.getItem(test2, testTotal1);
            fail("Null Item was not thrown");
        } catch (VendingMachineInventoryValidationException e) {
            return;
        }
        
    }
    
    @Test
    public void testZeroInventory() throws Exception{
        
        VendingItem test3 = new VendingItem();
        test3.setName("Juice");
        test3.setCost("3.45");
        BigDecimal testTotal1 = new BigDecimal("5.00");
        
        try{
            service.getItem(test3, testTotal1);
            fail("0 inventory was not thrown");
        } catch (VendingMachineInventoryValidationException e) {
            return;
        }
        
    }
    
    @Test
    public void testNullCurrency() throws Exception{
        
        VendingItem test1 = new VendingItem();
        test1.setName("Juice");
        test1.setInventory(1);
        test1.setCost("3.45");
        BigDecimal testTotal1 = null;
        
        try{
            service.getItem(test1, testTotal1);
            fail("Null total was not thrown");
        } catch (VendingMachineCostValidationException e) {
            return;
        }
        
    }
    
    @Test
    public void testNoEnoughCurrency() throws Exception{
        
        VendingItem test1 = new VendingItem();
        test1.setName("Juice");
        test1.setInventory(1);
        test1.setCost("6.45");
        BigDecimal testTotal1 = new BigDecimal("5.00");
        
        try{
            service.getItem(test1, testTotal1);
            fail("Less than total was not thrown");
        } catch (VendingMachineCostValidationException e) {
            return;
        }
        
    }

    /**
     * Test of getChange method, of class VendingMachineService.
     */
    @Test
    public void testGetChange() throws Exception {
        VendingItem test1 = new VendingItem();
        test1.setName("Juice");
        test1.setInventory(1);
        test1.setCost("3.45");
        
        BigDecimal testTotal1 = new BigDecimal("5.00");
        
        Map<String, Integer> testMap = new HashMap();
        testMap.put("Quarter", 6);
        testMap.put("Dime", 0);
        testMap.put("Nickel", 1);
        testMap.put("Penny", 0);
        
        Map<String, Integer> serviceMap = service.getChange(test1, testTotal1);
        
        assertEquals(testMap, serviceMap);
    }

    /**
     * Test of validateAdmin method, of class VendingMachineService.
     */
    @Test
    public void testValidateAdmin() throws Exception {
        String test1 = "stockItUp123";
        String test2 = "";
        
        service.validateAdmin(test1);
        try{
            service.validateAdmin(test2);
            fail("incorrect string was not thrown");
        } catch (VendingMachineDaoPersistenceException e) {
            return;
        }
    }

    

//    public class VendingMachineServiceImpl implements VendingMachineService {
//
//        public List<VendingItem> getVendingMachineItems() throws VendingMachineDaoPersistenceException {
//            return null;
//        }
//
//        public BigDecimal addToTotal(BigDecimal amount) {
//            return null;
//        }
//
//        public VendingItem getItem(VendingItem item, BigDecimal total) throws VendingMachineInventoryValidationException, VendingMachineCostValidationException, VendingMachineDaoPersistenceException {
//            return null;
//        }
//
//        public Map<String, Integer> getChange(VendingItem item, BigDecimal total) throws VendingMachineDaoPersistenceException {
//            return null;
//        }
//
//        public void validateAdmin(String passowrd) throws VendingMachineDaoPersistenceException {
//        }
//
//        public VendingItem checkItem(VendingItem item) {
//            return null;
//        }
//
//        public BigDecimal checkTotal(BigDecimal total) {
//            return null;
//        }
//    }
    
}
