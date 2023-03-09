package adt;

/**
 * List
 * 
 * Interface to serve as a simplified version of Java's java.util.List 
 * interface and as an example of the List ADT. Any iterator returned by
 * the list should iterate through the elements of the list in order.
 * 
 * The toString method for list should return
 * the elements of the list, in order, separated by commas. The list should 
 * be bordered by square braces. For instance a list with elements A, B, and C
 * in that order must return the String "[A, B, C]". 
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * July 9, 2014
 * @param <E> The base-type of the list
 */

public interface List<E> extends Iterable<E> {

    /**
     * Append the specified element to the end of this list.
     * This increases the size by one.
     * @param element The element to be appended
     */
    void add(E element);

    /**
     * Replace the element at the specified position in this list
     * with the specified element. If the index is invalid, an 
     * IndexOutOfBoundsException is thrown.
     * @param index The index of the element to return
     * @param element The element at the specified position
     */
    void set(int index, E element);

    /**
     * Retrieve the element at the specified position in this list.
     * If the index is invalid, an IndexOutOfBoundsException is thrown.
     * @param index The index of the element to return
     * @return The element at the specified position
     */
    E get(int index);

    /**
     * Remove (and return) the element at the specified position.
     * This reduces the size of the list by one and, if necessary,
     * shifts other elements over. If the index is invalid, an 
     * IndexOutOfBoundsException is thrown.
     * @param index The index of the element to remove
     * @return The item removed
     */
    E remove(int index);

    /**
     * Insert a new item at the specified position, shifting the
     * item already at the position and everything after it over
     * one position. If the index is equal to the length of the list,
     * then this is equivalent to the add method. If the index is 
     * negative or is greater than the length, an IndexOutOfBoundsException 
     * is thrown.
     * @param index The index into which to insert the element
     * @param element The element which to insert
     */
    void insert(int index, E element);
    
    /**
     * Return the number of elements in this list.
     * @return The number of elements in this list.
     */
    int size();
}
