import java.util.Random;

public class MCSAlgorithms {

    public static void main(String[] args) {
        int[] ns = {100, 1000, 10000, 100000, 1000000};

        System.out.printf("%-10s %-15s %-15s %-15s %-15s%n", "n", "O(n^3)", "O(n^2)A", "O(n^2)B", "O(n)");

        for (int n : ns) {
            int[] X = generateArray(n);

            long countN3 = (n <= 1000) ? mcsOn3(X) : -1; 
            long countN2A = (n <= 10000) ? mcsOn2A(X) : -1;
            long countN2B = (n <= 10000) ? mcsOn2B(X) : -1;
            long countN = mcsOn(X);

            System.out.printf("%-10d %-15s %-15s %-15s %-15d%n", 
                n, 
                (countN3 == -1 ? "slow" : countN3),
                (countN2A == -1 ? "slow" : countN2A),
                (countN2B == -1 ? "slow" : countN2B),
                countN);
        }
    }

    public static int[] generateArray(int n) {
        Random rand = new Random();
        int[] X = new int[n];
        for (int i = 0; i < n; i++) {
            int val = rand.nextInt(n) + 1;
            int exponent = rand.nextInt(3) + 2; 
            X[i] = val * (int)Math.pow(-1, exponent);
        }
        return X;
    }

    public static long mcsOn3(int[] X) {
        int n = X.length;
        long maxSoFar = 0;
        long opsCount = 0;
        for (int low = 0; low < n; low++) {
            for (int high = low; high < n; high++) {
                int sum = 0;
                for (int r = low; r <= high; r++) {
                    opsCount++;
                    sum += X[r];
                }
                if (sum > maxSoFar) maxSoFar = sum;
            }
        }
        return opsCount;
    }

    public static long mcsOn2A(int[] X) {
        int n = X.length;
        long maxSoFar = 0;
        long opsCount = 0;
        for (int low = 0; low < n; low++) {
            int sum = 0;
            for (int high = low; high < n; high++) {
                opsCount++;
                sum += X[high];
                if (sum > maxSoFar) maxSoFar = sum;
            }
        }
        return opsCount;
    }

    public static long mcsOn2B(int[] X) {
        int n = X.length;
        long opsCount = 0;
        int[] sumTo = new int[n + 1];
        int currentSum = 0;
        for (int i = 0; i < n; i++) {
            currentSum += X[i];
            sumTo[i + 1] = currentSum;
        }
        long maxSoFar = 0;
        for (int low = 1; low <= n; low++) {
            for (int high = low; high <= n; high++) {
                opsCount++;
                int sum = sumTo[high] - sumTo[low - 1];
                if (sum > maxSoFar) maxSoFar = sum;
            }
        }
        return opsCount;
    }

    public static long mcsOn(int[] X) {
        int n = X.length;
        double maxSoFar = 0.0;
        double maxToHere = 0.0;
        long opsCount = 0;
        for (int i = 0; i < n; i++) {
            opsCount++;
            maxToHere = Math.max(maxToHere + X[i], 0.0);
            maxSoFar = Math.max(maxSoFar, maxToHere);
        }
        return opsCount;
    }
}