package test;
import impl.SkipListMap;
public class SLMRemoveTest extends MapStressTest {

    @Override
    protected void resetInteger() {
        testMapInt = new SkipListMap<Integer,Integer>();
    
        
    }

    @Override
    protected void reset() {
        testMap = new SkipListMap<String,String>();
        
    }

}
