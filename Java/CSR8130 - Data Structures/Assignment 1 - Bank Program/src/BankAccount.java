/*    File Name: BankAccount.java
 *  Course Name: CST8130 - Data Structures 
 *  Lab Section: 301
 * Student Name: Nathan M. Abbey
 *         Date: September 27, 2016
 *         
 * This class creates Bank Accounts which store a valid account number,  owner's full name, and the account balance.
 * 
 * 
 * Class Variables:
 * 
 *   int accountNumber -> Accepts integers and sets as the account number
 *   String firstName  -> A blank string which holds the account owner's first name
 *   String lastName   -> A blank string which holds the account owner's last name
 *   double balance    -> A double that holds the account balance; 2 decimal place precision
 *   Scanner scn       -> Accepts user input from keyboard
 *
 * Class Methods:
 *   
 *   BankAccount()           -> Default constructor; creates a bank Account object
 *   addBankAccount()        -> Prompts user for valid account number, first and last name, and balance; and sets the values
 *   isEqual(int i)          -> Compares input to account numbers looking for quality; if found returns true
 *   updateBalance()         -> Updates the balance based on user input
 *   monthlyUpdate;          -> Abstract class that updates the balance using the sub classes
 *   toString()              -> Returns a string representation of the class
 *   readFile(String [] str) -> Assigns the passed in array values to the appropriate class variable
 */
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * This class creates Bank Accounts which store the account number, the owner's full name, and the account
 * balance. 
 *
 * @author Nathan M. Abbey;
 * 
 * @version 1.0
 */
public abstract class BankAccount {

	protected int accountNumber;
	protected String firstName;
	protected String lastName;
	protected double balance;
	Scanner scn = new Scanner(System.in);


	/**
	 * Default constructor
	 * 
	 * @since 1.0
	 */
	public BankAccount(){
	}



	/**
	 * This method asks for the generic bank account values and stores them; including an account number, first and last name, 
	 * and account balance. The input data is error checked and will not continue until good values are entered.
	 * 
	 * @since 1.0
	 */
	public void addBankAccount(){

		/* moveOn: a boolean initially set to TRUE to allow user to move on to the next piece of code. Unless the user inputs bad
		 *         data and it is set to FALSE and the code repeats the process.
		 *  */
		boolean moveOn;

		do {

			moveOn = true;


			// Entering account number and ensuring data is correct; if not this code repeats.
			do {
				moveOn = true;

				try {	
					System.out.print("Enter Account Number: ");
					accountNumber = scn.nextInt();
				} 
				catch (InputMismatchException ex)
				{
					System.out.println("\nERROR: Invalid account number entered\n");
					scn.nextLine();
					moveOn = false;
				}
			}while (moveOn == false);

			// Prompts user for first and last name. If either is empty (spaces,  enter) program returns error and repeats code segment.
			if (moveOn == true) {

				scn.nextLine();

				do {
					System.out.print("Enter Customer's First Name: ");
					firstName = scn.nextLine();

					System.out.print("Enter Customer's Last Name: ");
					lastName = scn.nextLine();

					if (firstName.isEmpty() == true || firstName.trim().isEmpty() == true || lastName.isEmpty() == true || lastName.trim().isEmpty() == true){

						System.out.println("\nERROR: Name field(s) cannot be empty.\n");
					}
				}while (firstName.trim().isEmpty() == true || lastName.trim().isEmpty() == true);
			}

			// Prompts for account balance and only accepts a proper value. If incorrect the code segment repeats.
			if (moveOn == true) {

				do{
					try{
						System.out.print("Enter Balance: ");
						balance = scn.nextFloat();
						moveOn = false;
						scn.nextLine();

					} catch (InputMismatchException ex)
					{
						System.out.println("\n** ERROR: Invalid account balance entered **\n");
						moveOn = true;
						scn.nextLine();
					} 
				}while (moveOn == true);
			}
		}while (moveOn != false);
	} 





	/**
	 * This method feeds in a String array and ensures the class variables are set and valid.
	 * 
	 * @since 1.0
	 */
	public boolean readFile(String[] str){

		if (str[1].length() == 0 || str[1].length() > 8){

			return false;

		} else if (str[3].isEmpty() || str[2].isEmpty()){

			return false;

		} else if (balance < 0) {

			return false;

		} else {

			try {
				accountNumber = Integer.parseInt(str[1]);
				balance = Double.parseDouble(str[4]);
				lastName = str[3];
				firstName = str[2];

			} catch (NumberFormatException ex) 
			{
				System.out.println("\n**ERROR: Problem with file; cannot upload.");
			}
			return true;
		}
	} 



	/**
	 * This method accepts a value and compares it to existing account numbers; if it's found program returns true.
	 * 
	 * @since 1.0
	 */
	public boolean isEqual(int i){

		if (i == accountNumber) {
			return true;
		} 
		else{
			return false;
		}
	}



	/**
	 * This method updates the balance of the account
	 * 
	 * @since 1.0
	 */
	public void updateBalance(double a){

		balance = a;
	}



	/**
	 * This abstract method updates the account using the sub class based on the type of account.
	 * 
	 * @since 1.0
	 */
	public abstract void monthlyUpdate();




	/**
	 * 
	 * This method returns a string representation of the class. 
	 * 
	 * @return String
	 * 
	 * @since 1.0
	 */
	@Override
	public String toString(){

		StringBuilder sb = new StringBuilder();

		String st = String.format("\nAccount: %d   %s %s   Balance: $%.2f  ",  accountNumber, firstName, lastName, balance );

		sb.append(st);

		return sb.toString();
	}



} // End of BankAccount class