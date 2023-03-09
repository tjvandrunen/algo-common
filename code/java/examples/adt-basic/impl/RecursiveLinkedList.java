package impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import adt.List;

public class RecursiveLinkedList<E> implements List<E> {

    private class Node {
        E datum;
        Node next;
        Node(E datum, Node next) {
            this.datum = datum;
            this.next = next;
        }
        public void add(E element) {
            if (next == null) next = new Node(element, null);
            else next.add(element);
        }
        public void set(int index, E element) {
            if (index == 0) datum = element;
            else if (next == null) throw new IndexOutOfBoundsException();
            else next.set(index-1, element);
        }
        public E get(int index) {
            if (index == 0) return datum;
            else if (next == null) throw new IndexOutOfBoundsException();
            else return next.get(index-1);            
        }
        public Node remove(int index) {
            if (index == 0) return next;
            else if (next == null) throw new IndexOutOfBoundsException();
            else {
                next = next.remove(index - 1);
                return this;
            }
        }
        public void insert(int i, E element) {
            if (i == 0) next = new Node(element, next);
            else if (next == null) throw new IndexOutOfBoundsException();
            else next.insert(i - 1, element);
        }
        public int size() {
            return 1 + (next == null? 0 : next.size());
        }
    }

    private Node head;
    
    /**
     * Append the specified element to the end of this list.
     * This increases the size by one.
     * @param element The element to be appended
     */
    public void add(E element) {
        if (head == null) head = new Node(element, null);
        else head.add(element);
    }

    /**
     * Replace the element at the specified position in this list
     * with the specified element. If the index is invalid, an 
     * IndexOutOfBoundsException is thrown.
     * @param index The index of the element to return
     * @param element The element at the specified position
     */
    public void set(int index, E element) {
        if (head == null) throw new IndexOutOfBoundsException();
        else head.set(index, element);
    }

    /**
     * Retrieve the element at the specified position in this list.
     * If the index is invalid, an IndexOutOfBoundsException is thrown.
     * @param index The index of the element to return
     * @return The element at the specified position
     */
    public E get(int index) {
        if (head == null) throw new IndexOutOfBoundsException();
        else return head.get(index);

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
        if (head == null) throw new IndexOutOfBoundsException();
        else {
            E toReturn = head.get(index);
            head = head.remove(index);
            return toReturn;
        }
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
        if (index == 0) head = new Node(element, head);
        else if (index < 0 || head == null) throw new IndexOutOfBoundsException();
        else head.insert(index - 1, element);
    }
    
    /**
     * Return the number of elements in this list.
     * @return The number of elements in this list.
     */
    public int size() {
        if (head == null) return 0;
        else return head.size();
    }

    /**
     * Return an iterator over this collection (remove() is
     * unsupported, nor is concurrent modification checked).
     */
    public Iterator<E> iterator() {
        return new Iterator<E>(){

            Node current = head;
            
            public boolean hasNext() {
                return current != null;
            }

            public E next() {
                if (current == null)
                    throw new NoSuchElementException();
                E nextElement = current.datum;
                current = current.next;
                return nextElement;
                
            }
        };
    }

}
