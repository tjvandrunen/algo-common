package test;

import impl.BackwardsLinkedList;

public class BLLTest extends ListTest {


    protected void reset() {
        testList = new BackwardsLinkedList<String>();
    }

	@Override
	protected void resetInt() {
		testListInt = new BackwardsLinkedList<Integer>();
	}


}
