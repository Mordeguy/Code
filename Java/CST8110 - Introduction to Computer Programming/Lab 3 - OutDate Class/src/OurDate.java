import java.util.Scanner;

public class OurDate {

	private int day;
	private int month;
	private int year;
	
	
	public OurDate(){  //Initial Constructor
		day = 1;
		month = 1;
		year = 1901;
		
		System.out.println(("Initial date is " + day + "/" + month + "/" + year));
	}

	
	
	
	public void inputDay(){
		Scanner keyboard = new Scanner (System.in);
		System.out.print("Enter a Day: ");
		day=keyboard.nextInt();
	}
	
	public void inputMonth () {
		Scanner keyboard = new Scanner (System.in);
		System.out.print ("Enter a month: ");
		month=keyboard.nextInt();
	}
	
	public void inputYear () {
		Scanner keyboard = new Scanner (System.in);
		System.out.print ("Enter a year: ");
		year= keyboard.nextInt();
	}
	

	
	
	public void displayDate(){
		
	System.out.println ("Date is " + month + "/" + day + "/" + year );
	}
	
	
	
}




