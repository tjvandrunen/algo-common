package test;

import impl.MapBag;

public class MBTest extends BagTest {

    protected void reset() {
        testBag = new MapBag<String>();
    }

	@Override
	protected void resetInt() {
		testBagInt = new MapBag<Integer>();
	}


}
