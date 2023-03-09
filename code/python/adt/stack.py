'''
Created on Aug 13, 2019

@author: tvandrun
'''

from abc import ABC

from abc import abstractmethod

class Stack(ABC):
    @abstractmethod
    def push(self, item):
        pass
    @abstractmethod
    def top(self):
        pass
    @abstractmethod
    def pop(self):
        pass
    @abstractmethod
    def is_empty(self):
        pass

