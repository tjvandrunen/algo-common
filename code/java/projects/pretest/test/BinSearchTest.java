package test;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import static exercises.BinarySearch.binarySearch;


public class BinSearchTest {

    private static int savedComparisons;
    private static Random randy = new Random(System.currentTimeMillis());
    
    
    private static class MeasComp implements Comparable<MeasComp> {
        private Integer kernel;
        private static int comparisons;
        private static void resetComparisons() {
            comparisons = 0;
        }
        private static int getComparisons() {
            return comparisons;
        }
        public MeasComp(Integer num) {
            kernel = num;
        }
        @Override
        public boolean equals(Object o){
            comparisons++;
            return o instanceof MeasComp && kernel.equals(((MeasComp) o).kernel);
        }
        @Override
        public int compareTo(MeasComp anotherMC) {
            comparisons++;
            return kernel.compareTo(anotherMC.kernel);
        }
        @Override 
        public int hashCode() {
            throw new UnsupportedOperationException();
        }

    }

    public static int maxComparisons(int n) {
        return ((int) Math.ceil(Math.log(n)/Math.log(2))) + 1;
        
    }
    
    private MeasComp[] original;
    private MeasComp[] workingCopy;
    
    
    private void checkTampering() {
        if (original != null)
            for (int i = 0; i < original.length; i++)
                assertTrue("Array tampered with", original[i] == workingCopy[i]);
    }

    private void checkComparisons() {
        int maxComparisons = maxComparisons(original.length);
        assertTrue("Too many comparisons (" + savedComparisons + " > " + maxComparisons + ")", 
                savedComparisons <= maxComparisons);
    }
    
    private void populateArray(int size, int referencePt, boolean uniform) {
        if (size < 1) {
            original = null;
            workingCopy = null;
        }
        else {
            original = new MeasComp[size];
            for (int i = 0; i < size; i++)
                if (uniform)
                    original[i] = new MeasComp(randy.nextInt(referencePt));
                else
                    original[i] = new MeasComp((int) (referencePt * randy.nextGaussian()));
            java.util.Arrays.sort(original);
            workingCopy = new MeasComp[size];
            for (int i = 0; i < size; i++)
                workingCopy[i] = original[i];
        }
    }

    private void populateArray(int[] given) {
        if (given == null){
            original = null;
            workingCopy = null;
        }
        else {
            original = new MeasComp[given.length];
            for (int i = 0; i < given.length; i++)
                original[i] = new MeasComp(given[i]);
            java.util.Arrays.sort(original);
            workingCopy = new MeasComp[given.length];
            for (int i = 0; i < given.length; i++)
                workingCopy[i] = original[i];
        }
        MeasComp.resetComparisons();   
    }
    
    private void runSearch(int item) {
        MeasComp msItem = new MeasComp(item);
        int result = binarySearch(workingCopy, msItem);
        savedComparisons = MeasComp.getComparisons();
        assertTrue("Invalid result", result >= -1 && (workingCopy == null || result < workingCopy.length));
        if (result == -1)  {
            if (workingCopy != null)
                for (int i = 0; i < workingCopy.length; i++)
                    assertFalse("Valid item not found", msItem.equals(workingCopy[i]));
        }
        else 
            assertTrue("Incorrect result", msItem.equals(workingCopy[result]));
        checkTampering();
    }
    
    @Test
    public void a() {
        populateArray(null);
        runSearch(5);
    }

    @Test
    public void b() {
        populateArray(new int[0]);
        runSearch(5);
    }

    int[] c = { 5 };

    @Test
    public void c1a() {
        populateArray(c);
        runSearch(0);
    }

    @Test
    public void c1b() {
        c1a();
        checkComparisons();
   }

    @Test
    public void c2a() {
        populateArray(c);
        runSearch(5);
    }

    @Test
    public void c2b() {
        c2a();
        checkComparisons();
    }
    
    @Test
    public void c3a() {
        populateArray(c);
        runSearch(8);
    }

    @Test
    public void c3b() {
        c3a();
        checkComparisons();
    }
    
    int[] d = { 1, 2};

