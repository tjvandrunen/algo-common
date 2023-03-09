package test;

import impl.MapList;

public class MLTest extends ListTest {
    
    protected void reset() {
        testList = new MapList<String>();
    }
    
	@Override
	protected void resetInt() {
		testListInt = new MapList<Integer>();
	}   
}
