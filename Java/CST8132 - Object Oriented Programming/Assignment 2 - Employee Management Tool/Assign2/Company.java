/* File Name: Company.java
 * Course Name: Object Oriented Programming - CST8132 
 * Lab Section: 301
 * Student Name: Nathan M. Abbey
 * Date: March 7, 2016
 */
package Assign2;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.time.LocalDate;
/*
 * This is the Company class. It is the class which runs the software interface. It displays the menu and
 * creates entries based on user input; with the help of other classes.
 */
public class Company {
	
	
	// Class Variables -----------------------------------------------------------------------------------------||
	private final int MAXIMUMEMPLOYEES = 10;
	Employee[] employees = new Employee[MAXIMUMEMPLOYEES];
	private int totalEmployees;
	
	

	// Default Constructor: Creates 10 objects of the "Executive" Class, Since Employee is Abstract and MSG ----||
	public Company(){

		System.out.println("Welcome to the Employee Tracking Software System!");
		System.out.println("\nThis software is designed to enable you to track and manage your workforce, in\n" +
				"a more efficient manner.");
		System.out.println("\nPlease Enter Your Selection Below:\n");

		for (int i = 0; i < MAXIMUMEMPLOYEES; i++){
			employees[i] = new Executive();
		}
	}

	
	
	// menu Method: Runs the menu and directs to class methods based on choice --------------------------------||
	public void menu(){
		
		int choice;
		Scanner input = new Scanner (System.in);

		do {

			System.out.println("------------------------------------------------------|");
			System.out.println();
			System.out.println("1.  Add an Employee");
			System.out.println("2.  Print Employee Financial Information");
			System.out.println("3.  Print Employee List");
			System.out.println("4.  Print Name of Employee with Highest Seniority");

			System.out.println("\n\nPress -1 to EXIT");
			System.out.println("------------------------------------------------------|");

			System.out.print ("\nSelection -> ");
			choice = input.nextInt();

			if (choice == 1){
				addEmployee();
			}

			if (choice == 2){
				calculatePayroll();
			}

			if (choice == 3){

				printEmployeeList();
			}

			if (choice ==4){

				findLongestEmployedEmployee();
			}

			if (choice == -1){

				System.out.println("\nThank you for using our Employee Tracking Software System! Goodbye!");
			}
		}while (choice != -1);
	}

	
	
	// printEmployeeList Method: Prints the employee list( Name and Start Date) -------------------------------||
	private void printEmployeeList(){	

		if (totalEmployees ==0){

			System.out.println("\n* * There are currently no employees entered. Please choose another option from the menu. * *\n");

		} else {
			
			System.out.println();
			
			for (int i =0; i < totalEmployees; i++){
				
				String st1 = String.format("Name: %-24s", employees[i].getName());
				String st2 = String.format("Date of Hire: %-24s", employees[i].getStartDate());

				System.out.println(st1 + st2);
			}
			System.out.println();
		}
	}
	
	
	
	// calculatePayroll Method: Prints Employee Finances and Adds the Total Pay --------------------------------||
	private void calculatePayroll(){

		DecimalFormat t1 = new DecimalFormat ("#.00");
		double totalPayroll = 0;

		if (totalEmployees ==0){

			System.out.println("\n* * There are currently no employees entered. Please choose another option from the menu. * *\n");
		}
		
		for (int i = 0; i < totalEmployees; i++){

			System.out.println(employees[i].toString());
		}
		System.out.println();

		for (int i =0; i < totalEmployees; i++){

			totalPayroll += employees[i].getNetPay();
		}
		System.out.println("TOTAL WEEKLY PAYROLL: " + t1.format(totalPayroll/52));
	}

	
	
