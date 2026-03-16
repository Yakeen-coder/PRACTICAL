import java.io.*;
import java.util.*;

/**
 * Phase 1: Load words and set up basic structure
 * Time: ~30 minutes
 */
public class TryHeapsort {
    
    public static void main(String[] args) {
        System.out.println("=== Phase 1: Load Words ===\n");
        
        // Load words from file
        List<String> words = loadWords("joyce1922_ulysses.text");
        System.out.println("Loaded " + words.size() + " words");
        
        // Show first 10 words
        System.out.println("\nFirst 10 words:");
        for (int i = 0; i < Math.min(10, words.size()); i++) {
            System.out.println((i+1) + ". " + words.get(i));
        }
        
        // Get small test array
        System.out.println("\nSmall test array (first 10 words):");
        List<String> testWords = new ArrayList<>(words.subList(0, Math.min(10, words.size())));
        System.out.println(testWords);
    }
    
    /**
     * Load and clean words from the text file
     */
    static List<String> loadWords(String filename) {
        List<String> words = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split into words (anything that's not a letter/number)
                String[] parts = line.split("\\W+");
                
                for (String word : parts) {
                    String clean = word.toLowerCase().trim();
                    if (!clean.isEmpty()) {
                        words.add(clean);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error: Cannot read file " + filename);
            System.out.println(e.getMessage());
        }
        
        return words;
    }
}
