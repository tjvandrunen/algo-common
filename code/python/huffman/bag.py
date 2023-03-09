'''
Created on Jul 16, 2014

@author: tvandrun
'''



from collections.abc import Collection

from abc import abstractmethod

class Bag(Collection):
    # Inherts __contains__, __iter__, and __len__ from Collection
    
    # Add an item to the bag, increasing its count if it's there already
    @abstractmethod
    def add(self, x):
        pass
    
    # How many times does this bag contain this item?
    # Equivalent to Bag.count() in Java examples.  
    @abstractmethod
    def __getitem__(self, item):
        pass
    
    # Remove (all occurrences of) an item from the bag, if it's there.
    # Equivalent to Bag.remove() in Java examples.
    @abstractmethod
    def discard(self, item):
        pass
    
    


class DictBag(Bag):
    
    def __init__(self):
        self.internal = {}
        
    def add(self, item):
        if item in self.internal :
            self.internal[item] = self.internal[item] + 1
        else :
            self.internal[item] = 1
    
    def __getitem__(self, item):
        if item in self.internal :
            return self.internal[item]
        else:
            return 0;
    
    def discard(self, item):
        if item in self.internal :
            del self.internal[item]
            
    def __len__(self):
        return sum([self.internal[item] for item in self.internal])
    
    def __iter__(self):
        for item in self.internal :
            for i in range(self.internal[item]) :
                yield item

    def __contains__(self, item):
        return self[item] != 0
    
    def __str__(self):
        return "[" + ",".join([str(item) + ":" + str(self[item]) for item in set(self)]) + "]"
                
class ListBag(Bag):
    
    def __init__(self):
        self.internal = []
        
    def add(self, item):
        self.internal.append(item)
    
    def __getitem__(self, item):
        c = 0
        for x in self.internal :
            if item == x :
                c += 1
        return c
    
    def discard(self, item):
        self.internal = [x for x in self.internal if x != item]
        
    def __len__(self):
        return len(self.internal)
    
    def __iter__(self):
        return iter(self.internal)
    
    def __contains__(self, item):
        return self[item] != 0

