package test;

import impl.WimpList;

public class WLTest extends ListTest {


    protected void reset() {
        testList = new WimpList<String>();
    }

	@Override
	protected void resetInt() {
		testListInt = new WimpList<Integer>();
	}

}
