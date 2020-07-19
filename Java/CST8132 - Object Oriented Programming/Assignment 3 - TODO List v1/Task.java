/*    File Name: Task.java
 *  Course Name: Object-Oriented Programming CST8132
 *  Lab Section: 301
 * Student Name: Nathan M. Abbey
 *         Date: April 1, 2016
 *       Author: Stanley Pieda (2015) Personal Communication
 *   Updated By: Nathan M. Abbey
 */
package task;
/*
 * This class works to create the task from the inputs entered in the ToDoListManager class.
 * It sets the name, priority and isComplete boolean and ensures the inputs are valid.
 * It also creates a string representation of the class.
 * 
 * References For Class:
 * - Algonquin College (2016) CST8132 Lab Assignment 3 - To Do List UML Diagram. [UML Visual Diagram]
 * - Algonquin College (2016) CST8132 Lab Assignment 3 - To Do List UML Diagram. [Task Class Description]
 * - Algonquin College (2016) CST8132 Lab Assignment 3 - To Do List UML Diagram. [Code Provided]
 */
public class Task {
	
	private String title;
	private String priority;
	private boolean isComplete;
	
	
	// Constructor setting title and priority ---------------------------------------------------||
	public Task(){
		title = "No title";
		priority = "No priority";
	}
	
	
	
	// Constructor that sets title and priority to data inputed --------------------------------||
	public Task(String title, String priority) throws ValidationException{
		
		setTitle(title);
		setPriority(priority);
	}
	
	
	
	
	// Get Title Method: Returns the title ----------------------------------------------------||
	public String getTitle(){
		return title;
	}
	
	
	
	
	// Set Title Method: Ensures titles is within parameters and sets it ----------------------|| 
	public void setTitle(String title) throws ValidationException{

		if (title == null || title.isEmpty() || title.trim().length() <= 0) 

			throw new ValidationException ("\nThere was a problem adding a task:\nThe title cannot be empty\nPlease try again.\n");

		else if (title.length() > 25)

			throw new ValidationException ("\nThere was a problem adding a task:\nTitle cannot exceed 25 characters\nPlease try again.\n");

		else 

			this. title = title.trim(); 
	}
	
	
	
	
	// Get Priority Method: Returns the priority inputed -------------------------------------||
	public String getPriority(){
		return priority;
	}
	
	
	
	// Set Priority Method: Sets the priority and ensures certain parameters are met ---------||
	public void setPriority(String priority) throws ValidationException{
		
		if (priority == null || priority.isEmpty()) 

			throw new ValidationException ("\nThere was a problem adding a task:\nPriority cannot be empty\nPlease try again.\n");
		
		else if (!priority.equalsIgnoreCase("high") && !priority.equalsIgnoreCase("medium") && !priority.equalsIgnoreCase("low") )
			
			throw new ValidationException ("\nThere was a problem adding a task:\nPriority must be high, medium, or low\nPlease try again.\n");
		
		this. priority = priority.trim();
	}
	
	
	
	
	// Get isComplete Method: Returns true or false in regards to a task being complete ------||
	public boolean getIsComplete(){
		return isComplete;
	}
	
	
	
	// Set isComplete Method: Sets the isComplete to either true or false --------------------||
	public void setIsComplete(boolean isComplete){
		this.isComplete = isComplete;
	}
	
	
	
	// toString Method: Returns a string representation of the class -------------------------||
	@Override
	public String toString(){
		
		String status = " ";
		
		StringBuilder sb = new StringBuilder();

		
		if (getIsComplete() == false)
		sb.append(title + " (" + priority + ") is not complete" );
		
		else 
			
			sb.append(title + " (" + priority + ") is complete" );
		
		return sb.toString();
	}
	
	
} // End of Task Class