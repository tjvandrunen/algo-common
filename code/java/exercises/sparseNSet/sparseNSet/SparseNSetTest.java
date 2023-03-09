package sparseNSet;

public class SparseNSetTest extends NSetTest {

    protected void reset() {
        testSet = new SparseNSet(data.length);
    }

}
