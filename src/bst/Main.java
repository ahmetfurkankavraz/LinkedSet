package bst;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        int[] insList = {10, 13, 11, 3, 2, 6, 4, 62, 253, 34, 26, 234, 63, 74, 457, 546, 4, 4, 4};
        int[] delList = {10, 74, 4, 2, 6};

        for (int l : insList) {
            bst.insert(l);
        }

        bst.inorder();
        for (int l : delList) {
            bst.delete(l);
            bst.inorder();
        }

        for (BSTNode tra = bst.getBegin(); tra != null; tra = tra.getNext()) {
            System.out.print(tra.getValue() + ", ");
        }
        System.out.println();

        for (BSTNode tra = bst.getEnd(); tra != null; tra = tra.getPrev()) {
            System.out.print(tra.getValue() + ", ");
        }
        System.out.println();
    }
}
