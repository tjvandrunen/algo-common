package impl;

import static impl.OptimalBSTMap.dummy;

import java.util.Arrays;

import impl.OptimalBSTMap.Internal;
import impl.OptimalBSTMap.Node;

/**
 * OptimalBSTMapFactory
 * 
 * Build an optimal BST, given the keys, values, key probabilities and miss
 * probabilities.
 * 
 * @author Thomas VanDrunen Algorithmic Commonplaces Feb 25, 2015
 */

public class NaiveOptimalBSTMapFactory {

	/**
	 * Exception to throw if the input to building an optimal BST is not right:
	 * either the number of keys, values, key probs, and miss probs aren't
	 * consistent, or the total probability is not 1.
	 */
	public static class BadOptimalBSTInputException extends RuntimeException {
		private static final long serialVersionUID = -444687298513060315L;

		private BadOptimalBSTInputException(String msg) {
			super(msg);
		}
	}

	/**
	 * Build an optimal BST from given raw data, passed as a single object. A
	 * convenient overloading of the other buildOptimalBST().
	 * 
	 * @param rawData
	 *            The collection of data for building this BST
	 * @return A BST with the given keys and values, optimal with the given
	 *         probabilities.
	 */
	public static OptimalBSTMap buildOptimalBST(OptimalBSTData rawData) {
		return buildOptimalBST(rawData.keys, rawData.values, rawData.keyProbs, rawData.missProbs);
	}

	/**
	 * Build an optimal BST from given raw data, passed as individual arrays.
	 * 
	 * @param keys
	 *            The keys for the tree, IN ORDER
	 * 
	 * @param values
	 *            The values for the tree such that values[i] corresponds to
	 *            keys[i]
	 * 
	 * @param keyProbs
	 *            keyProbs[i] is the probability of keys[i] begins accessed
	 * 
	 * @param missProbs
	 *            missProbs[i] is the probability of a miss between keys i-1 and
	 *            i
	 * 
	 * @return A BST with the given keys and values, optimal with the given
	 *         probabilities.
	 */
	public static OptimalBSTMap buildOptimalBST(String[] keys, String[] values, double[] keyProbs, double[] missProbs) {

		// keep these checks
		checkLengths(keys, values, keyProbs, missProbs);
		checkProbs(keyProbs, missProbs);

		int n = keyProbs.length;

		// uses taocp 7.2.1.6 algorithm L to generate all trees
		// Note that one based indexing is used here
		// Each node is assigned a positive integer and 0 is null. For us, node
		// i is keyProbs[i-1].
		/*
		 * The left and right links in integer form, where l0 is the root and ri
		 * and li are the right an left children of node i. Note that r0 is
		 * unused.
		 */
		int[] l = new int[n + 1], r = new int[n + 1];
		// auxiliary arrays
		int[] k = new int[n + 1], o = new int[n + 1];

		// the minimum cost so far
		double min = Double.POSITIVE_INFINITY;
		// a tree with cost min
		int[] minr = null, minl = null;

		// initialization (L1)
		for (int j = 1; j < n; j++) {
			l[j] = 0;
			r[j] = j + 1;
			k[j] = j - 1;
			o[j] = -1;
		}
		l[0] = o[0] = 1;
		l[n] = r[n] = 0;
		k[n] = n - 1;
		o[n] = -1;
		boolean done = false;
		while (!done) {
			// examine this tree (L2)
			double currentCost = cost(keyProbs, missProbs, r, l);
			if (currentCost < min) {
				min = currentCost;
				minr = Arrays.copyOf(r, r.length);
				minl = Arrays.copyOf(l, l.length);
			}
			int j = n;
			int p = 0;

			// L3
			while (true) {
				int m = -1;
				if (o[j] > 0) {
					m = l[j];
					if (m != 0) {
						// rotate right
						if (j == 0) {
							done = true;
							break;
						}
						l[j] = r[m];
						r[m] = j;
						k[j] = m;
						int x = k[m];
						if (x == 0)
							l[p] = m;
						else
							r[x] = m;
					}
				}
				if (o[j] < 0) {
					m = k[j];
					if (m != 0) {
						// rotate left
						r[m] = l[j];
						l[j] = m;
						int x = k[m];
						k[j] = x;
						if (x == 0) {
							l[p] = j;
						} else {
							r[x] = j;
						}
					} else {
						p = j;
					}
				}
				if (m == 0) {
					o[j] = -o[j];
					j--;
					// repeat L3
					continue;
				} else {
					break;
				}
			}

		}
		// to keep track of the nodes while building
		Internal[] nodes = new Internal[keys.length];
		//build the tree recursively
		return new OptimalBSTMap(buildTree(keys,values,minl,minr,minl[0],nodes));
	}
	/**
	 * Build the tree by populating internal.
	 * 
	 * @param keys the keys for the tree
	 * @param values the values for the tree
	 * @param l l[i] is the left child of node i
	 * @param r r[i] is the right child of node i
	 * @param root the root of the tree being built
	 * @param nodes an array containing Internal nodes for every node that has been created and null for every node that has not
	 * @return the root of the built tree
	 */
	private static Node buildTree(String[] keys, String[] values, int l[], int r[], int root, Internal[] nodes) {
		if (root == 0)
			return dummy;
		return new Internal(buildTree(keys,values,l,r,l[root],nodes), keys[root-1],values[root-1],buildTree(keys,values,l,r,r[root],nodes));
	}
		
