package test;
import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public abstract class MapRemoveTest extends MapTest{
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

    
}
