import java.util.*;

public class Prac1 {

    public static int[] slowshuffle(int N) {
        int[] shuffled = new int[N + 1];
        boolean[] isNotPresent = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            isNotPresent[i] = true; 
        }
        
        int i = 0; 
        while (i < N - 1) { 
            int r = (int)(Math.random() * N) + 1; 
            if (isNotPresent[r]) { 
                i += 1; 
                shuffled[i] = r; 
                isNotPresent[r] = false; 
            }
        }
        
        for (int r = 1; r <= N; r++) { 
            if (isNotPresent[r]) { 
                shuffled[N] = r; 
                break;
            }
        }
        
        return Arrays.copyOfRange(shuffled, 1, N + 1);
    }

    public static int[] biasedshuffle(int N){
        int[] shuffled = new int[N];
        for (int i = 0; i < N; i++) {
            shuffled[i] = i + 1;  
    }
        int[] shuffled = new int[N];
        for (int i = 0; i < N; i++) {
            shuffled[i] = i + 1; 
        }
        
        for (int i = 0; i < N; i++) {
            int r = (int)(Math.random() * N); 
            int temp = shuffled[i];
            shuffled[i] = shuffled[r]; 
            shuffled[r] = temp; 
        return shuffled;
    }

    public static int[] shuffle(int N) {
        int[] B = new int[N];
        for (int i = 0; i < N; i++) {
            B[i] = i + 1; 
        }
        
        int b = 0; 
        while (b < N) { 
            int r = b + (int)(Math.random() * (N - b)); 
            int temp = B[b];
            B[b] = B[r]; 
            B[r] = temp; 
            b++;
        }
        return B;
    }

    public static void runBiasTest(String mode) {
        Map<String, Integer> D = new HashMap<>(); 
        
        for (int i = 0; i < 60000; i++) { 
            int[] result = mode.equals("biased") ? biasedshuffle(3) : shuffle(3); [cite: 57]
            StringBuilder key = new StringBuilder();
            for (int val : result) {
                key.append(val); 
            }
            String k = key.toString();
            
            if (!D.containsKey(k)) { 
                D.put(k, 1); 
            } else { 
                D.put(k, D.get(k) + 1); 
            }
        }
        
        for (String key : new TreeSet<>(D.keySet())) {
            System.out.println(key + " " + D.get(key)); 
    }

    public static void main(String[] args) {
        System.out.println("Biased:");
        runBiasTest("biased");
        System.out.println("\nUnbiased:");
        runBiasTest("unbiased");
    }
}

