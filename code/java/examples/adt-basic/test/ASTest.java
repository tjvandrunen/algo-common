package test;

import impl.ArrayStack;


public class ASTest extends StackTest {

    public static int cap = 100;
    
    protected void reset() {
        testStack = new ArrayStack<String>(cap);
    }

	protected void resetInt() {
		testStackInt = new ArrayStack<Integer>(cap);
	}


}
