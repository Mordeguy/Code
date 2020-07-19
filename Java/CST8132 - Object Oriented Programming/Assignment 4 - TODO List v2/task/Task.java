/*    File Name: Task.java
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
 * - Algonquin College (2016) CST8132 Lab Assignment 4 - To Do List v2 UML Diagram. [UML Visual Diagram]
 * - Algonquin College (2016) CST8132 Lab Assignment 4 - To Do List v2 UML Diagram. [Task Class Description]
 */


/**
 * This class works to creates a task from the inputs entered in the ToDoListManager class. It sets the name, priority and 
 * isComplete boolean of the Task, and ensures the inputs are valid. It also creates a string representation of the class.
 *
 * @author Nathan M. Abbey;
 * 
 * @version 1.0
 */
public class Task {
	
	private String title;
	private String priority;
	private boolean isComplete;
	
	
	
	
	/**
	 * This default constructor sets the title to "No Title" and the priority to "No Priority".
	 * @since 1.0
	 */
	public Task(){
		title = "No title";
		priority = "No priority";
	}
	
	
	
	
	/**
	* This constructor sets title and priority of the task after verifying appropriate input from user.
	* 
	* @param title name of the task
	* @param priority importance of task will be high, medium, or low
	* 
	* @throws ValidationException ensures title and priority are valid entries and will display a message
	* informing the user if this is not the case.
	* 
	* @since 1.0
	*/
	public Task(String title, String priority) throws ValidationException{
		
		setTitle(title);
		setPriority(priority);
	}
	
	
	
	/**
	 * This overloaded constructor verifies and accepts all 3 task parameters.
	 * 
	 * @param title name of the task
	 * @param priority importance of task will be high, medium, or low
	 * @param isComplete boolean which will be true (is complete) or false (isn't complete)
	 * 
	 * @throws ValidationException ensures title, priority, and isComplete boolean is correct. Will respond to an
	 * incorrect input by displaying an appropriate message.
	 * 
	 * @since 1.0
	 */
	public Task (String title, String priority, boolean isComplete) throws ValidationException{
		
		setTitle(title);
		setPriority(priority);
		setIsComplete(isComplete);
		
	}
	
	
	
    /**
     * This method returns the name of the task.
     * 
     * @return title - name of the task
     * 
     * @since 1.0
     */
	public String getTitle(){
		return title;
	}
	
	
	
	
	
	/**
	 * This method verifies the Task name is valid and sets it. Meaning it is not null, empty, exceeding 25 characters, or contains tabs.
	 * 
	 * @param title name of the task
	 * 
	 * @throws ValidationException ensures title is not empty, null, greater than 25 characters or contain any
	 * tab characters in it. If so, it will display an appropriate message informing the user of the problem.
	 * 
	 * @since 1.0
	 */
	public void setTitle(String title) throws ValidationException{

		if (title == null || title.isEmpty() || title.trim().length() <= 0) 

			throw new ValidationException ("\nThere was a problem adding a task:\nThe title cannot be empty\nPlease try again.\n");

		else if (title.length() > 25)

			throw new ValidationException ("\nThere was a problem adding a task:\nTitle cannot exceed 25 characters\nPlease try again.\n");

		else  if (title.contains("\t"))
			
			throw new ValidationException ("\nThere was a problem adding a task:\nTitle cannot contain tab characters\nPlease try again.\n");
		
		else
			this. title = title.trim(); 
	}
	
	
	
	

	
	/**
	 *  This method returns the task priority inputed.
	 *  
	 * @return priority - importance of task will be high, medium, or low
	 * 
	 * @since 1.0
	 */
	public String getPriority(){
		return priority;
	}
	
	
	
	
	/**
	 * This method creates and returns a string representation of the class with the variables separated by tabs.
	 * 
	 * @return tabString - String with the variables separated by tabs
	 * 
	 * @since 1.0
	 */
	public String createTabRecord(){
		
		String tabString = getTitle() + "\t" + getPriority() + "\t" + getIsComplete();
		
		return tabString;
	}
	
	
	
	/**
	 * This method verifies the Priority value is valid and sets it. Meaning it's not null, empty, and is set to either high, medium, or low.
	 * 
	 * @param priority importance of task will be high, medium, or low
	 * 
	 * @throws ValidationException ensures priority is not null, empty, and either high, medium or low. If not it will
	 * display an appropriate message to the user informing them of the mistake.
	 * 
	 * @since 1.0
	 */
	public void setPriority(String priority) throws ValidationException{
		
		if (priority == null || priority.isEmpty()) 

			throw new ValidationException ("\nThere was a problem adding a task:\nPriority cannot be empty\nPlease try again.\n");
		
		else if (!priority.equalsIgnoreCase("high") && !priority.equalsIgnoreCase("medium") && !priority.equalsIgnoreCase("low") )
			
			throw new ValidationException ("\nThere was a problem adding a task:\nPriority must be high, medium, or low\nPlease try again.\n");
		
		this. priority = priority.trim();
	}
	
	
	
	/**
	 * This method returns the valid task isComplete value inputed by the user.
	 * 
	 * @return isComplete - boolean which will be true (is complete) or false (isn't complete)
	 * 
	 * @since 1.0
	 */
	public boolean getIsComplete(){
		return isComplete;
	}
	
	
	
	/**
	 * This method sets the isComplete boolean to either true or false depending on user input.
	 * 
	 * @param isComplete boolean which will be true (is complete) or false (isn't complete)
	 * 
	 * @since 1.0
	 */
	public void setIsComplete(boolean isComplete){
		this.isComplete = isComplete;
	}
	
	
	
	
	 
	/**
	 * 
	 * This method returns a string representation of the class. If isComplete is true it will append "is complete" to the string, if isComplete is false
	 * it will append "is not complete" to the string.
	 * 
	 * @return sb.toString() - a string consisting of the title, followed by the priority in brackets, followed by the isComplete boolean which appends
	 * either "is complete" for true; or "is not complete" for false.
	 * 
	 * @since 1.0
	 */
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