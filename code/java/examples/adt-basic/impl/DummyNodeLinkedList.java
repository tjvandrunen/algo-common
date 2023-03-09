package impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import adt.List;

public class DummyNodeLinkedList<E> implements List<E> {

    private static interface Node<X> {
        Node<X> add(X element); //
        void set(int index, X element); //
        X get(int index); 
        Node<X> remove(int index); //
        Node<X> insert(int index, X element);
        int size();
        boolean isReal();
    }
    
    private class RealNode implements Node<E> {
        E datum;
        Node<E> next;
        RealNode(E datum, Node<E> next) {
            this.datum = datum;
            this.next = next;
        }
        public Node<E> add(E element) { 
            next = next.add(element); 
            return this;
        }
        public void set(int index, E element) {
            if (index == 0) datum = element;
            else next.set(index-1, element);
        }
        public E get(int index) { 
            if (index == 0) return datum;
            else return next.get(index-1);
        }
        public Node<E> remove(int index) {
            if (index == 0) return next;
            else {
                next = next.remove(index-1);
                return this;
            }
        }
        public Node<E> insert(int index, E element) {
            if (index == 0) return new RealNode(element, this);
            else {
                next = next.insert(index-1, element);
                return this;
            }
        }
        public int size() { return 1 + next.size(); }
        public boolean isReal() { return true; }
    }
    
    private Node<E> head = new Node<E>() {
        public Node<E> add(E element) { return new RealNode(element, this); }
        public void set(int index, E element) { throw new IndexOutOfBoundsException(); }
        public E get(int index) { throw new IndexOutOfBoundsException(); }
        public Node<E> remove(int index) { throw new IndexOutOfBoundsException(); }
        public Node<E> insert(int index, E element) {
            if (index == 0) return new RealNode(element, this);
            else throw new IndexOutOfBoundsException();
        }
        public int size() { return 0; }
        public boolean isReal() { return false; }
    };

    public void add(E element) { head = head.add(element);  }

    public void set(int index, E element) { head.set(index, element); }

    public E get(int index) { return head.get(index); }

    public E remove(int index) { 
        E toReturn = head.get(index);
        head = head.remove(index); 
        return toReturn;
    }

    public void insert(int index, E element) { head = head.insert(index, element); }

    public int size() { return head.size(); }
    
    public Iterator<E> iterator() {
        return new Iterator<E> () {
            Node<E> current = head;
            public boolean hasNext() {  return current.isReal(); }
            public E next() {
                if (! hasNext()) throw new NoSuchElementException();
                RealNode temp = (RealNode) current;
                current = temp.next;
                return temp.datum;
            }
            
        };
    }

}
