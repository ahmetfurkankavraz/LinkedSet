package linkedset;

// LinkedSet implementation as the changed version of Red-Black Tree implementation.

public class LinkedSet {
    private final LinkedSetNode TNULL;
    private LinkedSetNode root;


    public LinkedSet() {
        TNULL = new LinkedSetNode();
        TNULL.color = 0;
        TNULL.left = TNULL;
        TNULL.right = TNULL;
        TNULL.parent = TNULL;
        TNULL.prev = TNULL;
        TNULL.next = TNULL;
        root = TNULL;
    }

    // Preorder
    private void preOrderHelper(LinkedSetNode node) {
        if (node != TNULL) {
            System.out.print(node.data + " ");
            preOrderHelper(node.left);
            preOrderHelper(node.right);
        }
    }

    // Inorder
    private void inOrderHelper(LinkedSetNode node) {
        if (node != TNULL) {
            inOrderHelper(node.left);
            System.out.print(node.data + " ");
            inOrderHelper(node.right);
        }
    }

    // Post order
    private void postOrderHelper(LinkedSetNode node) {
        if (node != TNULL) {
            postOrderHelper(node.left);
            postOrderHelper(node.right);
            System.out.print(node.data + " ");
        }
    }

    // Search the tree
    private LinkedSetNode searchTreeHelper(LinkedSetNode node, int key) {
        if (node == TNULL || key == node.data) {
            return node;
        }

        if (key < node.data) {
            return searchTreeHelper(node.left, key);
        }
        return searchTreeHelper(node.right, key);
    }

    // Balance the tree after deletion of a node
    private void fixDelete(LinkedSetNode x) {
        LinkedSetNode s;
        while (x != root && x.color == 0) {
            if (x == x.parent.left) {
                s = x.parent.right;
                if (s.color == 1) {
                    s.color = 0;
                    x.parent.color = 1;
                    leftRotate(x.parent);
                    s = x.parent.right;
                }

                if (s.left.color == 0 && s.right.color == 0) {
                    s.color = 1;
                    x = x.parent;
                } else {
                    if (s.right.color == 0) {
                        s.left.color = 0;
                        s.color = 1;
                        rightRotate(s);
                        s = x.parent.right;
                    }

                    s.color = x.parent.color;
                    x.parent.color = 0;
                    s.right.color = 0;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                s = x.parent.left;
                if (s.color == 1) {
                    s.color = 0;
                    x.parent.color = 1;
                    rightRotate(x.parent);
                    s = x.parent.left;
                }

                if (s.left.color == 0 && s.right.color == 0) {
                    s.color = 1;
                    x = x.parent;
                } else {
                    if (s.left.color == 0) {
                        s.right.color = 0;
                        s.color = 1;
                        leftRotate(s);
                        s = x.parent.left;
                    }

                    s.color = x.parent.color;
                    x.parent.color = 0;
                    s.left.color = 0;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = 0;
    }

    private void rbTransplant(LinkedSetNode u, LinkedSetNode v) {
        if (u.parent == TNULL) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    private void deleteNodeHelper(LinkedSetNode node, int key) {
        LinkedSetNode z = TNULL;
        LinkedSetNode x, y;
        while (node != TNULL) {
            if (node.data == key) {
                z = node;
            }

            if (node.data <= key) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        if (z == TNULL) {
            System.out.println("Couldn't find key in the tree");
            return;
        }

        if (z.prev != TNULL) {
            z.prev.next = z.next;
        }
        if (z.next != TNULL) {
            z.next.prev = z.prev;
        }

        y = z;
        int yOriginalColor = y.color;
        if (z.left == TNULL) {
            x = z.right;
            rbTransplant(z, z.right);
        } else if (z.right == TNULL) {
            x = z.left;
            rbTransplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                rbTransplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            rbTransplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if (yOriginalColor == 0) {
            fixDelete(x);
        }
    }

    // Balance the node after insertion
    private void fixInsert(LinkedSetNode k) {
        LinkedSetNode u;
        while (k.parent.color == 1) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left;
                if (u.color == 1) {
                    u.color = 0;
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotate(k);
                    }
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    leftRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right;

                if (u.color == 1) {
                    u.color = 0;
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        leftRotate(k);
                    }
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root.color = 0;
    }

    private void printHelper(LinkedSetNode root, String indent, boolean last) {
        if (root != TNULL) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }

            String sColor = root.color == 1 ? "RED" : "BLACK";
            System.out.println(root.data + "(" + sColor + ")");
            printHelper(root.left, indent, false);
            printHelper(root.right, indent, true);
        }
    }

    public void preorder() {
        preOrderHelper(this.root);
    }

    public void inorder() {
        inOrderHelper(this.root);
    }

    public void postorder() {
        postOrderHelper(this.root);
    }

    public LinkedSetNode searchTree(int k) {
        return searchTreeHelper(this.root, k);
    }

    public LinkedSetNode minimum(LinkedSetNode node) {
        while (node.left != TNULL) {
            node = node.left;
        }
        return node;
    }

    public LinkedSetNode maximum(LinkedSetNode node) {
        while (node.right != TNULL) {
            node = node.right;
        }
        return node;
    }

    public LinkedSetNode successor(LinkedSetNode x) {
        return x.next;
    }

    public LinkedSetNode predecessor(LinkedSetNode x) {
        return x.prev;
    }

    public void leftRotate(LinkedSetNode x) {
        LinkedSetNode y = x.right;
        x.right = y.left;
        if (y.left != TNULL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == TNULL) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    public void rightRotate(LinkedSetNode x) {
        LinkedSetNode y = x.left;
        x.left = y.right;
        if (y.right != TNULL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == TNULL) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    public void insert(int key) {
        LinkedSetNode node = new LinkedSetNode();
        node.parent = TNULL;
        node.data = key;
        node.left = TNULL;
        node.right = TNULL;
        node.prev = TNULL;
        node.next = TNULL;
        node.color = 1;

        LinkedSetNode y = TNULL;
        LinkedSetNode x = this.root;

        while (x != TNULL) {
            y = x;
            if (node.data < x.data) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.parent = y;
        if (y == TNULL) {
            root = node;
        } else {
            if (y.data == node.data)
                return;
            else if (node.data < y.data) {
                y.left = node;

                node.next = y;
                node.prev = y.prev;
                if (y.prev != TNULL) {
                    y.prev.next = node;
                }
                y.prev = node;
            } else {
                y.right = node;

                node.next = y.next;
                node.prev = y;
                if (y.next != TNULL) {
                    y.next.prev = node;
                }
                y.next = node;
            }
        }

        if (node.parent == TNULL) {
            node.color = 0;
            return;
        }

        if (node.parent.parent == TNULL) {
            return;
        }

        fixInsert(node);
    }

    public LinkedSetNode getRoot() {
        return this.root;
    }

    public void deleteNode(int data) {
        deleteNodeHelper(this.root, data);
    }

    public void printTree() {
        printHelper(this.root, "", true);
    }

    public LinkedSetNode getStart() {
        return minimum(root);
    }

    public LinkedSetNode getEnd() {
        return maximum(root);
    }

    public LinkedSetNode getTNULL() {
        return TNULL;
    }
}