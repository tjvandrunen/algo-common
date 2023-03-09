package test;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

import adt.DisjointSet;

public abstract class DisjointSetTest {

    protected DisjointSet testDisjSet;
    
    protected abstract void reset(int size);

    protected void connectOne() {
        reset(10);
        testDisjSet.union(3, 8);
    }

    protected void connectSome() {
        reset(10);
        testDisjSet.union(0, 3);
        testDisjSet.union(9, 6);
        testDisjSet.union(1, 2);
        testDisjSet.union(4, 7);
        testDisjSet.union(4, 5);
        testDisjSet.union(6, 3);
    }

    protected void connectAll() {
        reset(10);
        testDisjSet.union(0,1);
        testDisjSet.union(2,3);
        testDisjSet.union(4,5);
        testDisjSet.union(6,7);
        testDisjSet.union(8,9);
        testDisjSet.union(1,8);
        testDisjSet.union(3,6);
        testDisjSet.union(5,8);
        testDisjSet.union(0,7);
    }
    
    @Test
    public void emptyFind() {
        reset(0);
        boolean caught = false;
        try {
            testDisjSet.find(0);
        } catch(NoSuchElementException nsee) {
            caught = true;
        }
        assertTrue(caught);
    }
    
    @Test
    public void emptyUnion() {
        reset(0);
        boolean caught = false;
        try {
            testDisjSet.union(1, 4);
        } catch(NoSuchElementException nsee) {
            caught = true;
        }
        assertTrue(caught);
    }
    
    @Test
    public void emptyConnected() {
        reset(0);
        boolean caught = false;
        try {
            testDisjSet.connected(3, 1);
        } catch(NoSuchElementException nsee) {
            caught = true;
        }
        assertTrue(caught);
    }

    @Test
    public void emptyCount() {
        reset(0);
        assertEquals(0, testDisjSet.count());
    }
    
    @Test
    public void emptyFindAll() {
        reset(0);
        boolean caught = false;
        try {
            testDisjSet.findAll(1);
        } catch(NoSuchElementException nsee) {
            caught = true;
        }
        assertTrue(caught);

    }
    
    @Test
    public void emptyIterator() {
        reset(0);
        int i = 0;
        for (Iterator<Integer> it = testDisjSet.iterator(); it.hasNext(); i++)
            it.next();
        assertEquals(0, i);
    }

    @Test
    public void smallInitialFind() {
        reset(10);
        for (int i = 0; i < 10; i++)
            assertEquals(i, testDisjSet.find(i));
    }

    @Test
    public void smallInitialConnected() {
        reset(10);
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                if (i == j) 
                    assertTrue(testDisjSet.connected(i, j));
                else
                    assertFalse(testDisjSet.connected(i, j));
    }

    @Test
    public void smallInitialCount() {
        reset(10);
        assertEquals(10, testDisjSet.count());
    }

    @Test
    public void smallInitialFindAll() {
        reset(10);
        for (int i = 0; i < 10; i++) {
            int j = 0;
            for (Integer x : testDisjSet.findAll(i)) {
                assertEquals(i, x.intValue()); // auto-boxing fails :(
                j++;
            }
            assertEquals(1, j);
        }
    }

    @Test
    public void smallInitialIterator() {
        reset(10);
        boolean[] found = new boolean[10];
        for (Integer x : testDisjSet) {
            assertFalse(found[x]);
            found[x] = true;
        }
        for (boolean foundIt : found)
            assertTrue(foundIt);
    }
    
    @Test
    public void smallOneFind() {
        reset(10);
        testDisjSet.union(1, 6);
        for (int i = 0; i < 10; i++)
            if (i != 1 && i != 6)
                assertEquals(i, testDisjSet.find(i));
        assertEquals(testDisjSet.find(1), testDisjSet.find(6));
    }
    
    @Test
    public void smallOneConnected() {
        reset(10);
        testDisjSet.union(1, 6);
        for (int i = 0; i < 10; i++)
            if (i != 1 && i != 6) {
                assertFalse(testDisjSet.connected(i, 1));
                assertFalse(testDisjSet.connected(1, i));
                assertFalse(testDisjSet.connected(i, 6));
                assertFalse(testDisjSet.connected(6, i));
            }
        assertTrue(testDisjSet.connected(1, 6));
        assertTrue(testDisjSet.connected(6, 1));
    }
    
