/*    File Name: Numbers.java
 *  Course Name: CST8130 - Data Structures 
 *  Lab Section: 301
 *      Project: Lab 2 - Searching and Sorting
 *       Author: Nathan M. Abbey
 *         Date: September 29, 2016
 *
 *
 * This class creates and handles a num array; list of numbers between 1-100.
 * 
 *  Class Variables:
 *    
 *     num[]  -> An array of ints
 *  
 *  Class Methods:
 *  
 *     Numbers()          -> Default constructor
 *     Numbers(int a)     -> Initial constructor that initializes the size of the array
 *     generateNumbers()  -> Generates random numbers (1-100) and populates the array
 *     count()            -> Counts through array for number user input; returns count
 *     toString()         -> Creates a string representation of the class
 */
import java.util.Arrays;


public class Numbers <T> {
	
	T data;


	
	// Creates memory for an array of ints names num ------------------------------------||
	T num [];	
	int index = 0;




	// This default constructor ---------------------------------------------------------||
	public Numbers(){
		
	
	}


	


	// This initial constructor that initializes the array list w/ input ----------------||
	@SuppressWarnings("unchecked")
	public Numbers(int a){

		num = (T[])new Object[a];
	}



	public void addNumber(T number){
		
		num[index] = number;
		index++;
			
	}





	public void sort() {
	
		
		Arrays.sort(num);

	} // End of Sort



	
	
	
	


	// This method counts the instances of a number user inputs -------------------------||
	public int count(T a){

		int count = 0;

		for (int i=0; i < num.length; i++){

			if (num[i] == a) {

				count++;
			}
		}
		return count;	
	}



	// Creates a string representation of the class -------------------------------------||
	public void display(){

		StringBuilder sb = new StringBuilder();
		System.out.print("\nArray -> ");
		for (int i = 0; i < num.length ; i++) {
		
			sb.append(num[i]);
			System.out.print("[" + i + "]" + num[i] + " ");
		}
		System.out.println("\n");
	}
	
	
	
	
} // End if Numbers class