package impl;

import java.util.Iterator;
import adt.Map;
import adt.List;

/**
 * MapList
 * 
 * An implementation of List that uses a Map as its
 * underlying implementation.
 * 
 * Algorithmic Commonplaces
 * Spring 2016
 * @param <E> The base-type of the list
 */
public class MapList<E> implements List<E> {

    /**
     * The internal representation (can be any 
     * implementation of map).
     */
    private Map<Integer, E> internal;
    //begin solution, replace with:
    private int size;
    //end solution
    
    /**
     * Constructor that is given the internal representation.
     * From a software development perspective, that's a bad idea
     * (breaks encapsulation), but for the purpose of this project 
     * it allows us to parameterize this class by what implementation
     * of Map we use. (Maybe in a future version we'll use 
     * reflection instead).
     */
    public MapList() {
        this.internal = new ArrayMap<Integer,E>();
        //begin solution, replace with:
        size = 0;
        //end solution
    }
    
    /**
     * Return an iterator over this collection (remove() is
     * unsupported, nor is concurrent modification checked).
     */
    public Iterator<E> iterator() {
        //begin solution, replace with: throw new UnsupportedOperationException();
        
        return new Iterator<E>() {
        	int i = 0;
            public boolean hasNext() {
                return internal.containsKey(i);
            }

            public E next() {
                return internal.get(i++);
            }
            
        };
        //end solution
    }

    /**
     * Append the specified element to the end of this list.
     * This increases the size by one.
     * @param element The element to be appended
     */
    public void add(E element) {
        //begin solution, replace with: throw new UnsupportedOperationException();
        internal.put(size++, element);
        //end solution
    }

    /**
     * Replace the element at the specified position in this list
     * with the specified element. If the index is invalid, an 
     * IndexOutOfBoundsException is thrown.
     * @param index The index of the element to return
     * @param element The element at the specified position
     */
    public void set(int index, E element) {
        //begin solution, replace with: throw new UnsupportedOperationException();
        if (index <0 || index >= size)
            throw new IndexOutOfBoundsException();
        else 
            internal.put(index, element);
        //end solution
    }

    /**
     * Retrieve the element at the specified position in this list.
     * If the index is invalid, an IndexOutOfBoundsException is thrown.
     * @param index The index of the element to return
     * @return The element at the specified position
     */
    public E get(int index) {
        //begin solution, replace with: throw new UnsupportedOperationException();
        if (index <0 || index >= size)
            throw new IndexOutOfBoundsException();
        else 
            return internal.get(index);
        //end solution
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
        //begin solution, replace with: throw new UnsupportedOperationException();
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        for (int i = size; i > index; i--)
            internal.put(i, internal.get(i-1));
        internal.put(index, element);
        size++;
        //end solution
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
       //begin solution, replace with: throw new UnsupportedOperationException();
       if (index <0 || index >= size)
           throw new IndexOutOfBoundsException();
       E toReturn = internal.get(index);
       for (int i = index; i < size - 1; i++)
           internal.put(i, internal.get(i+1));
       internal.remove(--size);
       return toReturn;
       //end solution
   }

   /**
    * Return the number of elements in this list.
    * @return The number of elements in this list.
    */
    public int size() {
        //begin solution, replace with: throw new UnsupportedOperationException();
        return size;
        //end solution
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
