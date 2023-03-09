package miniEnglish;

import adt.Set;
import static miniEnglish.MENonterminal.*;
import ckyparse.TerminalParser;
import impl.LinkedSet;
import net.sf.extjwnl.dictionary.Dictionary;
import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;

public class METerminalParser implements TerminalParser<MENonterminal> {

    private static String[] RELATIVE_PRONOUNS = 
            new String[]{ "who", "that", "which" };
    private static String[] ARTICLES =
            new String[] { "a", "an", "the" };
    private static String[] PERSONAL_PRONOUNS =
            new String[] { "I", "me", "he", "him", "she", "her", "it", 
                    "they", "them", "you" };
    private static String[] PREPOSITIONS = 
            new String[] {"aboard", "about", "above", "across", "after", "against", 
                    "along", "amid", "among", "anti", "around", "as", "at", "before", 
                    "behind", "below", "beneath", "beside", "besides", "between", 
                    "beyond", "but", "by", "concerning", "considering", "despite", 
                    "down", "during", "except", "excepting", "excluding", "following", 
                    "for", "from", "in", "inside", "into", "like", "minus", "near", "of", 
                    "off", "on", "onto", "opposite", "outside", "over", "past", "per", 
                    "plus", "regarding", "round", "save", "since", "than", "through", 
                    "to", "toward", "towards", "under", "underneath", "unlike", "until", 
                    "up", "upon", "versus", "via", "with", "within", "without" };
    
    
    private static boolean contains(String[] array, String word) {
        for (String x : array) if (x.equals(word)) return true;
        return false;
    }
    private static Dictionary dictionary;
    static {
        try {
            dictionary = Dictionary.getDefaultResourceInstance();
        } catch (JWNLException e) {
            System.out.print("Could not load WordNet dictionary");
            System.exit(-1);
        }
    }
    
    public Set<MENonterminal> getNTsForToken(String word) {
        Set<MENonterminal> poses = new LinkedSet<MENonterminal>();
        if (word.equals("that")) poses.add(THAT);
        if (contains(ARTICLES, word)) poses.add(ART);
        if (contains(PERSONAL_PRONOUNS, word)) poses.add(PERS_PRON);
        if (contains(RELATIVE_PRONOUNS, word)) poses.add(REL_PRON);
        if (contains(PREPOSITIONS, word)) poses.add(PREP);
        try {
            for (IndexWord iw : dictionary.lookupAllIndexWords(word).getIndexWordCollection())
                switch (iw.getPOS()) {
                case ADJECTIVE : poses.add(ADJ); break;
                case ADVERB : poses.add(ADV); break;
                case NOUN: poses.add(NOUN); break;
                case VERB: poses.add(VERB);
                }
        } catch (JWNLException e) {
            System.out.print("Problem with WordNet dictionary");
            System.exit(-1);
        }
        
        return poses;
    }

}
