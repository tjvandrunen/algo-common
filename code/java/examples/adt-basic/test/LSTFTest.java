package test;

import impl.ListStackTopFront;

public class LSTFTest extends StackTest {

    protected void reset() {
        testStack = new ListStackTopFront<String>();
    }

	@Override
	protected void resetInt() {
		testStackInt = new ListStackTopFront<Integer>();
	}



}
