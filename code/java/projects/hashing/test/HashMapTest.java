package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import org.junit.Test;

import adt.Map;

public abstract class HashMapTest extends MapStressTest {

    protected String[] arrayOfAllLetterSeqs(int seqLen) {
        String[] result = { "" };
        while (result[0].length() < seqLen) {
            String[] newResult = new String[result.length * 26];
            for (int i = 0; i < result.length; i++)
                for (int j = 0; j < 26; j++) {
                    newResult[(i * 26) + j] = result[i] + ((char) ('a' + j));
                }
            result = newResult;
        }
        return result;
    }
    Map<PoorlyHashed, Integer> testHashMap;
    
    protected abstract void resetPH();
    

    protected class PoorlyHashed {
        private Integer kernel;
        private int hash;

        public PoorlyHashed(Integer kernel) {
            this.kernel = kernel;
            hash = 0;
        }

        public PoorlyHashed(Integer kernel, int hash) {
            this.kernel = kernel;
            this.hash = hash;
        }

        @Override
        public int hashCode() {
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof PoorlyHashed && ((PoorlyHashed) obj).kernel.equals(kernel);
        }

        /**
         * Only makes String in Debug mode so the string cannot be used to hash.
         */
        @Override
        public String toString() {
            if (DEBUG)
                return "PH" + kernel;
            throw new UnsupportedOperationException();
        }
    }

    private void checkEqual(HashMap<PoorlyHashed, Integer> good, Map<PoorlyHashed, Integer> test,
            HashSet<PoorlyHashed> removed) {
        HashSet<PoorlyHashed> keys = new HashSet<PoorlyHashed>();
        keys.addAll(removed);
        keys.addAll(good.keySet());
        for (PoorlyHashed key : keys) {
            if (good.get(key) == null) {
                assert (test.get(key) == null);
            } else {
                assert (good.get(key).equals(test.get(key)));
            }
        }
    }

    
    @Test
    public void hashGapIdeal() {
        resetPH();
        PoorlyHashed a = new PoorlyHashed(1, 39);
        PoorlyHashed b = new PoorlyHashed(2, 39);
        PoorlyHashed c = new PoorlyHashed(3, 39);
        PoorlyHashed d = new PoorlyHashed(4, 39);
        PoorlyHashed e = new PoorlyHashed(5, 39);

        testHashMap.put(a, 1);
        testHashMap.put(b, 2);
        testHashMap.put(c, 3);
        testHashMap.put(d, 4);
        testHashMap.put(e, 6);
        testHashMap.remove(c);
        assert (testHashMap.get(c) == null);
        assert (testHashMap.get(e) == 6);
    }
    
    @Test
    public void hashGapPos() {
        resetPH();
        PoorlyHashed a = new PoorlyHashed(1, 40);
        PoorlyHashed b = new PoorlyHashed(2, 40);
        PoorlyHashed c = new PoorlyHashed(3, 40);
        PoorlyHashed d = new PoorlyHashed(4, 40);
        PoorlyHashed e = new PoorlyHashed(5, 40);

        testHashMap.put(a, 11);
        testHashMap.put(b, 22);
        testHashMap.put(c, 33);
        testHashMap.put(d, 44);
        testHashMap.put(e, 55);
        testHashMap.remove(c);
        assert (testHashMap.get(c) == null);
        assert (testHashMap.get(e) == 55);
    }

    @Test
    public void sameHashRemove() {
        resetPH();
        PoorlyHashed a = new PoorlyHashed(1, 0);
        PoorlyHashed b = new PoorlyHashed(2, 0);
        testHashMap.put(a, 1);
        testHashMap.put(b, 2);
        testHashMap.remove(a);
        assert (testHashMap.get(a) == null);
        assert (testHashMap.get(b) != null);
        assert (testHashMap.get(b) == 2);
    }

    @Test
    public void overlappingHashRemove() {
        resetPH();
        PoorlyHashed a = new PoorlyHashed(1, 0);
        PoorlyHashed b = new PoorlyHashed(2, 0);
        PoorlyHashed c = new PoorlyHashed(3, 0);
        PoorlyHashed d = new PoorlyHashed(4, 1);

        testHashMap.put(a, 1);
        testHashMap.put(b, 2);
        testHashMap.put(c, 3);
        testHashMap.put(d, 4);

        testHashMap.remove(d);
        assert (testHashMap.get(a) != null);
        assert (testHashMap.get(a) == 1);
        assert (testHashMap.get(b) != null);
        assert (testHashMap.get(b) == 2);
        assert (testHashMap.get(c) != null);
        assert (testHashMap.get(c) == 3);
        assert (testHashMap.get(d) == null);
    }

