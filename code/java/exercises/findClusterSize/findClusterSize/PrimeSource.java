package findClusterSize;

/**
 * PrimeSource
 * 
 * A class to contain static methods for generating prime numbers.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * July 30,  2014
 */

public class PrimeSource {

    /**
     * An array of a bunch of primes, indicated by index
     */
    private static boolean[] primes;
    
    /**
     * Recompute the array of primes (such as when we want
     * more than we had before)
     * @param bound An upper bound on the numbers we're considering for
     * prime candidacy.
     */
    public static void sieve(int bound) {
        primes = new boolean[bound];
        for (int i = 2; i < primes.length; i++)
            primes[i] = true;
        for (int i = 2; i < primes.length; i++)
            if (primes[i])
                for (int j = i * i; j < primes.length; j += i)
                    primes[j] = false;
    }
    
    /**
     * Find the next prime number greater than or equal to the given one.
     * @param x
     * @return
     */
    public static int nextOrEqPrime(int x) {
        if (x < 2) return 2;
        while (x >= primes.length || ! primes[x]) 
            if (x >= primes.length) {
                sieve(primes.length + 1000);
            }
            else
                x++;
        
        return x;
    }
    
    static {
        sieve(100);
    }

}
