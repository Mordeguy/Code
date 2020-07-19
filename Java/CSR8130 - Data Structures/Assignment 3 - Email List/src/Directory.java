import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/***********************************************************************************
 *    Class: Directory.java
 *  Purpose: This class will hold and manage ArrayLists of EmailList objects.
 *           Including the ability to create objects, add objects, display objects, 
 *           delete objects, and load objects from file into an EmailList.
 *           
 *   Author: Nathan M. Abbey  
 *   Course: CST8130 - Data Structures 
 *  Subject: Assignment 3 - ArrayLists of EmailList Linked Lists
 *     Date: November 8, 2016 
 *     
 *Global Variables: 
 *     
 *     emailList - ArrayList<EmailList> - An ArrayList of EmailList objects 
 *           scn - Scanner - object used to obtain user input
 *     		list - EmailList - Temporary EmailList used to set initial paramteers to an EmailList. 
 *      
 *Methods: 
 *      
 *  1.   createEmailList() - Prompts user for the name of new ArrayList of EmailList objects
 *    			             and sets it; while prompting for an e-mail address using addToList() 
 *    					     method. 
 *  2.         addToList() - Displays message and prompts user to input an e-mail address, and sets
 *           			     it if appropriate. 
 *  3.    deleteFromList() - Prompts user for name of list to delete from. If found, user is prompted
 *         			         for the index number of the list to delete from. If user deletes the last
 *      				     object and the list is empty, the list is deleted completely.      
 *  4.       displayList() - This will display a single list on screen based on user input.
 *  5	        loadFile() - 
 *  6.  toString(): String - Returns a string representation of the class.
 *    
 *************************************************************************************/
public class Directory {

	// Global Variables ------------------------------------------------------|
	private ArrayList <EmailList> emailList = new ArrayList<EmailList>();
	private Scanner scn = new Scanner(System.in);
	private EmailList list = null;


	// 1 ---------------------------------------------------------------------|
	public void createEmailList() {
		list = new EmailList();
		list.setName();	
		list.addEmailAddress();
		emailList.add(list);
	}


	// 2 ---------------------------------------------------------------------|
	public void addToList() {

		String name;
		System.out.print("Enter the name of the list to add to: ");
		name = scn.nextLine();

		if (emailList.isEmpty()){
			System.out.println("\nERROR: There are no lists to look though.\n");
		}
		for (int i = 0; i < emailList.size(); i++) {
			if (emailList.get(i).returnName().equals(name)) {				
				emailList.get(i).addEmailAddress();		
			} else {
		
			}
		}
	}


	// 3 ----------------------------------------------------------------------| 
	public void deleteFromList(){
	

		if (list == null) {
			System.out.println("\nERROR: There are no entires to delete from.\n");
			return;
		}
		String name;
		System.out.print("Enter the name of the list to delete from: ");
		name = scn.nextLine();
		
		
		for (int i =0; i< emailList.size(); i++) {
			
			if (emailList.get(i).returnName().equals(name)) {
				System.out.print( "Enter entry number to delete: ");
				emailList.get(i).deleteAddress(scn.nextInt());
				
				if (emailList.get(i).isEmpty()){						
					emailList.remove(i);
				}
				scn.nextLine();
			} else {
				
				
			}
		}
	}



	// 4 --------------------------------------------------------------------|
	public void displayList(){

		String name;
		System.out.print("\nEnter the name of the list to display: ");
		name = scn.nextLine();

		if (emailList.isEmpty()){
			System.out.println("\nERROR: The email list is empty; there is nothing to display.\n");
		}
		for (int i =0; i< emailList.size(); i++) {
			if (emailList.get(i).returnName().equals(name)) {
				System.out.println("\n" + emailList.get(i).toString() + "\n");
			} 
		}
	}


	// 5 --------------------------------------------------------------------|
	public void loadFile() {

		String name;
		int size, i;
		Scanner fileIn = null;
		list = new EmailList();
		i = 0;

		System.out.print("Enter the name of the file to load: ");
		name = scn.nextLine();
		File file = new File(name);

		try {
			if (file.exists()) {
				fileIn = new Scanner(file);
				size = fileIn.nextInt();
				
				while(i < size){
					list = new EmailList();
					list.loadFile(fileIn);
					emailList.add(i, list);
					i++;
				}
			} else {
				System.out.println("\nERROR: Input not recognized as a file.\n");
			}
		} catch (IOException e) {
			System.out.println("Could not open file...." + name + "exiting");
		}		
	}


	
	// 6 -------------------------------------------------------------------|
	public String toString() {
		
		return emailList.toString();
	}


} // End of Directory Class *************************************************/