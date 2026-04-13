import java.util.*;

public class tryBST {
    
    // This represents a single "block" or node in our tree
    static class tNode {
        int value;
        tNode left;
        tNode right;
        
        tNode(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
    
    private tNode root;
    
    public tryBST() {
        this.root = null;
    }
    // Adds a new number to the tree.
    public void insert(int value) {
        root = insertHelper(root, value);
    }
    
    private tNode insertHelper(tNode node, int value) {
        // If we find an empty spot, put the new node here
        if (node == null) {
            return new tNode(value);
        }
        // If the value is smaller, go down the left side
        if (value < node.value) {
            node.left = insertHelper(node.left, value);
        // If the value is larger, go down the right side
        } else if (value > node.value) {
            node.right = insertHelper(node.right, value);
        }
        
        return node;
    }
    // Removes a specific number from the tree
    public void delete(int value) {
        root = deleteHelper(root, value);
    }
    
    private tNode deleteHelper(tNode node, int value) {
        if (node == null) {
            return null;
        }
        // Search for the node to delete
        if (value < node.value) {
            node.left = deleteHelper(node.left, value);
        } else if (value > node.value) {
            node.right = deleteHelper(node.right, value);
        } else {
            
            if (node.left == null && node.right == null) {
                return null;
            }
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            tNode successor = findMin(node.right);
            node.value = successor.value;
            node.right = deleteHelper(node.right, successor.value);

        }
        
        return node; 
    }

    private tNode findMin(tNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    // Checks if the tree follows the rules
    public boolean isBST() {
        return isBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean isBSTHelper(tNode node, long minValue, long maxValue) {
        if (node == null) {
            return true;
        }
        
        if (node.value <= minValue || node.value >= maxValue) {
            // If a node breaks the min/max rules, it's not a valid BST
            return false;
        }
        
        return isBSTHelper(node.left, minValue, node.value) &&
               isBSTHelper(node.right, node.value, maxValue);
    }
    public void populateBalanced(int n) {
        // Fills the tree with numbers in a way that keeps it perfectly balanced
        int maxValue = (1 << n) - 1;
        populateRangeHelper(1, maxValue);
    }
    
    private void populateRangeHelper(int low, int high) {
        if (low > high) {
            return;
        }
        // Always insert the middle number first to keep it balanced
        int middle = low + (high - low) / 2;
        insert(middle);
        
        populateRangeHelper(low, middle - 1);
        populateRangeHelper(middle + 1, high);
    }
    
    public void deleteAllEvens() {
        //Finds and deletes all even numbers in the tree
        List<Integer> evens = new ArrayList<>();
        collectEvens(root, evens);
        
        for (int value : evens) {
            delete(value);
        }
    }
    // Helper to scan the tree and save all even numbers into a list
    private void collectEvens(tNode node, List<Integer> evens) {
        if (node == null) {
            return;
        }
        
        if (node.value % 2 == 0) {
            evens.add(node.value);
        }
        
        collectEvens(node.left, evens);
        collectEvens(node.right, evens);
    }
    
    public int countNodes() {
        //Counts how many total nodes are in the tree
        return countNodesHelper(root);
    }
    
    private int countNodesHelper(tNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodesHelper(node.left) + countNodesHelper(node.right);
    }
    
    public void inOrder() {
        inOrderHelper(root);
        System.out.println();
    }
    
    private void inOrderHelper(tNode node) {
        if (node == null) {
            return;
        }
        inOrderHelper(node.left);
        System.out.print(node.value + " ");
        inOrderHelper(node.right);
    }
    public static void main(String[] args) {
        System.out.println("CSC 211 - Binary Search Tree, Prac 7\n");
        
        System.out.println("Testing with small values...");
        testBST(5);
        testBST(6);
        testBST(7);
        
        System.out.println("\nGathering timing statistics (n = 20, 30 iterations)");
        gatherStatistics(20, 30);
    }
    
    private static void testBST(int n) {
        System.out.println("\nn=" + n);
        
        tryBST tree = new tryBST();
        
        tree.populateBalanced(n);
        System.out.println("  Populated: " + tree.countNodes() + " nodes");
        System.out.println("  Valid BST: " + tree.isBST());
        
        tree.deleteAllEvens();
        System.out.println("  After deletion: " + tree.countNodes() + " nodes");
        System.out.println("  Still valid: " + tree.isBST());
    }

    private static void gatherStatistics(int n, int reps) {
        long[] popTimes = new long[reps];
        long[] delTimes = new long[reps];
        
        for (int i = 0; i < reps; i++) {
            tryBST tree = new tryBST();
            
            long start = System.currentTimeMillis();
            tree.populateBalanced(n);
            popTimes[i] = System.currentTimeMillis() - start;
            
            start = System.currentTimeMillis();
            tree.deleteAllEvens();
            delTimes[i] = System.currentTimeMillis() - start;
            
            if ((i + 1) % 10 == 0) {
                System.out.println("  Iteration " + (i + 1) + "/" + reps);
            }
        }

        Stats popStats = calculateStats(popTimes);
        Stats delStats = calculateStats(delTimes);
        
        System.out.println("\nTiming Results:");
        System.out.println("Number of\tAverage time\tStandard");
        System.out.println("Method\t\tkeys n\t\tin ms.\t\tDeviation");
        System.out.println("-".repeat(55));
        System.out.printf("Populate tree\t%d\t\t%.2f\t\t%.2f\n", 
            (1 << n) - 1, popStats.average, popStats.stdDev);
        System.out.printf("Remove evens\t%d\t\t%.2f\t\t%.2f\n", 
            (1 << n) - 1, delStats.average, delStats.stdDev);
    }

            static class Stats {
                double average;
                double stdDev;
                
                Stats(double average, double stdDev) {
                    this.average = average;
                    this.stdDev = stdDev;
                }
            }

            private static Stats calculateStats(long[] times) {
                long sum = 0;
                for (long time : times) {
                    sum += time;
                }
                double average = (double) sum / times.length;
                
                double sumSqDiff = 0;
                for (long time : times) {
                    double diff = time - average;
                    sumSqDiff += diff * diff;
                }
                double variance = sumSqDiff / times.length;
                double stdDev = Math.sqrt(variance);
                
                return new Stats(average, stdDev);
            }
        }

    




