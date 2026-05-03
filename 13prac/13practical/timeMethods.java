// Yakeen Lucas
// 13practical
// 4482051

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class timeMethods {

    private static class Node {
        int key;
        @SuppressWarnings("unused")
        String data;

        Node(int key, String data) {
            this.key = key;
            this.data = data;
        }
    }

    public static Node[] records;
    public static int N;

    public static int linearsearch(Node[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].key == target) {
                return i;
            }
        }
        return -1;
    }

    public static int binarysearch(Node[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midKey = arr[mid].key;
            if (midKey == target) {
                return mid;
            } else if (midKey < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    private static void loadRecords() throws IOException {
        ArrayList<Node> list = new ArrayList<Node>();
        BufferedReader br = new BufferedReader(new FileReader("ulysses.numbered"));
        try {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() < 5) {
                    continue;
                }
                String keyStr = line.substring(0, 5).trim();
                if (keyStr.length() == 0) {
                    continue;
                }
                int key = Integer.parseInt(keyStr);
                String data = "";
                if (line.length() > 6) {
                    data = line.substring(6);
                }
                list.add(new Node(key, data));
            }
        } finally {
            br.close();
        }
        N = list.size();
        records = list.toArray(new Node[N]);
    }

    public static void main(String args[]) throws IOException {
        loadRecords();

        DecimalFormat fourD = new DecimalFormat("0.0000");
        DecimalFormat fiveD = new DecimalFormat("0.00000");

        long start, finish;
        double time;
        int n = N;
        int repetition;
        int repetitions = 30;

        Random rand = new Random();

        double runTimeLinear = 0.0;
        double runTime2Linear = 0.0;
        double runTimeBinary = 0.0;
        double runTime2Binary = 0.0;

        for (repetition = 0; repetition < repetitions; repetition++) {
            int targetKey = records[0].key + rand.nextInt(records[N - 1].key - records[0].key + 1);

            start = System.currentTimeMillis();
            linearsearch(records, targetKey);
            finish = System.currentTimeMillis();
            time = (double) (finish - start);
            runTimeLinear += time;
            runTime2Linear += time * time;

            start = System.currentTimeMillis();
            binarysearch(records, targetKey);
            finish = System.currentTimeMillis();
            time = (double) (finish - start);
            runTimeBinary += time;
            runTime2Binary += time * time;
        }

        double aveRuntimeLinear = runTimeLinear / repetitions;
        double stdDeviationLinear =
                Math.sqrt(runTime2Linear - repetitions * aveRuntimeLinear * aveRuntimeLinear) / (repetitions - 1);

        double aveRuntimeBinary = runTimeBinary / repetitions;
        double stdDeviationBinary =
                Math.sqrt(runTime2Binary - repetitions * aveRuntimeBinary * aveRuntimeBinary) / (repetitions - 1);

        System.out.printf("\n\n\fStatistics\n");
        System.out.println("________________________________________________");
        System.out.println("Linear search average time       = " + fiveD.format(aveRuntimeLinear / 1000) + "s.");
        System.out.println("Linear search std deviation (ms) = " + fourD.format(stdDeviationLinear));
        System.out.println("Binary search average time       = " + fiveD.format(aveRuntimeBinary / 1000) + "s.");
        System.out.println("Binary search std deviation (ms) = " + fourD.format(stdDeviationBinary));
        System.out.println("n                                = " + n);
        System.out.println("Repetitions                      = " + repetitions);
        System.out.println("________________________________________________");
        System.out.println();
        System.out.println();
    }
}