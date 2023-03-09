package ckyparse;

import adt.Queue;
import adt.Set;
import ckyparse.Grammar.*;
import impl.ListQueue;

public class Parser<NT> {

    private TerminalParser<NT> termer;
    
    private NT[][] duals;
    private NT[][] units;
    
    public Parser(TerminalParser<NT> termer, NT[][] duals, NT[][] units) {
        this.termer = termer;
        this.duals = duals;
        this.units = units;
    }

    private NT buildTree(SyntaxTree<NT> a, SyntaxTree<NT> b) {
        NT toReturn = null;
        for (int i = 0; toReturn == null && i < duals.length; i++) 
            if (duals[i][1] == a.nt && duals[i][2] == b.nt) 
                toReturn = duals[i][0];
            
        
        return toReturn;
    }

    private void unitClosure(TableEntry<NT> tableEntry) {
         throw new UnsupportedOperationException();
    }
    
    
    public TableEntry<NT> parse(String sentence) {
        String[] words = sentence.split("\\s+");
        int n = words.length;
        @SuppressWarnings("unchecked")
        TableEntry<NT>[][] parseTable = new TableEntry[n][n];

         // fill table

        return parseTable[0][n-1];
    }

}