    @Test
    public void smallOneCount() {
        reset(10);
        testDisjSet.union(1, 6);
        assertEquals(9, testDisjSet.count());
    }

    @Test
    public void smallOneFindAll() {
        reset(10);
        testDisjSet.union(1, 6);
        for (int i = 0; i < 10; i++) {
            if (i != 1 && i != 6) {
                int j = 0;
                for (Integer x : testDisjSet.findAll(i)) {
                    assertEquals(i, x.intValue()); // auto-boxing fails :(
                    j++;
                }
                assertEquals(1, j);
            }
        }
        int j = 0;
        for (Integer x : testDisjSet.findAll(1)) {
            assertTrue(x.intValue() == 1 || x.intValue() == 6); // auto-boxing fails :(
            j++;
        }
        assertEquals(2, j);
        j = 0;
        for (Integer x : testDisjSet.findAll(6)) {
            assertTrue(x.intValue() == 1 || x.intValue() == 6); // auto-boxing fails :(
            j++;
        }
    }

    @Test
    public void smallSomeFind() {
        connectSome();
        // spot check
        assertEquals(testDisjSet.find(0), testDisjSet.find(3));
        assertEquals(testDisjSet.find(0), testDisjSet.find(9));
        assertEquals(testDisjSet.find(6), testDisjSet.find(0));
        assertEquals(testDisjSet.find(1), testDisjSet.find(2));
        assertEquals(testDisjSet.find(5), testDisjSet.find(4));
        assertEquals(testDisjSet.find(5), testDisjSet.find(7));

        assertNotEquals(testDisjSet.find(0), testDisjSet.find(1));
        assertNotEquals(testDisjSet.find(5), testDisjSet.find(9));
        assertNotEquals(testDisjSet.find(8), testDisjSet.find(3));
        
    }

    @Test
    public void smallSomeConnected() {
        connectSome();
        assertTrue(testDisjSet.connected(3,9));
        assertTrue(testDisjSet.connected(2, 1));
        assertTrue(testDisjSet.connected(4, 7));

        assertFalse(testDisjSet.connected(3, 4));
        assertFalse(testDisjSet.connected(9, 2));
        assertFalse(testDisjSet.connected(7, 8));
    }
    
    @Test
    public void smallSomeCount() {
        connectSome();
        assertEquals(4, testDisjSet.count());
    }

    @Test
    public void smallSomeFindAll() {
        connectSome();
        boolean[] found = new boolean[10];
        for (Integer x: testDisjSet.findAll(3))
            found[x] = true;
        for (int i = 0; i < 10; i++)
            if (i % 3 == 0)
                assertTrue(found[i]);
            else
                assertFalse(found[i]);
        
    }
    
    @Test
    public void smallSomeIterator() {
        connectSome();
        int i = 0;
        for (Iterator<Integer> it=testDisjSet.iterator(); it.hasNext();it.next())
            i++;
        assertEquals(4, i);
    }
    
    @Test
    public void smallAllFind() {
        connectAll();
        int leader = testDisjSet.find(0);
        for (int i = 1; i < 10; i++)
            assertEquals(leader, testDisjSet.find(i));
    }

    @Test
    public void smallAllConnected() {
        connectAll();
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                assertTrue(testDisjSet.connected(i, j));
    }
    
    @Test
    public void smallAllCount() {
        connectAll();
        assertEquals(1, testDisjSet.count());
    }

    @Test
    public void smallAllFindAll() {
        connectAll();
        for (int i = 0; i < 10; i++) {
            boolean[] found = new boolean[10];
            for (Integer x : testDisjSet.findAll(i)) {
                assertFalse(found[x]);
                found[x] = true;
            }
            for (boolean foundIt : found)
                assertTrue(foundIt);
        }
    }

    @Test
    public void smallAllIterator() {
        connectAll();
        int j = 0;
        for (Integer x : testDisjSet) {
            boolean[] found = new boolean[10];
            for (Integer y : testDisjSet.findAll(x)) {
                assertFalse(found[y]);
                found[y] = true;
            }
            j++;
        }
        assertEquals(1, j);
    }
}
