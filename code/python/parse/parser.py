'''
Created on Jun 19, 2020

@author: tvandrun
'''

from tokenizer import Tokenizer
from parse.simple_parser import adjective_list

class BadSyntax(Exception):
    def __init__(self, message):
        self.message = message

class SyntaxTree :
    def __init__(self, nonterminal, children):
        self.nonterminal = nonterminal
        self.children = children
    def __str__(self):
        return ("(" + self.nonterminal + " [" + 
                ",".join([str(x) for x in self.children]) + "]" )

def check_token(tok, ty):
    if tok.pos != ty : raise BadSyntax("Expected " + ty + ", found " + tok.pos)

def get_token(tokzer, ty):
    token = tokzer.extract_current()
    check_token(token, ty)
    return token
    
def sentence(tokzer):
    return SyntaxTree("Sentence", [noun_phrase(tokzer), predicate(tokzer)])
    
def noun_phrase(tokzer):
    first_pos = tokzer.current().pos
    if first_pos == "THAT" :
        return abstract_noun_phrase(tokzer)
    elif first_pos == "ART" :
        return concrete_noun_phrase(tokzer)
    else:
        raise BadSyntax("Expected THAT or ART, found " + first_pos)
    
def abstract_noun_phrase(tokzer):
    check_token(tokzer.current(), "THAT")
    tokzer.advance()
    return SyntaxTree("AbsNounPhrase", [sentence(tokzer)])

def concrete_noun_phrase(tokzer):
    return SyntaxTree("ConcNounPhrase", 
                      [get_token(tokzer, "ART"), adjective_list(tokzer), 
                       get_token(tokzer, "NOUN")])
    
def adjective_list(tokzer):
    first_pos = tokzer.current().pos
    if first_pos == "ADJ" :
        adj = tokzer.extract_current()
        adj_list = adjective_list(tokzer)
        adj_list.children.insert(0, adj)
        return adj_list
    else :
        return SyntaxTree("AdjList", [])

def predicate(tokzer):
    return SyntaxTree("Predicate", [verb_phrase(tokzer), verb_modifier(tokzer)])

def verb_phrase(tokzer):    
    first_pos = tokzer.current().pos
    
    if first_pos == "LV" :
        return linking_verb_phrase(tokzer)
    elif first_pos == "TV" :
        return transitive_verb_phrase(tokzer)
    elif first_pos == "IV" :
        return intransitive_verb_phrase(tokzer)
    else :
        raise BadSyntax("Expected LV, TV, or IV, found " + first_pos)
    
def linking_verb_phrase(tokzer):
    return SyntaxTree("LinkingVerbPhrase", 
                      [get_token(tokzer, "LV"), get_token(tokzer, "ADJ")])

def transitive_verb_phrase(tokzer):
    return SyntaxTree("TransitiveVerbPhrase",
                      [get_token(tokzer, "TV"), noun_phrase(tokzer)])

def intransitive_verb_phrase(tokzer):
    return SyntaxTree("IntransitiveVerbPhrase",
                      [get_token(tokzer, "IV")])

def verb_modifier(tokzer):
    if tokzer.has_current() :
        first_pos = tokzer.current().pos
        if first_pos == "PREP" :
            return prep_phrase(tokzer)
        elif first_pos == "ADV" :
            return tokzer.extract_current()
    return None

def prep_phrase(tokzer):
    return SyntaxTree("PrepPhrase", [get_token(tokzer, "PREP"), noun_phrase(tokzer)])

trivial = "the dog ran"
a = "the big dog ran through the bright field"
b = "the loud dog chased the small ball quickly"
c = "the old man knew that the smart woman loved the beautiful unicorn"
d = "that the cat chased the mouse concerned the dog"
e = "the dog knew that the cat knew that the dog knew that the cat knew that the dog knew that the cat chased the mouse"

for s in [trivial, a, b, c, d, e] :
    try:
        print(sentence(Tokenizer(s))) 
    except Exception as ex :
        print(str(ex.message)) 
        
