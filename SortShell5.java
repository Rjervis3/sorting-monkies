package sorting;

import java.io.*;
import java.lang.Math;

public class SortShell5 {
// a sorting shell for experiments with the quicksort

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
} // insertionSort

public static void quicksort (int [] a) {
// method to sort using the quicksort
    quicksortKernel (a, 0, a.length-1);
}

private static void quicksortKernel (int [] a, int first, int last) {
    int left=first+1;
    int right=last;
    int temp;

    // this part is new from SortShell2
    // begin by selecting a random integer, midIndex, between first and last,
    // and then swap a[first] with a[midIndex].
    int midIndex = first + (int)Math.floor((last-first)*Math.random());
    temp = a[first];
    a[first] = a[midIndex];
    a[midIndex] = temp;

    // quicksortKernel continues in SortShell2
    while (right >= left) {
        // search left to find small array item
        while ((right >= left) && (a[first] <= a[right]))
            right--;
        // search right to find large array item
        while ((right >= left) && (a[first] >= a[left]))
            left++;
        // swap large left item and small right item, if needed
        if (right > left) {
            temp = a[left];
            a[left] = a[right];
            a[right] = temp;
        }
    }
    // put a[first] in its place
    temp = a[first];
    a[first] = a[right];
    a[right] = temp;

    // recursively apply algorithm to a[first]..a[right-1] 
    // and a[right+1]..a[last], provided these segments contain >= 2 items
    if (first < right-1)
        quicksortKernel (a, first, right-1);
    if (right+1 < last)
        quicksortKernel (a, right+1, last);   
}

public static void refQuicksort (int [] a) {
// method to sort using the refined quicksort
    refQuicksortKernel (a, 0, a.length-1);
}

private static void refQuicksortKernel (int [] a, int first, int last) {
    int left=first+1;
    int right=last;
    int temp;

    // this part is new from SortShell2
    // begin by selecting a random integer, midIndex, between first and last,
    // and then swap a[first] with a[midIndex].
    int midIndex = first + (int)Math.floor((last-first)*Math.random());
    temp = a[first];
    a[first] = a[midIndex];
    a[midIndex] = temp;

    // refQuicksortKernel continues following the quicksort algorithm
    while (right >= left) {
        // search left to find small array item
        while ((right >= left) && (a[first] <= a[right]))
            right--;
        // search right to find large array item
        while ((right >= left) && (a[first] >= a[left]))
            left++;
        // swap large left item and small right item, if needed
        if (right > left) {
            temp = a[left];
            a[left] = a[right];
            a[right] = temp;
        }
    }
    // put a[first] in its place
    temp = a[first];
    a[first] = a[right];
    a[right] = temp;

    // recursively apply algorithm to a[first]..a[right-1] 
    // and a[right+1]..a[last], provided these segments contain >= 2 items
    if (first < right-1)
        refQuicksortKernel (a, first, right-1);
    if (right+1 < last)
        refQuicksortKernel (a, right+1, last);   
}

public static void main (String [] args) 
    throws Exception {

    long start_time;
    long end_time;

    // declarations: two arrays and I/O;
    int size = 160000;
    int [] c = new int [size];
    int [] d = new int [size];
    int [] e = new int [size];
    InputStreamReader istream  = new InputStreamReader(System.in);
    BufferedReader in = new BufferedReader(istream);
    PrintWriter out = new PrintWriter(System.out, true);
    String line;

    // initial arrays to same values by reading and copying
    out.println ("Please enter " + size + " integer values to sort,"
                 + " with each number on a separate line");
    for (int i = 0; i < size; i++) {

        /* for user entered data */
        line = in.readLine();
        c[i] = new Integer(line).intValue();

        /* for ascending order data */
        //c[i] = i;

        /* for descending order */
        //c[i] = -i;

        /* for random order */
        //c[i] = (int)(Math.random()*1000);

        d[i] = c[i];
        e[i] = c[i];
    }

    // sort and print first array

    start_time = System.currentTimeMillis();
    insertionSort (c);
    end_time = System.currentTimeMillis();
    out.println ("Result of Insertion Sort:");
    //for (int i = 0; i < size; i++) 
    //    out.print(c[i] + "\t");
    out.println();

    out.println ("start:   " + start_time);
    out.println ("end:     " + end_time);
    out.println ("elapsed: " + (end_time - start_time));


    // sort and print second array
    start_time = System.currentTimeMillis();
    quicksort (d);
    end_time = System.currentTimeMillis();
  
    out.println ("Result of Quicksort:");
    //for (int i = 0; i < size; i++) 
     //   out.print(d[i] + "\t");
    out.println();

    out.println ("start:   " + start_time);
    out.println ("end:     " + end_time);
    out.println ("elapsed: " + (end_time - start_time));


    // sort and print third array
     start_time = System.currentTimeMillis();
    refQuicksort (e);
    end_time = System.currentTimeMillis();
    out.println ("Result of Refined Quicksort:");
    //for (int i = 0; i < size; i++) 
    //    out.print(e[i] + "\t");
    out.println();

    out.println ("start:   " + start_time);
    out.println ("end:     " + end_time);
    out.println ("elapsed: " + (end_time - start_time));

} // main
} // SortShell4