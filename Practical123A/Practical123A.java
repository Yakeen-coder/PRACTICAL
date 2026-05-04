/**
 * Student Name: Yakeen Lucas
 * Module: CSC211 (2026)
 * Assessment: Term 2 Practical 3A
 * Student Number : 4482051
 * 
 * Sources include:
 * Class notes on Ternary Heaps and Array representation.
 */

public class Practical123A {

    // Question 1: Prints the children for each node in a 1-based ternary heap.
    public static void print(int[] heap) {
        if (heap == null || heap.length <= 1) {
            return;
        }

        int n = heap.length - 1;
        for (int i = 1; i <= n; i++) {
            int c1 = 3 * i - 1;
            int c2 = 3 * i;
            int c3 = 3 * i + 1;

            // If the first child doesn't exist, it's a leaf node
            if (c1 > n) {
                continue; 
            }

            StringBuilder line = new StringBuilder();
            line.append("Node ").append(heap[i]).append(" -> ");
            line.append(heap[c1]);

            if (c2 <= n) {
                line.append(", ").append(heap[c2]);
            }
            if (c3 <= n) {
                line.append(", ").append(heap[c3]);
            }

            System.out.println(line);
        }
    }

// Question 2: Validates if the array is a valid min or max ternary heap.
    public static int validate(int[] heap) {
        if (heap == null || heap.length <= 1) {
            return 1;
        }

        int n = heap.length - 1;
        boolean isMinHeap = true;
        boolean isMaxHeap = true;

        for (int i = 1; i <= n; i++) {
            int[] children = {3 * i - 1, 3 * i, 3 * i + 1};

            for (int childIndex : children) {
                if (childIndex <= n) {
                    // Check Min-heap property: Parent <= Child
                    if (heap[i] > heap[childIndex]) {
                        isMinHeap = false;
                    }
                    // Check Max-heap property: Parent >= Child
                    if (heap[i] < heap[childIndex]) {
                        isMaxHeap = false;
                    }
                }
            }
            
            // If it's neither min nor max at any point, we can stop early
            if (!isMinHeap && !isMaxHeap) {
                return -1;
            }
        }

        return (isMinHeap || isMaxHeap) ? 1 : -1;
    }
//Test Cases
    public static void main(String[] args) {
        // Test Case 1: A valid Min Ternary Heap
        System.out.println("--- Test Case 1: Valid Min Heap ---");
        int[] minHeap = {0, 5, 10, 12, 15, 20, 25, 30, 35}; 
        print(minHeap);
        System.out.println("Validation Result: " + validate(minHeap)); // Expected ValidationResult is 1

        System.out.println("\n--- Test Case 2: Valid Max Heap ---");
        // Test Case 2: A valid Max Ternary Heap
        int[] maxHeap = {0, 100, 50, 40, 30, 20, 10};
        print(maxHeap);
        System.out.println("Validation Result: " + validate(maxHeap)); // Expected Validation Result is 1

        System.out.println("\n--- Test Case 3: Invalid Heap ---");
        // Test Case 3: Random array (Invalid heap)
        int[] invalid = {0, 10, 50, 5, 100};
        print(invalid);
        System.out.println("Validation Result: " + validate(invalid)); // Expected ValidationResult is -1
    }
}
