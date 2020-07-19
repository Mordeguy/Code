/* File Name: Programmer.java
 * Course Name: Object Oriented Programming - CST8132 
 * Lab Section: 301
 * Student Name: Nathan M. Abbey
 * Date: March 7, 2016
 */
package Assign2;
import java.text.DecimalFormat;
/*
 * This is the programmer class; a sub-class of employee. It sets the information in that class as well as adding additional
 * information. 
 */
public  class Programmer extends Employee {

	
	// Class Variables -----------------------------------------------------------------------------------------||
	private double hoursWorked;
	private double rateOfPay;
	
	
	// Default Constructor -------------------------------------------------------------------------------------||
	public Programmer(){
	}
	
	
	// Two overloaded Constructors  ----------------------------------------------------------------------------||
	public Programmer(String name, OurDate startDate){
	}
	public Programmer (String name, OurDate startDate, double hoursWorked, double rateOfPay){
		
		super (name, startDate);
		this.hoursWorked = hoursWorked;
		this.rateOfPay = rateOfPay;
	}
	
	
	
	// calculatePay Method: Minuses the appropriate taxes from the salary -------------------------------------||
	@Override
	public double calculatePay() {
		double pay;
		
		pay = (hoursWorked * rateOfPay);
		
		return pay;
	}
	
	
	
	// toString Method: Returns a string representation of the class ------------------------------------------||
	@Override
	public String toString(){
		
		DecimalFormat t1 = new DecimalFormat ("#.00");
		System.out.println ("\n--PROGRAMMER--");
		StringBuilder sb = new StringBuilder(super.toString());
		
		String st = String.format("Week Total: $%-25s",   t1.format(calculatePay()));
		String st2 = String.format("Hours Worked: %-25s",   hoursWorked);
		String st3 = String.format("Hourly Wage: $%-25s\n",   t1.format(rateOfPay));
		
		
		sb.append(st).append(st2).append(st3);
		
		return sb.toString();
	}
	
	
	
	// Set and Get Hours Worked Methods ----------------------------------------------------------------------||
	public void setHoursWorked (double hoursWorked){
		
		this.hoursWorked = hoursWorked;
	}
	public double getHoursWorked(){
		
		return hoursWorked;
	}
	
	
	
	// Set and Get rateOfPay Methods -------------------------------------------------------------------------||
	public void setRateOfPay(double rateOfPay){
		
		this.rateOfPay = rateOfPay;
	}
	public double getRateOfPay(){
		
		return rateOfPay;
	}

	
} // End of Programmer Class
