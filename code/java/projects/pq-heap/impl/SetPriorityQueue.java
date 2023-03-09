package impl;

import java.util.Comparator;

import adt.PriorityQueue;
import adt.Set;

public class SetPriorityQueue<E> implements PriorityQueue<E> {

    private Set<E> internal;
    private Comparator<E> compy;
    
    public SetPriorityQueue(Iterable<E> itably, Comparator<E> compy) {
        internal = new LinkedSet<E>();
        for (E x : itably) internal.add(x);
        this.compy = compy;
    }

    public SetPriorityQueue(int size, Comparator<E> compy) {
        internal = new LinkedSet<E>();
        this.compy = compy;
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    public void insert(E key) {
        throw new UnsupportedOperationException();
    }

    public E max() {
        throw new UnsupportedOperationException();
    }

    public E extractMax() {
        throw new UnsupportedOperationException();
    }

    public boolean contains(E key) {
        throw new UnsupportedOperationException();
    }

    public void increaseKey(E key) {
        throw new UnsupportedOperationException();

    }

    public void decreaseKey(E key) {
        throw new UnsupportedOperationException();

    }

}
