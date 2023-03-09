package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TrieTestKeysThatMatch extends TrieTest {

    @Test
    public void keysThatMatchOne() {
        reset();
        populate();
        String[] results = new String[2];
        int i = 0;
        for (String s : testSetTrie.keysThatMatch("ANN."))
            results[i++] = s;
        assertTrue((results[0].equals("ANNE") && results[1].equals("ANNA")) ||
                (results[0].equals("ANNA") && results[1].equals("ANNE")));
    
        i = 0;
        for (String s : testSetTrie.keysThatMatch("HERODIA."))
            results[i++] = s;
        assertTrue((results[0].equals("HERODIAN") && results[1].equals("HERODIAS")) ||
                (results[0].equals("HERODIAS") && results[1].equals("HERODIAN")));
    }

    @Test
    public void keysThatMatchTwo() {
        reset();
        populate();
        String[] results = new String[2];
        int i = 0;
        for (String s : testSetTrie.keysThatMatch("ELL.."))
            results[i++] = s;
        assertTrue((results[0].equals("ELLIE") && results[1].equals("ELLEN")) ||
                (results[0].equals("ELLEN") && results[1].equals("ELLIE")));
    }

    @Test
    public void keysThatMatchThree() {
        reset();
        populate();
        String[] results = new String[2];
        int i = 0;
        for (String s : testSetTrie.keysThatMatch("...IE"))
            results[i++] = s;
        assertTrue((results[0].equals("ANNIE") && results[1].equals("ELLIE")) ||
                (results[0].equals("ELLIE") && results[1].equals("ANNIE")));
    }

    @Test
    public void keysThatMatchFive() {
        reset();
        populate();
        String[] results = new String[2];
        int i = 0;
        for (String s : testSetTrie.keysThatMatch("ANN....."))
            results[i++] = s;
        assertTrue((results[0].equals("ANNMARIE") && results[1].equals("ANNALISE")) ||
                (results[0].equals("ANNALISE") && results[1].equals("ANNMARIE")));
    }

    @Test
    public void keysThatMatchSix() {
        reset();
        populate();
        String[] results = new String[2];
        int i = 0;
        for (String s : testSetTrie.keysThatMatch("..NA...."))
            results[i++] = s;
        assertTrue((results[0].equals("JONATHAN") && results[1].equals("ANNALISE")) ||
                (results[0].equals("ANNALISE") && results[1].equals("JONATHAN")));
    }


}
