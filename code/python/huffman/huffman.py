'''
Created on Jan 14, 2020

@author: tvandrun
'''

from bag import DictBag
from priorityqueue import PriorityQueue
from functools import reduce

# Class to represent a leaf node in a Huffman key
class Leaf :    
    def __init__(self, c):
        # The character represented by this leaf
        self.c = c
        # The set of characters in the subtree rooted here,
        # which is just the one letter
        self.letters = set([c])
    def contains(self, x):
        return x == self.c
    def encode(self, x):
        return []
    def decode(self, bits):
        return (self.c, bits)
    def __str__(self):
        return "{" + self.c + "}"

# Class to represent an internal node in a Huffman key
class Internal :
    def __init__(self, left, right):
        # The left and right children
        self.left = left
        self.right = right
        # The set of characters in the subtree rooted here
        self.letters = left.letters.union(right.letters)
    def contains(self, x):
        return (x in self.letters)
    def encode(self, x):
        if self.left.contains(x) :
            return [True] + self.left.encode(x)
        else :
            #print(x)
            assert self.right.contains(x) 
            return [False] + self.right.encode(x)
    def decode(self, bits):
        if bits[0] :
            return self.left.decode(bits[1:])
        else :
            return self.right.decode(bits[1:])
    def __str__(self):
        return "["+str(self.left) + "," + str(self.right) + "]";

def char_encoding_to_bit_string(c, key):
    to_return = ""
    for b in key.encode(c) :
        to_return += "1" if b else "0"
    return to_return

def msg_encoding_to_bit_string(msg, key):
    to_return = ""
    for c in msg:
        to_return += char_encoding_to_bit_string(c, key)
    return to_return

def encode(msg):
    # Analyze the characters in msg by frequency
    frequencies = DictBag()
    characters = set([])
    for c in msg :
        frequencies.add(c)
        characters.add(c)

    # Initialize the worklist to be a priority queue of
    # node, combined-frequency pairs, with a lower combined
    # frequency as a higher priority
    worklist = PriorityQueue(len(characters), lambda x, y : x[1] < y[1])

    # Make leaves for all the characters, inserting them in the worklist
    for c in characters :
        worklist.insert((Leaf(c), frequencies[c]))

    # Repeatedly remove the two nodes with least combined frequency,
    # unite them, and insert the new node into the worklist
    for i in range(len(characters)-1):
        (left, left_freq) = worklist.extract_max()
        (right, right_freq) = worklist.extract_max()
        merged = Internal(left, right)
        worklist.insert((merged, left_freq + right_freq))

    # Retrieve the remaining node, which is the root of the final key
    key = worklist.extract_max()[0]
    assert worklist.is_empty()
    for c in characters :
        print(str(c) + "&" + char_encoding_to_bit_string(c, key))

    # Encode the message using the key as key
    encoding = reduce(lambda bits, c : bits + key.encode(c), msg, [])
    ## Without using reduce:
    #encoding = []
    #for c in msg :
    #    encoding += key.encode(c)

    # Return the encoded message and key
    return (encoding, key)

def encode_from_tree(msg, key):
    bitseq = []
    for c in msg:
        bitseq += key.encode(c)
    return bitseq
    
def encode_from_tree_redux(msg, key):
    return reduce(lambda bits, c : bits + key.encode(c), msg, [])

def decode(msg, key):
    decoded_msg = ""
    print(str(len(msg)))
    while len(msg) > 0 :
        (c, msg) = key.decode(msg)
        decoded_msg += c
    return decoded_msg

(encoded_msg, key) = encode("IF THIS IS A CONSULAR SHIP THEN WHERE IS THE AMBASSADOR")
print(msg_encoding_to_bit_string("COMPLETE", key))

import unittest

class HuffmanTest(unittest.TestCase):
    def checkMsg(self, msg):
        (encoded_msg, key) = encode(msg)
        self.assertEqual(msg, decode(encoded_msg, key))
    
    def testVader(self):
        self.checkMsg("IF THIS IS A CONSULAR SHIP THEN WHERE IS THE AMBASSADOR")
        