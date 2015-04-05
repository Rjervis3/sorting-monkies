package index; 

import java.util.Arrays;

class Partition2 {

	/** 
	 * A method to partition the array around a pivot element. The
	 * array will be divided into 2 portions, R and L, where all elements
	 * in R are greater that the pivot and all elements in L are less than the
	 * pivot.
	 * @param a array to be partitioned
	 * @param left first index to be considered, location of partition element
	 * @param right last element to be considered
	 * @return final index of the partition element 
	 */
	public static int partition (int a[], int left, int right){

		int r_spot, l_spot, temp;
		/* start searching right to left at rightmost element */
		r_spot = right;  
		/* start searching right to left at next index after partition element*/
		l_spot = left + 1;  
		if (left == right) /* if theres only one element, it must be the one we want */
			return left;

		/* continue until the last execttion moves r_spot past l_spot */
		while( r_spot >= l_spot)  
		{  
			/* reset temporary variable */
			temp = 0;

			/* process R, move until find element that belongs in L */
			while (r_spot >= l_spot && a[r_spot] > a[left]){
				--r_spot;
			}
			/* process L, move until find element that belongs in R */	
			while( l_spot <= r_spot && a[l_spot] <= a[left]){
				++l_spot;
			}

			/* once 2 elements are found, swap them */
			if(r_spot > l_spot){
				temp =a[r_spot];
				a[r_spot] = a[l_spot];
				a[l_spot] = temp;
			}
		} //while

		//final swap partition into right spot
		temp = a[left];
		a[left] = a[r_spot];
		a[r_spot]=temp;

		return r_spot;
	}// partition

	/** Method to return the kth smallest element in an array
	 * @param a the array to search
	 * @param n number of elements in array
	 * @param k order of the element we wish to find
	 * @return kth smallest element in array
	 */
	public static int select (int a[], int n, int k)
	{
		return selection(a, n, k, 0, n-1);
	}

	/** a method to select the kth largest leemnt in the array
	 * @param a array to be searched
	 * @param n number element in array
	 * @param k order of element to find (kth smallest)
	 * @param left index of first element to consider
	 * @param right index last element to consider
	 * @return kth smallest element in array
	 */
	public static int selection (int a[], int n, int k, int left, int right)
	{
		if(n==1) // if one element, return that element
		{
			return a[left];
		}
		int middle = partition(a, left, right);
		//System.out.println("middle= "+middle);
		//int larger = n- middle-1; //number elements in R
		if(k == ((middle) - left +1))
		{
			return a[middle];
		}
		if (k <= ((middle) - left)) //look on left side
		{
			//System.out.println("left");
			return selection(a, n, k, left, middle-1);
		}
		else if (k >= ((middle) - left)) //look on right side
		{
			//System.out.println("right");
			int newK = (k-(middle-left+1));
			return selection(a, n, newK, middle+1, right); //???
		}
		else
		{
			return a[middle];
		}	

	}
	
	/** A method to find the element that is the median value in an array.
	 *  Note that we round the middle index up by adding 1
	 * @param a array to search
	 * @param n number of elements in array
	 * @return median element in array
	 */
	public static int median (int a[], int n)
	{
		return select(a, n, n/2 +1);
	}
	
	/** Quicksort is a sorting algorithm that utilizes the
	 * partition function to recursively sort portions of the
	 * array.
	 * @param a is array to be sorted
	 * @param n number of elements in array
	 */
	public static void quicksort (int a[], int n){
		quicksortKernel(a, 0, n-1);
	}
	/* a is array to be sorted, first is index of partition element
	 * last is index of last element to be processed */
	public static void quicksortKernel(int a[], int first, int last)
	{   
		int partition = partition(a, first, last);
		int right = partition+1; //index first element right side
		int left = partition -1; //index last element on left side

		//printAr(a, first, last);

		if (first < left) //left side
		{
			quicksortKernel (a, first , left);
		}
		if (right < last) //right side
		{
			quicksortKernel (a, right, last);  	
		}

	}

