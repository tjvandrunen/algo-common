package test;

import java.util.Iterator;

import org.junit.Test;

public class TrieStressTest extends TrieTest{
    @Test
    public void stressTest() {
        // let size be the current size of test
        reset();
        int tests = 100;
        int MAXSIZE = 10000;
        String[] keys;
        for (int i = 0; i < tests; i++) {
            int currentMaxSize = rand.nextInt(MAXSIZE);
            keys = new String[currentMaxSize];
            for (int j = 0; j < currentMaxSize; j++) {

                String toPut = numToTrieString(j);
                testSet.add(toPut);
                // check for a random number that probably isn't in the map
                testSet.contains(numToTrieString(rand.nextInt()));
            }
            for (int j = 0; j < currentMaxSize; j++) {

                testSet.add(numToTrieString(j));

            }

            Iterator<String> it = testSet.iterator();
            assert(it != null);
            for (int j = 0; j < currentMaxSize; j++) {
                assert (it.hasNext());
                keys[j] = it.next();

            }
            for (int j = currentMaxSize - 1; j >= 0; j--) {
                assert (testSet.contains(keys[j]));
                testSet.remove(keys[j]);
                assert (!testSet.contains(keys[j]));

            }
            assert (!testSet.iterator().hasNext());
        }
    }

    @Test
    public void randTest() {
        // let size be the current size of test
        reset();
        int tests = 10;
        int MAXSIZE = 50000;
        String[] keys;
        for (int i = 0; i < tests; i++) {
            int currentMaxSize = rand.nextInt(MAXSIZE);
            keys = new String[currentMaxSize * 2];
            for (int j = 0; j < currentMaxSize; j++) {

                String toPut =numToTrieString(rand.nextLong());
                testSet.add(toPut);

            }

            Iterator<String> it = testSet.iterator();
            assert(it != null);
            int size = 0;
            for (int j = 0; it.hasNext(); j++) {
                keys[j] = it.next();
                size++;
            }
            assert (size <= currentMaxSize);
            for (int j = size - 1; j >= 0; j--) {
                assert (testSet.contains(keys[j]));
                testSet.remove(keys[j]);
                assert (!testSet.contains(keys[j]));

            }
            assert (!testSet.iterator().hasNext());
        }
    }
    
    private String numToTrieString(long input) {
        String toReturn = "";
        while (input > 0) {
            toReturn += (char) ('A' + (input%26));
            input /= 26;
        }
        return toReturn;
    }
}
