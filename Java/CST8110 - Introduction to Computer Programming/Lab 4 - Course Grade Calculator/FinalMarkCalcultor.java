import java.text.DecimalFormat;
import java.util.Scanner;

public class FinalMarkCalcultor {

	//instance variables
	private double hybridActivities;
	private double labs;
	private double assignments;
	private double testsQuizzes;
	private double labTest;
	private double finalExam;
	private double finalMark;


	//Sets a scanner object instance variable
	Scanner input = new Scanner (System.in);


	//Default constructor
	public FinalMarkCalcultor() {

		double hybridActivities = 0F;
		double labs = 0F;
		double assignments = 0F;
		double testsQuizzes = 0F;
		double labTest = 0F;
		double finalExam = 0F;
		double finalMark = 0F;	
		
		System.out.println("Welcome to the CST8110 Final Mark Calculator \n");

	}

	//Prompts and accepts input from user
	public void getGradesFromUser() {

		System.out.print("Enter your hybrid mark out of 5: ");
		hybridActivities = input.nextDouble();

		System.out.print("Enter your lab mark out of 10: ");
		labs = input.nextDouble();

		System.out.print("Enter your assignments mark out of 100: ");
		assignments = input.nextDouble();

		System.out.print("Enter your tests / quiz mark out of 25: ");
		testsQuizzes = input.nextDouble();

		System.out.print ("Enter your lab test mark out of 5: ");
		labTest = input.nextDouble();

		System.out.print ("Enter your final exam mark out of 30: ");
		finalExam = input.nextDouble();

	}
	
	
	//Calculates final grade
	public void calcFinalGrade(){
		finalMark = (hybridActivities / 5 * 100 * 0.05) + (labs / 10 * 100 * 0.10) + (assignments / 100 * 100 * .20) + (testsQuizzes / 25 * 100 * 0.25) + (labTest / 5 * 100 * 0.10) + (finalExam / 30 * 100 * 0.30);
	}
	


	//Displays the calculated values
	DecimalFormat t1 = new DecimalFormat ("#.00");
	public void display(){
		System.out.println ("\n\nCalculated final mark: " + t1.format(finalMark));
	}







}
