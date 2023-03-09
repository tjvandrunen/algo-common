package matrix;

import java.util.Arrays;

import org.junit.Test;

/**
 * Matrix
 * 
 * Brute forces the matrix problem
 */
public class NaiveMatrix {

	/**
	 * Compute the best order of multiplication for a given sequence of
	 * matrices.
	 * 
	 * @param dimensions
	 *            The dimensions of the matrices indicating that matrix i is a
	 *            dimensions[i] by dimensions[i+1] matrix.
	 * @return The optimal order of multiplications represented by a string
	 *         showing the matrices optimally parenthesized
	 */
	public static String planMatrixMult(int[] dimensions) {
		int numMatrices = dimensions.length - 1;
		int numMults = numMatrices - 1;
		/*
		 * If the matrices are A,B,C,D and are multiplied in the order ABCD we
		 * call AB multiplication 0 BC multiplication 1, and CD multiplication
		 * 2.
		 * 
		 * The following array will be iterated through all the permutations of
		 * the set of integers between 0 (inclusive) and numMults (exclusive).
		 * If we allow the order of the the numbers if the array represent the
		 * order in the which the multiplications are performed we will have
		 * examined every possible way to multiply the matrices.
		 */
		int[] multOrder = new int[numMults];
		int[] minMultOrder = new int[numMults];
		for (int i = 0; i < numMults; i++)
			multOrder[i] = i;

		/*
		 * iterate through all permutation of multOrder using algorithm P from
		 * taocp 7.2.1.2
		 */
		int[] c = new int[numMults];
		int[] o = new int[numMults];
		for (int j = 1; j <= numMults; j++) {
			c[j - 1] = 0;
			o[j - 1] = 1;
		}
		// the fewest multiplications so far
		int min = Integer.MAX_VALUE;
		boolean terminated = false;
		while (!terminated) {
			// examine this ordering, and find the minimum
			int mults = numScalarMults(dimensions, multOrder);
			if (mults < min) {
				min = mults;
				minMultOrder = Arrays.copyOf(multOrder, multOrder.length);
			}
			// prepare for the change
			int j = numMults;
			int s = 0;
			boolean done = false;
			while (!done) {
				int q = c[j - 1] + o[j - 1];
				if (q < 0 || q == j) {
					if (q == j) {
						if (j == 1)
							terminated = done = true;
						else
							s++;
					}
					o[j - 1] = -o[j - 1];
					j--;
				} else {
					int temp = multOrder[j - c[j - 1] + s - 1];
					multOrder[j - c[j - 1] + s - 1] = multOrder[j - q + s - 1];
					multOrder[j - q + s - 1] = temp;
					c[j - 1] = q;
					done = true;
				}
			}
		}
		String[] matrices = new String[numMatrices];
		for (int i = 0; i < numMatrices; i++)
			matrices[i] = "M"+i;
		String toReturn = printResult(minMultOrder,matrices,0,numMatrices);
		return toReturn;
	}
	
	static String printResult(int[] multOrder, String[] matrices, int i, int numMatrices){
		if (numMatrices == 1)
			return matrices[0];
		// the multiplication that will be performed on this round
		int mult = multOrder[i];
		matrices[mult] = "(" + matrices[mult] + matrices[mult+1] + ")";
		for (int j = mult+1; j < numMatrices-1; j++)
			matrices[j] = matrices[j+1];
		for (int j = i+1; j < multOrder.length; j++) {
			if (multOrder[j] > multOrder[i])
					multOrder[j]--;
		}
		return printResult(multOrder,matrices,i+1,numMatrices-1);
	}
		
	/* takes an array of matrix dimensions and the order in which to performs the matrix
	 * multiplications and returns the total number of scalar multiplications performed
	 */
	static int numScalarMults(int[] dimensions, int[] multOrder) {
		int[] dimCopy = Arrays.copyOf(dimensions,dimensions.length);
		int[] multCopy = Arrays.copyOf(multOrder, multOrder.length);
		int total = 0;
		for (int i = 0; i < multCopy.length; i++) {
			total += dimCopy[multCopy[i]]*dimCopy[multCopy[i]+1]*dimCopy[multCopy[i]+2];
	
			for (int j = multCopy[i]+1; j < dimCopy.length-1;j++) {
				dimCopy[j] = dimCopy[j+1];
			}
			for (int j = i+1; j < multCopy.length; j++) {
				if (multCopy[j] > multCopy[i])
						multCopy[j]--;
			}
		}
		return total;
	}

}
