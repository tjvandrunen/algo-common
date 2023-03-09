package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Random;

import impl.OptimalBSTData;
import impl.OptimalBSTMap;
import impl.OptimalBSTMapFactory;
import optbstutil.OptBSTUtil;

import org.junit.Test;

public class OBSTTest {
	protected Random rand = new Random(42);
    static OptimalBSTData rawTiny, rawSimple, rawBarrie, rawBaum;
    
    static {
    	 try {
             rawTiny = OptBSTUtil.fromFile("tiny");
         } catch (FileNotFoundException e) {
             System.out.println("File tiny not found");
         }
        try {
            rawSimple = OptBSTUtil.fromFile("simple");
        } catch (FileNotFoundException e) {
            System.out.println("File simple not found");
        }
        try {
            rawBarrie = OptBSTUtil.fromFile("peterpan.prob");
        } catch (FileNotFoundException e) {
            System.out.println("File peterpan.prob not found");
        }
        try {
            rawBaum = OptBSTUtil.fromFile("baum-wwoo.prob.med");
        } catch (FileNotFoundException e) {
            System.out.println("File baum-wwoo.prob.med not found");
        }
    }
    @Test
    public void tinyTreeCost() {
        OptimalBSTMap map = OptimalBSTMapFactory.buildOptimalBST(rawTiny);
        double expectedSearchCost = OptBSTUtil.expectedSearchCost(map, rawTiny);
        double mine = 2.0;
        System.out.println("Expected cost: Yours: " + expectedSearchCost + " Mine: "+mine);
        System.out.println("Off by " + ((Math.abs(mine-expectedSearchCost)/mine)*100) + "%");
        assertEquals(mine, expectedSearchCost, .001);
    }
    
    @Test
    public void simpleTreeCost() {
        OptimalBSTMap map = OptimalBSTMapFactory.buildOptimalBST(rawSimple);
        double expectedSearchCost = OptBSTUtil.expectedSearchCost(map, rawSimple);
        double mine = 3.193;
        System.out.println("Expected cost: Yours: " + expectedSearchCost + " Mine: "+mine);
        System.out.println("Off by " + ((Math.abs(mine-expectedSearchCost)/mine)*100) + "%");
        assertEquals(mine, expectedSearchCost, .001);
    }
    
    
    @Test
    public void simpleTreeCorrectness() {
        OptimalBSTMap map = OptimalBSTMapFactory.buildOptimalBST(rawSimple);
        for (char x = 'a'; x < 'l'; x++) {
            String key = x + "";
            // evens are in the map
            if (x % 2 == 0) {
                assertTrue(map.containsKey(key));
                assertEquals(key, map.get(key));
            }
            // odds aren't
            else {
                assertFalse(map.containsKey(key));
                assertEquals(null, map.get(key));
            }
        }
        
      
    }

    @Test
    public void barriePPtreeCost() {
        OptimalBSTMap map = OptimalBSTMapFactory.buildOptimalBST(rawBarrie);
        double expectedSearchCost = OptBSTUtil.expectedSearchCost(map, rawBarrie);
        double mine = 4.702354342058536;
        System.out.println("Expected cost: Yours: " + expectedSearchCost + " Mine: " + mine );
        System.out.println("Off by " + ((Math.abs(mine-expectedSearchCost)/mine)*100) + "%");
        assertEquals(mine, expectedSearchCost, .001);
    }
    
    @Test
    public void barriePPtreeCorrectPutGet() {
        OptimalBSTMap map = OptimalBSTMapFactory.buildOptimalBST(rawBarrie);

        String[] actual = { "Darling", "Smee", "Wendy", "children", "the" };
        String[] spurious = { "pancake", "popcorn", "walrus", "syzygy" };

        for (String s : actual) {
            assertTrue(map.containsKey(s));
            assertEquals(reverse(s), map.get(s));
        }

        for (String s : spurious) {
            assertFalse(map.containsKey(s));
            assertEquals(null, map.get(s));
        }
    }

    @Test
    public void barriePPtreeCorrectBST() {
        OptimalBSTMap map = OptimalBSTMapFactory.buildOptimalBST(rawBarrie);
        Iterator<String> it = map.iterator();
        String previous = it.next();
        int count = 1;
        while (it.hasNext()) {
            String current = it.next();
            count++;
            assertTrue(previous.compareTo(current) < 0);
            previous = current;
        }
        assertEquals(count, rawBarrie.keys.length);
    }
    
    @Test
    public void baumWWOOtreeCost() {
        OptimalBSTMap map = OptimalBSTMapFactory.buildOptimalBST(rawBaum);
        double expectedSearchCost = OptBSTUtil.expectedSearchCost(map, rawBaum);
        double mine = 4.536161564841866;
        System.out.println("Expected cost: Yours: " + expectedSearchCost + " Mine: " + mine );
        System.out.println("Off by " + ((Math.abs(mine-expectedSearchCost)/mine)*100) + "%");
        assertEquals(mine, expectedSearchCost, .001);
    }
    
    
    @Test
    public void baumWWOOCorrectnessPutGet() {
            OptimalBSTMap map = OptimalBSTMapFactory.buildOptimalBST(rawBaum);
            
            String[] actual = { "Kansas", "great", "with", "prairies"};
            String[] spurious = { "pancake", "popcorn", "walrus", "syzygy" };
            
            for (String s : actual) {
                assertTrue(map.containsKey(s));
                assertEquals(reverse(s), map.get(s));
            }
            
            for (String s : spurious) {
                assertFalse(map.containsKey(s));
                assertEquals(null, map.get(s));
            }
        
      
    }

     

    @Test
    public void baumWWOOtreeCorrectBST() {
        OptimalBSTMap map = OptimalBSTMapFactory.buildOptimalBST(rawBaum);
        Iterator<String> it = map.iterator();
        String previous = it.next();
        int count = 1;
        while (it.hasNext()) {
            String current = it.next();
            count++;
            assertTrue(previous.compareTo(current) < 0);
            previous = current;
        }
        assertEquals(count, rawBaum.keys.length);
    }
    
    private Object reverse(String s) {
        String toReturn = "";
        for (char c : s.toCharArray())
            toReturn = c + toReturn;
        return toReturn;
    }
    


}
