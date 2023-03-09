package test;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Random;

import org.junit.Test;

import exercises.LinkedList;;

public class LinkedListTestAll {

    protected LinkedList<String> testList;

    private static Random randy = new Random(System.currentTimeMillis());

    
 
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
        
    @Test 
    public void replaceFront() {
        reset();
        populate(8);
        testList.set(0, data[8]);
        assertEquals(8, testList.size());
        assertEquals(data[8], testList.get(0));
        for (int i = 1; i < 8; i++)
            assertEquals(data[i], testList.get(i));
        boolean caught = false;
        try {
            testList.get(-1);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught);
        caught = false;
        try {
            testList.get(12);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught); 
        int i = 0;
        for (Iterator<String> it = testList.iterator(); it.hasNext(); ) {
            if (i == 0)
                assertEquals(data[8], it.next());
            else 
                assertEquals(data[i], it.next());
            i++;
        }
        assertEquals(8, i);
    }

    @Test 
    public void replaceMid() {
        reset();
        populate(8);
        testList.set(5, data[8]);
        assertEquals(8, testList.size());
        assertEquals(data[8], testList.get(5));
        for (int i = 1; i < 8; i++)
            if (i != 5)
                assertEquals(data[i], testList.get(i));
        boolean caught = false;
        try {
            testList.get(-1);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught);
        caught = false;
        try {
            testList.get(12);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught); 
        int i = 0;
        for (Iterator<String> it = testList.iterator(); it.hasNext(); ) {
            if (i == 5)
                assertEquals(data[8], it.next());
            else 
                assertEquals(data[i], it.next());
            i++;
        }
        assertEquals(8, i);
    }

    @Test 
    public void replaceEnd() {
        reset();
        populate(8);
        testList.set(7, data[8]);
        assertEquals(8, testList.size());
        assertEquals(data[8], testList.get(7));
        for (int i = 1; i < 8; i++)
            if (i != 7)
                assertEquals(data[i], testList.get(i));
        boolean caught = false;
        try {
            testList.get(-1);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught);
        caught = false;
        try {
            testList.get(12);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught); 
        int i = 0;
        for (Iterator<String> it = testList.iterator(); it.hasNext(); ) {
            if (i == 7)
                assertEquals(data[8], it.next());
            else 
                assertEquals(data[i], it.next());
            i++;
        }
        assertEquals(8, i);
    }

    @Test
    public void replaceNeg() {
        reset();
        populate(8);
        boolean caught = false;
        try {
            testList.set(-1, data[8]);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught); 
    }

    @Test
    public void replacePosOut() {
        reset();
        populate(8);
        boolean caught = false;
        try {
            testList.set(12, data[8]);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught); 
        
    }
    
    @Test
    public void removeFirst() {
        reset();
        populate(8);
        testList.remove(0);
        assertEquals(7, testList.size());
        for (int i = 0; i < 7; i++)
            assertEquals(data[i+1], testList.get(i));
        int i = 1;
        for (String s : testList)
            assertEquals(data[i++], s);
        boolean caught = false;
        try {
            testList.get(7);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught); 
    }

    @Test
    public void removeMid() {
        reset();
        populate(8);
        testList.remove(4);
        assertEquals(7, testList.size());
        for (int i = 0; i < 7; i++)
            if (i < 4)
                assertEquals(data[i], testList.get(i));
            else
                assertEquals(data[i+1], testList.get(i));
        int i = 0;
        for (String s : testList) {
            assertEquals(data[i++], s);
            if (i == 4) i++;
        }
        boolean caught = false;
        try {
            testList.get(7);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught); 
    }
    
    @Test
    public void removeLast() {
        reset();
        populate(8);
        testList.remove(7);
        assertEquals(7, testList.size());
        for (int i = 0; i < 7; i++)
            assertEquals(data[i], testList.get(i));
        int i = 0;
        for (String s : testList) {
            assertEquals(data[i++], s);
        }
        boolean caught = false;
        try {
            testList.get(7);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught); 
    }

    @Test
    public void removeAll() {
        reset();
        populate(8);
        for (int i = 0; i < 8; i++) {
            assertEquals(8 - i, testList.size());
            testList.remove(randy.nextInt(testList.size()));
        }
        assertEquals(0, testList.size());
    }

    @Test
    public void removeFirstAdd() {
        reset();
        populate(8);
        testList.remove(0);
        testList.add(data[8]);
        assertEquals(8, testList.size());
        for (int i = 0; i < 8; i++)
            assertEquals(data[i+1], testList.get(i));
        int i = 1;
        for (String s : testList)
            assertEquals(data[i++], s);
        boolean caught = false;
        try {
            testList.get(8);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught); 
    }
    
    @Test
    public void removeMidAdd() {
        reset();
        populate(8);
        testList.remove(4);
        testList.add(data[8]);
        assertEquals(8, testList.size());
        for (int i = 0; i < 8; i++)
            if (i < 4)
                assertEquals(data[i], testList.get(i));
            else
                assertEquals(data[i+1], testList.get(i));
        int i = 0;
        for (String s : testList) {
            assertEquals(data[i++], s);
            if (i == 4) i++;
        }
        boolean caught = false;
        try {
            testList.get(8);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught); 
    }
    
    @Test
    public void removeLastAdd() {
        reset();
        populate(8);
        testList.remove(7);
        testList.add(data[8]);
        assertEquals(8, testList.size());
        for (int i = 0; i < 8; i++)
            if (i < 7)
                assertEquals(data[i], testList.get(i));
            else 
                assertEquals(data[i+1], testList.get(i));
        int i = 0;
        for (String s : testList) {
            assertEquals(data[i++], s);
            if (i == 7) i++;
        }
        boolean caught = false;
        try {
            testList.get(8);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught); 
    }

    @Test
    public void removeAllAdd() {
        reset();
        populate(8);
        for (int i = 0; i < 8; i++) {
            assertEquals(8 - i, testList.size());
            testList.remove(randy.nextInt(testList.size()));
        }
        assertEquals(0, testList.size());
        testList.add(data[8]);
        assertEquals(1, testList.size());
        assertEquals(data[8], testList.get(0));
    }

    
    @Test
    public void removeNeg() {
        reset();
        populate(8);
        boolean caught = false;
        try {
            testList.remove(-2);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught); 
    }

    @Test
    public void removeTooBig() {
        reset();
        populate(8);
        boolean caught = false;
        try {
            testList.remove(8);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught); 
    }
    @Test
    public void oneReplacePos() {
        reset();
        populate(1);
        boolean caught = false;
        try {
            testList.set(8, data[8]);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertEquals(data[0], testList.get(0));
        assertTrue(caught);
    }


}
