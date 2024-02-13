package coe528.lab3;

/**
 *
 * @author yoloi
 */

// Counter Interface
public class Odometer implements Counter {
    
    private DigitCounter lastCounter;
    private LinkedDigitCounter[] counters;
    private int n;
    
    
    //if < 1 arg, illegal args thrown
    //else decrements arg by 1 and initializes lastCounter as a digitCounter value 0
    //responsible for initializing linkedDigitCounters
    public Odometer(int n) {
        if (n < 1) {
            throw new IllegalArgumentException(); 
        } else {
            this.n = n;
            
            //initializes the counter array with size n - 1 [each element is a digit counter]
            counters = new LinkedDigitCounter[n - 1];
            //initializes lastCounter starting at 0
            lastCounter = new DigitCounter(0);
            
            //initializes the first element as a linked digit counter with value of 0 and its left neighbour as "lastCounter"
            if (n > 1) {
                counters[0] = new LinkedDigitCounter(0, lastCounter);
            }
            
            //starting at index 1 to n - 1, counter is being initialized as linkedDigitCounters starting at 0 
            //and its left neighbour as the previous element in the counters array
            for (int i = 1; i < n - 1; i++) {
                counters[i] = new LinkedDigitCounter(0, counters[i - 1]);
            }
        }
    }

    @Override
    public String count() {
        StringBuilder sb = new StringBuilder();
        //appends the lastCounter to the sb which is the right most digit
        sb.append(lastCounter.count());
        
        //itterates over the counters starting at 0 to n - 2 
        //(skips the last element - as its appended before the loop)
        //appends each digit to sb where count is obtained by count()
        for (int i = 0; i < n - 1; i++) {
            sb.append(counters[i].count());
        }
        return sb.toString();
    }
    
    
    //if the second last digit equals 9 then it increments the leftneighbour (now the 3rd last)
    @Override
    public void increment() {
        if (counters[n - 2].count().equals("9")) {
            IncrementLeftNeighbour(counters[n - 2]); 
        }
        counters[n - 2].increment();
    }
    
    //if the second last digit equals 0 then it decrements the left neighbour
    @Override
    public void decrement() {
        if (counters[n - 2].count().equals("0")) {
            DecrementLeftNeighbour(counters[n - 2]);
        }
        counters[n - 2].decrement();
    }
    
    //otherwise resets
    @Override
    public void reset() {
        lastCounter.reset();
        for (int i = 0; i < n - 1; i++) {
            counters[i].reset();
        }
    }
    //cascading implies here that the increment or devrement opp is being applied to 
    //the current digit along with the neighbouring digit counter in the linked list
    
    
    
    //checks if current is = 9, if so it cascades the increment to the left in the linked list
    private void IncrementLeftNeighbour(LinkedDigitCounter current) {
        if (current.getLeftNeighbour().count().equals("9")) {
            try {
                IncrementLeftNeighbour((LinkedDigitCounter) current.getLeftNeighbour());
            } catch (java.lang.ClassCastException e) {
            }
        }
        try {
            current.getLeftNeighbour().increment();
        } catch (java.lang.ClassCastException e) {
        }
    }
    
    //checks if current is = 0, if so then it cascades the decrement to the left in the linked list
    private void DecrementLeftNeighbour(LinkedDigitCounter current) {
        if (current.getLeftNeighbour().count().equals("0")) {
            try {
                DecrementLeftNeighbour((LinkedDigitCounter) current.getLeftNeighbour());
            } catch (java.lang.ClassCastException e) {
            }
        }
        try {
            current.getLeftNeighbour().decrement();
        } catch (java.lang.ClassCastException e) {
        }
    }
}
