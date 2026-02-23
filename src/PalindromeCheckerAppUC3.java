public class PalindromeCheckerAppUC3 {

    public static void main(String[] args) {
        // UC1: Welcome Message
        System.out.println("=== Palindrome Checker App v1.0 ===");

        // UC3: String Reverse Logic
        // Data Structure: String (Immutability check)
        String original = "radar";
        String reversed = "";

        // Loop: Iterating from the last index down to 0
        for (int i = original.length() - 1; i >= 0; i--) {
            // String Concatenation: Building the new string
            reversed += original.charAt(i);
        }

        // Displaying both strings
        System.out.println("Original String: " + original);
        System.out.println("Reversed String: " + reversed);

        // Comparison using equals() method
        if (original.equals(reversed)) {
            System.out.println("Status: The string is a Palindrome.");
        } else {
            System.out.println("Status: The string is NOT a Palindrome.");
        }
    }
}

