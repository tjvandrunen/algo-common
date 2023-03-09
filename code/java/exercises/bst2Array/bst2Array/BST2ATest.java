package bst2Array;

import static org.junit.Assert.*;

import org.junit.Test;

public class BST2ATest {

    
    public void runTest(BSTNode root, int[] testArray) {
        int[] result = BST2Array.bst2Array(root);
        assertEquals(testArray.length, result.length);
        for (int i = 0; i < testArray.length; i++)
            assertEquals(testArray[i], result[i]);
    }
    
    @Test
    public void testEmpty() {
        runTest(null, new int[0]);
    }
    
    @Test
    public void testLeaf() {
        runTest(new BSTNode(5, null, null), new int[]{5});
    }
    
    @Test
    public void testLopLeft() {
        runTest(new BSTNode(5, new BSTNode(4, null, null), null), new int[]{4, 5});
    }
    @Test
    public void testLopRight() {
        runTest(new BSTNode(5, null, new BSTNode(6, null, null)), new int[]{5, 6});
    }
    @Test
    public void testSmall() {
        runTest(new BSTNode(4,new BSTNode(2,new BSTNode(1, null, null),new BSTNode(3, null, null)),
                new BSTNode(6, new BSTNode(5, null, null), new BSTNode(7, null, null))),
                new int[]{1,2,3,4,5,6,7});
    }

}
