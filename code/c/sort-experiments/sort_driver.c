#include "array_util.h"
#include "sorts.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>


int samples[][25]  =
  { { },
    { 5 },
    { 1, 2 },
    { 2, 1},
    { 3, 3},
    { 1, 2, 3 },
    { 3, 2, 1 },
    { 2, 1, 3}, 
    { 2, 1, 1},
    { 2, 1, 2},
    { 3, 3, 1},
    { 1, 2, 3, 4, 5, 6, 7, 8 },
    { 8, 7, 6, 5, 4, 3, 2, 1},
    { 5, 8, 3, 5, 8, 7, 9, 2 },
    { 2, 5, 5, 5, 5, 5, 5, 2 },
    { -5, 7, 14, 81, -10, 0, 8, 8},
    { 7, 7, 7, 7, 7, 7, 7, 7},
    {33, 29, 7, 96, 6, 62, 59, 0, 36, 93, 27, 10, 77, 19, 22, 88, 41, 29, 8, 87, 59, 57, 13, 65, 75},
    {725, 110, 785, 972, 62, 971, 106, 31, 487, 81, 599, 248, 932, 147, 752, 549, 940, 271, 174, 968},
    {12, 54, -23, 76, -565, -3, 5, 8, 5, 23, 85, -69, 45, 2, 36, 86, -34, -2, 4, 64 }
};

int sizes[] = { 0, 1, 2, 2, 2, 3, 3, 3, 3, 3, 3, 8, 8, 8, 8, 8, 8, 25, 30, 20  };

char explanations[][30] = {
  "empty array",
  "trivial array",
  "sorted array",
  "back sorted array",
  "uniform value array",
  "sorted array",
  "back sorted array",
  "scrambled array",
  "repeated value array",
  "repeated value array",
  "repeated value array",
  "sorted array",
  "back sorted array",
  "scrambled array",
  "repeated value array",
  "array with negative numbers",
  "uniform value array",
  "scrambled array",
  "big number array",
  "array with negatives"};

int num_tests = 19;

int main(int argc, char** argv) 
{

  int i;
  int *temp;
  int failure = 0;

  int (*sorter) (int[], size_t);

  temp = (int*) calloc(sizeof(int), 30);
  
  if (argc < 2) {
    printf("Please enter a sorting algorithm to test.\n");
    free(temp);
    return 0;
  }
  else if (strcmp(argv[1], "selection") == 0)
    sorter = selection_sort;
  else if (strcmp(argv[1], "insertion") == 0)
    sorter = insertion_sort;
  else if (strcmp(argv[1], "bubble") == 0)
    sorter = bubbleSort;
  else if (strcmp(argv[1], "bubble2") == 0)
    sorter = bubbleSort2;
  else if (strcmp(argv[1], "merge") == 0)
    sorter = merge_sort;
  else if (strcmp(argv[1], "quick") == 0)
    sorter = quick_sort;
  else if (strcmp(argv[1], "superQuick") == 0)
    sorter = superQuickSort;
   else if (strcmp(argv[1], "shell") == 0)
    sorter = shellSort;
   else if (strcmp(argv[1], "radix") == 0)
     sorter = radix_sort;
   else if (strcmp(argv[1], "quickSortNew") == 0)
     sorter = quickSortNew;
   //else if (strcmp(argv[1], "stiny") == 0)
    // sorter = stinySort;
 else
    {
      printf("unknown sort\n");
      free(temp);
      return 0;
    }


  
  for (i = 0; i < 19; i++) 
    {
      int preserves_contents, sorted; 
      int n = sizes[i];
      copy_array(samples[i], temp, n);

      sorter(temp, n);
      preserves_contents = same_contents(samples[i], temp, n);
      sorted = is_sorted(temp, n);
      if (!(preserves_contents && sorted))
        {
          failure = 1;
          printf("Failure on %s, size %d:\n", explanations[i], n);
          display_array(samples[i], n);
          display_array(temp, n);
        }
    }          
  if (! failure)
    printf("All tests pass.\n");
  free(temp);
  return 0;
}
