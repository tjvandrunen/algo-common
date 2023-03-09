package test;

import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

import impl.ArrayMap;


public class AMTest extends MapStressTest {
    
    /* The maximum number of elements to stress test with */
    private static final int MAXSIZE=10000;
    
    /* The number of comparisons above the min that is allowed */
    private static final int ERROR=0;
    

    @Override
    protected void reset() {
        testMap = new ArrayMap<String, String>();
    }
    @Override
    protected void resetInteger() {
        testMapInt = new ArrayMap<Integer, Integer>();
        
    }
   /*
    @Test 
    public void stressTestComparisons() {
        // let size be the current size of test
        ArrayMap<MeasEq, MeasEq> test = new ArrayMap<MeasEq, MeasEq>();
        MeasEq[] keys;
        int magic = 42;
        int tests = 10;
        for (int i = 0; i < tests; i++) {
            MeasEq.resetComparisons();
            int currentMaxSize = rand.nextInt(MAXSIZE);
            keys = new MeasEq[currentMaxSize];
            for (int j = 0; j < currentMaxSize; j++) {
                
                MeasEq.resetComparisons();
                MeasEq toPut = new MeasEq(j);
                test.put(toPut, new MeasEq(null));
                assertTrue("put() should make at most size comparisons",
                        MeasEq.getComparisons() <= j+ERROR);
                
                MeasEq.resetComparisons();
                test.get(new MeasEq(rand.nextInt()));
                assertTrue("get() should make at most size comparisons",
                        MeasEq.getComparisons() <=j+1+ERROR);
            }
            for (int j = 0; j <currentMaxSize; j++){
                
                MeasEq.resetComparisons();
                test.put(new MeasEq(j),new MeasEq(j*42));
                assertTrue("put() should make at most size comparison",
                        MeasEq.getComparisons() <=currentMaxSize+ERROR);
                
            }
                
            Iterator<MeasEq> it = test.iterator();
            
            MeasEq.resetComparisons();
            for (int j = 0; j < currentMaxSize; j++) {
                assert(it.hasNext());
                keys[j] = it.next();
                
            }
            assertTrue("iteration should not make any comparisons",
                    MeasEq.getComparisons() <= ERROR);
            for (int j = currentMaxSize-1; j >= 0;j--){
                MeasEq.resetComparisons();
                assert(test.containsKey(keys[j]));
                assertTrue("containsKey() should make at most size comparison",
                        MeasEq.getComparisons() <= j+1+ERROR);
                
                MeasEq.resetComparisons();
                MeasEq returned = test.get(keys[j]);
                assert(returned != null);
                assert(returned.kernel.equals(keys[j].kernel * magic));
                assertTrue("get() should make at most size comparisons",
                        MeasEq.getComparisons() <=j+1+ERROR);
                
                
                MeasEq.resetComparisons();
                test.remove(keys[j]);
                assertTrue("remove() should make at most size comparisons",
                        MeasEq.getComparisons() <= j+1+ERROR);
                
                MeasEq.resetComparisons();
                assert(!test.containsKey(keys[j]));
                assertTrue("containsKey() should make at most size comparisons",
                        MeasEq.getComparisons() <= j+ERROR);
                
            }
            MeasEq.resetComparisons();
            assert(!test.iterator().hasNext());
            assertTrue("iteration should not make any comparisons",
                    MeasEq.getComparisons() <= ERROR);
        }
    }
    */
}
