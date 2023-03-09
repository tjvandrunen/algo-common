package test;


import impl.LinkedList;

public class LLTest extends ListTest {

    protected void reset() {
        testList = new LinkedList<String>();
    }

	@Override
	protected void resetInt() {
		testListInt = new LinkedList<Integer>();
	}
}
