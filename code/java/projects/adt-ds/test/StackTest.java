package test;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

import adt.FullContainerException;
import adt.Stack;

public abstract class StackTest extends CollectionTest {
    
    protected Stack<String> testStack;
    protected Stack<Integer> testStackInt;
    
    protected abstract void reset();
    protected abstract void resetInt();

    protected void populate(int size) {
        for (int i = 0; i < size && i < getData().length; i++)
            testStack.push(getData()[i]);
    }

    @Test
    public void initialEmpty() {
        reset();
        assertTrue(testStack.isEmpty());
    }
    
    @Test
    public void initialTopPop() {
        reset();
        boolean caught = false;
        try {
            testStack.pop();
        } catch(NoSuchElementException nsee) {
            caught = true;
        }
        assertTrue(caught);
        caught = false;
        try {
            testStack.top();
        } catch(NoSuchElementException nsee) {
            caught = true;
        }
        assertTrue(caught);
    }
    
    @Test
    public void plainPush() {
        reset();
        try {
            populate(6);
        } catch(FullContainerException fce) {
        }
    }

    @Test
    public void pushEmpty() {
        reset();
        try { 
            populate(1);
            assertFalse(testStack.isEmpty());
        } catch(FullContainerException fce) {
            assertTrue(testStack.isEmpty());
        }
        
    }
    
    @Test
    public void pushTop() {
        reset();
        try {
            populate(1);
            assertEquals(getData()[0], testStack.top());
        } catch(FullContainerException fce) {
        }
    }

    @Test
    public void pushesTop() {
        reset();
        try {
            populate(8);
            assertEquals(getData()[7], testStack.top());
        } catch(FullContainerException fce) {
        }
    }

    @Test
    public void pushesTop2() {
        reset();
        try {
            populate(5);
            assertEquals(getData()[4], testStack.top());
        } catch(FullContainerException fce) {
        }
    }

    @Test
    public void pushNonEmpty() {
        reset();
        try {
            populate(1);
            assertFalse(testStack.isEmpty());
        } catch(FullContainerException fce) {
            assertTrue(testStack.isEmpty());
        }

    }

    @Test
    public void pushesNonEmpty() {
        reset();
        try {
            populate(1);
            try {
                populate(8);
            } catch (FullContainerException fce) {
            }
            assertFalse(testStack.isEmpty());
        
        } catch(FullContainerException fce) {
            assertTrue(testStack.isEmpty());
        }
    }

    @Test
    public void pushTopTop() {
        reset();
        try {
            populate(1);
            testStack.top();
            assertEquals(getData()[0], testStack.top());
        }catch(FullContainerException fce) {
        }
    }
    
    @Test
    public void pushesTopTop() {
        reset();
        try {
            populate(8);
            testStack.top();
            assertEquals(getData()[7], testStack.top());
        }catch(FullContainerException fce) {
        }
        
    }
    
    @Test
    public void pushPop() {
        reset();
        try {
            populate(1);
            assertEquals(getData()[0], testStack.pop());
            assertTrue(testStack.isEmpty());
            boolean caught = false;
            try {
                testStack.top();
            } catch (NoSuchElementException nsee) {
                caught = true;
            }
            assertTrue(caught);
            caught = false;
            try {
                testStack.pop();
            } catch (NoSuchElementException nsee) {
                caught = true;
            }
        } catch(FullContainerException fce) {
        }
    }
    
    @Test
    public void pushesPop() {
        reset();
        try {
            populate(8);
            assertEquals(getData()[7], testStack.pop());
            assertFalse(testStack.isEmpty());
            assertEquals(getData()[6], testStack.top());
        } catch(FullContainerException fce) {
        }
    }

    @Test
    public void pushesPops() {
        reset();
        try {
            populate(8);
            for (int i = 7; i >= 0; i--) {
                assertFalse(testStack.isEmpty());
                assertEquals(getData()[i], testStack.top());
                assertEquals(getData()[i], testStack.pop());
            }
            assertTrue(testStack.isEmpty());
            boolean caught = false;
            try {
                testStack.top();
            } catch (NoSuchElementException nsee) {
                caught = true;
            }
            assertTrue(caught);
            caught = false;
            try {
                testStack.pop();
            } catch (NoSuchElementException nsee) {
                caught = true;
            }
            assertTrue(caught);
        } catch(FullContainerException fce) {
        }
        
    }
    
    @Test
    public void pushesPopsPushesPops() {
        reset();
        try {
            populate(6);
            for (int i = 5; i > 3; i--) {
                assertFalse(testStack.isEmpty());
                assertEquals(getData()[i], testStack.top());
                assertEquals(getData()[i], testStack.pop());
            }
            assertFalse(testStack.isEmpty());
            for (int i = 6; i < 10; i++)
                testStack.push(getData()[i]);
            for (int i = 9; i > 5; i--) {
                assertFalse(testStack.isEmpty());
                assertEquals(getData()[i], testStack.top());
                assertEquals(getData()[i], testStack.pop());
            }
            for (int i = 3; i >= 0; i--) {
                assertFalse(testStack.isEmpty());
                assertEquals(getData()[i], testStack.top());
                assertEquals(getData()[i], testStack.pop());
            }
            assertTrue(testStack.isEmpty());
            boolean caught = false;
            try {
                testStack.top();
            } catch (NoSuchElementException nsee) {
                caught = true;
            }
            assertTrue(caught);
            caught = false;
            try {
                testStack.pop();
            } catch (NoSuchElementException nsee) {
                caught = true;
            }
            assertTrue(caught);
            
        }catch(FullContainerException fce) {
        }
    }
    @Test 
    public void stressTest() {
    	resetInt();
    	final int NUM_TESTS = 10;
    	int maxsize = 1000;
    	boolean foundSize = false;
    	for (int test = 0; test < NUM_TESTS; test++) {
    		int i = 0;
    		try {
    		int n = rand.nextInt(maxsize);
    		assertTrue(testStackInt.isEmpty());
    		for (i = 0; i < n; i++) {
    			testStackInt.push(new Integer(i));
    			assertFalse(testStackInt.isEmpty());
    		}
    		for (int j = n-1; j >= 0; j--) {
    			assertFalse(testStackInt.isEmpty());
    			assertEquals(new Integer(j), testStackInt.top());
    			assertEquals(new Integer(j), testStackInt.pop());
    		}
				assertTrue(testStackInt.isEmpty());
			} catch (FullContainerException fce) {
				assertFalse(foundSize);
				maxsize = i;
				foundSize = true;
		   		for (int j = i-1; j >= 0; j--) {
	    			assertFalse(testStackInt.isEmpty());
	    			assertEquals(new Integer(j), testStackInt.top());
	    			assertEquals(new Integer(j), testStackInt.pop());
	    		}
			}
    	}
    }
}
