package subsequence;

/**
 * Subsequence
 * 
 * Placeholder for solution to the longest common subsequence problem:
 * 
 * Given two sequences represented as character arrays find (not
 * necessarily contiguous) subsequences in each sequence that are
 * equal to each other in term of content and are maximal in length,
 * represented as two arrays (returned as a two-dimensional array with
 * major dimension length 2) indicating the indices in the respective
 * given sequences that constitute the subsequence.
 * 
 * Terminology note: A substring is necessarily contiguous. A subsequence
 * is not. 
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * July 13, 2015
 */
public class Subsequence {

    /**
     * Indicators for choices made in populating
     * the table.
     */
    private static enum LCSStep {
        TAKE,   // take the character indicated by this position
        SKIP_I, // skip the character indicated by the i coordinate
        SKIP_J  // skip the character indicated by the j coordinate
    };
    
    /**
     * Find the longest common subsequence of two character 
     * sequences
     * @param a A character sequence
     * @param b Another character sequence
     * @return A two-dimensional array with major dimension length
     * 2 such that the 0th row indicates the indices of sequence a
     * constituting longest common subsequence is found and the 1st
     * row indicates the indices of sequence b constituting the
     * longest common subsequence.
     */
    public static int[][] longestCommonSubsequence(char[] a, char[] b) {
        
        // The lengths of the longest common subsequence of
        // each pair of substrings from position 0 of the
        // respective two given sequences. 
        // lengths[i][j] indicates the length of the longest
        // common subsequence between sequence a[0...i]
        // and b[0...j] where i and j are inclusive indices
        int[][] lengths = new int[a.length][b.length];

        // Whether we should take a given position in the
        // respective sequences to include in the longest
        // common subsequence. takes[i][j] == TAKE indicates that
        // the a[i] == b[j] and that that character and its
        // respective positions is part of the longest
        // common subsequence between sequence a[0...i]
        // and b[0...j]. takes[i][j] === SKIP_I indicates that
        // the longest common subsequence between a[0...i]
        // and b[0...j] is the same as that between a[0...i-1]
        // and b[0...j]. Etc.
        LCSStep[][] takes = new LCSStep[a.length][b.length];

        // special case for the first letter of BOTH
        // sequences, which we would
        // take if and only if the two sequences start with 
        // the same character
        if (a[0] == b[0]) {
            takes[0][0] = LCSStep.TAKE;
            lengths[0][0] = 1;
        }
        else {
            takes[0][0] = LCSStep.SKIP_I; // arbitrary; could be SKIP_J
            lengths[0][0] = 0;
        }
            
        // special cases for the first letter of the
        // second sequence
        for (int i = 1; i < a.length; i++)
            if (a[i] == b[0]) {
                takes[i][0] = LCSStep.TAKE;
                lengths[i][0] = 1;
            }
            else {
                takes[i][0] = LCSStep.SKIP_I;
                lengths[i][0] = lengths[i-1][0];
            }
        
        // special cases for the first letter of the
        // first sequence
        for (int j = 1; j < b.length; j++)
            if (a[0] == b[j]) {
                takes[0][j] = LCSStep.TAKE;
                lengths[0][j] = 1;
            }
            else {
                takes[0][j] = LCSStep.SKIP_J;
                lengths[0][j] = lengths[0][j-1];
            }

        // Add code: finish populating the takes table and length table

        return buildSequences(lengths, takes);
    }

    /**
     * Convert the "takes" table into the longest common subsequence
     * @param lengths The table indicating the lengths of the
     * longest common subsequences of each pair of substrings 
     * starting at 0 of their respective strings; actually needed only
     * for the length of the total longest common subsequence
     * @param takes The table indicating for each position
     * (ie, pair of substrings starting at 0) whether the last characters
     * are included in a longest common subsequence
     * @return A two-dimensional array with major dimension length
     * 2 such that the 0th row indicates the indices of sequence a
     * constituting longest common subsequence is found and the 1st
     * row indicates the indices of sequence b constituting the
     * longest common subsequence.
     */
    private static int[][] buildSequences(int[][] lengths, LCSStep[][] takes) {
        int aLen = lengths.length,
            bLen = lengths[0].length,
            subLen = lengths[aLen-1][bLen-1];
        
        int[][] sequences = new int[2][subLen];
        int i = aLen - 1,
            j  = bLen - 1,
            k = subLen - 1;
        while (k >= 0) {
            if (takes[i][j] == LCSStep.TAKE) {
                sequences[0][k] = i;
                sequences[1][k] = j;
                i--; j--; k--;
            }
            else if (takes[i][j] == LCSStep.SKIP_I) 
                i--;
            else
                j--;
            }
        return sequences;
    }
    
}
