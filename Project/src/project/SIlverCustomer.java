
package project;

/**
 *
 * @author yoloi
 */

/*
OVERVIEW: Constructs a SilverCustomer object with the specified username, password, and initial balance.

REQUIRES: The username, password, and initial balance must not be null.
MODIFIES: Sets the username, password, and balance fields of the SilverCustomer object.
EFFECTS: Initializes the SilverCustomer object with the provided username, password, and balance.
*/

//"When a customer has less than 10000 dollars in her account, she is at silver level."
public class SIlverCustomer extends Customer {
    private String username;
    private String password;
    private int balance; 

    public SIlverCustomer(String username, String password, int initialBalance) {
        this.username = username;
        this.password = password;
        this.balance = initialBalance;
    }
    
    /*
    REQUIRES: The username and password to be non-null.
    EFFECTS: Returns true if the provided username and password match the customer's credentials, false otherwise.
    */

    @Override
    public boolean Login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
    
    /*
    REQUIRES: Username and password to be null 
    EFFECTS: Returns true if the username and password are not null or empty, false otherwise.
    */
    
    public boolean repOk() {
        if (username.equals("") || password.equals("")) {
            return false;
        }
        return true;
    }
    
    //EFFECTS: Returns the username of the customer.
    
    @Override
    public String getUsername() {
        return username;
    }
    
    //EFFECTS: Returns the password of the customer.
    
    @Override
    public String getPassword() {
        return password;
    }
    
    //EFFECTS: Returns true indicating successful logout.
    
    @Override
    public boolean Logout() {
        return true;
    }
    
    /*
    REQUIRES: The amount to deposit must be non-negative.
    MODIFIES: Increases the balance of the customer by the specified amount.
    */
    
    @Override
    public void deposit(int amount) {
        balance += amount;
    }
    
    /*
    REQUIRES: The amount to withdraw must be non-negative and less than or equal to the current balance.
    MODIFIES: Decreases the balance of the customer by the specified amount if the withdrawal is successful.
    EFFECTS: Returns true if the withdrawal is successful, false otherwise.
     */
    
    @Override
    public boolean withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            return true; // Withdrawal successful
        } else {
            System.out.println("Insufficient balance");
            return false; // Withdrawal failed due to insufficient balance
        }
    }
    
    //EFFECTS: Returns the balance of the customer's account.
    
    @Override
    public int getBalance() {
        return balance;
    }
    
    //When a silver customer does an online purchase using her account, 
    //she needs to pay a fee of 20 dollars in addition to the purchase amount from her account.
    
    //for an online purchace to procceed, the amount must be more than 0 
    //and the ammount must be less than the balancce 
    //otherwise if those two conditions fail the purchace will not go through
    
    /*
    REQUIRES: The purchase amount must be non-negative and less than or equal to the balance.
    MODIFIES: Decreases the balance of the customer by the purchase amount plus a $10 fee.
    EFFECTS: Returns true if the purchase is successful, false otherwise.
     */
    public boolean purchase(int amount) {
        // Implementing purchase logic for the SilverCustomer's
        if(amount >= 0 && amount <= balance){
        balance = balance - amount - 20;
        return true;
        }
        return false;
    }
    
    //EFFECTS: Returns the level of the customer.
    
    @Override
    public String getLevel() {
        return "Silver Level"; // Return the level for a silver customer
    }
}
