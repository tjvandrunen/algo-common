package adt;

/**
 * NSet
 * 
 * Interface for sets specifically of natural numbers within a
 * specific range, which afford efficient implementations of the 
 * standard set operations plus some others. The operations that
 * take another NSet as a parameter expect the parameter to be
 * of the same class and same range as the NSet object on which
 * they are called and will throw a BadNSetParameterException
 * otherwise.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * June 12, 2015
 */

public interface NSet extends Set<Integer> {
    
    /**
     * The range of this set, that is, one greater
     * than the largest number than can be stored
     * in this set.
     * @return n such that the elements of this set are
     * drawn from the range [0, n).
     */
    int range();
    
    /**
     * Compute the complement of of this set.
     * @return A set containing all the elements that
     * aren't in this one and none of the elements that
     * are.
     */
    NSet complement();
    
    /**
     * Compute the union of this and the given set.
     * @param other Another set of the same class and
     * range.
     * @return A set containing all the elements that are
     * in either this or the other set.
     */
    NSet union(NSet other);
    
    /**
     * Compute the intersection of this and the given set.
     * @param other Another set of the same class and 
     * range.
     * @return A set containing all the elements that are
     * in both this and the other set.
     */
    NSet intersection(NSet other);
    
    /**
     * Compute the difference between this and the given
     * set. 
     * @param other Another set of the same class and 
     * range.
     * @return A set containing all the elements that
     * are in this set but not in the other set.
     */
    NSet difference(NSet other);
    
}