    @Test
    public void loopSameHashRemove() {
        resetPH();
        PoorlyHashed a = new PoorlyHashed(1, 6);
        PoorlyHashed b = new PoorlyHashed(2, 6);
        testHashMap.put(a, 1);
        testHashMap.put(b, 2);
        testHashMap.remove(a);
        assert (testHashMap.get(a) == null);
        assert (testHashMap.get(b) != null);
        assert (testHashMap.get(b) == 2);
    }

    @Test
    public void loopOverlappingHashRemoveAfter() {
        resetPH();
        PoorlyHashed d = new PoorlyHashed(4, 0);
        PoorlyHashed a = new PoorlyHashed(1, 6);
        PoorlyHashed b = new PoorlyHashed(2, 6);
        PoorlyHashed c = new PoorlyHashed(3, 6);

        testHashMap.put(d, 4);
        testHashMap.put(a, 1);
        testHashMap.put(b, 2);
        testHashMap.put(c, 3);

        testHashMap.remove(d);
        assert (testHashMap.get(a) != null);
        assert (testHashMap.get(a) == 1);
        assert (testHashMap.get(b) != null);
        assert (testHashMap.get(b) == 2);
        assert (testHashMap.get(c) != null);
        assert (testHashMap.get(c) == 3);
        assert (testHashMap.get(d) == null);
    }

    @Test
    public void loopOverlappingHashRemoveBefore() {
        resetPH();
        PoorlyHashed d = new PoorlyHashed(4, 6);
        PoorlyHashed a = new PoorlyHashed(1, 5);
        PoorlyHashed b = new PoorlyHashed(2, 5);
        PoorlyHashed c = new PoorlyHashed(3, 5);

        testHashMap.put(d, 4);
        testHashMap.put(a, 1);
        testHashMap.put(b, 2);
        testHashMap.put(c, 3);

        testHashMap.remove(d);
        assert (testHashMap.get(a) != null);
        assert (testHashMap.get(a) == 1);
        assert (testHashMap.get(b) != null);
        assert (testHashMap.get(b) == 2);
        assert (testHashMap.get(c) != null);
        assert (testHashMap.get(c) == 3);
        assert (testHashMap.get(d) == null);
    }

    @Test
    public void stressTestBadHashing() {
        resetPH();
        int size = 1000;
        HashMap<PoorlyHashed, Integer> good = new HashMap<PoorlyHashed, Integer>();
        HashSet<PoorlyHashed> removed = new HashSet<PoorlyHashed>();
        for (int i = size; i >= 0; i--) {
            good.put(new PoorlyHashed(i), i * 2);
            testHashMap.put(new PoorlyHashed(i), i * 2);
            if (i % 5 == 0 && i < size) {
                good.remove(new PoorlyHashed(i - 2));
                testHashMap.remove(new PoorlyHashed(i - 2));
                removed.add(new PoorlyHashed(i - 2));
                assert (testHashMap.get(new PoorlyHashed(i - 2)) == null);
            }
            if (i % 13 == 0 && i > 13) {
                good.remove(new PoorlyHashed(i));
                testHashMap.remove(new PoorlyHashed(i));
                removed.add(new PoorlyHashed(i));
                assert (testHashMap.get(new PoorlyHashed(i)) == null);
            }
            checkEqual(good, testHashMap, removed);
        }
        checkEqual(good, testHashMap, removed);
    }

    @Test
    public void randomTest() {
        resetPH();
        HashMap<PoorlyHashed, Integer> good = new HashMap<PoorlyHashed, Integer>();
        HashSet<PoorlyHashed> removed = new HashSet<PoorlyHashed>();
        int size = 100;
        Random rand = new Random();
        int tests = 10;
        for (int t = 0; t < tests; t++) {
            for (int i = 0; i < size; i++) {
                PoorlyHashed key = new PoorlyHashed(rand.nextInt());
                int val = rand.nextInt();
                good.put(key, val);
                testHashMap.put(key, val);
                checkEqual(good, testHashMap, removed);
                if (i % 5 == 0) {
                    // remove a random element every 5th iteration
                    PoorlyHashed toRemove = (PoorlyHashed) good.keySet().toArray()[rand.nextInt(good.size())];
                    good.remove(toRemove);
                    testHashMap.remove(toRemove);
                    removed.add(toRemove);
                }
                checkEqual(good, testHashMap, removed);
            }
            checkEqual(good, testHashMap, removed);
            // remove everything and start over
            removed.clear();
            for (PoorlyHashed ph : good.keySet().toArray(new PoorlyHashed[good.size()])) {
                good.remove(ph);
                testHashMap.remove(ph);
            }
            checkEqual(good, testHashMap, removed);
        }
        
        
    }

