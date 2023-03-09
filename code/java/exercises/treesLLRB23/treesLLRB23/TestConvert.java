package treesLLRB23;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestConvert {

    @Test
    public void testLlrb2ttSimple() {
        assertEquals(new TwoThreeNode(null, "A", null, "B", null),
                Convert.llrb2tt(new RBNode(new RBNode(null, "A", null, true), "B", null, false)));
    }

    @Test
    public void testTt2llrbSimple() {
        assertEquals(new RBNode(new RBNode(null, "A", null, true), "B", null, false),
                Convert.tt2llrb(new TwoThreeNode(null, "A", null, "B", null)));
        
    }
}
