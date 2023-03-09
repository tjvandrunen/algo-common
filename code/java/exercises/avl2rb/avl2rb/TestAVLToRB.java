package avl2rb;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestAVLToRB {

    private boolean treesEqual(AVLNode avl, RBNode rb) {
        if (avl == null && rb == null) return true;
        else if (avl == null || rb == null) return false;
        else return avl.key.equals(rb.key) && 
                treesEqual(avl.left, rb.left) && treesEqual(avl.right, rb.right);
    }
    
    private boolean rbVerify(RBNode rb) {
        try {
            rbVerifyR(rb);
        } catch (BadRBTreeException ibhe) {
            return false;
        }
        return rb == null || ! rb.isRed();
    }
    
    private class BadRBTreeException extends Exception {
        private static final long serialVersionUID = 4044804956691920928L; 
    }
        
    private int rbVerifyR(RBNode rb) throws BadRBTreeException {
        if (rb == null) return 0;
        else {
            int lbh = rbVerifyR(rb.left),
                rbh = rbVerifyR(rb.right);
            if (lbh != rbh) throw new BadRBTreeException();
            else if (rb.isRed()) 
                if ((rb.left != null && rb.left.isRed()) ||
                    (rb.right != null && rb.right.isRed()))
                    throw new BadRBTreeException();
                else
                    return lbh;
            else
                return lbh + 1;
        }
    }
    
    private void testTree(String originalDesc) {
        AVLNode original = AVLNode.avlFactory(originalDesc);
        RBNode result = AVLToRB.avl2rb(original);
        System.out.println(result);
        assertTrue(treesEqual(original, result));
        assertTrue(rbVerify(result));
    }
    
   
    
    @Test
    public void testNull() {
        testTree(".");
    }
    
    @Test
    public void testTrivial() {
        testTree("(.A.)");
    }
    
    @Test
    public void testSmallBalanced() {
        testTree("((.A.)B(.C.))");
    }

    @Test
    public void testSmallLeft() {
        testTree("((.A.)B.)");
    }

    @Test
    public void testSmallRight() {
        testTree("(.A(.B.))");
    }

    @Test
    public void testSmallish() {
        testTree("((.A(.B.))C((.D.)E.))");
    }

    @Test
    public void testMediumA() {
        testTree("(((.A.)B.)C((.D.)C(.F.)))");
    }

    @Test
    public void testMediumB() {
        testTree("(((.A.)B((.C.)D.))E((.F.)G(.H.)))");
    }
    @Test
    public void testStumper() {
        testTree("(((.A.)B((.C.)D(.E.)))F((.G.)H(.I.)))");
    }
    @Test
    public void testAnotherStumper() {
        testTree("((((.A.)B(.C.))D(.E.))F((.G.)H(.I.)))");
    }
    @Test
    public void testBranchOut() {
        testTree("((((.A.)B.)C(((.D.)E.)F(.G.)))H((.I(.J.))K(.L.)))");
    }
}
