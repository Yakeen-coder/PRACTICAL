import java.util.*;

public class Prac1 {

    public static int[] slowshuffle(int N) {
        int[] shuffled = new int[N + 1];
        boolean[] isNotPresent = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            isNotPresent[i] = true; [cite: 22, 24]
        }
        
        int i = 0; [cite: 30]
        while (i < N - 1) { [cite: 27, 41]
            int r = (int)(Math.random() * N) + 1; [cite: 19, 40]
            if (isNotPresent[r]) { [cite: 29]
                i += 1; [cite: 30]
                shuffled[i] = r; [cite: 31]
                isNotPresent[r] = false; [cite: 39]
            }
        }
        
        for (int r = 1; r <= N; r++) { [cite: 42]
            if (isNotPresent[r]) { [cite: 42]
                shuffled[N] = r; [cite: 42]
                break;
            }
        }
        
        return Arrays.copyOfRange(shuffled, 1, N + 1);
    }

    public static int[] biasedshuffle(int N) {
        int[] shuffled = new int[N];
        for (int i = 0; i < N; i++) {
            shuffled[i] = i + 1; [cite: 45, 46]
        }
        
        for (int i = 0; i < N; i++) {
            int r = (int)(Math.random() * N); [cite: 46]
            int temp = shuffled[i];
            shuffled[i] = shuffled[r]; [cite: 46]
            shuffled[r] = temp; [cite: 46]
        }
        return shuffled;
    }

    public static int[] shuffle(int N) {
        int[] B = new int[N];
        for (int i = 0; i < N; i++) {
            B[i] = i + 1; [cite: 52]
        }
        
        int b = 0; [cite: 53]
        while (b < N) { [cite: 54]
            int r = b + (int)(Math.random() * (N - b)); [cite: 55]
            int temp = B[b];
            B[b] = B[r]; [cite: 55]
            B[r] = temp; [cite: 55]
            b++; [cite: 56]
        }
        return B;
    }

    public static void runBiasTest(String mode) {
        Map<String, Integer> D = new HashMap<>(); [cite: 64]
        
        for (int i = 0; i < 60000; i++) { [cite: 57]
            int[] result = mode.equals("biased") ? biasedshuffle(3) : shuffle(3); [cite: 57]
            StringBuilder key = new StringBuilder();
            for (int val : result) {
                key.append(val); [cite: 63]
            }
            String k = key.toString();
            
            if (!D.containsKey(k)) { [cite: 67]
                D.put(k, 1); [cite: 69]
            } else { [cite: 71]
                D.put(k, D.get(k) + 1); [cite: 73]
            }
        }
        
        for (String key : new TreeSet<>(D.keySet())) { [cite: 78]
            System.out.println(key + " " + D.get(key)); [cite: 80]
        }
    }

    public static void main(String[] args) {
        System.out.println("Biased:");
        runBiasTest("biased");
        System.out.println("\nUnbiased:");
        runBiasTest("unbiased");
    }
}

