package test;


import impl.RecursiveLinkedList;

public class RLLTest extends ListTest {

    protected void reset() {
        testList = new RecursiveLinkedList<String>();
    }

    protected void resetInt() {
        testListInt = new RecursiveLinkedList<Integer>();
        
    }

}
