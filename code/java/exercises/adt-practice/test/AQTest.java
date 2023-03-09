package test;

import impl.ArrayQueue;

public class AQTest extends QueueTest {


    protected void reset() {
        testQueue = new ArrayQueue<String>(100);
    }

    protected void resetInt() {
        testQueueInt = new ArrayQueue<Integer>(100);
    }

}
