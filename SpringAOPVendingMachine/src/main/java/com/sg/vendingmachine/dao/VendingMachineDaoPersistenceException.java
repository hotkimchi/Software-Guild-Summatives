/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

/**
 *
 * @author apprentice
 */
public class VendingMachineDaoPersistenceException extends Exception{
    
    public VendingMachineDaoPersistenceException (String message) {
        super(message);
    }
    
    public VendingMachineDaoPersistenceException (String message, Throwable cause) {
        super(message, cause);
    }
    
}
