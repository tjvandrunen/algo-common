#ifndef SORTS
#define SORTS
#include <stdlib.h>
/* sorts.h */

/* 
   Each function sorts the given array with
   a different sorting algorithm. Each sort is
   either in-place or has the appearance of
   in-place. Each function returns the number
   of comparisons.
*/

int selection_sort(int array[], size_t n);

int insertion_sort(int array[], size_t n);

int bubbleSort(int array[], size_t n);

int bubbleSort2(int array[], size_t n);

int merge_sort(int array[], size_t n);

int merge(int array[], int aux[], int start, int stop);

int quick_sort(int array[], size_t n);

int superQuickSort(int array[], size_t n);

int shellSort(int array[], size_t n);

int radix_sort(int array[], size_t n);

int movingPivotPartition(int array[], int start, int stop);

int quickSortNew(int array[], size_t n);

int stinySort(int array[], size_t n);
#endif
