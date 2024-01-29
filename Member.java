package flightbookingsystem;

/**
 *
 * @author yoloi
 */
public class Member extends Passenger {
    private int yearsOfMembership;

    public Member(String name, int age) {
        super(name, age);
    }
    @Override
    public double applyDiscount(double p){

        if(yearsOfMembership>5){
            p=0.5*p;
        } 
        else if(yearsOfMembership>1){
            p=0.9*p;
        }
        return p;
    }
    
    public int getYearsOfMembership() {
        return yearsOfMembership;
    }

    public void setYearsOfMembership(int yearsOfMembership) {
        this.yearsOfMembership = yearsOfMembership;
    }
}
