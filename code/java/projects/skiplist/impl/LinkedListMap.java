package impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import adt.Map;

public class LinkedListMap<K,V> implements Map<K, V> {

   protected class Node {
       K key;
       V value;
       Node next;
       Node(K key, V value, Node next) {
           this.key = key;
           this.value = value;
           this.next = next;
       }
       public String toString() {
           return "[" + key + "," + value + "]";
       }
   }

   protected Node head;

   
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            Node current = head;
            public boolean hasNext() {
                return current != null;
            }
            public K next() {
                if (! hasNext()) throw new NoSuchElementException();
                else {
                    K toReturn = current.key;
                    current = current.next;
                    return toReturn;
                }
            }
            
        };
    }

    protected Node find(K key) {
        Node current = head;
        while (current != null && ! key.equals(current.key))
            current = current.next;
        return current;
    }
     
    protected boolean nodeContainsKey(Node place, K key) {
        return place != null;
    }
    
    public void put(K key, V val) {
        Node old = find(key);
        if (! nodeContainsKey(old, key))
            head = new Node(key, val, head);
        else
            old.value = val;
    }

    public V get(K key) {
        Node found = find(key);
        if (! nodeContainsKey(found, key))
            return null;
        else
            return found.value;
    }

    public boolean containsKey(K key) {
        return nodeContainsKey(find(key), key);
    }

    public void remove(K key) {
        if (head != null) {
            if (head.key.equals(key))
                head = head.next;
            else {
                Node previous = head;
                while (previous.next != null && ! previous.next.key.equals(key))
                    previous = previous.next;
                if (previous.next != null)
                    previous.next = previous.next.next;
            }
        }
    }
    
    @Override
    public String toString() {
        String toReturn = "[";
        boolean prefix = false;
        for (K item : this) {
                if (prefix)
                        toReturn += ", ";
                toReturn += item + "=" + get(item);
                prefix = true;
        }
        return toReturn +"]";
    }

    
}