import unittest

class ParserTest(unittest.TestCase):

    def testTrivial(self):
        self.assertEqual(str(sentence(Tokenizer(trivial))),
                         "(Sentence [(ConcNounPhrase [(ART,the),(AdjList [],(NOUN,dog)],(Predicate [(IntransitiveVerbPhrase [(IV,ran)],None]]")
                        
    def testA(self):
        self.assertEqual(str(sentence(Tokenizer(a))),
                         "(Sentence [(ConcNounPhrase [(ART,the),(AdjList [(ADJ,big)],(NOUN,dog)],(Predicate [(IntransitiveVerbPhrase [(IV,ran)],(PrepPhrase [(PREP,through),(ConcNounPhrase [(ART,the),(AdjList [(ADJ,bright)],(NOUN,field)]]]]")
    
    def testB(self):
        self.assertEqual(str(sentence(Tokenizer(b))),
                         "(Sentence [(ConcNounPhrase [(ART,the),(AdjList [(ADJ,loud)],(NOUN,dog)],(Predicate [(TransitiveVerbPhrase [(TV,chased),(ConcNounPhrase [(ART,the),(AdjList [(ADJ,small)],(NOUN,ball)]],(ADV,quickly)]]")

    def testC(self):
        self.assertEqual(str(sentence(Tokenizer(c))),
                         "(Sentence [(ConcNounPhrase [(ART,the),(AdjList [(ADJ,old)],(NOUN,man)],(Predicate [(TransitiveVerbPhrase [(TV,knew),(AbsNounPhrase [(Sentence [(ConcNounPhrase [(ART,the),(AdjList [(ADJ,smart)],(NOUN,woman)],(Predicate [(TransitiveVerbPhrase [(TV,loved),(ConcNounPhrase [(ART,the),(AdjList [(ADJ,beautiful)],(NOUN,unicorn)]],None]]]],None]]")

    def testD(self):
        self.assertEqual(str(sentence(Tokenizer(d))),
                         "(Sentence [(AbsNounPhrase [(Sentence [(ConcNounPhrase [(ART,the),(AdjList [],(NOUN,cat)],(Predicate [(TransitiveVerbPhrase [(TV,chased),(ConcNounPhrase [(ART,the),(AdjList [],(NOUN,mouse)]],None]]],(Predicate [(TransitiveVerbPhrase [(TV,concerned),(ConcNounPhrase [(ART,the),(AdjList [],(NOUN,dog)]],None]]")

    def testE(self):
        self.assertEqual(str(sentence(Tokenizer(e))),
                         "(Sentence [(ConcNounPhrase [(ART,the),(AdjList [],(NOUN,dog)],(Predicate [(TransitiveVerbPhrase [(TV,knew),(AbsNounPhrase [(Sentence [(ConcNounPhrase [(ART,the),(AdjList [],(NOUN,cat)],(Predicate [(TransitiveVerbPhrase [(TV,knew),(AbsNounPhrase [(Sentence [(ConcNounPhrase [(ART,the),(AdjList [],(NOUN,dog)],(Predicate [(TransitiveVerbPhrase [(TV,knew),(AbsNounPhrase [(Sentence [(ConcNounPhrase [(ART,the),(AdjList [],(NOUN,cat)],(Predicate [(TransitiveVerbPhrase [(TV,knew),(AbsNounPhrase [(Sentence [(ConcNounPhrase [(ART,the),(AdjList [],(NOUN,dog)],(Predicate [(TransitiveVerbPhrase [(TV,knew),(AbsNounPhrase [(Sentence [(ConcNounPhrase [(ART,the),(AdjList [],(NOUN,cat)],(Predicate [(TransitiveVerbPhrase [(TV,chased),(ConcNounPhrase [(ART,the),(AdjList [],(NOUN,mouse)]],None]]]],None]]]],None]]]],None]]]],None]]]],None]]")
