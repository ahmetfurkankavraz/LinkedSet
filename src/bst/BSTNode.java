package bst;

public class BSTNode {
    private int value;
    private BSTNode parent;
    private BSTNode left;
    private BSTNode right;
    private BSTNode prev;
    private BSTNode next;


    public BSTNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BSTNode getLeft() {
        return left;
    }

    public void setLeft(BSTNode left) {
        this.left = left;
    }

    public BSTNode getRight() {
        return right;
    }

    public void setRight(BSTNode right) {
        this.right = right;
    }

    public BSTNode getParent() {
        return parent;
    }

    public void setParent(BSTNode parent) {
        this.parent = parent;
    }

    public BSTNode getPrev() {
        return prev;
    }

    public void setPrev(BSTNode prev) {
        this.prev = prev;
    }

    public BSTNode getNext() {
        return next;
    }

    public void setNext(BSTNode next) {
        this.next = next;
    }
}
