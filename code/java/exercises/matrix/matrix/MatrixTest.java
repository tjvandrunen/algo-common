package matrix;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

public class MatrixTest {
	
	Random rand = new Random(42);
	/**
	 * Takes an array of matrix dimensions and the order in which to performs the matrix
	 * multiplications and returns the total number of scalar multiplications performed
	 * @param dimensions The dimensions of the matrices indicating
     * that matrix i is a dimensions[i] by dimensions[i+1] matrix
	 * @param multOrder The order in which the multiplications should be performed where multiplications
	 * are number sequentially left to right and multOrder[0] is the first multiplication performed
	 * @return The number of scalar multiplication done by multiplying matrices in the specified order
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
		
		
	/**
	 * Find the number of scalar multiplications that would be performed if the 
	 * matrices where multiplied in the specified order.
	 * @param order The order in which the matrices are multiplied in the 
	 * form of a string with matrix numbers and parenthesis.
	 * @param dimensions The dimensions of the matrices indicating
     * that matrix i is a dimensions[i] by dimensions[i+1] matrix
	 * @return The number of scalar multiplications required to multiply the matrices
	 * in the order that is given.
	 */
	public static int numScalarMults(int[] dimensions,String order) {
		// convert the order string to an order array that numScalar mults can read.
		int[] multOrder = new int[dimensions.length-2];
		// multiplications that have been passed but not performed
		ListStackTopEnd<Integer> stack = new ListStackTopEnd<Integer>();
		// the id of the next multiplication we will pass
		int currentMult = 0;
		// the index of the first empty spot in multOrder
		int currentIndex = 0;
		/* we treated multiplications as beings before the second matrix 
		 * beings multiplied. The easiest way to do this is ignore the first
		 * matrix and treat the location of every matrix after the first one
		 * as the location of a multiplication 
		 */
		boolean passedFirstMatrix = false;
		for (int i = 0; i < order.length(); i++) {
			if (order.charAt(i) == 'M') {
				if (passedFirstMatrix)
					stack.push(currentMult++);
				passedFirstMatrix = true;
			}
			else if (order.charAt(i) == ')') {
				multOrder[currentIndex++] = stack.pop();
			}
		}
		return numScalarMults(dimensions,multOrder);
	}

		
	@Test 
	public void trivial() {
		int[] dimensions = new int[] {10,10};
		String answer = "M0";
		assertEquals(answer, Matrix.planMatrixMult(dimensions));
	}
	
	@Test 
	public void trivial2() {
		int[] dimensions = new int[] {10,10,10};
		String answer = "(M0M1)";
		assertEquals(answer, Matrix.planMatrixMult(dimensions));
	}
	
	@Test 
	public void tiny() {
		int[] dimensions = new int[] {10,10,10};
		String answer = "(M0M1)";
		assertEquals(answer, Matrix.planMatrixMult(dimensions));
	}
	
	@Test 
	public void oneSolution() {
		int[] dimensions = new int[] {1,10,20,30};
		String answer = "((M0M1)M2)";
		assertEquals(answer, Matrix.planMatrixMult(dimensions));
	}	
	
	@Test 
	public void normal() {
		int[] dimensions = new int[] {29,32,95,10,80,68,51,26,6};
		int scalarMults = 95712;
		assertEquals(scalarMults, numScalarMults(dimensions,Matrix.planMatrixMult(dimensions)));
	}
	
	@Test 
	public void large() {
		// the number of dimensions
		final int N = 1000;
		// all the dimension are set to this scalar (won't overflow)
		int size = rand.nextInt((int)Math.floor(Math.cbrt(Integer.MAX_VALUE)*(N-2))-1)+1;
		int[] dimensions = new int[N];
		for (int i = 0; i < N; i++)
			dimensions[i] = size;
		int scalarMults = size*size*size*(N-2);
		assertEquals(scalarMults, numScalarMults(dimensions,Matrix.planMatrixMult(dimensions)));
	}
	
	@Test 
	public void stressTest() {
		// the maximum number of dimensions
		final int MAX_SIZE = 15;
		// the number of tests that will be run
		int TESTS = 10;
		
		for (int t = 0; t < TESTS; t++) {
			// the number of dimensions
			int n = rand.nextInt(MAX_SIZE-3)+3;
			// the dimensions of the matrices
			int[] dimensions = new int[n];
			
			for (int i = 0; i < n; i++) {
				// populate the dimensions from a large range that won't overflow
				dimensions[i] = rand.nextInt((int)Math.floor(Math.cbrt(Integer.MAX_VALUE)/(n-2))-1)+1;
			}
			String correct = NaiveMatrix.planMatrixMult(dimensions);
			int correctMults = numScalarMults(dimensions,correct);
			String test = Matrix.planMatrixMult(dimensions);
			int testMults = numScalarMults(dimensions,test);
			assertEquals(correctMults, testMults);
		}
	}
}
