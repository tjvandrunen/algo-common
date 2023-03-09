package bst2Array;

/**
 * BSTNode.java
 * 
 * Class to model a simplified binary search tree represented
 * by the root of that (sub)-tree. Actually, nothing in the
 * code forces it to be a BST; that is merely its purpose
 * in context.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * Feb 28, 2020
 */
public class BSTNode {

    public final int key;
    public final BSTNode left, right;

    public BSTNode(int key, BSTNode left, BSTNode right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

}
