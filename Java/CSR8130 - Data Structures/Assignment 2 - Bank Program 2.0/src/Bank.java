import java.util.*;

import java.io.*;

/***********************************************************************************
 *    Class: Bank 
 *  Purpose: This class will contain the data structure for the Bank
 *           Simulator (a dynamically allocated array 
 *   Author: Linda Crane 
 *  Updated: Nathan M. Abbey 
 *   Course: CST8130 - Data Structures 
 *     Date: October 18, 2016 
 *     
 *Data members: 
 *     
 *     bankData - An ArrayList of BankAccount objects ( either a
 *                SavingsAccount or ChequingAccount object) 
 *  numAccounts - int - contains the current number of accounts in the array 
 *     sizeBank - int - contains the maximum size of accounts allocated to 
 *                      array
 *         bnkA - an object of the BankAccount class used to pass values into a 
 *                BankAccount type 
 *      
 *Methods: 
 *      
 *      default constructor () - allocates default size of 1000 to the ArrayList 
 *   initial constructor (int) - parameter is size of ArrayList to be allocated 
 *         addAccount: boolean - successful add or not; prompts user to enter data for 
 *                               an account which is added to the ArrayList from smallest 
 *                               account number to largest account number; using the sort 
 *                               method - either chequing or savings account is added
 * 						         if there is room 
 *          toString(): String - prompts user to enter an account number to display, then 
 *                               returns the data for that number if found else returns error
 * 						         message.
 *            update(): String - prompts user to enter which account number to update, and by 
 *                               how much and then updates the balance appropriately; returns
 *                               success message or error message if not successful 
 *               toFind(): int - prompts user to enter which account number they wish to find, 
 *                               and returns array index where it is found; using a binary search,
 *                               else returns -1. 
 *             monthlyUpdate() - processes through each current account in the array and updates the account -
 *    	   			             withdraws fee for chequing, and adds interest for savings
 *         readFile(): boolean - prompts user for name of file to process, opens that file, then reads
 * 						         through the file and adds accounts (from smallest to lowest account number)
 * 						         to the array if there is room - returns false if bad data is encountered,
 * 						         else returns true 
 *         openFile(): Scanner - returns the Scanner object if a file (name input by user) is opened, else 
 *                               returns null 
 *                               
 * Newly Added Methods:
 * 
 *                displayAll() - This method goes through the ArrayList and prints all of the BankAccount 
 *        				         objects on to the screen; all the info included. 
 *      binarySearch(int): int - Feeds the account number to search for in to the parameter and binary 
 *                               searches for the matching account number. Returns index if match; returns -1 if no match.
 *************************************************************************************/

public class Bank {

	private ArrayList<BankAccount> bankData;
	private int numAccounts;
	private int sizeBank;
	private BankAccount bnkA;

	// **************default constructor*****************************************************
	public Bank() {
		sizeBank = 1000;
		numAccounts = 0;
		bankData = new ArrayList<BankAccount>();
	}

	// **************initial constructor - parameter of size to allocate to array *********
	public Bank(int size) {
		sizeBank = size;
		numAccounts = 0;
		bankData = new ArrayList<BankAccount>(size);
	}

	// **************method addAccount *****************************************************
	public boolean addAccount() {

		Scanner keyboard = new Scanner(System.in);
		boolean moveOn = false;

		if (numAccounts >= sizeBank) {
			System.out.println("Cannot add to bank....full");
			return false;
		}
		System.out.print("Enter an s for Savings Account or c for Chequing Account: ");
		String choice = new String();
		choice = keyboard.next();
		choice = choice.toLowerCase();

		while (choice.charAt(0) != 'c' && choice.charAt(0) != 's') {
			System.out.println("Invalid entry...please enter s for Savings or c for Chequing: ");
			choice = keyboard.next();
			choice = choice.toLowerCase();
		}

		if (choice.charAt(0) == 'c') {
			bnkA = new ChequingAccount();
			bnkA.addBankAccount();
			bankData.add(bnkA);
			numAccounts++;

			sort();
			moveOn = true;

		} else {
			bnkA = new SavingsAccount();
			bnkA.addBankAccount();
			bankData.add(bnkA);
			numAccounts++;

			sort();
			moveOn = true;
		}

		if (moveOn == true) {

			return true;
		} else {
			System.out.println("Invalid data....account not added");
			return false;
		}
	}

