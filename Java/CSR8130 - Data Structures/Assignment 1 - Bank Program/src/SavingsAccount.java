/*    File Name: SavingsAccount.java
 *  Course Name: Data Structures CST8130
 *  Lab Section: 301
 * Student Name: Nathan M. Abbey
 *         Date: September 27, 2016
 *         
 * This class handles the savings account specific items and sets them if they are valid.
 * 
 *  
 *  Class Variables:
 *     
 *   double interestRate -> Decimal number the balance gets multiplied by and that number added on to balance
 *   double minBalance   -> This is the minimum amount allowed in their savings account
 *   
 *  Class Methods:
 *  
 *   addBankAccount()       -> After using the super class it prompts and sets interest rate and minimum balance
 *   monthlyUpdate()        -> Updates super class by adding (balance * interest) to the balance
 *   readFile(String[] str) -> Takes the scanner array from another class and sets interest rate and minimum balance
 *   toString()             -> Creates a string representation of the class in conjunction with super class
 */
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class extends the BankAccount class enabling user to input interest rate and minimum balance and storing those values..
 *
 * @author Nathan M. Abbey;
 * 
 * @version 1.0
 */
public class SavingsAccount extends BankAccount {

	private double interestRate;
	private double minBalance;
	Scanner scn = new Scanner (System.in);


	/**
	 *  This method asks for minimum balance and interest rate;  and ensures they are valid before setting them accordingly.
	 *  @since 1.0
	 */
	public void addBankAccount() {


		/* moveOn: a boolean initially set to TRUE to allow user to move on to the next piece of code. 
		 *         Unless the user inputs bad data and it is set to FALSE and the code repeats the process.
		 *  */
		boolean moveOn = true;

		// Jumps initially to it's super class addBanAccount method in order to get the complete information.
		super.addBankAccount();


		// Prompts for minimum balance and only accepts proper format; repeats code segment if incorrect.
		do {
			moveOn = true;
			try {
				System.out.print ("Enter monthly minimum balance: ");
				minBalance = scn.nextDouble();
				scn.nextLine(); 	

			} catch (InputMismatchException ex) {

				System.out.println("\nERROR: Invalid minimum balance entered.\n");
				scn.nextLine(); 
				moveOn = false;
			}
		}while (moveOn == false);

		// Prompts the user for monthly interest rate and only accepts valid input and sets it.
		do {
			try {

				moveOn = true;

				System.out.print ("Enter Monthly Interest Rate: ");
				interestRate = scn.nextDouble();

			} catch (InputMismatchException ex) {

				System.out.println("\nERROR: Invalid interest rate entered.\n");
				scn.nextLine();
				moveOn = false;
			}
		}while (moveOn == false);	
	}




	/**
	 *  This method updates the super class (BankAccount.java) balance by adding the interest rate
	 *  set to the original balance.
	 *  @since 1.0
	 */
	public void monthlyUpdate(){

		double newAmount = super.balance;
		newAmount += (interestRate * super.balance);
		super.updateBalance(newAmount);
	}



	/**
	 *  This method opens the file and adds account(s) based on the file.
	 *  @since 1.0
	 */	
	public boolean readFile(String[] str){
		
		

		if (super.readFile(str)); {

		interestRate = Double.parseDouble(str[5]);
		minBalance = Double.parseDouble(str[6]);

		}

		return true;
		
	}


	/**
	 * This method returns a string representation of the class.
	 * 
	 * @return sb.toString() - a string consisting of the account number, first and last name, balance, minimum balance,
	 * and monthly interest rate.
	 * 
	 * @since 1.0
	 */
	@Override
	public String toString(){

		StringBuilder sb = new StringBuilder(super.toString());

		String st = String.format("Min Balance: $%.2f  Monthly Interest Rate: %.2f    Account Type: Savings", minBalance, interestRate);
		sb.append(st);

		return sb.toString();
	}

} // End of SavingsAccount class