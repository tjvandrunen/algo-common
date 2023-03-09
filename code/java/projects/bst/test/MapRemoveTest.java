package test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import org.junit.Test;

public abstract class MapRemoveTest extends MapStressTest{
    /* Testing remove */
    @Test
    public void emptyRemove() {
        reset();
        testMap.remove("Alaska");
        for (int i = 0; i < getData().length; i += 2)
            assertFalse(testMap.containsKey(getData()[i]));
        for (int i = 0; i < otherData.length; i++)
            assertFalse(testMap.containsKey(otherData[i]));
    }

    @Test
    public void populatedRemove() {
        reset();
        populate(getData().length / 2);
        testMap.remove("Alaska");
        assertFalse(testMap.containsKey("Alaska"));
        for (int i = 0; i < getData().length; i += 2)
            if (getData()[i].equals("Alaska"))
                assertEquals(null, testMap.get(getData()[i]));
            else
                assertEquals(getData()[i+1], testMap.get(getData()[i]));
        for (int i = 0; i < otherData.length; i++)
            assertEquals(null, testMap.get(otherData[i]));
    }
    
    @Test
    public void populatedRemoveSpurious() {
        reset();
        populate(getData().length / 2);
        testMap.remove("Wisconsin");
        for (int i = 0; i < getData().length; i += 2)
            assertEquals(getData()[i+1], testMap.get(getData()[i]));
        for (int i = 0; i < otherData.length; i++)
            assertEquals(null, testMap.get(otherData[i]));

    }

    @Test
    public void removeMiddleAndEnd() {
        reset();
        populate(4);
        testMap.remove("Texas");
        testMap.remove("New Jersey");
        assertTrue(testMap.containsKey(getData()[0]));
        assertFalse(testMap.containsKey(getData()[2]));
        assertTrue(testMap.containsKey(getData()[4]));
        assertFalse(testMap.containsKey(getData()[6]));
        
    }



    @Test
    public void removeIterator() {
        reset();
        populate(getData().length / 2);
        testMap.remove("Alaska");
        boolean[] founds = new boolean[(getData().length / 2) - 1];
        for (int i = 0; i < founds.length; i++)
            founds[i] = false;
        for (Iterator<String> it = testMap.iterator(); it.hasNext(); ) {
            String key = it.next();
            boolean foundIt = false;
            for (int i = 0; i < founds.length && ! foundIt; i++) {
                if (getData()[2 * i].equals(key)) {
                    // key returned from iterator has right value in map
                    assertEquals(getData()[2 * i+1], testMap.get(key));
                    // iterator hasn't returned this key before
                    assertFalse("Repeated key: " + key, founds[i]);
                    founds[i] = true;
                    foundIt = true;
                }
            }
            // key returned by iterator was a real key (it was found in raw getData())
            assertTrue("Extraneous key: " + key, foundIt);
        }
        for (int i = 0; i < founds.length; i++)
            assertTrue("Missed key: " + getData()[i*2], founds[i]);

    }
    
    @Test
    public void removeSomeIterator() {
        reset();
        populate(getData().length / 2);
        testMap.remove(getData()[4]);
        for (int i = 0; i < getData().length; i+=2)
            if (i == 4)
                assertFalse(testMap.containsKey(getData()[i]));
            else
                assertTrue(testMap.containsKey(getData()[i]));
    }     

    @Test
    public void removeTRBRC2() {
        reset();
        testMap.put("B", "X");
        testMap.put("A", "X");
        testMap.put("C", "X");
 
        // to get the right colors, first add a level of
        // red leaves
        testMap.put("@", "X");
        testMap.put("AB", "X");
        testMap.put("BC", "X");
        testMap.put("Z", "X");
        testMap.remove("@");
        testMap.remove("AB");
        testMap.remove("BC");
        testMap.remove("Z");

        
        testMap.remove("A");
        
   }
    
    @Test
    public void removeTRBRC3() {
        reset();
        // main part of the example
        testMap.put("D", "D");
        testMap.put("B", "D");
        testMap.put("H", "D");
        testMap.put("A", "D");
        testMap.put("C", "D");
        testMap.put("F", "D");
        testMap.put("I", "D");
        testMap.put("E", "D");
        testMap.put("G", "D");
        // to get the right colors, first add a level of
        // red leaves
        testMap.put("@", "X");
        testMap.put("AB", "X");
        testMap.put("BC", "X");
        testMap.put("CD", "X");
        testMap.put("DE", "X");
        testMap.put("EF", "X");
        testMap.put("FG", "X");
        testMap.put("GH", "X");
        testMap.put("HI", "X");
        testMap.put("Z", "X");
       
        // remove the red leaves
        testMap.remove("@");
        testMap.remove("AB");
        testMap.remove("BC");
        testMap.remove("CD");

       
       testMap.remove("DE");
        testMap.remove("EF");
        testMap.remove("FG");
        testMap.remove("GH");
        testMap.remove("HI");
        testMap.remove("Z");

        
        // remove a key that then induces a case 2 followed by a case 3
        testMap.remove("A");
        
        
    }
    
