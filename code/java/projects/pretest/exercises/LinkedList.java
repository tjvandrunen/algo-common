package exercises;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements Iterable<E> {


    /**
     * Simple node class for this list.
     */
    private class Node {
        E datum;
        Node next;
        Node(E datum, Node next) {
            this.datum = datum;
            this.next = next;
        }
    }
    
    /**
     * The head node for this list
     */
    private Node head;
    
    /**
     * The last node in the list (to optimize add()) 
     */
    private Node tail;
    
    /**
     * The number of nodes in this list (to optimize size())
     */
    private int size;

    /**
     * Completely unnecessary constructor. It's here to assert
     * that we intend this all to happen, even though it would
     * happen by default.
     */
    public LinkedList() { 
        head = tail = null;
        size = 0;
    }

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
        // Special case: what if the list is empty?
        // The following four predicates should be equivalent (all iff each other):
        // 1. The list is empty
        // 2. tail == null
        // 3. head == null
        // 4. size == 0
        // We will check for #1 by testing #2
        if (tail == null) {
                // Make sure #3 and #4 also hold.
                assert size == 0 && head == null;
                // Add a new node as the new sole element, both head and tail
                head = tail = new Node(element, null);
        }
        // Normal case: list is not empty, so link the tail into a new node
        // and make that new node the tail
        else {
            tail.next = new Node(element, null);
            tail = tail.next;
        }
        // In either case, increase the size.
        size++;
    }

    /**
     * Retrieve the element at the specified position in this list.
     * If the index is invalid, an IndexOutOfBoundsException is thrown.
     * @param index The index of the element to return
     * @return The element at the specified position
     */
    public E get(int index) {
         throw new UnsupportedOperationException();
    }

    /**
     * Replace the element at the specified position in this list
     * with the specified element. If the index is invalid, an 
     * IndexOutOfBoundsException is thrown.
     * @param index The index of the element to return
     * @param element The element at the specified position
     */
    public void set(int index, E element) {
         throw new UnsupportedOperationException();
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
         throw new UnsupportedOperationException();
    }

    /**
     * Return the number of elements in this list.
     * @return The number of elements in this list.
     */
    public int size() {
        return size;
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

            public void remove() {
                throw new UnsupportedOperationException();
            }};
        
    }
    
}
