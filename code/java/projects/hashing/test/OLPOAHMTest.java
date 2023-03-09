package test;

import impl.OptimizedLPOpenAddressingHashMap;

public class OLPOAHMTest extends HashMapTest {

    private class TOLPOAHM<K,V> extends OptimizedLPOpenAddressingHashMap<K,V> {

        @Override
        public void remove(K key) {
            super.remove(key);
            assert deleteds == 0;
            for (Pair<K,V> p : table)
                assert p != deleted;
        }

    }
    
    protected void reset() {
        testMap = new TOLPOAHM<String,String>();
    }
    
    protected void resetPH() {
        testHashMap = new TOLPOAHM<PoorlyHashed,Integer>();
        
    }

    protected void resetInteger() {
        testMapInt = new TOLPOAHM<Integer,Integer>();
        
    }

    protected void resetSK() {
        skMap = new OptimizedLPOpenAddressingHashMap<SkeletonKey,String>();
    }

}
