package test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import adt.Bag;

public abstract class BagTest extends CollectionTest{

    protected Bag<String> testBag;
    protected Bag<Integer> testBagInt;
    
    private void populate() {
        for (int i = 0; i < getData().length; i++)
            for (int j = i; j < getData().length; j++)
                testBag.add(getData()[j]);
    }

    
    protected abstract void reset();
    protected abstract void resetInt();
    protected int indexForDatum(String datum) {
        int index = -1;
        for (int i = 0; i < getData().length && index == -1; i++)
            if (getData()[i].equals(datum))
                index = i;
        return index;
    }
    
    @Test
    public void initialEmpty() {
        reset();
        assertTrue(testBag.isEmpty());
    }

    @Test
    public void initialSize() {
        reset();
        assertEquals(0, testBag.size());
    }
    
    @Test
    public void addNotEmpty() {
        reset();
        testBag.add(getData()[0]);
        assertFalse(testBag.isEmpty());
    }

    @Test
    public void addSize() {
        reset();
        testBag.add(getData()[0]);
        assertEquals(1, testBag.size());
    }

    @Test
    public void twoAddsUniqueSize() {
        reset();
        testBag.add(getData()[0]);
        testBag.add(getData()[1]);
        assertFalse(testBag.isEmpty());
        assertEquals(2, testBag.size());
    }

    @Test
    public void twoAddsIdenticalSize() {
        reset();
        testBag.add(getData()[0]);
        testBag.add(getData()[0]);
        assertFalse(testBag.isEmpty());
        assertEquals(2, testBag.size());
    }

    @Test
    public void addsUnique() {
        reset();
        for (int i = 0; i < 8; i++) 
            testBag.add(getData()[i]);
        assertFalse(testBag.isEmpty());
        assertEquals(8, testBag.size());
    }
    
    @Test
    public void addsIdentical() {
        reset();
        for (int i = 0; i < 8; i++) 
            testBag.add(getData()[0]);
        assertFalse(testBag.isEmpty());
        assertEquals(8, testBag.size());
    }

    @Test
    public void addsMix() {
        reset();
        for (int i = 0; i < 50; i++)
            testBag.add(getData()[rand.nextInt(getData().length)]);
        assertFalse(testBag.isEmpty());
        assertEquals(50, testBag.size());
    }

    @Test
    public void addsLots() {
        reset();
        populate();
        assertFalse(testBag.isEmpty());
        assertEquals(getData().length*(getData().length + 1)/2, testBag.size());
    }
    
    @Test
    public void countInitial() {
        reset();
        assertEquals(0, testBag.count(getData()[0]));
    }
    
    @Test
    public void countOne() {
        reset();
        testBag.add(getData()[0]);
        assertEquals(1, testBag.count(getData()[0]));
    }

    @Test
    public void countTwoUnique() {
        reset();
        testBag.add(getData()[0]);
        testBag.add(getData()[1]);
        assertEquals(1, testBag.count(getData()[0]));
        assertEquals(1, testBag.count(getData()[1]));
    }

    @Test
    public void countTwoIdentical() {
        reset();
        testBag.add(getData()[0]);
        testBag.add(getData()[0]);
        assertEquals(2, testBag.count(getData()[0]));
    }

    
    @Test
    public void countLots() {
        reset();
        populate();
        for (int i = 0; i < getData().length; i++)
            assertEquals(i+1, testBag.count(getData()[i]));
    }

    @Test
    public void removeInitial() {
        reset();
        testBag.remove(getData()[0]);
        assertEquals(0, testBag.count(getData()[0]));
        assertEquals(0, testBag.size());
        assertTrue(testBag.isEmpty());
    }

    @Test
    public void removeSpurious() {
        reset();
        testBag.add(getData()[0]);
        testBag.add(getData()[1]);
        testBag.add(getData()[0]);
        testBag.remove(getData()[2]);
        assertEquals(2, testBag.count(getData()[0]));
        assertEquals(1, testBag.count(getData()[1]));
        assertEquals(0, testBag.count(getData()[2]));
        assertEquals(3, testBag.size());
        assertFalse(testBag.isEmpty());
    }

    @Test
    public void removeOnlySingleton() {
        reset();
        testBag.add(getData()[0]);
        testBag.remove(getData()[0]);
        assertEquals(0, testBag.count(getData()[0]));
        assertEquals(0, testBag.size());
        assertTrue(testBag.isEmpty());
    }

