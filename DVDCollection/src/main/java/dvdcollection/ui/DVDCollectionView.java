/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdcollection.ui;

import dvdcollection.dto.DVD;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class DVDCollectionView {

    private final UserIO io;

    public DVDCollectionView(UserIO io) {
        this.io = io;
    }

    public void displayWelcome() {
        io.print("Welcome to Tommy's DVD Library!");
    }

    public void displayUnknownCommand() {
        io.print("\nUNKOWN COMMAND");
        io.print("Returning to main menu");
    }

    public void displayMenu() {
        io.print("\nHere our you menu options: ");
        io.print("1. Add DVD");
        io.print("2. Remove DVD");
        io.print("3. Edit DVD Information");
        io.print("4. List DVDs in Library");
        io.print("5. Display DVD Information");
        io.print("6. Find DVD");
        io.print("7. Exit Menu\n");
    }

    public int getSelection() {
        return io.readInt("What is your selection? ", 1, 7);
    }

    public void displayGoodBye() {
        io.print("\nGOOD BYE");
    }

    public void displayAddDVDSuccess() {
        io.print("\nSuccessfully Added new DVD");
    }

    public void displayRemoveDVD() {
        io.print("$$$ REMOVE DVD $$$\n");
    }

    public void displayRemoveDVDSuccess(DVD removeDVD) {
        if (removeDVD != null) {
            io.print("\nSuccesfully removed the DVD");
            io.print("Returning to main menu");
        } else {
            io.print("\nThere was no DVD by that title");
            io.print("Returning to main menu");
        }
    }

    public void displayEditDVD() {
        io.print("$$$ EDIT NEW DVD $$$\n");
    }

    public void displayEditDVDSuccess(DVD editedDVD) {
        if (editedDVD != null) {
            io.print("\nYou have successfully edited the DVD");
            io.print("Returning to main menu");
        } else {
            io.print("\nThere was no DVD by that title");
            io.print("Returning to main menu");
        }
    }

    public void displayDVDDisplay() {
        io.print("$$$ DISPLAY DVD INFO $$$\n");
    }

    public void displaySearchDVD() {
        io.print("$$$ SEARCH FOR DVD $$$\n");
    }

    public String getTitle() {
        return io.readString("What is the title? ");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print(" === ERROR === ");
        io.print(errorMsg);
    }

    public DVD getNewDVD() {
        io.print("$$$ ADD NEW DVD $$$\n");

        String title = io.readString("What is the title of the movie? ");
        DVD newDVD = new DVD(title);
        newDVD.setReleaseDate(io.readString("When was the release date? "));
        newDVD.setMpaaRating(io.readString("What is the movie's rating? "));
        newDVD.setDirectorName(io.readString("What is the director's name? "));
        newDVD.setStudio(io.readString("What is the name of the studio? "));
        newDVD.setUserNote(io.readString("Do you have anything to say in regards to the movie? "));

        return newDVD;

    }

    public DVD askForEditions(String title, DVD editDVD) {
        if (editDVD != null) {
            io.print("Changing details for " + editDVD.getTitle());
            io.print("==============");
            io.print("Released: " + editDVD.getReleaseDate());
            io.print("MPAA Rating: " + editDVD.getMpaaRating());
            io.print("Director's Name: " + editDVD.getDirectorName());
            io.print("Studio: " + editDVD.getStudio());
            io.print("People say: " + editDVD.getUserNote() + "\n");
            
            io.print("=== EDITING OPTIONS ===");
            io.print("1. Title");
            io.print("2. Release Date");
            io.print("3. MPAA RAting");
            io.print("4. Director's Name");
            io.print("5. Studio Name");
            io.print("6. User Note");
            io.print("7. Exit Menu");
            int input;
            boolean keepGoing = true;
            while (keepGoing) {
                input = io.readInt("Enter an editing option (7 to exit) ", 1, 7);
                switch (input) {
                    case 1:
                        editDVD.setTitle(io.readString("Enter the movie's name "));
                        break;
                    case 2:
                        editDVD.setReleaseDate(io.readString("Enter the movie's release "));
                        break;
                    case 3:
                        editDVD.setMpaaRating(io.readString("Enter the movie's MPAA rating "));
                        break;
                    case 4:
                        editDVD.setDirectorName(io.readString("Enter the Director's name "));
                        break;
                    case 5:
                        editDVD.setStudio(io.readString("Enter the studio's name "));
                        break;
                    case 6:
                        editDVD.setUserNote(io.readString("Enter any regards about the movie "));
                        break;
                    case 7:
                        keepGoing=false;
                        break;
                    default:
                        displayUnknownCommand();
                        keepGoing=false;
                }
            }
            
            
            
            
            
            
        }
        return editDVD;
    }

    public void displayDVDList(List<DVD> list) {
        io.print("$$$ TOMMY'S FULL COLLECTION $$$\n");
        if (!list.isEmpty()) {
            for (DVD d : list) {
                io.print(d.getTitle());
                io.print("==============");
                io.print("Released: " + d.getReleaseDate());
                io.print("MPAA Rating: " + d.getMpaaRating());
                io.print("Director's Name: " + d.getDirectorName());
                io.print("Studio: " + d.getStudio());
                io.print("People say: " + d.getUserNote() + "\n");
            }
            io.print("That's the full collection.");
            io.print("Returning to main menu");
        } else {
            io.print("There were no DVDs in the library");
            io.print("Returning to main menu");
        }
    }

    public void displayDVDinfo(DVD displayDVD) {
        if (displayDVD != null) {
            io.print("\n" + displayDVD.getTitle());
            io.print("==============");
            io.print("Released " + displayDVD.getReleaseDate());
            io.print("MPAA Rating " + displayDVD.getMpaaRating());
            io.print("Director's Name " + displayDVD.getDirectorName());
            io.print("Studio " + displayDVD.getStudio());
            io.print("People say " + displayDVD.getUserNote());

            io.print("\nThere's the info you wanted!");
            io.print("Returning to main menu");
        } else {
            io.print("\nThere was no DVD by that title");
            io.print("Returning to main menu");
        }
    }

    public void displayFoundDVD(DVD searchDVD) {
        if (searchDVD != null) {
            io.print("We found your DVD!!!\n");
            io.print(searchDVD.getTitle());
            io.print("==============");
            io.print("\nReturning to main menu");
        } else {
            io.print("\nThere was no DVD by that title");
            io.print("Returning to main menu");
        }
    }
}
