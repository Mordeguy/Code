package assign1;
/* File Name: 		OurDate.java
 * Course Name:		Object Oriented Programming
 * Lab Section:		301
 * Student Name:	Nathan M. Abbey
 * Date:			2016 / 02 / 11
 */

/* This OurDate class sets the day, month and year and manipulates this data in many ways. Including:
 * - Calculating days from January 1, 2001
 * - Calculating if the year is a leap year
 * - Testing for equality
 * - Ensuring proper values are put in within a defined range
 */

public class OurDate {

	//Class Variables ---------------------------------------------------------------------------------------||
	private int day;
	private int month = 1;
	private int year;
	private final int [] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	
	//Default Constructor --------------------------------------------------------------------------------------||
	public OurDate(){	
	}

	//Overloaded Constructor -----------------------------------------------------------------------------------||
	public OurDate(int day, int month, int year){
		this.day = day;
		this.month = month;
		this.year = year;
	}

	//Overloaded Constructor -----------------------------------------------------------------------------------||
	public OurDate (OurDate date){
	}



	// Set Day/Month/Year Methods: prompts and allows user to input day, month and, year -----------------------||
	// ------------------------------------------------------
	public void setDay(int day){

		int maxValue;

		maxValue = daysInMonth [month -1];

		while (day > maxValue || day <0){
			day = 1;
		}

		if (isLeapYear(year)== true){
			daysInMonth[1] = 29;
		}

		this.day = day;
	}
	// ------------------------------------------------------
	public void setMonth(int month){


		while (month <= 0 || month > 12){
			month = 1;
		}

		this.month = month;
	}
	// ------------------------------------------------------
	public void setYear(int year){
		this.year = year;

	}


	// Get Day/Month/Year Methods: returns day, month, and year integer ----------------------------------------||
	public int getDay(){
		return day;
	}	
	public int getMonth(){
		return month;	
	}	
	public int getYear(){
		return year;
	}


	// isEqual Method: Measures if the date inputed is equal to any other date ---------------------------------||
	public boolean isEqual(OurDate date){

		if (this.year == date.year && this.month == date.month && this.day == date.day){

			return true;
		}
		else
			return false;
	}


	//toString method: returns a string representation ---------------------------------------------------------||
	public String toString(){
		return year + "/" + month + "/" + day;
	}



	/* isLeap Year Method - tests to see if they year has an extra day in February -----------------------------||
	 * Java Code for Calculating Leap Years (2013, December 13) Retrieved from:
	 * http://stackoverflow.com/questions/1021324/java-code-for-calculating-leap-year */
	public boolean isLeapYear(int year){

		if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)){
			return true;
			
		} else

			return false;
	}


	//calcDays method: calculates amount of days from date input to January 1, 2000 ---------------------------||
	public void calcDays(){

		int yearNum = year - 2000;
		int sum = 0;
		int leapYear = 0;

		for (int i = 0; i < month-1; i++){
			sum += daysInMonth[i];

			if (isLeapYear (year) == true)
				leapYear += 1;

			System.out.print((yearNum * 365)+ sum + day + leapYear);
		}
	}


	//addOne Method: Adds one to the day an ensures parameters are met ----------------------------------------||
	public void addOne(){

		for (int i = 0; i < daysInMonth.length; i++){
			if (day < daysInMonth[i])
				day += 1;
			i = daysInMonth.length;
		}

		for (int i = 0; i < daysInMonth.length; i++){

			if (day > daysInMonth[i]){
				month += 1;
				day = 1;
			}
			if (month == 12){
				month = 1;
				year += 1;
			}
		}
	}	

} // End of OurDate Class