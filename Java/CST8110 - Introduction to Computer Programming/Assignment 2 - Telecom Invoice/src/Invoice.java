//Nathan Abbey, Section: 301, Date: October 30, 2015, Lab Teacher: Mauricio Orozco-Trujillo, Assignment #2    

import java.text.DecimalFormat;
import java.util.Scanner;

public class Invoice {


	private double minutesPurchased;
	private double invoiceAmount;
	private double interest;
	private double tax;
	private double totalAmount;
	private int totalDays;

	OurDate date = new OurDate();
	OurDate invDate = new OurDate();
	Scanner input = new Scanner(System.in);
	DecimalFormat t1 = new DecimalFormat ("#.00");


	public Invoice(){
		System.out.println("Welcome to the Abbey Telecom Company Billing System");
		minutesPurchased = 0;
		invoiceAmount = 0;
		interest = 0;
		tax = 0;
		totalAmount = 0;
	}


	// Prompts the user to input minutes
	public void setMinutesFromUser(){
		System.out.print ("Enter the number of minutes you wish to purchase: ");
		minutesPurchased = input.nextInt();
	}

	// Prompts the user to input todays/invoice dates
	public void setDatesFromUser(){
		System.out.println("Enter today's date:");
		date.setYearFromUser();
		date.setMonthFromUser();
		date.setDayFromUser();

		System.out.println("Enter invoice date:");
		invDate.setYearFromUser();
		invDate.setMonthFromUser();
		invDate.setDayFromUser();


		// Displays invoice/today date
		System.out.print ("\nToday date: ");
		date.displayDate();
		System.out.print ("Invoice date: ");
		invDate.displayDate();
	}


	//Calculates the total number of days overall
	public void calcDays(){
		totalDays = (date.year * 360) - (invDate.year * 360) + (date.month * 30)- (invDate.month * 30) + (date.day - invDate.day);

		if (totalDays >= 60) {
			interest = invoiceAmount * 0.10d;
		} else {
			interest = 0.00d;
		}
	}



	//Calculates the Amounts
	
	
	public void calculateInvoice(){
		invoiceAmount = 5 + (minutesPurchased * 0.02d);
		tax = (invoiceAmount * 0.13d);
		totalAmount = (invoiceAmount + tax );	
	}


	//Displays Invoice Amounts
	public void displayInvoice(){
		System.out.println("\nThe number of days since invoice date: " + totalDays);



		System.out.println ("Invoice Amount : $" + t1.format(invoiceAmount));
		System.out.println ("Tax            : $" + t1.format(tax));
		System.out.println ("Interest       : $" + t1.format(interest));
		System.out.println ("TOTAL          : $" + t1.format(totalAmount + interest));
	}


}