    @Test
    public void TRBRC4() {
        reset();
        // main part of the example
        testMap.put("D", "D");
        testMap.put("B", "D");
        testMap.put("H", "D");
        testMap.put("A", "D");
        testMap.put("C", "D");
        testMap.put("F", "D");
        testMap.put("J", "D");
        
        
        testMap.put("@", "X");
        testMap.put("AB", "X");
        testMap.put("BC", "X");
        testMap.put("CD", "X");
        testMap.put("E", "D");
        testMap.put("G", "D");
        testMap.put("I", "D");
        testMap.put("K", "D");

 
        
        // to get the right colors, first add a level of
        // red leaves
        testMap.put("DE", "X");
        testMap.put("EF", "X");
        testMap.put("FG", "X");
        testMap.put("GH", "X");
        testMap.put("HI", "X");
        testMap.put("IJ", "X");
        testMap.put("JK", "X");
        testMap.put("Z", "X");

        // remove the red leaves
        testMap.remove("@");
        testMap.remove("AB");
        testMap.remove("BC");
        testMap.remove("CD");
        testMap.remove("DE");
        testMap.remove("EF");
        testMap.remove("FG");
        testMap.remove("GH");
        testMap.remove("HI");
        testMap.remove("IJ");
        testMap.remove("JK");
        testMap.remove("Z");


        testMap.remove("A");
        
        
    }
    
    @Test
    public void TRBRCX() {
        reset();
        testMap.put("H", "D");
       testMap.put("D", "D");
       testMap.put("J", "D");
        testMap.put("B", "D");
        testMap.put("F", "D");
        testMap.put("I", "D");
        testMap.put("K", "D");
        testMap.put("E", "D");
        testMap.put("G", "D");
        testMap.put("HI", "X");
        testMap.put("IJ", "X");
        testMap.put("JK", "X");
        testMap.put("Z", "X");
        testMap.put("DE", "X");
        testMap.put("EF", "X");
        testMap.put("FG", "X");
        testMap.put("GH", "X");      
        testMap.put("A", "D");
        testMap.put("C", "D");
        testMap.put("@", "X");
        testMap.put("AB", "X");
        testMap.put("BC", "X");
        testMap.put("CD", "X");

        // remove the red leaves
        testMap.remove("@");
        testMap.remove("AB");
        testMap.remove("BC");
        testMap.remove("CD");
        testMap.remove("DE");
        testMap.remove("EF");
        testMap.remove("FG");
        testMap.remove("GH");
        testMap.remove("HI");
        testMap.remove("IJ");
        testMap.remove("JK");
        testMap.remove("Z");

        testMap.remove("A");
        testMap.remove("C");
        testMap.put("A", "D");
        testMap.put("C", "D");
      
       
     
        
        testMap.remove("A");
       
  
        testMap.remove("B");
             
  
        testMap.remove("C");
       
         
    }
    
    @Test
    public void TRBRCRightLeftRed() {
        reset();
        testMap.put("E", "D");
        testMap.put("B", "D");
        testMap.put("F", "D");
        testMap.put("A", "D");
        testMap.put("D", "D");
        testMap.put("EF", "X");
        testMap.put("Z", "X");
        testMap.put("@", "X");
        testMap.put("AB", "X");
        testMap.put("C", "D");
        testMap.put("DE", "X");
        testMap.put("EF", "X");
        testMap.put("Z", "X");

        
        testMap.remove("@");
        testMap.remove("AB");
        testMap.remove("DE");
        testMap.remove("EF");
        testMap.remove("Z");
       
        testMap.remove("A");
   }

    @Test
    public void TRBRCAllBlack() {
        reset();
        testMap.put("D", "D");
        testMap.put("B", "D");
        testMap.put("E", "D");
        testMap.put("A", "D");
        testMap.put("C", "D");
        testMap.put("DE", "X");
        testMap.put("Z", "X");
        testMap.put("@", "X");
        testMap.put("AB", "X");
        testMap.put("BC", "X");
        testMap.put("CD", "X");
        testMap.remove("@");
        testMap.remove("AB");
        testMap.remove("BC");
        testMap.remove("CD");

        
        testMap.remove("A");
       
        
    }

