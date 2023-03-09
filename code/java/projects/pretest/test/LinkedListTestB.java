package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

public class LinkedListTestB extends LinkedListTestA {
    @Test 
    public void replaceFront() {
        reset();
        populate(8);
        testList.set(0, data[8]);
        assertEquals(8, testList.size());
        assertEquals(data[8], testList.get(0));
        for (int i = 1; i < 8; i++)
            assertEquals(data[i], testList.get(i));
        boolean caught = false;
        try {
            testList.get(-1);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught);
        caught = false;
        try {
            testList.get(12);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught); 
        int i = 0;
        for (Iterator<String> it = testList.iterator(); it.hasNext(); ) {
            if (i == 0)
                assertEquals(data[8], it.next());
            else 
                assertEquals(data[i], it.next());
            i++;
        }
        assertEquals(8, i);
    }

    @Test 
    public void replaceMid() {
        reset();
        populate(8);
        testList.set(5, data[8]);
        assertEquals(8, testList.size());
        assertEquals(data[8], testList.get(5));
        for (int i = 1; i < 8; i++)
            if (i != 5)
                assertEquals(data[i], testList.get(i));
        boolean caught = false;
        try {
            testList.get(-1);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught);
        caught = false;
        try {
            testList.get(12);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught); 
        int i = 0;
        for (Iterator<String> it = testList.iterator(); it.hasNext(); ) {
            if (i == 5)
                assertEquals(data[8], it.next());
            else 
                assertEquals(data[i], it.next());
            i++;
        }
        assertEquals(8, i);
    }

    @Test 
    public void replaceEnd() {
        reset();
        populate(8);
        testList.set(7, data[8]);
        assertEquals(8, testList.size());
        assertEquals(data[8], testList.get(7));
        for (int i = 1; i < 8; i++)
            if (i != 7)
                assertEquals(data[i], testList.get(i));
        boolean caught = false;
        try {
            testList.get(-1);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught);
        caught = false;
        try {
            testList.get(12);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught); 
        int i = 0;
        for (Iterator<String> it = testList.iterator(); it.hasNext(); ) {
            if (i == 7)
                assertEquals(data[8], it.next());
            else 
                assertEquals(data[i], it.next());
            i++;
        }
        assertEquals(8, i);
    }

    @Test
    public void replaceNeg() {
        reset();
        populate(8);
        boolean caught = false;
        try {
            testList.set(-1, data[8]);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught); 
    }

    @Test
    public void replacePosOut() {
        reset();
        populate(8);
        boolean caught = false;
        try {
            testList.set(12, data[8]);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught); 
        
    }
    
    @Test
    public void oneReplacePos() {
        reset();
        populate(1);
        boolean caught = false;
        try {
            testList.set(8, data[8]);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertEquals(data[0], testList.get(0));
        assertTrue(caught);
    }

}
