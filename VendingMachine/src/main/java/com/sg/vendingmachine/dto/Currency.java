/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author apprentice
 */
public class Currency {
    
    private static final BigDecimal penny = new BigDecimal("0.01");
    private static final BigDecimal nickel = new BigDecimal("0.05");
    private static final BigDecimal dime = new BigDecimal("0.10");
    private static final BigDecimal quarter = new BigDecimal("0.25");
    private static final BigDecimal dollar = new BigDecimal("1.00");
    private static final BigDecimal fiveDollar = new BigDecimal("5.00");
    private static final BigDecimal tenDollar = new BigDecimal("10.00");
    private BigDecimal total;

    public BigDecimal getPenny() {
        return penny;
    }

    public BigDecimal getNickel() {
        return nickel;
    }

    public BigDecimal getDime() {
        return dime;
    }

    public BigDecimal getQuarter() {
        return quarter;
    }

    public BigDecimal getDollar() {
        return dollar;
    }

    public BigDecimal getFiveDollar() {
        return fiveDollar;
    }

    public BigDecimal getTenDollar() {
        return tenDollar;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = new BigDecimal(total);
    }
    
    
    
    
    
    
    
}
