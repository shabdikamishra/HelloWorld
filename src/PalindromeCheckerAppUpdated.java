import java.util.Scanner;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class PalindromeCheckerAppUpdated {

    private static final String APP_NAME = "Palindrome Checker App";
    private static final String VERSION = "1.0.0";

    public static void main(String[] args) {

        // UC1: Display welcome message
        displayWelcomeMessage();

        // UC2: Take input from user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string to check: ");
        String input = scanner.nextLine();

        // UC3: Convert string to char array
        char[] charArray = input.toCharArray();

        // =================================
        // UC4: Two Pointer Palindrome Check
        // =================================
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

        // =================================
        // UC5: Stack-Based Palindrome Check
        // =================================
        Stack<Character> stack = new Stack<>();

        for (char c : charArray) {
            stack.push(Character.toLowerCase(c));
        }

        boolean isPalindromeStack = true;

        for (char c : charArray) {
            if (Character.toLowerCase(c) != stack.pop()) {
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

        // =================================
        // UC6: Queue + Stack Palindrome Check
        // =================================
        Queue<Character> queue = new LinkedList<>();
        Stack<Character> stack2 = new Stack<>();

        // Enqueue and Push characters
        for (char c : charArray) {
            char ch = Character.toLowerCase(c);
            queue.add(ch);     // enqueue
            stack2.push(ch);   // push
        }

        boolean isPalindromeQueueStack = true;

        // Compare dequeue vs pop
        while (!queue.isEmpty()) {
            if (queue.remove() != stack2.pop()) {
                isPalindromeQueueStack = false;
                break;
            }
        }

        System.out.println("\n--- Queue + Stack Technique Result ---");
        if (isPalindromeQueueStack) {
            System.out.println("'" + input + "' is a palindrome.");
        } else {
            System.out.println("'" + input + "' is NOT a palindrome.");
        }

        scanner.close();
    }

    // UC1: Welcome Message
    private static void displayWelcomeMessage() {
        System.out.println("========================================");
        System.out.println("   Welcome to " + APP_NAME);
        System.out.println("   Version: " + VERSION);
        System.out.println("========================================");
        System.out.println();
    }
}
