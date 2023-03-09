package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

import impl.HeapPositionAware;
import impl.OptimizedHeapPriorityQueue;

public class OHPQTest extends  CollectionTest {

    private static class HAMeasEq extends MeasEq implements HeapPositionAware {
        private int pos;
        public HAMeasEq(Integer num) { super(num); }
        public void setPosition(int pos) { this.pos = pos; }
        public int getPosition() { return pos; }
    }
    protected Comparator<HAMeasEq> hmCompo = new Comparator<HAMeasEq>() {
        public int compare(HAMeasEq o1, HAMeasEq o2) {
            return o1.kernel.compareTo(o2.kernel);
        }
    };
 
    // makes a HAMeasEq 
    private HAMeasEq mm(int i){ return new HAMeasEq(i); }
    // --- for tests on int priority queues ---
    HAMeasEq[] array = { mm(33), mm(22), mm(66), mm(99), mm(11), mm(88), mm(55),mm( 77), mm(44)};
    protected OptimizedHeapPriorityQueue<HAMeasEq> mpq;
    protected Comparator<HAMeasEq> mCompo = new Comparator<HAMeasEq>() {
        public int compare(HAMeasEq o1, HAMeasEq o2) {
            return o1.kernel.compareTo(o2.kernel);
        }
    };
    
    protected Comparator<HAMeasEq> reverseMCompo = new Comparator<HAMeasEq>() {
        public int compare(HAMeasEq o1, HAMeasEq o2) {
            return o2.kernel.compareTo(o1.kernel);
        }
    };
    
    
    // --- for tests on HAWidget priority queues ---
    protected class Widget implements HeapPositionAware { 
        final int index;
        Widget(int index) { this.index = index; }
        @Override
        public boolean equals(Object o) {
            return o instanceof Widget && ((Widget) o).index == index;
        }
        @Override 
        public int hashCode() {
            throw new UnsupportedOperationException();
        }
        public String toString() {
            return "W"+priorities[index];
        }
        private int pos;
        public void setPosition(int pos) { this.pos = pos; }
        public int getPosition() { return pos; }
    }
    protected OptimizedHeapPriorityQueue<Widget> wpq;
    private int[] priorities = { 10, 40, 20, 60, 30, 70, 80, 50, 90, 0 };
    private void wpRestore() {
        priorities[3] = 60;
        priorities[0] = 10;
    }
    protected Comparator<Widget> wCompo = new Comparator<Widget>() {
        public int compare(Widget o1, Widget o2) {
            return priorities[o1.index] - priorities[o2.index];
        }
    };
    protected Iterable<Widget> itably = new Iterable<Widget>() {
        public Iterator<Widget> iterator() {
            return new Iterator<Widget>() {
                int i = 0;
                public boolean hasNext() {
                    return i < priorities.length;
                }

                public Widget next() {
                    if (! hasNext()) throw new NoSuchElementException();
                    return new Widget(i++);
                }

                public void remove() {
                    throw new UnsupportedOperationException();
                }

            };
        }
    };


    
    
    @Test
    public void testIInsertOne() {
        resetHAMeasEqEmpty(array.length);
        for (int i = 0; i < 1; i++) 
            mpq.insert(array[i]);
         assertEquals(33, mpq.extractMax().kernel.intValue());  
    }
    
    @Test
    public void testIInsertFew() {
        resetHAMeasEqEmpty(array.length);
        for (int i = 0; i < 5; i++) 
            mpq.insert(array[i]);
        assertEquals(99, mpq.extractMax().kernel.intValue());
    }
    
    @Test
    public void testIInsertFewStraightLine() {
        resetHAMeasEqEmpty(array.length);
        for (int i = 0; i < 5; i++)
            mpq.insert(mm(i));
        assertEquals(4, mpq.extractMax().kernel.intValue());
    }
    
    @Test
    public void testIExtractMaxFew() {
        resetHAMeasEqEmpty(array.length);
       for (int i = 0; i < 4; i++) {
            mpq.insert(array[i]);
            if (i % 3 == 1)
                mpq.extractMax();
        }
        assertEquals(99, mpq.extractMax().kernel.intValue());  
    }
    
    
    @Test
    public void testIExtractMaxMany() {
        resetHAMeasEqEmpty(array.length);
        for (int i = 0; i < 7; i++) {
            mpq.insert(array[i]);
            if (i % 3 == 1)
                mpq.extractMax();
        }
        assertEquals(88, mpq.extractMax().kernel.intValue());  
    }
    
    @Test
    public void testIExtractMaxAll() {
        resetHAMeasEqEmpty(array.length);
        for (int i = 0; i < array.length; i++) {
            mpq.insert(array[i]);
            if (i % 3 == 1)
                mpq.extractMax();
        }
        assertEquals(77, mpq.extractMax().kernel.intValue());  
        assertEquals(66, mpq.extractMax().kernel.intValue());  
        assertEquals(55, mpq.extractMax().kernel.intValue());  
        assertEquals(44, mpq.extractMax().kernel.intValue());  
        assertEquals(22, mpq.extractMax().kernel.intValue());  
        assertEquals(11, mpq.extractMax().kernel.intValue());  
    }

    
    @Test
    public void testWMed() {
        wpRestore();
        resetWidgetPopulated();
        wpq.extractMax();
        wpq.extractMax();
        wpq.extractMax();
        assertFalse(wpq.isEmpty());
    }
    
