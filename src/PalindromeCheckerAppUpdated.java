import java.util.Scanner;

public class PalindromeCheckerAppUpdated {

    private static final String APP_NAME = "Palindrome Checker App";
    private static final String VERSION = "1.0.0";

    public static void main(String[] args) {
        displayWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string to check: ");
        String input = scanner.nextLine();

        // UC4: Convert string to char[]
        char[] charArray = input.toCharArray();
        boolean isPalindrome = true;

        // Two-Pointer Technique
        int left = 0;
        int right = charArray.length - 1;

        while (left < right) {
            // Compare start & end characters
            if (Character.toLowerCase(charArray[left]) != Character.toLowerCase(charArray[right])) {
                isPalindrome = false;
                break;
            }
            left++;
            right--;
        }

        System.out.println("Input analyzed using Two-Pointer Technique.");
        if (isPalindrome) {
            System.out.println("Result: '" + input + "' is a palindrome.");
        } else {
            System.out.println("Result: '" + input + "' is NOT a palindrome.");
        }

        scanner.close();
    }

    private static void displayWelcomeMessage() {
        System.out.println("========================================");
        System.out.println("   Welcome to " + APP_NAME);
        System.out.println("   Version: " + VERSION);
        System.out.println("========================================");
        System.out.println();
    }
}

