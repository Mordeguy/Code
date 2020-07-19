/* File Name: Assign2.java
 * Course Name: Object Oriented Programming - CST8132 
 * Lab Section: 301
 * Student Name: Nathan M. Abbey
 * Date: March 7, 2016
 */
package Assign2;
/* 
 * This is the "driver" class for the employee tracker. It contains a main method which calls the menu
 * object from the Company class to run the program.
 */
public class Assign2 {

	public static void main(String[] args) {

		Company cmpny = new Company();
		
		cmpny.menu();
	}

} // End of Assign2 Class