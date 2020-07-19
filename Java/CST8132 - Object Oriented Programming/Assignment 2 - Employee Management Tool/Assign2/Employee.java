/* File Name: Employee.java
 * Course Name: Object Oriented Programming - CST8132 
 * Lab Section: 301
 * Student Name: Nathan M. Abbey
 * Date: March 7, 2016
 */
package Assign2;

/*
 * This class is the super class to the employees. It enables user to input start date, set taxable income,
 * calculate taxes owing, and calculates net pay by calculating and subtracting taxes from taxable income.
 */
public abstract class Employee {

	
	
	// Class Variables -----------------------------------------------------------------------------------------||
	private String name;
	OurDate startDate = new OurDate();
	private double taxableIncome;
	protected double netPay;
	private double tax;
	
	
	
	// Default Constructor -------------------------------------------------------------------------------------||
	public Employee (){
	}
	
	
	// Initial Constructor -------------------------------------------------------------------------------------||
	public Employee (String name, OurDate startDate){
		this.name = name;
		this.startDate = startDate;
	}
	
	
	
	// toString Method: Returns a string representation of this class ------------------------------------------||
	@Override
	public String toString(){

		StringBuilder sb = new StringBuilder();
	
		String st = String.format("\nName: %-25s",  name);
		
		sb.append(st);
		
		return sb.toString();
	}
	

	
	// Abstract calculatePay Method ---------------------------------------------------------------------------||
	public abstract double calculatePay();
	
	
	
	// calculateTax Method: Calculates the taxes owed based on income earned ----------------------------------|| 
	public double calculateTax(){

		double stepTax;
		double incomeTaxed = taxableIncome;

		if (incomeTaxed <= 45282){

			stepTax = (incomeTaxed * 0.15);

			tax += stepTax;

		} else {

			stepTax = (45282 * 0.15);

			incomeTaxed -= 45282;

			tax += stepTax; 
		}

		if (incomeTaxed <= 45281) {

			stepTax = (incomeTaxed * 0.205);

			tax += stepTax;

			incomeTaxed -= incomeTaxed;

		} else {

			stepTax = (45281 * .205);

			incomeTaxed -= 45281;

			tax += stepTax;
		}

		if (incomeTaxed <= 49825) {

			stepTax = (incomeTaxed * 0.26);

			tax += stepTax;

		} else {

			stepTax = (49825 * 0.26);

			incomeTaxed -= 49825;

			tax += stepTax;
		}

		if (incomeTaxed <= 59612) {

			stepTax = (incomeTaxed * 0.29);

			tax += stepTax;

			incomeTaxed =0;

		} else {

			stepTax = (59612 * 0.29);

			incomeTaxed -= 59612;

			tax += stepTax;
		}

		if (incomeTaxed > 1){

			stepTax = (incomeTaxed * 0.33);

			tax += stepTax;
		}
		
		return tax;
	}
	
	
	
	// Get and Set Name Methods: Accepts name input and sets it -----------------------------------------------||
	public String getName(){
		return name;
	}
	public void setName(String name){
		
		this.name = name;
	}
	
	
	
	// Get and Set Date Methods: Accepts date input and sets it -----------------------------------------------||
	public OurDate getStartDate(){
		
		return startDate;
	}
	public void setStartDate (OurDate date){
		startDate = date;
	}
	
	
	
	// Get and Set TaxAble Income Methods: Accepts income input and sets it -----------------------------------||
	public void setTaxableIncome (double taxableIncome){
		
		this.taxableIncome = taxableIncome;
	}
	public double getTaxableIncome (){
		
		return taxableIncome;
	}
	
	

	// getNetPay Method: Accepts a net pay input and sets it --------------------------------------------------||
	public double getNetPay(){
		
		netPay = taxableIncome - tax;
		
		return netPay;
	}
	

	
	// getTax Method: Returns the calculated tax --------------------------------------------------------------||
	public double getTax(){
		
		return tax;
	}
	

}   // End of Employee Class