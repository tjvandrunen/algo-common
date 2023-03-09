package grammar;

import java.util.ArrayList;

import grammar.Token.TokenType;

public class SimpleParser {

    private Tokenizer tokey;
    
    public String parse(String message) {
        tokey = new Tokenizer(message);
        return (new Sentence()).toString();
    }
    
    private static String trivial = "the dog ran";
    private static String a = "the big dog ran through the bright field";
    private static String b = "the loud dog chased the small ball quickly";

    
    public static void main(String[] args) {
        Parser parsy = new Parser();
        System.out.println(parsy.parse(a));
    }
   
    class Sentence {
        NounPhrase subject;
        Predicate pred;
        Sentence() {
            subject = new NounPhrase();
            pred = new Predicate();
        }
        public String toString() { return "(Sentence [" + subject + "," + pred + "])"; }
    }

    class NounPhrase {
        Token art;
        ArrayList<Token> adjs;
        Token nominal;

        public NounPhrase() {
            art = tokey.extractCurrent(TokenType.ART);
            adjs = new ArrayList<Token>();
            while (tokey.current().ty == TokenType.ADJ)
                adjs.add(tokey.extractCurrent(TokenType.ADJ));
            nominal = tokey.extractCurrent(TokenType.NOUN);
        }
        public String toString() { return "(ConcNounPhrase [" + art + ", " +
                adjs + ", " + nominal + "])"; }
    }
    
    
    class Predicate {
        VerbPhrase vp;
        VerbModifier vm;
        public Predicate() {
            switch(tokey.current().ty) {
            case LV: vp = new LinkingVerbPhrase(); break;
            case TV: vp = new TransitiveVerbPhrase(); break;
            case IV: vp = new IntransitiveVerbPhrase(); break;
            default: throw new SyntaxException();
            }
            if (tokey.hasCurrent())
                switch(tokey.current().ty) {
                case PREP: vm = new PrepositionalPhrase (); break;
                case ADV: vm = new Adverbial(); break;
                default: vm = null;
                }
            else vm = null;
        }
        public String toString() { return "(Predicate [" + vp + ", " +
                (vm == null? "None" : vm.toString()) + "])"; 
        }
    }
    
    static interface VerbModifier {}
    
    class PrepositionalPhrase implements VerbModifier {
        Token prep;
        NounPhrase object;
        public PrepositionalPhrase() {
            prep = tokey.extractCurrent(TokenType.PREP);
            object = new NounPhrase();
        }
        public String toString() { return "(PrepPhrase [" + prep + "," + object + "])"; }
    }
    
    class Adverbial implements VerbModifier {

        Token adv;
        public Adverbial() {
            adv = tokey.extractCurrent(TokenType.ADV);
        }
        public String toString() { return adv.toString(); }
    }
    
    static interface VerbPhrase {}
    
    class LinkingVerbPhrase implements VerbPhrase {
        Token lv;
        Token adj;
        public LinkingVerbPhrase() {
            lv = tokey.extractCurrent(TokenType.LV);
            adj = tokey.extractCurrent(TokenType.ADJ);
        }
        public String toString() { return "(LVP [" + lv + ", " + adj + "])"; }
    }
    class TransitiveVerbPhrase implements VerbPhrase {
        Token tv;
        NounPhrase directObject;
        public TransitiveVerbPhrase() {
            tv = tokey.extractCurrent(TokenType.TV);
            directObject = new NounPhrase();
        }
        public String toString() { return "(TVP [" + tv + ", " + directObject + "])"; }
    }
    class IntransitiveVerbPhrase implements VerbPhrase {

        Token iv;
        public IntransitiveVerbPhrase() {
            iv = tokey.extractCurrent(TokenType.IV);
        }
        public String toString() { return "(IVP [" + iv + "])"; }
   }

}
