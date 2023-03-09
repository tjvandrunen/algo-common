'''
Created on Sep 30, 2019

@author: thomasvandrunen
'''

# A quick and simple heap priority queue, no optimization for
# finding a key.
# Priority is based on a numerical value (designed for
# the weight to add a vertex); a *lower* value is a *higher* priority.
class PriorityQueue:
    def __init__(self, n, inverse_priority):
        self.internal = [i for i in range(n)]
        self.heap_size = n
        self.inverse_priority = inverse_priority
        
    def is_empty(self):
        return self.heap_size == 0

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
            heap_oks = [left >= self.heap_size or  self.inverse_priority[self.internal[i]] < self.inverse_priority[self.internal[left]],
                        right >= self.heap_size or self.inverse_priority[self.internal[i]] < self.inverse_priority[self.internal[right]]]
            if heap_oks == [True, True]:
                break
            else :
                v = self.internal[i]
                if heap_oks == [False, False] :
                    left_smaller = self.inverse_priority[self.internal[left]] < self.inverse_priority[self.internal[right]]
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
    
    def find_key(self, v):
        i = 0
        while  i < self.heap_size and self.internal[i] != v :
            i += 1
        if i == self.heap_size :
            return None
        else :
            return i

    def contains(self, v):
        return self.find_key(v) != None
    
    def increase_key(self, v):
        i = self.find_key(v)
        parent = (i - 1) / 2
        while i > 0 and self.inverse_priority[self.internal[i]] < self.inverse_priority[self.internal[parent]] :
            v = self.internal[i]
            self.internal[i] = self.internal[parent]
            self.internal[parent] = v
            i = parent
            parent = (i - 1) / 2
            
    def __str__(self):
        return str(self.internal[:self.heap_size])
