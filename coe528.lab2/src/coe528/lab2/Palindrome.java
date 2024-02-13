package coe528.lab2;

/**
 *
 * @author yoloi
 * Ex2
 */
public class Palindrome {

    // Requires: a should be a non-null String
    // Modifies: None
    // Effects: Returns true if the specified string a is a palindrome (reads the same backward and forward),
    //          false otherwise. The method does not modify the input string.
    public static boolean isPalindrome(String a) {
        if (a == null) {
            // Palindrome checking is not meaningful for a null string
            return false;
        }

        // Remove spaces and convert to lowercase for case-insensitivity
        String cleanedStr = a.replaceAll("\\s", "").toLowerCase();

        // Initialize an empty string to store the reverse of the original cleanedStr
        String reversedStr = "";

        // Iterate through the characters in reverse order and append to reversedStr
        for (int i = cleanedStr.length() - 1; i >= 0; i--) {
            reversedStr += cleanedStr.charAt(i);
        }

        // Check if both the cleanedStr and its reverse are equal
        return cleanedStr.equals(reversedStr);
    }

    public static void main(String[] args) {
        if(args.length == 1) {
            if (args[0].equals("1"))
                System.out.println(isPalindrome(null));
            else if (args[0].equals("2"))
                System.out.println(isPalindrome(""));
            else if (args[0].equals("3"))
                System.out.println(isPalindrome("deed"));
            else if (args[0].equals("4"))
                System.out.println(isPalindrome("abcd"));
        }
    }
}
