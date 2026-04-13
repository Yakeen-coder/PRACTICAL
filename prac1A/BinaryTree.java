package prac1A;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<E> {
    private BinaryTreeNode<E> root;

    public BinaryTree() {
        this.root = null;
    }

    public BinaryTree(BinaryTreeNode<E> root) {
        this.root = root;
    }

    public BinaryTreeNode<E> getRoot() {
        return root;
    }

    public void setRoot(BinaryTreeNode<E> root) {
        this.root = root;
    }

    public void printLevelOrder() {
        if (root == null) {
            System.out.println("(empty)");
            return;
        }

        Queue<BinaryTreeNode<E>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            BinaryTreeNode<E> current = queue.remove();
            System.out.print(current.getValue() + " ");

            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }
            if (current.getRight() != null) {
                queue.add(current.getRight());
            }
        }
        System.out.println();
    }
}
