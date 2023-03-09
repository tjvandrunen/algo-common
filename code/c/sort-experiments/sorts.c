
/* sort.c */

#include "sorts.h"
#include "array_util.h"
#include <stdio.h>
#include <stdlib.h>

int selection_sort(int sequence[], size_t n) 
{
  int compars = 0;   /* comparisons */
  unsigned int i, j;

  for (i = 0; i + 1 <  n; i++) 
    {
      int minPos = i;
      int min = sequence[i];

      for (j = i + 1; j < n; j++) 
        {
          compars++;
          if (sequence[j] < min) 
            {
              min = sequence[j];
              minPos = j;
            }
        }
    
      sequence[minPos] = sequence[i];
      sequence[i] = min;
    }
  return compars;
}

int insertion_sort(int sequence[], size_t n) 
{
  int compars = 0;
  unsigned int i, j;

  for (i = 1; i < n; i++) 
    {
      compars++;
      for (j = i; j  > 0 && sequence[j] < sequence[j-1]; j--) 
        {
          int temp = sequence[j];
          sequence[j] = sequence[j-1];
          sequence[j-1] = temp;
          compars++;
        }
      if (j == 0) compars--;
    }
  
  return compars;
}

int bubbleSort(int sequence[], size_t n) 
{
  int compars = 0;
  unsigned int i;
  
  int changed = 1;
  while (changed) 
    {
      changed = 0;
      for (i = 1; i < n; i++)
        {
          compars++;
          if (sequence[i] < sequence[i-1])
            {
              changed = 1;
              int temp = sequence[i];
              sequence[i] = sequence[i-1];
              sequence[i-1] = temp;
            }
        }
    }
  
  return compars;
}

int bubbleSort2(int sequence[], size_t n)
{
  int compars = 0;

  int i, j;

  for (j = n; j > 1; j--) 
    {
      for (i = 1; i < j; i++)
        {
          compars++;
          if (sequence[i] < sequence[i-1])
            {
              int temp = sequence[i];
              sequence[i] = sequence[i-1];
              sequence[i-1] = temp;
            }
        }
    }

  return compars;
}


int merge(int sequence[], int aux[], int low,int high) 
{
  int i, j, k;
  int n = high - low;
  int midpoint = (low + high) / 2; // index to the middle of the range
  int comparisons = 0;
  //the subarrays
  i = low;          // index into the first subarray
  j = midpoint;    // index into the second subarray
  for (k = 0; k < n; k++)      // (k is index into aux)
    {
      if (i >= midpoint) aux[k] = sequence[j++];
      else if (j >= high) aux[k] = sequence[i++];
      else if (sequence[i] < sequence[j]) {
        comparisons++;
        aux[k] = sequence[i++];
      }
    else {
      comparisons++;
      aux[k] = sequence[j++];
    }
  }
    for (k = 0; k < n; k++)
      sequence[low + k] = aux[k];
  return comparisons;
}

int merge_sort_r(int sequence[], int aux[], int low, int high)
{
  if (low + 1 >= high) 
    return 0;
  else {
    int compars = 0;  // the number of comparisons
    int midpoint = (low + high) / 2; // index to the middle of the range
    int n;
    n = high - low;
    compars += merge_sort_r(sequence, aux, low, midpoint);
    compars += merge_sort_r(sequence, aux, midpoint, high);
    compars = merge(sequence, aux, low, high);
    
    return compars;
  }
}

int merge_sort(int sequence[], size_t n)
{
  int comparisons;
  int *aux= (int*) calloc(sizeof(int), n);
  comparisons =  merge_sort_r(sequence, aux, 0, n);
  free(aux);
  return comparisons;
}


int quick_sort_r(int sequence[], int low, int high)
{
  if (low + 1 >= high) return 0;

  int i, j, temp;
  int compars = 0;
  for (i = j = low; j < high-1; j++) {
    compars++;
    if (sequence[j] < sequence[high-1])
      {
        temp = sequence[j];
        sequence[j] = sequence[i];
        sequence[i] = temp;
        i++;
      }
  }
  
  temp = sequence[i];
  sequence[i] = sequence[j];
  sequence[j] = temp;

  return compars + quick_sort_r(sequence, low, i)
    + quick_sort_r(sequence, i+1, high);
}

