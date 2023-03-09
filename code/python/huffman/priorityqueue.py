'''
Created on Sep 30, 2019

@author: thomasvandrunen
'''

# A quick and simple heap priority queue, no optimization for
# finding a key.
# For the priority function, priority(a, b) means, does a have a higher
# priority than b?
class PriorityQueue:
    def __init__(self, n, priority):
        self.internal = [None for i in range(n)]
        self.heap_size = 0
        self.priority = priority
        
    def is_empty(self):
        return self.heap_size == 0

    def insert(self, k):
        self.internal[self.heap_size] = k
        i = self.heap_size
        self.heap_size += 1
        while i > 0 and self.priority(self.internal[i], self.internal[i // 2]) :
            temp = self.internal[i]
            self.internal[i] = self.internal[i // 2]
            self.internal[i // 2] = temp
            i = i // 2
    

    # Extract the key with max priority, which is min weight    
    def extract_max(self):
        m = self.internal[0]
        self.heap_size -= 1
        self.internal[0] = self.internal[self.heap_size]
        i = 0
        # Maybe this wasn't the simplest way... seemed like it while
        # I was hacking it up...
        while True :
            left = 2 * i + 1
            right = 2 * i + 2
            heap_oks = [left >= self.heap_size or  self.priority(self.internal[i], self.internal[left]),
                        right >= self.heap_size or self.priority(self.internal[i], self.internal[right])]
            if heap_oks == [True, True]:
                break
            else :
                v = self.internal[i]
                if heap_oks == [False, False] :
                    left_smaller = self.priority(self.internal[left], self.internal[right])
                if heap_oks == [False, True] or (heap_oks == [False, False] and left_smaller) :
                    self.internal[i] = self.internal[left]
                    self.internal[left] = v
                    i = left
                elif heap_oks == [True, False] or (heap_oks == [False, False] and not left_smaller):
                    self.internal[i] = self.internal[right]
                    self.internal[right] = v
                    i = right
                else :
                    assert False
        return m
    
    def __str__(self):
        return str(self.internal[:self.heap_size])
