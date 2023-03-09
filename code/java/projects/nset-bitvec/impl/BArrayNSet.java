package impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import adt.BadNSetParameterException;
import adt.NSet;

/**
 * BArrayNSet
 * 
 * Implementation of NSet that uses an array of booleans
 * to represent the set. That is, i is in the set 
 * iff the array at position i is true.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * June 15, 2015
 */
public class BArrayNSet implements NSet {

    /**
     * The internal representation of this set, such that 
     * i is in the set iff internal[i].
     */
    private boolean[] internal;

    /**
     * Plain old constructor.
     * @param range One greater than the largest value
     * that can be stored.
     */
    public BArrayNSet(int range) {
        internal = new boolean[range];
    }

    /**
     * Check to see if a value could possibly be in this set,
     * and throw an exception if it is out of range.
     * @param x The value in question, interpreted as an index
     * into the array.
     */
    private void checkIndex(int x) {
        if (x < 0 || x >= internal.length)
            throw new BadNSetParameterException(x + "");
    }

    /**
     * Add an item to the set. (Do nothing if the item is 
     * already there.)
     * @param item The item to add
     */
    public void add(Integer item) {
        checkIndex(item);
        internal[item] = true;
    }

    /**
     * Does this set contain the item?
     * @param item The item to check
     * @return True if the item is in the set, false otherwise
     */
    public boolean contains(Integer item) {
        checkIndex(item);
        return internal[item];
    }

    /**
     * Remove an item from the set, if it's there
     * (ignore otherwise).
     * @param item The item to remove
     */
    public void remove(Integer item) {
        checkIndex(item);
        internal[item] = false;
    }


    /**
     * Is the set empty?
     * @return True if the set is empty, false otherwise.
     */
    public boolean isEmpty() {
        boolean hasSomething = false;
        for (boolean x : internal)
            hasSomething |= x;
        return ! hasSomething;
    }

    /**
     * The number of items in the set
     * @return The number of items.
     */
    public int size() {
         throw new UnsupportedOperationException();
    }


    /**
     * The range of this set, that is, one greater
     * than the largest number than can be stored
     * in this set.
     * @return n such that the elements of this set are
     * drawn from the range [0, n).
     */
    public int range() { return internal.length; }

    /**
     * Compute the complement of of this set.
     * @return A set containing all the elements that
     * aren't in this one and none of the elements that
     * are.
     */
    public NSet complement() {
        BArrayNSet toReturn = new BArrayNSet(internal.length);
        for (int i = 0; i < internal.length; i++)
            toReturn.internal[i] = ! internal[i];
        return toReturn;
    }


    /**
     * Make sure the other NSet has the same class and range as
     * this one, throw an exception otherwise.
     * @param other The other NSet, to be checked.
     */
    private void checkParameter(NSet other) {
        if (! (other instanceof BArrayNSet) || other.range() != internal.length)
            throw new BadNSetParameterException(this.getClass() + "," + internal.length + " / " +
                    other.getClass() + "," + other.range());
    }

    /**
     * Compute the union of this and the given set.
     * @param other Another set of the same class and
     * range.
     * @return A set containing all the elements that are
     * in either this or the other set.
     */
    public NSet union(NSet other) {
        checkParameter(other);
         throw new UnsupportedOperationException();
    }

    /**
     * Compute the intersection of this and the given set.
     * @param other Another set of the same class and 
     * range.
     * @return A set containing all the elements that are
     * in both this and the other set.
     */
    public NSet intersection(NSet other) {
        checkParameter(other);
         throw new UnsupportedOperationException();
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
        checkParameter(other);
         throw new UnsupportedOperationException();
    }

    /**
     * Iterate over this NSet.
     * @return An iterator for the items in the NSet.
     */
    public Iterator<Integer> iterator() {
        // calculate the index of the first true position,
        // if any; that is, the first value the iterator should
        // return
        int j = 0;
        while (j < internal.length && !internal[j]) j++;
        final int finalJ = j;
         throw new UnsupportedOperationException();
    }

}
