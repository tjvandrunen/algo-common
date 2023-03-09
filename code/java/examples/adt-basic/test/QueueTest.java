package test;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

import adt.FullContainerException;
import adt.Queue;

public abstract class QueueTest extends CollectionTest {
	protected Queue<String> testQueue;
	protected Queue<Integer> testQueueInt;

	protected abstract void reset();

	protected abstract void resetInt();

	protected void populate(int size) {
		for (int i = 0; i < size && i < getData().length; i++)
			testQueue.enqueue(getData()[i]);
	}

	@Test
	public void initialEmpty() {
		reset();
		assertTrue(testQueue.isEmpty());
	}

	@Test
	public void initialFrontRemove() {
		reset();
		boolean caught = false;
		try {
			testQueue.remove();
		} catch (NoSuchElementException nsee) {
			caught = true;
		}
		assertTrue(caught);
		caught = false;
		try {
			testQueue.front();
		} catch (NoSuchElementException nsee) {
			caught = true;
		}
		assertTrue(caught);
	}

	@Test
	public void plainEnqueue() {
		reset();
		try {
			populate(6);
		} catch (FullContainerException fce) {
		}
	}

	@Test
	public void enqueueEmpty() {
		reset();
		try {
			populate(1);
			assertFalse(testQueue.isEmpty());
		} catch (FullContainerException fce) {
			assertTrue(testQueue.isEmpty());
		}
	}

	@Test
	public void enqueueFront() {
		reset();
		try {
			populate(1);
			assertEquals(getData()[0], testQueue.front());
		} catch (FullContainerException fce) {
		}
	}

	@Test
	public void enqueuesfront() {
		reset();
		try {
			populate(8);
			assertEquals(getData()[0], testQueue.front());
		} catch (FullContainerException fce) {
		}
	}

	@Test
	public void enqueueNonEmpty() {
		reset();
		try {
			populate(1);
			assertFalse(testQueue.isEmpty());
		} catch (FullContainerException fce) {
			assertTrue(testQueue.isEmpty());
		}

	}

	@Test
	public void enqueuesNonEmpty() {
		reset();
		try {
			populate(1);
			try {
				populate(8);
			} catch (FullContainerException fce) {
			}
			assertFalse(testQueue.isEmpty());

		} catch (FullContainerException fce) {
			assertTrue(testQueue.isEmpty());
		}
	}

	@Test
	public void enqueueFrontFront() {
		reset();
		try {
			populate(1);
			testQueue.front();
			assertEquals(getData()[0], testQueue.front());
		} catch (FullContainerException fce) {
		}
	}

	@Test
	public void enqueuesFrontFront() {
		reset();
		try {
			populate(8);
			testQueue.front();
			assertEquals(getData()[0], testQueue.front());
		} catch (FullContainerException fce) {
		}

	}

	@Test
	public void enqueueRemove() {
		reset();
		try {
			populate(1);
			assertEquals(getData()[0], testQueue.remove());
			assertTrue(testQueue.isEmpty());
			boolean caught = false;
			try {
				testQueue.front();
			} catch (NoSuchElementException nsee) {
				caught = true;
			}
			assertTrue(caught);
			caught = false;
			try {
				testQueue.remove();
			} catch (NoSuchElementException nsee) {
				caught = true;
			}
		} catch (FullContainerException fce) {
		}
	}

	@Test
	public void enqueuesRemove() {
		reset();
		try {
			populate(8);
			assertEquals(getData()[0], testQueue.remove());
			assertFalse(testQueue.isEmpty());
			assertEquals(getData()[1], testQueue.front());
		} catch (FullContainerException fce) {
		}
	}

	@Test
	public void enqueuesRemoves() {
		reset();
		try {
			populate(8);
			for (int i = 0; i < 8; i++) {
				assertFalse(testQueue.isEmpty());
				assertEquals(getData()[i], testQueue.front());
				assertEquals(getData()[i], testQueue.remove());
			}
			assertTrue(testQueue.isEmpty());
			boolean caught = false;
			try {
				testQueue.front();
			} catch (NoSuchElementException nsee) {
				caught = true;
			}
			assertTrue(caught);
			caught = false;
			try {
				testQueue.remove();
			} catch (NoSuchElementException nsee) {
				caught = true;
			}
			assertTrue(caught);
		} catch (FullContainerException fce) {
		}

	}

	@Test
	public void enqueuesRemovesEnqueuesRemoves() {
		reset();
		try {
			populate(6);
			for (int i = 0; i < 2; i++) {
				assertFalse(testQueue.isEmpty());
				assertEquals(getData()[i], testQueue.front());
				assertEquals(getData()[i], testQueue.remove());
			}
			assertFalse(testQueue.isEmpty());
			for (int i = 6; i < 10; i++)
				testQueue.enqueue(getData()[i]);
			for (int i = 2; i < 10; i++) {
				assertFalse(testQueue.isEmpty());
				assertEquals(getData()[i], testQueue.front());
				assertEquals(getData()[i], testQueue.remove());
			}
			assertTrue(testQueue.isEmpty());
			boolean caught = false;
			try {
				testQueue.front();
			} catch (NoSuchElementException nsee) {
				caught = true;
			}
			assertTrue(caught);
			caught = false;
			try {
				testQueue.remove();
			} catch (NoSuchElementException nsee) {
				caught = true;
			}
			assertTrue(caught);

		} catch (FullContainerException fce) {
		}
	}

	@Test
	public void stressTest() {
		resetInt();
		final int NUM_TESTS = 10;
		int maxSize = 1000;
		boolean foundSize = false;
		for (int test = 0; test < NUM_TESTS; test++) {
			int i = 0;
			try {
				assertTrue(testQueueInt.isEmpty());
				int n = rand.nextInt(maxSize);
				for ( i = 0; i < n; i++) {
					testQueueInt.enqueue(new Integer(i));
					assertFalse(testQueueInt.isEmpty());
				}
				for (int j = 0; j < n; j++) {
					assertEquals(new Integer(j), testQueueInt.front());
					assertEquals(new Integer(j), testQueueInt.remove());
				}
				assertTrue(testQueueInt.isEmpty());
			} catch (FullContainerException fce) {
				assertFalse(foundSize);
				maxSize = i;
				foundSize = true;				
				for (int j = 0; j < i; j++) {
					assertEquals(new Integer(j), testQueueInt.front());
					assertEquals(new Integer(j), testQueueInt.remove());
				}
			}
		}

	}
}
