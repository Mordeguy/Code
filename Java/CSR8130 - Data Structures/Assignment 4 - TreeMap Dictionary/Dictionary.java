import java.util.TreeMap;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
/***********************************************************************************
 *    Class: Dictionary.java
 *  Purpose: This class contains a TreeMap object and allows the user to manipulate
 *           the TreeMap by adding, searching, displaying, counting, and removing 
 *           values from it.
 *            
 *   Author: Nathan M. Abbey  
 *   Course: CST8130 - Data Structures 
 *  Subject: Assignment 4 - Dictionary TreeMap
 *     Date: December 6, 2016 
 *     
 *Global Variables: 
 *     
 *          tMap - TreeMap - A TreeMap which stores and keeps tracks of words input 
 *                 and their frequency 
 *     		 scn - Scanner - Scanner object used to accept input from user via the
 *                           keyboard and via a file. 
 *      
 *Methods: 
 *      
 *  1.     addAtKeyboard() - Allows user to add to the TreeMap via keyboard input.
 *  2.       addFromFile() - Allows user to add to the TreeMap via a file.
 *  3.       count nodes() - This method counts the number of nodes currently in the 
 *  					     TreeMap and returns and INT to the class calling it for 
 *  					     display purposes.
 *  4.   clearDictionary() - This method empties the TreeMap of all it's nodes.
 *  5.    displayTreeMap() - Displays all entries and frequency in TreeMap; this is
 *                           handy for debugging purposes.
 *  6.     searchForWord() - Searches the TreeMap for a specific word and returns 
 *  						 the word if its there and the number of times it is
 *                           in the TreeMap.
 *                        
 *************************************************************************************/
public class Dictionary {

	private TreeMap <String, Integer> tMap = new TreeMap <String, Integer>();
	private Scanner scn = new Scanner(System.in);


	// 1 --------------------------------------------------------------------------------|
	public void addAtKeyboard() {

		String input, word = null;
		String data[];
		int frequency;

		System.out.print("\nInput: ");
		input = scn.nextLine().toLowerCase();
		if (input != "\t"){
			data = input.split(" ");

			for (int i = 0; i < data.length; i++) {

				if (data[i] != " ")
					word = data[i];

				if (tMap.get(word) == null){
					tMap.put(word, 1);					
				} else {
					frequency = tMap.get(word).intValue();
					frequency++;
					tMap.put(word, frequency);
				}
			}
		}
		if (!tMap.isEmpty()){
		System.out.println("\nInput Successfully Added to the Dictionary!\n\n");
		} else {
			System.out.println("\nERROR: Input not added; please try again!\n\n");
		}
	}




	// 2 --------------------------------------------------------------------------------|
	public void addFromFile(){

		String name, word;
		int frequency;

		System.out.print("\nEnter the name of the file to load: ");
		name = scn.nextLine();

		File f = new File(name);

		try {
			if (f.exists()) {
				scn = new Scanner(f);

				while (scn.hasNext()){

					word = scn.next().toLowerCase();

					if (tMap.get(word) == null){
						tMap.put(word, 1);

					} else {

						frequency = tMap.get(word).intValue();
						frequency++;
						tMap.put(word, frequency);
					}
				}
				System.out.println("\nInput Successfully Added to the Dictionary!\n\n");
			} else {
				System.out.println("\n***ERROR: Input not recognized as a file***\n\n");
			}

		} catch (IOException e) {

			System.out.println("Could not open file...." + name + "exiting");
		}		
	}





	// 3 --------------------------------------------------------------------------------|
	public void clearDictionary() {
		if (tMap.isEmpty()){
			System.out.println("Dictionary is already empty!\n\n");
		} else {
			tMap.clear();
			System.out.println("Dictionary succesfully cleared!\n\n");
		}
	}




	// 4 -------------------------------------------------------------------------------|
	public int countNodes() {
		return tMap.size();
	}




	//5 --------------------------------------------------------------------------------|
	public void displayTreeMap() {

		System.out.println(tMap);
		System.out.println("\n");
	}




	//6 --------------------------------------------------------------------------------|
	public void searchForWord() {
		scn = new Scanner(System.in);

		String word;
		int frequency;

		if (tMap.isEmpty()){
			System.out.println("\n***ERROR: There is nothing in the TreeMap to look through!***\n\n");
			return;
		}

		System.out.print("\nWord to Search for: ");
		word = scn.next().toLowerCase();

		System.out.print("\n\nRESULT: ");

		if (tMap.containsKey(word)){

			frequency = tMap.get(word).intValue();

			System.out.println(" The word '" + word + "' was found " + frequency + " time(s).\n\n");
		} else {

			System.out.println(" The word '" + word + "' was not found!\n\n");

		}

	}



} // End of Dictionary Class ******************************