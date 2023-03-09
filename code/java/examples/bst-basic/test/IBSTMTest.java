package test;

import impl.IterativeBSTMap;

public class IBSTMTest extends MapStressTest {

    protected void reset() {
        testMap = new IterativeBSTMap<String, String>();
    }

    protected void resetInteger() {
        testMapInt = new IterativeBSTMap<Integer, Integer>();
    }

}
