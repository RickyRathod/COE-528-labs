import java.util.LinkedList;

//Queue<Q> is the name of our interface where <Q> is our type 
//this means that Q can be of any type as we declared it to be generic
//is it were Queue<String> like in ex1, then it would only hold elements of type string
//which in this case we are not. 
public class Queue<Q> {
    // Internal representation of the queue
    private LinkedList<Q> elements;

    // EFFECTS: Initializes the queue
    public Queue() {
        elements = new LinkedList<>();
    }

    // MODIFIES: this
    // EFFECTS: Adds an element to the rear end of the queue
    
    //Takes parameter name element of type Q
    public void enqueue(Q element) {
        elements.addLast(element);
    }

    // MODIFIES: this
    // EFFECTS: Removes and returns the element from the front end of the queue
    //          Throws IllegalStateException if the queue is empty
    
    //takes no specified parameters
    public Q dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return elements.removeFirst();
    }

    // EFFECTS: Returns true if the queue is empty, false otherwise
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    // EFFECTS: Returns true if the rep invariant holds true for the current state of the queue
    public boolean repOK() {
        // No specific rep invariant for a queue, it only needs to be non-null (filled with some element)
        return elements != null;
    } 

    // EFFECTS: Returns a string representation of the queue's elements
    @Override
    public String toString() {
        return elements.toString();
    }

    // Main method for testing the queue implementation
    public static void main(String[] args) {
        // Test the queue implementation
        Queue<Integer> queue = new Queue<>();
        // Enqueue elements
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        // Dequeue elements
        int dequeuedElement1 = queue.dequeue(); // 10
        int dequeuedElement2 = queue.dequeue(); // 20
        // Check if the queue is empty
        boolean isEmpty = queue.isEmpty(); // false
        // Expected output
        System.out.println("Dequeued element 1: " + dequeuedElement1);
        System.out.println("Dequeued element 2: " + dequeuedElement2);
        System.out.println("Is the queue empty? " + isEmpty);
    }
}