    @Test
    public void removeOnlyMultiple() {
        reset();
        testBag.add(getData()[0]);
        testBag.add(getData()[0]);
        testBag.add(getData()[0]);
        testBag.remove(getData()[0]);
        assertEquals(0, testBag.count(getData()[0]));
        assertEquals(0, testBag.size());
        assertTrue(testBag.isEmpty());
    }
        
    @Test
    public void removeSingletonAmongMany() {
        reset();
        testBag.add(getData()[0]);
        testBag.add(getData()[1]);
        testBag.add(getData()[2]);
        testBag.add(getData()[1]);
        testBag.add(getData()[3]);
        testBag.add(getData()[4]);
        testBag.add(getData()[1]);
        testBag.add(getData()[4]);
        testBag.remove(getData()[0]);
        assertEquals(0, testBag.count(getData()[0]));
        assertEquals(3, testBag.count(getData()[1]));
        assertEquals(1, testBag.count(getData()[2]));
        assertEquals(1, testBag.count(getData()[3]));
        assertEquals(2, testBag.count(getData()[4]));
        assertEquals(7, testBag.size());
        assertFalse(testBag.isEmpty());
    }

    @Test
    public void removeMultipleAmongMany() {
        reset();
        testBag.add(getData()[0]);
        testBag.add(getData()[1]);
        testBag.add(getData()[2]);
        testBag.add(getData()[1]);
        testBag.add(getData()[0]);
        testBag.add(getData()[3]);
        testBag.add(getData()[4]);
        testBag.add(getData()[1]);
        testBag.add(getData()[4]);
        testBag.add(getData()[0]);
        testBag.remove(getData()[0]);
        assertEquals(0, testBag.count(getData()[0]));
        assertEquals(3, testBag.count(getData()[1]));
        assertEquals(1, testBag.count(getData()[2]));
        assertEquals(1, testBag.count(getData()[3]));
        assertEquals(2, testBag.count(getData()[4]));
        assertEquals(7, testBag.size());
        assertFalse(testBag.isEmpty());
    }

    @Test
    public void addSameAfterRemove() {
        reset();
        testBag.add(getData()[0]);
        testBag.add(getData()[1]);
        testBag.add(getData()[2]);
        testBag.add(getData()[1]);
        testBag.add(getData()[0]);
        testBag.add(getData()[3]);
        testBag.add(getData()[4]);
        testBag.add(getData()[1]);
        testBag.add(getData()[4]);
        testBag.add(getData()[0]);
        testBag.remove(getData()[0]);
        testBag.add(getData()[0]);
        assertEquals(1, testBag.count(getData()[0]));
        assertEquals(3, testBag.count(getData()[1]));
        assertEquals(1, testBag.count(getData()[2]));
        assertEquals(1, testBag.count(getData()[3]));
        assertEquals(2, testBag.count(getData()[4]));
        assertEquals(8, testBag.size());
        assertFalse(testBag.isEmpty());
    }

    @Test
    public void addDifferentAfterRemove() {
        reset();
        testBag.add(getData()[0]);
        testBag.add(getData()[1]);
        testBag.add(getData()[2]);
        testBag.add(getData()[1]);
        testBag.add(getData()[0]);
        testBag.add(getData()[3]);
        testBag.add(getData()[4]);
        testBag.add(getData()[1]);
        testBag.add(getData()[4]);
        testBag.add(getData()[0]);
        testBag.remove(getData()[0]);
        testBag.add(getData()[4]);
        testBag.add(getData()[5]);
        assertEquals(0, testBag.count(getData()[0]));
        assertEquals(3, testBag.count(getData()[1]));
        assertEquals(1, testBag.count(getData()[2]));
        assertEquals(1, testBag.count(getData()[3]));
        assertEquals(3, testBag.count(getData()[4]));
        assertEquals(1, testBag.count(getData()[5]));
        assertEquals(9, testBag.size());
        assertFalse(testBag.isEmpty());
    }

    @Test 
    public void removeSomeOfMany() {
        reset();
        populate();
        testBag.remove(getData()[3]);
        testBag.remove(getData()[4]);
        testBag.remove(getData()[9]);
        for (int i = 0; i < getData().length; i++)
            if (i == 3 || i == 4 || i == 9)
                assertEquals(0, testBag.count(getData()[i]));
            else
                assertEquals(i+1, testBag.count(getData()[i]));
        assertEquals(getData().length*(getData().length + 1)/2 - 19, testBag.size());
    }

    @Test
    public void removeAllOfMany() {
        reset();
        populate();
        for (int i = 0; i < getData().length; i++)
            testBag.remove(getData()[i]);
        for (int i = 0; i < getData().length; i++)
            assertEquals(0, testBag.count(getData()[i]));
        assertEquals(0, testBag.size());
    }

