package expr;

import alg.StringMergeSort;
import alg.StringRadixSort;
import alg.StringSelectionSort;
import alg.StringSortUtil;
import alg.StringThreeWayQuickSort;

public class StringExperiments {
    public static void main(String[] args) {
        // Size of the array
        int n = 10;
        // Size (number of characters) of each string
        int d = 3;

        // Interpret commandline arguments
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-n"))  {
                if (i + 1 == args.length) usage();
                else n = Integer.parseInt(args[++i]);
            }
            if (args[i].equals("-d"))  {
                if (i + 1 == args.length) usage();
                else d = Integer.parseInt(args[++i]);
            }
        }

        // make the array and working copy
        String[] original = StringSortUtil.arrayGen(n, d);
        String[] copy = StringSortUtil.copy(original);
        if (n <= 10000)
            StringSelectionSort.sorter.sort(copy);

        // Run all the sorts once, untimed, so that
        // they are JIT-compiled first (although in my tests
        // this doesn't seem to have made a difference)
        copy = StringSortUtil.copy(original);
        StringMergeSort.sorter.sort(copy);
        copy = StringSortUtil.copy(original);
        StringRadixSort.sorter.sort(copy);
        copy = StringSortUtil.copy(original);
        StringThreeWayQuickSort.sorter.sort(copy);
        copy = StringSortUtil.copy(original);

        // Run all the sorts, timed
        long fore = System.nanoTime();
        if (n <= 10000)
            StringSelectionSort.sorter.sort(copy);
        long selectionTime = System.nanoTime() - fore;
        copy = StringSortUtil.copy(original);
        fore = System.nanoTime();
        StringMergeSort.sorter.sort(copy);
        long mergeTime = System.nanoTime() - fore;
        copy = StringSortUtil.copy(original);
        fore = System.nanoTime();
        StringRadixSort.sorter.sort(copy);
        long radixTime = System.nanoTime() - fore;
        copy = StringSortUtil.copy(original);
        fore = System.nanoTime();
        StringThreeWayQuickSort.sorter.sort(copy);
        long threeWayQuickTime = System.nanoTime() - fore;
        
        if (n <= 10000)
            System.out.println("Selection: \t" + selectionTime);
        System.out.println("Merge: \t\t" + mergeTime);
        System.out.println("Radix: \t\t" + radixTime);
        System.out.println("TWQ: \t\t" + threeWayQuickTime);
       
    }
    private static void usage() {
        System.out.println("java sorts.Experiment [options]");
        System.out.println("options: ");
        System.out.println("\t-n size of the array (default 10)");
        System.out.println("\t-d size (number of characters) of each string (default 3)");

        System.exit(-1);
    }

}