int quick_sort(int sequence[], size_t n)
{
  return quick_sort_r(sequence, 0, n);
}

int superQuickSortR(int sequence[], int low, int high)
{
  if (low + 1 >= high) return 0;

  int piv =  rand() % (high - low) + low;
  int pivVal = sequence[piv];   
  int i, j, temp;
  int compars = 0;
  sequence[piv] = sequence[low];
  sequence[low] = pivVal;
  for (i = j = low + 1; j < high; j++) {
    compars++;
    if (sequence[j] < pivVal)
      {
        temp = sequence[j];
        sequence[j] = sequence[i];
        sequence[i] = temp;
        i++;
      }
  }
  
  temp = sequence[i-1];
  sequence[i-1] = sequence[low];
  sequence[low] = temp;

  return compars + superQuickSortR(sequence, low, i-1)
    + superQuickSortR(sequence, i, high);
}

int superQuickSort(int sequence[], size_t n)
{
  return superQuickSortR(sequence, 0, n);
}




int shellSort(int sequence[], size_t n)
{
  int compars = 0;
  unsigned int gap, i, j, k;

  for (gap = n / 3; ; gap = gap / 13 + 1)
    {
      //printf("(%d)\n", gap);
      for (k = 0; k < gap; k++)
        for (i = k + gap; i < n; i += gap) 
          {
            compars++;
            for (j = i; j  >= gap && sequence[j] < sequence[j-gap]; j -= gap) 
              {
                int temp = sequence[j];
                sequence[j] = sequence[j-gap];
                sequence[j-gap] = temp;
                compars++;
              }
          }
      if (gap == 1) break;
    }  

  return compars;
}


int movingPivotPartition(int sequence[], int low, int high)
{
  int i = low;
  int j = i + 1;
  while (j < high)
    {
      if (sequence[j] <= sequence[i])
        {
          int temp = sequence[j];
          sequence[j] = sequence[i+1];
          sequence[i+1] = temp;
          if (sequence[i+1] < sequence[i])
            {
              sequence[i+1] = sequence[i];
              sequence[i] = temp;
              i++;
            }
        }
      j++;
    }

  return i;


}

int quickSortNewR(int sequence[], int low, int high)
{
  
  if (low + 1 >= high) return 0;

  int mid = movingPivotPartition(sequence, low, high);
  
  return (high-low) + quickSortNewR(sequence, low, mid)
    + quickSortNewR(sequence, mid+1, high);
}

int quickSortNew(int sequence[], size_t n)
{
  return quickSortNewR(sequence, 0, n);
}


int radix_sort(int array[], size_t n)
{


  int* aux = (int*) calloc(sizeof(int), n);

  int *src = array,
    *dst = aux;
  int zeros, ones;  
  int  next_place[2];
  unsigned int mask, bit, i;

  for (bit = 0; bit < (8 * sizeof(int)) - 1; bit++) {
    if (bit % 2 == 0) {
      src = array;
      dst = aux;
    }
    else {
      src = aux;
      dst = array;
    }
    
    mask = 1 << bit;
    
    zeros = 0;
    for (i = 0; i < n; i++) if (! (src[i] & mask)) zeros++;
    
    next_place[0] = 0;
    next_place[1] = zeros;
    
    for (i = 0; i < n; i++) {
      int item = src[i];
      dst[next_place[(item & mask)? 1: 0]++] = item;
    }
  }
  
  // What would be the last iteration of the loop...
  // This needs to be handled differently because
  // now we're looking at the sign bit, so a 1 (negative)
  // is smaller than 0 (positive).
  // Also, we don't need to use src/dst, we know
  // we're copying from aux to array.
  mask = 1 << bit;
  ones = 0;
  for (i = 0; i < n; i++) if (src[i] & mask) ones++;
  
  next_place[0] = 0;
  next_place[1] = ones;
  
  for (i = 0; i < n; i++) {
    int item = aux[i];
    array[next_place[(item & mask)? 0:1]++] = item;
  }
  
  free(aux);
  return 0;  // no comparisons!
}



 /* 
int stinySort(int sequence[], size_t n) 
{
  return 0;
}
*/
