package impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import adt.List;

/**
 * BackwardsLinkedList
 * 
 * A linked implementation of the List interface
 * where we keep a reference to the last node
 * (tail) of the list and all the links point to the
 * node coming (logically) *before* the node.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * July 9, 2014
 * @param <E> The base-type of the list
 */

public class BackwardsLinkedList<E> implements List<E> {

    /**
     * Simple node class for this list, except the link
     * is called "previous" instead of "next".
     */
    private class Node {
        E datum;
        Node previous;
        Node(E datum, Node previous) {
            this.datum = datum;
            this.previous = previous;
        }
    }

    /**
     * The last element in the list, if any.
     */
    private Node tail;

    /**
     * The number of elements in the list
     */
    int size;

    
    /**
     * Helper to check the validity of the index, throwing an
     * exception if not.
     * @param index The index in question
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("" + index);
    }

    
    /**
     * Append the specified element to the end of this list.
     * This increases the size by one.
     * @param element The element to be appended
     */
    public void add(E element) {
        tail = new Node(element, tail);
        size++;
    }

    /**
     * Replace the element at the specified position in this list
     * with the specified element. If the index is invalid, an 
     * IndexOutOfBoundsException is thrown.
     * @param index The index of the element to return
     * @param element The element at the specified position
     */
    public void set(int index, E element) {
        checkIndex(index);
        Node current = tail;
        for (int i = size - 1; i > index; i--)
            current = current.previous;
        current.datum = element;
    }

    /**
     * Retrieve the element at the specified position in this list.
     * If the index is invalid, an IndexOutOfBoundsException is thrown.
     * @param index The index of the element to return
     * @return The element at the specified position
     */
    public E get(int index) {
        checkIndex(index);
        Node current = tail;
        for (int i = size - 1; i > index; i--)
            current = current.previous;
        return current.datum;
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
        checkIndex(index);
        E removed;
        if (index == size-1) {
            removed = tail.datum;
            tail = tail.previous;
        }
        else {
            // in the end it will point to the node "after" the
            // one to remove
            Node current = tail;
            for (int i = size - 2; i > index; i--)
                current = current.previous;
            removed = current.previous.datum;
            current.previous = current.previous.previous;
        }
        size--;
        return removed;
    }

    /**
     * Return the number of elements in this list.
     * @return The number of elements in this list.
     */
    public int size() {
        return size;
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
        // can't use checkIndex() because index == size is ok here
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("" + index);

        if (index == size)
            tail = new Node(element, tail);
        else {
            Node after = tail;
            for (int i = size - 1; i > index; i--)
                after = after.previous;
            after.previous = new Node(element, after.previous);
        }
        size++;
    }
    
    /**
     * Return an iterator over this collection (remove() is
     * unsupported, nor is concurrent modification checked).
     * This first dumps the whole thing into an array and then
     * iterates over the array.
     */
    public Iterator<E> iterator() {
        @SuppressWarnings("unchecked")
        final E[] copy = (E[]) new Object[size];
        Node current = tail;
        for (int i = size-1; i >= 0; i--) {
            copy[i] = current.datum;
            current = current.previous;
        }
        
        return new Iterator<E>() {
            int i = 0;
            
            public boolean hasNext() {
                return i < copy.length;
            }

            public E next() {
                if (i >= copy.length)
                    throw new NoSuchElementException();
                else
                    return copy[i++];
            }
            
        };
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
