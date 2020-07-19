/* FILE HEADER ------------------------------------------------------------------------------------------
             File Name:  Animation.cpp
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
               Purpose:  This class runs the part of the program the user interacts with. It handles the
                         inserting, deleting, retrieving, and displaying of the Frame objects.
-------------------------------------------------------------------------------------------------------*/
#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
#include <string>
#include <vector>
#include <forward_list>

using namespace std;
#include "Display.h"
#include "SystemMemoryDisplay.h"
#include "GPUMemoryDisplay.h"
#include "Frame.h"
#include "Animation.h"

/******************************************************************************************************
 Function Name:  InsertFrame
       Purpose:  Prompts the user for a fileName, number of displays, x and y coordinates, duration,
                 and display name. It takes these values and creates a Frame that contains a Display
                 vector, which is then saved on the heap. The vector contains dynamically casted
				 GPUMemoryDisplay and SystemMemoryDisplay displays to their base-class Display.
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
	int displayType = 0;
	vector<Display*> displays;
	Display* d1;

	cout << "Insert a frame in the Animation" << endl;
	cout << "Please enter the Frame filename: ";
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
		cout << "Please enter the duration for this Display: ";
		cin >> time;
		cout << "Please enter the name for this Display: ";
		cin >> array;
		cout << "Please etner the type for this Display (1 = SystemMemoryDisplay, 2 = GPUMemoryDisplay):  ";
		cin >> displayType;

		if (displayType == 1) 
			cout << "\n";

		int nameLength = (strlen(array) + 1);
		temp2 = new char[nameLength];
		strcpy(temp2, array);
		nameLength = 0;
		
		if (displayType == 1) {
			displays.push_back(new SystemMemoryDisplay(x, y, time, temp2));
		}
	
		if (displayType == 2) {
			string temp;	
			cout << "Please enter the file name for the associated GPU shader: ";
			cin >> temp;
			cout << "\n";

			displays.push_back(new GPUMemoryDisplay(x, y, time, temp2, temp));
		}
	}


	if (frames.empty())
		cout << "This is the first Frame in the list" << endl;

	forward_list<Frame>::iterator it;

	int count = 0;
	for (it = frames.begin(); it != frames.end(); ++it) {
		count++;
	}

	if (count > 1) {
		int position = 0;

		cout << "\nThere are " << count << " Frame(s) in the list" << endl;
		cout << "Please specify the position, between 0 and " << (count) << " to insert after: ";
		scanf("%d", &position);

		count = 0;
		for (it = frames.begin(); it != frames.end(); ++it) {

			if (count == position) {
				Frame *f = new Frame(s, displays);
				frames.insert_after(it, *f);
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
		os << "Frame # "<< i << ":" << *it << endl;
		i++; 
	}
	os << "Output finished" << endl;
	return os;
}