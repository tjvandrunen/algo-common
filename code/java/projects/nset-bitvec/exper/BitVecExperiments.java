package exper;

import impl.BArrayNSet;
import impl.BitVecNSet;
import impl.NaiveNSet;

import java.util.Random;

import adt.NSet;

public class BitVecExperiments {

    private static Random randy = new Random();
    
    private static int[] randomSeq(int size, int range) {
        int[] toReturn = new int[size];
        for (int i = 0; i < toReturn.length; i++)
            toReturn[i] = randy.nextInt(range);
        return toReturn;
    }

    private static long runBasicExperiment(NSet experSet, int[] operations) {
        int half = operations.length / 2;
        long fore = System.nanoTime();
        for (int i = 0; i < half; i++)
            experSet.add(operations[i]);
        for (int i = half; i < operations.length; i++)
            experSet.contains(operations[i]);
        long aft = System.nanoTime();
        return aft - fore;
    }
    
    private static long runWholeSetExperiment(NSet experSet, int[] operations) {
        NSet other = experSet.union(experSet);
        int half = operations.length / 2;
        long fore = System.nanoTime();
        for (int i = 0; i < half; i++)
            experSet.add(operations[i]);
        for (int i = half; i < operations.length; i++)
            switch (operations[i] % 4) {
            case 0: other = experSet.complement(); break;
            case 1: other = experSet.difference(other); break;
            case 2: other = experSet.intersection(other); break;
            case 3: other = experSet.union(other); break;
            }
        long aft = System.nanoTime();
        return aft - fore;
    }

    
    public static void main(String[] args) {
        long nResults = 0,
                baResults = 0,
                bvResults = 0;
        
        for (int i = 0; i < 5; i++) {
            int[] operations = randomSeq(500, 1000);
            nResults += runBasicExperiment(new NaiveNSet(1000), operations);
            baResults += runBasicExperiment(new BArrayNSet(1000), operations);
            bvResults += runBasicExperiment(new BitVecNSet(1000), operations);
        }
        nResults /= 5;
        baResults /= 5;
        bvResults /= 5;
        
        System.out.println("Basic set operations (size 500, range 1000):");
        System.out.println("Naive \t\t" + nResults);
        System.out.println("Boolean Array:\t" + baResults);
        System.out.println("Bit vector:\t" + bvResults);
        
        baResults = 0;
        bvResults = 0;

        for (int i = 0; i < 5; i++) {
            int[] operations = randomSeq(50000, 100000);
            baResults += runBasicExperiment(new BArrayNSet(100000), operations);
            bvResults += runBasicExperiment(new BitVecNSet(100000), operations);
        }
        baResults /= 5;
        bvResults /= 5;
        
        System.out.println("\nBasic set operations (size 50000, range 100000):");
        System.out.println("Boolean Array:\t" + baResults);
        System.out.println("Bit vector:\t" + bvResults);
       
        
        nResults = 0;
        baResults = 0;
        bvResults = 0;
        
        for (int i = 0; i < 5; i++) {
            int[] operations = randomSeq(50, 100);
            nResults += runWholeSetExperiment(new NaiveNSet(100), operations);
            baResults += runWholeSetExperiment(new BArrayNSet(100), operations);
            bvResults += runWholeSetExperiment(new BitVecNSet(100), operations);
        }
        nResults /= 5;
        baResults /= 5;
        bvResults /= 5;
        
        System.out.println("\nWhole-set operations: (size 50, range 100)");
        System.out.println("Naive \t\t" + nResults);
        System.out.println("Boolean Array:\t" + baResults);
        System.out.println("Bit vector:\t" + bvResults);   

        baResults = 0;
        bvResults = 0;
        
        for (int i = 0; i < 5; i++) {
            int[] operations = randomSeq(5000, 10000);
            baResults += runWholeSetExperiment(new BArrayNSet(10000), operations);
            bvResults += runWholeSetExperiment(new BitVecNSet(10000), operations);
        }
        baResults /= 5;
        bvResults /= 5;
        
        System.out.println("\nWhole-set operations: (size 5000, range 10000)");
        System.out.println("Boolean Array:\t" + baResults);
        System.out.println("Bit vector:\t" + bvResults);   

   }
    
}
