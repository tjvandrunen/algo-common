'''
Created on Jun 19, 2020

@author: tvandrun
'''

vocab = {"THAT" : ["that"],
         "ART" : ["a", "an", "the"],
         "ADJ" : ["big", "small", "beautiful", "fast", "bright", "smart",
                  "old", "new", "strong", "weak", "loud", "quiet", "sharp"],
         "NOUN" : ["man", "woman", "dog", "flea", "unicorn", "tree", "field",
                   "ball", "bat", "house", "axe", "cat", "mouse", "bird"],
         "ADV" : ["gently", "slowly", "quickly", "carefully", "precisely"],
         "PREP" : ["through", "with", "above", "by", "in", "on"],
         "LV" : ["was", "felt", "seemed"],
         "TV" : ["chased", "saw", "greeted", "loved", "hit", "concerned", "proved", "knew"],
         "IV" : ["ran", "slept", "sang"]}

class UnknownWord(Exception):
    def __init__(self, message):
        self.message = message

class Token :
    def __init__(self, word):
        self.word = word
        self.pos = None
        for pos in vocab:
            if word in vocab[pos] :
                self.pos = pos
        if self.pos == None :
            raise UnknownWord(word)

    def __str__(self):
        return "(" + self.pos + "," + self.word + ")"


class Tokenizer :
    def __init__(self, message):
        self.tokens = [Token(x) for x in message.split()]
        self.i = 0
    
    # Are there more tokens?
    def has_current(self):
        return self.i < len(self.tokens)

    # Throw an exception if there are no more tokens
    def check(self):
        if not self.has_current() :
            raise StopIteration

    # Retrieve the current token, but do not advance
    def current(self):
        self.check()
        return self.tokens[self.i]
    
    # Advance to the next token
    def advance(self):
        self.check()
        self.i += 1

    # Retrieve the current token and advance
    def extract_current(self):
        self.check()
        current_token = self.tokens[self.i]
        self.i += 1
        return current_token
    