    @Test
    public void stressTest() {
        reset();
        HashSet<String> original = new HashSet<String>();
        original.addAll(Arrays.asList(arrayOfAllLetterSeqs(3)));
        Iterator<String> source = original.iterator();

        HashSet<String> added = new HashSet<String>();
        HashSet<String> removed = new HashSet<String>();

        // add a whole bunch

        for (int i = 0; i < 150; i++) {
            String x = source.next();
            added.add(x);
            testMap.put(x, x);
            assertTrue(testMap.containsKey(x));
            assertEquals(x, testMap.get(x));
        }

        // check they're there
        for (Iterator<String> it = added.iterator(); it.hasNext();) {
            String key = it.next();
            assertTrue(testMap.containsKey(key));
            assertEquals(key, testMap.get(key));
        }

        // remove a bunch

        Iterator<String> someAdded = added.iterator();
        for (int i = 0; i < 50; i++) {
            String key = someAdded.next();
            testMap.remove(key);
            removed.add(key);
            someAdded.remove();
            assertFalse(testMap.containsKey(key));
        }
        // finish off that iterator
        while (someAdded.hasNext())
            someAdded.next();

        // check the ones we removed are gone and the
        // ones we didn't are still there
        for (Iterator<String> it = added.iterator(); it.hasNext();) {
            String key = it.next();
            assertTrue(testMap.containsKey(key));
            assertEquals(key, testMap.get(key));
        }
        for (Iterator<String> it = removed.iterator(); it.hasNext();) {
            String key = it.next();
            assertFalse(testMap.containsKey(key));
            assertEquals(null, testMap.get(key));
        }

        // add the rest

        while (source.hasNext()) {
            String x = source.next();
            added.add(x);
            testMap.put(x, x);
        }

        // check again
        for (Iterator<String> it = added.iterator(); it.hasNext();) {
            String key = it.next();
            assertTrue(testMap.containsKey(key));
            assertEquals(key, testMap.get(key));
        }
        for (Iterator<String> it = removed.iterator(); it.hasNext();) {
            String key = it.next();
            assertFalse(testMap.containsKey(key));
            assertEquals(null, testMap.get(key));
        }

        // remove them all
        for (Iterator<String> it = added.iterator(); it.hasNext();) {
            String key = it.next();
            testMap.remove(key);
            removed.add(key);
            it.remove();
        }

        // check again
        int i = 0;
        for (Iterator<String> it = added.iterator(); it.hasNext();) {
            String key = it.next();
            assertTrue(testMap.containsKey(key));
            assertEquals(key, testMap.get(key));
            i++;
        }
        assertEquals(0, i);
        for (Iterator<String> it = removed.iterator(); it.hasNext();) {
            String key = it.next();
            assertFalse(testMap.containsKey(key));
            assertEquals(null, testMap.get(key));
        }
    }

    /**
     * This tests a special situation in naive open-addressing hashtables
     * (that is, hashtables that delete by replacing an item with a dummy
     * rather than fixing the chain). If all positions have been filled
     * and some of them deleted, then the hashtable will be "full" from 
     * the perspective of the find() method---that is, the probe will
     * infinitely loop. Instead, determining when to rehash should be
     * based on the number of pairs plus the number of deleteds. 
     */
    @Test
    public void oneOfAllFortyOne() {
        // Using a "plain old hash function" with size 41, the strings in this
        // array will cover all 41 possible values.
        String[] oneOfAll = {"1080", "10-point", "10th", "11-point", "12-point", 
                "16-point", "18-point", "1st", "2", "20-point", "2,4,5-t", "2,4-d", 
                "30-30", "3-D", "3-d", "3M", "4-D", "4GL", "5-T", "6-point", "6th", 
                "7th", "8-point", "8th", "9th", "A", "A4", "AA", "aa", "AAAA", "AAAAAA", 
                "AAAL", "aahed", "aalii", "Aalto", "A-and-R", "Aargau", "Aaronite", 
                "Aaronsburg", "Aaronson", "Ababdeh" };

        reset();
        for (int i = 0; i < oneOfAll.length; i++) {
            testMap.put(oneOfAll[i], "X");
            if (i > 6)
                testMap.remove(oneOfAll[i-6]);
        }
        assertFalse(testMap.containsKey("anything"));
        
    }

