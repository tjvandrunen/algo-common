package expr;

import java.util.Random;
import alg.Sorts;

public class ComparisonExperiment {

    public static void main(String[] args) {
        Random randy = new Random();
        int[] array1 = new int[10000],
                array2 = new int[10000];
        for (int i = 0; i < array1.length; i++)
            array1[i] = array2[i] = randy.nextInt();
        System.out.println("Bubble sort: " + (new Sorts.Bubble()).sort(array1) + " comparisons.");
        System.out.println("Shell sort: " + (new Sorts.Shell()).sort(array1) + " comparisons.");
        
    }
    
}
