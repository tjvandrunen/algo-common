'''
Created on Jan 16, 2018

@author: tvandrun
'''

def mat_find1(M, x):
    i = 0
    found = False
    while not found and i < len(M):
        j = 0
        while not found and j < len(M[i]) :
            found = M[i][j] == x
            j += 1
        i += 1
    if found :
        return (i-1, j-1)
    else :
        return None
        


def mat_find2(M, x):
    i = len(M) - 1
    j = 0
    found = False
    while not found and i >= 0  and j < len(M[i]):
        while i >= 0 and M[i][j] > x :
            i -= 1
        while i >= 0 and j < len(M[i]) and M[i][j] < x :
            j += 1
        if i >= 0 and j < len(M[i]) :
            found = M[i][j] == x
    
    if found :
        return (i, j)
    else :
        return None
    

def mat_find3(M, x):
    return mad_find3_rec(M, x, 0, len(M), 0, len(M[1])) 
    
# def mat_find3_rec(M, x, ilow, ihigh, jlow, jhigh):
#     if ihigh - ilow == 0 or jhigh - jlow == 0 :
#         return None
#     if ihigh - ilow == 1 and jhigh - jlow == 1 :
#         if M[ilow][jlow] == x :
#             return (ilow, jlow)
#         else :
#             return None
#     
#     imid = (ihigh - ilow) / 2
#     jmid = (jhigh - jlow) / 2
#     if M[imid][jmid] == x :
#         return (imid, jmid)
#     elif M[imid][jmid] < x


import unittest

M1 = [[3, 2, 6, 12, 456, 87, 23, 16],
      [76, 123, 61, 14, 357, 78, 333, 85],
      [8, 43, 16, 65, 43, 114, 76, 83], 
      [25, 343, 11, 637, 46, 444, 567, 17],
      [0, 91, 29, 740, 9, 18, 25, 709],
      [28, 35, 70, 919, 3, 257, 19], 
      [2, 83, 79, 128, 81, 40, 918, 23], 
      [7, 409, 1, 2, 3, 8, 20, 4]]
       
M2 = [[1, 16, 23, 26, 38, 44, 57, 78],
      [5, 21, 24, 39, 47, 51, 97, 102],
      [18, 22, 40, 41, 59, 63, 71, 103],
      [26, 38, 45, 62, 77, 80, 110, 157],
      [55, 72, 81, 87, 93, 104, 117, 193],
      [74, 79, 88, 109, 121, 148, 175, 214],
      [84, 95, 100, 118, 143, 151, 182, 227],
      [115, 122, 134, 176, 199, 216, 284, 300]]       
       
class TestMF(unittest.TestCase):
    def testBoundariesUS(self):
        self.assertEqual(mat_find1(M1, 3), (0,0))
        self.assertEqual(mat_find1(M1, 87), (0, 5))
        self.assertEqual(mat_find1(M1, 16), (0, 7))
        self.assertEqual(mat_find1(M1, 28), (5, 0))
        self.assertEqual(mat_find1(M1, 7), (7, 0))
        self.assertEqual(mat_find1(M1, 4), (7, 7))
        
    def testInsidesUS(self):
        self.assertEqual(mat_find1(M1, 14), (1,3))
        self.assertEqual(mat_find1(M1, 91), (4,1))
        self.assertEqual(mat_find1(M1, 918), (6,6))
        
    def testNegativesUS(self):
        self.assertEqual(mat_find1(M1, 13), None)
        self.assertEqual(mat_find1(M1, 119), None)
        self.assertEqual(mat_find1(M1, 287), None)
        self.assertEqual(mat_find1(M1, 311), None)
        
    def testBoundariesS(self):
        self.assertEqual(mat_find1(M2, 1), (0,0))
        self.assertEqual(mat_find1(M2, 44), (0, 5))
        self.assertEqual(mat_find1(M2, 78), (0, 7))
        self.assertEqual(mat_find1(M2, 74), (5, 0))
        self.assertEqual(mat_find1(M2, 115), (7, 0))
        self.assertEqual(mat_find1(M2, 300), (7, 7))
        
    def testInsidesS(self):
        self.assertEqual(mat_find1(M2, 39), (1,3))
        self.assertEqual(mat_find1(M2, 79), (5,1))
        self.assertEqual(mat_find1(M2, 182), (6,6))
        
    def testNegativesS(self):
        self.assertEqual(mat_find1(M2, 13), None)
        self.assertEqual(mat_find1(M2, 119), None)
        self.assertEqual(mat_find1(M2, 287), None)
        self.assertEqual(mat_find1(M2, 311), None)
        
class TestMF2(unittest.TestCase):
    def testBoundaries(self):
        self.assertEqual(mat_find2(M2, 1), (0,0))
        self.assertEqual(mat_find2(M2, 44), (0, 5))
        self.assertEqual(mat_find2(M2, 78), (0, 7))
        self.assertEqual(mat_find2(M2, 74), (5, 0))
        self.assertEqual(mat_find2(M2, 115), (7, 0))
        self.assertEqual(mat_find2(M2, 300), (7, 7))
        
    def testInsides(self):
        self.assertEqual(mat_find2(M2, 39), (1,3))
        self.assertEqual(mat_find2(M2, 79), (5,1))
        self.assertEqual(mat_find2(M2, 182), (6,6))
        
    def testNegatives(self):
        self.assertEqual(mat_find2(M2, 13), None)
        self.assertEqual(mat_find2(M2, 119), None)
        self.assertEqual(mat_find2(M2, 287), None)
        self.assertEqual(mat_find2(M2, 311), None)
        
class TestMF3(unittest.TestCase):
    def testBoundaries(self):
        self.assertEqual(mat_find3(M2, 1), (0,0))
        self.assertEqual(mat_find3(M2, 44), (0, 5))
        self.assertEqual(mat_find3(M2, 78), (0, 7))
        self.assertEqual(mat_find3(M2, 74), (5, 0))
        self.assertEqual(mat_find3(M2, 115), (7, 0))
        self.assertEqual(mat_find3(M2, 300), (7, 7))
        
    def testInsides(self):
        self.assertEqual(mat_find3(M2, 39), (1,3))
        self.assertEqual(mat_find3(M2, 79), (5,1))
        self.assertEqual(mat_find3(M2, 182), (6,6))
        
    def testNegatives(self):
        self.assertEqual(mat_find3(M2, 13), None)
        self.assertEqual(mat_find3(M2, 119), None)
        self.assertEqual(mat_find3(M2, 287), None)
        self.assertEqual(mat_find3(M2, 311), None)
        
        
       
