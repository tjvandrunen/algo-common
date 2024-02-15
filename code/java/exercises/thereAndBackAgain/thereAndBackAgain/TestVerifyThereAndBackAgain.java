package thereAndBackAgain;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestVerifyThereAndBackAgain {

    @Test
    public void testTriangle() {
        AdjListGraph.ALGBuilder builder = new AdjListGraph.ALGBuilder(3);
        builder.connect(0, 1);
        builder.connect(1, 2);
        builder.connect(2, 0);
        assertTrue(VerifyThereAndBackAgain.verifyThereAndBackAgain(builder.getGraph()));
    }

    @Test
    public void testTriangleNeg() {
        AdjListGraph.ALGBuilder builder = new AdjListGraph.ALGBuilder(3);
        builder.connect(0, 1);
        builder.connect(1, 2);
        assertFalse(VerifyThereAndBackAgain.verifyThereAndBackAgain(builder.getGraph()));
    }

    @Test
    public void testComplete() {
        AdjListGraph.ALGBuilder builder = new AdjListGraph.ALGBuilder(3);
        builder.connect(0, 1);
        builder.connect(0, 2);
        builder.connect(1, 2);
        builder.connect(1, 0);
        builder.connect(2, 1);
        builder.connect(2, 0);
        assertTrue(VerifyThereAndBackAgain.verifyThereAndBackAgain(builder.getGraph()));
    }

    @Test
    public void testCompleteNeg() {
        AdjListGraph.ALGBuilder builder = new AdjListGraph.ALGBuilder(3);
        builder.connect(0, 1);
        builder.connect(0, 2);
        builder.connect(1, 2);
        builder.connect(1, 0);
        assertFalse(VerifyThereAndBackAgain.verifyThereAndBackAgain(builder.getGraph()));
    }

    @Test
    public void testClearingHouse() {
        AdjListGraph.ALGBuilder builder = new AdjListGraph.ALGBuilder(7);
        builder.connect(0, 1);
        builder.connect(0, 3);
        builder.connect(0, 5);
        builder.connect(1, 2);
        builder.connect(3, 4);
        builder.connect(5, 6);
        builder.connect(2, 0);
        builder.connect(4, 0);
        builder.connect(6, 0);
        assertTrue(VerifyThereAndBackAgain.verifyThereAndBackAgain(builder.getGraph()));
    }

    @Test
    public void testClearingHouseNeg() {
        AdjListGraph.ALGBuilder builder = new AdjListGraph.ALGBuilder(7);
        builder.connect(0, 1);
        builder.connect(0, 3);
        builder.connect(0, 5);
        builder.connect(1, 2);
        builder.connect(3, 4);
        builder.connect(5, 6);
        builder.connect(2, 0);
        builder.connect(4, 0);
        assertFalse(VerifyThereAndBackAgain.verifyThereAndBackAgain(builder.getGraph()));
    }

    @Test
    public void testShortcutHome() {
        AdjListGraph.ALGBuilder builder = new AdjListGraph.ALGBuilder(8);
        builder.connect(0, 1);
        builder.connect(0, 2);
        builder.connect(1, 3);
        builder.connect(2, 3);
        builder.connect(2, 4);
        builder.connect(3, 5);
        builder.connect(3, 6);
        builder.connect(4, 6);
        builder.connect(5, 7);
        builder.connect(6, 7);
        builder.connect(7, 0);
        assertTrue(VerifyThereAndBackAgain.verifyThereAndBackAgain(builder.getGraph()));

    }
    @Test
    public void testShortcutHomeNeg() {
        AdjListGraph.ALGBuilder builder = new AdjListGraph.ALGBuilder(8);
        builder.connect(0, 1);
        builder.connect(0, 2);
        builder.connect(1, 3);
        builder.connect(2, 3);
        builder.connect(2, 4);
        builder.connect(3, 6);
        builder.connect(4, 6);
        builder.connect(5, 7);
        builder.connect(6, 7);
        builder.connect(7, 0);
        assertFalse(VerifyThereAndBackAgain.verifyThereAndBackAgain(builder.getGraph()));

    }

    @Test
    public void testTwoCyclesNeg() {
        AdjListGraph.ALGBuilder builder = new AdjListGraph.ALGBuilder(4);
        builder.connect(0,1);
        builder.connect(1,0);
        builder.connect(2,3);
        builder.connect(3,2);
        assertFalse(VerifyThereAndBackAgain.verifyThereAndBackAgain(builder.getGraph()));
    }  


    
    
}
