import java.util.Scanner;
import java.text.DecimalFormat;

public class Calc {

	double choice;
	double num1;
	double num2;
	Scanner input = new Scanner (System.in);
	DecimalFormat d = new DecimalFormat ("#.00");
	
	
	public void setMathOp(){
		System.out.println ("Please enter an option:");
		System.out.println ("1 Addition");
		System.out.println ("2 Subtraction");
		System.out.println ("3 Multiplication");
		System.out.println ("4 Division");
		choice = input.nextDouble();
		
		while (choice < 0 || choice > 4) {
			System.out.println ("Invalid entry...please re-enter a valid number between 1-4:");
			choice = input.nextDouble();
		}
	}

	public void setNums(){
		System.out.print ("Please enter number 1:");
		num1 = input.nextDouble();
		System.out.print ("Please enter number 2:");
		num2 = input.nextDouble();
	}


	public void calcMath(){
		
		if (choice == 1){
			System.out.println (num1 + " + " + num2 + " is " + d.format((num1 + num2)));
		}
		
		if (choice == 2){
			System.out.println (num1 + " - " + num2 + " is " + d.format((num1 - num2)));
		}

		if (choice == 3){
			System.out.println (num1 + " * " + num2 + " is " + d.format((num1 * num2)));
		}

		if (choice == 4){
			System.out.println (num1 + " / " + num2 + " is " + d.format((num1 / num2)));
		}
	}
}