/* File Name: SalesRep.java
 * Course Name: Object Oriented Programming - CST8132 
 * Lab Section: 301
 * Student Name: Nathan M. Abbey
 * Date: March 7, 2016
 */
package Assign2;
import java.text.DecimalFormat;
/*
 * This is the SalesRep class; a sub-class of employee. It sets the information in that class as well as adding additional
 * information. 
 */
public class SalesRep extends Employee {

	
	// Class Variables -----------------------------------------------------------------------------------------||
	private double salesAmount;
	private double comissionRate;

	
	// Default Constructor -------------------------------------------------------------------------------------||
	public SalesRep(){
	}
	
	
	// Two overloaded Constructors  ----------------------------------------------------------------------------||
	public SalesRep(String name, OurDate startDate){
	}
	public SalesRep (String name, OurDate startDate, double salesAmount, double comissionRate){
		
		super (name, startDate);
		
		this.salesAmount = salesAmount;
		this.comissionRate = comissionRate;
	}
	
	

	// calculatePay Method: Minuses the appropriate taxes from the salary -------------------------------------||
	@Override
	public double calculatePay() {

		double pay;

		pay = (salesAmount * comissionRate);

				return pay;
	}
	
	
	
	// toString Method: Returns a string representation of the class ------------------------------------------||
	@Override
	public String toString(){
		
		DecimalFormat t1 = new DecimalFormat ("#.00");
		System.out.println ("--SALES REPRESENTATIVE--");
		StringBuilder sb = new StringBuilder(super.toString());
		
		String st = String.format("Week Total: $%-25s",   t1.format(calculatePay()));
		String st2 = String.format("Sales Amount: %-25s",   salesAmount);
		String st3 = String.format("Comission Rate: %-25s\n",   comissionRate);
		
		sb.append(st).append(st2).append(st3);
		
		return sb.toString();
	
	}
	
	
	
	// Set Get comissionRate Methods --------------------------------------------------------------------------||
	public void setComissionRate(double comissionRate){
		
		this.comissionRate = comissionRate;
	}
	public double getComissionRate(){
		
		return comissionRate;
	}
	
	
	
	// Set and Get salesAmount Methods ------------------------------------------------------------------------||
	public void setSalesAmount (int salesAmount){
		
		this.salesAmount = salesAmount;
	}
	public double getSalesAmount(){
		
		return salesAmount;
	}

	
} // End of SalesRep Class