	// Add Employee Method: Runs the menu and accepts appropriate inputs, setting values as needed -------------||
	public void addEmployee(){

		DecimalFormat t1 = new DecimalFormat ("#.00");
		Scanner input = new Scanner (System.in);
		int choice;
		String name;
		OurDate d1 = new OurDate();

		if (totalEmployees < MAXIMUMEMPLOYEES) {

			System.out.println("\nEnter Employee Information:");

			System.out.print("Name: ");
			name =input.nextLine();

			System.out.println("Hiring Date:");

			System.out.println("(NOTE: Year must be after 2000 or else it will default to 2000)");
			System.out.print("Year: ");
			d1.setYear(input.nextInt());
			
			System.out.println ("(NOTE: Month must be between 1-12 or it will default to 1)");
			System.out.print("Month: ");
			d1.setMonth(input.nextInt());

			System.out.println ("(NOTE: Day must be in appropriate range. FEB 29th will only be accepted on a leap years)");
			System.out.print("Day: ");
			d1.setDay(input.nextInt());

			System.out.println ("\nWhat kind of employee is " + name + "?\n");
			System.out.println("1.  Executive");
			System.out.println("2.  Sales Rep");
			System.out.println("3.  Programmer");

			System.out.print ("\nSelection -> ");
			choice = input.nextInt();

			if (choice == 1){

				System.out.println("\nEXECUTIVE\n" );
				double salary;

				Executive exec = new Executive();

				employees[totalEmployees] = exec;

				System.out.print("Annual Salary: $");
				salary = input.nextDouble();

				exec.setName(name);
				exec.setStartDate(d1);
				exec.setSalary(salary);
				exec.setTaxableIncome(salary);
				exec.netPay = (exec.getSalary() - exec.calculateTax());

				System.out.println();
				System.out.println( name + " has been added as an EXECUTIVE, with an annual SALARY of $" + 
						t1.format(salary) + "; and hired on " + d1 + ".\n");
			}


			if (choice == 2){

				System.out.println("\nSALES REP\n" );
				double comissionRate;
				int totalSales;

				SalesRep sr = new SalesRep();

				employees [totalEmployees] = sr;

				System.out.print("Comission Rate: ");
				comissionRate = input.nextDouble();

				System.out.print("Total Sales: ");
				totalSales = input.nextInt();

				sr.setName(name);
				sr.setStartDate(d1);
				sr.setComissionRate(comissionRate);
				sr.setSalesAmount(totalSales);
				sr.setTaxableIncome(totalSales * comissionRate);
				
				System.out.println("\n" + name + " has been added as a SALES REP, with a " + comissionRate + " comission rate"
						+  " and " + totalSales + " in total sale(s); and hired on " +d1 + ".\n" );
			}

			if (choice ==3){

				System.out.println("\nPROGRAMMER\n" );
				double hourlyWage;
				double totalHours;

				Programmer prgmr = new Programmer();

				employees[totalEmployees] = prgmr;

				System.out.print("Hourly Wage: $");
				hourlyWage = input.nextDouble();

				System.out.print("Total Hours Worked: ");
				totalHours = input.nextDouble();

				prgmr.setName(name);
				prgmr.setStartDate(d1);
				prgmr.setHoursWorked(totalHours);
				prgmr.setRateOfPay(hourlyWage);
				prgmr.setTaxableIncome(totalHours * hourlyWage);

				System.out.println("\n" + name + " has been added as a PROGRAMMER, with an hourly wage of $" + 
						t1.format(hourlyWage) + " and " + totalHours + " in hours worked; they were hired on " + d1 + ".\n");
			}

			totalEmployees ++;
		} 
	} 
	
	
	
	// findLongestEmployedEmployee Method: Finds the employee who has been employed the longest ----------------||
	public void findLongestEmployedEmployee(){
		
		int holder1;
		int holder2;
		Employee emp = new Executive();
		int yearDivider = 365;
		int today = 0;
		int leapYearNum = 0;
		
	
		LocalDate todayAPI = LocalDate.now();
		OurDate d1 = new OurDate(todayAPI.getDayOfMonth(),todayAPI.getMonthValue(), todayAPI.getYear());

		if (totalEmployees ==0){

			System.out.println("\n* * There are currently no employees entered. Please choose another option from the menu. * *\n");
		}

		else{

			holder1 = employees[0].startDate.calcDays(employees[0].getStartDate());
			emp = employees[0];

			for (int i = 1; i < totalEmployees; i++){

				holder2 = employees[i].startDate.calcDays(employees[i].getStartDate());

				if (holder2 < holder1){

					holder1 = holder2;

					emp = employees[i];
				}
			}

			for (int i = emp.startDate.getYear(); i < d1.getYear(); i++){
				if (emp.startDate.isLeapYear(i) == true)
					leapYearNum ++;
			}
			
			today = d1.calcDays(d1) - holder1;
			int totalDays = today % yearDivider - leapYearNum;
			int totalYears = today / yearDivider;
			
			System.out.println("\nEmployee with Highest Seniority -->  " + emp.getName() + 
					" who has been employed for " + totalYears + " year(s) and " + totalDays + " day(s).\n");
		}
	}
	
	
} // End of Company Class
