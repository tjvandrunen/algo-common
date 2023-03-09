package test;

import impl.ListStackTopEnd;


public class LSTETest extends StackTest {

    protected void reset() {
        testStack = new ListStackTopEnd<String>();
    }

	@Override
	protected void resetInt() {
		testStackInt = new ListStackTopEnd<Integer>();
	}


}
