package assign1;
/* File Name: 		Planner.java
 * Course Name:		Object Oriented Programming
 * Lab Section:		301
 * Student Name:	Nathan M. Abbey
 * Date:			2016 / 02 / 11
 */
import java.util.Scanner;

/* Represents an electronic planner that keeps track of events. */

public class Planner {

	// Class Variables -----------------------------------------------------------------------------------------||
	private final int MAXEVENTS = 10;
	Event[] events = new Event[MAXEVENTS];
	private int numEvents = 0;
	OurDate date = new OurDate();


	// Default Constructor -------------------------------------------------------------------------------------||
	public Planner(){
		for (int i = 0; i < events.length; i++) {
			events[i] = new Event();
		}
	}


	// runMenu Method: Prints out the menu, prompts for choice; directs to choice in menu ----------------------||
	public void runMenu(){

		Scanner in = new Scanner(System.in);
		int choice;

		do {
			System.out.println ("Make a selection:");
			System.out.println("1.  Add event to planner");
			System.out.println("2.  Display event for day");
			System.out.println("3.  Display events for week");
			System.out.println("4.  Delete an event");
			System.out.print("0 to quit: ");
			choice = in.nextInt();

			if (choice == 1)
				addEvent();

			if (choice == 2){
				enterDate();
				displayOneDay();
			}
			if (choice == 3)//{
				displaySevenDays();
			//}

			if (choice == 4)
				deleteEvent();

			System.out.println();

			if (choice ==0)
				System.out.println ("Goodbye");

		} while (choice != 0);
	} 


	// addEvent Method: Makes sure there's room and creates event, accepts description and date; error message if date taken.
	public void addEvent(){	

		Scanner in = new Scanner(System.in);
		String temp;
		boolean checker = false;

		if (numEvents < MAXEVENTS){	 								

			System.out.print("Enter description: ");
			temp = in.nextLine();
			events[numEvents].setDescription(temp);

			System.out.println("Enter event date:");
			enterDate(); 

			for (int i = 0; i < MAXEVENTS; i++){

				if (date.isEqual(events[i].getDate())){ 

					System.out.println("You already have an activity for that date and time . . . cannot be entered");
					checker = true;   
				}   
			} 
			if (checker == false){  //checker = false
				events[numEvents].setDate(date.getDay(), date.getMonth(), date.getYear());
				numEvents += 1;
			}
		} 
	}


	// enterDate Method: Prompts for year, month and day and sets it. ------------------------------------------||
	public void enterDate(){

		Scanner in = new Scanner(System.in);

		System.out.print ("Enter year:");
		date.setYear(in.nextInt());

		System.out.print ("Enter month:");
		date.setMonth(in.nextInt());

		System.out.print ("Enter day:");
		date.setDay(in.nextInt());

	}


	//displayOneDay Method: displays the day w/ description if it exists in the events[i] array, blank line if none
	public void displayOneDay(){

		System.out.println ("Your event for " + date.toString() + " is:");

		for (int i = 0; i < numEvents; i++){

			if (date.isEqual(events[i].getDate())) {
				System.out.println("\t" + events[i].toString());
			} 
		} 	
	}


	// displaySevenDays Method: Prompts for a start date and runs through displayOneDay() seven times ---------||
	public void displaySevenDays(){

		System.out.println ("Enter starting date to display:");
		enterDate();

		System.out.println ("The events for the week starting " + date.toString() + " are:");

		for (int i = 0; i < 7; i++){

			displayOneDay();

			System.out.println();

			date.addOne();
		}
	}


	// deleteEvent Method: Searches through the array and deletes the matching date and decrements numEvents by 1
	public void deleteEvent(){	

		System.out.println ("Enter date to delete event from: ");
		enterDate();

		for (int i = 0; i < numEvents; i++){

			if (date.isEqual (events[i].getDate()))  {

				events [i].setDate(0, 0, 0);

				events [i].setDescription(null);

				numEvents -= 1;

				for (int j = i+1; j < events.length ; j++, i++){

					events[i] = events [j];
				}

				events[9] = new Event();
				events[9].setDate(0, 0, 0);
				events[9].setDescription(null);
			}
		} 
	}  
} // End of Planner Class