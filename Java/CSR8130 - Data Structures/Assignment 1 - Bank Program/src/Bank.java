/*    File Name: Bank.java
 *  Course Name: CST8130 - Data Structures 
 *  Lab Section: 301
 * Student Name: Nathan M. Abbey
 *         Date: September 27, 2016
 *         
 * This class interacts with the user, presenting a menu to navigate. Checking for all valid inputs. It also 
 * initializes the array of Bank Accounts.
 *         
 *          
 * Class Variables:
 * 
 *   int MAX_SIZE -> Sets the final max size for the array
 *   BankAccount bnkA -> Creates an object of BankAccount class
 *   int numAccount -> Keeps track of number of accounts
 *  
 * Class Methods:
 * 
 *   Bank()           -> Default constructor; prints out welcome and initialize 1000 accounts
 *   Bank(int a)      -> Initial constructor; which sets array length to number inputed
 *   runMenu()        -> Displays menu and jumps to appropriate method based on verified input
 *   addBankAccount() -> Asks user if they want to create a Savings or Checkings account and makes it
 *   toFind()         -> Loops through the accounts looking for equality and prints it out
 *   monthlyUpdate()  -> Loops through the array and updates them based on account type
 *   update()         -> Asks for account number and updates balance based on user input
 *   toString()       -> Creates a string representation of the class
 *   readFile()       -> Opens a file and creates String array and passes it another class
 */ 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class interacts with the user, presenting a menu to navigate. Checking for all valid inputs. It also 
 * initializes the array of Bank Accounts.
 * 
 * @author Nathan M. Abbey;
 * 
 * @version 1.0
 */ 
public class Bank {

	private final int MAX_SIZE = 1000;
	private BankAccount [] bnkA;
	private int numAccount = 0;
	Scanner scn = new Scanner (System.in);


	/**
	 *Default constructor sets the initial array to 1000 and creates the objects.
	 * 
	 * @since 1.0
	 */
	public Bank(){
		System.out.println("Hello! Welcome to the computerized interactive banking system.\n");
		final int MAX_SIZE = 1000;
		bnkA = new BankAccount[MAX_SIZE];

		for (int i = 0; i < MAX_SIZE; i++){

			bnkA[i] = new SavingsAccount();
		}
	}

	
	/**
	 * Initial constructor sets the array size to the number inputed by user.
	 * 
	 * @since 1.0
	 */
	public Bank(int a){

		bnkA = new BankAccount[a];	
		for (int i = 0; i < a; i++){
			bnkA[i] = new SavingsAccount();
			numAccount++;
		}
	}

	
	
	/**
	 * This method displays the user menu and accepts user input via the Scanner method. If user inputs blank spaces they 
	 * will receive an error message. Choosing a char listed in the menu takes the program to the appropriate method to
	 * accomplish the task. Choice of chars not listed in menu will have menu re-print.  
	 * 
	 * @since 1.0
	 */
	public void runMenu(){
			
		char choice = ' ';
		boolean moveOn = true;
	
		do {	
			System.out.println("\nPlease enter an option from the menu below.");
			System.out.println ("-----------------------------------------------|");
			System.out.println ("a - Add a New Bank Account");
			System.out.println ("d - Display a Specific Account Details");
			System.out.println ("u - Update Balance of Specific Account");
			System.out.println ("m - Run Monthly Update on All Accounts");
			System.out.println ("f - Load Bank Account Info from File");
			System.out.println ("s - Save Bank Account Data to File");
			System.out.println ("q - Quit Program");
			System.out.println ("-----------------------------------------------|");

			try {

				System.out.print ("\nSelection --> ");
				choice = scn.nextLine().charAt(0);
			
				if (choice == ' ') {
					System.out.println("\nERROR: Selection cannot be blank.");	
				}
				
			switch (choice){

			case 'a' : case 'A':
				addBankAccount();
				break;
				
			case 'd' : case 'D':
				toFind();
				break;
			
			case 'u': case 'U':
				if (numAccount == 0){
					
					System.out.println("\n**ERROR: No Accounts to update.**");
				} else {
				update();
				}
				break;
				
			case 'm': case 'M':
				if (numAccount == 0){
					System.out.println("\n**ERROR: No Accounts to update.**");
				} else {
				monthlyUpdate();
				}
				break;

			case 'f': case 'F':
				readFile();
				break;

			case 'q' : case 'Q':

				System.out.println("\nThank-you for using this software. See you Next Time!\n");
			}
			} catch (IndexOutOfBoundsException ex)
			{
				System.out.println("\nERROR: Selection cannot be blank.");
				moveOn = false;
			}
		} while (choice != 'q' && choice != 'Q');
	}	





	
	