	/*----------------- Helper functions -----------*/

	public static void printAr(int a[], int start, int end){
		System.out.print("[ ");
		for(int i = start; i<=end; i++){
			System.out.printf("%d ", a[i]);
		}
		System.out.println("]");
	} //printAr

	/*----------------- End Helper functions -----------*/
	

	/* --------------- Test Functions ------------------------ */

	/* Since our partition uses the first index as partition, this function
       swaps each successive element into the first position and tests the
       effectiveness of the partition algorithm on the whole array each time */
	public static void testPartition(int arr[])
	{
		int size = arr.length;
		System.out.print("Original array: ");
		printAr(arr, 0, size-1);

		int i, temp;
		for(i=0; i<size; i++)
		{
			int arr2[]=Arrays.copyOfRange(arr, 0, size);


			System.out.print("Partition Element: " + arr2[i] );
			//swap partition el to beginning of array
			temp = arr2[i];
			arr2[i] = arr2[0];
			arr2[0]=temp;
			int index = partition(arr2, 0, size-1);
			System.out.print("    Partitioned Array: ");
			printAr(arr2, 0, size-1);

		}
		System.out.print("\n");
	}//testPartition

	/* test function for quicksort */
	public static void testQuicksort(int arr[]){
		System.out.print("Original array: ");
		printAr(arr, 0, arr.length-1);
		quicksort(arr, arr.length);
		System.out.print("Sorted Array:   ");
		printAr(arr, 0, arr.length-1);
		System.out.print("\n");

	}
	/* test function for select*/
	public static void testSelect(int arr[]){
		int k;
		System.out.print("Original array: ");
		printAr(arr, 0, arr.length-1);
		for(k=1; k<arr.length+1;k++)
		{
			int select =select(arr, arr.length, k);
			printAr(arr, 0, arr.length-1);
			switch (k)
			{
			case 1:
				System.out.println("The " +k+ "st smallest element is " + select);
				break;
			case 2:
				System.out.println("The " +k+ "nd smallest element is " + select);
				break;
			case 3:
				System.out.println("The " +k+ "rd smallest element is " + select);
				break;
			default:
				System.out.println("The " +k+ "th smallest element is " + select);
			}//switch
		}
		System.out.print("\n");
	} // testSelect
	
	/* test function for median */
	public static void testMedian(int arr[]) {
		System.out.print("Original array: ");
		printAr(arr, 0, arr.length-1);
		int med = median(arr, arr.length);
		quicksort(arr, arr.length);
		System.out.println("Median returns: " + med);
		System.out.println("Sorted array for checking: ");
		printAr(arr, 0, arr.length-1);
		System.out.println("\n");
	}

	/* --------------- End Test Functions ------------------------ */

	public static void main(String args[])
	{
		int test[] = {1, 5, 8, 9, 33, 5, 1, 2, 7};
		int test2[]= {1, 2, 5,0};
		int test3[]={5, 6, 4, 0, 22};
		int arr[] = {6, 0, 5, 7, 5, 1, 10, 77};
		/* Testing for partition */	
		/*
	      testPartition(test);
	      testPartition(test2);
	      testPartition(test3);
	      testPartition(arr);
	    /* 

    	/* Testing for kth smallest (select) function*/	

		/*
	      testSelect(test);
	      testSelect(test2);
	      testSelect(test3);
	      testSelect(arr);
		 */

		/* Testing for median function */
		
		/*
		testMedian(test);
		testMedian(test2);
		testMedian(test3);
		testMedian(arr);
		 */
		
		/* Testing for quicksort */	
		int asc[] = {1, 2, 3, 4, 5};
		int des[] = {5, 4, 3, 2, 1};
		/*
	      testQuicksort(test);
	      testQuicksort(test2);
	      testQuicksort(test3);
	      testQuicksort(arr);

	      System.out.println("Ascending array:");
	      testQuicksort(asc);
	      System.out.println("Descending array:");   
		  testQuicksort(des);
		 */ 
	}//main


}
