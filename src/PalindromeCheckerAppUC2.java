public class PalindromeCheckerAppUC2 { private static final String APP_NAME = "Palindrome Checker App";
    private static final String VERSION = "1.0.0";

    public static void main(String[] args) {
        displayWelcomeMessage();

        // UC2: Hardcoded String Logic
        String original = "madam"; // String Literal
        String reversed = "";

        // Standard logic: Reverse the string using a for loop
        for (int i = original.length() - 1; i >= 0; i--) {
            reversed += original.charAt(i);
        }

        // Conditional Statement to check for palindrome
        if (original.equalsIgnoreCase(reversed)) {
            System.out.println("Result: '" + original + "' is a palindrome!");
        } else {
            System.out.println("Result: '" + original + "' is NOT a palindrome.");
        }
    }

    private static void displayWelcomeMessage() {
        System.out.println("========================================");
        System.out.println("   Welcome to " + APP_NAME);
        System.out.println("   Version: " + VERSION);
        System.out.println("========================================");
        System.out.println();
    }}

