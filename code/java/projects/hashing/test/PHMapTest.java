package test;

import impl.PerfectHashMap;


public class PHMapTest extends MapRemoveTest {
    
    protected void reset() {
        testMap = new PerfectHashMap<String, String>(getKeys());
    }

 
}
