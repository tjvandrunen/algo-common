package test;

import impl.ListQueue;


public class LQTest extends QueueTest {

    protected void reset() {
        testQueue = new ListQueue<String>();
    }

    protected void resetInt() {
        testQueueInt = new ListQueue<Integer>();
    }
}
