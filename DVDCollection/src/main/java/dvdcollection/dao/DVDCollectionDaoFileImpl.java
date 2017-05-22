/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdcollection.dao;

import dvdcollection.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class DVDCollectionDaoFileImpl implements DVDCollectionDao{
    
    public static final String COLLECTION_FILE = "collection.txt";
    public static final String DELIMITER = "::";
    private Map<String, DVD> collection = new HashMap<>();

    @Override
    public DVD addNewDVD(String title, DVD dvd) throws DVDCollectionDaoException{
        DVD newDVD = collection.put(title, dvd);
        writeCollection();
        return newDVD;
    }

    @Override
    public DVD removeOldDVD(String title) throws DVDCollectionDaoException{
        DVD removeDVD = collection.remove(title);
        writeCollection();
        return removeDVD;
    }

    @Override
    public DVD editOldDVD(DVD editedDVD) throws DVDCollectionDaoException{
        String title = "";
        if (editedDVD != null){
            title = editedDVD.getTitle();
            collection.put(title, editedDVD);
            writeCollection();
        }
        return editedDVD;
        
    }

    @Override
    public List<DVD> listAllDVDs() throws DVDCollectionDaoException{
        loadCollection();
        return new ArrayList<DVD>(collection.values());
    }

    @Override
    public DVD findOldDVD(String title) throws DVDCollectionDaoException{
        loadCollection();
        return collection.get(title);
    }

    @Override
    public DVD displayInformation(String title) throws DVDCollectionDaoException{
        loadCollection();
        return collection.get(title);
    }
    
    private void loadCollection() throws DVDCollectionDaoException {
        Scanner myScanner;
        
        try {
            myScanner = new Scanner(new BufferedReader(
                new FileReader(COLLECTION_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDCollectionDaoException("-_- Could not load collection "
            +"data into memory.", e);
        }
        
        String currentLine;
        String[] currentToken;
        
        while (myScanner.hasNextLine()) {
            currentLine = myScanner.nextLine();
            currentToken = currentLine.split(DELIMITER);
            DVD currentDVD = new DVD(currentToken[0]);
            currentDVD.setReleaseDate(currentToken[1]);
            currentDVD.setMpaaRating(currentToken[2]);
            currentDVD.setDirectorName(currentToken[3]);
            currentDVD.setStudio(currentToken[4]);
            currentDVD.setUserNote(currentToken[5]);
            
            collection.put(currentDVD.getTitle(), currentDVD);
        }
        myScanner.close();
    }
    
    private void writeCollection() throws DVDCollectionDaoException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(COLLECTION_FILE));
        } catch (IOException e) {
            throw new DVDCollectionDaoException("Could not save DVD data.", e);
        }
        
        List<DVD> dvdList = listAllDVDs();
        for (DVD d : dvdList) {
            out.println(d.getTitle() + DELIMITER + d.getReleaseDate() 
            + DELIMITER + d.getMpaaRating() + DELIMITER + d.getDirectorName() 
            + DELIMITER + d.getStudio() + DELIMITER + d.getUserNote());
            
            out.flush();
        }
        out.close();
    }
    
    
    
    
    
}
