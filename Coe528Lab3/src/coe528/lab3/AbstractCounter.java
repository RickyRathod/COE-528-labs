package coe528.lab3;

/**
 *
 * @author yoloi
 */
public abstract class AbstractCounter implements Counter {
    
    //protected so all classes and subclasses can access "couter" due to abstract class
    protected int counter;

    public AbstractCounter() {
        counter = 0;
    }

    @Override
    public String count() {
        return Integer.toString(counter); 
    }
    
    //if the right most digit is  < 9 then it gets incremented. o/w set back to 0
    @Override
    public void increment() {
        if (counter < 9) {
            counter++;
        } else {
            counter = 0;
        }
    }
    
    //if couter is > 0 then decrement 
    @Override
    public void decrement() {
        if (counter > 0) {
            counter--;
        } else {
            counter = 9;
        }
    }
    
    
    //resets counter back to 0 if non of the above apply (default case) 
    @Override
    public void reset() {
        counter = 0;
    }
}
