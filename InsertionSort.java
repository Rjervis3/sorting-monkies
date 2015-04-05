
package sorting;

import java.io.*;

public class InsertionSort {
public static void insertionSort (int [] a) {
// method to sort using the insertion sort
   for (int k = 1; k < a.length; k++) {
      int item = a[k];
      int i = k-1;
      while ((i >= 0) && a[i] > item){
         a[i+1] = a[i];
         i--;
      }
      a[i+1] = item;
   }
}

public static void main (String[] args)
{


	
}
}