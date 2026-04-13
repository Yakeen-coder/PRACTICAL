package prac1A;

public class Main {
    public static void main(String[] args) {
        BinaryTreeNode<Character> bt1Root = new BinaryTreeNode<>('a', null, null);
        BinaryTreeNode<Character> bt1Left = new BinaryTreeNode<>('b', null, null);
        BinaryTreeNode<Character> bt1Right = new BinaryTreeNode<>('c', null, null);
        BinaryTreeNode<Character> bt1LeftLeft = new BinaryTreeNode<>('d', null, null);
        BinaryTreeNode<Character> bt1LeftRight = new BinaryTreeNode<>('e', null, null);

        bt1Root.setLeft(bt1Left);
        bt1Root.setRight(bt1Right);
        bt1Left.setLeft(bt1LeftLeft);
        bt1Left.setRight(bt1LeftRight);

        BinaryTree<Character> bt1 = new BinaryTree<>(bt1Root);

        BinaryTreeNode<Double> bt2Root = new BinaryTreeNode<>(3.4, null, null);
        BinaryTreeNode<Double> bt2Left = new BinaryTreeNode<>(-1.5, null, null);
        BinaryTreeNode<Double> bt2Right = new BinaryTreeNode<>(2.9, null, null);
        BinaryTreeNode<Double> bt2LeftLeft = new BinaryTreeNode<>(-9.3, null, null);

        bt2Root.setLeft(bt2Left);
        bt2Root.setRight(bt2Right);
        bt2Left.setLeft(bt2LeftLeft);

        BinaryTree<Double> bt2 = new BinaryTree<>(bt2Root);

        System.out.println("bt1 level order:");
        bt1.printLevelOrder();
        System.out.println("bt2 level order:");
        bt2.printLevelOrder();
    }
}
