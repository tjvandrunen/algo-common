#include "sorts.h"
#include "array_util.h"
#include <string.h>
#include <stdio.h>

int main(int argc, char** argv)
{

  int* array;
  int compars;
  int n;

  if (argc < 3) n = 10;
  else n = atoi(argv[2]);

  array = randomArray(n);

  if (n <= 20)
    displayArray(array);

  if (strcmp(argv[1], "selection") == 0)
    compars = selectionSort(array);
  else if (strcmp(argv[1], "insertion") == 0)
    compars = insertionSort(array);
  else if (strcmp(argv[1], "bubble") == 0)
    compars = bubbleSort(array);
  else if (strcmp(argv[1], "bubble2") == 0)
    compars = bubbleSort2(array);
  else if (strcmp(argv[1], "merge") == 0)
    compars = mergeSort(array);
  else if (strcmp(argv[1], "quick") == 0)
    compars = quickSort(array);
  else if (strcmp(argv[1], "superQuick") == 0)
    compars = superQuickSort(array);
   else if (strcmp(argv[1], "shell") == 0)
    compars = shellSort(array);
   else if (strcmp(argv[1], "radix") == 0)
    compars = radixSort(array);
 else
    {
      printf("unknown sort\n");
      return 0;
    }

  if (n <= 20)
    displayArray(array);
  printf("The resulting array is ");
  if (! isSorted(array))
    printf("NOT ");
  printf("sorted.\n");
  printf("%d comparisons.\n", compars);

} 


