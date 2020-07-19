/*    File Name: ValidationException.java
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
 * - Algonquin College (2016). CST8132 Lab Assignment 3 - To Do List UML Diagram. [UML Visual Diagram]
 * - Algonquin College (2016). CST8132 Lab Assignment 3 - To Do List UML Diagram. [Task Class Description]
 * - Algonquin College (2016). CST8132 Lab Assignment 3 - To Do List UML Diagram. [Code Provided]
 * - Algonquin College (2016). Exception Handling [Lecture – PDF Format]
 */

/**
 *This is a custom exception class which extends the Exception class. Meaning it has to meet the programming
* requirements of checked exceptions.
* 
 * @author Nathan M. Abbey
 *
 *@version 1.0
 */
public class ValidationException extends Exception{


	/**
	 * This constructor sets the default message to display if the program encounters an error.
	 * 
	 * @since 1.0
	 */
	public ValidationException(){
		super("There was a problem when validating data");
	}


	/**
	 * This method handles a message.
	 * @param message A error message set by the "parent" class.
	 */
	public ValidationException(String message){
		super(message);
	}


	/**
	 * This method handles a message and a throwable.
	 * @param message A error message set by the "parent" class.
	 * @param throwable Throws to the "parent" class throwable.
	 */
	public ValidationException(String message, Throwable throwable){
		super(message, throwable);
	}


	/** 
	 * This method handles just a throwable.
	 * @param throwable Throws to the "parent" class throwable.
	 */
	public ValidationException(Throwable throwable){
		super(throwable);
	}
}
