/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.Controller;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoMemImpl;
import com.sg.vendingmachine.dao.VendingMachineDaoPersistenceException;
import com.sg.vendingmachine.service.VendingMachineService;
import com.sg.vendingmachine.service.VendingMachineServiceFileImpl;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIoFileImpl;
import com.sg.vendingmachine.ui.VendingMachineView;

/**
 *
 * @author apprentice
 */
public class App {

    public static void main(String[] args) throws VendingMachineDaoPersistenceException {
        UserIO myIo = new UserIoFileImpl();
        VendingMachineView myView = new VendingMachineView(myIo);
        VendingMachineDao myDao = new VendingMachineDaoMemImpl();
        VendingMachineService myService = new VendingMachineServiceFileImpl(myDao);
        Controller myController = new Controller(myService, myView);
        myController.execute();
    }

}
