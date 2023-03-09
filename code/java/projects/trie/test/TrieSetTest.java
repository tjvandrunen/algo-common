package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

import adt.Set;

public abstract class TrieSetTest extends CollectionTest{
    
    protected String[] trieBreaker = 
        { "CONSTANCE", "HELEN", "JUSTIN", "JON", "JOHN", "CONSTANTIUS",
            "HELENA", "HELLA", "JONATHAN", "CONSTANTINE", "JUSTINIAN",
            "CLEMENS", "HEROD", "ANN", "JOHANA", "JUSTINIANUS",
            "CONSTANTINUS", "ELLEN", "ELAINE", "ELLIE", "ELLA",
            "HERODIAS", "HERODIAN", "JOHNA", "ANNALISE", "ANNETTE",
            "ANNIKA", "CLEMENT", "CAESAR", "AUGUSTUS", "ANNE", "ANNMARIE",
            "JUSTINMARTYR", "JOHNBAPTIST", "ANNIE", "ANNA", "CAESARION",
            "CAESAREA", "AUGUSTA", "ANNMARGARET", "AUGUST", "AUGUSTINE",
            "CLEMENZO", "JONATHANIAN", "CAESARINA", "AUGUSTINUS", "CONSTANS",
            "HELENE" };
   
    @Override
    protected String[] getData() {
        return trieBreaker;
    }
    
    protected Set<String> testSet;
    
    protected void populate() {
        for (int i = 0; i < getData().length; i++)
            testSet.add(getData()[i]);
    }

    
    protected abstract void reset();


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


}
