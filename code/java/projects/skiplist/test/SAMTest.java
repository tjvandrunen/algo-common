package test;

import impl.SortedArrayMap;

public class SAMTest extends MapStressTest {

    protected void reset() {
        testMap = new SortedArrayMap<String, String>();
    }

    protected void resetInteger() {
        testMapInt = new SortedArrayMap<Integer, Integer>();
    }

}
