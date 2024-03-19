package project;
/**
 *
 * @author yoloi
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Project extends Application {
    private static Manager manager;

    @Override
    public void start(Stage primaryStage) {
        // Instantiate Manager with username, password, and role
        manager = new Manager("Admin", "Admin", "Manager");

        // After successful manager login, transition to manager interface
        ManagerInterface.createManagerInterface(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static class ManagerInterface {
        public static void createManagerInterface(Stage primaryStage) {
            Label nameLabel = new Label("Customer Name:");
            TextField nameField = new TextField();
            Label passwordLabel = new Label("Password:");
            TextField passwordField = new TextField();
            Label balanceLabel = new Label("Initial Balance:");
            TextField balanceField = new TextField(); // Text field for initial balance
            Button createButton = new Button("Create Account");
            Button deleteButton = new Button("Delete Account");

            createButton.setOnAction(e -> {
                String username = nameField.getText();
                String password = passwordField.getText();
                int initialBalance = Integer.parseInt(balanceField.getText()); // Parse initial balance from text field

                // Call createCustomerAccount method on the manager instance
                Project.createCustomerAccount(username, password, initialBalance);

                // Display a confirmation message or perform other actions
                // Clear input fields
                nameField.clear();
                passwordField.clear();
                balanceField.clear();
            });

            deleteButton.setOnAction(e -> {
                String username = nameField.getText(); // Assuming you have a text field for entering the username to delete
                Project.deleteCustomerFile(username);
                // Display confirmation message or handle any errors
            });

            VBox root = new VBox(10);
            root.setPadding(new Insets(10));
            root.getChildren().addAll(nameLabel, nameField, passwordLabel, passwordField, balanceLabel, balanceField, createButton, deleteButton);

            Scene scene = new Scene(root, 300, 250);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Manager Account Creation");
            primaryStage.show();
        }
    } 

    public static void createCustomerAccount(String username, String password, int initialBalance) {
        // Determine the customer level based on the initial balance
        Customer customer;
        if (initialBalance < 10000) {
            customer = new SIlverCustomer(username, password, initialBalance);
        } else if (initialBalance < 20000) {
            customer = new GoldCustomer(username, password, initialBalance);
        } else {
            customer = new PlatinumCustomer(username, password, initialBalance);
        }

        // Save customer information to a file
        saveCustomerToFile(customer);
    }

    private static void saveCustomerToFile(Customer customer) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(customer.getUsername() + ".txt"))) {
            writer.println("Username: " + customer.getUsername());
            writer.println("Password: " + customer.getPassword());
            writer.println("Balance: " + customer.getBalance());
            writer.println("Level: " + customer.getLevel()); // Print the customer's level
            // Add additional customer information as needed
            System.out.println("Customer information saved to file.");
        } catch (IOException e) {
            System.err.println("Error saving customer information to file: " + e.getMessage());
        }
    }

    public static void deleteCustomerFile(String username) {
        File file = new File(username + ".txt");
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File for customer " + username + " deleted successfully.");
            } else {
                System.err.println("Failed to delete file for customer " + username);
            }
        } else {
            System.err.println("File for customer " + username + " does not exist.");
        }
    }
}
