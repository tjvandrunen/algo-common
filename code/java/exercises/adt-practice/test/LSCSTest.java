package test;

import impl.ListSubclassStack;

public class LSCSTest extends StackTest {


    protected void reset() {
        testStack = new ListSubclassStack<String>();
    }

    protected void resetInt() {
        testStackInt = new ListSubclassStack<Integer>();
    }
}
