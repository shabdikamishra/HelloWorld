import java.util.Scanner;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Deque;
import java.util.ArrayDeque;

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

        // UC2: Take user input
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
        if (isPalindromeTwoPointer)
            System.out.println("'" + input + "' is a palindrome.");
        else
            System.out.println("'" + input + "' is NOT a palindrome.");

        // =================================
        // UC5: Stack-Based Palindrome Check
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
        if (isPalindromeStack)
            System.out.println("'" + input + "' is a palindrome.");
        else
            System.out.println("'" + input + "' is NOT a palindrome.");

        // =================================
        // UC6: Queue + Stack Palindrome Check
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
        if (isPalindromeQueueStack)
            System.out.println("'" + input + "' is a palindrome.");
        else
            System.out.println("'" + input + "' is NOT a palindrome.");

        // =================================
        // UC7: Deque-Based Optimized Palindrome Check
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
        if (isPalindromeDeque)
            System.out.println("'" + input + "' is a palindrome.");
        else
            System.out.println("'" + input + "' is NOT a palindrome.");

        // =================================
        // UC8: Linked List Based Palindrome Check
        // =================================
        Node head = null;

        for (char c : charArray)
            head = append(head, c);

        boolean isPalindromeLinkedList = isPalindromeLinkedList(head);

        System.out.println("\n--- Linked List Technique Result ---");
        if (isPalindromeLinkedList)
            System.out.println("'" + input + "' is a palindrome.");
        else
            System.out.println("'" + input + "' is NOT a palindrome.");

        // =================================
        // UC9: Recursive Palindrome Checker
        // =================================
        boolean isPalindromeRecursive = isPalindromeRecursive(input.toLowerCase(), 0, input.length() - 1);

        System.out.println("\n--- Recursive Technique Result ---");
        if (isPalindromeRecursive)
            System.out.println("'" + input + "' is a palindrome.");
        else
            System.out.println("'" + input + "' is NOT a palindrome.");

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

    // =========================
    // Linked List Methods (UC8)
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
    // UC9 Recursive Method
    // =========================
    static boolean isPalindromeRecursive(String str, int left, int right) {

        // Base condition
        if (left >= right)
            return true;

        if (str.charAt(left) != str.charAt(right))
            return false;

        // Recursive call
        return isPalindromeRecursive(str, left + 1, right - 1);
    }
}