package grammar;

import java.util.ArrayList;
import grammar.Token.TokenType;


public class Parser {

    private Tokenizer tokzer;
    
    public String parse(String message) {
        tokzer = new Tokenizer(message);
        return (new Sentence()).toString();
    }
    
    private static String trivial = "the dog ran";
    private static String a = "the big dog ran through the bright field";
    private static String b = "the loud dog chased the small ball quickly";
    private static String c = "the old man knew that the smart woman loved the beautiful unicorn";
    private static String d = "that the cat chased the mouse concerned the dog";
    private static String e = "the dog knew that the cat knew that the dog knew that the cat knew that the dog knew that the cat chased the mouse";

    
    public static void main(String[] args) {
        Parser parsy = new Parser();
        System.out.println(parsy.parse(e));
    }
    
    class Sentence {
        NounPhrase subject;
        Predicate pred;
        Sentence() {
            // add code here
        }
        public String toString() { return "(Sentence [" + subject + "," + pred + "])"; }
    }
    
    static interface NounPhrase {}
    
    NounPhrase nounPhrase() {
        switch (tokzer.current().ty) {
        case THAT : return new AbstractNounPhrase(); 
        case ART : return new ConcreteNounPhrase(); 
        default: throw new SyntaxException();
        }
    }
    
    class AbstractNounPhrase implements NounPhrase {
        Sentence clause;
        public AbstractNounPhrase() {
            // add code here
        }
        public String toString() { return "(AbsNounPhrase [" + clause + "])"; }
    }
    
    class ConcreteNounPhrase implements NounPhrase {
        Token article;
        ArrayList<Token> adjectives;
        Token noun;

        public ConcreteNounPhrase() {
            article = tokzer.extractCurrent(TokenType.ART);
            adjectives = new ArrayList<Token>();
            while (tokzer.current().ty == TokenType.ADJ)
                adjectives.add(tokzer.extractCurrent(TokenType.ADJ));
            noun = tokzer.extractCurrent(TokenType.NOUN);
        }
        public String toString() { return "(ConcNounPhrase [" + article + ", " +
                adjectives + ", " + noun + "])"; }
    }
    
    
    class Predicate {
        VerbPhrase vp;
        VerbModifier vm;
        public Predicate() {
            // add code here
        }
        public String toString() { return "(Predicate [" + vp + ", " +
                (vm == null? "None" : vm.toString()) + "])"; 
        }
    }
    
    static interface VerbModifier {}
    
    class PrepositionalPhrase implements VerbModifier {
        Token preposition;
        NounPhrase object;
        public PrepositionalPhrase() {
            // add code here
        }
        public String toString() { return "(PrepPhrase [" + preposition + "," + object + "])"; }
    }
    
    class Adverbial implements VerbModifier {

        Token adverb;
        public Adverbial() {
            // add code here
        }
        public String toString() { return adverb.toString(); }
    }
    
    static interface VerbPhrase {}
    
    class LinkingVerbPhrase implements VerbPhrase {
        Token linkingVerb;
        Token adjective;
        public LinkingVerbPhrase() {
            // add code here
        }
        public String toString() { return "(LVP [" + linkingVerb + ", " + adjective + "])"; }
    }
    class TransitiveVerbPhrase implements VerbPhrase {
        Token transitiveVerb;
        NounPhrase directObject;
        public TransitiveVerbPhrase() {
            // add code here
        }
        public String toString() { return "(TVP [" + transitiveVerb + ", " + directObject + "])"; }
    }
    class IntransitiveVerbPhrase implements VerbPhrase {

        Token intransitiveVerb;
        public IntransitiveVerbPhrase() {
            // add code here
        }
        public String toString() { return "(IVP [" + intransitiveVerb + "])"; }
   }
 
}