    protected static class SkeletonKey {
        private String name;
        private int hashCode;
        private int extra;
        private int comparisons;
        public SkeletonKey(String name, int hashCode, int extra) {
            this.name = name;
            this.hashCode = hashCode;
            this.extra = extra;
            comparisons = 0;
        }
        public int getExtra() { return extra; }
        public void resetComparisons() { comparisons = 0; }
        public int getComparisons() { return comparisons; }
        @Override
        public String toString() { return name; }
        @Override
        public int hashCode() { return hashCode; }
        @Override
        public boolean equals(Object o) {
            comparisons++;
            if (o instanceof SkeletonKey) ((SkeletonKey) o).comparisons++;
            return o instanceof SkeletonKey 
                    && ((SkeletonKey) o).name.equals(name)
                    && ((SkeletonKey) o).hashCode == hashCode;
        }
    }

    protected Map<SkeletonKey,String> skMap;
    protected abstract void resetSK();
    
    @Test
    public void putTwiceRemoveOnce() {
        resetSK();
        SkeletonKey[] keys = 
                new SkeletonKey[] { new SkeletonKey("A", 1, 0), new SkeletonKey("B", 1, 1),
                        new SkeletonKey("C", 1, 2), new SkeletonKey("D", 1, 3)
                };
        String value = "x";
        skMap.put(keys[0], value);
        skMap.put(keys[1], value);
        skMap.put(keys[2], value);
        skMap.put(keys[3], value);
        skMap.remove(keys[1]);

        boolean[] marks = new boolean[4];
        int num = 0;
        for (SkeletonKey k : skMap) {
            assertFalse(marks[k.extra]);
            marks[k.extra] = true;
            num++;
        }
        assertFalse(marks[1]);
        assertEquals(3, num);
        
        skMap.put(keys[3], "y");

        marks = new boolean[4];
        num = 0;
        for (SkeletonKey k : skMap) {
            assertFalse(marks[k.extra]);
            marks[k.extra] = true;
            num++;
        }
        assertFalse(marks[1]);
        assertEquals(3, num);
       
        
        skMap.remove(keys[3]);
        assertFalse(skMap.containsKey(keys[3]));

        marks = new boolean[4];
        num = 0;
        for (SkeletonKey k : skMap) {
            assertFalse(marks[k.extra]);
            marks[k.extra] = true;
            num++;
        }
        assertFalse(marks[1]);
        assertFalse(marks[3]);
        assertEquals(2, num);
        
    }

    @Test
    public void rehashAfterRemoveGoodHash() {
        resetSK();
        SkeletonKey[] keys = new SkeletonKey[40];
        for (int i = 0; i < 25; i++) {
            keys[i] = new SkeletonKey(i + "", i, 0);
            skMap.put(keys[i], "x");
        }
        skMap.remove(keys[5]);
        for (int i = 25; i < 40; i++) {
            keys[i] = new SkeletonKey(i + "", i, 0);
            skMap.put(keys[i], "x");
        }
        for (int i = 0; i < keys.length; i++)
            if (i == 5)
                assertFalse(skMap.containsKey(keys[i]));
            else
                assertTrue(skMap.containsKey(keys[i]));
    }
    
    @Test
    public void rehashAfterRemoveBadHash() {
        resetSK();
        SkeletonKey[] keys = new SkeletonKey[40];
        for (int i = 0; i < 25; i++) {
            keys[i] = new SkeletonKey(i + "", i, 0);
            skMap.put(keys[i], "x");
        }
        skMap.remove(keys[5]);
        for (int i = 25; i < 40; i++) {
            keys[i] = new SkeletonKey(i + "", i, 0);
            skMap.put(keys[i], "x");
        }
        for (int i = 0; i < keys.length; i++)
            if (i == 5)
                assertFalse(skMap.containsKey(keys[i]));
            else
                assertTrue(skMap.containsKey(keys[i]));
    }
    
   
}
