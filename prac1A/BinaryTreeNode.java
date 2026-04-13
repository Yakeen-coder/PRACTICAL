package prac1A;

public class BinaryTreeNode<E> {
    private E value;
    private BinaryTreeNode<E> left;
    private BinaryTreeNode<E> right;

    public BinaryTreeNode() {
        this(null, null, null);
    }

    public BinaryTreeNode(E value, BinaryTreeNode<E> left, BinaryTreeNode<E> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public E getValue() {
        return value;
    }

    public BinaryTreeNode<E> getLeft() {
        return left;
    }

    public BinaryTreeNode<E> getRight() {
        return right;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public void setLeft(BinaryTreeNode<E> left) {
        this.left = left;
    }

    public void setRight(BinaryTreeNode<E> right) {
        this.right = right;
    }
}
