package sorting;

import java.io.*;

public class SortShell1 {
// a sorting shell for experiments with the insertion and selection sorts

public static void insertionSort (int [] a) {
// method to sort using the insertion sort
   for (int k = 1; k < a.length; k++) {
      int item = a[k];
      int i = k-1;
      while ((i >= 0) && a[i] < item){
         a[i+1] = a[i];
         i--;
      }
      a[i+1] = item;
   }
} // insertionSort

public static void main (String [] args) 
    throws Exception {

    // declarations: two arrays and I/O;
    int size = 12;
    int [] c = new int [size];
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
    }

    // sort and print first array
    insertionSort (c);
    out.println ("Result of Insertion Sort:");
    for (int i = 0; i < size; i++) 
        out.print(c[i] + "\t");
    out.println();

} // main

} // SortShell1 