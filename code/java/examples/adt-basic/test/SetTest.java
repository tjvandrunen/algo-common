package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

import adt.Set;

public abstract class SetTest extends CollectionTest{

    protected Set<String> testSet;
    protected Set<Integer> testSetInt;
    
    protected void populate() {
        for (int i = 0; i < getData().length; i++)
            testSet.add(getData()[i]);
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
        assertTrue(testSet.isEmpty());
    }

    @Test
    public void initialSize() {
        reset();
        assertEquals(0, testSet.size());
    }
    
    @Test
    public void addNotEmpty() {
        reset();
        testSet.add(getData()[0]);
        assertFalse(testSet.isEmpty());
    }

    @Test
    public void addSize() {
        reset();
        testSet.add(getData()[0]);
        assertEquals(1, testSet.size());
    }

    @Test
    public void twoAddsUniqueSize() {
        reset();
        testSet.add(getData()[0]);
        testSet.add(getData()[1]);
        assertFalse(testSet.isEmpty());
        assertEquals(2, testSet.size());
    }

    @Test
    public void twoAddsIdenticalSize() {
        reset();
        testSet.add(getData()[0]);
        testSet.add(getData()[0]);
        assertFalse(testSet.isEmpty());
        assertEquals(1, testSet.size());
    }

    @Test
    public void addsUnique() {
        reset();
        for (int i = 0; i < 8; i++) 
            testSet.add(getData()[i]);
        assertFalse(testSet.isEmpty());
        assertEquals(8, testSet.size());
    }
    
    @Test
    public void addsIdentical() {
        reset();
        for (int i = 0; i < 8; i++) 
            testSet.add(getData()[0]);
        assertFalse(testSet.isEmpty());
        assertEquals(1, testSet.size());
    }

    @Test
    public void addsMix() {
        reset();
        for (int i = 0; i < 50; i++)
            testSet.add(getData()[rand.nextInt(getData().length)]);
        assertFalse(testSet.isEmpty());
        //assertEquals(getData().length, testSet.size());
    }

    @Test
    public void addsLots() {
        reset();
        populate();
        assertFalse(testSet.isEmpty());
        assertEquals(getData().length, testSet.size());
    }
    
    @Test
    public void containsInitial() {
        reset();
        assertFalse(testSet.contains(getData()[0]));
    }
    
    @Test
    public void containsOne() {
        reset();
        testSet.add(getData()[0]);
        assertTrue(testSet.contains(getData()[0]));
    }

    @Test
    public void containsTwoUnique() {
        reset();
        testSet.add(getData()[0]);
        testSet.add(getData()[1]);
        assertTrue(testSet.contains(getData()[0]));
        assertTrue(testSet.contains(getData()[1]));
    }

    @Test
    public void containsTwoIdentical() {
        reset();
        testSet.add(getData()[0]);
        testSet.add(getData()[0]);
        assertTrue(testSet.contains(getData()[0]));
    }

    
    @Test
    public void containsLots() {
        reset();
        populate();
        for (int i = 0; i < getData().length; i++)
            assertTrue(testSet.contains(getData()[i]));
    }

    @Test
    public void removeInitial() {
        reset();
        testSet.remove(getData()[0]);
        assertFalse(testSet.contains(getData()[0]));
        assertEquals(0, testSet.size());
        assertTrue(testSet.isEmpty());
    }

    @Test
    public void removeSpurious() {
        reset();
        testSet.add(getData()[0]);
        testSet.add(getData()[1]);
        testSet.add(getData()[0]);
        testSet.remove(getData()[2]);
        assertTrue(testSet.contains(getData()[0]));
        assertTrue(testSet.contains(getData()[1]));
        assertFalse(testSet.contains(getData()[2]));
        assertEquals(2, testSet.size());
        assertFalse(testSet.isEmpty());
    }

    @Test
    public void removeOnlySingleton() {
        reset();
        testSet.add(getData()[0]);
        testSet.remove(getData()[0]);
        assertFalse(testSet.contains(getData()[0]));
        assertEquals(0, testSet.size());
        assertTrue(testSet.isEmpty());
    }

    @Test
    public void removeOnlyMultiple() {
        reset();
        testSet.add(getData()[0]);
        testSet.add(getData()[0]);
        testSet.add(getData()[0]);
        testSet.remove(getData()[0]);
        assertFalse(testSet.contains(getData()[0]));
        assertEquals(0, testSet.size());
        assertTrue(testSet.isEmpty());
    }
        
