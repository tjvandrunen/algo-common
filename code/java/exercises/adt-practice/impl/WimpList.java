package impl;

import java.util.Iterator;

import adt.List;

/**
 * WimpList
 * 
 * A wimpy implementation of the List interface
 * that delegates all the operations to the ArrayList
 * in the Java API.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * July 9, 2014
 * @param <E> The base-type of the list
 */

public class WimpList<E> implements List<E> {

    /**
     * The internal representation that everything gets
     * delegated to (note it doesn't have to be an ArrayList;
     * any java.util.List will do).
     */
    private java.util.List<E> internal;

    /**
     * Constructor. This is the only place we choose a
     * java.util.ArrayList in particular.
     */
    public WimpList() {
        internal = new java.util.ArrayList<E>();
    }


    /**
     * Append the specified element to the end of this list.
     * This increases the size by one.
     * @param element The element to be appended
     */
    public void add(E element) {
        internal.add(element);
    }

    /**
     * Replace the element at the specified position in this list
     * with the specified element. If the index is invalid, an 
     * IndexOutOfBoundsException is thrown. (java.util.ArrayList.set()
     * will throw the appropriate exception, according to the API.)
     * @param index The index of the element to return
     * @param element The element at the specified position
     */
    public void set(int index, E element) {
        internal.set(index, element);
    }

    /**
     * Retrieve the element at the specified position in this list.
     * If the index is invalid, an IndexOutOfBoundsException is thrown.
     * @param index The index of the element to return
     * @return The element at the specified position
     */
    public E get(int index) {
        return internal.get(index);
    }

    /**
     * Remove (and return) the element at the specified position.
     * This reduces the size of the list by one and, if necessary,
     * shifts other elements over. If the index is invalid, an 
     * IndexOutOfBoundsException is thrown.
     * @param index The index of the element to remove
     * @return The item removed
     */
    public E remove(int index) {
        return internal.remove(index);
    }

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
    public void insert(int index, E element) {
        internal.add(index, element);
    }

    /**
     * Return the number of elements in this list.
     * @return The number of elements in this list.
     */
    public int size() {
        return internal.size();
    }

    /**
     * Return an iterator over this collection.
     */
    public Iterator<E> iterator() {
        return internal.iterator();
    }
    
    @Override
    public String toString() {
    	return internal.toString();
    }



}
