package test;

import impl.BagSet;

public class BSTest extends SetTest {

    protected void reset() {
        testSet = new BagSet<String>();
    }

	@Override
	protected void resetInt() {
		testSetInt = new BagSet<Integer>();
		
	}

    
    
}
