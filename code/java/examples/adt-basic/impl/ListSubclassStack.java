package impl;

import java.util.NoSuchElementException;

import adt.Stack;

public class ListSubclassStack<E> extends LinkedList<E> implements Stack<E> {

    public void push(E item) { add(item); }
    public E top() {
        if (size() > 0) return get(size() - 1);
        else throw new NoSuchElementException();
    }

    public E pop() {
        if (size() > 0)  return remove(size() - 1);
        else throw new NoSuchElementException();
    }

    public boolean isEmpty() { return size() == 0; }

}
