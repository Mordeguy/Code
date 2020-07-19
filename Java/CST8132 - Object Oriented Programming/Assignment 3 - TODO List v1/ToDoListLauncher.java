/*    File Name: ToDoListLauncher.java
 *  Course Name: Object-Oriented Programming CST8132
 *  Lab Section: 301
 * Student Name: Nathan M. Abbey
 *         Date: April 1, 2016
 *       Author: Stanley Pieda (2015) Personal Communication
 *   Updated By: Nathan M. Abbey
 */
package task;
/*
 * This is the "runner" class for the TO DO list program. It initializes a ToDoListManager
 * object and then calls that class's runToDoList method.
 * 
 * References For Class:
 * - Algonquin College (2016) CST8132 Lab Assignment 3 - To Do List UML Diagram. [UML Visual Diagram]
 * - Algonquin College (2016) CST8132 Lab Assignment 3 - To Do List UML Diagram. [Task Class Description]
 * - Algonquin College (2016) CST8132 Lab Assignment 3 - To Do List UML Diagram. [Code Provided]
 * 
 */
 public class ToDoListLauncher {

	public static void main(String[] args) {
	
		(new ToDoListManager()).runToDoList();
	}
	
	
} // End of ToDoListLauncher Class