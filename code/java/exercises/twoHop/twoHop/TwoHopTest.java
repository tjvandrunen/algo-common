package twoHop;

import static org.junit.Assert.*;

import org.junit.Test;

public class TwoHopTest {

    @Test
    public void testA() {
        AdjListGraph.ALGBuilder builder = new AdjListGraph.ALGBuilder(10);
        builder.connect(1, 2);
        builder.connect(1, 3);
        builder.connect(1, 4);
        builder.connect(2, 5);
        builder.connect(2, 6);
        builder.connect(3, 6);
        builder.connect(4, 7);
        builder.connect(4, 8);
        builder.connect(4, 9);
        AdjListGraph g = builder.getGraph();
        boolean[] result = TwoHop.findTwoHop(g, 1);
        assertEquals(10, result.length);
        for (int i = 0; i < 10; i++)
            if (i == 5 || i == 6 || i == 7 ||i == 8 || i == 9)
                assertTrue(result[i]);
            else
                assertFalse(result[i]);
    }
    
    @Test
    public void testB() {
        AdjListGraph.ALGBuilder builder = new AdjListGraph.ALGBuilder(10);
        builder.connect(1, 2);
        builder.connect(1, 3);
        builder.connect(2, 4);
        builder.connect(2, 3);
        AdjListGraph g = builder.getGraph();
        boolean[] result = TwoHop.findTwoHop(g, 1);
        assertEquals(10, result.length);
        for (int i = 0; i < 10; i++)
            if (i == 4)
                assertTrue(result[i]);
            else
                assertFalse(result[i]);
    }
    

}
