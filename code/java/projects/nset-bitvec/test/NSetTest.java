package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.Random;

import org.junit.Test;

import adt.NSet;

public abstract class NSetTest {

    protected NSet testSet;

    private static Random randy = new Random(System.currentTimeMillis());
    
    private void populate() {
        for (int i = 0; i < data.length; i++)
            for (int j = i; j < data.length; j++)
                testSet.add(data[j]);
    }

    
    protected abstract void reset();
    
    protected int[] data = 
        { 5, 16, 3, 1, 0, 11, 13, 9, 15, 14, 8, 2, 7, 4, 6, 10, 12 };
    
    // let's hope that the implicit static initializer statements
    // are executed in the order they appear in the file
    protected int[] marks = new int[data.length];
    
    protected void clearMarks() {
        for (int i = 0; i < marks.length; i++)
            marks[i] = 0;
    }

    protected int indexForDatum(int datum) {
        int index = -1;
        for (int i = 0; i < data.length && index == -1; i++)
            if (data[i] == datum)
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
        testSet.add(data[0]);
        assertFalse(testSet.isEmpty());
    }

    @Test
    public void addSize() {
        reset();
        testSet.add(data[0]);
        assertEquals(1, testSet.size());
    }

    @Test
    public void twoAddsUniqueSize() {
        reset();
        testSet.add(data[0]);
        testSet.add(data[1]);
        assertFalse(testSet.isEmpty());
        assertEquals(2, testSet.size());
    }

    @Test
    public void twoAddsIdenticalSize() {
        reset();
        testSet.add(data[0]);
        testSet.add(data[0]);
        assertFalse(testSet.isEmpty());
        assertEquals(1, testSet.size());
    }

    @Test
    public void addsUnique() {
        reset();
        for (int i = 0; i < 8; i++) 
            testSet.add(data[i]);
        assertFalse(testSet.isEmpty());
        assertEquals(8, testSet.size());
    }
    
    @Test
    public void addsIdentical() {
        reset();
        for (int i = 0; i < 8; i++) 
            testSet.add(data[0]);
        assertFalse(testSet.isEmpty());
        assertEquals(1, testSet.size());
    }

    @Test
    public void addsMix() {
        reset();
        for (int i = 0; i < 50; i++)
            testSet.add(data[randy.nextInt(data.length)]);
        assertFalse(testSet.isEmpty());
        //assertEquals(data.length, testSet.size());
    }

    @Test
    public void addsLots() {
        reset();
        populate();
        assertFalse(testSet.isEmpty());
        assertEquals(data.length, testSet.size());
    }
    
    @Test
    public void containsInitial() {
        reset();
        assertFalse(testSet.contains(data[0]));
    }
    
    @Test
    public void containsOne() {
        reset();
        testSet.add(data[0]);
        assertTrue(testSet.contains(data[0]));
    }

    @Test
    public void containsTwoUnique() {
        reset();
        testSet.add(data[0]);
        testSet.add(data[1]);
        assertTrue(testSet.contains(data[0]));
        assertTrue(testSet.contains(data[1]));
    }

    @Test
    public void containsTwoIdentical() {
        reset();
        testSet.add(data[0]);
        testSet.add(data[0]);
        assertTrue(testSet.contains(data[0]));
    }

    
    @Test
    public void containsLots() {
        reset();
        populate();
        for (int i = 0; i < data.length; i++)
            assertTrue(testSet.contains(data[i]));
    }

    @Test
    public void removeInitial() {
        reset();
        testSet.remove(data[0]);
        assertFalse(testSet.contains(data[0]));
        assertEquals(0, testSet.size());
        assertTrue(testSet.isEmpty());
    }

    @Test
    public void removeSpurious() {
        reset();
        testSet.add(data[0]);
        testSet.add(data[1]);
        testSet.add(data[0]);
        testSet.remove(data[2]);
        assertTrue(testSet.contains(data[0]));
        assertTrue(testSet.contains(data[1]));
        assertFalse(testSet.contains(data[2]));
        assertEquals(2, testSet.size());
        assertFalse(testSet.isEmpty());
    }

    @Test
    public void removeOnlySingleton() {
        reset();
        testSet.add(data[0]);
        testSet.remove(data[0]);
        assertFalse(testSet.contains(data[0]));
        assertEquals(0, testSet.size());
        assertTrue(testSet.isEmpty());
    }

