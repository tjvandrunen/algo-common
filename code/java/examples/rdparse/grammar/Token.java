package grammar;


public class Token {


    // --- Token classes ---
    static enum TokenType {
        THAT, ART, ADJ, ADV, NOUN, PREP, LV, TV, IV
    }
    
    private static String[]  thatList = new String[] {"that"};
    private static String[] artList = new String[] {"a", "an", "the"};
    private static String[] adjList = new String[] {"big", "small", "beautiful", "fast", "bright", "smart",
            "old", "new", "strong", "weak", "loud", "quiet", "sharp"};
    private static String[] nounList = new String[] {"man", "woman", "dog", "flea", "unicorn", "tree", "field",
            "ball", "bat", "house", "axe", "cat", "mouse", "bird"};
    private static String[] adverbList = new String[] {"gently", "slowly", "quickly", "carefully", "precisely"};
    private static String[] prepList = new String[] {"through", "with", "above", "by", "in", "on"};
    private static String[] lvList = new String[] {"was", "felt", "seemed"};
    private static String[] tvList = new String[] {"chased", "saw", "greeted", "loved", "hit", "concerned", "proved", "knew"};
    private static String[] ivList = new String[] {"ran", "slept", "sang"};
           
    private static boolean contains(String x, String[] words) {
        boolean foundIt = false;
        for (int i = 0; ! foundIt && i < words.length; i++) foundIt |= words[i].equals(x);
        return foundIt;
    }

    static class UnknownWordException extends RuntimeException {
        private static final long serialVersionUID = 2507817631939342381L;
        UnknownWordException(String msg) { super(msg); }
    }
    
    TokenType ty;
    String word;
    public Token(String word) { 
        if (contains(word, thatList)) ty = TokenType.THAT;
        else if (contains(word, artList)) ty = TokenType.ART;
        else if (contains(word, adjList)) ty = TokenType.ADJ;
        else if (contains(word, nounList)) ty = TokenType.NOUN;
        else if (contains(word, adverbList)) ty = TokenType.ADV;
        else if (contains(word, prepList)) ty = TokenType.PREP;
        else if (contains(word, lvList)) ty = TokenType.LV;
        else if (contains(word, tvList)) ty = TokenType.TV;
        else if (contains(word, ivList)) ty = TokenType.IV;
        else throw new UnknownWordException(word);
        
        this.word = word; 
    }
    public String toString() { return "(" + ty + ", " + word + ")"; }
}