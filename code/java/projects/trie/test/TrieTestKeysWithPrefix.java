package test;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

public class TrieTestKeysWithPrefix extends TrieTest {

    @Test
    public void keysWithPrefixEmpty() {
        reset();
        int count = 0;
        for (String s : testSetTrie.keysWithPrefix("ANN")){
            assert(s.substring(0, 3).equals("ANN"));
            count++;
        }
        assertEquals(0, count);
    }

    @Test
    public void keysWithPrefixOneHit() {
        reset();
        testSetTrie.add("ANNE");
        int count = 0;
        for (String s : testSetTrie.keysWithPrefix("ANN")){
            assert(s.substring(0, 3).equals("ANN"));
            count++;
        }
        assertEquals(1, count);
        
    }

    @Test
    public void keysWithPrefixOneMiss() {
        reset();
        testSetTrie.add("JOHN");
        int count = 0;
        for (String s : testSetTrie.keysWithPrefix("ANN")) {
            assert(s.substring(0, 3).equals("ANN"));
            count++;
        }
            
        assertEquals(0, count);
    }

    @Test
    public void keysWithPrefixTwoOneHit() {
        reset();
        testSetTrie.add("ANNE");
        testSetTrie.add("JOHN");       
        int count = 0;
        for (String s : testSetTrie.keysWithPrefix("ANN")){
            assert(s.substring(0, 3).equals("ANN"));
            count++;
        }
        assertEquals(1, count);
        
    }

    @Test
    public void keysWithPrefixTwoBothHit() {
        reset();
        testSetTrie.add("ANNE");
        testSetTrie.add("ANNA");
        int count = 0;
        for (String s : testSetTrie.keysWithPrefix("ANN")){
            assert(s.substring(0, 3).equals("ANN"));
            count++;
        }
        assertEquals(2, count);
    }

    @Test
    public void keysWithPrefixExactHit() {
        reset();
        testSetTrie.add("ANNE");
        int count = 0;
        for (String s : testSetTrie.keysWithPrefix("ANNE")){
            assert(s.substring(0, 4).equals("ANNE"));
            count++;
        }
        assertEquals(1, count);
    }

    @Test
    public void keysWithPrefixManyHitsMany() {
        reset();
        populate();
        int count = 0;
        for (String s : testSetTrie.keysWithPrefix("ANN")){
            assert(s.substring(0, 3).equals("ANN"));
            count++;
        }
        assertEquals(9, count);
    }

    @Test
    public void keysWithPrefixManyHitsOne() {
        reset();
        populate();
        int count = 0;
        for (String s : testSetTrie.keysWithPrefix("ELA")){
            assert(s.substring(0, 3).equals("ELA"));
            count++;
        }
        assertEquals(1, count);
    }

    @Test
    public void keysWithPrefixManyHitsNone() {
        reset();
        populate();
        int count = 0;
        for (String s : testSetTrie.keysWithPrefix("BOB")){
            assert(s.substring(0, 3).equals("BOB"));
            count++;
        }
            
        assertEquals(0, count);
    }

    @Test
    public void keysWithPrefixManyEmptyStringHitsMany() {
        reset();
        populate();
        int count = 0;
        for (Iterator<String> it =testSetTrie.keysWithPrefix("").iterator(); it.hasNext(); it.next())
            count++;
        assertEquals(48, count);
    }

    @Test
    public void keysWithPrefixEmptyEmptyStringHitsNone() {
        reset();
        int count = 0;
        for (Iterator<String> it =testSetTrie.keysWithPrefix("").iterator(); it.hasNext(); it.next())
            count++;
        assertEquals(0, count);
    }

    @Test
    public void keysWithPrefixOneEmptyStringHitsOne() {
        reset();
        testSetTrie.add("ANN");
        int count = 0;
        for (Iterator<String> it =testSetTrie.keysWithPrefix("").iterator(); it.hasNext(); it.next())
            count++;
        assertEquals(1, count);
    }
}
