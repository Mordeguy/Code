/*    File Name: ValidationException.java
 *  Course Name: Object-Oriented Programming CST8132
 *  Lab Section: 301
 * Student Name: Nathan M. Abbey
 *         Date: March 29, 2016
 *       Author: Stanley Pieda (2015) Personal Communication
 *   Updated By: Nathan M. Abbey
 */
package task;
/*
 * This is a custom exception class which extends the Exception class. Meaning it has to meet the programming
 * requirements of checked exceptions.
 * 
 * References For Class:
 * - Algonquin College (2016). CST8132 Lab Assignment 3 - To Do List UML Diagram. [UML Visual Diagram]
 * - Algonquin College (2016). CST8132 Lab Assignment 3 - To Do List UML Diagram. [Task Class Description]
 * - Algonquin College (2016). CST8132 Lab Assignment 3 - To Do List UML Diagram. [Code Provided]
 * - Algonquin College (2016). Exception Handling [Lecture – PDF Format]
 */
public class ValidationException extends Exception{


	public ValidationException(){
		super("There was a problem when validating data");
	}


	public ValidationException(String message){
		super(message);
	}


	public ValidationException(String message, Throwable throwable){
		super(message, throwable);
	}


	public ValidationException(Throwable throwable){
		super(throwable);
	}
}
