package test;

import impl.ListMap;


public class LMTest extends MapStressTest {

    protected void reset() {
        testMap = new ListMap<String, String>();
    }

	@Override
	protected void resetInteger() {
		testMapInt = new ListMap<Integer, Integer>();
	}

}
