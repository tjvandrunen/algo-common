package optimizedIntSet;

import java.util.Iterator;
/**
 * ModestlyOptimizedIntSet
 * 
 * Class to implement a set of integers "modestly optimized"
 * by distributing the integers over several internal lists.
 */

public class ModestlyOptimizedIntSet implements Set<Integer> {

    /**
     * An array of m lists containing the items in the set, where 
     * the ith list contains those numbers that are equal to i mod m 
     * (that is, have remainder i when divided by m).
     */
    private List<Integer>[] internals;

    @SuppressWarnings("unchecked")
    public ModestlyOptimizedIntSet(int m) {
        internals = (List<Integer>[]) new List[m];
        for (int i = 0; i < m; i++)
            internals[i] = new LinkedList<Integer>();
    }

    /**
     * Add an item to the set. (No problem if it's 
     * already there.)
     * @param item The item to add
     */
    public void add(Integer item) {
        throw new UnsupportedOperationException();
    }

    /**
     * Does this set contain the item?
     * @param item The item to check
     * @return True if the item is in the set, false otherwise
     */
    public boolean contains(Integer item) {
        throw new UnsupportedOperationException();
    }

    /**
     * Remove an item from the set, if it's there
     * (ignore otherwise).
     * @param item The item to remove
     */
    public void remove(Integer item) {
        throw new UnsupportedOperationException();
    }

    /**
     * The number of itmes in the set
     * @return The number of items.
     */
    public int size() {
        throw new UnsupportedOperationException();
    }

    /**
     * Is the set empty?
     * @return True if the set is empty, false otherwise.
     */
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    /**
     * Return an iterator over this set.
     */
    public Iterator<Integer> iterator() {
        throw new UnsupportedOperationException();
    }
}
