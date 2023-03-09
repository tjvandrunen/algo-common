package impl;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import adt.Map;
/**
 * A SkipList is a modified version of the linked list with
 * extra pointers that allow searches to skip some nodes. Each node
 * is assigned a level k between 0 and maxLevel inclusive. That node is 
 * then considered to be in every level from 0 to k inclusive. It is given
 * k+1 pointers which point to the next node in every level it is a part of.
 * Levels are assigned randomly.
 *
 * @param <K> The type of the keys this skiplist holds.
 * @param <V> The type of the valuses this skiplist holds.
 */
public class SkipListMap<K extends Comparable<K>, V> implements Map<K, V> {
    /**
     *  
     * @param <K> The type of the key this Node holds.
     * @param <V> The type of the value this Node holds.
     */
    private class Node {
        //nexts[i] is the next node in level i
        Node[] nexts;
        K key;
        V val;

        @SuppressWarnings("unchecked")
        public Node(K key, V val, int level) {
            this.key = key;
            this.val = val;
            this.nexts = new SkipListMap.Node[level+1];
        }
        
        /**
         * The greatest sublist (indexed from 0) that this
         * node is in. This is one less than the number
         * of references the node has.
         */
        public int level() { return nexts.length-1; }
        
        public String toString(){
            return "("+key+","+val+","+level()+")";
        }
    }
    
    /**
     * Returned by the search method. A SearchResult holds the node searched for 
     * if it was found and an array with the maximal element of the set of nodes bounded 
     * exclusively above by the search key at every level.
     *
     */
    private class SearchResult {
        /**
         * The previous node at each level, up to topLevel, 
         * null if there was no previous node
         */
        Node prevs[];
        /**
         *  the Node that was found, or null if it was not found
         */
        Node resultNode;
        public SearchResult(Node[] prevs, Node resultNode) {
            this.prevs = prevs;
            this.resultNode = resultNode;
        }
    }

    /**
     *  The head node of each level
     */
    private Node[] heads;

    /**
     *  The highest currently used level
     */
    private int topLevel;

    /** 
     * The inverse of p, the fraction of nodes with at some level that
     * are also at the next level, or the average number of nodes to count off in
     * a sublist to get to a node in the next level sublist.
     * When a node is allocated, it is assigned a level randomly. The probability of it 
     * being assigned level k is p^k - p^(k+1), unless k=maxLevel, in which case the probability
     * p^k. 
     */
    private int d;
    
    /**
     * Upperbound for random numbers generated for determining
     * a new node's level, which is d^maxLevel
     */
    private int maxRand;
    
    /**
     * A random number generator for the skip list.
     */
    private Random rand;

    /**
     * 
     * @param pInverse
     * @param maxNumLevels the maximum number of skip levels, should be about 
     * lg(n) where n is the number of elements
     */
    @SuppressWarnings("unchecked")
    public SkipListMap(int pInverse, int maxNumLevels) {
        this.d = pInverse;
        topLevel = -1;
        heads = new SkipListMap.Node[maxNumLevels];
        rand = new Random(System.currentTimeMillis());
        maxRand = (int) Math.pow(d, maxLevel());
    }

    public SkipListMap() {
        this(4, 16);
    }

    private int maxLevel() {
        return heads.length - 1;
    }
    
    /**
     * Iterators over all the keys by simply iterating through level 0.
     */
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            Node current = heads[0];
            public boolean hasNext() {
                return current != null;
            }
            public K next() {
                if (!hasNext()) throw new NoSuchElementException();
                else {
                    Node toReturn = current;
                    current = current.nexts[0];
                    return toReturn.key;
                }
            }
        };
    }

    /**
     * Add an association to the map.
     * @param key The key to this association
     * @param val The value to which this key is associated
     */
    public void put(K key, V val) {
        SearchResult result = find(key);
        if (result.resultNode != null) { // update old key
            result.resultNode.val = val;
        } 
        else {                          // insert new key
            int insertLevel = randLevel();
            Node newNode = new Node(key, val, insertLevel);
            if (topLevel < insertLevel)
                topLevel = insertLevel;
            for (int i = 0; i <= insertLevel; i++) {
                if (i >= result.prevs.length || result.prevs[i] == null){
                    newNode.nexts[i] = heads[i];
                    heads[i] = newNode;
                } else {
                    newNode.nexts[i] = result.prevs[i].nexts[i];
                    result.prevs[i].nexts[i] = newNode;
                }
            }
        }
    }

    /**
     * Get the value for a key.
     * @param key The key whose value we're retrieving.
     * @return The value associated with this key, null if none exists
     */
    public V get(K key) {
        SearchResult result = find(key);
        return result.resultNode == null ? null : result.resultNode.val;
    }

    /**
     * Test if this map contains an association for this key.
     * @param key The key to test.
     * @return true if there is an association for this key, false otherwise
     */
    public boolean containsKey(K key) {
        return find(key).resultNode != null;
    }

    /**
     * Remove the association for this key, if it exists.
     * @param key The key to remove
     */
    public void remove(K key) {
        SearchResult result = find(key);
        if (result.resultNode != null)
            unlink(result.prevs,result.resultNode);
    }

    /**
     * A helper method for remove that removes an individual node.
     * @param prevs The previous nodes to the one being removed at every level,
     *              or null if it was the head at that level.
     * @param toUnlink The nodes that will be removed.
     */
    private void unlink(Node[] prevs,Node toUnlink){
         throw new UnsupportedOperationException();
    }

    /**
     * Looks for a key and returns the previous Nodes on every level, if they exist,
     * and the Node with the key, if it exists.
     * @param key
     * @return a valid SearchResult object
     */
    @SuppressWarnings("unchecked")
    private SearchResult find(K key) {
         throw new UnsupportedOperationException();
    }

    /** 
     * @return An integer between 0 and maxLevel inclusive. The probability of the returned
     * number k is (1/pInverse)^(k+1), unless k=maxLevel, in which case the probability is
     * 1 - ((1/pInverse)^1 + .. + (1/pInverse)^k)
     */
    private int randLevel() {
        int nonce = rand.nextInt(maxRand);
        int level = 0;
        while (level < maxLevel() && nonce % d == 0) {
            level++;
            nonce /= d;
        }
        return level;
    }

}
