import linkedset.LinkedSet;
import linkedset.LinkedSetNode;
import rbt.RBTNode;
import rbt.RedBlackTree;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        long[] res = experiment();
        long linkedListBuildTime = res[0];
        long rbtBuildTime = res[1];
        long linkedListIteration = res[2];
        long rbtIteration = res[3];

        System.out.println();
        System.out.println();

        System.out.println("LinkedSet Average Build Time : " + linkedListBuildTime);
        System.out.println("LinkedSet Iteration Time : " + linkedListIteration);
        System.out.println("RBTree Average Build Time : " + rbtBuildTime);
        System.out.println("RBTree Average Iteration Time : " + rbtIteration);
    }

    public static long[] experiment() {
        long startTime, endTime;
        LinkedSet linkedSet = new LinkedSet();
        RedBlackTree redBlackTree = new RedBlackTree();
        Random random = new Random();
        long linkedSetBuildTime = 0, rbtBuildTime = 0, linkedSetIteration = 0, rbtIteration = 0;

        // Experiment set-up
        Set<Integer> ll = new HashSet<>();
        for (int i = 0; i < 10_000_000; i++) {
            ll.add(random.nextInt());
        }
        System.out.println("Size of the Set is " + ll.size());

        // Red-Black Tree set-up
        startTime = System.currentTimeMillis();
        for (int l : ll) {
            redBlackTree.insert(l);
        }
        endTime = System.currentTimeMillis();

        rbtBuildTime = endTime - startTime;
        System.out.println("Red-Black Tree Insertion Time: " + rbtBuildTime + " milliseconds");


        // Red-Black Tree iteration
        startTime = System.currentTimeMillis();
        for (RBTNode itr2 = redBlackTree.getStart(); itr2 != redBlackTree.getTNULL(); itr2 = redBlackTree.successor(itr2)) {}
        endTime = System.currentTimeMillis();

        rbtIteration = endTime - startTime;
        System.out.println("Old Iteration time: " + rbtIteration + " milliseconds");


        // Linked Set set-up
        startTime = System.currentTimeMillis();
        for (int l : ll) {
            linkedSet.insert(l);
        }
        endTime = System.currentTimeMillis();

        linkedSetBuildTime = endTime - startTime;
        System.out.println("LinkedSet Insertion time: " + linkedSetBuildTime + " milliseconds");


        // LinkedSet iteration
        startTime = System.currentTimeMillis();
        for (LinkedSetNode itr1 = linkedSet.getStart(); itr1 != linkedSet.getTNULL(); itr1 = linkedSet.successor(itr1)) {}
        endTime = System.currentTimeMillis();

        linkedSetIteration = endTime - startTime;
        System.out.println("New Iteration time: " + linkedSetIteration + " milliseconds");

        return new long[]{linkedSetBuildTime, rbtBuildTime, linkedSetIteration, rbtIteration};
    }
}
