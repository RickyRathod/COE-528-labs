package project;

/**
 *
 * @author yoloi
 */

//since there are multiple types of customers accessing the bank
//i will make this an abstract class where the Gold, Silver, and Platiinum 
//members will share behaviours of this class

//"A customer can login, logout, deposit money, withdraw money, get balance, and do online purchase(s)"
public abstract class Customer {
    
    public abstract boolean Login(String username, String password);
    public abstract String getUsername();
    public abstract String getPassword();
    
    public abstract boolean Logout();
    
    public abstract void deposit(int amount);
    
    //boolean so that it can check for a condition to allow for a withdraw, 
    //if it fails then it should return a statement
    public abstract boolean withdraw(int amount);
    
    public abstract int getBalance();
    
    //same reason as before, must pass condition
    public abstract boolean purchase(int amount);
    
    //method to retrive the level
    public abstract String getLevel();
}
