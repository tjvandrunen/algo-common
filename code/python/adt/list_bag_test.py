#!/usr/bin/env python
import unittest
import bag_test
import bag

class ListBagTest(bag_test.BagTest):
    def setUp(self):
        self.bag = bag.ListBag()

def main():
    unittest.main()

del(bag_test.BagTest)

if __name__ == '__main__':
    main()
