package test;

import impl.RecursiveBSTMap;

public class RBSTMTest extends MapStressTest {

    protected void reset() {
        testMap = new RecursiveBSTMap<String, String>();
    }


    protected void resetInteger() {
        testMapInt = new RecursiveBSTMap<Integer, Integer>();
    } 

}
