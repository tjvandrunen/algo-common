package test;

import impl.DummyNodeLinkedList;

public class DNLLTest extends ListTest {

    protected void reset() {
        testList = new DummyNodeLinkedList<String>();
    }

    protected void resetInt() {
        testListInt = new DummyNodeLinkedList<Integer>();
    }

}
