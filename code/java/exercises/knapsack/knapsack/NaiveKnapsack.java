package knapsack;

import java.math.BigInteger;

/**
 * Knapsack
 * 
 * Placeholder for solution to the knapsack problem:
 * 
 * Given a set of objects represented as parallel arrays of
 * weights and values and a capacity, return the set of  objects 
 * that should be taken to maximize value but not exceed the 
 * capacity in weight.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * July 13, 2015
 */

public class NaiveKnapsack {

    /**
     * Compute the set of objects that maximize value under
     * a given capacity constraint.
     * @param weights The weights of each object.
     * @param values The values of each object
     * @param capacity The capacity of the knapsack
     * @return An array indicating which objects should be taken
     */
    public static boolean[] knapsack(int[] weights, int values[], int capacity) {
        
        BigInteger maxBv = null;
        int max = -1;
        for (BigInteger bv = BigInteger.ZERO; bv.bitLength() <= weights.length; bv = bv.add(BigInteger.ONE)) {
        	int currentVal = value(weights, values, capacity,bv);
        	if (currentVal > max) {
        		max = currentVal;
        		maxBv = bv;
        	}
        }
        boolean[] toReturn = new boolean[weights.length];
        for (int i = 0; i < toReturn.length; i++)
        	toReturn[i] = maxBv.testBit(i);
        return toReturn;
    }
    
    public static int value(int[] weights, int[] values, int capacity, BigInteger bv) {
    	int weight = 0, value = 0;
    	for (int i = 0; i < weights.length; i++) {
    		if(bv.testBit(i)) {
    			weight += weights[i];
    			value += values[i];
    		}
    	}
    	if (weight > capacity)
    		return -1;
    	return value;
    }
    	
}
