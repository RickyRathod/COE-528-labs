package project;

/**
 *
 * @author yoloi
 */
//"When a customer has 10000 dollars or more but less than 20000 dollars in her account, she is at gold level."

public class GoldCustomer extends Customer {
    
    private String username;
    private String password;
    private int balance;
    
    public GoldCustomer(String u, String p, int b){
        
        username = u;
        password = p;
        this.balance = b;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public int getBal(){
        return balance;
    }
    
    //returns true if the username's & passwords's are the same

    public boolean Login(String username, String password){
        return(username.equals(username)&&password.equals(password));   
    }
    

    public boolean logout(){
        return true;
    }
    
    public void deposit(int money){
        balance += money;
    }
    
    
    @Override
    public String toString(){
        return "Username: " + username + "Password: " + password + "Account Balance: " + balance;
    }
}

