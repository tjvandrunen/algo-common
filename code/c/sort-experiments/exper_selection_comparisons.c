#include "array_util.h"
#include "sorts.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main()
{
  int test_array[1000];
  int n, i, comparisons;
  for (n = 10; n <= 1000; n *= 10) 
    {
      printf("%d\t" , n);
      for (i = 0; i < 5; i++) 
        {
          random_array(test_array, n);
          comparisons = selection_sort(test_array, n);
          printf("%d\t", comparisons);
        }
      printf("\n");
    }
  return 0;
}
