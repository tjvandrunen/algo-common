package test;

import impl.SeparateChainingHashMap;

public class SCHMTest extends HashMapTest {

    protected void reset() {
        testMap = new SeparateChainingHashMap<String,String>();
    }
    
    protected void resetPH() {
        testHashMap = new SeparateChainingHashMap<PoorlyHashed,Integer>();
        
    }

    protected void resetInteger() {
        testMapInt = new SeparateChainingHashMap<Integer,Integer>();
        
    }
    
    protected void resetSK() {
        skMap = new SeparateChainingHashMap<SkeletonKey,String>();
    }

}
