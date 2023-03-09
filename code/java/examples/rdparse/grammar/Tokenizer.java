package grammar;


import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import grammar.Token.TokenType;


public class Tokenizer {


    private StringTokenizer tokey;
    private Token current;
    
    public Tokenizer(String sentence) {
        this.tokey = new StringTokenizer(sentence);
        current = tokey.hasMoreTokens()? 
                new Token(tokey.nextToken()) : null;
    }
    
    public boolean hasCurrent() {
        return current != null;
    }

    private void check() {
        if (current == null) throw new NoSuchElementException();
    }
    
    public Token current() {
        check();
        return current;
    }
    
    public void advance() {
        check();
        current = tokey.hasMoreTokens()? 
                new Token(tokey.nextToken()) : null;
    }
    
    public Token extractCurrent(TokenType ty) {
        check();
        if (current.ty != ty) throw new SyntaxException();
        Token toReturn = current;
        advance();
        return toReturn;
    }
    public Token extractCurrent() {
        check();
        Token toReturn = current;
        advance();
        return toReturn;
    }
    
}
