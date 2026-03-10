import java.util.Scanner;
import java.util.Stack;

public class PalindromeCheckerAppUpdated {

    private static final String APP_NAME = "Palindrome Checker App";
    private static final String VERSION = "1.0.0";

    public static void main(String[] args) {

        // UC1: Display welcome message
        displayWelcomeMessage();

        // UC2: Take user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string to check: ");
        String input = scanner.nextLine();

        // UC3: Convert string to char array
        char[] charArray = input.toCharArray();

        // ===============================
        // UC4: Two Pointer Palindrome Check
        // ===============================
        boolean isPalindromeTwoPointer = true;

        int left = 0;
        int right = charArray.length - 1;

        while (left < right) {
            if (Character.toLowerCase(charArray[left]) != Character.toLowerCase(charArray[right])) {
                isPalindromeTwoPointer = false;
                break;
            }
            left++;
            right--;
        }

        System.out.println("\n--- Two Pointer Technique Result ---");
        if (isPalindromeTwoPointer) {
            System.out.println("'" + input + "' is a palindrome.");
        } else {
            System.out.println("'" + input + "' is NOT a palindrome.");
        }

        // ===============================
        // UC5: Stack-Based Palindrome Check
        // ===============================
        Stack<Character> stack = new Stack<>();

        // Push characters into stack
        for (char c : charArray) {
            stack.push(Character.toLowerCase(c));
        }

        boolean isPalindromeStack = true;

        // Pop characters and compare
        for (char c : charArray) {
            char popped = stack.pop();
            if (Character.toLowerCase(c) != popped) {
                isPalindromeStack = false;
                break;
            }
        }

        System.out.println("\n--- Stack Based Technique Result ---");
        if (isPalindromeStack) {
            System.out.println("'" + input + "' is a palindrome.");
        } else {
            System.out.println("'" + input + "' is NOT a palindrome.");
        }

        scanner.close();
    }

    // UC1: Welcome message
    private static void displayWelcomeMessage() {
        System.out.println("========================================");
        System.out.println("   Welcome to " + APP_NAME);
        System.out.println("   Version: " + VERSION);
        System.out.println("========================================");
        System.out.println();
    }
}

