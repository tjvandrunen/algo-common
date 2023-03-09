/* array_util.c */

#include "array_util.h"
#include<stdlib.h>
#include<stdio.h>
#include<assert.h>
#include<sys/time.h>
#include<time.h>





void random_array(int array[], int n)
{
  int i;
  static int initialized = 0;

  if (! initialized)
    {
      srand(time(NULL));
      initialized = 1;
    }

  for (i = 0; i < n; i++)
    array[i] = rand() % 10000;
    
}

void blank_array(int array[], int n)
{
  int i;

  for (i = 0; i < n; i++)
    array[i] = 0;


}

void display_array(int array[], int n) 
{
  int i;


  for (i = 0; i< n; i++)
    printf("%d ", array[i]);
  printf("\n");
}


long int get_time_millis()
{
  struct timeval time;
  gettimeofday(&time, NULL);
  return time.tv_sec * 1000 + time.tv_usec / 1000;

}

void copy_array(int source[], int destination[], int n)
{
  int i;
  for (i = 0; i < n; i++)
    destination[i] = source[i];
}

int is_sorted(int array[], int n)
{
  int sorted;
  int i;
  for (sorted = 1, i = 0; 
       sorted && i < n-1;
       i++)
    sorted &= (array[i] <= array[i+1]);

  return sorted;
}

int same_contents(int a[], int b[], int n)
{
  int min, max;
  int i;
  int* tally;
  int contents_match;

  if (n <= 0) return 1;

  min = max = a[0];

  for (i = 0; i < n; i++)
    {
      if (a[i] < min) min = a[i];
      if (a[i] > max) max = a[i];
    }

  tally = (int*) calloc(sizeof(int), 1 + max - min);

  for (i = 0; i < n; i++)
    {
      assert(a[i] - min >= 0 && a[i] - min < 1 + max - min);
      tally[a[i] - min]++;
    }

  for (i = 0; i < n; i++)
    {
      if (b[i] >= min && b[i] <= max)
        {
          assert(b[i] - min >= 0 && b[i] - min < 1 + max - min);
          tally[b[i] - min]--;
        }
    }

  for (i = 0, contents_match = 1; 
       contents_match && i < 1 + max - min;
       i++)
    contents_match &= (tally[i] == 0); 

  free(tally);
  tally = NULL;

  return contents_match;

}
