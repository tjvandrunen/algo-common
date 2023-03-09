package test;


import impl.ListBag;

public class LBTest extends BagTest {

    
    protected void reset() {
        testBag = new ListBag<String>();
    }
    protected void resetInt() {
        testBagInt = new ListBag<Integer>();
    }
    

}
