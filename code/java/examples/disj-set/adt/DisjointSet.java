package adt;

/**
 * DisjointSet
 * 
 * Interface to define the disjoint set (or "union-find")
 * ADT. The operations find() and union() are probably
 * all one would need; connected(), count(), iterator(),
 * and findAll() are probably feature bloat, but make
 * the example a little more interesting.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * June 1, 2015
 */


public interface DisjointSet extends Iterable<Integer> {
    
    /**
     * Find the leader of this element's set.
     * @param p An element of the universe.
     * @return The representative leader of the element's set
     * @throws NoSuchElementException If the given element is
     * beyond the bounds of this universe
     */
    int find(int p);
    
    /**
     * Merge the sets of these two elements.
     * @throws NoSuchElementException If either element is
     * beyond the bounds of this universe
     */
    void union(int p, int q);
    
    /**
     * Test to see if these two elements are in the same
     * set.
     * @return True if p and q are in the same set, false
     * otherwise.
     * @throws NoSuchElementException If either element is
     * beyond the bounds of this universe
     */
    boolean connected(int p, int q);

    /**
     * Determine the number of sets in the partition.
     * @return The number of equivalent classes.
     */
    int count();
    
    /**
     * Iterate through the elements in a set identified by
     * an element in that set.
     * @param p An element in the universe.
     * @return An Iterable that will return an Iterator that 
     * will iterate over all elements that the given one
     * is connected to, including the given one itself.
     */
    Iterable<Integer> findAll(int p);
    
}
