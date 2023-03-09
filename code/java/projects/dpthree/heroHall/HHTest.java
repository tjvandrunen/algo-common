package heroHall;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class HHTest {
	Random rand = new Random();
    @Test
    public void testOneJog() {
        assertEquals(21, HeroHall.bestTreasure(new int[] {1,  4,  10, 3}, 
                new int[] {8,2,4, 6}, 
                new int[] {6, 3, 2}));
    }
    
    @Test
    public void testGoStraight() {
        assertEquals(7, HeroHall.bestTreasure(new int[]{2, 2, 1,2},
                new int[]{1,3,1,1}, 
                new int[]{8,5,7}));
    }

    @Test
    public void testZigZag() {
        assertEquals(33, HeroHall.bestTreasure(new int[]{5,8,3,6}, 
                new int[]{9,2,16,1}, 
                new int[]{2,1,3}));
    }

    @Test
    public void testAntiGreedy() {
        assertEquals(55, HeroHall.bestTreasure(new int[]{5, 12, 1}, 
                new int[]{3, 2, 50}, 
                new int[]{3, 30}));
    }
    
    @Test
    public void testLong() {
    	final int N = 40000;
    	int[] rights = new int[N], lefts = new int[N], guards = new int[N-1];
    	// set it up so the right hall is always best
    	for (int i = 0; i < N; i++) {
    		lefts[i] = rand.nextInt(i+1);
    		// the treasures in the right hall is are just integers from 1 to n
    		rights[i] = i +1;
    		if (i < N-1)
    			guards[i] = i;
    	}
        assertEquals((N*(N+1))/2, HeroHall.bestTreasure(rights, lefts, guards));
    }
    
    @Test
    public void stressTest() {
    	// compares to the naive solution for small cases
    	// the maximum hall length
    	final int MAX_SIZE = 24;
    	// the number of tests
    	final int TESTS = 20;
    	for (int t = 0; t < TESTS; t++) {
    		int n = rand.nextInt(MAX_SIZE-1)+1;
    		int[] rights = new int[n], lefts = new int[n], guards = new int[n-1];
    		for (int i = 0; i < n; i++) {
    			// use a large range that won't overflow
    			int upperBound = Integer.MAX_VALUE/n;
    			rights[i] = rand.nextInt(upperBound);
    			lefts[i] = rand.nextInt(upperBound);
    			if (i < n-1)
    				guards[i] = rand.nextInt(upperBound);
    		}
    		assertEquals(NaiveHeroHall.bestTreasure(rights,lefts,guards),HeroHall.bestTreasure(rights, lefts, guards));
    	}
    }
}
