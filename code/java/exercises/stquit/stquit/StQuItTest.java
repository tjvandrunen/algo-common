package stquit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

abstract class StQuItTest {

    protected abstract Iterator<Integer> getIterator(int[] data);
    
    protected void runTest(int[] data) {
        Iterator<Integer> it = getIterator(data);
        for (int i = 0; i < data.length; i++) {
            assertTrue(it.hasNext());
            assertEquals((int) it.next(), data[i]);
        }
    }
    
    @Test
    void testA() {
        runTest(new int[] {});
    }

    @Test
    void testB() {
        runTest(new int[] { 21 });
    }
   
    @Test
    void testC() {
        runTest(new int[] { 0, 1, 2, 3, 4, 5, 6, 7 });
    }
   
    @Test
    void testD() {
        runTest(new int[] { 4, 14, 26, 7, 3, 19, 22, 4, 6, 21, 104 });
    }
    
}
