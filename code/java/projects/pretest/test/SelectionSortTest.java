package test;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

import static exercises.SelectionSort.selectionSort;

public class SelectionSortTest {
    /**
     * The number of comparisons the sort is allowed to deviate from (n*(n+1))/2
     */
    private static final int ERROR = 1;
    /**
     * A wrapper class for Integer that counts the total number of comparisons
     * that have been made.
     */
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
        public int compareTo(MeasComp anotherCC) {
            comparisons++;
            return kernel.compareTo(anotherCC.kernel);
        }
        @Override 
        public int hashCode() {
            throw new UnsupportedOperationException();
        }

    }

    /**
     * Checks the correctness of SelectionSort on a given array by comparing its
     * results to those of the java build in array sorting function
     * 
     * @param array
     *            - The array to test
     */
    private static <T extends Comparable<? super T>> void checkCorrectness(T[] array) {
        T[] testArray = Arrays.copyOf(array, array.length);
        Arrays.sort(array);
        selectionSort(testArray);
        assert (Arrays.equals(array, testArray));
    }

    /**
     * Makes a random array of ComparisonCounters.
     * 
     * @returns An array of randomized ComparisonCounters
     */
    private static MeasComp[] makeRandom(int n) {
        Random rand = new Random();
        MeasComp[] array = new MeasComp[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = new MeasComp(rand.nextInt());
        }
        return array;
    }

    /**
     * Tests that the algorithm sort a random array of size n correctly with the
     * correct number of comparisons
     * 
     * @param n
     *            - The size of the random array that will be tested. Must greater than 0.
     */
    private static void checkRandom(int n) {
        assert(n > 0);
        MeasComp[] array = makeRandom(n);
        MeasComp[] testArray = Arrays.copyOf(array, array.length);
        MeasComp.resetComparisons();
        selectionSort(testArray);
        /* selection sort should perform exactly (n*(n+1))/2) comparisons when sorting an array of
         * size n. Since some implementation will optimize out a comparison we allow for selection
         * sort to deviate by ERROR comparisons. 
         */
        int comparisonsOff = MeasComp.getComparisons() -(n*(n+1))/2;
        assert(comparisonsOff <= 0);
        Arrays.sort(array);
        assert (Arrays.equals(array, testArray));
    }

    @Test
    public void trivial() {
        Integer[] test = { 0 };
        checkCorrectness(test);
    }

    @Test
    public void twoElements() {
        Integer[] test = { 2, 1 };
        checkCorrectness(test);
    }

    @Test
    public void preSorted() {
        Integer[] test = { 1, 2 };
        checkCorrectness(test);
    }

    @Test
    public void tenElement() {
        Integer[] test = { 7, 4, 9, 8, 2, 3, 1, 6, 0, 5 };
        checkCorrectness(test);
    }

    @Test
    public void repeatedElements() {
        Integer[] test = { 7, 4, 9, 8, 2, 3, 1, 6, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2 };
        checkCorrectness(test);
    }

    public void stringSmall() {
        String[] test = "Neither a borrower nor a lender be".split(" ");
        checkCorrectness(test);

    }

    @Test
    public void stringLarge() {
        String[] test = "To be, or not to be: that is the question: Whether 'tis nobler in the mind to suffer The slings and arrows of outrageous fortune, Or to take arms against a sea of troubles, And by opposing end them? To die: to sleep; No more; and by a sleep to say we end The heart-ache and the thousand natural shocks That flesh is heir to, 'tis a consummation Devoutly to be wish'd. To die, to sleep; To sleep: perchance to dream: ay, there's the rub; For in that sleep of death what dreams may come When we have shuffled off this mortal coil, Must give us pause: there's the respect That makes calamity of so long life; For who would bear the whips and scorns of time, The oppressor's wrong, the proud man's contumely, The pangs of despised love, the law's delay, The insolence of office and the spurns That patient merit of the unworthy takes, When he himself might his quietus make With a bare bodkin? who would fardels bear, To grunt and sweat under a weary life, But that the dread of something after death, The undiscover'd country from whose bourn No traveller returns, puzzles the will And makes us rather bear those ills we have Than fly to others that we know not of? Thus conscience does make cowards of us all; And thus the native hue of resolution Is sicklied o'er with the pale cast of thought, And enterprises of great pith and moment With this regard their currents turn awry, And lose the name of action.--Soft you now! The fair Ophelia! Nymph, in thy orisons Be all my sins remember'd."
                .split(" ");
        checkCorrectness(test);
    }

    @Test
    public void smallRandom() {
        checkRandom(100);
    }

    @Test
    public void largeRandom() {
        checkRandom(10000);
    }

    @Test
    public void randomRandom() {
        checkRandom((new Random()).nextInt(10000));
    }

}
