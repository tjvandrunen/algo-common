'''
Created on Nov 15, 2019

@author: tvandrun
'''
PRINT_TABLE = False

def longest_common_subsequence(a, b):
    n = len(a)
    m = len(b)

    # lengths[i][j] indicates the length of the longest
    # common subsequence between the prefixes a[:i] and b[:j]
    lengths = [[None for j in range(m+1)] for i in range(n+1)]
    
    # decisions[i][j] records the decision made for the last
    # respective symbols in prefixes a[:i] and b[:j]:
    # 0 means a[i-1] and b[j-1] match, take the symbol
    # -1 means they don't match, and we skip a[i-1]
    # 1 means they don't match, and we skip b[j-1]
    decisions = [[None for j in range(m+1)] for i in range(n+1)]

    # Trivial (base) cases, for prefixes of length 0
    for i in range(n+1):
        lengths[i][0] = 0
    for j in range(1, m+1):
        lengths[0][j] = 0
        
    # For each subproblem i, j
    for i in range(1, n+1):
        for j in range(1, m+1):
            # If the last characters of the prefixes match, take the character
            if a[i-1] == b[j-1]:
                lengths[i][j] = 1 + lengths[i-1][j-1]
                decisions[i][j] = 0  
            # If they don't match, is it better to skip i or j?
            elif lengths[i][j-1] < lengths[i-1][j] :
                lengths[i][j] = lengths[i-1][j]
                decisions[i][j] = -1   # Skip i
            else :
                assert lengths[i][j-1] >= lengths[i-1][j]
                lengths[i][j] = lengths[i][j-1]
                decisions[i][j] = 1    # Skip j
    
    if PRINT_TABLE :
        for j in reversed(range(m+1)):
            line = " " if j == 0 else str(b[j-1]) 
            for i in range(n+1) :
                line += "&" + str(lengths[i][j]) + "/" + str(decisions[i][j])
            line += "\\\\"
            print line
        line = " "
        for i in range(n) :
            line += "&" + str(a[i])
        print line
    
    
    subsequence = ""
    i = n
    j = m
    while i > 0 and j > 0 :
        if decisions[i][j] == 0 :
            assert a[i-1] == b[j-1]
            subsequence = a[i-1] + subsequence
            i -= 1
            j -= 1
        elif decisions[i][j] == -1 :
            i -= 1
        else :
            assert decisions[i][j] == 1
            j -= 1
            
    return subsequence



import unittest

class TestLCS(unittest.TestCase):
    
    def check(self, a, b, lcs):
        self.assertEquals(longest_common_subsequence(a, b), lcs)
        
    def testTrivialFull(self):
        self.check("AAAAAAA", "AAAA", "AAAA")
        
    def testTrivialEmpty(self):
        self.check("AAAAAAA", "BBBB", "")
                
    def testCLRS(self):
        # CLRS pg 391
        self.check("ABCBDAB", "BDCABA", "BDAB")
        
    def testCLRS_DNA(self):
        # CLRS pg 391
        self.check("ACCGGTCGAGTGCGCGGAAGCCGGCCGAA", "GTCGTTCGGAATGCCGTTGCTCTGAAA", "GTCGTCGGAAGCCGGCCGAA")
        
    def testJS(self):
        # Johnsonbaugh and Schaefer, pg 343
        self.check("GDVEGTA", "GVCEKST", "GVET")
        
    def testDPV(self):
        # Dasgupta et al, pg 157 (adapted from Longest Increasing
        # Subsequence problem)
        self.check("52863697", "123456789", "2367")
        
    def testAC(self):
        # Algorithmic Commonplaces, Section 6.2
        self.check("datastructures", "algorithms", "barts")
        