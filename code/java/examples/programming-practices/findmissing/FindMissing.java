package findmissing;

public class FindMissing {

    /**
     * Find the missing number in a list that otherwise
     * would have the numbers in the range [0, sequence.length()].
     * @param sequence An array containing only the unique elements
     * in the range [0, sequence.length()], sorted.
     * @return The number in the range [0, sequence.length()]
     * that is absent from sequence
     */
    public static int findMissing(int[] sequence) {
        int n = sequence.length;
        if (sequence[0] != 0) return 0;
        else if (sequence[n-1] == n-1) return n;
        else {
            int start = 0,
                stop = n - 1;
            assert sequence[start] == start && sequence[stop] == stop + 1;
            // Invariant: 
            //     sequence[start] == start && sequence[stop] == stop + 1
            while (stop > start + 1) {
                int mid = (stop + start) / 2;
                if (sequence[mid] == mid) start = mid;
                else {
                    assert sequence[mid] == mid + 1;
                    stop = mid;
                }
            }
            return stop;
        }
    }
    
}
