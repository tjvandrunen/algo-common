package impl;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class IteratorUtil {

    public static class Node<T> {
        public Node(T datum, Node<T> next) {
            this.datum = datum;
            this.next = next;
        }
        private T datum;
        private Node<T> next;

    }

    public static <T> Iterator<T> arrayToIterator(final T[] array) {
         throw new UnsupportedOperationException();
    }
    
    public static <T> Iterator<T> llToIterator(final Node<T> head) {
         throw new UnsupportedOperationException();
    }

    public static <T> Iterator<T> twoDArrayToIterator(final T[][] array) {
         throw new UnsupportedOperationException();
    }
    
}
