package flightbookingsystem;

/**
 *
 * @author yoloi
 */
public class NonMember extends Passenger {

    public NonMember(String name, int age) {
        super(name, age);
    }
    @Override
    public double applyDiscount(double p){
        if(age>65){
            p=0.9*p;
        } 
        return p;   
}
}
