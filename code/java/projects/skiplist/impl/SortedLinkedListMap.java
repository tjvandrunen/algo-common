package impl;


public class SortedLinkedListMap<K extends Comparable<K>, V> extends LinkedListMap<K, V> {

    @Override
    protected Node find(K key) {
        if (head == null || key.compareTo(head.key) < 0)
            return null;
        else {
            Node previous = head, current = head.next;
            while (current != null &&  current.key.compareTo(key) <= 0) {
                previous = current;
                current = current.next;
            }
            return previous;
        }
    }
    
    @Override
    protected boolean nodeContainsKey(Node place, K key) {
        return place != null && place.key.compareTo(key) == 0;
    }
    
    @Override
    public void put(K key, V val) {
        Node old = find(key);
        if (nodeContainsKey(old, key))
            old.value = val;
        else if (old == null)
            head = new Node(key, val, head);
        else 
            old.next = new Node(key, val, old.next);
        
    }
    
}
