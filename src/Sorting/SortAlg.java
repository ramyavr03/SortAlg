package Sorting;
import java.util.Arrays;
import  Sorting. RandomNumbers;
import  Sorting.RefObject;
import Sorting.ConsoleInput;


public class SortAlg
{
	public static final int MAX_SIZE = 10000000;
	
    public static int recursion_depth=0;
    

	public static int leftr=0;

	public static int rightr=0;
	
	

	// Set this to true if you wish the arrays to be printed.
	public static final boolean OUTPUT_DATA = false;
/********************************************************************/
	public static  String sortAlg = new String();
	public static void main(String [] args)
	{
		int size=0;
		
	    RefObject<Integer> temp_size = new RefObject<Integer>(size);
		ReadInput(temp_size);
		size = temp_size.argValue;

		int[] data = new int[size];
		if (data == null)
		{
			System.out.print("\n Memory allocation error.");
			System.out.print("\n");
			System.exit(1);
		}
		
		RefObject<String> sortedData = new RefObject<String>("Sorted Data");
		RefObject<String> NearlySortedData = new RefObject<String>("Nearly Sorted Data");
		RefObject<String> ReverselySortedData = new RefObject<String>("Reversely Sorted Data");
		RefObject<String> RandomData = new RefObject<String>("Random Data");

		GenerateSortedData(data, size);
		Sort(data, size, sortAlg,sortedData);
		
		GenerateNearlySortedData(data, size);
		Sort(data, size, sortAlg,NearlySortedData);
		
		GenerateReverselySortedData(data, size);
		Sort(data, size, sortAlg,ReverselySortedData);
		
		GenerateRandomData(data, size);
		Sort(data, size, sortAlg,RandomData);
		
		System.out.print("\nProgram Completed Successfully.");
		System.out.print("\n");

		data = null;

		
	}

    private static void ReadInput(RefObject<Integer> size)
	{
		System.out.print("  I:\tInsertion Sort");
		System.out.print("\n");
		System.out.print("  M:\tMergeSort");
		System.out.print("\n");
		System.out.print("  Q:\tQuickSort");
		System.out.print("\n");
		System.out.print("  S:\tSTLSort");
		System.out.print("\n");
		System.out.print("Enter sorting algorithm: ");
		sortAlg = ConsoleInput.readToWhiteSpace(true);
		String sortAlgName=null;


		if (sortAlg.equals("I"))
		{
			sortAlgName = "Insertion Sort";
		}
		else if (sortAlg.equals("M"))
		{
			sortAlgName = "MergeSort";
		}
		else if (sortAlg.equals("Q"))
		{
			sortAlgName = "QuickSort";
		}
		else if (sortAlg.equals("S"))
		{
			sortAlgName = "STLSort";
		}
		else
		{
			System.out.print("\nUnrecognized sorting algorithm Code: ");
			System.out.print(sortAlg);
			System.out.print("\n");
			System.exit(1);
		}

		System.out.print("Enter input size: ");
		size.argValue = Integer.parseInt(ConsoleInput.readToWhiteSpace(true));

		System.out.print("\nSorting Algorithm: ");
		System.out.print(sortAlgName);
		System.out.print("\nInput Size = ");
		System.out.print(size.argValue);
		System.out.print("\n");
		System.out.print("\n");

	}
/******************************************************************************/
    private static void GenerateSortedData(int[] data, int size)
	{
		int i;

		for (i = 0; i < size; i++)
		{
			data[i] = i * 3 + 5;
		}
	}
/*****************************************************************************/

    private static void GenerateNearlySortedData(int[] data, int size)
	{
		int i;

		GenerateSortedData(data, size);

		for (i = 0; i < size; i++)
		{
			if (i % 10 == 0)
			{
				if (i + 1 < size)
				{
					data[i] = data[i + 1] + 7;
				}
			}
		}
	}
/*****************************************************************************/



	private static void GenerateReverselySortedData(int[] data, int size)
	{
		int i;

		for (i = 0; i < size; i++)
		{
			data[i] = (size - i) * 2 + 3;
		}
	}
/*****************************************************************************/



	private static void GenerateRandomData(int[] data, int size)
	{
		int i;

		for (i = 0; i < size; i++)
		{
			data[i] = RandomNumbers.nextNumber();
		}
	}
/*****************************************************************************/



	private static void Sort(int[] data, int size, String sortAlg, RefObject<String> dataType)
	{

		System.out.print("\n");
		System.out.print(dataType.argValue);
		System.out.print(":");


		if (OUTPUT_DATA)
		{
			printData(data, size, "Data before sorting:");
		}

        // Sorting is about to begin ... start the timer!
		Long  startTime = System.nanoTime();
		//long startTime = System.currentTimeMillis();


		if (sortAlg.equals("I"))
		{
			InsertionSort(data, size);
		}
		else if (sortAlg.equals("M"))
		{
			MergeSort(data, 0, size-1);
		}
		else if (sortAlg.equals("Q"))
		{
			QuickSort(data, 0, size-1);
			
			System.out.println("recursion depth "+recursion_depth);
			
		}
		
		else if (sortAlg.equals("S"))
		{
			STLSort(data, size);
		}
		else
		{
			System.out.print("Invalid sorting algorithm!");
			System.out.print("\n");
			System.exit(1);
		}

		// Sorting has finished ... stop the timer!
		Long elapsed = (System.nanoTime()-startTime)/1000000;
		


		if (OUTPUT_DATA)
		{
			printData(data, size, "Data after sorting:");
		}


		if (IsSorted(data, size))
		{
			System.out.print("\nCorrectly sorted ");
			System.out.print(size);
			System.out.print(" elements in ");
			System.out.print(elapsed);
			System.out.print("ms");
		}
		else
		{
			System.out.print("ERROR!: INCORRECT SORTING!");
			System.out.print("\n");
		}
		System.out.print("\n-------------------------------------------------------------\n");
	}
/*****************************************************************************/

