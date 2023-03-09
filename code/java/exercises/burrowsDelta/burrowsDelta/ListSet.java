package burrowsDelta;

import java.util.Iterator;


/**
 * ListSet
 * 
 * A basic list implementation of a set (I was surprised
 * to find that I hadn't made one yet.)
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * June 12, 2015
 */
public class ListSet<E> implements Set<E> {

    /**
     * The internal representation of this set. The
     * constructor makes this a linked list, but in
     * principle it doesn't matter.
     */
    private List<E> internal;
    
    /**
     * Plain constructor. Currently chooses a linked
     * list representation, but that can be changed easily enough.
     */
    public ListSet() {
        internal = new LinkedList<E>();
    }
    
    
    public Iterator<E> iterator() {
        return internal.iterator();
    }

    /**
     * Add an item to the set. (Do nothing if it's 
     * already there.)
     * @param item The item to add
     */
    public void add(E item) {
        if (! contains(item))
            internal.add(item);
    }

    /**
     * Does this set contain the item?
     * @param item The item to check
     * @return True if the item is in the set, false otherwise
     */
    public boolean contains(E item) {
        boolean foundIt = false;
        for (int i = 0; !foundIt && i < internal.size(); i++)
            foundIt |= internal.get(i).equals(item);
        return foundIt;
    }

    /**
     * Remove an item from the set, if it's there
     * (ignore otherwise).
     * @param item The item to remove
     */   
    public void remove(E item) {
        boolean removedIt = false; 
        for (int i = 0; !removedIt && i < internal.size(); i++) 
            if (internal.get(i).equals(item)) {
                internal.remove(i);
                removedIt = true;
        }
    }

    /**
     * The number of items in the set
     * @return The number of items.
     */
    public int size() {
        return internal.size();
    }

    /**
     * Is the set empty?
     * @return True if the set is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size() == 0;
    }
    
    @Override
    public String toString() {
    	String toReturn = "[";
    	boolean prefix = false;
    	for (E item : this) {
    		if (prefix)
    			toReturn += ", ";
    		toReturn += item;
    		prefix = true;
    	}
    	return toReturn +"]";
    }

}
