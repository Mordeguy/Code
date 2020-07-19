import java.util.Random;
import java.util.Scanner;


public class Merge {




	// Creates memory for an array of ints names num ------------------------------------||
	int num [];	
	int [] helper;
	int number;
	int high;




	// This default constructor ---------------------------------------------------------||
	public Merge(){

		num = new int[5];
	}




	// This initial constructor that initializes the array list w/ input ----------------||
	public Merge(int a){

		num = new int[a];
	}



	// This method generates random numbers to fill the array with ----------------------||
	public int[] generateNumbers(){

		Random rndm = new Random();

		try {

			for(int i = 0; i < num.length; i++){

				num[i] = (rndm.nextInt(100) + 1);
			}
			System.out.println("\nNumbers have been generated\n\n");

		}catch (NullPointerException ex) 
		{
			System.out.println("\n***ERROR: No array to generate numbers to.***\n");
		}

		return num;	
	}



	// This method counts the instances of a number user inputs -------------------------||
	public int count(int a){

		int count = 0;

		for (int i=0; i < num.length; i++){

			if (num[i] == a) {

				count++;
			}
		}
		return count;	
	}



	// Creates a string representation of the class -------------------------------------||
	public String toString(){

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < num.length ; i++) {
			String str = String.format("%d", num[i]);
			sb.append(num[i] + " ");
		}
		return sb.toString();
	}
	
	
	
	
	public void sort(int [] array) {
		
		// Here we create a blank helper array, and feed [0] and last to mergeSort.
		helper = new int[array.length];
		mergeSort (0, array.length-1);
	}
	
	
	
	public void mergeSort(int low, int high) {
		
		// Is the first position [0] lower than the last[4]? YES!
		if (low < high) {
			
			//  middle = 0   +   (4-0) /2 =  2
		      
			int middle = low + (high - low) /2;
			
			
			//         0      2
			//         0      1
			//         0      0
			mergeSort(low, middle);

			
			//               1      1 
			mergeSort(middle + 1, high);
			
			merge(low, middle, high);
		}
		
		
		
	}
                 //        0          0         1	
	public void merge(int low, int middle, int high) {
		  
		 //           0         1
 		for (int i = low; i <= high; i++){
			
 			// Grbs sub array [0], [1]
			helper [i] = num[i];
		}
		//        0
		int i = low;
		        //   1
		int j = middle + 1;
		      //  0
		int k = low;
		
		     // 0 <= 1        1 <= 1     
		while (i<= middle && j <= high) {
			
			if (helper[i] <= helper[j]) {
				
				num[k] = helper [i];
				i++;
			} else {
				
				num[k] = helper[j];
				j++;
			}
			k++;
			
		}
		
		while (i<=middle) {
			
			num[k] = helper[i];
			k++;
			i++;
		}
			
			
			
		
			}

			
		

		
		
	}


	


