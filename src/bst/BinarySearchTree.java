package bst;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {

    BSTNode root;
    BSTNode begin;
    BSTNode end;

    public BinarySearchTree() {
        this.root = null;
    }

    public static BSTNode successor(BSTNode node) {
        if (node.getRight() != null) {
            return minimum(node);
        }
        BSTNode traverse = node.getParent();
        while (traverse != null && node == traverse.getRight()) {
            node = traverse;
            traverse = traverse.getParent();
        }
        return node;
    }

    public static BSTNode predecessor(BSTNode node) {
        if (node.getLeft() != null) {
            return maximum(node);
        }
        BSTNode traverse = node.getParent();
        while (traverse != null && node == traverse.getLeft()) {
            node = traverse;
            traverse = traverse.getParent();
        }
        return node;
    }

    public static BSTNode minimum(BSTNode node) {
        BSTNode traverse = node;
        while (traverse.getLeft() != null) {
            traverse = traverse.getLeft();
        }
        return traverse;
    }

    public static BSTNode maximum(BSTNode node) {
        BSTNode traverse = node;
        while (traverse.getRight() != null) {
            traverse = traverse.getRight();
        }
        return traverse;
    }

    private static void inorderHelper(BSTNode node, List<Integer> inorderList) {
        if (node == null)
            return;

        inorderHelper(node.getLeft(), inorderList);
        inorderList.add(node.getValue());
        inorderHelper(node.getRight(), inorderList);
    }

    public BSTNode search(int target) {
        BSTNode traverse = this.root;
        while (traverse != null && traverse.getValue() != target) {
            if (target < traverse.getValue()) {
                traverse = traverse.getLeft();
            } else {
                traverse = traverse.getRight();
            }
        }
        return traverse;
    }

    public void inorder() {
        List<Integer> values = new ArrayList<>();
        inorderHelper(root, values);
        System.out.println(values);
    }

    public void insert(int key) {
        BSTNode z = new BSTNode(key);
        BSTNode x = this.root;
        BSTNode y = null;

        while (x != null) {
            y = x;
            if (z.getValue() < x.getValue()) {
                x = x.getLeft();
            } else {
                x = x.getRight();
            }
        }

        z.setParent(y);
        if (y == null) {
            this.root = z;
            this.begin = z;
            this.end = z;
        } else if (z.getValue() < y.getValue()) {
            y.setLeft(z);

            z.setNext(y);
            z.setPrev(y.getPrev());
            if (y.getPrev() != null) {
                y.getPrev().setNext(z);
            } else {
                this.begin = z;
            }
            y.setPrev(z);
        } else {
            y.setRight(z);

            z.setNext(y.getNext());
            z.setPrev(y);
            if (y.getNext() != null) {
                y.getNext().setPrev(z);
            } else {
                this.end = z;
            }
            y.setNext(z);
        }
    }

    private void transplant(BSTNode u, BSTNode v) {
        if (u.getParent() == null) {
            this.root = v;
        } else if (u == u.getParent().getLeft()) {
            u.getParent().setLeft(v);
        } else {
            u.getParent().setRight(v);
        }
        if (v != null) {
            v.setParent(u.getParent());
        }
    }

    public void delete(int key) {
        BSTNode z = search(key);

        if (z == null)
            return;

        if (z.getPrev() != null) {
            z.getPrev().setNext(z.getNext());
        } else {
            this.begin = z.getNext();
        }

        if (z.getNext() != null) {
            z.getNext().setPrev(z.getPrev());
        } else {
            this.end = z.getPrev();
        }

        if (z.getLeft() == null) {
            transplant(z, z.getRight());
        } else if (z.getRight() == null) {
            transplant(z, z.getLeft());
        } else {
            BSTNode y = minimum(z.getRight());
            if (y != z.getRight()) {
                transplant(y, y.getRight());
                y.setRight(z.getRight());
                y.getRight().setParent(y);
            }
            transplant(z, y);
            y.setLeft(z.getLeft());
            y.getLeft().setParent(y);
        }
    }

    public BSTNode getBegin() {
        return begin;
    }

    public BSTNode getEnd() {
        return end;
    }
}
