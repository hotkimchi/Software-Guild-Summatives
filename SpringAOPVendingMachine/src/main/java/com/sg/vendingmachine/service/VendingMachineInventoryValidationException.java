/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

/**
 *
 * @author apprentice
 */
public class VendingMachineInventoryValidationException extends Exception{
    
    
    
    public VendingMachineInventoryValidationException (String message){
        super(message);
    }
    
    public VendingMachineInventoryValidationException(String message, Throwable cause){
        super(message, cause);
    }
    
    
    
}
