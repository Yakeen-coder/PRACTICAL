import java.io.*;
import java.util.*;

public class TryHeapsort {    
    public static void main(String[] args) {
        System.out.println("Loading Words");
        
        // Load words from file
        List<String> words = loadWords("joyce1922_ulysses.text");
        System.out.println("Loaded " + words.size() + " words");
        
        // Show first 20 words
        System.out.println("\nFirst 20 words:");
        for (int i = 0; i < Math.min(20, words.size()); i++) {
            System.out.println((i+1) + ". " + words.get(i));
        }
        
        // Get small test array
        System.out.println("\nSmall test array (first 20 words):");
        List<String> testWords = new ArrayList<>(words.subList(0, Math.min(20, words.size())));
        System.out.println(testWords);

        List<String> sorted = heapSortBottomUp(testWords);
        System.out.println("\nHeapsort result (ascending):");
        System.out.println(sorted);
        System.out.println("Sorted? " + isSorted(sorted));
    }

    // Heapsort using bottom-up heap construction (min-heap).

     static List<String> heapSortBottomUp(List<String> words) {
        // Convert to array
        String[] heap = words.toArray(new String[0]);
        int n = heap.length;
        
        // Build heap from bottom-up
        System.out.println("\nBuilding heap from bottom-up...");
        for (int i = n / 2 - 1; i >= 0; i--) {
            bubbleDown(heap, i, n);
        }
        System.out.println("Heap built: " + Arrays.toString(heap));
        
        // Extract elements in sorted order
        List<String> result = new ArrayList<>();
        for (int i = n - 1; i > 0; i--) {
            result.add(heap[0]);  // Save root (smallest)
            heap[0] = heap[i];    // Move last to root
            bubbleDown(heap, 0, i); // Fix heap
        }
        result.add(heap[0]); // Add last element
        return result;
    }

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
    //Check if array is sorted
     static boolean isSorted(List<String> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                return false; // Out of order
            }
        }
        return true;
    }
    
    //Load words from file

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
