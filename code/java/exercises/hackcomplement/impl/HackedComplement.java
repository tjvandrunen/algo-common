package impl;

import java.util.Iterator;

import adt.Set;

public class HackedComplement {
    
    public static <E> Set<E> union(Set<E> a, Set<E> b) {
        Set<E> result = new ListSet<E>();
        for (E x : a) result.add(x);
        for (E x : b) result.add(x);
        return result;
    }
    
    public static <E> Set<E> intersection(Set<E> a, Set<E> b) {
        Set<E> result = new ListSet<E>();
        for (E x : a) if (b.contains(x)) result.add(x);
        return result;
    }
    
    public static <E> Set<E> difference(Set<E> a, Set<E> b) {
        Set<E> result = new ListSet<E>();
        for (E x : a) if (! b.contains(x)) result.add(x);
        return result;
    }

    public static <E> Set<E> complement(Set<E> a) {
        // A copy of the given set a
        // We want to remember the state that the given
        // set was in at the time when this method was called,
        // not be affected by subsequent changes to a
        Set<E> original = new ListSet<E>();
        for (E x : a) original.add(x);

        return new Set<E>() {

            
            public void add(E item) {
                throw new UnsupportedOperationException(); // replace
            }

            public boolean contains(E item) {
                throw new UnsupportedOperationException(); // replace
            }

            public void remove(E item) {
                original.add(item);
            }

            public int size() {
                throw new UnsupportedOperationException(); // impossible -- don't replace
            }

            public boolean isEmpty() {
                return false;
            }
            public Iterator<E> iterator() {
                throw new UnsupportedOperationException(); // impossible -- don't replace
           }
           
        };
    }
}
