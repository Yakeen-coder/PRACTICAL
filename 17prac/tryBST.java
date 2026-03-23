import java.util.*;

public class tryBST {
    
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
    
    public void insert(int value) {
        root = insertHelper(root, value);
    }
    
    private tNode insertHelper(tNode node, int value) {
        if (node == null) {
            return new tNode(value);
        }
        
        if (value < node.value) {
            node.left = insertHelper(node.left, value);
        } else if (value > node.value) {
            node.right = insertHelper(node.right, value);
        }
        
        return node;
    }

    public void delete(int value) {
        root = deleteHelper(root, value);
    }
    
    private tNode deleteHelper(tNode node, int value) {
        if (node == null) {
            return null;
        }

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
    
    public boolean isBST() {
        return isBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean isBSTHelper(tNode node, long minValue, long maxValue) {
        if (node == null) {
            return true;
        }
        
        if (node.value <= minValue || node.value >= maxValue) {
            return false;
        }
        
        return isBSTHelper(node.left, minValue, node.value) &&
               isBSTHelper(node.right, node.value, maxValue);
    }
    public void populateBalanced(int n) {
        int maxValue = (1 << n) - 1;
        populateRangeHelper(1, maxValue);
    }
    
    private void populateRangeHelper(int low, int high) {
        if (low > high) {
            return;
        }


        int middle = low + (high - low) / 2;
        insert(middle);
        
        populateRangeHelper(low, middle - 1);
        populateRangeHelper(middle + 1, high);
    }
    
    public void deleteAllEvens() {
        List<Integer> evens = new ArrayList<>();
        collectEvens(root, evens);
        
        for (int value : evens) {
            delete(value);
        }
    }
    
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

    


    