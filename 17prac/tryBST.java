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
    