    @Test
    public void removeSingletonAmongMany() {
        reset();
        testSet.add(getData()[0]);
        testSet.add(getData()[1]);
        testSet.add(getData()[2]);
        testSet.add(getData()[1]);
        testSet.add(getData()[3]);
        testSet.add(getData()[4]);
        testSet.add(getData()[1]);
        testSet.add(getData()[4]);
        testSet.remove(getData()[0]);
        assertFalse(testSet.contains(getData()[0]));
        assertTrue(testSet.contains(getData()[1]));
        assertTrue(testSet.contains(getData()[2]));
        assertTrue(testSet.contains(getData()[3]));
        assertTrue(testSet.contains(getData()[4]));
        assertEquals(4, testSet.size());
        assertFalse(testSet.isEmpty());
    }

    @Test
    public void removeMultipleAmongMany() {
        reset();
        testSet.add(getData()[0]);
        testSet.add(getData()[1]);
        testSet.add(getData()[2]);
        testSet.add(getData()[1]);
        testSet.add(getData()[0]);
        testSet.add(getData()[3]);
        testSet.add(getData()[4]);
        testSet.add(getData()[1]);
        testSet.add(getData()[4]);
        testSet.add(getData()[0]);
        testSet.remove(getData()[0]);
        assertFalse(testSet.contains(getData()[0]));
        assertTrue(testSet.contains(getData()[1]));
        assertTrue(testSet.contains(getData()[2]));
        assertTrue(testSet.contains(getData()[3]));
        assertTrue(testSet.contains(getData()[4]));
        assertEquals(4, testSet.size());
        assertFalse(testSet.isEmpty());
    }

    @Test
    public void addSameAfterRemove() {
        reset();
        testSet.add(getData()[0]);
        testSet.add(getData()[1]);
        testSet.add(getData()[2]);
        testSet.add(getData()[1]);
        testSet.add(getData()[0]);
        testSet.add(getData()[3]);
        testSet.add(getData()[4]);
        testSet.add(getData()[1]);
        testSet.add(getData()[4]);
        testSet.add(getData()[0]);
        testSet.remove(getData()[0]);
        testSet.add(getData()[0]);
        assertTrue(testSet.contains(getData()[0]));
        assertTrue(testSet.contains(getData()[1]));
        assertTrue(testSet.contains(getData()[2]));
        assertTrue(testSet.contains(getData()[3]));
        assertTrue(testSet.contains(getData()[4]));
        assertEquals(5, testSet.size());
        assertFalse(testSet.isEmpty());
    }

    @Test
    public void addDifferentAfterRemove() {
        reset();
        testSet.add(getData()[0]);
        testSet.add(getData()[1]);
        testSet.add(getData()[2]);
        testSet.add(getData()[1]);
        testSet.add(getData()[0]);
        testSet.add(getData()[3]);
        testSet.add(getData()[4]);
        testSet.add(getData()[1]);
        testSet.add(getData()[4]);
        testSet.add(getData()[0]);
        testSet.remove(getData()[0]);
        testSet.add(getData()[4]);
        testSet.add(getData()[5]);
        assertFalse(testSet.contains(getData()[0]));
        assertTrue(testSet.contains(getData()[1]));
        assertTrue(testSet.contains(getData()[2]));
        assertTrue(testSet.contains(getData()[3]));
        assertTrue(testSet.contains(getData()[4]));
        assertTrue(testSet.contains(getData()[5]));
        assertEquals(5, testSet.size());
        assertFalse(testSet.isEmpty());
    }

    @Test 
    public void removeSomeOfMany() {
        reset();
        populate();
        testSet.remove(getData()[3]);
        testSet.remove(getData()[4]);
        testSet.remove(getData()[9]);
        for (int i = 0; i < getData().length; i++)
            if (i == 3 || i == 4 || i == 9)
                assertFalse(testSet.contains(getData()[i]));
            else
                assertTrue(testSet.contains(getData()[i]));
        assertEquals(getData().length - 3, testSet.size());
    }

    @Test
    public void removeAllOfMany() {
        reset();
        populate();
        for (int i = 0; i < getData().length; i++)
            testSet.remove(getData()[i]);
        for (int i = 0; i < getData().length; i++)
            assertFalse(testSet.contains(getData()[i]));
        assertEquals(0, testSet.size());
    }

    @Test
    public void initialIterator() {
        reset();
        int i = 0;
        for (Iterator<String> it = testSet.iterator(); it.hasNext(); )
            i++;
        assertEquals(0, i);
    }

