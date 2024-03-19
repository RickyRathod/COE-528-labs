
package project;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
/**
 *
 * @author yoloi
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

    public boolean LoginAuthenticate(String username, String password) {
        // Implement authentication logic here
        // For example, you can check if the provided username and password match the manager's credentials
        return this.username.equals(username) && this.password.equals(password);
    }

    public void logout() {
        System.out.println("Logged out successfully");
    }
    
}
