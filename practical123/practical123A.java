
public class practical123A {
    /**
     * Prints each parent node and up to three children for a ternary heap
     * stored from index 1 (index 0 is ignored).
     */
    public static void print(int[] heap) {
        if (heap == null || heap.length <= 1) {
            return;
        }

        int n = heap.length - 1;
        for (int i = 1; i <= n; i++) {
            int c1 = 3 * i - 1;
            int c2 = 3 * i;
            int c3 = 3 * i + 1;

            if (c1 > n) {
                continue; // Leaf node, no children to print
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

    /**
     * Returns 1 if heap[] is a valid min-heap or max-heap ternary heap
     * (1-indexed array representation), or -1 otherwise.
     */
    public static int validate(int[] heap) {
        if (heap == null || heap.length <= 1) {
            return 1;
        }

        int n = heap.length - 1;
        boolean isMinHeap = true;
        boolean isMaxHeap = true;

        for (int i = 1; i <= n; i++) {
            int[] children = {3 * i - 1, 3 * i, 3 * i + 1};

            for (int child : children) {
                if (child > n) {
                    continue;
                }

                if (heap[i] > heap[child]) {
                    isMinHeap = false;
                }
                if (heap[i] < heap[child]) {
                    isMaxHeap = false;
                }

                if (!isMinHeap && !isMaxHeap) {
                    return -1;
                }
            }
        }

        return (isMinHeap || isMaxHeap) ? 1 : -1;
    }
}
