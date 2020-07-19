/*    File Name: CheckingAccount.java
 *  Course Name: CST8130 - Data Structures 
 *  Lab Section: 301
 * Student Name: Nathan M. Abbey
 *         Date: September 27, 2016
 *
 * This class handles the checking account specific items and sets them if they are valid.
 * 
 * 
 * Class Variables:
 * 
 *   double fee -> This is the monthly fee charged to the account.
 *
 * Class Methods:
 * 
 *   addBankAccount() -> After using the super class it prompts and sets the monthly fee
 *   toString()       -> Creates a string representation of the class in conjunction with super class
 *   monthlyUpdate()  -> This minuses the fee from the balance and returns the rest
 *   readFile()       -> Opens the string Array from another class and sets valid values to variables
 *   
 */import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * This class extends the BankAccount class enabling user to input the monthly fee and stores the value.
 *
 * @author Nathan M. Abbey;
 * 
 * @version 1.0
 */
public class CheckingAccount extends BankAccount{


	private double fee;
	Scanner scn = new Scanner (System.in);



	/**
	 *  This method prompts for the monthly fee and verifies the input is correct before setting the variable.
	 *  @since 1.0
	 */
	public void addBankAccount(){

		/* moveOn: a boolean initially set to TRUE to allow user to move on to the next piece of code. 
		 *         Unless the user inputs bad data and it is set to FALSE and the code repeats the process.
		 *  */
		boolean moveOn;

		super.addBankAccount();

		do {
			moveOn = true;
			System.out.print("Enter monthly fee: ");

			try {

				fee = scn.nextDouble();
			}
			catch (InputMismatchException ex) 
			{
				System.out.println("ERROR: Incorrect fee format entered");
				moveOn = false;
				scn.nextLine();
			}
		} while (moveOn == false);
	}



	/**
	 * This method returns a string representation of the class.
	 * 
	 * @return sb.toString() - a string consisting of the account number, first and last name, balance, and monthly fee.
	 * 
	 * @since 1.0
	 */
	@Override
	public String toString(){

		StringBuilder sb = new StringBuilder(super.toString());

		String st = String.format("Monthly Fee: %.2f     Account Type: Checking", fee);
		sb.append(st);

		return sb.toString();
	}



	/**
	 *  This method minuses the monthly fee from the account balance.
	 *  @since 1.0
	 */
	public void monthlyUpdate(){

		super.balance -= fee;
	}

	
	
	/**
	 *  This method opens the file and adds account parameters based on the file
	 *  @since 1.0
	 */
	public boolean readFile(String[] str){
		
		 if (super.readFile(str));{
			 
		fee = Double.parseDouble(str[5]);
		
		return true;
		 }
	}
	
	
	
} // End of Checking Account