    @Test
    public void onlySingletonIterator() {
        reset();
        testSet.add(getData()[0]);
        clearMarks();
        for (Iterator<String> it = testSet.iterator(); it.hasNext(); )
            marks[indexForDatum(it.next())]++;
        assertEquals(1, marks[0]);
        for (int i = 1; i < marks.length; i++)
            assertEquals(0, marks[i]);
    }

    @Test
    public void onlyMultipleIterator() {
        reset();
        testSet.add(getData()[0]);
        testSet.add(getData()[0]);
        testSet.add(getData()[0]);
        clearMarks();
        for (Iterator<String> it = testSet.iterator(); it.hasNext(); )
            marks[indexForDatum(it.next())]++;
        assertEquals(1, marks[0]);
        for (int i = 1; i < marks.length; i++)
            assertEquals(0, marks[i]);
    }

    @Test
    public void manyPlannedIterator() {
        reset();
        populate();
        clearMarks();
        for (Iterator<String> it = testSet.iterator(); it.hasNext(); )
            marks[indexForDatum(it.next())]++;
        for (int i = 0; i < marks.length; i++)
            assertEquals(1, marks[i]);
    }

    @Test
    public void manyRandomIterator() {
        reset();
        for (int i = 0; i < 50; i++)
            testSet.add(getData()[rand.nextInt(getData().length)]);
        clearMarks();
        for (Iterator<String> it = testSet.iterator(); it.hasNext(); )
            marks[indexForDatum(it.next())]++;
        for (int i = 0; i < marks.length; i++)
            assertEquals(testSet.contains(getData()[i])? 1 : 0, marks[i]);
    }

    @Test
    public void removeSomeIterator() {
        reset();
        populate();
        testSet.remove(getData()[3]);
        testSet.remove(getData()[4]);
        testSet.remove(getData()[9]);
        clearMarks();
        for (Iterator<String> it = testSet.iterator(); it.hasNext(); )
            marks[indexForDatum(it.next())]++;
        for (int i = 0; i < marks.length; i++)
            if (i == 3 || i == 4 || i == 9)
                assertEquals(0, marks[i]);
            else
                assertEquals(1, marks[i]);
    }

    @Test
    public void removeAllIterator() {
        reset();
        populate();
        for (int i = 0; i < getData().length; i++)
            testSet.remove(getData()[i]);
        int i = 0;
        for (Iterator<String> it = testSet.iterator(); it.hasNext(); i++)
            it.next();
        assertEquals(0, i);
    }

    @Test
    public void addAfterRemoveIterator() {
        reset();
        populate();
        for (int i = 0; i < getData().length; i++)
            testSet.remove(getData()[i]);
        testSet.add(getData()[0]);
        testSet.add(getData()[1]);
        testSet.add(getData()[0]);
        clearMarks();
        for (Iterator<String> it = testSet.iterator(); it.hasNext(); )
            marks[indexForDatum(it.next())]++;
        assertEquals(1, marks[0]);
        assertEquals(1, marks[1]);
        for (int i = 2; i < marks.length; i++)
            assertEquals(0, marks[i]);
        
    }
    
    @Test 
    public void stressTest() {
    	resetInt();
    	final int MAXSIZE =1000;
    	final int TESTS = 10;
        assert(testSetInt.isEmpty());
        
        for (int i = 0; i < TESTS; i++) {
            int currentMaxSize = rand.nextInt(MAXSIZE);
            Integer[] keys = new Integer[currentMaxSize];
            for (int j = 0; j < currentMaxSize; j++) {
                assert(testSetInt.size() == j);
                Integer toAdd = new Integer(j);
                testSetInt.add(toAdd);
                
                assert(testSetInt.size() == j+1);
                
                assert(!testSetInt.contains(new Integer(rand.nextInt()+j)));
            }
            for (int j = 0; j <currentMaxSize; j++){
                testSetInt.add(new Integer(j));
                assert(testSetInt.size() == currentMaxSize);
            }
                
            Iterator<Integer> it = testSetInt.iterator();
            for (int j = 0; j < currentMaxSize; j++) {
                assert(it.hasNext());
                keys[j] = it.next();
                
            }
            for (int j = currentMaxSize-1; j >= 0;j--){
                assert(testSetInt.size() == j+1);
                assert(testSetInt.contains(keys[j]));
                
                testSetInt.remove(keys[j]);
                
                assert(!testSetInt.contains(keys[j]));
                assert(testSetInt.size() == j);
                
            }
            assert(!testSetInt.iterator().hasNext());
        }
    }
    
}
