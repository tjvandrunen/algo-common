package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import adt.FullContainerException;
import impl.PQQueue;

public class PQQTest extends QueueTest {

    protected void reset() {
        testQueue = new PQQueue<String>(20);
    }
	@Override
	protected void resetInt() {
		testQueueInt = new PQQueue<Integer>(20);
		
	}
    @Test
    public void testFull() {
        reset();
        boolean caught = false;
        populate(getData().length);
        testQueue.enqueue("Severus");
        testQueue.enqueue("Caracalla");
        testQueue.enqueue("Macrinus");
        try {
            testQueue.enqueue("Elagabalus");
        } catch (FullContainerException fce) {
            caught = true;
        }
        assertTrue(caught);
    }


}
