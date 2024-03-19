
package project;

/**
 *
 * @author yoloi
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

    @Override
    public boolean Login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean Logout() {
        return true;
    }

    @Override
    public void deposit(int amount) {
        balance += amount;
    }

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

    @Override
    public int getBalance() {
        return balance;
    }
    
    //When a silver customer does an online purchase using her account, 
    //she needs to pay a fee of 20 dollars in addition to the purchase amount from her account.
    
    //for an online purchace to procceed, the amount must be more than 0 
    //and the ammount must be less than the balancce 
    //otherwise if those two conditions fail the purchace will not go through
    
    public boolean purchase(int amount) {
        // Implementing purchase logic for the SilverCustomer's
        if(amount >= 0 && amount <= balance){
        balance = balance - amount - 20;
        return true;
        }
        return false;
    }
    
    @Override
    public String getLevel() {
        return "Silver Level"; // Return the level for a silver customer
    }
}
