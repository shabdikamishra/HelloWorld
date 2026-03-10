import java.util.*;

public class PalindromeCheckerAppUpdated {

    private static final String APP_NAME = "Palindrome Checker App";
    private static final String VERSION = "1.0.0";

    // =========================
    // Linked List Node (UC8)
    // =========================
    static class Node {
        char data;
        Node next;

        Node(char data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {

        displayWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string to check: ");
        String input = scanner.nextLine();

        char[] charArray = input.toCharArray();

        // =================================
        // UC4 Two Pointer
        // =================================
        boolean twoPointer = twoPointerCheck(input);
        System.out.println("\n--- Two Pointer Technique ---");
        printResult(input, twoPointer);

        // =================================
        // UC5 Stack
        // =================================
        boolean stackResult = stackCheck(input);
        System.out.println("\n--- Stack Technique ---");
        printResult(input, stackResult);

        // =================================
        // UC6 Queue + Stack
        // =================================
        boolean queueStack = queueStackCheck(input);
        System.out.println("\n--- Queue + Stack Technique ---");
        printResult(input, queueStack);

        // =================================
        // UC7 Deque
        // =================================
        boolean dequeResult = dequeCheck(input);
        System.out.println("\n--- Deque Technique ---");
        printResult(input, dequeResult);

        // =================================
        // UC8 Linked List
        // =================================
        Node head = null;
        for (char c : charArray)
            head = append(head, c);

        boolean linkedList = isPalindromeLinkedList(head);
        System.out.println("\n--- Linked List Technique ---");
        printResult(input, linkedList);

        // =================================
        // UC9 Recursion
        // =================================
        boolean recursive = isPalindromeRecursive(input.toLowerCase(), 0, input.length() - 1);
        System.out.println("\n--- Recursive Technique ---");
        printResult(input, recursive);

        // =================================
        // UC10 Ignore Spaces & Case
        // =================================
        String normalized = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        boolean normalizedResult = twoPointerCheck(normalized);
        System.out.println("\n--- Case-Insensitive & Space-Ignored ---");
        printResult(input, normalizedResult);

        // =================================
        // UC11 OOP Service
        // =================================
        PalindromeChecker service = new PalindromeChecker();
        boolean oopResult = service.checkPalindrome(input);
        System.out.println("\n--- OOP Service ---");
        printResult(input, oopResult);

        // =================================
        // UC12 Strategy Pattern
        // =================================
        PalindromeStrategy strategy = new StackStrategy(); // can change to DequeStrategy
        boolean strategyResult = strategy.checkPalindrome(input);
        System.out.println("\n--- Strategy Pattern (" + strategy.getClass().getSimpleName() + ") ---");
        printResult(input, strategyResult);

        // =================================
        // UC13 Performance Comparison
        // =================================
        System.out.println("\n--- Performance Comparison (nanoTime) ---");

        long start, end;

        start = System.nanoTime();
        twoPointerCheck(input);
        end = System.nanoTime();
        System.out.println("Two Pointer Time: " + (end - start) + " ns");

        start = System.nanoTime();
        stackCheck(input);
        end = System.nanoTime();
        System.out.println("Stack Time: " + (end - start) + " ns");

        start = System.nanoTime();
        dequeCheck(input);
        end = System.nanoTime();
        System.out.println("Deque Time: " + (end - start) + " ns");

        start = System.nanoTime();
        isPalindromeRecursive(input.toLowerCase(), 0, input.length() - 1);
        end = System.nanoTime();
        System.out.println("Recursive Time: " + (end - start) + " ns");

        scanner.close();
    }

    // =========================
    // Welcome Message
    // =========================
    private static void displayWelcomeMessage() {
        System.out.println("========================================");
        System.out.println("Welcome to " + APP_NAME);
        System.out.println("Version: " + VERSION);
        System.out.println("========================================\n");
    }

    private static void printResult(String input, boolean result) {
        if (result)
            System.out.println("'" + input + "' is a palindrome.");
        else
            System.out.println("'" + input + "' is NOT a palindrome.");
    }

    // =========================
    // UC4 Two Pointer
    // =========================
    static boolean twoPointerCheck(String input) {

        char[] arr = input.toLowerCase().toCharArray();
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            if (arr[left] != arr[right])
                return false;

            left++;
            right--;
        }

        return true;
    }

    // =========================
    // UC5 Stack
    // =========================
    static boolean stackCheck(String input) {

        Stack<Character> stack = new Stack<>();
        char[] arr = input.toLowerCase().toCharArray();

        for (char c : arr)
            stack.push(c);

        for (char c : arr)
            if (c != stack.pop())
                return false;

        return true;
    }

    // =========================
    // UC6 Queue + Stack
    // =========================
    static boolean queueStackCheck(String input) {

        Queue<Character> queue = new LinkedList<>();
        Stack<Character> stack = new Stack<>();

        for (char c : input.toLowerCase().toCharArray()) {
            queue.add(c);
            stack.push(c);
        }

        while (!queue.isEmpty())
            if (queue.remove() != stack.pop())
                return false;

        return true;
    }

    // =========================
    // UC7 Deque
    // =========================
    static boolean dequeCheck(String input) {

        Deque<Character> deque = new ArrayDeque<>();

        for (char c : input.toLowerCase().toCharArray())
            deque.add(c);

        while (deque.size() > 1)
            if (deque.removeFirst() != deque.removeLast())
                return false;

        return true;
    }

    // =========================
    // UC8 Linked List
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

        while (head != null) {
            Node next = head.next;
            head.next = prev;
            prev = head;
            head = next;
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

        while (secondHalf != null) {

            if (firstHalf.data != secondHalf.data)
                return false;

            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }

        return true;
    }

    // =========================
    // UC9 Recursion
    // =========================
    static boolean isPalindromeRecursive(String str, int left, int right) {

        if (left >= right)
            return true;

        if (str.charAt(left) != str.charAt(right))
            return false;

        return isPalindromeRecursive(str, left + 1, right - 1);
    }
}

// =========================
// UC11 OOP Service
// =========================
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

// =========================
// UC12 Strategy Pattern
// =========================
interface PalindromeStrategy {
    boolean checkPalindrome(String input);
}

class StackStrategy implements PalindromeStrategy {

    public boolean checkPalindrome(String input) {
        return PalindromeCheckerAppUpdated.stackCheck(input);
    }
}

class DequeStrategy implements PalindromeStrategy {

    public boolean checkPalindrome(String input) {
        return PalindromeCheckerAppUpdated.dequeCheck(input);
    }
}