    @Test
    public void testWEmpty() {
        wpRestore();
        resetWidgetPopulated();
        wpq.extractMax();
        wpq.extractMax();
        wpq.extractMax();
        wpq.extractMax();
        wpq.extractMax();
        wpq.extractMax();
        wpq.extractMax();
        wpq.extractMax();
        wpq.extractMax();
        wpq.extractMax();
        assertTrue(wpq.isEmpty());
    }

    @Test
    public void testWMaxInitial() {
        wpRestore();
        resetWidgetPopulated();
        assertEquals(wpq.max(), new Widget(8));
    }
    
    @Test
    public void testWExtractMaxInitial() {
        wpRestore();
        resetWidgetPopulated();
        assertEquals(wpq.extractMax(), new Widget(8));
        
    }
    
    @Test
    public void testWMaxMed() {
        wpRestore();
        resetWidgetPopulated();
        wpq.extractMax();
        wpq.extractMax();
        wpq.extractMax();
        assertEquals(wpq.max(), new Widget(3));
    }

    @Test
    public void testWContainsInitial() {
        wpRestore();
        resetWidgetPopulated();
        for (int i = 0; i < priorities.length; i++) 
            assertTrue(wpq.contains(new Widget(i)));
        
    }

    @Test
    public void testWContainsMed() {
        wpRestore();
        resetWidgetPopulated();
        Widget a = wpq.extractMax();
        Widget b = wpq.extractMax();
        Widget c = wpq.extractMax();
        assertFalse(wpq.contains(a));
        assertFalse(wpq.contains(b));
        assertFalse(wpq.contains(c));
    }

    /*
    @Test
    public void testWIncreaseKeyToMax() {
        wpRestore();
        resetWidgetPopulated();
        priorities[3] = 99;
        wpq.increaseKey(new Widget(3));
        assertEquals(new Widget(3), wpq.max());
    }
*/    
    @Test
    public void testWIncreaseKeyToMed() {
        wpRestore();
        resetWidgetPopulated();
        priorities[0] = 55;
        wpq.increaseKey(new Widget(0));
        wpq.extractMax();
        wpq.extractMax();
        wpq.extractMax();
        wpq.extractMax();

        assertEquals(wpq.max(), new Widget(0));
    }
    @Test
    public void insertPollLots() {
        
        int maxSize = 10000;
        resetHAMeasEqEmpty(maxSize);
        int numTests = 10;
        for (int test = 0; test < numTests; test++) {
            HAMeasEq[] Ints = new HAMeasEq[maxSize];
            for (int i = 0; i < maxSize; i++) {
                Ints[i] = mm(i);
                mpq.insert(Ints[i]);
                
            }
            for (int i = maxSize-1; i >= 0; i--) {
                assertEquals(Ints[i],mpq.extractMax());
            }
        }
    }
    
    @Test
    public void stressTest() {
        
        int maxSize = 10000;
        resetHAMeasEqEmpty(maxSize);
        int numTests = 10;
        for (int test = 0; test < numTests; test++) {
            // insert
            HAMeasEq[] Ints = new HAMeasEq[maxSize];
            for (int i = 0; i < maxSize; i++) {
                Ints[i] = mm(i);
                mpq.insert(Ints[i]);
            }
            //poll and test
            for (int i = 0; i < maxSize; i++) {
                assertEquals(Ints[maxSize-i-1],mpq.extractMax());
            }
            
            
            //insert
            for (int i = 0; i < maxSize; i++) {
                Ints[i] = mm(i);
                mpq.insert(Ints[i]);
                
            }
            /*
             * shuffle by adding numbers generated with the linear congruential method (to be deterministic). 
             * As described in TAOCP 3.2
             */
            int current = 42;
            int a = 7;
            int c = 20;
            int n = 300;
            for (int i = maxSize -1; i >= 0; i--) {
                Ints[i].kernel = new Integer(Ints[i].kernel + current);
                current = (a*current + c ) %n;
                mpq.increaseKey(Ints[i]);
            }
            Arrays.sort(Ints,reverseMCompo);
            // poll and test
            for (int i = 0; i < maxSize; i++) {
                assert(mpq.contains(Ints[i]));
                assertEquals(Ints[i],mpq.extractMax());
            }
        }
    }
  
    
    protected void resetHAMeasEqEmpty(int size) {
        mpq = new OptimizedHeapPriorityQueue<HAMeasEq>(size, hmCompo);
    }

    protected void resetWidgetPopulated() {
        wpq = new OptimizedHeapPriorityQueue<Widget>(itably, wCompo); 
    }

}
