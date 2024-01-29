package flightbookingsystem;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author yoloi
 */

//stores rlight and ticket objects in an arraylist
public class Manager {
    ArrayList<Flight> flights = new ArrayList<Flight>();
    ArrayList<Ticket> tickets = new ArrayList<Ticket>();
    private static final Scanner user = new Scanner(System.in);
    
public void createFlights(){
    
    //prompts for user input, Loop runs while value is set to 1 and once Stop is invoked the value
    //is set to - 1 therefore ending the loop.
    
    String origin, destination, departureTime, input;
    int flightNumber, capacity;
    double originalPrice;
        int j=1;
        while(j==1){
            
        // Inputs required from the lab manual 
        
         System.out.println("Input: Flight Origin ");
            origin = user.nextLine().trim();
            
         System.out.println("Input: The Flight's Destination ");
            destination = user.nextLine().trim();
            
         System.out.println("Input: Flight's Departure Date and Time ");
            departureTime = user.nextLine().trim();
            
         System.out.println("Input: Flight Price ");
            originalPrice = Double.parseDouble(user.nextLine().trim());
            
         System.out.println("Input: Flight Number ID ");
            flightNumber = Integer.parseInt(user.nextLine().trim());
            
         System.out.println("Input: Flight Capacity ");
         capacity = Integer.parseInt(user.nextLine().trim());
         
         Flight F = new Flight(capacity, flightNumber, originalPrice, origin, destination, departureTime);
         flights.add(F);
         
         System.out.println("Type 'Stop' to stop adding flights. Else press enter");
         input = user.nextLine().trim();
         
         if(input.toLowerCase().equals("stop")){
             j--;
         }

    }
}
     
// public void displayAvailableFlights(String origin, String destination): this method should
//display all the available flights from origin to destination. It should display only those flights
//that are not yet fully booked.   

// takes param origin and destination, the for loop iterates through the elements in the Flights array list 
// the if Statement is used to check if the origin and destination match the inputted origin and destination
//if true then the flight infornation will be printed 
//if false then the print statement will not be exicuted and will show the above UI again
public void displayAvailableFlights(String origin, String destination){
    for(int i=0; i<flights.size(); i++){
        if(origin.equalsIgnoreCase(flights.get(i).getOrigin()) && destination.equalsIgnoreCase(flights.get(i).getDestination())){   
            System.out.println(flights.get(i).toString());
                }
            }
        }

// for loop checks the elements in the Flights arraylist and assigns each element to the temp var f
//if statement will check if the flight number is the same as the inputted flight number when creating a flight
//if true then it will return the flight number in the terminal ow/ null

//input gained from the Search case below
public Flight getFlight(int flightNumber){
    for(Flight f : flights){
        if(f.getFlightNumber()==flightNumber){
            System.out.println(f);
                return f;
                }
            }             
        return null;
    }

//takes the params flight number and passengers p
//if the flight number doesnt exist, or is fully booked and returns false from the bookSeat method 
//then it returns an IllegalArgument

public void bookSeat(int flightNumber, Passenger p){
    if(getFlight(flightNumber).equals(null) || !getFlight(flightNumber).bookASeat()){
        throw new IllegalArgumentException("Flight doesnt exist");
        }
        //if the top portion returns false ( There are seats and a flight option)
        //this portion also calculates and applies the discounts
        //and adds the new ticket to the ticket list and prints the updated price
        double x = p.applyDiscount(getFlight(flightNumber).getOriginalPrice());
        Ticket T = new Ticket(p, getFlight(flightNumber), x);   
        tickets.add(T);
        System.out.println(T);
        }
        
public static void main(String[] args){
    
    //creating the members and NonMembers with name, age, and years of membership params
    //done from the members file

        Manager M = new Manager();
         
        Member ricky = new Member("Ricky", 20);
        ricky.setYearsOfMembership(1);
        
        Member rathod = new Member("Rathod", 21);
        rathod.setYearsOfMembership(10);

        NonMember Harry = new NonMember("Harry", 27);
        
        NonMember Potter = new NonMember("Potter", 80);
        
        //Used input# so its easier to read and locate UI's
        
        String input2, input3, input4, passengerName;
        int input5, input6;

        int i=1;
        while(i==1){
            
        System.out.println("Type:");
        System.out.println("    'Create' to Add Flights");
        System.out.println("    'Flights' to See Avalible Flights");
        System.out.println("    'Search' to Lookup flights by Number");
        System.out.println("    'Book' to Book a Seat for a member");
        System.out.println("    'End' to close the program");
            
            input2 = user.nextLine().trim();
            
            if(input2.toLowerCase().equals("end")){
                System.out.println("Program shutting down");
                i--;
            }
            
            switch(input2.toLowerCase()){
                
                case "create":
                    M.createFlights();
                    break;
                
                case "flights":
                    System.out.println("Enter Flight Origin");
                    input3 = user.nextLine().trim();
                    System.out.println("Enter Flight Destination");                    
                    input4 = user.nextLine().trim();
                    M.displayAvailableFlights(input3, input4);
                    break;
                
                    //gets stored and called again in the GetFlights method above, which uses the input5 to 
                    //assign a new value to getFlights
                case "search":
                    System.out.println("Enter Flight Number");
                    input5 = Integer.parseInt(user.nextLine().trim());
                    M.getFlight(input5);
                    break;
                    
                    //input 5 is stored as the flgiht number from the UI (f.getFlightNumber) 
                    //while flightNumber is a param in the getFlight method which takes on the value of 
                    //input 6 
                    
                    //so in short its basically being used to display and store the new flight number at the end
                case "book":
                    System.out.println("Enter Flight Number");
                    input6 = Integer.parseInt(user.nextLine().trim());
                    
                    System.out.println("Enter Passenger Name");
                    passengerName = user.nextLine().trim();
                    
                        switch (passengerName.toLowerCase()) {
                        case "ricky":
                            M.bookSeat(input6, ricky);
                            break;
                        case "rathod":
                            M.bookSeat(input6, rathod);
                            break;
                        case "harry":
                            M.bookSeat(input6, Harry);
                            break;
                        case "potter":
                            M.bookSeat(input6, Potter);
                            break;
                        default:
                            System.out.println("Passenger does not exist");
                    }

                    break;
            }
        }    
    }
}
