/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdcollection.dao;
import dvdcollection.dto.DVD;
import java.util.List;
/**
 *
 * @author apprentice
 */
public interface DVDCollectionDao {
    
    DVD addNewDVD(String title, DVD dvd) throws DVDCollectionDaoException;
    DVD removeOldDVD(String title) throws DVDCollectionDaoException;
    DVD editOldDVD(DVD editedDVD) throws DVDCollectionDaoException;
    List<DVD> listAllDVDs() throws DVDCollectionDaoException;
    DVD findOldDVD(String title) throws DVDCollectionDaoException;
    DVD displayInformation(String title) throws DVDCollectionDaoException;
    
}
