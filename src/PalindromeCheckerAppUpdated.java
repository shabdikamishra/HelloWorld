import java.util.*;

public class PalindromeCheckerAppUpdated {

    private static final String APP_NAME = "Palindrome Checker App";
    private static final String VERSION = "1.0.0";

    // =========================
    // Node class for Linked List (UC8)
    // =========================
    static class Node {
        char data;
        Node next;

        Node(char data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {

        // UC1: Display welcome message
        displayWelcomeMessage();

        // UC2: User input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string to check: ");
        String input = scanner.nextLine();

        // UC3: Convert string to char array
        char[] charArray = input.toCharArray();

        // =================================
        // UC4: Two Pointer Technique
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
        printResult(input, isPalindromeTwoPointer);

        // =================================
        // UC5: Stack Technique
        // =================================
        Stack<Character> stack = new Stack<>();

        for (char c : charArray)
            stack.push(Character.toLowerCase(c));

        boolean isPalindromeStack = true;

        for (char c : charArray) {
            if (Character.toLowerCase(c) != stack.pop()) {
                isPalindromeStack = false;
                break;
            }
        }

        System.out.println("\n--- Stack Technique Result ---");
        printResult(input, isPalindromeStack);

        // =================================
        // UC6: Queue + Stack Technique
        // =================================
        Queue<Character> queue = new LinkedList<>();
        Stack<Character> stack2 = new Stack<>();

        for (char c : charArray) {
            char ch = Character.toLowerCase(c);
            queue.add(ch);
            stack2.push(ch);
        }

        boolean isPalindromeQueueStack = true;

        while (!queue.isEmpty()) {
            if (queue.remove() != stack2.pop()) {
                isPalindromeQueueStack = false;
                break;
            }
        }

        System.out.println("\n--- Queue + Stack Technique Result ---");
        printResult(input, isPalindromeQueueStack);

        // =================================
        // UC7: Deque Technique
        // =================================
        Deque<Character> deque = new ArrayDeque<>();

        for (char c : charArray)
            deque.add(Character.toLowerCase(c));

        boolean isPalindromeDeque = true;

        while (deque.size() > 1) {
            if (deque.removeFirst() != deque.removeLast()) {
                isPalindromeDeque = false;
                break;
            }
        }

        System.out.println("\n--- Deque Technique Result ---");
        printResult(input, isPalindromeDeque);

        // =================================
        // UC8: Linked List Technique
        // =================================
        Node head = null;

        for (char c : charArray)
            head = append(head, c);

        boolean isPalindromeLinkedList = isPalindromeLinkedList(head);

        System.out.println("\n--- Linked List Technique Result ---");
        printResult(input, isPalindromeLinkedList);

        // =================================
        // UC9: Recursive Technique
        // =================================
        boolean isPalindromeRecursive = isPalindromeRecursive(input.toLowerCase(), 0, input.length() - 1);

        System.out.println("\n--- Recursive Technique Result ---");
        printResult(input, isPalindromeRecursive);

        // =================================
        // UC10: Ignore Spaces & Case
        // =================================
        String normalized = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        boolean isPalindromeNormalized = true;

        int l = 0;
        int r = normalized.length() - 1;

        while (l < r) {
            if (normalized.charAt(l) != normalized.charAt(r)) {
                isPalindromeNormalized = false;
                break;
            }
            l++;
            r--;
        }

        System.out.println("\n--- Case-Insensitive Result ---");
        printResult(input, isPalindromeNormalized);

        // =================================
        // UC11: OOP Palindrome Service
        // =================================
        PalindromeChecker service = new PalindromeChecker();
        boolean isPalindromeOOP = service.checkPalindrome(input);

        System.out.println("\n--- OOP Service Result ---");
        printResult(input, isPalindromeOOP);

        // =================================
        // UC12: Strategy Pattern
        // =================================
        PalindromeStrategy strategy;

        // choose strategy dynamically
        strategy = new StackStrategy();  // can switch to DequeStrategy()

        boolean strategyResult = strategy.checkPalindrome(input);

        System.out.println("\n--- Strategy Pattern Result (" + strategy.getClass().getSimpleName() + ") ---");
        printResult(input, strategyResult);

        scanner.close();
    }

    // =========================
    // Welcome message
    // =========================
    private static void displayWelcomeMessage() {
        System.out.println("========================================");
        System.out.println("   Welcome to " + APP_NAME);
        System.out.println("   Version: " + VERSION);
        System.out.println("========================================\n");
    }

    private static void printResult(String input, boolean result) {
        if (result)
            System.out.println("'" + input + "' is a palindrome.");
        else
            System.out.println("'" + input + "' is NOT a palindrome.");
    }

    // =========================
    // Linked List Methods
    // =========================

    static Node append(Node head, char data) {
        Node newNode = new Node(Character.toLowerCase(data));

        if (head == null)
            return newNode;

        Node temp = head;

        while (temp.next != null)
            temp = temp.next;

        temp.next = newNode;
        return head;
    }

    static Node reverse(Node head) {

        Node prev = null;
        Node current = head;
        Node next;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    static boolean isPalindromeLinkedList(Node head) {

        if (head == null || head.next == null)
            return true;

        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node secondHalf = reverse(slow.next);

        Node firstHalf = head;
        Node temp = secondHalf;

        while (temp != null) {
            if (firstHalf.data != temp.data)
                return false;

            firstHalf = firstHalf.next;
            temp = temp.next;
        }

        return true;
    }

    // =========================
    // Recursive Method
    // =========================
    static boolean isPalindromeRecursive(String str, int left, int right) {

        if (left >= right)
            return true;

        if (str.charAt(left) != str.charAt(right))
            return false;

        return isPalindromeRecursive(str, left + 1, right - 1);
    }
}

// =================================
// UC11 OOP Service Class
// =================================
class PalindromeChecker {

    public boolean checkPalindrome(String input) {

        String processed = input.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");

        int left = 0;
        int right = processed.length() - 1;

        while (left < right) {

            if (processed.charAt(left) != processed.charAt(right))
                return false;

            left++;
            right--;
        }

        return true;
    }
}

// =================================
// UC12 Strategy Pattern
// =================================

interface PalindromeStrategy {
    boolean checkPalindrome(String input);
}

// Stack Strategy
class StackStrategy implements PalindromeStrategy {

    public boolean checkPalindrome(String input) {

        Stack<Character> stack = new Stack<>();
        char[] arr = input.toLowerCase().toCharArray();

        for (char c : arr)
            stack.push(c);

        for (char c : arr)
            if (c != stack.pop())
                return false;

        return true;
    }
}

// Deque Strategy
class DequeStrategy implements PalindromeStrategy {

    public boolean checkPalindrome(String input) {

        Deque<Character> deque = new ArrayDeque<>();

        for (char c : input.toLowerCase().toCharArray())
            deque.add(c);

        while (deque.size() > 1) {

            if (deque.removeFirst() != deque.removeLast())
                return false;
        }

        return true;
    }
}