    private static void InsertionSort(int[] data, int size)
	{
		//Write your code here
		for(int i=1;i<data.length;i++)
		{
			int value=data[i];
			int index=i;
			while(index>0 && data[index-1]>value)
			{
				data[index]=data[index-1];
				index=index-1;
			}
			data[index]=value;
		}
		

	}
/*****************************************************************************/
	
    private static void MergeSort(int[] data, int low, int high)
	{
		//Write your code here
		//You may create other functions if needed
		if(low<high)
		{   int mid=(high+low)/2;
			MergeSort(data,low,mid);
			MergeSort(data,mid+1,high);
			Merge(data,low,high,mid);
	    }
     }
	private static void Merge(int[] data, int low, int high, int mid) 
	{
			// TODO Auto-generated method stub
		int large;
		int n1= mid-low+1, n2=high-mid;
		int i,j;
		int[] L=new int[n1];
		int[] R=new int[n2];
		
		
		
		for( i=0;i<n1;i++){
			L[i]=data[low+i];
			
		}
		for( i =0;i<n2;i++){
			R[i]=data[mid+1+i];
			
		}
		large=Math.max(L[n1-1], R[n2-1]);
		L[n1-1]=large;
		R[n2-1]=large;
		
		int k=low;
		
		for( i=0,j=0;i<n1 && j<n2;k++){
			if(L[i]<=R[j]){
				data[k]=L[i];
				i++;
				
			}
			else{
				
				data[k]=R[j];
				j++;
			}
			
		}
		while(i<n1){
			
			data[k]=L[i];
			i++;
			k++;
		}
		while(j<n2){
			data[k]=R[j];
			j++;
			k++;
			
		}
	}

		 
		/*****************************************************************************/

	
	public static  int QuickSort(int[] data, int low, int high)
	{
		//Write your code here
		//You may create other functions if needed
		if(low >= high)
			return 0;
		
		/*Calling Insertion Sort in Quicksort when the array size is less than 30*/
		
		/* if(data.length<30)
		 {   System.out.println(data.length);
			 InsertionSort(data,data.length);
		 }*/
		 
	      int q= partition(data,low,high);
	     //int q=MedianPartition(data,low,high);
		   // int q=Median_ExtraPartition(data,low,high);
		    leftr= QuickSort(data, low, q-1);	
		    rightr=  QuickSort(data, q+1, high);
	        recursion_depth = Math.max(leftr,rightr);
		    
		return recursion_depth+1;
	}
	
	
	public static int partition(int[] data,int low,int high)
	{
		
		int limit = (high-low) +1;
		int rand = (int) ((Math.random()* limit)+low);
		swap(rand,high,data);
		int j=low-1;
		for(int i=low; i<=high;i++)
		{
			if(data[i]< data[high])
					{
					swap(i,j+1,data);
					j++;
					}
		
			
		}
		swap(high,j+1,data);
		return j+1;
		}
	/*****************************************************************************/
		

	public static int MedianPartition(int[] data, int low, int high)
	{
	
	int[] random= new int[3];
	SortAlg q = new SortAlg();
	for(int a=0; a<3; a++)
	{
		random[a]=(int) q.randomRange(low,high);
	}
	int median;
	median= Math.max (Math.min (random[0],random[1]), Math.min (Math.max(random[0],random[1]),random[2]));
	swap(median,high,data);
	int j=low-1;
	for(int i=low; i<high;i++)
	{
		if(data[i]< data[high])
				{
				swap(i,j+1,data);
				j++;
				}
	
	}
	swap(high,j+1,data);
	return j+1;
	}
	

	int randomRange(int min, int max)
	{
	   int limit = (max - min) + 1;     
	   return (int)(Math.random() * limit) + min;
	}
	
	public static int Median_ExtraPartition(int[] data,int low,int high)
	{
		int median=Median3(data, low,high);
		int j=low-1;
		swap(median,high,data);
		for(int i=low; i<high;i++)
		{
			if(data[i]< data[high])
					{
					swap(i,j+1,data);
					j++;
					}
		
		}
		swap(high,j+1,data);
		return j+1;
		}
		
	
	public static int Median3(int [] data,int low,int high)
	{
		int mid = (low + high)/2;
			    if (data[high] < data[low])
			        swap(low,high,data) ;       
			    if (data[mid] < data[low])
			        swap( mid, low,data);
			    if (data[high] < data[mid])
			        swap(high, mid,data);
			    return mid;
	}
	 
			
	
/*****************************************************************************/
  


	private static void STLSort(int[] data, int size)
	{
		//Write your code here
		//Your code should simply call the STL sorting function
		Arrays.sort(data);
	}
/*****************************************************************************/

    private static void swap(int i, int j, int[] arr) 
    {
		  int t = arr[i];
		  arr[i] = arr[j];
		  arr[j] = t;
	}


	
/*****************************************************************************/





	private static boolean IsSorted(int[] data, int size)
	{
		int i;

		for (i = 0; i < (size-1); i++)
		{
			if (data[i] > data[i + 1])
			{
				return false;
			}
		}
		return true;
	}
/*****************************************************************************/




	private static void printData(int[] data, int size, String title)
	{
		int i;

		System.out.print("\n");
		System.out.print(title);
		System.out.print("\n");
		for (i = 0; i < size; i++)
		{
			System.out.print(data[i]);
			System.out.print(" ");
			if (i % 10 == 9 && size > 10)
			{
				System.out.print("\n");
			}
		}
	}


	}