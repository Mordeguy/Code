import java.util.*;

/***********************************************************************************
 * Class: Assign2 Purpose: The class is the driver class for Assignment 2 - Bank
 * Simulator - Fall 2016 Author: Linda Crane Course: CST8130 - Data Structures
 *************************************************************************************/

public class Assign1 {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		String choice = new String("a");
		char choiceFirstChar = choice.charAt(0);
		Bank bank = new Bank(1000);

		// menu processing
		while (choiceFirstChar != 'q') {
			System.out.println("\nEnter your choice:  a - add new account; d - display an account;");
			System.out.println("                    u - update balance on account;  m - run month end update;");
			System.out.println("                    f - enter info from file;  l - list all accounts;");
			System.out.print("                    q - quit:");
			choice = keyboard.next();

			choice = choice.toLowerCase();
			choiceFirstChar = choice.charAt(0);

			if (choiceFirstChar == 'a') {
				bank.addAccount();
			} else if (choiceFirstChar == 'd') {
				System.out.println(bank);
			} else if (choiceFirstChar == 'u') {
				System.out.println(bank.update());
			} else if (choiceFirstChar == 'm') {
				bank.monthlyUpdate();
			} else if (choiceFirstChar == 'f') {
				boolean isOK = bank.readFile();
				if (!isOK) // bad data from file so quit
					break;

			} else if (choiceFirstChar == 'l') {
				bank.displayAll();

			} else if (choiceFirstChar == 'q') {
				System.out.println("GOODBYE");
			} else
				System.out.println("Invalid choice...please reenter...");
		}

	}// end of main

}// end class
