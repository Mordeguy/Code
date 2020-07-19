/* FILE HEADER ------------------------------------------------------------------------------------------
             File Name:  Display.cpp
               Version:  2.0
   Author/Student Name:  Nathan M. Abbey
        Student Number:  040 - 557 - 192
Course Name and Number:  CST8219 - C++ Programming
           Lab Section:  301
     Assignment Number:  03
       Assignment Name:  Animation Project in C++ using Polymorphic Inheritance and RTTI
              Due Date:  January 6, 2018 - 23:59
       Submission Date:  January 6, 2018 - 22:00
       Professors Name:  Mr. Andrew Tyler
               Purpose:  This class handles the display objects. It has an initial constructor, a copy
			             constructor and an overloaded operator (<<) for output.
-------------------------------------------------------------------------------------------------------*/
#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
#include <string>
#include <vector>
#include <forward_list>
#include<time.h>

using namespace std;
#include "Display.h"



/******************************************************************************************************
 Function Name:  Display
       Purpose:  Default constructor; initializes a Display object's variables
 In Parameters:  int x ~ x value
                 int y ~ y value
                 int duration ~ Length in seconds the animation will display in 1 second intervals
                 char * name ~ a pointer to be the name of the Display object
Out Parameters:  An initialized Display object
       Version:  1.0
        Author:  Nathan M. Abbey
*******************************************************************************************************/
Display::Display(int x, int y, int duration, char* name) {

	pixel_x = x;
	pixel_y = y;
	this->duration = duration;
	this->name = name;
}



/******************************************************************************************************
 Function Name:  Display
       Purpose:  Copy constructor; copies a Display object and replicates it
 In Parameters:  const Display &d ~ the address of a Display object that will be copied
Out Parameters:  A Display object sharing the same data as the copied Display object fed in
       Version:  1.0
        Author:  Nathan M. Abbey
*******************************************************************************************************/
Display::Display(const Display &d) {

	char *temp2;

	int nameLength = (strlen(d.name) + 1);
	temp2 = new char[nameLength];
	strcpy(temp2, d.name);

	pixel_x = d.pixel_x;
	pixel_y = d.pixel_y;
	this->duration = d.duration;
	this->name = temp2;
}






/******************************************************************************************************
 Function Name:  operator<<
       Purpose:  Overloaded constructor for the '<<' operator. This formats the output for the Display
                 object whenever the Display is output with the << operator.
 In Parameters:  ostream &os ~ The address location for the output stream
                 Display &d ~ The address of a Display object to be used for output
Out Parameters:  ostream& ~ Returns the address to the output stream
       Version:  1.0
        Author:  Nathan M. Abbey
*******************************************************************************************************/
ostream& operator<<(ostream& os, Display& d)
{
	time_t startsec, oldsec, newsec;
	startsec = oldsec = time(NULL);
	int i = 0;
	int count  = 1;


	os << "\t  name = " << d.name << "; pixel_x = " << d.pixel_x << ", pixel_y = " << d.pixel_y << ", duration = " << d.duration << endl;
	os << "\t  Counting the seconds for this display: ";

	while (count <= d.duration) {
		newsec = time(NULL);

		if (newsec > oldsec) {
			cout << count << ", ";

			oldsec = newsec;
			count++;
		}
	}

	os << "\n\t  Memory requirements = " << d.BufferSize() << " bytes" << endl;	
	return os;
}
