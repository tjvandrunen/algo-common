#include "array_util.h"
#include "sorts.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <math.h>

double run(int (*sort)(int[], size_t), int master_array[], int n) 
{
    double fore, aft;
    int* test_array = (int*) calloc(sizeof(int), n);
    copy_array(master_array, test_array, n);
    fore = get_time_millis();
    sort(test_array, n);
    aft = get_time_millis();
    free(test_array);
    return aft-fore;
}

int main()
{
  int* master_array;
  int n, i, trials;

  double time_ms, time_qs, time_rs;

  trials = 1;
  int x = 0;
  for (n = 1000; n <= 10000000; ) 
    {
      master_array = (int*) calloc(sizeof(int), n);

      time_ms = time_qs = time_rs = 0.0;

      for (i = 0; i < trials; i++) 
        {
          random_array(master_array, n);
          time_ms += run(merge_sort, master_array, n);
          time_qs += run(quick_sort, master_array, n);
          time_rs += run(radix_sort, master_array, n);
        }

      printf("%d&%f&%f&%f\\\\\n",
             n,
             time_ms / trials, time_qs / trials, time_rs / trials);
      free(master_array);
      if (x % 2 == 0) n *= 5;
      else n *= 2;
      x++;
    }
  return 0; 
}
