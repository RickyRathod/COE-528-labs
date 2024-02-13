package coe528.lab3;

/**
 *
 * @author yoloi
 */
public class LinkedDigitCounter extends AbstractCounter {
    private Counter leftNeighbour;
    
    
    //it must know its current value and it must also know its left neighbour
    
    public LinkedDigitCounter(int counter, Counter leftCounter) {
        this.counter = counter;
        this.leftNeighbour = leftCounter;
    }

    public Counter getLeftNeighbour() {
        return leftNeighbour;
    }
}