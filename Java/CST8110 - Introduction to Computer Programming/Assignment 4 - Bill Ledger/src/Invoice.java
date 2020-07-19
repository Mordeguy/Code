// Name: Nathan Abbey      Section: 301     Lab Teacher: Mauricio Orozco-Trujillo

import java.text.DecimalFormat;
import java.util.Scanner;

public class Invoice {


	private double billAmount;
	private String name;
	private OurDate dueDate = new OurDate();
	private Scanner input = new Scanner(System.in);
	
	// Initial Constructor
	public Invoice(){
	}
	
	// Default Constructor
	public Invoice(int a, String n){
		billAmount = a;	
		name = n;
		}

	
	// Prompts the user to input invoice date
	public void setDateFromUser(){
		System.out.println("Enter invoice due date:");
		dueDate.setDayFromUser();
		dueDate.setMonthFromUser();
		dueDate.setYearFromUser();
	}
	


	// Prompts the user to input the company name
	public void setCompanyNameFromUser(){
		System.out.print ("Enter Company Name: ");
		name = input.nextLine();
	}
	
	



	public void setBillAmountFromUser(){
		System.out.print ("Enter bill amount: ");
		billAmount = input.nextDouble();
	}
	
	public double getAmount(){
		return billAmount;
	}
	
	
	
	
	public String toString(){
	
	return name + "\n" + billAmount + "\n" + dueDate + "\n";
			

	}
	
	
	}



