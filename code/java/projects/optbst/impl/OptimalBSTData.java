package impl;

/**
 * OPtimalBSTData
 * 
 * Simple (struct-like) class for containing all the information 
 * for building an optimal BST.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * Feb 26, 2015
 */

public class OptimalBSTData {

    public final String[] keys;
    public final String[] values;
    public final double[] keyProbs;
    public final double[] missProbs;
    
    public OptimalBSTData(String[] keys, String[] values, double[] keyProbs, double[] missProbs) {
        this.keys = keys;
        this.values = values;
        this.keyProbs = keyProbs;
        this.missProbs = missProbs;
    }
    
}
