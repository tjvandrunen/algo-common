package test;

import impl.VacuousStack;


public class VSTest extends StackTest {

    protected void reset() {
        testStack = new VacuousStack<String>();
    }

	@Override
	protected void resetInt() {
		testStackInt = new VacuousStack<Integer>();
	}


}
