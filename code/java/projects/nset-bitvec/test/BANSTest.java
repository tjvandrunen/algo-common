package test;

import impl.BArrayNSet;

public class BANSTest extends NSetTest {

    protected void reset() {
        testSet = new BArrayNSet(data.length);
    }

}
