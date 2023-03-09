package knapsack;

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

public class Knapsack {

    /**
     * Compute the set of objects that maximize value under
     * a given capacity constraint.
     * @param weights The weights of each object.
     * @param values The values of each object
     * @param capacity The capacity of the knapsack
     * @return An array indicating which objects should be taken
     */
    public static boolean[] knapsack(int[] weights, int values[], int capacity) {
        // take[i][j] means we should take object j to get a set
        // of objects that maximizes value using only objects through j
        // and not exceeding i weight. That is, to fill a sub-knapsack
        // of capacity i using only objects 0 through j, should we take
        // object j?
        boolean[][] take = new boolean[capacity + 1][weights.length];

        // bestValue[i][j] indicates the best value we can get by
        // taking a subset of objects 0 through j without exceeding
        // weight i
        int[][] bestValue = new int[capacity + 1][weights.length + 1];


        // Add code: populate the take table and the bestValue table


        boolean[] result = new boolean[weights.length];

        // Add code: populate the result array

        return result;
    }
}
