package thereAndBackAgain;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestVerifyThereAndBackAgain {

    @Test
    public void testSimplePositive() {
        AdjListGraph.ALGBuilder builder = new AdjListGraph.ALGBuilder(3);
        builder.connect(0, 1);
        builder.connect(1, 2);
        builder.connect(2, 0);
        assertTrue(VerifyThereAndBackAgain.verifyThereAndBackAgain(builder.getGraph()));
    }

    @Test
    public void testSimpleNegative() {
        AdjListGraph.ALGBuilder builder = new AdjListGraph.ALGBuilder(3);
        builder.connect(0, 1);
        builder.connect(1, 2);
        builder.connect(0, 2);
        assertFalse(VerifyThereAndBackAgain.verifyThereAndBackAgain(builder.getGraph()));
    }

    
    
}
