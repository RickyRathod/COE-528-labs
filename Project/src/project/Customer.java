package project;

/**
 *
 * @author yoloi
 */

//since there are multiple types of customers accessing the bank
//this will be an abstract class where the Gold, Silver, and Platiinum 
//members will share behaviours of this class

//"A customer can login, logout, deposit money, withdraw money, get balance, and do online purchase(s)"
public abstract class Customer {
    public abstract boolean login(String username, String password);
    public abstract String getPassword();
    public abstract String getUsername();
    
    public abstract boolean Logout();
    public abstract void deposit(int money);
    public abstract void getBalance();
    public abstract void Purchace();
}
