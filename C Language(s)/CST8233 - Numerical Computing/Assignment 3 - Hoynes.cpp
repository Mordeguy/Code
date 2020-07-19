/* FILE HEADER ------------------------------------------------------------------------------------------
             File Name:  ass3.cpp
               Version:  1.0
   Author/Student Name:  Nathan M. Abbey
        Student Number:  040 - 557 - 192
Course Name and Number:  CST8233 - Numerical Computing
           Lab Section:  301
     Assignment Number:  03
       Assignment Name:  Numerical Earthquake Simulation
              Due Date:  January 7, 2018 - 23:59
       Submission Date:  January 7, 2018 - 18:00
       Professors Name:  Mr. Andrew Tyler
               Purpose:  This program simulates the movement from a historical earthquake and outputs
			             the values generated to a document.
-------------------------------------------------------------------------------------------------------*/
#include <iostream>
#include <fstream>
#include <string>

using namespace std;

/*Function prototype*/
double ordinaryDifferentialEquation(double a, double x, double v, double k, double b);



/******************************************************************************************************
 Function Name:  main
       Purpose:  This method runs the entire program. It opens up filestreams and does the calculating
	             and prints out the data to the file.
 In Parameters:  Void
Out Parameters:  int ~ Returns 0 on successful completion of the program
       Version:  1.0
        Author:  Nathan M. Abbey
*******************************************************************************************************/
int main(void) {

	int const size = 219;
	double time[size];
	double displacement[size];
	double acceleration[size];
	double movement[size];
	double velocity[size];
	double const k = 20.0;
	double const b = 10.0;
	double vPrime, xPrime;
	double h = 0.0461;
	int numLines = 0;
	ifstream in;
	ofstream out;
	string count;

	movement[0] = 0.0;
	velocity[0] = 0.0;
	displacement[0] = 0.0;
	acceleration[0] = 0.0;
	acceleration[size - 1] = 0;

	int selection = 0;
	cout << "1. run the simulation\n2. Quit" << endl;
	cin >> selection;


	string fileName;
	if (selection == 1) {
		cout << "Please enter the name of the earthquake file to open: ";
		cin >> fileName;

	}


	in.open(fileName);
	if (in.fail()) {
		cout << "Opening the File Was Not Successful" << endl;
		exit(0);
	}

	/*Counts the amount of lines in the file*/
	while (getline(in, count)) {
		numLines++;
	}
	in.clear();
	in.seekg(0, in.beg);


	cout << "File opened; " << numLines << " rows of data" << endl;
	cout << "OPEN FILE TO SAVE" << endl;

	/*Gets the contents from NorthRidge.txt and puts them in 2 columns*/
	for (int i = 0; i < numLines; i++) {
		in >> time[i];
		in >> displacement[i];
	}

	
	/*Generates acceleration values and stores in array 'acceleration'*/
	for (int i = 1; i < numLines-1; i++) {
		/*          f'' =             f( x + h )       -  2 *             f(x)         +              f(x-h)          /    h^2           */

		acceleration[i] = ((displacement[i + 1] / 100) - (2 * (displacement[i] / 100)) + (displacement[i - 1] / 100)) / pow(h, 2);

	}/*                                Divided by 100 for conversion                                       1 because it's an array; 1 is a step*/


	for (int i = 0; i < numLines; i++) {

		double temp, vi, xi;

		/*
		During	the	simulation	the	differential	equations	are	solved	iteratively	using	Heun’s	method	to	generate	the	
		building movement --> x	and	velocity -->v at the end of	each step in time using	the	ground acceleration	from your	
		calculation	and	the	x	and	v	from	the	last	iteration	that	are	needed	to	calculate	the	terms	in	the	differential	
		equation	above.		
		*/
		
		temp = ordinaryDifferentialEquation(acceleration[i], movement[i], velocity[i], k, b);

		vPrime = velocity[i] + (temp) * h;
		xPrime = movement[i] + (((vPrime + velocity[i]) / 2) * h);

		vi = ordinaryDifferentialEquation(acceleration[i], movement[i], velocity[i], k, b) + ordinaryDifferentialEquation(acceleration[i + 1], xPrime, vPrime, k, b);
		vi = velocity[i] + (vi / 2) * h;
		velocity[i+1] = vi;

		xi = movement[i] + ((velocity[i] + velocity[i+1]) / 2) * h;
		movement[i + 1] = xi;
	}


	string name;
	cout << "Please enter the name of the file to output: ";
	cin >> name;


	out.open(name);
	for (int i = 0; i < size-1; i++) {

		out << time[i] << "   ";
		out << displacement[i] << "   ";
		out << acceleration[i] << "   ";
		out << movement[i] << "   ";
		out << velocity[i] << "\n";
	}
	out.close();
	return 0;
}



/******************************************************************************************************
 Function Name:  ordinaryDifferentialEquation
       Purpose:  Calculates the value of the ordinary differential equation provided in the
	             assignment instructions. 
 In Parameters:  double a ~ the acceleration value
			     double x ~ the movement value
				 double v ~ the velocity value
				 double k ~ constant value of 20.0
				 double b ~ constant value 10.0
Out Parameters:  double ~ returns the result of the ODE, in double format
       Version:  1.0
        Author:  Nathan M. Abbey
*******************************************************************************************************/
double ordinaryDifferentialEquation(double a, double x, double v, double k, double b) {

	double result = a - (k *x) - (b*v);
	return result;
}