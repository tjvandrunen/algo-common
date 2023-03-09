package ckyparse;

import adt.Set;

public interface TerminalParser<NT> {

    Set<NT> getNTsForToken(String word);
    
}
