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

  double time_rs;

  trials = 10;
  for (n = 64; n <= 1048576; n *= 2) 
    {
      master_array = (int*) calloc(sizeof(int), n);

      time_rs = 0.0;

      for (i = 0; i < trials; i++) 
        {
          random_array(master_array, n);
          time_rs += run(radix_sort, master_array, n);
        }

       double ave = time_rs / trials,
         ndub = n,
         nlgn = ndub * (log(n)/log(2));
       

      printf("%d&%f&%f&%f\\\\\n",
             n, ave, ave / n, ave / nlgn);
      free(master_array);
    }
  return 0; 
}
