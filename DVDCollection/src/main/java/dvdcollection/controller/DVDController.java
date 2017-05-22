/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdcollection.controller;

import dvdcollection.ui.*;
import dvdcollection.dao.*;
import dvdcollection.dto.DVD;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class DVDController {

    private DVDCollectionDao dao;
    private DVDCollectionView view;

    public DVDController(DVDCollectionDao dao, DVDCollectionView view) {
        this.dao = dao;
        this.view = view;
    }

    public void execute() {
        boolean backToMenu = true;

        view.displayWelcome();
        try{
        while (backToMenu) {
            view.displayMenu();
            int choice = getMenuSelection();
            switch (choice) {
                case 1:
                    addDVD();
                    break;
                case 2:
                    removeDVD();
                    break;
                case 3:
                    editDVD();
                    break;
                case 4:
                    listDVDs();
                    break;
                case 5:
                    displayDVD();
                    break;
                case 6:
                    findDVD();
                    break;
                case 7:
                    backToMenu = false;
                    break;
                default:
                    unknownCommand();
            }
            
        }
        view.displayGoodBye();
        } catch (DVDCollectionDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private void unknownCommand() {
        view.displayUnknownCommand();
    }
    
    private int getMenuSelection() {
        return view.getSelection();
    }
    
    private void addDVD() throws DVDCollectionDaoException{
        DVD newDVD = view.getNewDVD();
        dao.addNewDVD(newDVD.getTitle(), newDVD);
        view.displayAddDVDSuccess();
    }
    
    private void removeDVD() throws DVDCollectionDaoException{
        view.displayRemoveDVD();
        String title = askTitle();
        DVD removeDVD = dao.removeOldDVD(title);
        view.displayRemoveDVDSuccess(removeDVD);
    }
    
    private void editDVD() throws DVDCollectionDaoException{
        view.displayEditDVD();
        String title = askTitle();
        DVD findDVD = dao.findOldDVD(title);
        DVD editDVD = view.askForEditions(title, findDVD);
        dao.removeOldDVD(title);
        editDVD = dao.editOldDVD(editDVD);
        view.displayEditDVDSuccess(editDVD);
        
    }
    
    private void listDVDs() throws DVDCollectionDaoException{
        List<DVD> list = dao.listAllDVDs();
        view.displayDVDList(list);
    }
    
    private void displayDVD() throws DVDCollectionDaoException{
        view.displayDVDDisplay();
        String title = askTitle();
        DVD displayDVD = dao.displayInformation(title);
        view.displayDVDinfo(displayDVD);
    }
    private void findDVD() throws DVDCollectionDaoException{
        view.displaySearchDVD();
        String title = askTitle();
        DVD searchDVD = dao.findOldDVD(title);
        view.displayFoundDVD(searchDVD);
    }
    
    private String askTitle() {
        return view.getTitle();
    }
}
