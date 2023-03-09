package expr;

import java.util.Random;

import alg.Sorts;
import alg.Sorts.Sorter;

public class RuntimeExperiment {

    private static long runExperiment(Sorter sortAlg, int[] array) {
     long fore = System.nanoTime();
     sortAlg.sort(array);
     long aft = System.nanoTime();
     return aft - fore;
    }
    
    private static Random randy = new Random();
    
    private static void fillRandom(int[] array) {
        for (int i = 0; i < array.length; i++)
            array[i] = randy.nextInt();
    }
    
    public static void main(String[] args) {
        int[] array = new int[100000];
        Sorts.Bubble bubs = new Sorts.Bubble();
        Sorts.Shell shelley = new Sorts.Shell();
        
        // untimed "practice" runs        
        for (int i = 0; i < 10; i++) {
            fillRandom(array);
            runExperiment(bubs, array);
            fillRandom(array);
            runExperiment(shelley, array);
        }

        fillRandom(array);
        int[] array2 = new int[100000];
        for (int i = 0; i < array.length; i++)
            array2[i] = array[i]; 
        System.out.println("Bubble sort: " + runExperiment(bubs, array) + " nanoseconds");
        System.out.println("Shell sort: " + runExperiment(shelley, array) + " nanoseconds");
        
    }
}
