package optimizedIntSet;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestMOIS {

    @Test
	public void testAddSome() {
		Set<Integer> testSet = new ModestlyOptimizedIntSet(3);
		testSet.add(1);
		testSet.add(2);
		testSet.add(3);
		testSet.add(5);
		assertFalse(testSet.contains(0));
		assertTrue(testSet.contains(1));
		assertTrue(testSet.contains(2));
		assertTrue(testSet.contains(3));
		assertFalse(testSet.contains(4));
		assertTrue(testSet.contains(5));
		assertFalse(testSet.contains(6));
	}

}
