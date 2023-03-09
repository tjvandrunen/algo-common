package test;

import impl.PQStack;

public class PQSTest extends StackTest {

    protected void reset() {
        testStack = new PQStack<String>(20);
    }

	@Override
	protected void resetInt() {
		testStackInt = new PQStack<Integer>(20);
	}

}