    @Test
    public void d1a() {
        populateArray(d);
        runSearch(1);
    }

    @Test
    public void d1b() {
        d1a();
        checkComparisons();
    }

    @Test
    public void d2a() {
        populateArray(d);
        runSearch(2);
    }

    @Test
    public void d2b() {
        d2a();
        checkComparisons();
    }

    @Test
    public void d3a() {
        populateArray(d);
        runSearch(8);
    }

    @Test
    public void d3b() {
        d3a();
        checkComparisons();
    }

    
    
    int[] e = { 1, 2, 3, 5, 6, 7};

    @Test
    public void e1a() {
        populateArray(e);
        runSearch(1);
    }
    
    @Test
    public void e1b() {
        e1a();
        checkComparisons(); 
    }
    @Test
    public void e2a() {
        populateArray(e);
        runSearch(3);
    }
    
    @Test
    public void e2b() {
        e2a();
        checkComparisons(); 
    }
     
    @Test
    public void e3a() {
        populateArray(e);
        runSearch(5);
    }
    
    @Test
    public void e3b() {
        e3a();
        checkComparisons(); 
    }
     
    @Test
    public void e4a() {
        populateArray(e);
        runSearch(7);
    }
    
    @Test
    public void e4b() {
        e4a();
        checkComparisons(); 
    }
     
    @Test
    public void e5a() {
        populateArray(e);
        runSearch(0);
    }
    
    @Test
    public void e5b() {
        e5a();
        checkComparisons(); 
    }
     
    @Test
    public void e6a() {
        populateArray(e);
        runSearch(4);
    }
    
    @Test
    public void e6b() {
        e6a();
        checkComparisons(); 
    }
     
    @Test
    public void e7a() {
        populateArray(e);
        runSearch(8);
    }
    
    @Test
    public void e7b() {
        e6a();
        checkComparisons(); 
    }

    int f[] = { 5, 6, 7, 8, 9, 10, 11 };
    
    @Test
    public void f1a() {
        populateArray(f);
        runSearch(4);
    }

    @Test
    public void f1b() {
        f1a();
        checkComparisons();
    }
    
    @Test
    public void f2a() {
        populateArray(f);
        runSearch(12);
    }

    @Test
    public void f2b() {
        f2a();
        checkComparisons();
    }
    

    
    @Test
    public void stress() {
        int[] sizes = {10, 100, 1000, 100000};
        for (int i = 0; i < sizes.length; i++) {
            for (int j = 0; j < 25; j++) {
                // uniformly distributed dense
                populateArray(sizes[i], 25, true);
                // valid item
                int index = randy.nextInt(sizes[i]);
                MeasComp.resetComparisons();
                runSearch(workingCopy[index].kernel);
                checkComparisons();
                // invalid item
                MeasComp.resetComparisons();
                runSearch(25);
                checkComparisons();

                // uniformly distributed sparse
                populateArray(sizes[i], 1000000, true);
                // valid item
                index = randy.nextInt(sizes[i]);
                
                MeasComp.resetComparisons();
                runSearch(workingCopy[index].kernel);
                checkComparisons();
                // invalid item
                MeasComp.resetComparisons();
                while (index + 1 < sizes[i] && (workingCopy[index].kernel == workingCopy[index+1].kernel || workingCopy[index].kernel == workingCopy[index+1].kernel + 1))  
                    index++;
                int searchNum = workingCopy[index].kernel + 1;
                runSearch(searchNum);
                checkComparisons();
                
                // gaussian distributed
                populateArray(sizes[i], 1000000, false);
                // valid item
                index = randy.nextInt(sizes[i]);
                MeasComp.resetComparisons();
                runSearch(workingCopy[index].kernel);
                checkComparisons();
                // invalid item
                MeasComp.resetComparisons();
                while (index + 1 < sizes[i] && (workingCopy[index].kernel == workingCopy[index+1].kernel || workingCopy[index].kernel == workingCopy[index+1].kernel + 1))  
                    index++;
                searchNum = workingCopy[index].kernel + 1;
                runSearch(searchNum);
                checkComparisons();
                
                
            }
        }
        
        
    }
    
    
}
