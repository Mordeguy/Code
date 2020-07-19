/* File Name: Executive.java
 * Course Name: Object Oriented Programming - CST8132 
 * Lab Section: 301
 * Student Name: Nathan M. Abbey
 * Date: March 7, 2016
 */
package Assign2;
import java.text.DecimalFormat;
/*
 * This is a sub-class to the Employee class. It sets the information in that class as well as adding additional
 * information. 
 */
public class Executive extends Employee {

	
	
	// Class Variable ------------------------------------------------------------------------------------------||
	private double salary;



	// Default Constructor -------------------------------------------------------------------------------------||
	public Executive(){
	}


	// Two overloaded Constructors  ----------------------------------------------------------------------------||
	public Executive(String name, OurDate startDate){
	}
	public Executive (String name, OurDate startDate, double salary){

		super (name, startDate);
		this.salary = salary;
	}



	// calculatePay Method: Returns the gross salary -----------------------------------------------------------||
	@Override 
	public double calculatePay() {

		return salary;
	}



	// toString Method: Returns a string representation of the class ------------------------------------------||
	@Override
	public String toString(){

		DecimalFormat t1 = new DecimalFormat ("#.00");
		
		System.out.println ("\n--EXECUTIVE--");
		StringBuilder sb = new StringBuilder(super.toString());

		String st = String.format("Annual Salary: $%-25s", t1.format(salary));
		String st2 = String.format("Total Annual Tax: $%-25s\n", t1.format(getTax()));
		String st3 = ("\t\t-------------------------------------------------------\n");
		String st4 = String.format("\t     Weekly Net Pay: $%-17s", t1.format(netPay/52));
		String st5 = String.format("   Weekly Tax Deducted: $%-25s\n", t1.format(getTax()/52));

		sb.append(st).append(st2).append(st3).append(st4).append(st5);

		return sb.toString();
	}



	// Set and Get Salary Methods  ----------------------------------------------------------------------------||
	public void setSalary(double salary){

		this.salary = salary;
	}
	public double getSalary (){

		return salary;
	}


} // End of Executive Class