    @Test
    public void removeOnlyMultiple() {
        reset();
        testSet.add(data[0]);
        testSet.add(data[0]);
        testSet.add(data[0]);
        testSet.remove(data[0]);
        assertFalse(testSet.contains(data[0]));
        assertEquals(0, testSet.size());
        assertTrue(testSet.isEmpty());
    }
        
    @Test
    public void removeSingletonAmongMany() {
        reset();
        testSet.add(data[0]);
        testSet.add(data[1]);
        testSet.add(data[2]);
        testSet.add(data[1]);
        testSet.add(data[3]);
        testSet.add(data[4]);
        testSet.add(data[1]);
        testSet.add(data[4]);
        testSet.remove(data[0]);
        assertFalse(testSet.contains(data[0]));
        assertTrue(testSet.contains(data[1]));
        assertTrue(testSet.contains(data[2]));
        assertTrue(testSet.contains(data[3]));
        assertTrue(testSet.contains(data[4]));
        assertEquals(4, testSet.size());
        assertFalse(testSet.isEmpty());
    }

    @Test
    public void removeMultipleAmongMany() {
        reset();
        testSet.add(data[0]);
        testSet.add(data[1]);
        testSet.add(data[2]);
        testSet.add(data[1]);
        testSet.add(data[0]);
        testSet.add(data[3]);
        testSet.add(data[4]);
        testSet.add(data[1]);
        testSet.add(data[4]);
        testSet.add(data[0]);
        testSet.remove(data[0]);
        assertFalse(testSet.contains(data[0]));
        assertTrue(testSet.contains(data[1]));
        assertTrue(testSet.contains(data[2]));
        assertTrue(testSet.contains(data[3]));
        assertTrue(testSet.contains(data[4]));
        assertEquals(4, testSet.size());
        assertFalse(testSet.isEmpty());
    }

    @Test
    public void addSameAfterRemove() {
        reset();
        testSet.add(data[0]);
        testSet.add(data[1]);
        testSet.add(data[2]);
        testSet.add(data[1]);
        testSet.add(data[0]);
        testSet.add(data[3]);
        testSet.add(data[4]);
        testSet.add(data[1]);
        testSet.add(data[4]);
        testSet.add(data[0]);
        testSet.remove(data[0]);
        testSet.add(data[0]);
        assertTrue(testSet.contains(data[0]));
        assertTrue(testSet.contains(data[1]));
        assertTrue(testSet.contains(data[2]));
        assertTrue(testSet.contains(data[3]));
        assertTrue(testSet.contains(data[4]));
        assertEquals(5, testSet.size());
        assertFalse(testSet.isEmpty());
    }

    @Test
    public void addDifferentAfterRemove() {
        reset();
        testSet.add(data[0]);
        testSet.add(data[1]);
        testSet.add(data[2]);
        testSet.add(data[1]);
        testSet.add(data[0]);
        testSet.add(data[3]);
        testSet.add(data[4]);
        testSet.add(data[1]);
        testSet.add(data[4]);
        testSet.add(data[0]);
        testSet.remove(data[0]);
        testSet.add(data[4]);
        testSet.add(data[5]);
        assertFalse(testSet.contains(data[0]));
        assertTrue(testSet.contains(data[1]));
        assertTrue(testSet.contains(data[2]));
        assertTrue(testSet.contains(data[3]));
        assertTrue(testSet.contains(data[4]));
        assertTrue(testSet.contains(data[5]));
        assertEquals(5, testSet.size());
        assertFalse(testSet.isEmpty());
    }

    @Test 
    public void removeSomeOfMany() {
        reset();
        populate();
        testSet.remove(data[3]);
        testSet.remove(data[4]);
        testSet.remove(data[9]);
        for (int i = 0; i < data.length; i++)
            if (i == 3 || i == 4 || i == 9)
                assertFalse(testSet.contains(data[i]));
            else
                assertTrue(testSet.contains(data[i]));
        assertEquals(data.length - 3, testSet.size());
    }

    @Test
    public void removeAllOfMany() {
        reset();
        populate();
        for (int i = 0; i < data.length; i++)
            testSet.remove(data[i]);
        for (int i = 0; i < data.length; i++)
            assertFalse(testSet.contains(data[i]));
        assertEquals(0, testSet.size());
    }

    @Test
    public void initialIterator() {
        reset();
        int i = 0;
        for (Iterator<Integer> it = testSet.iterator(); it.hasNext(); )
            i++;
        assertEquals(0, i);
    }

