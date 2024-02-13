package coe528.lab3;

import java.util.Scanner;

/**
 *
 * @author yoloi
 */
public class OdometerDriver {
public static void main(String[] args){
try{
//read number of digits for odometer from console
System.out.print("Enter number of digits for odometer: ");
Scanner s = new Scanner( System.in);
int numOfDigits = s.nextInt();
Odometer odometer = new Odometer(numOfDigits);
//increment 130 times and print the count.
for ( int i = 0; i < 130; ++i ) {
odometer.increment();
}
System.out.println(odometer.count());
//decrement 31 times and print the count.
for ( int i = 0; i < 31; ++i ){
odometer.decrement();
}
System.out.println(odometer.count());
//increment 1001 times and print the count.
for ( int i = 0; i < 1001; ++i ){
odometer.increment();
}
System.out.println(odometer.count());
// decrement 1101 times and print the count.
for ( int i = 0; i < 1101; ++i ){
odometer.decrement();
}
System.out.println(odometer.count());
//reset the odometer and print the count.
odometer.reset();
System.out.println(odometer.count());
//decrement once and print the count.
odometer.decrement();
System.out.println(odometer.count());
//increment once and print the count.
odometer.increment();
System.out.println(odometer.count());
}
catch(IllegalArgumentException ex){
System.out.println("Number of digits in odometer must be at least 1");
}
}
}
