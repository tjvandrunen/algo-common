package findClusterSize;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestLPHMAveCluster {

    @Test
    public void testF25() { // 7
        String[] keys = { "Fish", "French", "Fence", "False", "Fezzik", "Flippant",
                "Frosh", "Fibbert", "Fouchan", "Finza", "Feebly", "Forrest",
                "Finally", "Franz", "Fortenbras"
        };
        LinProbHashMap<String,String> map = new LinProbHashMap<String,String>(25);
        for (String k : keys)
            map.put(k, null);
        assertEquals(((double) keys.length)/7, map.aveClusterSize(), .001);
    }
    
    @Test
    public void testF31() { // 6
        String[] keys = { "Fish", "French", "Fence", "False", "Fezzik", "Flippant",
                "Frosh", "Fibbert", "Fouchan", "Finza", "Feebly", "Forrest",
                "Finally", "Franz", "Fortenbras"
        };
        LinProbHashMap<String,String> map = new LinProbHashMap<String,String>(31);
        for (String k : keys)
            map.put(k, null);
        assertEquals(((double) keys.length)/6, map.aveClusterSize(), .001);

    }
    @Test
    public void testF47() { // 9
        String[] keys = { "Fish", "French", "Fence", "False", "Fezzik", "Flippant",
                "Frosh", "Fibbert", "Fouchan", "Finza", "Feebly", "Forrest",
                "Finally", "Franz", "Fortenbras"
        };
        LinProbHashMap<String,String> map = new LinProbHashMap<String,String>(47);
        for (String k : keys)
            map.put(k, null);
        assertEquals(((double) keys.length)/9, map.aveClusterSize(), .001);

    }
    @Test
    public void testSt79() { // 16

    String[] keys = { 
            "Minnesota", 
            "Texas", 
            "Oregon", 
            "New Jersey", 
            "Pennsylvania", 
            "Massachusetts", 
            "Arizona", 
            "Michigan", 
            "Ohio", 
            "New York", 
            "Florida", 
            "Colorado", 
            "Alabama", 
            "Kentucky", 
            "Kansas", 
            "Alaska" };
    LinProbHashMap<String,String> map = new LinProbHashMap<String,String>(79);
    for (String k : keys)
        map.put(k, null);
    assertEquals(((double) keys.length)/12, map.aveClusterSize(), .001);

    }

    @Test
    public void testTr79() { // 16

    String[] keys =  { "CONSTANCE", "HELEN", "JUSTIN", "JON", "JOHN", "CONSTANTIUS",
            "HELENA", "HELLA", "JONATHAN", "CONSTANTINE", "JUSTINIAN",
            "CLEMENS", "HEROD", "ANN", "JOHANA", "JUSTINIANUS",
            "CONSTANTINUS", "ELLEN", "ELAINE", "ELLIE", "ELLA",
            "HERODIAS", "HERODIAN", "JOHNA", "ANNALISE", "ANNETTE",
            "ANNIKA", "CLEMENT", "CAESAR", "AUGUSTUS", "ANNE", "ANNMARIE",
            "JUSTINMARTYR", "JOHNBAPTIST", "ANNIE", "ANNA", "CAESARION",
            "CAESAREA", "AUGUSTA", "ANNMARGARET", "AUGUST", "AUGUSTINE",
            "CLEMENZO", "JONATHANIAN", "CAESARINA", "AUGUSTINUS", "CONSTANS",
            "HELENE" };
    LinProbHashMap<String,String> map = new LinProbHashMap<String,String>(79);
    for (String k : keys)
        map.put(k, null);
    assertEquals(((double) keys.length)/14, map.aveClusterSize(), .001);
    }

    @Test
    public void testTr51() { // 16

    String[] keys =  { "CONSTANCE", "HELEN", "JUSTIN", "JON", "JOHN", "CONSTANTIUS",
            "HELENA", "HELLA", "JONATHAN", "CONSTANTINE", "JUSTINIAN",
            "CLEMENS", "HEROD", "ANN", "JOHANA", "JUSTINIANUS",
            "CONSTANTINUS", "ELLEN", "ELAINE", "ELLIE", "ELLA",
            "HERODIAS", "HERODIAN", "JOHNA", "ANNALISE", "ANNETTE",
            "ANNIKA", "CLEMENT", "CAESAR", "AUGUSTUS", "ANNE", "ANNMARIE",
            "JUSTINMARTYR", "JOHNBAPTIST", "ANNIE", "ANNA", "CAESARION",
            "CAESAREA", "AUGUSTA", "ANNMARGARET", "AUGUST", "AUGUSTINE",
            "CLEMENZO", "JONATHANIAN", "CAESARINA", "AUGUSTINUS", "CONSTANS",
            "HELENE" };
    LinProbHashMap<String,String> map = new LinProbHashMap<String,String>(51);
    for (String k : keys)
        map.put(k, null);
    assertEquals(((double) keys.length)/5, map.aveClusterSize(), .001);
    }
}
