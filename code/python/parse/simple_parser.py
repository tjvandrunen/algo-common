'''
Created on Jun 19, 2020

@author: tvandrun
'''
from tokenizer import Tokenizer

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
    return SyntaxTree("NounPhrase", 
                      [get_token(tokzer, "ART"), adjective_list(tokzer), 
                       get_token(tokzer, "NOUN")])
    
def adjective_list(tokzer):
    adj_list = []
    while tokzer.current().pos == "ADJ" :
        adj_list.append(tokzer.extract_current())
    return SyntaxTree("AdjList", adj_list)

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

try:
    print(sentence(Tokenizer(b))) 
except Exception as ex :
    print(str(ex.message)) 

