/* arrayUtil.h */

/*
  Header file for library of utility functions
  for arrays (plus getTimeMillis(), for making
  time measurements)

  Thomas VanDrunen
  Algorithmic Commonplaces
  August 16, 2010
*/


/*  Fill an array with random values. */
void random_array(int array[], int n);

/* Blank-out an array */
void blank_array(int array[], int n);

/* Print an array to the screen, ending with a 
   new line. */
void display_array(int array[], int n);


/* Report the number of milliseconds since
   the epoch (midnight, Jan 1, 1970) */
long int get_time_millis();

/* Copy the contents from the source array to the destination array,
   both assumed to have length n. */
void copy_array(int a[], int b[], int n);

/* Test whether a given array is sorted.
   Returns 0 (false) if any items are out of order,
   1 (true) if the array is sorted */
int is_sorted(int array[], int n);

/* Test whether two arrays (assumed to be the same length,
   n) have the same contents---the same values with the same
   frequencies. */
int same_contents(int a[], int b[], int n);

