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
import java.util.Random;


public class Numbers {


	
	// Creates memory for an array of ints names num ------------------------------------||
	int num [];	




	// This default constructor ---------------------------------------------------------||
	public Numbers(){
		
		num = new int[5];
	}




	// This initial constructor that initializes the array list w/ input ----------------||
	public Numbers(int a){

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
	
	
	
	
} // End if Numbers class