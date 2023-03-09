package impl;

import adt.BadNSetParameterException;
import adt.NSet;

/**
 * NaiveNSet
 * 
 * An NSet that takes no advantage of being a set of
 * integers from a limited range.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * June 12, 2015
 */

public class NaiveNSet extends LinkedSet<Integer> implements NSet {

    /**
     * One greater than the maximum value permitted,
     * for checking purposes.
     */
    private int range;
    
    /**
     * Plain old constructor.
     * @param range One greater than the largest value
     * that can be stored.
     */
    public NaiveNSet(int range) {
        this.range = range;
    }

    /**
     * Add an item to the set. (No problem if it's 
     * already there.)
     * @param item The item to add
     */
    @Override
    public void add(Integer item) {
        if (item >= 0 && item < range)
            super.add(item);
    }

    /**
     * The range of this set, that is, one greater
     * than the largest number than can be stored
     * in this set.
     * @return n such that the elements of this set are
     * drawn from the range [0, n).
     */
    public int range() {
        return range;
    }

    /**
     * Compute the complement of of this set.
     * @return A set containing all the elements that
     * aren't in this one and none of the elements that
     * are.
     */
    public NSet complement() {
        NaiveNSet toReturn = new NaiveNSet(range);
        for (int i = 0; i < range; i++)
            if (! contains(i))
                toReturn.add(i);
        return toReturn;
    }

    /**
     * Compute the union of this and the given set.
     * @param other Another set of the same class and
     * range.
     * @return A set containing all the elements that are
     * in either this or the other set.
     */
    public NSet union(NSet other) {
        if (! (other instanceof NaiveNSet) || other.range() != range)
            throw new BadNSetParameterException(this.getClass() + "," + range + " / " +
                    other.getClass() + "," + other.range());
        NaiveNSet toReturn = new NaiveNSet(range);
        for (Integer i : this)
            toReturn.add(i);
        for (Integer i : other)
            toReturn.add(i);
        return toReturn;
    }

    /**
     * Compute the intersection of this and the given set.
     * @param other Another set of the same class and 
     * range.
     * @return A set containing all the elements that are
     * in both this and the other set.
     */
    public NSet intersection(NSet other) {
        if (! (other instanceof NaiveNSet) || other.range() != range)
            throw new BadNSetParameterException(this.getClass() + "," + range + " / " +
                    other.getClass() + "," + other.range());
        NaiveNSet toReturn = new NaiveNSet(range);
        for (Integer i : this)
            if (other.contains(i))
                toReturn.add(i);

        return toReturn;
    }
    
    /**
     * Compute the difference between this and the given
     * set. 
     * @param other Another set of the same class and 
     * range.
     * @return A set containing all the elements that
     * are in this set but not in the other set.
     */
    public NSet difference(NSet other) {
        if (! (other instanceof NaiveNSet) || other.range() != range)
            throw new BadNSetParameterException(this.getClass() + "," + range + " / " +
                    other.getClass() + "," + other.range());
        NaiveNSet toReturn = new NaiveNSet(range);
        for (Integer i : this)
            toReturn.add(i);
        for (Integer i : other)
            toReturn.remove(i);
        return toReturn;
    }
    
    public String toString() {
        String toReturn = "";
        for (Integer i : this)
            toReturn += i + " ";
        return toReturn;
    }
    

}
