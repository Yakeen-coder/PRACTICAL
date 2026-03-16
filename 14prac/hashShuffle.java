import java.util.*;

class Pair {
    String key;
    String value;
    Pair(String k, String v) { this.key = k; this.value = v; }
}

public class hashShuffle {
    public static void main(String[] args) {
        int N_total = 1 << 20; // 1,048,576 
        int m = 1_000_000;      // Table size for alpha calculations
        int[] testSizes = {750_000, 800_000, 850_000, 900_000, 950_000}; 

        // 1. Generate and Shuffle Keys
        List<Integer> keys = new ArrayList<>(N_total);
        for (int i = 0; i < N_total; i++) keys.add(i);
        Collections.shuffle(keys);

        // 2. Create Pair Array 
        Pair[] dataStore = new Pair[N_total];
        for (int i = 0; i < N_total; i++) {
            dataStore[i] = new Pair(String.valueOf(keys.get(i)), String.valueOf(i + 1));
        }

        System.out.println("N\t\tOpen Hash (ms)\tChained Hash (ms)");
        
        // 3. Timing Experiments 
        for (int n : testSizes) {
            double openAvg = runExperiment("open", n, m, dataStore);
            double chainedAvg = runExperiment("chained", n, m, dataStore);
            System.out.printf("%d\t\t%.4f\t\t%.4f\n", n, openAvg, chainedAvg);
        }
    }

    private static double runExperiment(String type, int n, int m, Pair[] data) {
        long totalTime = 0;
        int repetitions = 30; // 

        for (int r = 0; r < repetitions; r++) {
            if (type.equals("open")) {
                openHash table = new openHash(m);
                for (int i = 0; i < n; i++) table.insert(data[i].key, data[i].value);
                
                long start = System.currentTimeMillis(); // [cite: 53]
                for (int i = 0; i < 10000; i++) table.lookup(data[i].key); 
                totalTime += (System.currentTimeMillis() - start);
            } else {
                chainedHash table = new chainedHash(m);
                for (int i = 0; i < n; i++) table.insert(data[i].key, data[i].value);
                
                long start = System.currentTimeMillis();
                for (int i = 0; i < 10000; i++) table.lookup(data[i].key);
                totalTime += (System.currentTimeMillis() - start);
            }
        }
        return (double) totalTime / repetitions;
    }
}