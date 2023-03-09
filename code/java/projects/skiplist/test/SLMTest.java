package test;

import impl.SkipListMap;

public class SLMTest extends MapTest {


    @Override
    protected void reset() {
        testMap = new SkipListMap<String,String>();
        
    }

}
