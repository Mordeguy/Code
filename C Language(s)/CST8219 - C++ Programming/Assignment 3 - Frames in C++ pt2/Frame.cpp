/* FILE HEADER ------------------------------------------------------------------------------------------
             File Name:  Frame.cpp
               Version:  1.0
   Author/Student Name:  Nathan M. Abbey
        Student Number:  040 - 557 - 192
Course Name and Number:  CST8219 - C++ Programming
           Lab Section:  301
     Assignment Number:  02
       Assignment Name:  Animation Project C++ Using STL Container Classes and Overloaded Operators
              Due Date:  December 9, 2017 - 23:59
       Submission Date:  December 8, 2017 - 15:00
       Professors Name:  Mr. Andrew Tyler
               Purpose:  This class handles the two overloaded operators the Frame object uses. 
						 1.  <<
						 2.  +
-------------------------------------------------------------------------------------------------------*/
using namespace std;
#include <string>
#include <iostream>
#include <vector>
#include "Display.h"
#include "Frame.h"
#include "Animation.h"



/******************************************************************************************************
 Function Name:  operator<<
       Purpose:  Overloaded constructor for the '<<' operator. This formats the output for the Frame
                 object whenever the Frame is output with the << operator.
 In Parameters:  ostream &os ~ The address location for the output stream
                    Frame &f ~ The address of a Frame object to be used for output
Out Parameters:  ostream& ~ Returns the address to the output stream
       Version:  1.0
        Author:  Nathan M. Abbey
*******************************************************************************************************/
ostream& operator<<(ostream& os, Frame& f) 
{
	os << "\tfileName = " << f.fileName << endl;
	for (int i = 0; i < f.displays.size(); i++) {
		os << "\tDisplay # " << i << ":\t" << f.displays[i] << endl;
	}
	return os;
}



/******************************************************************************************************
 Function Name:  operator+
       Purpose:  This overrides the '+' operator and allows adding a frame to the vector by using the
  	             operator.
 In Parameters:  Frame &f ~ A Frame object to be added to the forward list.
Out Parameters:  Frame ~ Returns the new Frame that has been created.
       Version:  1.0
        Author:  Nathan M. Abbey
*******************************************************************************************************/
Frame Frame::operator+(Frame &f)
{
	string name = fileName + "_" + f.fileName;
	int a = f.displays.size();
	int i = 0;
	Frame temp(name, displays);

	for (; i < a; i++) {
		Display d = f.displays[i];
		temp.displays.push_back(d);
	}
	return temp;
}