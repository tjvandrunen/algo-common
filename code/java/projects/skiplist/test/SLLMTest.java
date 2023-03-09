package test;

import impl.SortedLinkedListMap;

public class SLLMTest extends MapStressTest {

    protected void resetInteger() {
        testMapInt = new SortedLinkedListMap<Integer, Integer>();
    }
    
    protected void reset() {
       testMap = new SortedLinkedListMap<String,String>();
   }
}
