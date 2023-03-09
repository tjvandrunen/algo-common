package knapsack;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class KnapsackTest {
	
	Random rand = new Random();

    private void check(int[] weights, int[] values, int capacity, boolean result[], int max) {
        assertEquals(weights.length, result.length);
        int totalWeight = 0, totalValue = 0;
        for (int i = 0; i < weights.length; i++) 
            if (result[i]) {
                totalValue += values[i];
                totalWeight += weights[i];
            }
        assertEquals(max, totalValue);
        assertTrue(totalWeight <= capacity);
        
    }
    @Test 
    public void trivial() {
    	int[] weights = new int[]{1,1,1,1,1,1,1,1,1,1,1};
    	int[] values = new int[]{1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862, 16796};
    	int capacity = 100;
    	check(weights, values, capacity, Knapsack.knapsack(weights, values, capacity), 23714);
    }
    
    @Test
    public void dmfp() {
        // DMFP, pg 502
        int[] weights = new int[]{2, 3, 4, 7, 8, 13, 15};
        int[] values = new int[]{1, 4, 6, 10, 11, 20, 21};
        check(weights, values, 15, Knapsack.knapsack(weights, values, 15), 21);
    }
    
    @Test
    public void fromClass() {
        int[] weights = new int[]{1, 2, 4, 5};
        int[] values = new int[]{20, 15, 90, 100};
        check(weights, values, 7, Knapsack.knapsack(weights, values, 7), 125);
    }
    
    @Test 
    public void dontGetGreedy() {
    	int[] weights = new int[]{30,30,30,20,20};
    	int[] values = new int[]{100,100,100,50,51};
    	int capacity = 100;
    	check(weights, values, capacity, Knapsack.knapsack(weights, values, capacity), 301);
    }
    
    @Test 
    public void getGreedy() {
    	int[] weights = new int[]{30,30,30,20,20};
    	int[] values = new int[]{105,105,105,50,51};
    	int capacity = 100;
    	check(weights, values, capacity, Knapsack.knapsack(weights, values, capacity), 315);
    }
    
    @Test 
    public void takeAll() {
    	int[] weights = new int[]{1,1,1,1,1,1,1,1,1,1,1};
    	int[] values = new int[]{1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862, 16796};
    	int capacity = 100;
    	check(weights, values, capacity, Knapsack.knapsack(weights, values, capacity), 23714);
    }
    
    @Test 
    public void largeKnapsack() {
    	// the number of things to be chosen (must be even)
    	final int N = 10000;
    	// the capacity of the knapsack
    	final int CAP = N/10;
    	int[] weights = new int[N], values = new int[N];
    	
    	for (int i = 0; i < N; i++) {
    		// even things are light, odd things are heavy
    		if (i%2 == 0)
    			weights[i] = 1;
    		else weights[i] = 90;
    		// values are integers from 1 to N
    		values[i] = i+1;
    	}
    	
    	// the correct choice is to pick the last CAP even numbers
    	int correctVal = 0;
    	for (int i = 1; i <= CAP; i++) 
    		correctVal += values[N - i*2];
    	check(weights,values, CAP, Knapsack.knapsack(weights, values, CAP), correctVal);
    }
    
    @Test 
    public void stressTest() {
    	// The maximum number of items offered
    	final int MAX_SIZE  = 20;
    	// the number of tests run
    	final int TESTS = 200;
    	for (int t = 0; t < TESTS; t++) {
    		int n = rand.nextInt(MAX_SIZE-1)+1;
    		int cap = rand.nextInt(MAX_SIZE-1)+1;
    		int[] weights = new int[n], values = new int[n];
    		for (int i = 0; i < n; i++) {
    			/* 
    			 * we want weight to use up the whole range from 1 to n, but we want more
    			 * small weights than large weights. 
    			 */
    			weights[i] = (rand.nextInt(n)+1)/(rand.nextInt(n)+1)+1;
    			// values use a large range that won't overflow
    			values[i] = rand.nextInt(Integer.MAX_VALUE/n);
    		}
    		boolean[] goodPicks = NaiveKnapsack.knapsack(weights, values, cap);
    		int max = 0;
    		for (int i = 0; i < n; i++) {
    			if (goodPicks[i])
    				max += values[i];
    		}
    		check(weights,values, cap, Knapsack.knapsack(weights, values, cap), max);
    	}
    }
    	
    	
    		

}
