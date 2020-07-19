import java.util.Scanner;

/***********************************************************************************
 *    Class: BankAccount 
 *  Purpose: This class will contain the base objects for the Bank Simulator 
 *           (a dynamically allocated array) 
 *   Author: Linda Crane 
 *  Updated: Nathan M. Abbey
 *   Course: CST8130 - Data Structures 
 *     Date: October 18 2016
 *  
 *Data members: 
 * 
 *  accountNumber: int - holds number associated with the account 
 *      firstName: String - for first name of customer
 *       lastName: String - for last name of customer 
 *        balance: double - holds the amount of money in the account 
 * 
 *Methods: 
 *
 *             toString(): String - returns the data of the account formatted to display 
 *      addBankAccount(): boolean - prompts user to enter data for this object from 
 *                                  keyboard - edits data, and doesn't allow user to continue 
 *                                  with bad data 
 *         isEqual (int): boolean - compares int parameter to account number in object and 
 *                                  returns true/false appropriately
 *    isGreater (double): boolean - compares the double parameter to the balance in
 *                                  object and returns true/false appropriately 
 *         updateBalance (double) - updates the balance in the object by the parameter amount 
 *                monthlyUpdate() - processes the object with monthly update (empty for base class) 
 *     readFile(Scanner): boolean - uses Scanner object parameter to fill object with data- returns
 *                                  false if bad data is encountered, else returns true 
 *                                  
 *Newly Added Methods:
 *                                  
 *    check(BankAccount): boolean - takes a BankAccount as a parameter and determines if it's account 
 *                                  number is bigger or smaller than the object's account number. If 
 *                                  bigger, return true; if not, return false. 
 *compareAccountNum(int): boolean - takes in an account number and determines if it's bigger or smaller 
 *									than the object's account number. If bigger returns true; if not 
 * 									returns false. 
 *           equal (int): boolean - determines if the account number parameter passed in is equal to
 * 									the object's account number. Returns true if so; returns false if not.
 *************************************************************************************/

public class BankAccount {
	protected int accountNumber;
	protected String firstName = new String();
	protected String lastName = new String();
	protected double balance;

	public BankAccount() {

	}

	public BankAccount(int accountNumber, String firstName, String lastName, double balance) {
		this.accountNumber = accountNumber;
		this.firstName = new String(firstName);
		this.lastName = new String(lastName);
		this.balance = balance;
	}

	// ********************toString**********************************************************
	public String toString() {
		String print = "Account: " + accountNumber + "\t" + firstName + " " + lastName + "\tBalance: $" + balance;
		return print;
	}

	// *******************addBankAccount***********************************************
	public boolean addBankAccount() {
		Scanner keyboard = new Scanner(System.in);
		boolean dataOk = false;

		// read valid accountNumber from user
		while (!dataOk) {
			System.out.print("Enter account number: ");
			if (keyboard.hasNextInt()) {
				accountNumber = keyboard.nextInt();
				if (accountNumber < 100000000)
					dataOk = true;
			} else {
				System.out.println("Invalid account number...must be 9 digits or less");
				String badData = new String();
				badData = keyboard.next();
			}
		}

		System.out.print("Enter customer first name: ");
		firstName = keyboard.next();

		System.out.print("Enter customer last name: ");
		lastName = keyboard.next();

		// read valid balance
		dataOk = false;
		while (!dataOk) {
			System.out.print("Enter balance: ");
			if (keyboard.hasNextDouble()) {
				balance = keyboard.nextDouble();
				if (balance >= 0.00)
					dataOk = true;
			} else {
				System.out.println("Invalid balance...must be a positive number");
				String badData = new String();
				badData = keyboard.next();
			}
		}

		return true;
	}
	
	

	// ******************isEqual***************************************************
	public boolean isEqual(int accountToFind) {
		return accountNumber == accountToFind;
	}

	// *****************isGreater***************************************************
	public boolean isGreater(double amount) {
		return balance + amount >= 0.0;
	}

	// ********************updateBalance********************************************
	public void updateBalance(double amount) {
		balance += amount;
	}

	// ********************monthlyUpdate**********************************************
	public void monthlyUpdate() {
	}

	// *******************readFile****************************************************
	public boolean readFile(Scanner bankFile) {

		if (bankFile.hasNextInt()) {
			accountNumber = bankFile.nextInt();
			if (accountNumber >= 100000000) { // invalid data so exist
				System.out.println("Invalid account number in file");
				return false;
			}
		} else { // invalid data so exist
			System.out.println("Invalid account number in file");
			return false;
		}

		firstName = bankFile.next();
		lastName = bankFile.next();

		if (bankFile.hasNextDouble()) {
			balance = bankFile.nextDouble();
		} else { // invalid data so exist
			System.out.println("Invalid balance in file");
			return false;
		}

		return true;
	}

	// *******************check*********************************
	public boolean check(BankAccount a) {

		if (a.accountNumber > accountNumber) {

			return true;

		} else {

			return false;
		}

	}

	// *******************compareAccountNum*********************************
	public boolean compareAccountNum(int a) {

		if (a > accountNumber) {

			return true;
		} else {
			return false;
		}
	}

	// *******************equal*********************************
	public boolean equal(int a) {

		if (a == accountNumber) {

			return true;

		} else {

			return false;
		}

	}

}
