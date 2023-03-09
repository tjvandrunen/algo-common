package test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import impl.IteratorUtil;

public class TDA2ITest {

    @Test
    public void testSquare() {
        Integer[][] array = { {1,2,4,9}, {0,6,3,4}, {11, 22, 33, 44}, {12,13,14,15}};
        runTestOnArray(array);
    }
    
    @Test
    public void testRectangular() {
        Integer[][] array = { {1,2,4,9,88}, {0,6,3,4,99}, {11, 22, 33, 44,103}, {12,13,14,15,16}};
        runTestOnArray(array);
    }

    @Test
    public void testRagged() {
        Integer[][] array = { {1,2,4,9,88,67}, {0,6,3}, {11}, {12,13,14,15,16}};
        runTestOnArray(array);
    }


    private void runTestOnArray(Integer[][] array) {
        Iterator<Integer> it = IteratorUtil.twoDArrayToIterator(array);
        for (int i = 0; i < array.length; i++)
            for (int j = 0; j < array[i].length; j++) {
                assertTrue(it.hasNext());
                assertEquals(array[i][j], it.next());
            }
        assertFalse(it.hasNext());
    }

}
