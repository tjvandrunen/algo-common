#include "array_util.h"
#include "sorts.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <math.h>

double run(int (*sort)(int[], size_t), int master_array[], int n) 
{
    double fore, aft;
    int test_array[131072];
    copy_array(master_array, test_array, n);
    fore = get_time_millis();
    sort(test_array, n);
    aft = get_time_millis();
    return aft-fore;
}

int main()
{
  int master_array[131072];
  int n, i, trials;

  double time_is, time_ss, time_ms, time_qs;

  trials = 10;
  for (n = 16; n <= 131072; n *= 2) 
    {
      printf("%d&" , n);

      time_is = time_ss = time_ms = time_qs = 0.0;

      for (i = 0; i < trials; i++) 
        {
          random_array(master_array, n);
          time_is += run(insertion_sort, master_array, n);
          time_ss += run(selection_sort, master_array, n);
          time_ms += run(merge_sort, master_array, n);
          time_qs += run(quick_sort, master_array, n);
        }

      printf("%d&%f&%f&%f&%f\\\\\n",
             n, time_is / trials, time_ss / trials,
             time_ms / trials, time_qs / trials);
      

    }
  return 0; 
}