    @Test
    public void onlySingletonIterator() {
        reset();
        testSet.add(data[0]);
        clearMarks();
        for (Iterator<Integer> it = testSet.iterator(); it.hasNext(); )
            marks[indexForDatum(it.next())]++;
        assertEquals(1, marks[0]);
        for (int i = 1; i < marks.length; i++)
            assertEquals(0, marks[i]);
    }

    @Test
    public void onlyMultipleIterator() {
        reset();
        testSet.add(data[0]);
        testSet.add(data[0]);
        testSet.add(data[0]);
        clearMarks();
        for (Iterator<Integer> it = testSet.iterator(); it.hasNext(); )
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
        for (Iterator<Integer> it = testSet.iterator(); it.hasNext(); )
            marks[indexForDatum(it.next())]++;
        for (int i = 0; i < marks.length; i++)
            assertEquals(1, marks[i]);
    }

    @Test
    public void manyRandomIterator() {
        reset();
        for (int i = 0; i < 50; i++)
            testSet.add(data[randy.nextInt(data.length)]);
        clearMarks();
        for (Iterator<Integer> it = testSet.iterator(); it.hasNext(); )
            marks[indexForDatum(it.next())]++;
        for (int i = 0; i < marks.length; i++)
            assertEquals(testSet.contains(data[i])? 1 : 0, marks[i]);
    }

    @Test
    public void removeSomeIterator() {
        reset();
        populate();
        testSet.remove(data[3]);
        testSet.remove(data[4]);
        testSet.remove(data[9]);
        clearMarks();
        for (Iterator<Integer> it = testSet.iterator(); it.hasNext(); )
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
        for (int i = 0; i < data.length; i++)
            testSet.remove(data[i]);
        int i = 0;
        for (Iterator<Integer> it = testSet.iterator(); it.hasNext(); i++)
            it.next();
        assertEquals(0, i);
    }

    @Test
    public void addAfterRemoveIterator() {
        reset();
        populate();
        for (int i = 0; i < data.length; i++)
            testSet.remove(data[i]);
        testSet.add(data[0]);
        testSet.add(data[1]);
        testSet.add(data[0]);
        clearMarks();
        for (Iterator<Integer> it = testSet.iterator(); it.hasNext(); )
            marks[indexForDatum(it.next())]++;
        assertEquals(1, marks[0]);
        assertEquals(1, marks[1]);
        for (int i = 2; i < marks.length; i++)
            assertEquals(0, marks[i]);
        
    }
    
    @Test
    public void emptyComplement() {
        reset();
        NSet comp = testSet.complement();
        clearMarks();
        for (Iterator<Integer> it = testSet.iterator(); it.hasNext(); )
            marks[indexForDatum(it.next())]++;
        for (int i =0; i < marks.length; i++)
            assertEquals(0, marks[i]);
        clearMarks();
        for (Iterator<Integer> it = comp.iterator(); it.hasNext(); )
            marks[indexForDatum(it.next())]++;
        for (int i =0; i < marks.length; i++) 
            assertEquals(1, marks[i]);
    }

    @Test
    public void fullComplement() {
        reset();
        populate();
        NSet comp = testSet.complement();
        clearMarks();
        for (Iterator<Integer> it = testSet.iterator(); it.hasNext(); )
            marks[indexForDatum(it.next())]++;
        for (int i =0; i < marks.length; i++)
            assertEquals(1, marks[i]);
        clearMarks();
        for (Iterator<Integer> it = comp.iterator(); it.hasNext(); )
            marks[indexForDatum(it.next())]++;
        for (int i =0; i < marks.length; i++)
            assertEquals(0, marks[i]);
        
    }
    
    @Test
    public void medComplement() {
        reset();
        for (int i = 0; i < 6; i++)
            testSet.add(data[i]);
        NSet comp = testSet.complement();
        clearMarks();
        for (Iterator<Integer> it = testSet.iterator(); it.hasNext(); )
            marks[indexForDatum(it.next())]++;
        for (int i = 0; i < 6; i++)
            assertEquals(1, marks[i]);
        for (int i = 6; i < marks.length; i++)
            assertEquals(0, marks[i]);
        clearMarks();
        for (Iterator<Integer> it = comp.iterator(); it.hasNext(); )
            marks[indexForDatum(it.next())]++;
        for (int i = 0; i < 6; i++)
            assertEquals(0, marks[i]);
        for (int i = 6; i < marks.length; i++)
            assertEquals(1, marks[i]);
    }

