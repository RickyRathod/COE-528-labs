
package project;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
/**
 *
 * @author yoloi
 */

/*
OVERVIEW: Constructs a Manager object with STATIC FINAL username, password.

REQUIRES: The username, password, and initial balance must not be null.
MODIFIES: Sets the username, password to STATIC and FINAL.
EFFECTS: Initializes the Manager object with the provided username, password.
*/

public class Manager {

    private static final String USERNAME = "Admin";
    private static final String PASSWORD = "Admin";
    private static final String ROLE = "Manager";

    private String username;
    private String password;
    private String role;

    public Manager(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    /*
    REQUIRES: The username and password to be non-null.
    EFFECTS: Returns true if the provided username and password match the customer's credentials, false otherwise.
    */
    
    public boolean LoginAuthenticate(String username, String password) {
        // Check if the provided username and password match the hardcoded values
        return USERNAME.equals(username) && PASSWORD.equals(password);
    }
    
    /*
    REQUIRES: Username and password to be null 
    EFFECTS: Returns true if the username and password are not null or empty, false otherwise.
     */
    
    public boolean repOk() {
        if (USERNAME.equals("") || PASSWORD.equals("")) {
            return false;
        }
        return true;
    }
    
    //EFFECTS: Returns true indicating successful logout.
    
    public void logout() {
        System.out.println("Logged out successfully");
    }
    
}
