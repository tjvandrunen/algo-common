package test;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Random;

import org.junit.Test;

import exercises.LinkedList;

public class LinkedListTestA {

    protected LinkedList<String> testList;

    protected static Random randy = new Random(System.currentTimeMillis());

    
 
    protected void reset() {
        testList = new LinkedList<String>();
    }
    
    protected String[] data = 
        { "Augustus", "Tiberius", "Caligula", "Claudius", "Nero",
            "Galba", "Otho", "Vitellius", "Vespasian", "Titus",
            "Domitian", "Nerva", "Trajan", "Hadrian", "Antoninus Pius",
            "Marcus Aurelius", "Commodus" };
    
    protected void populate(int size) {
        for (int i = 0; i < size && i < data.length; i++)
            testList.add(data[i]);
    }
    
    
    @Test
    public void emptySize() {
        reset();
        assertEquals(0, testList.size());
    }

    @Test
    public void emptyIndexNeg() {
        reset();
        boolean caught = false;
        try {
            testList.get(-1);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught);
    }

    @Test
    public void emptyIndexZero() {
        reset();
        boolean caught = false;
        try {
            testList.get(0);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught);
    }
    
    @Test
    public void emptyIndexPos() {
        reset();
        boolean caught = false;
        try {
            testList.get(5);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught);
    }
    
    @Test
    public void emptyIterator() {
        reset();
        int i = 0;
        for (Iterator<String> it = testList.iterator(); it.hasNext(); )
            i++;
        assertEquals(0, i);
    }
    
    @Test
    public void oneSize() {
        reset();    
        populate(1);
        assertEquals(1, testList.size());
    }
    
    @Test
    public void oneIndexNeg() {
        reset();    
        populate(1);
        boolean caught = false;
        try {
            testList.get(-1);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught);
    }

    @Test
    public void oneIndexZero() {
        reset();
        populate(1);
        assertEquals(data[0], testList.get(0));
    }
    
    @Test
    public void oneIndexPos() {
        reset();
        populate(1);
        boolean caught = false;
        try {
            testList.get(5);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught);
    }

    @Test
    public void oneIterator() {
        reset();
        populate(1);
        int i = 0;
        for (Iterator<String> it = testList.iterator(); it.hasNext(); ) {
            assertEquals(data[i], it.next());
            i++;
        }
        assertEquals(1, i);
    }
    
    @Test
    public void fillSize() {
        reset();
        populate(8);
        assertEquals(8, testList.size());
    }
    
    @Test 
    public void fillIndexNeg() {
        reset();
        populate(8);
        boolean caught = false;
        try {
            testList.get(-1);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught);
    }
    
    @Test 
    public void fillIndexValid() {
        reset();
        populate(8);
        for (int i = 0; i < 8; i++)
            assertEquals(data[i], testList.get(i));
    }
    
    @Test 
    public void fillIndexPosOut() {
        reset();
        populate(8);
        boolean caught = false;
        try {
            testList.get(10);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught);
    }

    @Test 
   public void fillIterator() {
        reset();
        populate(8);
        int i = 0;
        for (Iterator<String> it = testList.iterator(); it.hasNext(); ) {
            assertEquals(data[i], it.next());
            i++;
        }
        assertEquals(8, i);
    }
    
    @Test 
    public void getEmpty() {
        reset();
        boolean caught = false;
        try {
            testList.get(-1);
        }catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught);
        caught = false;
        try {
            testList.get(0);
        }catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught);
        
        caught = false;
        try {
            testList.get(1);
        }catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught);
    }

    @Test 
   public void getOne() {
        reset();
        populate(1);
        boolean caught = false;
        try {
            testList.get(-1);
        }catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught);

        assertEquals(data[0], testList.get(0));
        
        caught = false;
        try {
            testList.get(1);
        }catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught);
        
    }
    
    @Test 
    public void getFill() {
        reset();
        populate(8);
        boolean caught = false;
        try {
            testList.get(-1);
        }catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught);

        for (int i = 0; i < 8; i++)
            assertEquals(data[i], testList.get(i));
            
        caught = false;
        try {
            testList.get(8);
        }catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught);

        
    }
        

}
