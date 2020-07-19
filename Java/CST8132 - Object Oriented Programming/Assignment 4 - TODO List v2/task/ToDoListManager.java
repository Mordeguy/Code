/*    File Name: ToDoListManager.java
 *  Course Name: Object-Oriented Programming CST8132
 *  Lab Section: 301
 * Student Name: Nathan M. Abbey
 *         Date: April 22, 2016
 *       Author: Stanley Pieda (2015) Personal Communication
 *   Updated By: Nathan M. Abbey
 */
package task;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
/*
 * References For Class:
 * - Algonquin College (2016) CST8132 Lab Assignment 3 - To Do List UML Diagram. [UML Visual Diagram]
 * - Algonquin College (2016) CST8132 Lab Assignment 3 - To Do List UML Diagram. [Class Description]
 * - Algonquin College (2016) CST8132 Lab Assignment 3 - To Do List UML Diagram. [Code Provided]
 * - Algonquin College (2016) CST8132 Lab Assignment 4 - To Do List v2 UML Diagram. [UML Visual Diagram]
 * - Algonquin College (2016) CST8132 Lab Assignment 3 - To Do List v2 UML Diagram. [Class Description]
 */


/**
 * This is the list manager that enables the user to add, remove, toggle complete or not, view, save, load, and sort the tasks  in numerous 
 * ways. User can also exit and interact with the program. This class works directly with the other classes in 
 * order to ensure the program menu and calls work correctly.
 * 
 * @author Nathan M. Abbey
 * 
 * @version 1.0
 *
 */
public class ToDoListManager {


	// Class variables -------------------------------------------------------------------------------||
	private final static int ADD_TASK = 1;
	private final static int TOGGLE_TASK_COMPLETE = 2;
	private final static int REMOVE_TASK = 3;
	private final static int VIEW_TASKS = 4;
	private final static int SAVE_TASKS = 5;
	private final static int LOAD_TASKS = 6;
	private final static int SORT_BY_TITLE = 7;
	private final static int SORT_BY_PRIORITY = 8;
	private final static int SORT_BY_ISCOMPLETE = 9;
	private final static int EXIT = 10;
	private ArrayList<Task> tasks; 
	private Scanner input = new Scanner(System.in); 




	/**
	 * This default constructor that initializes the array list and scanner.
	 * 
	 * @since 1.0
	 */
	public ToDoListManager(){

		System.out.println("Welcome to the TO-DO Task List Program! "
				+ "\n\nPlease choose an option from the menu below.");

		tasks = new ArrayList<Task>();
	}




	/**
	 * This method displays the menu for the program and re-directs the user to the chosen method. It also checks
	 * the input is valid.
	 * 
	 * @since 1.0
	 */
	public void runToDoList(){

		do{
			int choice;

			System.out.println("\n-----------------------------------|");
			System.out.println("1 to add a task");
			System.out.println("2 to toggle a task is complete");
			System.out.println("3 to remove a task");
			System.out.println("4 to view all tasks");
			System.out.println("5 to save tasks to file");
			System.out.println("6 to load tasks from file");
			System.out.println("7 to sort tasks by title");
			System.out.println("8 to sort tasks by priority");
			System.out.println("9 to sort tasks by is-complete");
			System.out.println("10 to exit program");
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
				case SAVE_TASKS:
					saveTasks();
					break;
				case LOAD_TASKS:
					loadTasks();
					break;
				case SORT_BY_TITLE:
					sortByTitle();
					break;
				case SORT_BY_PRIORITY:
					sortByPriority();
					break;
				case SORT_BY_ISCOMPLETE:
					sortByIsComplete();
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



	/**
	 * This method adds a task to the array list. It accepts the title and priority of the task; ensuring both
	 * are within certain parameters.
	 * 
	 * @since 1.0
	 */
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




	/**
	 * This method toggles the icComplete boolean. True means is complete, false means is not complete. It ensures
	 * the input is valid and not out of bounds.
	 * 
	 * @since 1.0
	 */

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




	/**
	 * This method removes a task from the ArrayList. It prompts for the index number. It validates the input to ensure it is an
	 * integer within the range of the ArrayList.
	 * 
	 * @since 1.0
	 */
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


	/**
	 * This method prints out a list of the tasks in the ArrayList. If none are present it indicates there is nothing to show.
	 * 
	 * @since 1.0
	 */
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


	/**
	 * This method saves the tasks inputed onto the computer in a text file.
	 * 
	 * @since 1.0
	 */
	public void saveTasks(){

		if (tasks.isEmpty())
			System.out.println("\nNo tasks to save");

		else {

			File f = new File ("C:\\Users\\Nay-thwan\\Desktop\\Tasks.txt");
			Formatter fmtr = null;

			try {
				fmtr = new Formatter(f);
			} catch (FileNotFoundException e) {

				System.out.println("FIle not found.");
			}

			for (int i = 0; i <tasks.size(); i++){

				fmtr.format("%s%n", tasks.get(i).createTabRecord());
			}

			fmtr.flush();
			fmtr.close();	
		}
	}


	/**
	 * This method loads a task list from an already existing text file on a computer.
	 * 
	 * @since 1.0
	 */
	public void loadTasks(){

		File f = new File ("C:\\Users\\Nay-thwan\\Desktop\\Tasks.txt");

		tasks.removeAll(tasks);

		try {
			input = new Scanner(f);
			while (input.hasNextLine()){
				String[] s = input.nextLine().split("\t");

				if (s[2].equals("false")){

					tasks.add(new Task(s[0], s[1], false));

				}else{
					tasks.add(new Task(s[0], s[1], true));
				}
			}
		} catch (FileNotFoundException |ValidationException e) {

			System.out.print("This  could not be done!");

		} finally {
			//input.close();
			input = new Scanner (System.in);
		}
	}



	/**
	 * This method sorts the tasks alphabetically by title.
	 * 
	 * @since 1.0
	 */
	public void sortByTitle(){

		if (tasks.isEmpty())
			System.out.println("\nThere are no tasks to sort.");

		else {

			Collections.sort(tasks, new TaskTitleComparator());

			System.out.println("\nTasks have been sorted alphabetically by title.");
		}
	}


	/** 
	 * This method sorts the tasks from high to medium to low.
	 * 
	 * @since 1.0
	 */
	public void sortByPriority(){

		if (tasks.isEmpty())
			System.out.println("\nThere are no tasks to sort.");

		else {

			Collections.sort(tasks, new TaskPriorityComparator());

			System.out.println("\nTasks have been sorted by priority (high - medium - low).");
		}
	}


	/**
	 * This method sorts the tasks; not complete on top, is complete on bottom.
	 * 
	 * @since 1.0
	 */

	public void sortByIsComplete(){

		if (tasks.isEmpty())
			System.out.println("\nThere are no tasks to sort.");

		else {

			Collections.sort(tasks, new TaskIsCompleteComparator());

			System.out.println("\nTasks have been sorted by is complete status.");
		}
	}


} // End of ToDoListManager Class
