import java.text.DecimalFormat;
import java.util.Scanner;
public class Program2 {

	public static void main(String[] args) {
		
		double hoursWorked;	
		double rateOfPay;
		double overtimePay;
		double overtimeHours;
		double overtime;
		double basePay;
		double tax;
		double totalPay;
		double netPay;
		
		
		// Constructs an object of a Scanner class
		Scanner input = new Scanner (System.in);
		
		//Constructs an object of a DecimalFormat class
		DecimalFormat decimal = new DecimalFormat("#.00");
	
		
		//Prompts input of hours worked
		System.out.print ("Enter the number of hours worked  :   ");
		hoursWorked = input.nextDouble();
       
	
		//Prompts input of rate of pay
		System.out.print ("Enter the rate of pay  :   ");
		rateOfPay = input.nextDouble();
		
		basePay = hoursWorked * rateOfPay;
		overtimePay = rateOfPay * 1.5;
		overtimeHours = hoursWorked - 37.5;
		overtime = 0;
		totalPay = overtime + basePay;
		tax = 0;
		
		

		if (hoursWorked > 37.5) {
			basePay = (hoursWorked - overtimeHours) * rateOfPay;
			overtime = overtimePay * overtimeHours;
			totalPay = overtime + basePay;
		} else {
		}
	
		if (totalPay > 0)
			tax = 0;
			
		if (totalPay > 1000 && hoursWorked < 2000)
			tax = totalPay * .20;
		
		if (totalPay > 2000)
			tax = totalPay * .30;
		
		netPay = totalPay - tax;
		
		System.out.println ("\nBase pay is:  $ " + decimal.format(basePay));
		System.out.println ("Overtime pay is  $ " + decimal.format(overtime));
		System.out.println ("Total pay is  $ " + decimal.format(totalPay));
		System.out.println ("Tax deducted is  $ " + decimal.format(tax));
		System.out.println ("Net pay is  $ " + decimal.format(netPay) );
		}
}




