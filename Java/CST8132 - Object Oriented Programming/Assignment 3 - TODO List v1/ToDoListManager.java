/*    File Name: ToDoListManager.java
 *  Course Name: Object-Oriented Programming CST8132
 *  Lab Section: 301
 * Student Name: Nathan M. Abbey
 *         Date: March 29, 2016
 *       Author: Stanley Pieda (2015) Personal Communication
 *   Updated By: Nathan M. Abbey
 */
package task;
import java.util.Scanner;
import java.util.*;
/*
 * This is the list manager that enables the user to add, remove, toggle complete or not, view, and
 * exit tasks; and interact with the program. This class works directly with the other classes in 
 * order to ensure the program menu and calls work correctly.
 * 
 * References For Class:
 * - Algonquin College (2016) CST8132 Lab Assignment 3 - To Do List UML Diagram. [UML Visual Diagram]
 * - Algonquin College (2016) CST8132 Lab Assignment 3 - To Do List UML Diagram. [Task Class Description]
 * - Algonquin College (2016) CST8132 Lab Assignment 3 - To Do List UML Diagram. [Code Provided]
 */
public class ToDoListManager {


	// Class variables -------------------------------------------------------------------------------||
	private final static int ADD_TASK = 1;
	private final static int TOGGLE_TASK_COMPLETE = 2;
	private final static int REMOVE_TASK = 3;
	private final static int VIEW_TASKS = 4;
	private final static int EXIT = 5;
	private ArrayList<Task> tasks; 
	private Scanner input; 




	//  Default constructor that initializes the array list and the scanner --------------------------||
	public ToDoListManager(){

		System.out.println("Welcome to the TO-DO Task List Program! "
				+ "\n\nPlease choose an option from the menu below.");

		tasks = new ArrayList<Task>();
		input = new Scanner(System.in);
	}





	// Runs to do list and directs to choice; verifies proper input ----------------------------------||
	public void runToDoList(){

		do{
			int choice;

			System.out.println("\n-----------------------------------|");
			System.out.println("1 to add a task");
			System.out.println("2 to toggle a task is complete");
			System.out.println("3 to remove a task");
			System.out.println("4 to view all tasks");
			System.out.println("5 to exit program");
			System.out.println("-----------------------------------|");

			try {

				System.out.print("\nChoice: ");
				choice = input.nextInt();
				input.nextLine();

				switch(choice){

				case ADD_TASK:
					addTask();
					break;
				case TOGGLE_TASK_COMPLETE:
					toggleTaskComplete();
					break;
				case REMOVE_TASK:
					removeTask();
					break;
				case VIEW_TASKS:
					viewTasks();
					break;
				case EXIT:
					System.out.println("\nGoodbye! See you next time!");
					return;
				default:
					System.out.println("\nUnrecognized Command");
					break;
				}

			} catch (InputMismatchException ex)

			{
				System.out.println("\nPlease enter integer numbers for menu selection (1-5)"); 
				input.nextLine();
			}
		}while (true);
	}





	// Adds a task (name and priority) to the Array List, ensuring certain parameters ----------------||
	public void addTask() {

		String title;
		String priority;

		try {
			System.out.println("\n-- ADD TASK --");
			System.out.println("\nPlease enter task title");
			System.out.println("(Title cannot be empty, max 25 characters)");

			title = input.nextLine();

			System.out.println("\nPlease enter task priority (high, medium, low):");
			priority = input.nextLine();

			Task task = new Task(title, priority);

			tasks.add(task);

			System.out.println("\n" +title + " has been added; with a " + priority + " priority rating.");

		} catch (ValidationException ex)
		{
			System.out.print(ex.getMessage());
		} 
	}





	// toggleTaskComplete Method: Toggle boolean true or false to indicate if task is complete or not ||
	public void toggleTaskComplete(){

		int indexNumber;

		if (tasks.isEmpty())
			System.out.println("\nNothing to toggle, no tasks.");

		else{

			System.out.println("\n -- TOGGLE A TASK IS COMPLETE --");
			try{
				System.out.print("\nWhat is the index number of the task?: ");
				indexNumber = input.nextInt();


				tasks.get(indexNumber).setIsComplete(!tasks.get(indexNumber).getIsComplete());

			} catch (InputMismatchException ex){

				System.out.print("\nCould not toggle task complete\nPlease enter only integers for index\nPlease try again\n");
				input.nextLine();
			} catch (IndexOutOfBoundsException ex){

				System.out.print("\nCould not toggle task comeplete\nPlease enter a valid index\n0 to " + (tasks.size() - 1) + "\n");
				input.nextLine();
			}
		}
	}





	// removeTask Method: Removes the indicated task from the list -----------------------------------||
	public void removeTask(){

		int indexNumber;

		if (tasks.isEmpty())
			System.out.println("\nNothing to remove, no tasks");

		else{

			System.out.println("\n-- REMOVE TASK --");
			try{
				System.out.print("\nWhat is the index number of the task?: ");
				indexNumber = input.nextInt();

				tasks.remove(indexNumber);

			} catch (InputMismatchException ex){

				System.out.print("\nCould not remove task\nPlease enter only integers for index\nPlease try again\n");
				input.nextLine();

			} catch (IndexOutOfBoundsException ex){

				System.out.print("\nCould not remove task\nPlease enter valid index number\n0 to " + (tasks.size() - 1) + "\n");
				input.nextLine();
			}
		}
	}



	// viewTasks Method: Prints out a list of the tasks ---------------------------------------------||
	public void viewTasks() {


		if (tasks.isEmpty())
			System.out.println("\nNothing to show, no tasks");

		else{

			for (int i = 0; i < tasks.size(); i++){

				System.out.print("\nIndex #" + i + " --> ");
				System.out.println(" " + tasks.get(i));
			}
		}	
	}

} // End of ToDoListManager Class
