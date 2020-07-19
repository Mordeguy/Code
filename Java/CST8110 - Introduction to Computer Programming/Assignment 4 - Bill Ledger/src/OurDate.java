// Name: Nathan Abbey      Section: 301     Lab Teacher: Mauricio Orozco-Trujillo

import java.util.Scanner;

public class OurDate {

	private int day;
	private int month;
	private int year;

	
	Scanner keyboard = new Scanner (System.in);
	
	
	// Initial Constructor
	public OurDate(){ 
	}

	// Default Constructor
	public OurDate(int d, int m, int y){
		day = d;
		month = m;
		year = y;
	}


	// Prompts and accepts date inputs
	public void setDayFromUser (){	
		System.out.print("Enter the Day: ");
		day=keyboard.nextInt();
	}	
	public void setMonthFromUser () {
		System.out.print ("Enter the month: ");
		month=keyboard.nextInt();
	}
	public void setYearFromUser () {
		System.out.print ("Enter a valid year: ");
		year= keyboard.nextInt();
	}



	// Displays date in dd/mm/yyyy format
	public String toString(){
		return day + "/" + month + "/" + year;
		
	}
}


	

