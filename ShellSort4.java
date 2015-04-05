package sorting;

import java.io.*;
import java.lang.Math;

public class SortShell4 {
// a sorting shell for experiments with the refined quicksort

public static void quicksort (int [] a) {
// method to sort using the refined quicksort
    quicksortKernel (a, 0, a.length-1);
}

private static void quicksortKernel (int [] a, int first, int last) {
   int left = first + 1;
   int right = first + 1;
   int temp;

    // begin by selecting a random integer, midIndex, between first and last,
    // and then swap a[first] with a[midIndex].
    int midIndex = first + (int)Math.floor((last-first)*Math.random());
    temp = a[first];
    a[first] = a[midIndex];
    a[midIndex] = temp;

   // progress through array, 
   //     moving small elements to a[first+1] .. a[left-1]
   //     and moving large lements to a[left] .. a[right-1]
   while (right <= last)
     {
       if (a[first] < a[right])
          right++;
       else {
          // swap a[left] and a[right]
          temp = a[right];
          a[right] = a[left];
          a[left] = temp;
          left++;
          right++;
       }
     }
    // put a[first] in its place
    temp = a[first];
    a[first] = a[left-1];
    a[left-1] = temp;

    // recursively apply algorithm to a[first]..a[left-2] 
    // and a[left]..a[last], provided these segments contain >= 2 items
    if (first < left-2)
        quicksortKernel (a, first, left-2);
    if (left < last)
        quicksortKernel (a, left, last);   
}

public static void main (String [] args) 
    throws Exception {

    // declarations: two arrays and I/O;
    int size = 12;
    int [] c = new int [size];
    int [] d = new int [size];
    InputStreamReader istream  = new InputStreamReader(System.in);
    BufferedReader in = new BufferedReader(istream);
    PrintWriter out = new PrintWriter(System.out, true);
    String line;

    // initial arrays to same values by reading and copying
    out.println ("Please enter " + size + " integer values to sort,"
                 + " with each number on a separate line");
    for (int i = 0; i < size; i++) {
        line = in.readLine();
        c[i] = new Integer(line).intValue();
        d[i] = c[i];
    }

    // sort and print first array
    quicksort (c);
    out.println ("Result of Refined Quicksort:");
    for (int i = 0; i < size; i++) 
        out.print(c[i] + "\t");
    out.println();

} // main
} // SortShell3