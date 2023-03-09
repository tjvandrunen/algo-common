package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTestC  extends LinkedListTestB {
    


    @Test
    public void removeFirst() {
        reset();
        populate(8);
        testList.remove(0);
        assertEquals(7, testList.size());
        for (int i = 0; i < 7; i++)
            assertEquals(data[i+1], testList.get(i));
        int i = 1;
        for (String s : testList)
            assertEquals(data[i++], s);
        boolean caught = false;
        try {
            testList.get(7);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught); 
    }

    @Test
    public void removeMid() {
        reset();
        populate(8);
        testList.remove(4);
        assertEquals(7, testList.size());
        for (int i = 0; i < 7; i++)
            if (i < 4)
                assertEquals(data[i], testList.get(i));
            else
                assertEquals(data[i+1], testList.get(i));
        int i = 0;
        for (String s : testList) {
            assertEquals(data[i++], s);
            if (i == 4) i++;
        }
        boolean caught = false;
        try {
            testList.get(7);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught); 
    }
    
    @Test
    public void removeLast() {
        reset();
        populate(8);
        testList.remove(7);
        assertEquals(7, testList.size());
        for (int i = 0; i < 7; i++)
            assertEquals(data[i], testList.get(i));
        int i = 0;
        for (String s : testList) {
            assertEquals(data[i++], s);
        }
        boolean caught = false;
        try {
            testList.get(7);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught); 
    }

    @Test
    public void removeAll() {
        reset();
        populate(8);
        for (int i = 0; i < 8; i++) {
            assertEquals(8 - i, testList.size());
            testList.remove(randy.nextInt(testList.size()));
        }
        assertEquals(0, testList.size());
    }

    @Test
    public void removeFirstAdd() {
        reset();
        populate(8);
        testList.remove(0);
        testList.add(data[8]);
        assertEquals(8, testList.size());
        for (int i = 0; i < 8; i++)
            assertEquals(data[i+1], testList.get(i));
        int i = 1;
        for (String s : testList)
            assertEquals(data[i++], s);
        boolean caught = false;
        try {
            testList.get(8);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught); 
    }
    
    @Test
    public void removeMidAdd() {
        reset();
        populate(8);
        testList.remove(4);
        testList.add(data[8]);
        assertEquals(8, testList.size());
        for (int i = 0; i < 8; i++)
            if (i < 4)
                assertEquals(data[i], testList.get(i));
            else
                assertEquals(data[i+1], testList.get(i));
        int i = 0;
        for (String s : testList) {
            assertEquals(data[i++], s);
            if (i == 4) i++;
        }
        boolean caught = false;
        try {
            testList.get(8);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught); 
    }
    
    @Test
    public void removeLastAdd() {
        reset();
        populate(8);
        testList.remove(7);
        testList.add(data[8]);
        assertEquals(8, testList.size());
        for (int i = 0; i < 8; i++)
            if (i < 7)
                assertEquals(data[i], testList.get(i));
            else 
                assertEquals(data[i+1], testList.get(i));
        int i = 0;
        for (String s : testList) {
            assertEquals(data[i++], s);
            if (i == 7) i++;
        }
        boolean caught = false;
        try {
            testList.get(8);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught); 
    }

    @Test
    public void removeAllAdd() {
        reset();
        populate(8);
        for (int i = 0; i < 8; i++) {
            assertEquals(8 - i, testList.size());
            testList.remove(randy.nextInt(testList.size()));
        }
        assertEquals(0, testList.size());
        testList.add(data[8]);
        assertEquals(1, testList.size());
        assertEquals(data[8], testList.get(0));
    }

    
    @Test
    public void removeNeg() {
        reset();
        populate(8);
        boolean caught = false;
        try {
            testList.remove(-2);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught); 
    }

    @Test
    public void removeTooBig() {
        reset();
        populate(8);
        boolean caught = false;
        try {
            testList.remove(8);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught); 
    }

    
}
