
import copy

# Convert the character at position c in string s to 
# an index into counts or next_place in string_bucket_sort_r(). 
def c2i(s, c):
    return 0 if c >= len(s) else ord(s[c]) - ord('a') + 1
     
# Function to start the recursive process
def string_bucket_sort(sequence):
    string_bucket_sort_r(sequence, 0, len(sequence), 0)
    
# Sort the strings in sequence from start to stop.
# Precondition: The strings in that sequence have
# the same prefix of size c    
def string_bucket_sort_r(sequence, start, stop, pre):
    if start < stop - 1 :
        # The counts for each letter: index 0 for none, 1 for 'a', etc
        counts = [0 for i in range(27)]
        for i in range(start, stop):
            counts[c2i(sequence[i],pre)] += 1

        # The locations for the next occurrences of each letter
        next_place = [0 for i in range(27)]
        for i in range(1, 27) :
            next_place[i] = next_place[i-1] + counts[i-1]

        # A copy of the range that we're sorting
        aux = sequence[start:stop]

        # Place the strings in sorted order by their cth character
        for i in range(len(aux)):
            sequence[start + next_place[c2i(aux[i],pre)]] = aux[i]
            next_place[c2i(aux[i],pre)] += 1
    
        # Sort each subrange, each with strings equal in their cth character
        for i in range(1, 27):
            string_bucket_sort_r(sequence, start + next_place[i-1], start + next_place[i], pre+1)

def string_radix_sort(sequence):
    # Find the length of the longest string
    max_len = max([len(x) for x in sequence])

    # Perform counting sort on each character position from
    # max_len-1 down
    for c in reversed(range(max_len)) :
        counts = [0 for i in range(27)]
        for x in sequence :
            counts[c2i(x,c)] += 1
        next_place = [0 for i in range(27)]
        for i in range(1, 27) :
            next_place[i] = next_place[i-1] + counts[i-1]
        aux = copy.copy(sequence)
        for x in aux:
            sequence[next_place[c2i(x, c)]] = x
            next_place[c2i(x, c)] += 1

        
def string_quick_sort(sequence):
    string_quick_sort_r(sequence, 0, len(sequence), 0)
    
def string_quick_sort_r(sequence, start, stop, pre):
    if start < stop - 1 :
        i = start
        j = i
        # The character from the pivot key for comparison
        pivot = c2i(sequence[stop-1],pre)
        for k in range(start, stop) :
            # The current item to inspect
            key = sequence[k]
            # The relevant character in the item
            c = c2i(key, pre)
            # The three conceptual cases (less than, equal, 
            # or greater than pivot) are dstributed among 
            # these algorithmic cases. 
            if c <= pivot:
                sequence[k] = sequence[j]      # c <= pivot
                if c < pivot :
                    sequence[j] = sequence[i]  # c <  pivot
                    sequence[i] = key          # c <  pivot
                    i += 1                     # c <  pivot
                else :
                    sequence[j] = key          # c == pivot
                j += 1                         # c <= pivot
        string_quick_sort_r(sequence, start, i, pre)
        string_quick_sort_r(sequence, i, j, pre+1)
        string_quick_sort_r(sequence, j, stop, pre)
    

words = ["barbados", "ally", "alley", "petrify", "excite", "zaney",
         "torturous", "amazing", "deprive", "october", "animate",
         "anemone", "paste", "balloon", "understate", "zale",
         "hunger", "lemony", "primate", "rounder", "pinochet",
         "marzapan", "tortuous", "glimpse", "barbituate", "allele",
         "edible", "yucca", "amaze", "excelior", "petrichor",
         "business", "accomplish", "understand", "hiccough",
         "hungry", "pasta", "ballet", "deprivation", "exitus",
         "pomenade", "lemonade", "eagle", "animatronics", "zero",
         "archer", "angst", "illiois", "elephant", "peso",
         "roundabout", "peleton", "sesame", "azure", "pincher",
         "roustabout", "primadona", "orange", "octopus", "marital"]

string_bucket_sort(words)
#string_radix_sort(words)
#string_quick_sort(words)

print words
