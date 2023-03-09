package test;

import impl.ListSubclassQueue;

public class LSCQTest extends QueueTest {

    protected void reset() {
        testQueue = new ListSubclassQueue<String>();
    }

    protected void resetInt() {
        testQueueInt = new ListSubclassQueue<Integer>();
    }
}
