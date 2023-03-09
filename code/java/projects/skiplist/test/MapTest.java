package test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import adt.Map;

public abstract class MapTest extends CollectionTest{

    protected Map<String,String> testMap;

    protected abstract void reset();

    protected String[] otherData = { "Wisconsin", "Oklahoma", "Washington" };
    
    @Override
    protected String[] getData() {
        return states;
    }
    
    protected void populate(int pairs) {
        for (int i = 0; i < pairs; i++)
            testMap.put(getData()[2 * i], getData()[2 * i + 1]);
    }
    protected String[] getKeys() {
        String[] keys = new String[getData().length/2];
        for (int i = 0; i < getData().length/2; i++)
            keys[i] = getData()[2*i ];
        return keys;
    }

    /* testing containsKey */
    @Test
    public void emptyContainsKey() {
        reset();
        for (int i = 0; i < getData().length; i += 2)
            assertFalse(testMap.containsKey(getData()[i]));
        for (int i = 0; i < otherData.length; i++)
            assertFalse(testMap.containsKey(otherData[i]));
    }

    /* testing put */
    @Test
    public void putContainsKey() {
        reset();
        populate(getData().length / 2);
        for (int i = 0; i < getData().length; i += 2)  
            assertTrue(testMap.containsKey(getData()[i]));
        for (int i = 0; i < otherData.length; i++)
            assertFalse(testMap.containsKey(otherData[i]));
    }

    /* testing get */
    @Test
    public void emptyGet() {
        reset();
        for (int i = 0; i < getData().length; i += 2)
            assertEquals(null, testMap.get(getData()[i]));
        for (int i = 0; i < otherData.length; i++)
            assertEquals(null, testMap.get(otherData[i]));
        
    }
    
    @Test
    public void putGet() {
        reset();
        populate(getData().length / 2);
        for (int i = 0; i < getData().length; i += 2)
            assertEquals(getData()[i+1], testMap.get(getData()[i]));
        for (int i = 0; i < otherData.length; i++) 
            assertEquals(null, testMap.get(otherData[i]));
    }

    /* Testing replacement */

    @Test
    public void putReplace() {
        reset();
        populate(getData().length / 2);
        testMap.put("Alaska", "Barrows");
        assertTrue(testMap.containsKey("Alaska"));
        for (int i = 0; i < getData().length; i += 2)
            if (getData()[i].equals("Alaska"))
                assertEquals("Barrows", testMap.get(getData()[i]));
            else 
                assertEquals(getData()[i+1], testMap.get(getData()[i]));
        for (int i = 0; i < otherData.length; i++)
            assertEquals(null, testMap.get(otherData[i]));
    }

    /* Testing iterator */
    @Test
    public void emptyIterator() {
        reset();
        int i = 0;
        for (Iterator<String> it = testMap.iterator(); it.hasNext(); )
            i++;
        assertEquals(0, i);
    }

    @Test
    public void populatedIterator() {
        reset();
        populate(getData().length/ 2);
        boolean[] founds = new boolean[getData().length / 2];
        for (int i = 0; i < founds.length; i++)
            founds[i] = false;
        for (Iterator<String> it = testMap.iterator(); it.hasNext(); ) {
            String key = it.next();
            boolean foundIt = false;
            for (int i = 0; i < getData().length && ! foundIt; i += 2) {
                if (getData()[i].equals(key)) {
                    // key returned from iterator has right value in map
                    assertEquals(getData()[i+1], testMap.get(key));
                    // iterator hasn't returned this key before
                    assertFalse("Repeated key: " + key, founds[i/2]);
                    founds[i/2] = true;
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
    public void replacedIterator() {
        reset();
        populate(getData().length / 2);
        testMap.put("Alaska", "Barrows");
        boolean[] founds = new boolean[getData().length / 2];
        for (int i = 0; i < founds.length; i++)
            founds[i] = false;
        for (Iterator<String> it = testMap.iterator(); it.hasNext(); ) {
            String key = it.next();
            boolean foundIt = false;
            for (int i = 0; i < getData().length && ! foundIt; i += 2) {
                if (getData()[i].equals(key)) {
                    // key returned from iterator has right value in map
                    if (key.equals("Alaska"))
                        assertEquals("Barrows", testMap.get(key));
                    else 
                        assertEquals(getData()[i+1], testMap.get(key));
                    // iterator hasn't returned this key before
                    assertFalse("Repeated key: " + key, founds[i/2]);
                    founds[i/2] = true;
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
    public void putNull() {
        reset();
        testMap.put("Alaska", "x");
        testMap.put("Kansas", null);
        assertTrue(testMap.containsKey("Alaska"));
        assertTrue(testMap.containsKey("Kansas"));
        assertFalse(testMap.containsKey("Colorado"));
        assertEquals("x", testMap.get("Alaska"));
        assertEquals(null, testMap.get("Kansas"));
        assertEquals(null, testMap.get("Colorado"));
    }

    @Test
    public void stressComparison() {
        reset();
        populate(getData().length / 2);
        char[] rawChars = { 'M', 'j', 'h', 'r'};
        rawChars[0] += 2;
        rawChars[1] -= 2;
        rawChars[2] += 1;
        rawChars[3] -= 3;
        String key = new String(rawChars);
        assertTrue(testMap.containsKey(key));
        assertEquals("Cincinatti", testMap.get(key));
    }
    
}