	/**
	 * This method asks for the account type and creates the object based on user input; savings or checkings.
	 * 
	 * @since 1.0
	 */
	public void addBankAccount(){

		char choice;
		boolean moveOn = false;

		if (numAccount <= MAX_SIZE ){

			System.out.println("\n-- ADD ACCOUNT --");
			
			do {
				
			System.out.println("\nPlease choose a type of account listed below.");
			System.out.println("\ns for SAVINGS");
			System.out.println("c for CHECKING\n");
			System.out.print("Selection --> ");
			
			choice = scn.nextLine().charAt(0);
			
			if (choice == 's' || choice == 'S'){

				bnkA[numAccount] = new SavingsAccount();
				bnkA[numAccount].addBankAccount();
				numAccount++;
				moveOn = true;
			}
			if (choice == 'c' || choice == 'C'){

				bnkA[numAccount] = new CheckingAccount();
				bnkA[numAccount].addBankAccount();
				numAccount++;
				moveOn = true;
			}
			} while (moveOn == false);
		} else {

			System.out.println("\n**ERROR: Cannot create bank account; database is full.**\n");
		}
	}

	
	
	
	/**
	 * This method asks for an account number and uses the isEqual method to compare the inputed number to the
	 * bank account numbers on file. Returns -1 if no match found.
	 * @since 1.0
	 */
	public int toFind() {

		int numSearch;
		boolean moveOn;

		System.out.print("\n--DISPLAY ACCOUNT--\n");

		try {
			System.out.print("Enter Account Number: ");

			numSearch = scn.nextInt();

			if (numAccount == 0) {

				System.out.println("\n**ERROR: There are no accounts to search through.**");
				scn.nextLine();
				moveOn = false;					
			}
			else {

				for (int i = 0 ; i < numAccount; i++){

					if (bnkA[i].isEqual(numSearch) == true){
						System.out.println(bnkA[i]);

						scn.nextLine();
						return i;
					} 
				}
			}
			scn.nextLine();
		}
		catch (InputMismatchException ex)
		{
			System.out.println("\nERROR: Incorrect account number format.");
			scn.nextLine();
			moveOn = false;
		}
		return -1;
	}

	
	
	/**
	 * This method loops through the accounts array and monthly updates them
	 * @since 1.0
	 */
	public void monthlyUpdate(){
		
		for (int i = 0; i <numAccount; i++) {
			
			bnkA[i].monthlyUpdate();													
		}
	}
	
	
	
	/**
	 * This method updates the balance based on user input; negative for withdrawal, positive for deposit.
	 * @since 1.0
	 */
	public String update(){

		int numSearch = 0;
		int update = 0;
		String str= " ";
		boolean moveOn = true;

		System.out.print("\n--UPDATE ACCOUNT--\n");

		do{
			moveOn = true;

			System.out.print("Enter Account Number: ");

			try {
				numSearch = scn.nextInt(); 

				for (int i = 0; i <= numAccount; i++){

					if (bnkA[i].isEqual(numSearch) == true){
						moveOn = true;
						i = numAccount;
					}
					else {
						moveOn = false;		
					}
				}
			} catch (InputMismatchException ex) 
			{	
				System.out.println("\n**ERROR: Did not input a valid account number.**\n");
				moveOn = false;
				scn.nextLine();
			} 
		} while ( moveOn == false);

		for (int i = 0 ; i <= numAccount; i++){

			if (bnkA[i].isEqual(numSearch) == true){
				
				do {
					moveOn = true;
				System.out.print("Enter amount to update (negative for withdrawal, positive for deposit): ");

				try {

					update = scn.nextInt();
					
					bnkA[i].updateBalance(bnkA[i].balance + update);
					i = numAccount;
					str = "Account Updated";
					scn.nextLine();
				} 
				catch (InputMismatchException ex) 
				{
					System.out.println("\n**ERROR: Did not input a valid account update.**\n");
					moveOn = false;
					scn.nextLine();
				}
			} while (moveOn == false);
			}
		}
		return str;
	}

	





	/**
	 * This method creates a string representation of the class
	 * @since 1.0
	 */
	public String toString(){

		StringBuilder sb = new StringBuilder(super.toString());

		String st = String.format(" ");
		sb.append(st);

		return sb.toString();

	}



	/**
	 * This method opens the file and adds the  type account based on user selection
	 * @since 1.0
	 */
	public String[] readFile(){

		String option;
		Scanner scn2 = null;
		FileReader fr = null;
		String [] strArray = new String[0];

		try {

			System.out.print("Enter the name of file to process: ");
			option = scn.nextLine();

			File f = new File (option);
			fr = new FileReader(f);
			scn2 = new Scanner(fr);

			while (scn2.hasNextLine()){

				strArray = scn2.nextLine().split(" ");

				if (strArray[0].equals("s") || strArray[0].equals("S")){

					bnkA[numAccount] = new SavingsAccount();
					bnkA[numAccount].readFile(strArray);
					numAccount++;
				}

				if (strArray[0].equals("c") || strArray[0].equals("C")){

					bnkA[numAccount] = new CheckingAccount();
					bnkA[numAccount].readFile(strArray);
					numAccount++;
				}
			}
		}	catch (FileNotFoundException e) {

			System.out.print("\n**ERROR - This file could not be found.**\n");
		} 

		return strArray;

	}
} // End of Bank Class