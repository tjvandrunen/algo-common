package test;

import java.util.Iterator;

import org.junit.Test;

import impl.ArrayMap;


public class AMTest extends MapStressTest {
    

    

    @Override
    protected void reset() {
         testMap = new ArrayMap<String, String>();
    }
    @Override
    protected void resetInteger() {
        testMapInt = new ArrayMap<Integer, Integer>();
        
    }

    
    @Test
    public void fullyPopulateThenRemove() {
        ArrayMap<Integer, Integer> testMap = new ArrayMap<Integer, Integer>();
        // populate testMap with internal.length values
        for (int i = 0; i < 100; i++) {
            testMap.put(i, i);
        } 
        // remove the first value
        testMap.remove(0);
        // check to make sure that values were correctly shifted over
        Iterator<Integer> it = testMap.iterator();
        for (int i = 0; it.hasNext(); i++) {
            assert it.next() == i+1;
        } 
    }
   
    
}