	// *****************method toString *******************************
	public String toString() {

		int accountIndex = toFind();

		if (accountIndex == -1)
			return "Account Number cannot be found in database";
		else
			return bankData.get(accountIndex).toString();
	}

	// ***********method update******************************************************
	public String update() {
		int accountIndex = toFind();
		if (accountIndex == -1)
			return "Account Number cannot be found in database";

		Scanner keyboard = new Scanner(System.in);

		boolean dataOk = false;
		double updateAmount = 0;

		// read valid updateAmount from user
		while (!dataOk) {
			System.out.print("Enter amount to update (negative for withdrawal, positive for deposit): ");
			if (keyboard.hasNextDouble()) {
				updateAmount = keyboard.nextDouble();
				if (updateAmount < 0 && bankData.get(accountIndex).isGreater(updateAmount))
					dataOk = true;
				if (updateAmount > 0)
					dataOk = true;
			} else {
				System.out.println("Invalid updateAmount...reenter");
				String badData = new String();
				badData = keyboard.next();
			}
		}
		bankData.get(accountIndex).updateBalance(updateAmount);
		return "Account updated";
	}

	// *************************toFind**********************************************
	public int toFind() {
		Scanner keyboard = new Scanner(System.in);
		boolean dataOk = false;
		int accountToFind = 0;

		// read valid accountNumber from user
		while (!dataOk) {
			System.out.print("Enter account number to find: ");
			if (keyboard.hasNextInt()) {
				accountToFind = keyboard.nextInt();
				if (accountToFind < 100000000)
					dataOk = true;
			} else {
				System.out.println("Invalid account number...must be 9 digits or less");
				String badData = new String();
				badData = keyboard.next();
			}
		}
		return binarySearch(accountToFind);
	}

	// ***********************monthlyUpdate********************************************
	public void monthlyUpdate() {
		for (int i = 0; i < numAccounts; i++)
			bankData.get(i).monthlyUpdate();
	}

	// ********************readFile**************************************************
	public boolean readFile() {

		Scanner bankFile = openFile();

		if (bankFile == null) {
			return true;
		}

		String type = new String();
		BankAccount newOne = null;
		boolean isOK = true;

		while (bankFile.hasNext()) {

			type = bankFile.next();

			if (type.charAt(0) == 's')
				newOne = new SavingsAccount();

			else if (type.charAt(0) == 'c')
				newOne = new ChequingAccount();

			else // invalid data so end the program
				return false;

			isOK = newOne.readFile(bankFile);

			if (!isOK) // bad data in file so return
				return false;

			if (numAccounts >= sizeBank) {
				System.out.println("Cannot add to bank....full");
				return true;
			}
			bankData.add(newOne);
			numAccounts++;
			sort();
		}
		return true;
	}

	// ********************openFile*********************************
	public Scanner openFile() {

		Scanner keyboard = new Scanner(System.in);
		String fileName = new String();
		Scanner inFile = null;

		System.out.print("\n\nEnter name of file to process: ");
		fileName = keyboard.next();

		File file = new File(fileName);
		try {
			if (file.exists()) {
				inFile = new Scanner(file);
			}
			return inFile;
		} catch (IOException e) {
			System.out.println("Could not open file...." + fileName + "exiting");
			return null;
		}
	}// end openFile method

	// *****************************displayAll **********************************
	public void displayAll() {

		if (bankData.size() >= 1) {

			for (int i = 0; i < bankData.size(); i++) {

				System.out.println(bankData.get(i).toString());
			}
		} else {

			System.out.println("There are no BankAccounts to display.");
		}
	} // End of displayAll

	
	// *******************************sort***********************************
	public void sort() {
		BankAccount temp = new BankAccount();
		int j, i;

		if (numAccounts > 1) {
			for (i = 1; i < numAccounts; i++) {

				temp = bankData.get(i);

				for (j = i - 1; j >= 0 && temp.check(bankData.get(j)); j--) {

					bankData.set(j + 1, bankData.get(j));
				}
				bankData.set(j + 1, temp);
			}
		}
	} // End of Sort

	// *******************************binary
	// search************************************
	public int binarySearch(int a) {

		int min = 0;
		int mid;
		int max = bankData.size() - 1;

		while (max >= min) {

			mid = (min + max) / 2;

			if (bankData.get(mid).equal(a)) {
				return mid;
			}

			if (bankData.get(mid).compareAccountNum(a)) {

				min = mid + 1;

			} else {

				max = mid - 1;
			}
		}
		return -1;
	}// End of Binary Search

}
