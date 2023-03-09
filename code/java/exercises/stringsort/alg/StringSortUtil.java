package alg;

import java.util.Random;

public class StringSortUtil {
    private static Random randy = new Random();

    public static String[] arrayGen(int n, int d) {
        char[] platform = new char[d];
        String[] toReturn = new String[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < d; j++)
                platform[j] = (char) ('A' + randy.nextInt(26));
            toReturn[i] = new String(platform);
        }
        return toReturn;
    }
    
    public static String[] copy(String[] orig) {
        String[] toReturn = new String[orig.length];
        for (int i = 0; i < orig.length; i++)
            toReturn[i] = orig[i];
        return toReturn;
    }
    
    public static boolean isSorted(String[] array) {
        boolean sorted = true;
        for (int i = 0; i < array.length - 1 && sorted; i++)
            sorted &= array[i].compareTo(array[i+1]) <= 0;
        return sorted;
    }
    
    public static boolean containsSame(String[] array1, String[] array2) {
        if (array1.length != array2.length) return false;
        
        boolean containsAll = true;
        for (int i = 0; containsAll && i < array1.length; i++) {
            boolean containsThis = false;
            for (int j = 0; !containsThis && j < array2.length; j++)
                containsThis |= array1[i].equals(array2[j]);
            containsAll &= containsThis;
        }
        for (int i = 0; containsAll && i < array2.length; i++) {
            boolean containsThis = false;
            for (int j = 0; !containsThis && j < array1.length; j++)
                containsThis |= array2[i].equals(array1[j]);
            containsAll &= containsThis;
        }
        return containsAll;
    }
}
