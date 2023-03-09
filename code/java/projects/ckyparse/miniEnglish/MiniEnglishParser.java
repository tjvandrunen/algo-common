package miniEnglish;

import static miniEnglish.MENonterminal.*;

import ckyparse.Grammar.SyntaxTree;
import ckyparse.Parser;

public class MiniEnglishParser {

    private static MENonterminal[][] units = new MENonterminal[][] {
        {NOUN_PHRASE, ABS_NOUN_PHRASE},
        {NOUN_PHRASE, CONC_NOUN_PHRASE},
        {CONC_NOUN_PHRASE, CNPA},
        {CNPA, PERS_PRON},
        {NOMINAL, NOUN},
        {VERB_PHRASE, VPA},
        {VPA, VPB},
        {VPB, VERB}
    };
    private static MENonterminal[][] duals = new MENonterminal[][] {
        {SENTENCE, NOUN_PHRASE, VERB_PHRASE},
        {ABS_NOUN_PHRASE, THAT, SENTENCE},
        {CONC_NOUN_PHRASE, CNPA, REL_CLAUSE},
        {CONC_NOUN_PHRASE, CNPA, PREP_PHRASE},
        {CNPA, ART, NOMINAL},
        {NOMINAL, ADJ, NOMINAL},
        {REL_CLAUSE, REL_PRON, VERB_PHRASE},
        {PREP_PHRASE, PREP, NOUN_PHRASE},
        {VPA, VPB, PREP_PHRASE},
        {VPB, VERB, ADJ},
        {VPB, VERB, NOUN_PHRASE},
        {VERB_PHRASE, VPA, ADV}
    };

    
    private Parser<MENonterminal> parser;

    public MiniEnglishParser() {
        parser = new Parser<MENonterminal>(new METerminalParser(), duals, units);
    }
    
    public Iterable<SyntaxTree<MENonterminal>> parse(String sentence) {
        return parser.parse(sentence);
    }
}
