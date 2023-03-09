package impl;

import java.util.Iterator;

/**
 * BitSequence
 * 
 * Class to represent a sequence of bits, internally represented by 
 * an array of bytes. The supported operations are adding bits to the
 * sequence and iterating over them.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * May 18, 2020
 */
public class BitSequence implements Iterable<Boolean> {

    /**
     * The bytes storing the bits
     */
    private byte[] internal;

    /**
     * The number of bits currently represented.
     * Invariant: bits <= internal.length * 8
     */
    private int bits;

    /**
     * Constructor for an intially-empty bit sequence.
     */
    public BitSequence() { internal = new byte[32]; }

    /**
     * Constructor for a bit sequence initialized with a boolean array
     * @param barray The values of the initial bits
     */
    public BitSequence(boolean[] barray) {
        this();
        add(barray);
    }

    /**
     * Constructor for a bit sequence initialized with an iterable for booleans.
     * @param itable The iterable container of booleans for the initial bits
     */
    public BitSequence(Iterable<Boolean> itable) {
        this();
        add(itable);
    }

    /**
     * Allocate a new, larger internal representation.
     * Postcondition: internal.length has doubled
     */
    private void grow() {
         throw new UnsupportedOperationException();
    }

    /**
     * Add a new bit to the sequence
     * @param b The boolean value of the bit to be added
     */
    public void add(boolean b) {
         throw new UnsupportedOperationException();
    }

    /**
     * Add a sequence of new bits, passed as a boolean array
     * @param barray The bits to be added, as booleans
     */
    public void add(boolean[] barray) { for (boolean b: barray) add(b); }

    /**
     * Add a sequence of new bits, passed as an iterable container
     * @param itable The iterable container of booleans for the bits to be added
     */
    public void add(Iterable<Boolean> itable) { for (boolean b: itable) add(b); }

    
    /**
     * Iterate through the bits of the sequence, returning
     * them as booleans.
     */
    public Iterator<Boolean> iterator() {
         throw new UnsupportedOperationException();
    }
    
    
}
