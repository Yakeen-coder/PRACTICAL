//Yakeen Lucas
//4482051
//Used Claude Haiku (Free version)

import java.io.*;
import java.util.*;

public class TryHeapsort {    
    public static void main(String[] args) {
        System.out.println("Loading Words");
        
        // Load words from file
        List<String> allWords = loadWords("joyce1922_ulysses.text");
        System.out.println("Loaded " + allWords.size() + " words");
        
        // Show first 20 words
        System.out.println("\nFirst 20 words:");
        List<String> testWords = new ArrayList<>(allWords.subList(0, Math.min(20, allWords.size())));
        for (int i = 0; i < testWords.size(); i++) {
            System.out.println((i+1) + ". " + testWords.get(i));
        }
        System.out.println("\nSmall test array: " + testWords);

        // Test bottom-up
        System.out.println("\n--- BOTTOM-UP HEAPSORT ---");
        List<String> sorted1 = heapSortBottomUp(new ArrayList<>(testWords));
        System.out.println("Result: " + sorted1);
        System.out.println("Sorted? " + isSorted(sorted1));
        
        // Test top-down
        System.out.println("\n--- TOP-DOWN HEAPSORT ---");
        List<String> sorted2 = heapSortTopDown(new ArrayList<>(testWords));
        System.out.println("Result: " + sorted2);
        System.out.println("Sorted? " + isSorted(sorted2));
        
        // Timing comparison
        System.out.println("\n--- TIMING COMPARISON ---\n");
        System.out.println("Size\t\tBottom-Up\tTop-Down");
        System.out.println("----\t\t---------\t--------");
        
        int[] sizes = {1000, 5000, 10000, allWords.size()};
        
        for (int size : sizes) {
            if (size > allWords.size()) continue;
            
            List<String> testData = new ArrayList<>(allWords.subList(0, size));
            
            // Time bottom-up
            long start = System.currentTimeMillis();
            heapSortBottomUp(new ArrayList<>(testData));
            long time1 = System.currentTimeMillis() - start;
            
            // Time top-down
            start = System.currentTimeMillis();
            heapSortTopDown(new ArrayList<>(testData));
            long time2 = System.currentTimeMillis() - start;
            
            System.out.printf("%d\t\t%d ms\t\t%d ms\n", size, time1, time2);
        }
    }

    // Bottom-up heapsort
    static List<String> heapSortBottomUp(List<String> words) {
        String[] heap = words.toArray(new String[0]);
        int n = heap.length;
        
        // Build heap from bottom-up
        for (int i = n / 2 - 1; i >= 0; i--) {
            bubbleDown(heap, i, n);
        }
        
        // Extract elements in sorted order
        List<String> result = new ArrayList<>();
        for (int i = n - 1; i > 0; i--) {
            result.add(heap[0]);
            heap[0] = heap[i];
            bubbleDown(heap, 0, i);
        }
        result.add(heap[0]);
        Collections.reverse(result);
        return result;
    }

    // Top-down heapsort
    static List<String> heapSortTopDown(List<String> words) {
        SimpleHeap heap = new SimpleHeap();
        for (String word : words) {
            heap.insert(word);
        }
        List<String> result = new ArrayList<>();
        while (!heap.isEmpty()) {
            result.add(heap.getMin());
        }
        return result;
    }

    // Bubble down
    static void bubbleDown(String[] heap, int i, int n) {
        while (true) {
            int left = 2 * i + 1;
            int right = left + 1;
            int smallest = i;

            if (left < n && heap[left].compareTo(heap[smallest]) < 0) {
                smallest = left;
            }
            if (right < n && heap[right].compareTo(heap[smallest]) < 0) {
                smallest = right;
            }
            if (smallest == i) {
                return;
            }

            String tmp = heap[i];
            heap[i] = heap[smallest];
            heap[smallest] = tmp;
            i = smallest;
        }
    }

    // Check if array is sorted
    static boolean isSorted(List<String> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }
    
    // Load words from file
    static List<String> loadWords(String filename) {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\W+");
                for (String word : parts) {
                    String clean = word.toLowerCase().trim();
                    if (!clean.isEmpty()) {
                        words.add(clean);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return words;
    }
}

// Simple heap for top-down
class SimpleHeap {
    private List<String> items = new ArrayList<>();
    
    void insert(String word) {
        items.add(word);
        bubbleUp(items.size() - 1);
    }
    
    String getMin() {
        if (items.isEmpty()) return null;
        String min = items.get(0);
        items.set(0, items.get(items.size() - 1));
        items.remove(items.size() - 1);
        if (!items.isEmpty()) {
            bubbleDown(0);
        }
        return min;
    }
    
    boolean isEmpty() {
        return items.isEmpty();
    }
    
    private void bubbleUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (items.get(i).compareTo(items.get(parent)) < 0) {
                String tmp = items.get(i);
                items.set(i, items.get(parent));
                items.set(parent, tmp);
                i = parent;
            } else {
                break;
            }
        }
    }
    // Bubble down
    private void bubbleDown(int i) {
        while (true) {
            int smallest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            
            if (left < items.size() && items.get(left).compareTo(items.get(smallest)) < 0) {
                smallest = left;
            }
            if (right < items.size() && items.get(right).compareTo(items.get(smallest)) < 0) {
                smallest = right;
            }
            
            if (smallest != i) {
                String tmp = items.get(i);
                items.set(i, items.get(smallest));
                items.set(smallest, tmp);
                i = smallest;
            } else {
                break;
            }
        }
    }
}
