import java.util.LinkedList;
import java.util.Scanner;
/***********************************************************************************
 *    Class: EmalList.java
 *  Purpose: This class handles an EmailList Linked List by setting it's name, and 
 *           adding and removing from it. 
 *           
 *   Author: Nathan M. Abbey  
 *   Course: CST8130 - Data Structures 
 *  Subject: Assignment 3 - ArrayLists of EmailList Linked Lists
 *     Date: November 8, 2016 
 *     
 *Global Variables: 
 *     
 *       numInList - int - Keeps count of the number of entries in the LinkedList
 *            name - String - Stores the name of the EmailList object
 *     addressList - EmailAddress - a LinkedList of EmailAddress objects
 *             scn - Scanner - object used to obtain user input
 *              eA - EmailAddress - A temporary EmailAddress object used to create 
 *                   new EmaillAdress objects.
 *      
 *Methods: 
 *      
 *  1.            setName() - Prompts user for name of the EmailList and sets it.
 *  2.         returnName() - Returns the name of the EmailList.
 *  3.    addEmailAddress() - Adds an EmailAddress object to the LinkedList addressList
 *  4. loadFile(Scanner in) - Takes the scanner with the file in it and writes the data to
 *  						  a list called 'Loaded' if it is appropriately written.
 *  5.      deleteAddress() - Prompts user for name of list to delete from and deletes at
 *  			  		      specified index.
 *  6.            isEmpty(): boolean - Returns true if LinkedList is empty; false if otherwise. 
 *  7.           toString(): String - Returns a string representation of the class.
 *  
 *Reference:
 * 
 *      EmailAddress.java
 *              - This java file was provided by Linda Crane for use in this assignment.
 *    
 *************************************************************************************/

public class EmailList {

	// Global Variables ------------------------------------------------------------|
	private int numInList;
	private String name;
	private LinkedList <EmailAddress> addressList = new LinkedList<EmailAddress>();
	private Scanner scn = new Scanner (System.in);
	private EmailAddress eA = null;



	// 1 ---------------------------------------------------------------------------|
	public void setName() {	
		System.out.print("Please enter the name of the list: ");
		name = scn.nextLine();
		System.out.println();	
	}


	// 2 ---------------------------------------------------------------------------|
	public String returnName(){
		return name;	
	}


	// 3 ---------------------------------------------------------------------------|
	public void addEmailAddress() {
		eA = new EmailAddress();
		eA.addAddress(scn, "yes");
		addressList.add(numInList, eA);
		numInList++; 
	}



	// 4 ---------------------------------------------------------------------------|
	public void loadFile(Scanner in) {
		
		int listSize, i = 0;
		String name;
		
			name = in.next();
			this.name = name;
			listSize = in.nextInt();
			i =0;
				
			while (i < listSize) {	
				
				eA = new EmailAddress();
				eA.addAddress(in, "no");
				addressList.add(numInList,eA);
				numInList++;
				i++;
			}
	}
	


	// 5 --------------------------------------------------------------------------|
	public void deleteAddress(int index) {
		addressList.remove(index);
		numInList--;
	}



	// 6 --------------------------------------------------------------------------|
	public boolean isEmpty() {
		if (addressList.isEmpty()){
			return true;
		} else {
			return false;
		}
	}



	// 7 -------------------------------------------------------------------------|
	public String toString() {
		
		return name + ": " + addressList.toString() + "\n";
	}


	
} // End of EmailList Class ******************************************************/