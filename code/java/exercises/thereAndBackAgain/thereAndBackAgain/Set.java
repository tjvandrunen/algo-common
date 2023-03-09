package thereAndBackAgain;

/**
 * Set
 * 
 * Interface to serve as an example of the set ADT.
 * This is similar to what Sedgewick calls a "bag" (but
 * he's wrong).
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * July 9, 2014
 * @param <E> The base-type of the set
 */

public interface Set<E> extends Iterable<E> {

	/**
	 * Add an item to the set. (No problem if it's 
	 * already there.)
	 * @param item The item to add
	 */
	void add(E item);
	
	/**
	 * Does this set contain the item?
	 * @param item The item to check
	 * @return True if the item is in the set, false otherwise
	 */
	boolean contains(E item);

	/**
	 * Remove an item from the set, if it's there
	 * (ignore otherwise).
	 * @param item The item to remove
	 */
	void remove(E item);

	/**
	 * The number of itmes in the set
	 * @return The number of items.
	 */
	int size();
	
	/**
	 * Is the set empty?
	 * @return True if the set is empty, false otherwise.
	 */
	boolean isEmpty();
	
	
}
