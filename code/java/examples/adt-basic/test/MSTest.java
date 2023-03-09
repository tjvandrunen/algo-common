package test;

import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

import impl.MapSet;

public class MSTest extends SetTest {
    /* The maximum number of elements to stress test with */
    private final int MAXSIZE=10000;
    
    /* The number of comparisons above the min that is allowed */
    private final int ERROR=0;

    protected void reset() {
        testSet = new MapSet<String>();
    }
    protected void resetInt() {
        testSetInt = new MapSet<Integer>();
    }
    
    @Test 
    public void stressTestComparisons() {
        // let size be the current size of test
        MapSet<MeasEq> test = new MapSet<MeasEq>();
        MeasEq.resetComparisons();
        assert(test.isEmpty());
        assertTrue("isEmpty() should make no comparisons",
                MeasEq.getComparisons() == 0);
        MeasEq[] keys;
        int tests = 10;
        for (int i = 0; i < tests; i++) {
            MeasEq.resetComparisons();
            int currentMaxSize = rand.nextInt(MAXSIZE);
            keys = new MeasEq[currentMaxSize];
            for (int j = 0; j < currentMaxSize; j++) {
                MeasEq.resetComparisons();
                assert(test.size() == j);
                assertTrue("size() should make no comparisons",
                        MeasEq.getComparisons() <= ERROR);
                
                MeasEq.resetComparisons();
                MeasEq toAdd = new MeasEq(j);
                test.add(toAdd);
                assertTrue("add() should make at most size comparisons",
                        MeasEq.getComparisons() <= j+ERROR);
                
                MeasEq.resetComparisons();
                assert(test.size() == j+1);
                assertTrue("size() should make no comparisons",
                        MeasEq.getComparisons() <= ERROR);
                
                MeasEq.resetComparisons();
                assert(!test.contains(new MeasEq(rand.nextInt()+j)));
                assertTrue("contains() should make at most N comparisons",
                        MeasEq.getComparisons() <=j+1+ERROR);
            }
            for (int j = 0; j <currentMaxSize; j++){
                MeasEq.resetComparisons();
                test.add(new MeasEq(j));
                assertTrue("add() should make at most size comparisons",
                        MeasEq.getComparisons() <= currentMaxSize+ERROR);
                
                MeasEq.resetComparisons();
                assert(test.size() == currentMaxSize);
                assertTrue("size() should make no comparisons",
                        MeasEq.getComparisons() <= ERROR);
            }
                
            Iterator<MeasEq> it = test.iterator();
            MeasEq.resetComparisons();
            for (int j = 0; j < currentMaxSize; j++) {
                assert(it.hasNext());
                keys[j] = it.next();
                
            }
            assertTrue("iteration should make no comparisons",
                    MeasEq.getComparisons() <= ERROR);
            for (int j = currentMaxSize-1; j >= 0;j--){
                MeasEq.resetComparisons();
                assert(test.size() == j+1);
                assertTrue("size() should make no comparisons",
                        MeasEq.getComparisons() <= ERROR);
            
                MeasEq.resetComparisons();
                assert(test.contains(keys[j]));
                assertTrue("contains() should make at most N comparisons",
                        MeasEq.getComparisons() <=j+1+ERROR);
                
                MeasEq.resetComparisons();
                test.remove(keys[j]);
                assertTrue("remove() should make at most N comparisons",
                        MeasEq.getComparisons() <=j+1+ERROR);
                
                MeasEq.resetComparisons();
                assert(!test.contains(keys[j]));
                assertTrue("contains() should make at most N comparisons",
                        MeasEq.getComparisons() <=j+ERROR);
                
                MeasEq.resetComparisons();
                assert(test.size() == j);
                assertTrue("size() should make no comparisons",
                        MeasEq.getComparisons() <= ERROR);
                
            }
            MeasEq.resetComparisons();
            assert(!test.iterator().hasNext());
            assertTrue("iteration should make no comparisons",
                    MeasEq.getComparisons() <= ERROR);
        }
    }
    
}
