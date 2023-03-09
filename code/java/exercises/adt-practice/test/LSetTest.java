package test;

import impl.ListSet;

public class LSetTest extends SetTest {

    protected void reset() {
        testSet = new ListSet<String>();
    }

	@Override
	protected void resetInt() {
		testSetInt = new ListSet<Integer>();
	}

 
}
