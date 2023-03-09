package subsequence;

import static org.junit.Assert.*;

import org.junit.Test;

public class LCSTest {

    
    private void check(char[] a, char[] b, int[][] subseq, int longestSize) {
        assertEquals(2, subseq.length);
        for (int i = 0; i < subseq[0].length; i++) {
            assertEquals(a[subseq[0][i]], b[subseq[1][i]]);
            if (i < subseq.length - 1) {
                assertTrue(subseq[0][i] < subseq[0][i+1]);
                assertTrue(subseq[1][i] < subseq[1][i+1]);
            }
        }
        assertEquals(longestSize, subseq[0].length);
    }
    
    @Test
    public void trivial() {
        char[] a = "AAAAAAA".toCharArray();
        char[] b = "AAAA".toCharArray();
        check(a, b, Subsequence.longestCommonSubsequence(a, b), 4);
        a = "AAAAAAA".toCharArray();
        b = "BBBB".toCharArray();
        check(a, b, Subsequence.longestCommonSubsequence(a, b), 0);
    }
    
    @Test
    public void clrs() {
        // CLRS pg 391
        char[] a = "ABCBDAB".toCharArray();
        char[] b = "BDCABA".toCharArray();
        check(a, b, Subsequence.longestCommonSubsequence(a, b), 4);
    }

    @Test
    public void clrsDNA() {
        // CLRS pg 391
        char[] a = "ACCGGTCGAGTGCGCGGAAGCCGGCCGAA".toCharArray();
        char[] b = "GTCGTTCGGAATGCCGTTGCTCTGAAA".toCharArray();
        check(a, b, Subsequence.longestCommonSubsequence(a, b), 20);
    }
    
    @Test
    public void js() {
        // Johnsonbaugh and Schaefer, pg 343
        char[] a = "GDVEGTA".toCharArray();
        char[] b = "GVCEKST".toCharArray();
        check(a, b, Subsequence.longestCommonSubsequence(a, b), 4);
    }

    @Test
    public void dpv() {
        // Dasgupta et al, pg 157 (adapted from Longest Increasing
        // Subsequence problem
        char[] a = "52863697".toCharArray();
        char[] b = "123456789".toCharArray();
        check(a, b, Subsequence.longestCommonSubsequence(a, b), 4);
    }

    
}
