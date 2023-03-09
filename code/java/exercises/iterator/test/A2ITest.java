package test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import impl.IteratorUtil;


public class A2ITest {

    @Test
    public void testEmpty() {
        Integer[] array = new Integer[0];
        for (Iterator<Integer> it = IteratorUtil.arrayToIterator(array); it.hasNext(); ) {
            assertTrue(false);
        }
    }

    @Test
    public void testOne() {
        Integer[] array = new Integer[] { 12 };
        int x = 0;
        for (Iterator<Integer> it = IteratorUtil.arrayToIterator(array); it.hasNext(); ) {
            assertEquals(12, (int) it.next());
            assertEquals(x++, 0);
        }
        assertEquals(x, array.length);
    }

    @Test
    public void testLong() {
        Integer[] array = new Integer[] { 12, 7, 9, 14, 53, 22, 19, 63 };
        int x = 0;
        for (Iterator<Integer> it = IteratorUtil.arrayToIterator(array); it.hasNext(); ) {
            assertTrue(x < array.length);
            assertEquals(array[x++], it.next());
        }
        assertEquals(x, array.length);
        
    }

    @Test
    public void testAllSame() {
        Integer[] array = new Integer[] { 12, 12, 12, 12, 12, 12, 12, 12 };
        int x = 0;
        for (Iterator<Integer> it = IteratorUtil.arrayToIterator(array); it.hasNext(); ) {
            assertTrue(x < array.length);
            assertEquals(array[x++], it.next());
        }
        assertEquals(x, array.length);
    }

    
}
