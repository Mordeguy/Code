import java.util.Scanner;

/***********************************************************************************
 *   Class: ChequingAccount 
 * Purpose: This class will contain processing for the Chequing Account objects for
 *          the Bank Simulator (a dynamically allocated array) - inherited from BankAccount 
 *  Author: Linda Crane 
 *  Course: CST8130 - Data Structures Data members: fee: double - the amount of the monthly fee
 * Methods: toString(): String - returns the data of the account formatted to
 * display addBankAccount(): boolean - prompts user to enter data for this
 * object from keyboard - edits data, and doesn't allower user to continue with
 * bad data monthlyUpdate() - processes the object with monthly update of
 * withdrawing the fee (as long as bank balance is more than fee, else displays
 * error message readFile(Scanner): boolean - uses Scanner object parameter to
 * fill object with data- returns false if bad data is encountered, else returns
 * true
 *************************************************************************************/

public class ChequingAccount extends BankAccount {
	private double fee;

	// *****************AddBankAcount**************************************************
	public boolean addBankAccount() {
		super.addBankAccount();

		Scanner keyboard = new Scanner(System.in);
		boolean dataOk = false;

		// read valid monthly fee from user
		while (!dataOk) {
			System.out.print("Enter monthly fee: ");
			if (keyboard.hasNextDouble()) {
				fee = keyboard.nextDouble();
				if (fee >= 0.00)
					dataOk = true;
			} else {
				System.out.println("Invalid monthly fee...must be a positive number");
				String badData = new String();
				badData = keyboard.next();
			}
		}
		return true;
	}

	// *******************************toString***************************************
	public String toString() {
		return super.toString() + "\tMonthly Fee: $" + fee;
	}

	// *******************************monthlyUpdate**********************************
	public void monthlyUpdate() {
		System.out.print("Deducting fee of $" + fee + " from account " + accountNumber);
		if (fee > balance)
			System.out.println("......Insufficient funds for fee withdrawal");
		else {
			balance -= fee;
			System.out.println(".  New balance is $ " + balance);
		}
	}

	// *********************************readFile**************************************
	public boolean readFile(Scanner bankFile) {

		boolean isOk = super.readFile(bankFile);
		if (!isOk)
			return false;

		if (bankFile.hasNextDouble()) {
			fee = bankFile.nextDouble();
			if (fee < 0) { // invalid data so exist
				System.out.println("Invalid fee in Chequing account in file");
				return false;
			}
		} else { // invalid data so exist
			System.out.println("Invalid fee in Chequing account in file");
			return false;
		}

		return true;
	}

}
