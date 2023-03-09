#!/usr/bin/python
'''
Created on Dec 3, 2013

@author: tvandrun
'''

from nltk.corpus import wordnet as wn
import sys
import os

# This program will give a set of possible parses, given a sentence.
# Thus each entry in the table that the CKY parser populates
# will have a set of results.


id_gen = 0

# Nonterminals in the grammar
nonterminals = ['Sentence', 'NounPhrase', 'AbsNP', 'ConcNP',
                'CNPA', 'RelClause',   'Nominal',  'PrepPhrase', 
                'VerbPhrase', 'VPA', 'VPB', 
                'Prep',  'RelPronoun', 'That','PersPronoun',
                'Adj', 'Noun','Verb', 'Adv', 'Art']

# The last two lines are "terminal categories",
# roughly corresponding to parts of speech.

# Those nonterminals that are "last stop", that is, they are the
# righthand side of a production in the form of NT -> word
# where word is a terminal. The category "last stop nonterminals"
# also corresponds to the concept of "parts of speech".
#last_stop_non_terminals = ['That', 'Det', 'Adj', 'Noun', 'Adv',
#                           'Pronoun', 'RelPronoun', 'Prep', 'Verb']


# A table record is a single result, indicating a way of
# parsing the range of tokens encompassed by the table entry 
# this record is in. 
# There are three kinds of parsing results, based on three kinds
# of productions:
# NT -> word (what I call a "last stop" non-terminal)
# NT -> a (a "unit" production, which technically violates CNF) 
# NT -> a b (a "dual" production)
# If this were Java, these would be three subclasses of SyntaxTree
class SyntaxTree :
    def __init__(self, NT, a=None, b=None, word=None) :
        self.NT = NT
        self.a = a
        self.b = b
        self.word = word
        global id_gen
        self.id = "x" + str(id_gen)
        id_gen += 1
        if a == None :
            assert word != None
            self.parse = "(%s %s)" % (NT, word)
            self.tree = self.id + ' [style=bold, label = "' + self.NT + ':' + self.word + '"];'
        elif b == None :
            self.parse = "(%s %s)" % (NT, a.parse) 
            self.tree = (self.id + ' [label = "' + self.NT + '"];\n' 
                         + self.id + ' -> ' + self.a.id + ';\n'
                         + self.a.tree)
                          
        else :
            self.parse = "(%s %s %s)" % (NT, a.parse, b.parse) 
            self.tree = (self.id + ' [label = "' + self.NT + '"];\n'
                          + self.id + ' -> ' + self.a.id + ';\n'
                          + self.id + ' -> ' + self.b.id + ';\n'
                          + self.a.tree + self.b.tree)
   
    def __str__(self) :
        #return (self.NT, (self.ai, self.aj), (self.bi, self.bj)).__str__()
        return self.NT

    def __repr__(self) :
        return self.__str__()

# A table entry is a dynamic collection of table records
class TableEntry :
    def __init__(self) :
        self.records = []

    def __str__(self):
        return 'TE[' + str(self.records) + ']'

    # Add a possible parse based on the production NT -> a b
    # where a and b are nonterminals
    def add_dual(self, NT, a, b):
        self.records.append(SyntaxTree(NT, a, b))

    # Add a possible parse based on the production NT -> a        
    # where a is a nonterminal
    # This returns the new parse that is added, for
    # use in the unit_closure method
    def add_unit(self, NT, a):
        newTabRec = SyntaxTree(NT, a)
        self.records.append(newTabRec)
        return newTabRec
        #self.records.append(SyntaxTree(NT, a))
    
    # Add a possible parse based on the production NT -> token
    # where token is a terminal
    def add_term(self, NT, token):
        self.records.append(SyntaxTree(NT, word=token))



    # Retrieve all records
    def all_records(self) :
        return self.records

    # Modify this record by computing the closure of all unit 
    # productions applicable to this range of tokens.
    # For example, if the grammar contains the production a -> b
    # and this entry already indicates that this range can parse
    # to a b, then this range can also parse to an a.     
    def unit_closure(self) :
        worklist = [x for x in self.records]
        while worklist :
            x = worklist[0]
            worklist = worklist[1:]
            NT = x.NT            
            if NT in units :
                worklist.append(self.add_unit(units[NT], x))






# All "dual" productions in the grammar, that is, those in the form
# NT -> a b where a and b are nonterminals. This collection is
# represented as a map from lefthand sides of production to righthand
# sides. The production NT -> a b would be represented as (a, b):NT
duals = {}
duals[('NounPhrase', 'VerbPhrase')] = 'Sentence'
duals[('That', 'Sentence')] = 'AbsNP'
duals[('CNPA', 'RelClause')] = 'ConcNP'
duals[('CNPA', 'PrepPhrase')] = 'ConcNP'
duals[('Art', 'Nominal')] = 'CNPA'
duals[('Adj', 'Nominal')] = 'Nominal'
duals[('RelPronoun', 'VerbPhrase')] = 'RelClause'
duals[('Prep', 'NounPhrase')] = 'PrepPhrase'
duals[('VPA', 'Adv')] = 'VerbPhrase'
duals[('VPB', 'PrepPhrase')] = 'VPA'
duals[('Verb', 'Adj')] = 'VPB'
duals[('Verb', 'NounPhrase')] = 'VPB'

