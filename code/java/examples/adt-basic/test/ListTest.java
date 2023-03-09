package test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import adt.List;

public abstract class ListTest extends CollectionTest{


    protected List<String> testList;
    protected List<Integer> testListInt;
    
    /**
     * Rest testList by instantiating a class that implements
     * HomemadeList (which class to instantiate is deferred to
     * the child classes).
     */
    protected abstract void reset();
    protected abstract void resetInt();
    
    protected void populate(int size) {
        for (int i = 0; i < size && i < getData().length; i++) 
            testList.add(getData()[i]);
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
        assertEquals(getData()[0], testList.get(0));
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
            assertEquals(getData()[i], it.next());
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
            assertEquals(getData()[i], testList.get(i));
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

    public void fillIterator() {
        reset();
        populate(8);
        int i = 0;
        for (Iterator<String> it = testList.iterator(); it.hasNext(); ) {
            assertEquals(getData()[i], it.next());
            i++;
        }
        assertEquals(8, i);
    }
    
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

        assertEquals(getData()[0], testList.get(0));
        
        caught = false;
        try {
            testList.get(1);
        }catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught);
        
    }
    
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
            assertEquals(getData()[i], testList.get(i));
            
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
        testList.set(0, getData()[8]);
        assertEquals(8, testList.size());
        assertEquals(getData()[8], testList.get(0));
        for (int i = 1; i < 8; i++)
            assertEquals(getData()[i], testList.get(i));
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
                assertEquals(getData()[8], it.next());
            else 
                assertEquals(getData()[i], it.next());
            i++;
        }
        assertEquals(8, i);
    }

    @Test 
    public void replaceMid() {
        reset();
        populate(8);
        testList.set(5, getData()[8]);
        assertEquals(8, testList.size());
        assertEquals(getData()[8], testList.get(5));
        for (int i = 1; i < 8; i++)
            if (i != 5)
                assertEquals(getData()[i], testList.get(i));
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
                assertEquals(getData()[8], it.next());
            else 
                assertEquals(getData()[i], it.next());
            i++;
        }
        assertEquals(8, i);
    }

    @Test 
    public void replaceEnd() {
        reset();
        populate(8);
        testList.set(7, getData()[8]);
        assertEquals(8, testList.size());
        assertEquals(getData()[8], testList.get(7));
        for (int i = 1; i < 8; i++)
            if (i != 7)
                assertEquals(getData()[i], testList.get(i));
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
                assertEquals(getData()[8], it.next());
            else 
                assertEquals(getData()[i], it.next());
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
            testList.set(-1, getData()[8]);
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
            testList.set(12, getData()[8]);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught); 
        
    }
    
    @Test
    public void removeFirst() {
        reset();
        populate(8);
        String removed = testList.remove(0);
        assertEquals(getData()[0],removed);
        assertEquals(7, testList.size());
        for (int i = 0; i < 7; i++)
            assertEquals(getData()[i+1], testList.get(i));
        int i = 1;
        for (String s : testList)
            assertEquals(getData()[i++], s);
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
        String removed = testList.remove(4);
        assertEquals(getData()[4],removed);
        assertEquals(7, testList.size());
        for (int i = 0; i < 7; i++)
            if (i < 4)
                assertEquals(getData()[i], testList.get(i));
            else
                assertEquals(getData()[i+1], testList.get(i));
        int i = 0;
        for (String s : testList) {
            assertEquals(getData()[i++], s);
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
        String removed = testList.remove(7);
        assertEquals(getData()[7],removed);
        assertEquals(7, testList.size());
        for (int i = 0; i < 7; i++)
            assertEquals(getData()[i], testList.get(i));
        int i = 0;
        for (String s : testList) {
            assertEquals(getData()[i++], s);
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
            testList.remove(rand.nextInt(testList.size()));
        }
        assertEquals(0, testList.size());
    }

    @Test
    public void removeFirstAdd() {
        reset();
        populate(8);
        testList.remove(0);
        testList.add(getData()[8]);
        assertEquals(8, testList.size());
        for (int i = 0; i < 8; i++)
            assertEquals(getData()[i+1], testList.get(i));
        int i = 1;
        for (String s : testList)
            assertEquals(getData()[i++], s);
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
        testList.add(getData()[8]);
        assertEquals(8, testList.size());
        for (int i = 0; i < 8; i++)
            if (i < 4)
                assertEquals(getData()[i], testList.get(i));
            else
                assertEquals(getData()[i+1], testList.get(i));
        int i = 0;
        for (String s : testList) {
            assertEquals(getData()[i++], s);
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
        testList.add(getData()[8]);
        assertEquals(8, testList.size());
        for (int i = 0; i < 8; i++)
            if (i < 7)
                assertEquals(getData()[i], testList.get(i));
            else 
                assertEquals(getData()[i+1], testList.get(i));
        int i = 0;
        for (String s : testList) {
            assertEquals(getData()[i++], s);
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
            testList.remove(rand.nextInt(testList.size()));
        }
        assertEquals(0, testList.size());
        testList.add(getData()[8]);
        assertEquals(1, testList.size());
        assertEquals(getData()[8], testList.get(0));
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
    public void insertEmpty() {
        reset();
        testList.insert(0, getData()[2]);
        assertEquals(testList.size(), 1);
        assertEquals(testList.get(0), getData()[2]);
    }
    
    @Test
    public void insertFront() {
        reset();
        populate(8);
        testList.insert(0, getData()[8]);
        assertEquals(testList.size(), 9);
        assertEquals(testList.get(0), getData()[8]);
        for (int i = 0; i < 8; i++)
            assertEquals(testList.get(i+1), getData()[i]);
    }

    @Test
    public void insertMiddle() {
        reset();
        populate(8);
        testList.insert(4, getData()[8]);
        assertEquals(testList.size(), 9);
        for (int i = 0; i < 4; i++)
            assertEquals(testList.get(i), getData()[i]);
        assertEquals(testList.get(4), getData()[8]);
        for (int i = 5; i < 9; i++)
            assertEquals(testList.get(i), getData()[i-1]);
    }
    
    @Test
    public void insertEnd() {
        reset();
        populate(8);
        testList.insert(8, getData()[8]);
        assertEquals(testList.size(), 9);
        for (int i = 0; i < 8; i++)
            assertEquals(testList.get(i), getData()[i]);
        assertEquals(testList.get(8), getData()[8]);
    }
    
    @Test
    public void insertOOBLow() {
        reset();
        populate(8);
        boolean caught = false;
        try {
            testList.insert(-1, getData()[8]);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught); 
    }

    @Test
    public void insertOOBHigh() {
        reset();
        populate(8);
        boolean caught = false;
        try {
            testList.insert(9, getData()[8]);
        } catch (IndexOutOfBoundsException ioobe) {
            caught = true;
        }
        assertTrue(caught); 
    }
    @Test
    public void stressTest() {
    	resetInt();
    	final int MAX_SIZE = 1000;
    	final int NUM_TESTS = 10;
    	
    	for (int test = 0; test < NUM_TESTS; test++) {
    		int n = rand.nextInt(MAX_SIZE);
    		int[] indices = new int[n];
    		int index = 0;
    		for (int i = 0; i < n; i++) {
    			assertEquals(i,testListInt.size());
    			testListInt.insert(index, new Integer(i));
    			indices[i] = index;
    			for (int j =0; j < i; j++) {
    				if (indices[j] >= index)
    					indices[j]++;
    			}
    			index = rand.nextInt(i+2);
    		}
    		assertEquals(n,testListInt.size());
    		
    		
    		for (int i = 0; i < n; i++)
    			assertEquals(new Integer(i),testListInt.get(indices[i]));
    		assertEquals(n,testListInt.size());
    		
    		
    		int[] oldIndices = new int[n];
    		for (int i = 0; i < n; i++)
    			oldIndices[i] = indices[i];
    		for (int i = 0; i < n; i++) {
    			testListInt.set(oldIndices[i],new Integer(n-i-1));
    			indices[n-i-1] = oldIndices[i];
    		}
    		assertEquals(n,testListInt.size());
    		
    		
    		for (int i = 0; i < n; i += 2) {
    			assertEquals(n-i/2,testListInt.size());
    			assertEquals(new Integer(i),testListInt.remove(indices[i]));
    			for (int j = 0; j < n; j++) {
    				if (indices[j] > indices[i])
    				indices[j]--;
    			}
    			indices[i] = -1;
    		}
    		assertEquals( n - (int)Math.ceil(n/2.0),testListInt.size());
    		
    		for (int i = 1; i < n; i += 2) {
    			assertEquals(testListInt.size(),n - (int)Math.ceil(n/2.0) -i/2);
    			assertEquals(new Integer(i),testListInt.remove(indices[i]));
    			for (int j = 0; j < n; j++) {
    				if (indices[j] > indices[i])
    				indices[j]--;
    			}
    			indices[i] = -1;
    		}
    		assertEquals(0,testListInt.size());	
    	}
    	
    }
    
    
}