    @Test
    public void LLRBRDLRLRLR() {  // left-leaning red-black tree, right deficient, left red, left right left red
        reset();
        testMap.put("E", "D");
        testMap.put("B", "D");
         testMap.put("F", "D");
        testMap.put("A", "D");
         testMap.put("D", "D");
        testMap.put("C", "D");
        
        
        testMap.remove("F");
              
    }
    
    @Test
    public void removeAll() {
        resetInteger();

        ArrayList<Integer> keys = new ArrayList<Integer>();
        ArrayList<Integer> vals = new ArrayList<Integer>();
        Random rand = new Random();
        int size = rand.nextInt(2000);
        for (int i = 0; i < size; i++) {
            keys.add(rand.nextInt());
            vals.add(rand.nextInt());
            testMapInt.put(keys.get(i), vals.get(i));
        }
        for (int i = 0; i < size; i++) {
            testMapInt.remove(keys.get(i));
        }

        for (int i = 0; i < size; i++) {
            Integer val = testMapInt.get(keys.get(i));
            assert val == null;
        }

    }

    @Test
    public void removeSome() {
        resetInteger();
        HashMap<Integer, Integer> correctMap = new HashMap<Integer, Integer>();

        ArrayList<Integer> keys = new ArrayList<Integer>();
        ArrayList<Integer> vals = new ArrayList<Integer>();
        Random rand = new Random();
        int size = rand.nextInt(2000);
        for (int i = 0; i < size; i++) {
            keys.add(rand.nextInt());
            vals.add(rand.nextInt());
            testMapInt.put(keys.get(i), vals.get(i));
            correctMap.put(keys.get(i), vals.get(i));
        }
        int multiplier = 0;
        for (int i = 0; i < size / 10; i++) {
            multiplier = rand.nextInt(10);
            testMapInt.remove(keys.get(i * multiplier));
            correctMap.remove(keys.get(i * multiplier));
        }

        for (int i = 0; i < size; i++) {
            Integer testVal = testMapInt.get(keys.get(i));
            Integer correctVal = correctMap.get(keys.get(i));
            if (correctVal == null) {
                assert testVal == null;
            } else if (testVal == null) {
                assert false;
            } else {
                assert testVal.equals(correctVal);
            }
        }

    }
    
    @Test
    public void stressRemoveTest() {
        // let size be the current size of test
        resetInteger();
        int magic = 42;
        int tests = 100;
        int MAXSIZE = 1000;
        Integer[] keys;
        for (int i = 0; i < tests; i++) {
            int currentMaxSize = rand.nextInt(MAXSIZE);
            keys = new Integer[currentMaxSize];
            for (int j = 0; j < currentMaxSize; j++) {

                Integer toPut = new Integer(j);
                testMapInt.put(toPut, toPut);
                // get a random number that probably isn't in the map
                testMapInt.get(rand.nextInt());
            }
            for (int j = 0; j < currentMaxSize; j++) {

                testMapInt.put(j, j * magic);

            }

            Iterator<Integer> it = testMapInt.iterator();
            assert(it != null);
            for (int j = 0; j < currentMaxSize; j++) {
                assert (it.hasNext());
                keys[j] = it.next();

            }
            for (int j = currentMaxSize - 1; j >= 0; j--) {
                assert (testMapInt.containsKey(keys[j]));
                Integer returned = testMapInt.get(keys[j]);
                assert (returned != null);
                assert (returned.equals(keys[j] * 42));

                testMapInt.remove(keys[j]);

                assert (!testMapInt.containsKey(keys[j]));

            }
            assert (!testMapInt.iterator().hasNext());
        }
    }

    @Test
    public void randRemoveTest() {
        // let size be the current size of test
        resetInteger();
        int magic = 42;
        int tests = 10;
        int MAXSIZE = 50000;
        Integer[] keys;
        for (int i = 0; i < tests; i++) {
            int currentMaxSize = rand.nextInt(MAXSIZE);
            keys = new Integer[currentMaxSize * 2];
            for (int j = 0; j < currentMaxSize; j++) {

                int toPut = rand.nextInt(1000);
                testMapInt.put(toPut, toPut * magic );

            }

            Iterator<Integer> it = testMapInt.iterator();
            assert(it != null);
            int size = 0;
            for (int j = 0; it.hasNext(); j++) {
                keys[j] = it.next();
                size++;
            }
            assert (size <= currentMaxSize);
            for (int j = size - 1; j >= 0; j--) {
                assert (testMapInt.containsKey(keys[j]));
                Integer returned = testMapInt.get(keys[j]);
                assert (returned != null);
                assert (returned.equals(keys[j]* magic));

                testMapInt.remove(keys[j]);

                assert (!testMapInt.containsKey(keys[j]));

            }
            assert (!testMapInt.iterator().hasNext());
        }
    }

    
}
    
    
