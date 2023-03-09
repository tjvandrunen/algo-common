package miniEnglish;


import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.dictionary.Dictionary;
import net.sf.extjwnl.data.IndexWord;

public class Experiment {

    public static void main(String[] args) {
        try {
            Dictionary dictionary = Dictionary.getDefaultResourceInstance();
            for (IndexWord iw : dictionary.lookupAllIndexWords("still").getIndexWordCollection())
                System.out.println(iw.getPOS());
        
        } catch (JWNLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
    }

}
