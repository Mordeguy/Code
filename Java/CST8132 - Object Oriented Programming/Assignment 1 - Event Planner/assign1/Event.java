package assign1;
/* File Name: 		Event.java
 * Course Name:		Object Oriented Programming
 * Lab Section:		301
 * Student Name:	Nathan M. Abbey
 * Date:			2016 / 02 / 11
 */

/* This Event class manages the events objects; including day/month/year and description. */
public class Event {

	// Class Variables -----------------------------------------------------------------------------------------||
	private OurDate date = new OurDate();
	private String description;


	// Default Constructor -------------------------------------------------------------------------------------||
	public Event(){
	}


	// Two Overloaded Constructors: first accepts date, second accepts day/month/year + description ------------||
	public Event (OurDate date, String description ){
	}
	public Event (int day, int month, int year, String description){	
	}


	// Two setDate methods: first accepts full date, second accepts day / month / year -------------------------||
	public void setDate(OurDate date){
	} 
	public void setDate (int day, int month, int year){
		date.setDay(day);
		date.setMonth(month);
		date.setYear(year);
	}


	// Set and Get Description methods -------------------------------------------------------------------------||
	public void setDescription(String description){

		this.description = description;
	}
	public String getDescription (){
		return description;
	}



	// getDate method: will return the date entered to another class -------------------------------------------||
	public OurDate getDate(){
		return date;
	}




	//toString method: returns a string representation ---------------------------------------------------------||
	public String toString(){

		return date.toString() + " - " + description;

	}

} // End of Event class