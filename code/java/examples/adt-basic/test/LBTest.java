package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import impl.ListBag;

public class LBTest extends BagTest {
    /* The maximum number of elements to stress test with */
    private final int MAXSIZE=60;
    
    /* The number of comparisons above the min that is allowed */
    private final int ERROR=0;
    
    protected void reset() {
        testBag = new ListBag<String>();
    }
    protected void resetInt() {
        testBagInt = new ListBag<Integer>();
    }
    @Test 
    public void stressTestComparisons() {
        // let size be the total number of items in test
        ListBag<MeasEq> test = new ListBag<MeasEq>();
        int tests = 7;
        int sumcounts = 0;
        for (int i = 0; i < tests; i++) {
            MeasEq.resetComparisons();
            assert(test.isEmpty());
            assertTrue("isEmpty() should make no comparisons", 
                    MeasEq.getComparisons() <= ERROR);
            int currentMaxSize = rand.nextInt(MAXSIZE);
            for (int j = 0; j < currentMaxSize; j++) {
                MeasEq.resetComparisons();
                assert(test.size() == sumcounts);
                assertTrue("size() should make no comparisons", 
                        MeasEq.getComparisons() <= ERROR);
                
                for (int k = 0; k < j+5; k++) {
                    MeasEq.resetComparisons();
                    test.add(new MeasEq(j));
                    sumcounts++;
                    assertTrue("add() should make at most size comparisons",
                            MeasEq.getComparisons() <= sumcounts + ERROR-1);
                    
                    MeasEq.resetComparisons();
                    assertEquals(k+1,test.count(new MeasEq(j)));
                    assertTrue("count() should make at most size comparisons",
                            MeasEq.getComparisons() <= sumcounts+ ERROR);
                    
                    MeasEq.resetComparisons();
                    assertEquals(0,test.count(new MeasEq(-1)));
                    assertTrue("count() should make at most size comparisons",
                            MeasEq.getComparisons() <= sumcounts+ ERROR);
                }
                
            }
            for (int j = 0; j < currentMaxSize; j++ ){
                MeasEq.resetComparisons();
                assertEquals(j+5,test.count(new MeasEq(j)));
                assertTrue("count() should make at most size comparisons",
                        MeasEq.getComparisons() <= sumcounts+ ERROR);
                
                MeasEq.resetComparisons();
                assertEquals(0,test.count(new MeasEq(-1)));
                assertTrue("count() should make at most size comparisons",
                        MeasEq.getComparisons() <= sumcounts+ ERROR);
            }
            
            for (int j = 0; j < currentMaxSize; j++ ){
                MeasEq.resetComparisons();
                test.remove(new MeasEq(j));
                assertTrue("remove() should make at most size comparisons",
                        MeasEq.getComparisons() <= sumcounts+ ERROR);
                sumcounts -= j+5;
            }
            
        }
    }

}
