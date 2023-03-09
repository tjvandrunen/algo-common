package sudokuit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

public class SITest {

    protected void arrayVsIterable(int[] array, Iterable<Integer> itable) {
        Iterator<Integer> it = itable.iterator();
        for (int i = 0; i < array.length; i++) {
            assertTrue(it.hasNext());
            assertTrue(array[i] == it.next());
        }
        assertFalse(it.hasNext());
    }

}