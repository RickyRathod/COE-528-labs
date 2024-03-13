
package coe528lab4;

/**
 *
 * @author yoloi
 */
import java.util.ArrayList;

public class StackOfDistinctStrings {
// Overview: StacksOfDistinctStrings are mutable, bounded
// collection of distinct strings that operate in
// LIFO (Last-In-First-Out) order.
//
// The abstraction function is:
// a) Write the abstraction function here
//     
//
//
//
// The rep invariant is:
// b) Write the rep invariant here
//
//
//
//
//the rep

    private ArrayList<String> items;
// constructor

    public StackOfDistinctStrings() {
// EFFECTS: Creates a new StackOfDistinctStrings object
        items = new ArrayList<String>();
    }

    public void push(String element) throws Exception {
// MODIFIES: this
// EFFECTS: Appends the element at the top of the stack
// if the element is not in the stack, otherwise
// does nothing.
        if (element == null) {
            throw new Exception();
        }
        if (false == items.contains(element)) {
            items.add(element);
        }
    }

    public String pop() throws Exception {
// MODIFIES: this
// EFFECTS: Removes an element from the top of the stack
        if (items.size() == 0) {
            throw new Exception();
        }
        return items.remove(items.size() - 1);
    }

public boolean repOK() {
// EFFECTS: Returns true if the rep invariant holds for this
// object; otherwise returns false
// c) Write the code for the repOK() here

    return items != null;
    }

    @Override
    public String toString() {
// EFFECTS: Returns a string that contains the strings in the
// stack and the top element. Implements the
// abstraction function.
// d) Write the code for the toString() here

        String str = "";
        int length = items.size();
        int indexNumber;

        // Iterate through each element and get all the elements
        for(indexNumber = 0; indexNumber < length; indexNumber++)
        {
            str += " " + items.get(indexNumber);
        }
        return str;
    }
    
    public static void main(String[] args) {
        // Initialization of a new StackOfDistinctStrings
        StackOfDistinctStrings stack = new StackOfDistinctStrings();
        
        // try catch exception handeling to catch the exceptions thrown
        //by push and pop
        try {
            // Pushing strings onto the stack
            stack.push("ab");
            stack.push("cd");
            stack.push("ae");
            stack.push("bd");
            
            // removing strings from the stack
            String poppedString1 = stack.pop();
            String poppedString2 = stack.pop();
            
            // Output removed strings
            System.out.println("Popped string 1: " + poppedString1);
            System.out.println("Popped string 2: " + poppedString2);
        } catch (Exception e) {
            // print statement for push/pop exceptions being caught
            System.out.println("Caught some exception...");
        }
    }

}