package impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import adt.Map;
import adt.Stack;

public class TrieMap<V> implements Map<String, V>{

    private static class TrieNode<V> {
        TrieNode<V>[] children;
        /** 
         * Value associated with the key formed by the route
         * to this node, if any.
         */
        V value;
        /** Does the route to this node form a valid key? */
        boolean isTerminal;
        // Class invariant: isTerminal || value == null
        TrieNode() {
            children = (TrieNode<V>[]) new TrieNode[52];
            isTerminal = false;
        }
        /** Is this node non-terminal and without children? */
        boolean isDead() {
            boolean isLive = isTerminal;
            for (int i = 0; i < children.length && ! isLive; i++)
                isLive |= children[i] != null;
            return !isLive;
        }
    }
    public static class BadCharException extends RuntimeException {
        private static final long serialVersionUID = 3203665491139032638L;

        public BadCharException(char c) {
            super("Bad character: " + c);
        }
        public BadCharException(int i) {
            super("Bad character code: " + i);
        }
    }
   
    private static int c2i(char c) {
        if (c >= 'a' && c <= 'z') return c - 'a';
        else if (c >= 'A' && c <= 'Z') return 26 + c - 'A';
        else throw new BadCharException(c);
    }
    private static char i2c(int i) {
        if (i >= 0 && i < 26) return (char) ('a' + i);
        else if (i >= 26 && i < 52) return (char) ('A' + i - 26);
        else throw new BadCharException(i);
    }

    private TrieNode<V> root;
    
    public TrieMap() {
        root = new TrieNode<V>();
    }
    
    public void put(String key, V val) {
        TrieNode<V> current = root;
        for (int i = 0; i < key.length(); i++) {
            int j = c2i(key.charAt(i));
            if (current.children[j] == null) 
                current.children[j] = new TrieNode<V>();
            current = current.children[j];
        }
        current.value = val;
        current.isTerminal = true;
    }
    
    /**
     * Find the node that represents this key, or
     * return null if no such node exists.
     */
    private TrieNode<V> find(String key) {
        TrieNode<V> current = root;
        for (int i = 0; current != null && i < key.length(); i++) 
            current = current.children[c2i(key.charAt(i))];
        return current;
    }

    public V get(String key) {
        TrieNode<V> place = find(key);
        if (place == null) return null;
        else return place.value;
    }

    public boolean containsKey(String key) {
        TrieNode<V> place = find(key);
        if (place == null) return false;
        else return place.isTerminal;
    }    
    
    public void remove(String key) {
        if (root != null) {
            // The nodes along the path to the current node.
            Stack<TrieNode<V>> trace = new LinkedStack<TrieNode<V>>();
            TrieNode<V> current = root;
            trace.push(root);
            for (int i = 0; current != null && i < key.length(); i++) {
                current = current.children[c2i(key.charAt(i))];
                trace.push(current);
            }
            // Post condition: current != null iff current represents key.
            if (current != null) {
                // Remove the key by marking current not terminal.
                current.isTerminal = false;
                // Remove dead portion of the path.
                trace.pop();
                int i = key.length() - 1;
                while (current.isDead() && ! trace.isEmpty()) {
                    assert i >= 0;
                    current = trace.pop();
                    current.children[c2i(key.charAt(i))] = null;
                    i--;
                }
                if (root.isDead()) root = null;
            }
        }
        
    }
    
    
    private class StackRecord {
        TrieNode<V> node;
        String prefix;
        StackRecord(TrieNode<V> node, String prefix) {
            this.node = node;
            this.prefix = prefix;
        }
    }

    private void advanceStack(Stack<StackRecord> trace) {
        StackRecord top = trace.top();
        int i = 0;
        int j = 0;
        do {
            j++;
            if (i == 52) {
                trace.pop();
                if (trace.isEmpty()) top = null;
                else {
                    i = c2i(top.prefix.charAt(top.prefix.length()-1)) + 1;
                    top = trace.top();
                }
            }
            else if (top.node.children[i] != null) {
                //System.out.println("b");
                trace.push(new StackRecord(top.node.children[i], top.prefix + i2c(i)));
                top = trace.top();
                i = 0;
            }
            else {
                //System.out.println("c");
                i++;
            }
            
        } while (top != null && ! (top.node.isTerminal && i == 0));
    }
    
    public Iterator<String> iterator() {
        final Stack<StackRecord> trace = new LinkedStack<StackRecord>();
        trace.push(new StackRecord(root, ""));
        //System.out.println("1");
        if (! root.isTerminal) advanceStack(trace);
        //System.out.println("2");

        return new Iterator<String>() {
            public boolean hasNext() {
                return ! trace.isEmpty();
            }

            public String next() {
                if (! hasNext()) throw new NoSuchElementException();
                String toReturn = trace.top().prefix;
                advanceStack(trace);
                return toReturn;
            }
        };
    }
    public static void main(String[] args) {
        for (char c = 'a'; c <= 'z'; c++)
            System.out.println(c + " " + c2i(c));
        for (char c = 'A'; c <= 'Z'; c++)
            System.out.println(c + " " + c2i(c));
        for (int i = 0; i < 52; i++)
            System.out.println(i + " " + i2c(i));
    }

}
