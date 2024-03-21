
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
import java.io.FileWriter;
import java.io.PrintWriter;
/**
 * 
 *
 * @author yoloi
 */
public class CustomerGUI extends Application {
    private static Customer customer;

    @Override
    public void start(Stage primaryStage) {
        
        displayCustomerLogin(primaryStage);
    }
    
    public static void main(String[] args) {
        // Load customer data from files
        // Instantiate Customer objects
        // Start the JavaFX application
        launch(args);
    }
    
    //customers login window
    private void displayCustomerLogin(Stage primaryStage) {
        primaryStage.setTitle("Customer Login");

        Label nameLabel = new Label("Username:");
        TextField nameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
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

            // Authenticate login credentials with customer information
            if (login(username, password)) {
                // If login successful, transition to customer interface
                primaryStage.close();
                Customer customer = loadCustomer(username);
                if (customer != null) {
                    showCustomerInterface(primaryStage, customer);
                } else {
                    showError("Failed to load customer data.");
                }
            } else {
                // If login failed, display error message
                showError("Invalid login credentials. Please try again.");
            }
        });
    }
    
    //after login, customers can see the depost withdrawl and purchace button along with the logout feature
    private void showCustomerInterface(Stage primaryStage, Customer customer) {
        // Display the customer interface using the provided customer object
        // You can now use customer methods to perform operations
        // For example: customer.deposit(amount), customer.withdraw(amount), etc.
        Stage customerStage = new Stage();
        customerStage.setTitle("Customer Interface");

        // Create labels to display username, level, and balance
        Label usernameLabel = new Label("Username: " + customer.getUsername());
        Label levelLabel = new Label("Level: " + customer.getLevel());
        Label balanceLabel = new Label("Balance: $" + customer.getBalance());

        // Create text fields for deposit, withdraw, and purchase amounts
        TextField depositAmountField = new TextField();
        depositAmountField.setPromptText("Enter deposit amount");
        TextField withdrawAmountField = new TextField();
        withdrawAmountField.setPromptText("Enter withdraw amount");
        TextField purchaseAmountField = new TextField();
        purchaseAmountField.setPromptText("Enter purchase amount");

        // Create buttons for deposit, withdraw, and purchase actions
        Button depositButton = new Button("Deposit");
        Button withdrawButton = new Button("Withdraw");
        Button purchaseButton = new Button("Purchase");
        Button logoutButton = new Button("Logout");

        // Event handlers for deposit, withdraw, and purchase buttons
        depositButton.setOnAction(e -> {
            try {
                int amount = Integer.parseInt(depositAmountField.getText());
                customer.deposit(amount);
                balanceLabel.setText("Balance: $" + customer.getBalance());
                depositAmountField.clear();
                updateCustomerFile(customer); // Update customer file after deposit
            } catch (NumberFormatException ex) {
                showError("Invalid deposit amount. Please enter a valid number.");
            }
        });
        
        withdrawButton.setOnAction(e -> {
            try {
                int amount = Integer.parseInt(withdrawAmountField.getText());
                boolean success = customer.withdraw(amount);
                if (success) {
                    balanceLabel.setText("Balance: $" + customer.getBalance());
                    withdrawAmountField.clear();
                    updateCustomerFile(customer); // Update customer file after withdrawal
                } else {
                    showError("Insufficient balance for withdrawal.");
                }
            } catch (NumberFormatException ex) {
                showError("Invalid withdraw amount. Please enter a valid number.");
            }
        });
        
        purchaseButton.setOnAction(e -> {
            try {
                int amount = Integer.parseInt(purchaseAmountField.getText());
                boolean success = customer.purchase(amount);
                if (success) {
                    balanceLabel.setText("Balance: $" + customer.getBalance());
                    purchaseAmountField.clear();
                    updateCustomerFile(customer); // Update customer file after purchase
                } else {
                    showError("Insufficient balance for purchase.");
                }
            } catch (NumberFormatException ex) {
                showError("Invalid purchase amount. Please enter a valid number.");
            }
        });
        
        logoutButton.setOnAction(e -> {
        // Close the current stage (customer interface window)
        customerStage.close();
    });

        // Create a VBox layout to arrange UI components vertically
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(
                usernameLabel, levelLabel, balanceLabel,
                new Label("Deposit:"),
                depositAmountField, depositButton,
                new Label("Withdraw:"),
                withdrawAmountField, withdrawButton,
                new Label("Purchase:"),
                purchaseAmountField, purchaseButton,
                logoutButton               
        );

        // Create a Scene with the root layout
        Scene scene = new Scene(root, 300, 300);

        // Set the scene and show the stage
        customerStage.setScene(scene);
        customerStage.show();
    }
  
    private void showError(String message) {
        System.out.println("Error: " + message);
    }

    private boolean login(String username, String password) {
        // Authenticate customer credentials against stored information
        File file = new File(username + ".txt");
        if (file.exists()) {
            try ( BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Password:")) {
                        String storedPassword = line.substring(10).trim();
                        return storedPassword.equals(password);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false; // Return false if file doesn't exist or password doesn't match
    }
    
    private Customer loadCustomer(String username) {
        File file = new File(username + ".txt");
        if (file.exists()) {
            try ( BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                String password = null;
                int balance = 0; // Initialize balance to some default value
                String level = null;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Password:")) {
                        password = line.substring(10).trim();
                    } else if (line.startsWith("Balance:")) {
                        balance = Integer.parseInt(line.substring(9).trim());
                    } else if (line.startsWith("Level:")) {
                        level = line.substring(7).trim();
                    }
                }
                if (password != null && level != null) {
                    switch (level) {
                        case "Silver Level":
                            return new SIlverCustomer(username, password, balance);
                        case "Gold Level":
                            return new GoldCustomer(username, password, balance);
                        case "Platinum Level":
                            return new PlatinumCustomer(username, password, balance);
                        default:
                            return null; // Invalid level
                    }
                }
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null; // Return null if file doesn't exist or data couldn't be loaded
    }
    
    private void updateCustomerFile(Customer customer) {
    String username = customer.getUsername();
    File file = new File(username + ".txt");
    if (file.exists()) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println("Username: " + customer.getUsername());
            writer.println("Password: " + customer.getPassword());
            writer.println("Balance: " + customer.getBalance());
            writer.println("Level: " + customer.getLevel());
            // Add additional customer information as needed
            System.out.println("Customer information updated and saved to file.");
        } catch (IOException e) {
            System.err.println("Error updating customer information: " + e.getMessage());
        }
    } else {
        System.err.println("File for customer " + username + " does not exist.");
    }
}
}