	/**
	 * Returns the cost of the tree represented by r and l with the probabilities keyProbs and missProbs.
	 * The cost of each node and dummy is its depth times its probability. The cost of the tree is the sum of 
	 * the cost of the nodes plus 1.
	 * 
	 * @param keyProbs
	 * @param missProbs
	 * @param r
	 * @param l
	 * @return
	 */
	public static double cost(double[] keyProbs, double[] missProbs, int[] r, int[] l) {
		double treeCost = 1.0;
		int depth = 0;
		// visit nodes pre-order and add their (and the dummy nodes) costs
		ListStack<Integer> nodeStack = new ListStack<Integer>();
		// parallel stack to nodeStack, holds each nodes depth
		ListStack<Integer> depthStack = new ListStack<Integer>();
		int current = l[0];
		while (current != 0 || !nodeStack.isEmpty()) {

			while (current != 0) {
				// count the node
				treeCost += ((double)depth) * keyProbs[current - 1];
				// push the node
				nodeStack.push(current);
				depthStack.push(depth);
				depth++;
				current = l[current];
			}
			// count the dummy (note that this dummy is left of stack.top()
			treeCost += ((double) depth) * missProbs[nodeStack.top() - 1];
			current = r[nodeStack.pop()];
			depth = depthStack.pop() + 1;
		}
		// count the far right dummy 
		treeCost += depth * missProbs[missProbs.length-1];
		return treeCost;
	}

	/**
	 * Check that the given probabilities sum to 1, throw an exception if not.
	 * 
	 * @param keyProbs
	 * @param missProbs
	 */
	public static void checkProbs(double[] keyProbs, double[] missProbs) {
		double[] allProbs = new double[keyProbs.length + missProbs.length];
		int i = 0;
		for (double keyProb : keyProbs)
			allProbs[i++] = keyProb;
		for (double missProb : missProbs)
			allProbs[i++] = missProb;
		// When summing doubles, sum from smallest to greatest
		// to reduce round-off error.
		Arrays.sort(allProbs);
		double totalProb = 0;
		for (double prob : allProbs)
			totalProb += prob;
		// Don't compare doubles for equality directly. Check that their
		// difference is less than some epsilon.
		if (Math.abs(1.0 - totalProb) > .0001)
			throw new BadOptimalBSTInputException("Probabilities total to " + totalProb);
	}

	/**
	 * Check that the arrays have appropriate lengths (keys, values, and
	 * keyProbs all the same, missProbs one extra), throw an exception if not.
	 * 
	 * @param keys
	 * @param values
	 * @param keyProbs
	 * @param missProbs
	 */
	public static void checkLengths(String[] keys, String[] values, double[] keyProbs, double[] missProbs) {
		int n = keys.length;
		if (values.length != n || keyProbs.length != n || missProbs.length != n + 1)
			throw new BadOptimalBSTInputException(n + "keys, " + values.length + " values, " + keyProbs.length
					+ " key probs, and " + missProbs.length + " miss probs");
	}

}
