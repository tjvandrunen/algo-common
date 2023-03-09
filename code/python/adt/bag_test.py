#!/usr/bin/env python
import unittest
import random

# tests a bag stored in the instance variable bag
class BagTest(unittest.TestCase):

    def test_add(self):
        self.assertEquals(len(self.bag),0)
        self.bag.add(1)
        self.assertEquals(len(self.bag),1)

    def test_add_count(self):
        self.assertEquals(len(self.bag),0)
        self.bag.add(1)
        self.assertEquals(len(self.bag),1)
        self.bag.add(2)
        self.assertEquals(len(self.bag),2)
        self.bag.add(1)
        self.assertEquals(len(self.bag),3)
        self.assertEquals(2,self.bag[1])
        self.assertEquals(1,self.bag[2])

    def test_add_count_remove(self):
        self.assertEquals(len(self.bag),0)
        self.bag.add(1)
        self.assertEquals(len(self.bag),1)
        self.bag.add(2)
        self.assertEquals(len(self.bag),2)
        self.bag.add(1)
        self.assertEquals(len(self.bag),3)
        self.assertEquals(2,self.bag[1])
        self.assertEquals(1,self.bag[2])
        self.bag.discard(1)
        self.assertEquals(0,self.bag[1])
        self.assertEquals(1,self.bag[2])
        self.assertEquals(len(self.bag),1)

    def test_iter(self):
        for i in range(10):
            for j in range(i):
                self.bag.add(i)
        counts = [0]*10
        for i in self.bag:
            counts[i] += 1
        for i in range(10):
            self.assertEquals(i,counts[i])
