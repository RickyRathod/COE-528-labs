package flightbookingsystem;

/**
 *
 * @author yoloi
 */
public class Ticket {
    private static int number = 0;
    private Passenger passenger; 
    private Flight flight;
    private double price;
public Ticket(Passenger p, Flight flight, double price){
    passenger=p;
    this.flight=flight;
    this.price=price;
    number++;
    
}
public String toString(){
return passenger.getName() + ", "+ "Flight " + flight.getFlightNumber() + ", " 
        + flight.getOrigin() + " to " + flight.getDestination() + ", " 
        + flight.getDepartureTime() + ", original price: " 
        + flight.getOriginalPrice() + ", ticket price: " + price;
    }
public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public static int getNumber() {
        return number;
    }
}
