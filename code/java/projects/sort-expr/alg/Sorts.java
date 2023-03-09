package alg;

public class Sorts {
    public static interface Sorter {
        int sort(int[] sequence);
    }
    public static class Selection implements Sorter {
        public int sort(int[] sequence) {
            int compars = 0;   // comparisons
            int n = sequence.length;

            for (int i = 0; i + 1 <  n; i++)  {
                int minPos = i;
                int min = sequence[i];

                for (int j = i + 1; j < n; j++)  {
                    compars++;
                    if (sequence[j] < min)  {
                        min = sequence[j];
                        minPos = j;
                      }
                  }
              
                sequence[minPos] = sequence[i];
                sequence[i] = min;
              }
            return compars;
        }
    }

    public static class Insertion implements Sorter {
        public int sort(int[] sequence) {
            int compars = 0;   // comparisons
            int n = sequence.length;
            for (int i = 1; i < n; i++)  {
              compars++;
              int j;
              for (j = i; j  > 0 && sequence[j] < sequence[j-1]; j--)  {
                  int temp = sequence[j];
                  sequence[j] = sequence[j-1];
                  sequence[j-1] = temp;
                  compars++;
                }
              if (j == 0) compars--;
            }
          
          return compars;
        }
    }

    public static class Merge implements Sorter {
        private static int merge(int[] sequence, int[] aux, int low,int high)  {
          int i, j, k;
          int n = high - low;
          int midpoint = (low + high) / 2; // index to the middle of the range
          int comparisons = 0;
          //the subarrays
          i = low;          // index into the first subarray
          j = midpoint;    // index into the second subarray
          for (k = 0; k < n; k++) {     // (k is index into aux)
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

        private static int mergeSortR(int[] sequence, int[] aux, int low, int high)
        {
          if (low + 1 >= high) 
            return 0;
          else {
            int compars = 0;  // the number of comparisons
            int midpoint = (low + high) / 2; // index to the middle of the range
            compars += mergeSortR(sequence, aux, low, midpoint);
            compars += mergeSortR(sequence, aux, midpoint, high);
            compars = merge(sequence, aux, low, high);
            return compars;
          }
        }
        public int sort(int[] sequence) {
            int[] aux = new int[sequence.length];
            return mergeSortR(sequence, aux, 0, sequence.length);
        }
    }

    public static class Quick implements Sorter {
        private static int quickSortR(int sequence[], int low, int high) {
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

          return compars + quickSortR(sequence, low, i)
            + quickSortR(sequence, i+1, high);
        }

        public int sort(int[] sequence) { return quickSortR(sequence, 0, sequence.length);
        }
    }

    public static class Bubble implements Sorter {
        public int sort(int[] sequence) {
            int compars = 0;
            
            // Add code here in part a

            return compars;

        }
    }
    
    public static class Shell implements Sorter {
        public int sort(int[] sequence) {
            int compars = 0;
            
            // Add code here in part b

            return compars;
           
        }
    }
}
