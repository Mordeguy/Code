/* FILE HEADER ------------------------------------------------------------------------------------------
             File Name:  Animation.cpp
               Version:  2.0
   Author/Student Name:  Nathan M. Abbey
        Student Number:  040 - 557 - 192
Course Name and Number:  CST8219 - C++ Programming
           Lab Section:  301
     Assignment Number:  02
       Assignment Name:  Animation Project C++ Using STL Container Classes and Overloaded Operators
              Due Date:  December 9, 2017 - 23:59
       Submission Date:  December 9, 2017 - 15:00
       Professors Name:  Mr. Andrew Tyler
               Purpose:  This class runs the part of the program the user interacts with. It handles the
					     inserting, deleting, retrieving, and displaying of the Frame objects.
-------------------------------------------------------------------------------------------------------*/
#define _CRT_SECURE_NO_WARNINGS

using namespace std;
#include "Frame.h"
#include "Animation.h"
#include "Display.h"
#include <iostream>
#include <string>
#include <ctime>
#include <vector>



/******************************************************************************************************
 Function Name:  InsertFrame
       Purpose:  Prompts the user for a fileName, number of displays, x and y coordinates, duration,
	             and display name. It takes these values and creates a Frame that contains a Display
				 vector, which is then saved on the heap. If there is more than 2 Frames in the
				 forward_list, the user has the option of choosing which Frame to place the new Frame
				 after.
 In Parameters:  None
Out Parameters:  None
       Version:  1.0
        Author:  Nathan M. Abbey
*******************************************************************************************************/
void Animation::InsertFrame() {

	char array[300];
	string s;
	int x = 0, y = 0, time = 0;
	int displaysNo = 0;
	vector<Display> displays;

	cout << "Insert a frame in the Animation" << endl;
	cout << "Please enter the Frame file name: ";
	cin >> s;
	
	cout << "Entering the Frame Displays (the sets of dimensions and durations)" << endl;
	cout << "Please enter the number of displays: ";
	cin >> displaysNo;

	for (int i = 0; i < displaysNo; i++) {
		char * temp2 = nullptr;

		cout << "Please enter pixel x for Display # " << i << " pixel_x: ";
		cin >> x;
		cout << "Please enter pixel y for Display # " << i << " pixel_y: ";
		cin >> y;
		cout << "Please enter the duration for this display: ";
		cin >> time;
		cout << "Please enter the name for this Display: ";
		cin >> array;

		int nameLength = (strlen(array) + 1);
		temp2 = new char[nameLength];
		strcpy(temp2, array);
		nameLength = 0;
		
		Display d(x, y, time, temp2);
		displays.push_back(d);
		delete(temp2);		
	}

	if (frames.empty()) 
		cout << "This is the first Frame in the list" << endl;

	forward_list<Frame> ::iterator it;

	int count = 0;
	for (it = frames.begin(); it != frames.end(); ++it) {

		count++;
	}

	if (count > 1) {
		int position = 0;

		cout << "There are " << count << " Frame(s) in the list" << endl;
		cout << "Please specify the position, between 0 and " << (count - 1) << " to insert after: ";
		scanf("%d", &position);

		count = 0;
		for (it = frames.begin(); it != frames.end(); ++it) {
			
			if (count == position) {

				Frame f(s, displays);
				frames.insert_after(it, f);
				cout << endl;
				return;
			}
			count++;
		}
	}
	else {

		Frame f(s, displays);
		frames.reverse();
		frames.push_front(f);
		frames.reverse();
		cout << endl;
	}	
}



/***********************************************************************************
 Function Name:  DeleteFrames
       Purpose:  Clears the Frames forward list and sets index to 0.
 In Parameters:  None
Out Parameters:  None
       Version:  2.0
        Author:  Nathan M. Abbey
***********************************************************************************/
void Animation::DeleteFrames() 
{
	frames.clear();
	
}



/******************************************************************************************************
 Function Name:  operator[]
       Purpose:  Overloaded constructor for the '[]' operator. This allows the use to use square
	             brackets to treat the Animation Frame object as if it were an array.
 In Parameters:  unsigned in num ~ The index number to retrieve from the Frame forward list.
Out Parameters:  Frame& ~ The frame object address that is the index that the user input. 
       Version:  1.0
        Author:  Nathan M. Abbey
*******************************************************************************************************/
Frame& Animation::operator[](unsigned int num)
{
	int i = 0;
	forward_list<Frame> ::iterator it;
	for (it = frames.begin(); it != frames.end(); ++it) {

		if (i == num) {
			return *it;
		}
		i++;
	}
}



/******************************************************************************************************
 Function Name:  operator+=
       Purpose:  Overloaded constructor for the '+=' operator. This allows the user to put a Frame on
	             an Animation object by using the forward_list method push_front().
 In Parameters:  Frame& ~ The frame object address that is the index that the user input. 
Out Parameters:  None
       Version:  1.0
        Author:  Nathan M. Abbey
*******************************************************************************************************/
void Animation::operator+=(Frame &f)
{
	frames.push_front(f);
}




/******************************************************************************************************
 Function Name:  operator<<
       Purpose:  Overloaded constructor for the '<<' operator. This formats the output for the Animation
                 object whenever the Animation object(s) are output with the << operator.
 In Parameters:  ostream &os ~ The address location for the output stream
                 Animation &a ~ The address of a Animation object to be used for output
Out Parameters:  ostream& ~ Returns the address to the output stream
       Version:  1.0
        Author:  Nathan M. Abbey
*******************************************************************************************************/
ostream& operator<<(ostream& os, Animation& a)
{
	forward_list<Frame> ::iterator it;
	int i = 0;

	os << "\nAnimation " << a.name << "\nRun the Animation" << endl;
	for (it = a.frames.begin(); it != a.frames.end(); ++it) {		
		os << "Frame # " << i << endl;
		os << *it << endl;
		i++;
	}
	os << "Output finished" << endl;
	return os;
}