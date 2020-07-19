import java.util.Scanner;

/***********************************************************************************
 * Class: SavingsAccount Purpose: This class will contain processing for the
 * Savings Account objects for the Bank Simulator (a dynamically allocated
 * array) - inherited from BankAccount Author: Linda Crane Course: CST8130 -
 * Data Structures Data members: interestRate: double - the percentage amount of
 * interest rate minBalance: double - the monthly balance user must have to get
 * interest Methods: toString(): String - returns the data of the account
 * formatted to display addBankAccount(): boolean - prompts user to enter data
 * for this object from keyboard - edits data, and doesn't allower user to
 * continue with bad data monthlyUpdate() - processes the object with monthly
 * update of adding interest (as long as bank balance is more than minBalance,
 * else displays error message readFile(Scanner): boolean - uses Scanner object
 * parameter to fill object with data- returns false if bad data is encountered,
 * else returns true
 *************************************************************************************/

public class SavingsAccount extends BankAccount {
	private double minBalance;
	private double interestRate;

	// ******************addBankAccount*************************************************
	public boolean addBankAccount() {
		super.addBankAccount();

		Scanner keyboard = new Scanner(System.in);
		boolean dataOk = false;

		// read valid monthly minimum balance from user
		while (!dataOk) {
			System.out.print("Enter monthly minimum balance: ");
			if (keyboard.hasNextDouble()) {
				minBalance = keyboard.nextDouble();
				if (minBalance >= 0.00)
					dataOk = true;
			} else {
				System.out.println("Invalid monthly minimum balance...must be a positive number");
				String badData = new String();
				badData = keyboard.next();
			}
		}

		dataOk = false;
		// read valid monthly interest rate from user
		while (!dataOk) {
			System.out.print("Enter monthly interest rate: ");
			if (keyboard.hasNextDouble()) {
				interestRate = keyboard.nextDouble();
				if (interestRate >= 0.00)
					dataOk = true;
			} else {
				System.out.println("Invalid monthly interest rate...must be a positive number");
				String badData = new String();
				badData = keyboard.next();
			}
		}

		return true;
	}

	// ******************toString*************************************************
	public String toString() {
		return super.toString() + "\tMinimum Balance: $" + minBalance + "\tInterest Rate: $" + interestRate;
	}

	// ******************monthlyUpdate*************************************************
	public void monthlyUpdate() {
		System.out.print("Adding interest of " + interestRate + "% to account " + accountNumber);
		if (minBalance > balance)
			System.out.println("......Insufficient funds for interest addition");
		else {
			balance += interestRate * balance;
			System.out.println(".  New balance is $ " + balance);
		}
	}

	// ******************readFile*************************************************
	public boolean readFile(Scanner bankFile) {

		boolean isOk = super.readFile(bankFile);
		if (!isOk)
			return false;

		if (bankFile.hasNextDouble()) {
			interestRate = bankFile.nextDouble();
			if (interestRate < 0) { // invalid data so exist
				System.out.println("Invalid interest rate in Savings account in file");
				return false;
			}
		} else { // invalid data so exist
			System.out.println("Invalid interest rate in Savings account in file");
			return false;
		}

		if (bankFile.hasNextDouble()) {
			minBalance = bankFile.nextDouble();
			if (minBalance < 0) { // invalid data so exist
				System.out.println("Invalid minimum balance in Savings account in file");
				return false;
			}
		} else { // invalid data so exist
			System.out.println("Invalid minimum balance in Savings account in file");
			return false;
		}

		return true;
	}

}
