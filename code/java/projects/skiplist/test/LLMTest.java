package test;

import impl.LinkedListMap;

public class LLMTest extends MapStressTest {

    protected void resetInteger() {
        testMapInt = new LinkedListMap<Integer, Integer>();
    }
    
    protected void reset() {
       testMap = new LinkedListMap<String,String>();
   }

}
