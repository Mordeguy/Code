/* Nathan Abbey, Section: 301, Date: October 9, 2015, Lab Teacher: Mauricio Orozco-Trujillo, Assignment #1      
		   Purpose of Program: Calculate and display your final mark based on the weighting of course material*/ 

import java.text.DecimalFormat;
import java.util.Scanner;

public class Assign1 {



	public static void main(String[] args) {


		Scanner input = new Scanner (System.in);

		double hybrid;
		double labmark;
		double assignments;
		double testquiz;
		double labtest;
		double exam;


		// Greeting statement
		System.out.print ("Welcome to the CST8110 Final Mark Calculator");


		//Hybrid activities worth 5% of final mark
		System.out.print ("\n\nEnter your hybrid activity mark out of 5: ");
		hybrid = input.nextDouble();


		// Labs are worth 10% of your mark
		System.out.print ("Enter your lab mark out of 10: ");
		labmark = input.nextDouble();


		//Assignments are worth 20% of your mark
		System.out.print ("Enter your assignments mark out of 100: ");
		assignments = input.nextDouble();


		//Tests and Quizzes marks are worth 25% of your mark
		System.out.print ("Enter your test(s)/quiz mark out of 25: ");
		testquiz = input.nextDouble();


		// Lab test is called practical assessment and is worth 10%
		System.out.print ("Enter your lab test mark out of 5: ");
		labtest = input.nextDouble();


		//Exam mark is worth 30% of your mark 
		System.out.print ("Enter your final exam mark out of 30: ");
		exam = input.nextDouble();


		//Calculations based on weighted mark of each section
		double weightedhybrid = hybrid / 5 * 100 * 0.05;
		double weightedlabmark = labmark / 10 * 100 * 0.10;
		double weightedassignments = assignments / 100 * 100 * .20;
		double weightedtestquiz = testquiz / 25 * 100 * 0.25;
		double weightedlabtest = labtest / 5 * 100 * 0.10 ;
		double weightedexam = exam / 30 * 100 * 0.30 ;


		// Calculations to determine final mark
		double totalPercent =(weightedhybrid + weightedlabmark + weightedassignments + weightedtestquiz + weightedlabtest + weightedexam);

		DecimalFormat t1 = new DecimalFormat ("#.00");



		//Displays final mark out of 100%
		System.out.println ("\n\nCalculated final mark: " + t1.format(totalPercent) );

	}

}


