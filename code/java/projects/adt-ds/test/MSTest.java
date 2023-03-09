package test;



import impl.MapSet;

public class MSTest extends SetTest {

    protected void reset() {
        testSet = new MapSet<String>();
    }
    protected void resetInt() {
        testSetInt = new MapSet<Integer>();
    }
    
    
}
