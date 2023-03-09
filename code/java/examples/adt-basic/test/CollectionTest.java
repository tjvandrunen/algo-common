package test;

import java.util.Random;

public abstract class CollectionTest {
    /* Are we debugging or testing? This will be set to false for grading */
    protected static final boolean DEBUG = true;
    
    protected Random rand = new Random(42);
    
    protected String[] states = { 
             "Minnesota", "Minneapolis",
             "Texas", "Dallas",
             "Oregon", "Seattle",
             "New Jersey", "Newark",
             "Pennsylvania", "Philadelphia",
             "Massachusetts", "Springfield",
             "Arizona", "Tuscon",
             "Michigan", "Ann Arbor",
             "Ohio", "Cincinatti",
             "New York", "Buffalo",
             "Florida", "Orlando",
             "Colorado", "Boulder",
             "Alabama", "Jackson",
             "Kentucky", "Louisville",
             "Kansas", "Wichita",
             "Alaska", "Vasilia", };
    
    protected String[] romans = 
        { "Augustus", "Tiberius", "Caligula", "Claudius", "Nero",
            "Galba", "Otho", "Vitellius", "Vespasian", "Titus",
            "Domitian", "Nerva", "Trajan", "Hadrian", "Antoninus Pius",
            "Marcus Aurelius", "Commodus" };
    
    protected int[] marks;
    private boolean marksInitialized= false;
    protected void initMarks() {
        if (!marksInitialized)
            marks = new int[getData().length];
    }
    protected void clearMarks() {
        initMarks();
        for (int i = 0; i < marks.length; i++)
            marks[i] = 0;
    }
    
    protected String[] getData() {
        return romans;
    }
    
     protected static class MeasEq {
            protected Integer kernel;
            private static int comparisons;
            protected static void resetComparisons() {
                comparisons = 0;
            }
            protected static int getComparisons() {
                return comparisons;
            }
            public MeasEq(Integer num) {
                kernel = num;
            }
            @Override
            public boolean equals(Object o){
                comparisons++;
                return o instanceof MeasEq && kernel.equals(((MeasEq) o).kernel);
            }
            @Override 
            public int hashCode() {
                throw new UnsupportedOperationException();
            }
            /**
             * This should be used only for debugging
             */
            @Override
            public String toString() {
                if (DEBUG)
                    return kernel.toString();
                throw new UnsupportedOperationException();
            }
            

        }
}
