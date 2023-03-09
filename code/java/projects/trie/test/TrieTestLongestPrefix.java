package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TrieTestLongestPrefix extends TrieTest {

    @Test
    public void longestPrefixEmpty() {
        reset();
        assertEquals(null, testSetTrie.longestPrefixOf("ANNIKA"));
    }

    @Test
    public void longestPrefixOneHit() {
        reset();
        testSet.add("ANN");
        assertEquals("ANN", testSetTrie.longestPrefixOf("ANNIKA"));
    }

    @Test
    public void longestPrefixOneMiss() {
        reset();
        testSet.add("ANN");
        assertEquals(null, testSetTrie.longestPrefixOf("CONSTANS"));
    }

    @Test
    public void longestPrefixTwoFirstHit() {
        reset();
        testSet.add("ANN");
        testSet.add("HEROD");
        assertEquals("ANN", testSetTrie.longestPrefixOf("ANNIKA"));
    }

    @Test
    public void longestPrefixTwoSecondHit() {
        reset();
        testSet.add("ANN");
        testSet.add("HEROD");
        assertEquals("ANN", testSetTrie.longestPrefixOf("ANNIKA"));
    }

    @Test
    public void longestPrefixTwoBetterHit() {
        reset();
        testSet.add("ANN");
        testSet.add("ANNE");
        assertEquals("ANNE", testSetTrie.longestPrefixOf("ANNEMARIE"));
    }

    @Test
    public void longestPrefixTwoMiss() {
        reset();
        testSet.add("ANN");
        testSet.add("HEROD");
        assertEquals(null, testSetTrie.longestPrefixOf("CONSTANS"));
    }

    @Test
    public void longestPrefixManyShortHit() {
        reset();
        populate();
        assertEquals("JON", testSetTrie.longestPrefixOf("JONJON"));
    }

    @Test
    public void longestPrefixManyHitLong() {
        reset();
        populate();
        assertEquals("JUSTINMARTYR", testSetTrie.longestPrefixOf("JUSTINMARTYRODONALD"));
    }

    @Test
    public void longestPrefixManyMiss() {
        reset();
        populate();
        assertEquals(null, testSetTrie.longestPrefixOf("CALIGULA"));
    }

    @Test
    public void longestPrefixSpace() {
        reset();
        populate();
        testSet.add("");
        assertEquals("", testSetTrie.longestPrefixOf("CALIGULA"));
        assertEquals("HELEN", testSetTrie.longestPrefixOf("HELENIC"));
    }

}
