package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import impl.OpenAddressingHashMap;
import test.HashMapTest.SkeletonKey;

public class LPOAHMTest extends HashMapTest {

    protected void reset() {
        testMap = new OpenAddressingHashMap<String,String>(OpenAddressingHashMap.LINEAR_PROBING);
    }
    
    protected void resetPH() {
        testHashMap = new OpenAddressingHashMap<PoorlyHashed,Integer>(OpenAddressingHashMap.LINEAR_PROBING);
        
    }

    protected void resetInteger() {
        testMapInt = new OpenAddressingHashMap<Integer,Integer>(OpenAddressingHashMap.LINEAR_PROBING);
        
    }

    protected void resetSK() {
        skMap = new OpenAddressingHashMap<SkeletonKey,String>(OpenAddressingHashMap.LINEAR_PROBING);
    }


    @Test
    public void hashScatters() {
        resetSK();
        SkeletonKey[] keys = new SkeletonKey[31];
        for (int i = 1; i <= 31; i++) {
            keys[i-1] = new SkeletonKey("" + i, 41 * i, i);
            skMap.put(keys[i-1], "x");
        }
        keys[30].resetComparisons();
        assertTrue(skMap.containsKey(keys[30]));
        assertTrue(keys[30].getComparisons() <= 5);
    }
    
}
