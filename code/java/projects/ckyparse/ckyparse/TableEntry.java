package ckyparse;

import adt.Set;
import impl.LinkedSet;

import java.util.Iterator;

import ckyparse.Grammar.*;

public class TableEntry<NT> implements Iterable <SyntaxTree<NT>> {
    
   
    private Set<SyntaxTree<NT>> records;
    
    public TableEntry() {
        records = new LinkedSet<SyntaxTree<NT>>();
    }

    public void addDual(NT nt, SyntaxTree<NT> left, SyntaxTree<NT> right) {
        records.add(new DualProduction<NT>(nt, left, right));
    }

    public SyntaxTree<NT> addUnit(NT nt, SyntaxTree<NT> child) {
        UnitProduction<NT> unitSynTree = new UnitProduction<NT>(nt, child);
        records.add(unitSynTree);
        return unitSynTree;
    }
    public void addTerminal(NT nt, String word) {
        records.add(new PartOfSpeech<NT>(nt, word));
    }
    public Iterator<SyntaxTree<NT>> iterator() {
        return records.iterator();
    }
    public int size() {
        return records.size();
    }
    public String toString() {
        String toReturn = "TE[";
        for (SyntaxTree<NT> synT : records) 
            toReturn += synT.nt + ", ";
        if (toReturn.length() > 3) toReturn = toReturn.substring(0, toReturn.length()-2);
        toReturn += "]";
        return toReturn;
    }
}
