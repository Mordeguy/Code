//Nathan Abbey, Section: 301, Date: October 30, 2015, Lab Teacher: Mauricio Orozco-Trujillo, Assignment #2    

import java.util.Scanner;

public class OurDate {

	public int day;
	public int month;
	public int year;


	// Initial Constructor
	public OurDate(){ 
		this (1, 1, 1900);
	}


	// Default Constructor
	public OurDate(int d, int m, int y){
		day = d;
		month = m;
		year = y;
	}


	// Prompts and accepts date inputs
	public void setDayFromUser (){
		Scanner keyboard = new Scanner (System.in);
		System.out.print("Enter the Day: ");
		day=keyboard.nextInt();
	}
	public void setMonthFromUser () {
		Scanner keyboard = new Scanner (System.in);
		System.out.print ("Enter the month: ");
		month=keyboard.nextInt();
	}
	public void setYearFromUser () {
		Scanner keyboard = new Scanner (System.in);
		System.out.print ("Enter a valid year: ");
		year= keyboard.nextInt();
	}


	// Displays date in yyyy/mm/dd format
	public void displayDate(){

		System.out.println (+ year + "/" + month + "/" + day );
	}
}


	

