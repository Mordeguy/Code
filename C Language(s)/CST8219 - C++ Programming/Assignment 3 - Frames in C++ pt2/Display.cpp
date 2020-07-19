/* FILE HEADER ------------------------------------------------------------------------------------------
             File Name:  Display.cpp
               Version:  01
   Author/Student Name:  Nathan M. Abbey
        Student Number:  040 - 557 - 192
Course Name and Number:  CST8219 ~ C++ Programming
           Lab Section:  301
     Assignment Number:  02
       Assignment Name:  Animation Project in C++ Using STL Container Classes and Overloaded Operators
              Due Date:  December 09, 2017 - 23:59
       Submission Date:  December 08, 2017 - 15:00
       Professors Name:  Mr. Andrew Tyler
               Purpose:  This class handles the Display objects. There is a default constructor that 
			             initializes the objects' variables, a copy constructor for use when copying the 
						 Display object to the vector, an overloaded operator function for '<<' which 
						 formats the output from the Display object, and a deconsructor to free any memory 
						 allocated once it is no longer needed.

---------------------------------------------------------------------------------------------------------------*/
#define _CRT_SECURE_NO_WARNINGS

using namespace std;
#include <iostream>
#include <time.h>
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
Display::Display(int x, int y, int duration, char* name)
{	
	int nameLength = strlen(name);
	char * temp = new char[nameLength + 1];
	strcpy(temp, name);

	pixel_x = x;
	pixel_y = y;
	this->duration = duration;
    this->name = temp;
}



/******************************************************************************************************
 Function Name:  Display
       Purpose:  Copy constructor; copies a Display object and replicates it
 In Parameters:  const Display &d ~ the address of a Display object that wiull be copied
Out Parameters:  A Display object sharing the same data as the copied Display object fed in
       Version:  1.0
        Author:  Nathan M. Abbey
*******************************************************************************************************/
Display::Display(const Display &d) 
{
	int nameLength = strlen(d.name);
	char * temp = new char[nameLength + 1];
	strcpy(temp, d.name);

	pixel_x = d.pixel_x;
	pixel_y = d.pixel_y;
	this->duration = d.duration;
	this->name = temp;
}



/******************************************************************************************************
 Function Name:  ~Display
       Purpose:  Deletes the dynamically allocated memory for 'name' when no longer needed,
 In Parameters:  None
Out Parameters:  A Display object with the dynamic data un-allocated
       Version:  1.0
        Author:  Nathan M. Abbey
*******************************************************************************************************/
Display::~Display() 
{
	delete this->name;
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
	int i = 0;

	os << "name = " << d.name << "; pixel_x = " << d.pixel_x << "; pixel_y = " << d.pixel_y << "; duration =  " << d.duration << endl;
	os << "\tCounting the seconds for this Display: ";

	startsec = oldsec = time(NULL);
	while (i < d.duration) {
		newsec = time(NULL);

		if (newsec > oldsec) {
			cout << i + 1 << ", ";
			oldsec = newsec;
			i++;
		}
	}
	return os;
}
