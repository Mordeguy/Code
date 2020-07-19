import java.util.InputMismatchException;
import java.util.Scanner;
/***********************************************************************************
 *    Class: Main.java
 *  Purpose: This class contains the menu the user uses to navigate the program. It 
 *           ensures all inputs are valid and gives appropriate error messages when 
 *           its not.
 *            
 *   Author: Nathan M. Abbey  
 *   Course: CST8130 - Data Structures 
 *  Subject: Assignment 4 - Dictionary TreeMap
 *     Date: December 6, 2016 
 *     
 *Global Variables: 
 *     
 *          dict - Dictionary - A Dictionary.java class object 
 *        choice - int - An integer the user uses for navigating the main menu
 *     		 scn - Scanner - Scanner object used to accept input from user. 
 *      
 *Methods: 
 *      
 *  1.   main() - This method presents a menu to the user and offers choices of
 *                manipulating a TreeMap of words and their frequency. The menu
 *                directs users to appropriate methods of the Dictionary class
 *                based on selection.
 *                
 *************************************************************************************/
public class Main {

	public static void main(String[] args) {

		Dictionary dict = new Dictionary();
		int choice = 0;
		Scanner scn;

		do {

			scn = new Scanner(System.in);

			try {
				System.out.println("Welcome to Assignment 4 TreeMap Dictionary!\n");
				System.out.println("Please choose a valid selection from the menu below.");
				System.out.println("----------------------------------------------------|\n");
				System.out.println("  1.   Add from Keyboard to Dictionary");
				System.out.println("  2.   Add from File to Dictionary");
				System.out.println("  3.   Search for Word and Frequency");
				System.out.println("  4.   Number of Nodes in the Dictionary");
				System.out.println("  5.   Display TreeMap");
				System.out.println("  6.   Reset the Tree to Empty");
				System.out.println("  7.   Quit");
				System.out.println("\n----------------------------------------------------|");

				System.out.print("\nChoice--> ");
				choice = scn.nextInt();

				switch (choice) {

				case 1:
					System.out.println("\n--- INPUT FROM KEYBOARD ---");
					dict.addAtKeyboard();
					break;

				case 2:
					System.out.println("\n--- INPUT FROM FILE ---");
					dict.addFromFile();
					break;

				case 3:
					System.out.println("\n--- WORD SEARCH ---");
					dict.searchForWord();
					break;

				case 4:
					System.out.println("\n--- NUMBER OF NODES ---");
					System.out.println("\nRESULT: The dictionary has " + dict.countNodes() + " node(s).\n\n");
					break;

				case 5:
					System.out.println("\n--- DISPLAY TREEMAP ---\n");
					dict.displayTreeMap();
					break;

				case 6:
					System.out.println("--- CLEAR DICTIONARY ---\n");
					dict.clearDictionary();
					break;

				case 7:
					System.out.println("\n--- QUIT ---\n\n");
					System.out.println("Goodbye!!");
					System.out.println("Thank-you for using this program!");
					break;

				default:
					System.out.println("\n***ERROR: Please enter a valid integer between 1-5.***\n\n");
					scn.nextLine();
					break;

				}  


			} catch (InputMismatchException ex) {
				System.out.println("\n***ERROR: Selection must be numeric.***\n\n");
				scn.nextLine();
			}

		}while (choice != 7);
		scn.close();
	}

} // End of Main.java Class ******************************