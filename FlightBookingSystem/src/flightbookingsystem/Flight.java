package flightbookingsystem;

/**
 *
 * @author yoloi
 */
public class Flight {
    private int capacity;
    private int numberOfSeatsLeft;
    private int flightNumber;
    private String origin, destination, departureTime;
    private double originalPrice;
    
    public Flight(int capacity, int flightNumber, double originalPrice, String origin, String destination, String departureTime) {
        this.capacity = capacity;
        this.flightNumber = flightNumber;
        this.originalPrice = originalPrice;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.numberOfSeatsLeft = capacity; // Initialize numberOfSeatsLeft with the provided capacity

        if (origin.equalsIgnoreCase(destination)) {
            throw new IllegalArgumentException("Origin cannot be the same as the Destination, please change one or the other.");
        }
    }
    
     public boolean bookASeat() {
        if (numberOfSeatsLeft > 0) {
            numberOfSeatsLeft--;
            return true;
        } else {
            return false;
        }
    }
     
    public String toString(){
          return "Flight " + flightNumber + ", "+ origin + " to " + destination + ", " + departureTime + ", original price: " + originalPrice + ", Capacity : " + capacity; 
    }
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }
}
