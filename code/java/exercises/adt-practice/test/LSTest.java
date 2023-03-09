package test;


import impl.LinkedStack;

public class LSTest extends StackTest {

  
    protected void reset() {
        testStack = new LinkedStack<String>();
    }

    protected void resetInt() {
        testStackInt = new LinkedStack<Integer>();
    }

}