    @Test
    public void medUnion() {
        reset();
        NSet other = testSet;
        reset();
        for (int i = 0; i < 6; i++)
            testSet.add(data[i]);
        for (int i = 4; i < 13; i++)
            other.add(data[i]);
        NSet result = testSet.union(other);
        clearMarks();
        for (Iterator<Integer> it = testSet.iterator(); it.hasNext(); )
            marks[indexForDatum(it.next())]++;
        for (int i = 0; i < 6; i++)
            assertEquals(1, marks[i]);
        for (int i = 6; i < marks.length; i++)
            assertEquals(0, marks[i]);
        clearMarks();
        for (Iterator<Integer> it = other.iterator(); it.hasNext(); )
            marks[indexForDatum(it.next())]++;
        for (int i = 0; i < 4; i++)
            assertEquals(0, marks[i]);
        for (int i = 4; i < 13; i++)
            assertEquals(1, marks[i]);
        for (int i = 13; i < marks.length; i++)
            assertEquals(0, marks[i]);
        clearMarks();
        for (Iterator<Integer> it = result.iterator(); it.hasNext(); )
            marks[indexForDatum(it.next())]++;
        for (int i = 0; i < 13; i++)
            assertEquals(1, marks[i]);
        for (int i = 13; i < marks.length; i++)
            assertEquals(0, marks[i]);
    }

    @Test
    public void medIntersection() {
        reset();
        NSet other = testSet;
        reset();
        for (int i = 0; i < 6; i++)
            testSet.add(data[i]);
        for (int i = 4; i < 13; i++)
            other.add(data[i]);
        NSet result = testSet.intersection(other);
        clearMarks();
        for (Iterator<Integer> it = testSet.iterator(); it.hasNext(); ) 
            marks[indexForDatum(it.next())]++;
        for (int i = 0; i < 6; i++)
            assertEquals(1, marks[i]);
        for (int i = 6; i < marks.length; i++)
            assertEquals(0, marks[i]);
        clearMarks();
        for (Iterator<Integer> it = other.iterator(); it.hasNext(); )
            marks[indexForDatum(it.next())]++;
        for (int i = 0; i < 4; i++)
            assertEquals(0, marks[i]);
        for (int i = 4; i < 13; i++)
            assertEquals(1, marks[i]);
        for (int i = 13; i < marks.length; i++)
            assertEquals(0, marks[i]);
        clearMarks();
        for (Iterator<Integer> it = result.iterator(); it.hasNext(); )
            marks[indexForDatum(it.next())]++;
        for (int i = 0; i < 4; i++) 
            assertEquals(0, marks[i]);
        for (int i = 4; i < 6; i++)
            assertEquals(1, marks[i]);
        for (int i = 6; i < marks.length; i++)
            assertEquals(0, marks[i]);
    }

    @Test
    public void medDifference() {
        reset();
        NSet other = testSet;
        reset();
        for (int i = 0; i < 6; i++)
            testSet.add(data[i]);
        for (int i = 4; i < 13; i++)
            other.add(data[i]);
        NSet result = testSet.difference(other);
        clearMarks();
        for (Iterator<Integer> it = testSet.iterator(); it.hasNext(); )
            marks[indexForDatum(it.next())]++;
        for (int i = 0; i < 6; i++)
            assertEquals(1, marks[i]);
        for (int i = 6; i < marks.length; i++)
            assertEquals(0, marks[i]);
        clearMarks();
        for (Iterator<Integer> it = other.iterator(); it.hasNext(); )
            marks[indexForDatum(it.next())]++;
        for (int i = 0; i < 4; i++)
            assertEquals(0, marks[i]);
        for (int i = 4; i < 13; i++)
            assertEquals(1, marks[i]);
        for (int i = 13; i < marks.length; i++)
            assertEquals(0, marks[i]);
        clearMarks();
        for (Iterator<Integer> it = result.iterator(); it.hasNext(); )
            marks[indexForDatum(it.next())]++;
        for (int i = 0; i < 4; i++)
            assertEquals(1, marks[i]);
        for (int i = 4; i < marks.length; i++)
            assertEquals(0, marks[i]);
    }

    @Test
    public void isEmptyMSB() {
      reset();
      testSet.add(7);
      assertFalse(testSet.isEmpty());
    }

    
}
