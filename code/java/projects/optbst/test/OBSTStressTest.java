package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import impl.NaiveOptimalBSTMapFactory;
import impl.OptimalBSTData;
import impl.OptimalBSTMap;
import impl.OptimalBSTMapFactory;
import optbstutil.OptBSTUtil;

public class OBSTStressTest extends OBSTTest {

    @Test
    public void stressTest() {
    	// the maximum size of a test tree 
    	final int MAX_SIZE = 15;
    	// the number of tests run
    	final int TESTS = 20;
    	for (int t = 0; t < TESTS; t++) {
    		
    		// pick a size between 1 and MAX_SIZE
    		int n = rand.nextInt(MAX_SIZE-1)+1;
    		
    		//generate the probabilities
    		double[] hitProbs = new double[n];
    		double[] missProbs = new double[n+1];
    		double totalSoFar = 0.0;
    		for (int i = 0; i < n; i++) {
    			hitProbs[i] = rand.nextDouble()*(1.0 - totalSoFar);
    			totalSoFar += hitProbs[i];
    			missProbs[i] = rand.nextDouble()*(1.0 - totalSoFar);
    			totalSoFar += missProbs[i];
    		}
    		missProbs[n] = 1 - totalSoFar;
    		// shuffle the probabilities
    		// from taocp 3.4.2
    		for (int i = n-1; i > 0; i--) {
    			double u = rand.nextDouble();
    			int k = ((int) Math.floor(i*u)) +1;
    			double tmp = hitProbs[k];
    			hitProbs[k] = hitProbs[i];
    			hitProbs[i] = tmp;
    		}
    		for (int i = n; i > 0; i--) {
    			double u = rand.nextDouble();
    			int k = ((int) Math.floor(i*u)) +1;
    			double tmp = missProbs[k];
    			missProbs[k] = missProbs[i];
    			missProbs[i] = tmp;
    		}
    		// generate keys and values
    		String[] keys = new String[n];
    		String[] values = new String[n];
    		for (int i = 0; i < n; i++) {
    			keys[i] = "" + (char)('a'+i*2);
    			values[i] = "" + (char)('a'+i*2);
    		}
    		OptimalBSTData bundle = new OptimalBSTData(keys,values,hitProbs,missProbs);
    		
    		// build the BST
    		OptimalBSTMap correct = NaiveOptimalBSTMapFactory.buildOptimalBST(keys,values,hitProbs,missProbs);
    		OptimalBSTMap test = OptimalBSTMapFactory.buildOptimalBST(keys,values,hitProbs,missProbs);
    		// makes sure the tree is correct
    		for (int i = 0; i < n; i++) {
    			if (i%2 == 0) {
    				String result = test.get(""+(char)('a'+i));
    				assert(result != null);
    				assert(result.equals(""+(char)('a'+i)));
    			} else {
    				assert(!test.containsKey(""+(char)('a'+i)));
    			}
    		}
    				
    		// make sure the tree is optimal
    		assertEquals(OptBSTUtil.expectedSearchCost(correct, bundle), OptBSTUtil.expectedSearchCost(test, bundle),.001);
    	}
    }

}
