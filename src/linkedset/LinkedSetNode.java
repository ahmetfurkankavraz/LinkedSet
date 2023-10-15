package linkedset;

public class LinkedSetNode {
    int data;
    LinkedSetNode parent;
    LinkedSetNode left;
    LinkedSetNode right;
    LinkedSetNode prev;
    LinkedSetNode next;
    int color;

    public int getData() {
        return data;
    }
}
