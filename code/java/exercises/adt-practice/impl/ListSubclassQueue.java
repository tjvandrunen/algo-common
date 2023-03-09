package impl;

import java.util.NoSuchElementException;

import adt.Queue;

public class ListSubclassQueue<E> extends LinkedList<E> implements Queue<E> {

    public void enqueue(E item) { add(item); }
    public E front() {
        if (size() == 0) throw new NoSuchElementException();
        else return get(0);
    }
    public E remove() {
        if (size() == 0) throw new NoSuchElementException();
        else return remove(0);
    }
    public boolean isEmpty() { return size() == 0; }
}
