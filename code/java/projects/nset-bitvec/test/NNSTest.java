package test;

import impl.NaiveNSet;

public class NNSTest extends NSetTest {

    protected void reset() {
        testSet = new NaiveNSet(data.length);
    }

}
