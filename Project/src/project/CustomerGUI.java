
package project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 * 
 *
 * @author yoloi
 */
public class CustomerGUI extends Application {

    private static Manager manager;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Customer Login");

        Label nameLabel = new Label("Username:");
        TextField nameField = new TextField();
        Label passwordLabel = new Label("Password:");
        TextField passwordField = new TextField();
        Button loginButton = new Button("Login");

        VBox loginRoot = new VBox(10);
        loginRoot.setPadding(new Insets(10));
        loginRoot.getChildren().addAll(nameLabel, nameField, passwordLabel, passwordField, loginButton);

        Scene loginScene = new Scene(loginRoot, 300, 200);
        primaryStage.setScene(loginScene);
        primaryStage.show();

        // Event handler for login button
        loginButton.setOnAction(e -> {
            String username = nameField.getText();
            String password = passwordField.getText();

            // Authenticate login credentials with manager
            if (manager.LoginAuthenticate(username, password)) {
                // If login successful, transition to customer interface
                primaryStage.close();
                showCustomerInterface(username, level);
            } else {
                // If login failed, display error message
                showError("Invalid login credentials. Please try again.");
            }
        });
    }

    private void showCustomerInterface(String username, int level) {
        Stage customerStage = new Stage();
        customerStage.setTitle("Customer Interface");

        // Load customer information from file
        double balance = 0;

        // Read customer information from file
        File file = new File(username + ".txt");
        if (file.exists()) {
            try ( BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Balance:")) {
                        balance = Double.parseDouble(line.substring(9).trim());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Handle case where file doesn't exist (e.g., customer not found)
            System.err.println("File for customer " + username + " does not exist.");
        }

        // Print out the loaded customer information for debugging
        System.out.println("Loaded customer information:");
        System.out.println("Username: " + username);
        System.out.println("Balance: " + balance);
        System.out.println("Level: " + level);

        Label usernameLabel = new Label("Welcome, " + username + "!");
        Label balanceLabel = new Label("Balance: $" + balance);
        Label levelLabel = new Label("Level: " + level);

        Button depositButton = new Button("Deposit");
        Button withdrawButton = new Button("Withdraw");
        Button purchaseButton = new Button("Make a Purchase");

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(usernameLabel, balanceLabel, levelLabel, depositButton, withdrawButton, purchaseButton);

        Scene scene = new Scene(root, 300, 200);
        customerStage.setScene(scene);
        customerStage.show();

        // Event handlers for buttons
        depositButton.setOnAction(e -> deposit());
        withdrawButton.setOnAction(e -> withdraw());
        purchaseButton.setOnAction(e -> purchase(level));
    }

    private void deposit() {
        // Implement deposit functionality here
        // For simplicity, just print a message
        System.out.println("Deposit button clicked.");
    }

    private void withdraw() {
        // Implement withdrawal functionality here
        // For simplicity, just print a message
        System.out.println("Withdraw button clicked.");
    }

    private void purchase(int level) {
        // Implement purchase functionality here
        // Deduct fee based on the customer's level
        double fee = getFee(level);
        // For simplicity, just print a message with the fee
        System.out.println("Purchase button clicked. Fee: $" + fee);
    }

    private double getFee(int level) {
        // Implement fee calculation based on the customer's level
        // For simplicity, just return a fixed fee based on level
        switch (level) {
            case 1:
                return 5.0; // Example fee for level 1
            case 2:
                return 3.0; // Example fee for level 2
            case 3:
                return 1.0; // Example fee for level 3
            default:
                return 0.0; // No fee for other levels
        }
    }

    private void showError(String message) {
        // Implement error message display (e.g., dialog box)
        System.out.println("Error: " + message);
    }

    public static void main(String[] args) {
        // Instantiate Manager with username, password, and role
        manager = new Manager("Admin", "Admin", "Manager");

        launch(args);
    }
}