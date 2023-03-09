package heroHall;

import java.math.BigInteger;

public class NaiveHeroHall {
	/**
	 * Compute the maximum amount of treasure a hero can accumulate
	 * by running through a hall, with a choice of switching sides
	 * of the hall at each step. There is treasure on the left and
	 * right of each segment of the hall. Between each segment
	 * is a guardian who charges treasure for crossing the hall
	 * (there is no cost to go straight ahead, staying on the same
	 * side of the hall when entering a new segment).
	 * @param s Array storing the amounts of treasure at each segment
	 * on the left side of the hall
	 * @param t Array storing the amounts of treasure at each segment
	 * on the right side of the hall
	 * @param g Array storing the amount the guardians between segments
	 * charge. Note there is one less guardian than segments, and
	 * g[i] charges for crossing the hall between segments i and i+1.
	 * @return The maximum amount of treasure possible
	 */
	public static int bestTreasure(int[] s, int[] t, int[] g) {
		if (s.length == 0)
			return 0;
		int max = Integer.MIN_VALUE;
		// if the nth bit is 0 we go left, if it is 1 we go right
		BigInteger path;
		for (path = BigInteger.ZERO; path.bitLength() <= s.length; path = path.add(BigInteger.ONE))
			max = Math.max(max, treasure(s,t,g,path));
		return max;	
	}
	
	private static int treasure(int[] s, int[] t, int[] g, BigInteger path) {
		int toReturn = 0;
		// true for right, false for left
		boolean prev = path.testBit(0);
		if (prev) 
			toReturn += t[0];
		else 
			toReturn += s[0];
		for (int i = 1; i < s.length; i++) {
			boolean current = path.testBit(i);
			if (current != prev)
				toReturn -= g[i-1];
			if (current)
				toReturn += t[i];
			else 
				toReturn += s[i];
			prev = current;
		}
		return toReturn;
	}
}
