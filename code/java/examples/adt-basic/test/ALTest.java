package test;


import impl.ArrayList;

public class ALTest extends ListTest {

    protected void reset() {
        testList = new ArrayList<String>();
    }

	protected void resetInt() {
		testListInt = new ArrayList<Integer>();
	}

}
