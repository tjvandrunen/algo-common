#include "bitops.h"
#include <stdio.h>

int main()
{
  int i;
  for (i = 0; i < 17; i++)
    printf("%d %d\n", i, sum_bits_set(i));

  return 0;
  
}
