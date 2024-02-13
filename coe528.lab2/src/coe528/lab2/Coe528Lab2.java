package coe528.lab2;

import java.util.Arrays;
import java.util.Scanner;
/**
 *
 * @author yoloi
 */

//Ex3
public class Coe528Lab2 {
    public static class AnagramChecker {
        /**
         * @requires both strings must not be null otherwise it will cause a null error
         */
        public static boolean areAnagrams(String str1, String str2) { 
            // Check if both strings are not null
            if (str1 == null || str2 == null) {
                return false;
            }

            // Remove spaces and convert to lowercase 
            String processedStr1 = str1.replaceAll("\\s", "").toLowerCase();
            String processedStr2 = str2.replaceAll("\\s", "").toLowerCase();

            // Checks if the lengths are the same after removing spaces
            if (processedStr1.length() != processedStr2.length()) {
                return false;
            }

            // Convert strings to char arrays and sort them
            char[] charArray1 = processedStr1.toCharArray();
            char[] charArray2 = processedStr2.toCharArray();
            Arrays.sort(charArray1);
            Arrays.sort(charArray2);

            // Compare the sorted char arrays
            return Arrays.equals(charArray1, charArray2);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the first string: ");
        String str1 = scanner.nextLine();

        System.out.print("Enter the second string: ");
        String str2 = scanner.nextLine();

        boolean result = AnagramChecker.areAnagrams(str1, str2);

        System.out.println("Are \"" + str1 + "\" and \"" + str2 + "\" anagrams? " + result);

        scanner.close();
    }
}