    @Test
    public void initialIterator() {
        reset();
        int i = 0;
        for (Iterator<String> it = testBag.iterator(); it.hasNext(); )
            i++;
        assertEquals(0, i);
    }

    @Test
    public void onlySingletonIterator() {
        reset();
        testBag.add(getData()[0]);
        clearMarks();
        for (Iterator<String> it = testBag.iterator(); it.hasNext(); )
            marks[indexForDatum(it.next())]++;
        assertEquals(1, marks[0]);
        for (int i = 1; i < marks.length; i++)
            assertEquals(0, marks[i]);
    }

    @Test
    public void onlyMultipleIterator() {
        reset();
        testBag.add(getData()[0]);
        testBag.add(getData()[0]);
        testBag.add(getData()[0]);
        clearMarks();
        for (Iterator<String> it = testBag.iterator(); it.hasNext(); )
            marks[indexForDatum(it.next())]++;
        assertEquals(3, marks[0]);
        for (int i = 1; i < marks.length; i++)
            assertEquals(0, marks[i]);
    }

    @Test
    public void manyPlannedIterator() {
        reset();
        populate();
        clearMarks();
        for (Iterator<String> it = testBag.iterator(); it.hasNext(); )
            marks[indexForDatum(it.next())]++;
        for (int i = 0; i < marks.length; i++)
            assertEquals(i+1, marks[i]);
    }

    @Test
    public void manyRandomIterator() {
        reset();
        for (int i = 0; i < 50; i++)
            testBag.add(getData()[rand.nextInt(getData().length)]);
        clearMarks();
        for (Iterator<String> it = testBag.iterator(); it.hasNext(); )
            marks[indexForDatum(it.next())]++;
        for (int i = 0; i < marks.length; i++)
            assertEquals(testBag.count(getData()[i]), marks[i]);
    }

    @Test
    public void removeSomeIterator() {
        reset();
        populate();
        testBag.remove(getData()[3]);
        testBag.remove(getData()[4]);
        testBag.remove(getData()[9]);
        clearMarks();
        for (Iterator<String> it = testBag.iterator(); it.hasNext(); )
            marks[indexForDatum(it.next())]++;
        for (int i = 0; i < marks.length; i++)
            if (i == 3 || i == 4 || i == 9)
                assertEquals(0, marks[i]);
            else
                assertEquals(i+1, marks[i]);
    }

    @Test
    public void removeAllIterator() {
        reset();
        populate();
        for (int i = 0; i < getData().length; i++)
            testBag.remove(getData()[i]);
        int i = 0;
        for (Iterator<String> it = testBag.iterator(); it.hasNext(); i++)
            it.next();
        assertEquals(0, i);
    }

    @Test
    public void addAfterRemoveIterator() {
        reset();
        populate();
        clearMarks();
        for (int i = 0; i < getData().length; i++)
            testBag.remove(getData()[i]);
        testBag.add(getData()[0]);
        testBag.add(getData()[1]);
        testBag.add(getData()[0]);
        clearMarks();
        for (Iterator<String> it = testBag.iterator(); it.hasNext(); )
            marks[indexForDatum(it.next())]++;
        assertEquals(2, marks[0]);
        assertEquals(1, marks[1]);
        for (int i = 2; i < marks.length; i++)
            assertEquals(0, marks[i]);
    }
    
    @Test 
    public void stressTest() {
    	resetInt();
    	final int MAX_SIZE = 50;
        // let size be the total number of items in test
        int tests = 5;
        int sumcounts = 0;
        for (int i = 0; i < tests; i++) {
            assert(testBagInt.isEmpty());
            int currentMaxSize = rand.nextInt(MAX_SIZE);
            for (int j = 0; j < currentMaxSize; j++) {
                assert(testBagInt.size() == sumcounts);
                
                for (int k = 0; k < j+5; k++) {
                    testBagInt.add(new Integer(j));
                    sumcounts++;
                    assertEquals(k+1,testBagInt.count(new Integer(j)));
                    assertEquals(0,testBagInt.count(new Integer(-1)));
                }
                
            }
            for (int j = 0; j < currentMaxSize; j++ ){
                assertEquals(j+5,testBagInt.count(new Integer(j)));
                assertEquals(0,testBagInt.count(new Integer(-1)));
            }
            
            for (int j = 0; j < currentMaxSize; j++ ){
                testBagInt.remove(new Integer(j));
                sumcounts -= j+5;
            }
            
        }
    }
    
}
