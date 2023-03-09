package alg;

import java.util.Comparator;
import java.util.Iterator;

import adt.Bag;
import adt.Set;
import adt.PriorityQueue;
import impl.BitSequence;
import impl.HeapPriorityQueue;
import impl.MapBag;
import impl.MapSet;


/**
 * Class to implement the Huffman encoding.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * May 18, 2020
 */
public class Huffman {

    // ---- nested classes supporting the encoding ---
    
    /**
     * Supertype to represent nodes in a Huffman tree
     */
    private static abstract class HuffmanNode {
        /** The characters represented in the tree rooted here */
        char[] letters;
        /** The combined frequencies of all the characters in this subtree */
        int freq;

        /** 
         * Does this subtree contain the given character?
         * @param c The character to search for
         * @return True if this subtree contains the given character, false otherwise
         */
        boolean contains(char c) {
            // bounded linear search on the letters array
            boolean found = false;
            for (int i = 0; ! found && i < letters.length; i++)
                found = letters[i] == c;
            return found;
        }

        /**
         * Encode a given character by adding appropriate bits to a
         * given bit sequence.
         * Precondition: contains(c)
         * Postcondition: Bits have been added to bSeq indicating the path
         * in the tree rooted at this node to the leaf representing the given
         * character.
         * @param c The character to encode
         * @param bSeq The bits encoding the given character
         */
        abstract void encode(char c, BitSequence bSeq);

        /**
         * Find the character that results in a search from this node
         * as directed by the given boolean iterator.
         * Postcondition: The prefix of bits that leads to a leaf in the
         * tree has been expended by the iterator.
         * @param it The iterator directing the search
         * @return The character found
         */
        abstract char decode(Iterator<Boolean> it);

        /** 
         * Retrieve the combined frequencies of all the characters 
         * in this subtree 
         * @return The combined frequency.
         */
        int getFreq() { return freq; }
    }

    /**
     * Class to represent a leaf in a Huffman tree
     */
    private static class Leaf extends HuffmanNode {

        /** Constructor, taking a character and its frequency */
        Leaf(char c, int freq) { 
            letters = new char[1];
            letters[0] = c;
            this.freq = freq;
        }
        
        /** 
         * Encode a given character by adding appropriate bits to a
         * given bit sequence.
         * Precondition: This leaf represents the given character
         * Postcondition: No bits (that is, an empty bit sequence) has been added
         * to the given bitsequence
         */
        public void encode(char c, BitSequence bSeq) {
            assert letters[0] == c;
        }

        /**
         * Find the character that results in a search from this node
         * as directed by the given boolean iterator.
         * Postcondition: No bits have been expended by the iterator.
         * @param it The iterator directing the search
         * @return The character represented here
         */
        public char decode(Iterator<Boolean> it) { return letters[0]; }
    }

    /**
     * Class representing an internal node in a Huffman tree.
     *
     */
    private static class Internal extends HuffmanNode {
        /** The children of this node */
        HuffmanNode left, right;

        /**
         * Constructor taking the two children
         */
        Internal(HuffmanNode left, HuffmanNode right) {
            this.left = left;
            this.right = right;
            letters = new char[left.letters.length + right.letters.length];
            int i = 0;
            for (int j = 0; j < left.letters.length; j++)
                letters[i++] = left.letters[j];
            for (int j = 0; j < right.letters.length; j++)
                letters[i++] = right.letters[j];
        }
        
        /**
         * Encode a given character by adding appropriate bits to a
         * given bit sequence.
         */
        public void encode(char c, BitSequence bSeq) {
             throw new UnsupportedOperationException();
        }

        /**
         * Find the character that results in a search from this node
         * as directed by the given boolean iterator.
         */
       public char decode(Iterator<Boolean> it) {
           // This call consumes exactly one value from the iterator
           assert it.hasNext();
           return (it.next()? left : right).decode(it);
        }
    }
    
    /**
     * Class to represent encoded messages.
     * No public interface---this is returned from encode()
     * and can be passed to decode().
     */
    public static class EncodedMessage {
        private EncodedMessage(HuffmanNode root, BitSequence bits) {
            this.root = root;
            this.bits = bits;
        }
        private final HuffmanNode root;
        private final BitSequence bits;
    }
    
    /**
     * Encode a message
     * @param msg The plaintext to be encoded.
     * @return An object represented an encoded version of the text
     */
    public static EncodedMessage encode(String msg) {
        // Analyze the characters in msg by frequency
        Bag<Character> freqs = new MapBag<Character>();
        Set<Character> chars = new MapSet<Character>();
        for (char c : msg.toCharArray()) {
            freqs.add(c);
            chars.add(c);
        }
        int numChars = chars.size();

        // Initialize the worklist as a priority queue
        PriorityQueue<HuffmanNode> worklist = new HeapPriorityQueue<HuffmanNode>(
                numChars, 
                 null // need a Comparator
                );

        // The minimal prefix-code key for this message
        HuffmanNode key;

        // --- Compute minimal key ---
         // ADD CODE HERE

        // Encode the message using the key
        BitSequence bits = new BitSequence();
        for (char c : msg.toCharArray())
            key.encode(c, bits);
        return new EncodedMessage(key, bits);
    }
    
    /**
     * Decode a message.
     * @param msg An object standing for an encoded message
     * @return The plaintext decoded message.
     */
    public static String decode(EncodedMessage msg) {
        String decodedMsg = "";
        for (Iterator<Boolean> it = msg.bits.iterator(); it.hasNext();)
            decodedMsg += msg.root.decode(it);
        return decodedMsg;
    }
    
}
