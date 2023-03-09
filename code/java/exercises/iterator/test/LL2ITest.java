package test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import impl.IteratorUtil;
import impl.IteratorUtil.Node;

public class LL2ITest {

    @Test
    public void testEmpty() {
        for (Iterator<Integer> it = IteratorUtil.llToIterator(null); it.hasNext(); ) {
            assertTrue(false);
        }
    }

    @Test
    public void testOne() {
        int x = 0;
        for (Iterator<Integer> it = IteratorUtil.llToIterator(new Node<Integer>(12, null)); it.hasNext(); ) {
            assertEquals(12, (int) it.next());
            assertEquals(x++, 0);
        }
        assertEquals(x, 1);
    }

    @Test
    public void testLong() {
        Integer[] array = new Integer[] { 12, 7, 9, 14, 53, 22, 19, 63 };
        Node<Integer> head = null;
        for (int i = array.length - 1; i >= 0; i--)
            head = new Node<Integer>(array[i], head);
        int x = 0;
        for (Iterator<Integer> it = IteratorUtil.llToIterator(head); it.hasNext(); ) {
            assertTrue(x < array.length);
            assertEquals(array[x++], it.next());
        }
        assertEquals(x, array.length);
        
    }

    @Test
    public void testAllSame() {
        //Integer[] array = new Integer[] { 12, 12, 12, 12, 12, 12, 12, 12 };
        Node<Integer> head = null;
        for (int i = 0; i < 15; i++)
            head = new Node<Integer>(12, head);
        int x = 0;
        for (Iterator<Integer> it = IteratorUtil.llToIterator(head); it.hasNext(); ) {
            assertTrue(x++ < 15);
            assertEquals(12, (int) it.next());
        }
        assertEquals(x, 15);
    }

}
