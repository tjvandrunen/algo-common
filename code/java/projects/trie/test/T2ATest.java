package test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import impl.TrieSet;


public class T2ATest {

    private void testT2A(String[] originalKeys) {
        TrieSet testTrie = new TrieSet();//originalKeys, 0, originalKeys.length, 0);
        for (String key : originalKeys)
            testTrie.add(key);
        
        String[] resultKeys = testTrie.toArray();//new String[originalKeys.length];
        
        assertEquals(originalKeys.length, resultKeys.length);
        for (int i = 0; i < originalKeys.length; i++)
            assertEquals(originalKeys[i], resultKeys[i]);
    }
    
    

    @Test
    public void sample() {
       String[] data = new String[] {"ANN", "ANNA", "ANNIKA", "BETH", "BETHANY", "CARL"};
       testT2A(data);
    }
    
    @Test
    public void singleton() {
        String[] data = new String[] {"BETH"};
        testT2A(data);
  }

    @Test
    public void noSharedPaths() {
        String[] data = new String[] {"ANN", "BETH", "CARL", "DAHLIA", "EVERGREEN"};
        testT2A(data);
     }   
    
    @Test
    public void deepShare() {
        String[] data = new String[] {"CONSTANS", "CONSTANZE", "CONSTANTINE", "CONSTANTINUS", "CONSTANTIUS", "CONSTANTINOPLE", "CONSTANT", "CONSTANCE", "CONSTANTS", "CONSUBSTANIATION"};
        Arrays.sort(data);
        testT2A(data);        
    }
    
    @Test
    public void stress() {
        String[] data = new String[] {
                "CONSTANCE", "HELEN", "JUSTIN", "JON", "JOHN", "CONSTANTIUS",
                "HELENA", "HELLA", "JONATHAN", "CONSTANTINE", "JUSTINIAN",
                "CLEMENS", "HEROD", "ANN", "JOHANA", "JUSTINIANUS",
                "CONSTANTINUS", "ELLEN", "ELAINE", "ELLIE", "ELLA",
                "HERODIAS", "HERODIAN", "JOHNA", "ANNALISE", "ANNETTE",
                "ANNIKA", "CLEMENT", "CAESAR", "AUGUSTUS", "ANNE", "ANNMARIE",
                "JUSTINMARTYR", "JOHNBAPTIST", "ANNIE", "ANNA", "CAESARION",
                "CAESAREA", "AUGUSTA", "ANNMARGARET", "AUGUST", "AUGUSTINE",
                "CLEMENZO", "JONATHANIAN", "CAESARINA", "AUGUSTINUS", "CONSTANS",
                "HELENE"  
        };
        Arrays.sort(data);
        testT2A(data);

    }
}
