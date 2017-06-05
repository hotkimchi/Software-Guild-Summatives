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
    
    private static final BigDecimal PENNY = new BigDecimal("0.01");
    private static final BigDecimal NICKEL = new BigDecimal("0.05");
    private static final BigDecimal DIME = new BigDecimal("0.10");
    private static final BigDecimal QUARTER = new BigDecimal("0.25");
    private static final BigDecimal DOLLAR = new BigDecimal("1.00");
    private static final BigDecimal FIVE_DOLLAR = new BigDecimal("5.00");
    private static final BigDecimal TEN_DOLLAR = new BigDecimal("10.00");
    private BigDecimal total;

    public BigDecimal getPenny() {
        return PENNY;
    }

    public BigDecimal getNickel() {
        return NICKEL;
    }

    public BigDecimal getDime() {
        return DIME;
    }

    public BigDecimal getQuarter() {
        return QUARTER;
    }

    public BigDecimal getDollar() {
        return DOLLAR;
    }

    public BigDecimal getFiveDollar() {
        return FIVE_DOLLAR;
    }

    public BigDecimal getTenDollar() {
        return TEN_DOLLAR;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = new BigDecimal(total);
    }
    
    @Override
    public String toString() {
        return "User entered: " + getTotal() + " ";
    }
    
    
    
    
}