# All "unit" productions in the grammar, that is, those in the form
# NT -> a where a is a nonterminal As with duals, this is represented
# as a dict from lefthand sides to righthand sides.
units = {}
units['AbsNP'] = 'NounPhrase'
units['ConcNP'] = 'NounPhrase'
units['CNPA'] = 'ConcNP'
units['PersPronoun'] = 'CNPA'
units['Noun'] = 'Nominal'
units['VPA'] = 'VerbPhrase'
units['VPB'] = 'VPA'
units['Verb'] = 'VPB'

# For recognizing articles, that, relative pronouns, 
# personal pronouns, and prepositions, we use a
# dict from POS tags to word lists.
pos_to_word_list =  {'Art':['a', 'an', 'the'],
                     'That':['that'],
                     'RelPronoun':['who', 'that', 'which'],
                     'PersPronoun':['I', 'me', 'he', 'him', 'she', 'her',
                                    'it', 'they', 'them', 'you'],
                     # A list of 70 prepositions is found in a text file
                     'Prep':[x.strip() for x in open("preps").readlines()]}
                    

def get_NT_set(word):
    NT_set = set([])
    for pos in pos_to_word_list :
        if word in pos_to_word_list[pos] :
            NT_set.add(pos)
    # WordNet recognizes five parts of speech:
    # n for noun, v for verb, r for adverb,
    # a for adjectives, and s for "satellite adjectives".
    # We combine a and s together into Adj
    for synset in wn.synsets(word) :
        if synset.pos() == 'n' :
            NT_set.add('Noun')
        elif synset.pos() == 'v' :
            NT_set.add('Verb')
        # 's' is for "satellite adjective"
        elif synset.pos() == 'a' or synset.pos() == 's':
            NT_set.add('Adj')
        elif synset.pos() == 'r' :
            NT_set.add('Adv')
    return NT_set

DEBUG = True

def parse(sentence_toks):
    n = len(sentence_toks)

    # Make an empty parse table
    parse_table = [[TableEntry() if i <= j else None for j in range(n)]
                   for i in range(n)]


    # Populate the bottom row of the parse table,
    # that is, parse each word individually
    for i in range(n) :
        word = sentence_toks[i]
        NT_set = get_NT_set(word)
        for NT in NT_set :
            parse_table[i][i].add_term(NT, word)
        

        parse_table[i][i].unit_closure()
        if DEBUG : print('(' + str(i) + ',' + str(i) + ') ' + str(parse_table[i][i]))

    # Populate the rest of the table
    for d in range(1, n) :
        for i in range(n-d) :
            j = i + d
            for k in range(i, j) :
                for a in parse_table[i][k].all_records() :
                    for b in parse_table[k+1][j].all_records():
                        if (a.NT, b.NT) in duals :
                            parse_table[i][j].add_dual(duals[(a.NT,b.NT)], a, b)
            parse_table[i][j].unit_closure()
            if DEBUG : print('(' + str(i) + ',' + str(j) + ') ' + str(parse_table[i][j]))


    # Print the results
    ## if output_dot :
    ##     results = [tree for record in parse_table[0][n].all_records() for tree in record.trees]
    ## else :
    ##     results = [parse for record in parse_table[0][n].all_records() for parse in record.parses]

    d = 0
    for x in parse_table[0][n-1].all_records() :
        print(x.parse)
        #for t in x.trees:
        #    file = open("tree" + str(d) + ".dot", 'w')
        #    file.write("digraph G {")
        #    file.write(t)
        #    file.write("}")
        #    file.close()
        #    os.system("dot tree" + str(d) + ".dot -Tpng:cairo > tree" + str(d) + ".png")
        #    d += 1
    

                    
#sentence_text = sys.argv[1]
a = "the boy hit the ball in the field"
b = "the dog ran"
c = "the cat chased the dog in the kitchen"
d = "she knew that he knew that she knew that he knew that she knew that he knew that she knew that he loved her"
e = "that he loved her troubled her"
f = "the dog that wagged a tail is the one which ate the bone"
g = "the woman knew that the man loved her"
h = "the still still stills me still"
w = "that the man loved her troubled the woman"
x = "that that the cheese was gone troubled the mouse interested the scientist"
y = "the scarecrow walked awkwardly"
z = "he saw the dog with the binoculars"
aa = "the plain trains like a juicy apple"

for s in [aa] : #[a,b,c,d,e,f,g,h,w,x,y,z] :
    print(s)
    parse(s.split())




