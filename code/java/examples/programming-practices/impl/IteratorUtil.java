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
        return new Iterator<T>() {
            int i = 0;
            public boolean hasNext() {  return i < array.length;  }
            public T next() {
                if (! hasNext()) throw new NoSuchElementException();
                else return array[i++];
            }
        };
    }
    
    public static <T> Iterator<T> llToIterator(final Node<T> head) {
        return new Iterator<T>() {
            Node<T> current = head;
            public boolean hasNext() {  return current != null;  }
            public T next() {
                if (! hasNext())  throw new NoSuchElementException();
                else {
                    T toReturn = current.datum;
                    current = current.next;
                    return toReturn;
                }
            }
        };
    }

    public static <T> Iterator<T> twoDArrayToIterator(final T[][] array) {
        return new Iterator<T>() {
            int i = 0, j = 0;
            public boolean hasNext() { return i < array.length; }
            public T next() {
                if (! hasNext()) throw new NoSuchElementException();
                else {
                    T toReturn = array[i][j++];
                    if (j == array[i].length) {
                        i++;
                        j = 0;
                    }
                    return toReturn;
                }
            }
        };
    }
    
}
