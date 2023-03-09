package exper;

import java.util.PriorityQueue;

import adt.Map;
import impl.SkipListMap;
/**
 * 
 * This program essential does the same test that is done in SkipListExperiment,
 * except that slows the skip list down by timing get operations and removing 
 * and re-inserting the nodes that are returned quickly, since they are likely 
 * high level nodes. It repeats this process several times, removing and re-adding
 * less nodes each time. The end result is that the skip list runs ~20 times slower 
 * than before. This number can be increased if more rounds are used. 
 * 
 * This deterministic skip lists paper might fix the problem: https://dl.acm.org/citation.cfm?id=139478
 * 
 *
 */
public class SkipListBreaker {

    public static void main(String args[]) {
        class Retrieval implements Comparable<Retrieval> {
            int num;
            Long time;

            @Override
            public int compareTo(Retrieval o) {
                return time.compareTo(o.time);
            }

            @Override
            public boolean equals(Object o) {
                return o instanceof Retrieval && ((Retrieval) o).time == time;
            }

            @Override
            public int hashCode() {
                return time.hashCode();
            }

            public Retrieval(int num, long time) {
                this.num = num;
                this.time = time;
            }


        }

        int n = 100000;

        Map<Integer, Integer> map = new SkipListMap<Integer, Integer>(4, 16);
        for (int i = 0; i < n; i++) {
            map.put(i, i);
        }
        
        // begin adversarial code
        for (int j = 2; j < n; j += 10) {
            PriorityQueue<Retrieval> retrievals = new PriorityQueue<Retrieval>();
            for (int i = 0; i < n; i++) {
                long fore = System.nanoTime();
                map.get(i);
                long aft = System.nanoTime();
                retrievals.add(new Retrieval(i, aft - fore));
            }
            for (int i = 0; i < n/2 - j/2; i++) {
                int k = retrievals.remove().num;
                map.remove(k);
                map.put(k, k);
            }
        }
        // end adversarial code
        // java stuff
        for(int k = 0; k <20; k++ ) {
            for (int i = 0; i < n; i++){
                map.get(i);
            }
        }
        long fore = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            map.get(i);
        }
        long aft = System.currentTimeMillis();
        System.out.println("Time to get " + n + " ordered elements: " + (aft - fore) + "ms");
    }
}
