/*************************************************************************************************************************
         Filename:  ass1.cpp
          Version:  1.0
           Author:  Nathan M. Abbey
   Student Number:  040-557-192
      Course Name:  Numerical Computing
    Course Number:  CST 8233
      Lab Section:  301
Assignment Number:  01
  Assignment Name:  Catostrophic Vibration
         Due Date:  25:59, October 15, 2017
  Submission Date:  17:00, October 15, 2017
 Professor's Name:  Andrew Tyler
          Purpose:  This code is a building simulation in which the employer wants to model the start of the motion
		            response of a tall building hit by a strong wind; which can potentially result in catastrophy.
					We use a Maclaurin series to approximate the movement and compare that to the math library.
**************************************************************************************************************************/
#include <iostream>
#include <string>
#include <cmath>
#include <iomanip>

using namespace std;


/*------------Function Prototype(s)-----------*/
double truncatedTerm(int termNo, double time);


/*****************************************************************************************
          Function Name:  main
                Purpose:  Runs a program which calculates the output of a Maclaurin series 
				          over a period of time. The user can choose precision up to six 
						  non-zero terms.
 Function In Parameters:  None
Function Out Parameters:  0 on success
                Version:  1.0
    Author/Student Name:  Nathan M. Abbey
******************************************************************************************/
int main(void) {

	int choice;

	do {
		cout << "Evaluate the Maclaurin Series approximation to D(t) = exp(t) * cos(t)" << endl << endl;
		cout << "1: Evaluate the series\n2: quit" << endl;
		cin >> choice;

		if (choice == 1) {
			int termNumber;
			double incrementNumber, relativeExactError, relativeSeriesError;
			double equation = 1;
			double time = 0;

			cout << "Evaluating the series" << endl;
			cout << "Please enter the number of (non-zero) terms in the series (1, 2, 3, 4, 5, or 6):";
			cin >> termNumber;
			cout << "Please enter the range of t to evaluate in 10 increments (0.0 < t < +4.0):";
			cin >> incrementNumber;

			incrementNumber = incrementNumber / 10;

			cout << "MACLAURIN SERIES" << endl;
			cout << "   t\t\t" << "D(t) Series\t" << "  D(t) Exact\t" << "    RExactE\t   " << "   RSerE\t\t" << endl;

			double temp;
			for (int i = 0; i <= 10; i++) {

				switch (termNumber) {
				case 1:
					equation = 1;
					break;
				case 2:
					equation = 1 + (time);
					break;
				case 3:
					equation = 1
						+ (time)
						-((time)* (time) * (time) / (double)3);
					break;
				case 4:
					equation = 1
						+ (time)
						-((time)* (time) * (time)) / (double)3
						- ((time) * (time) * (time) * (time)) / (double)6;
					break;
				case 5:
					equation = 1
						+ (time)
						-((time)* (time) * (time)) / (double)3
						- ((time) * (time) * (time) * (time)) / (double)6
						- ((time)*(time)*(time)*(time)*(time)) / (double)30;
					break;
				case 6:
					equation = 1
						+ (time)
						-((time)* (time) * (time) / (double)3)
						- ((time) * (time) * (time) * (time) / (double)6)
						- ((time)*(time)*(time)*(time)*(time) / (double)30)
						+ ((time)*(time)*(time)*(time)*(time)*(time)*(time) / (double)630);
					break;
				}

				temp = exp(time)*cos(time);
				relativeExactError = 100 * (temp - equation) / temp;

				double temp2 = truncatedTerm(termNumber, time);
				relativeSeriesError = 100 * temp2 / equation;

				cout  << scientific << setprecision(3) << time << scientific << setprecision(5) 
					<< setw(18)<< equation << setw(18) << scientific << temp << setw(18) <<
					relativeExactError << setw(18) << relativeSeriesError << endl;
			
				time += incrementNumber;
			}
		}
		cout << endl << endl;
	} while (choice != 2);
}


/*****************************************************************************************
          Function Name:  truncatedTerm
                Purpose:  Find the value of the truncated term in the Maclaurin series 
			              and return it.
 Function In Parameters:   int termNo - The precision the user has selected
                          double time - The time value being fed in to the Maclaurin series
Function Out Parameters:  A double that is the result of the truncated term
                Version:  1.0
    Author/Student Name:  Nathan M. Abbey
******************************************************************************************/
double truncatedTerm(int termNo, double time) {

	double truncated = 0;

	switch (termNo) {
	case 1:
		truncated += (time);
		break;
	case 2:
		truncated -= ((time)* (time) * (time) / (double)3);
		break;
	case 3:
		truncated -= ((time) * (time) * (time) * (time)) / (double)6;
		break;
	case 4:
		truncated -= ((time)*(time)*(time)*(time)*(time)) / (double)30;
		break;
	case 5:
		truncated += ((time)*(time)*(time)*(time)*(time)*(time)*(time) / (double)630);
		break;
	case 6:
		truncated += ((time) * (time)* (time)* (time)* (time)* (time)* (time)* (time) / (double)2520);
		break;
	}
	return truncated;
}