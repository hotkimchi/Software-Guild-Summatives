/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.advice;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDaoPersistenceException;
import com.sg.vendingmachine.service.VendingMachineCostValidationException;
import com.sg.vendingmachine.service.VendingMachineInventoryValidationException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author apprentice
 */
public class LoggingAdvice {
    
    VendingMachineAuditDao auditDao;
    VendingMachineCostValidationException eCost;
    VendingMachineInventoryValidationException eInventory;
    
    public LoggingAdvice (VendingMachineAuditDao auditDao){
        this.auditDao = auditDao;
    }
    
    public void createAuditEntry(JoinPoint jp, Throwable exception){
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        
        for(Object currentArg : args){
            auditEntry += currentArg;
        }
        auditEntry+= " " + exception;
        try{
            auditDao.writeAuditEntry(auditEntry);
        } catch (VendingMachineDaoPersistenceException e){
            System.err.println("ERROR: Could not log object to audit file "
                    + "in LoggingAdvice");
        }
    }
}
