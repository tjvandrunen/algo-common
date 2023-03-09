package miniEnglish;

import ckyparse.Grammar;

public class Main {

    public static void parseAndPrint(MiniEnglishParser parser, String sentence) {
        System.out.println(sentence);
        for (Grammar.SyntaxTree<MENonterminal> synT : parser.parse(sentence)) 
            System.out.println(synT);
        
    }
    
    public static void main(String[] args) {
        MiniEnglishParser parser = new MiniEnglishParser();
 
        parseAndPrint(parser, "the dog ran");
                
        parseAndPrint(parser, "the cat chased the dog in the kitchen");
            
        parseAndPrint(parser, "she knew that he knew that she knew that he knew that she knew that he knew that she knew that he loved her");
            
        parseAndPrint(parser, "that he loved her troubled her");
            
        parseAndPrint(parser, "the boy hit the ball in the field");
        
        parseAndPrint(parser, "the dog that wagged a tail is the one which ate the bone");
        
        parseAndPrint(parser, "the woman knew that the man loved her");
        
        parseAndPrint(parser, "that the man loved her troubled the woman");
        
        parseAndPrint(parser, "that that the cheese was gone troubled the mouse interested the scientist");
        
        parseAndPrint(parser, "the scarecrow walked awkwardly");
        
        parseAndPrint(parser, "he saw the dog with the binoculars");
   
    }

}
