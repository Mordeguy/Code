/*    File Name: ToDoListLauncher.java
 *  Course Name: Object-Oriented Programming CST8132
 *  Lab Section: 301
 * Student Name: Nathan M. Abbey
 *         Date: April 22, 2016
 *       Author: Stanley Pieda (2015) Personal Communication
 *   Updated By: Nathan M. Abbey
 */
package task;
/*
 * References For Class:
 * - Algonquin College (2016) CST8132 Lab Assignment 3 - To Do List UML Diagram. [UML Visual Diagram]
 * - Algonquin College (2016) CST8132 Lab Assignment 3 - To Do List UML Diagram. [Task Class Description]
 * - Algonquin College (2016) CST8132 Lab Assignment 3 - To Do List UML Diagram. [Code Provided]
 */


/**
 * This is the driver class for the To Do List program. It initializes a ToDoListManager object and
 * then calls on the class's runtoDoList method.
 * 
 * @author Nathan M. Abbey
 * 
 * @version 1.0
 *
 */


 public class ToDoListLauncher {

	public static void main(String[] args){
		
		
		(new ToDoListManager()).runToDoList();
	}
	
	
} // End of ToDoListLauncher Class