import java.util.Scanner;
/***********************************************************************************
 *    Class: Main.java
 *  Purpose: This is the main "driver" method for this assignment. 
 *           
 *   Author: Nathan M. Abbey  
 *   Course: CST8130 - Data Structures 
 *  Subject: Assignment 3 - ArrayLists of EmailList Linked Lists
 *     Date: November 8, 2016 
 *     
 *Variables: 
 *     
 *           scn - Scanner - object used to obtain user input
 *     	  choice - char - a character used to navigate the selection in the menu interface 
 *            dr - Directory - a directory objects used to access that class's methods.
 *      
 *Methods: 
 *      
 *  1.    main() - Displays a menu and manipulates a Directory object based on user input.
 *    
 *************************************************************************************/
public class Main {

	// 1 ---------------------------------------------------------------------------|
	public static void main(String[] args) {

		Scanner scn = new Scanner(System.in);
		char choice = 'x';
		Directory dr = new Directory();

		do {

			System.out.println("Welcome to the virtual e-mail list index!\n");
			System.out.println("Please choose an option from the menu below.");
			System.out.println("-------------------------------------------------");
			System.out.println("c - Create a New List");
			System.out.println("p - Display All Email Lists");
			System.out.println("a - Add Entry to a List");
			System.out.println("d - Delete and Entry From a List");
			System.out.println("l - Display a List");
			System.out.println("f - Load List from File");
			System.out.println("q - to Quit");
			System.out.println("-------------------------------------------------");

			try {
				System.out.print("Choice --> ");
				choice = scn.nextLine().toLowerCase().charAt(0);

				switch(choice){

				case 'a':
					dr.addToList();
					break;

				case 'c':
					dr.createEmailList();
					break;

				case 'd':
					dr.deleteFromList();
					break;

				case 'l':
					dr.displayList();
					break;

				case 'p':
					
					System.out.println("\n" + dr.toString() + "\n");
					break;

				case 'f':
					dr.loadFile();
					break;

				case 'q':
					scn.close();
					System.out.println("\nThanks for using this program! Goodbye!");
					return;

				default:
					System.out.println("\nERROR: Please Choose a Valid Selection from the Menu.\n");
					break;
				}			
			} catch (StringIndexOutOfBoundsException ex){

				System.out.println("\nERROR: Selection cannot be empty.\n");
			}

		} while (choice != 'q');
	}

} // End of Main **********************************************************