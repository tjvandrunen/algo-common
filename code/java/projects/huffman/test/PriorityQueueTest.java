package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

import adt.PriorityQueue;

public abstract class PriorityQueueTest extends CollectionTest {
    // makes a MeasEq 
    private MeasEq mm(int i){ return new MeasEq(i); }
    // --- for tests on int priority queues ---
    MeasEq[] array = { mm(33), mm(22), mm(66), mm(99), mm(11), mm(88), mm(55),mm( 77), mm(44)};
    protected PriorityQueue<MeasEq> mpq;
    protected abstract void resetMeasEqEmpty(int size);
    protected Comparator<MeasEq> mCompo = new Comparator<MeasEq>() {
        public int compare(MeasEq o1, MeasEq o2) {
            return o1.kernel.compareTo(o2.kernel);
        }
    };
    
    protected Comparator<MeasEq> reverseMCompo = new Comparator<MeasEq>() {
        public int compare(MeasEq o1, MeasEq o2) {
            return o2.kernel.compareTo(o1.kernel);
        }
    };
    
    
    // --- for tests on Widget priority queues ---
    protected class Widget { 
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
    }
    protected PriorityQueue<Widget> wpq;
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
    protected abstract void resetWidgetPopulated();

    
    
    @Test
    public void testIInsertOne() {
        resetMeasEqEmpty(array.length);
        for (int i = 0; i < 1; i++) 
            mpq.insert(array[i]);
         assertEquals(33, mpq.extractMax().kernel.intValue());  
    }
    
    @Test
    public void testIInsertFew() {
        resetMeasEqEmpty(array.length);
        for (int i = 0; i < 5; i++) 
            mpq.insert(array[i]);
        assertEquals(99, mpq.extractMax().kernel.intValue());
    }
    
    @Test
    public void testIInsertFewStraightLine() {
        resetMeasEqEmpty(array.length);
        for (int i = 0; i < 5; i++)
            mpq.insert(mm(i));
        assertEquals(4, mpq.extractMax().kernel.intValue());
    }
    
    @Test
    public void testIExtractMaxFew() {
        resetMeasEqEmpty(array.length);
       for (int i = 0; i < 4; i++) {
            mpq.insert(array[i]);
            if (i % 3 == 1)
                mpq.extractMax();
        }
        assertEquals(99, mpq.extractMax().kernel.intValue());  
    }
    
    
    @Test
    public void testIExtractMaxMany() {
        resetMeasEqEmpty(array.length);
        for (int i = 0; i < 7; i++) {
            mpq.insert(array[i]);
            if (i % 3 == 1)
                mpq.extractMax();
        }
        assertEquals(88, mpq.extractMax().kernel.intValue());  
    }
    
    @Test
    public void testIExtractMaxAll() {
        resetMeasEqEmpty(array.length);
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
        wpq.extractMax();
        wpq.extractMax();
        wpq.extractMax();
        for (int i = 0; i < priorities.length; i++)
            if (i == 5 || i == 6 || i == 8)
                assertFalse(wpq.contains(new Widget(i)));
            else 
                assertTrue(wpq.contains(new Widget(i)));
    }

    @Test
    public void testWIncreaseKeyToMax() {
        wpRestore();
        resetWidgetPopulated();
        priorities[3] = 99;
        wpq.increaseKey(new Widget(3));
        assertEquals(new Widget(3), wpq.max());
    }
    
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
        resetMeasEqEmpty(maxSize);
        int numTests = 10;
        for (int test = 0; test < numTests; test++) {
            MeasEq[] Ints = new MeasEq[maxSize];
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
        resetMeasEqEmpty(maxSize);
        int numTests = 10;
        for (int test = 0; test < numTests; test++) {
            // insert
            MeasEq[] Ints = new MeasEq[maxSize];
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


}
