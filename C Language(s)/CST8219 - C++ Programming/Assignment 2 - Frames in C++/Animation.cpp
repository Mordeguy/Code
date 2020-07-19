/* FILE HEADER ------------------------------------------------------------------------------------------
             File Name:  Animation.cpp
               Version:  01
   Author/Student Name:  Nathan M. Abbey
        Student Number:  040 - 557 - 192
Course Name and Number:  CST8219 ~ C++ Programming
           Lab Section:  301
     Assignment Number:  01
       Assignment Name:  Animation Prpject in C++
              Due Date:  October 14, 2017 - 23:59
       Submission Date:  October 14, 2017 - 17:00
       Professors Name:  Mr. Andrew Tyler
               Purpose:  This is a linked list of object Frames. A user is able to manipulate the 
			             list of Frames by:
						           1. Adding a Frame to the list wherever you choose
								   2. Displaying the list of Frames in 1 second intervals
								   3. Deleting the list of Frames
-------------------------------------------------------------------------------------------------------*/
#define _CRT_SECURE_NO_WARNINGS

#include "Frame.h"
#include "Animation.h"
#include <iostream>
#include <string>
#include <ctime>


using namespace std;

/************************************************************************************
 Function Name:  Animation
       Purpose:  Default constructor that initializes the 2 Frame objects with 
	             values: nullptr
 In Parameters:  None
Out Parameters:  An initialized Animation object
       Version:  1.0
        Author:  Nathan M. Abbey
************************************************************************************/
Animation::Animation() {
	frames = NULL;
}


/***********************************************************************************
 Function Name:  ~Anitmation
       Purpose:  This is the destructor which de-allocates dynamic memory allocated
	             for the program
 In Parameters:  None
Out Parameters:  A deleted Animation object
       Version:  1.0
        Author:  Nathan M. Abbey
***********************************************************************************/
Animation::~Animation() {
	delete(frames);

}


/***********************************************************************************
 Function Name:  InsertFrame
       Purpose:  Dynamically allocates memory for a frame and enables user to insert
	             the new Frame in the list wherever. 
 In Parameters:  None
Out Parameters:  None
       Version:  1.0
        Author:  Nathan M. Abbey
***********************************************************************************/
void Animation::InsertFrame() {

	char array[300];
	int insert, size = 0;
	Frame *f = new Frame();
	Frame *f2 = frames;

	cout << "Insert a frame in the Animation\n" << endl;
	cout << "Please enter the file name: ";
	cin >> array;

	int nameLength = (strlen(array) + 1);
	char * temp = new char[nameLength];
	strcpy(temp, array);
	f->GetfileName() = temp;

	while (f2 != nullptr) {
		size++;
		f2 = f2->GetpNext();
	}

	if (size == 0) {
		cout << "This is the first frame in the list" << endl;
		frames = f;
		return;
	}

	bool moveOn;
	do {
		moveOn = true;
		cout << "There are " << size << " frame(s) in the list. Please specify the position (<= " << size << " to insert at: ";
		cin >> insert;

		if (insert < 0 || insert > size) {
			cout << "Retry with a valid index please." << endl;
			moveOn = false;
		}
	} while (moveOn == false);

	if (insert == 0) {
		f->GetpNext() = frames;
		frames = f;
		return;
	}

	f2 = frames;
	for (int i = 0; i < insert - 1; i++) {
		f2 = f2->GetpNext();
	}

	f->GetpNext() = f2->GetpNext();
	f2->GetpNext() = f;
}


/***********************************************************************************
 Function Name:  DeleteFrames
       Purpose:  This deallocates all of the memory for the Frame object(s)
 In Parameters:  None
Out Parameters:  None
       Version:  1.0
        Author:  Nathan M. Abbey
***********************************************************************************/
void Animation::DeleteFrames() {

	if (frames != NULL) {
		cout << "Delete all the Frames from the animation." << endl;
	}

	Frame *f = frames;
	while (frames != nullptr) {
		f = frames->GetpNext();
		delete(frames);
		frames = f;
	}
}


/***********************************************************************************
 Function Name:  RunFrames
       Purpose:  Runs through the Frames in the list at 1 second intervals and 
	             prints out the Frames objects on to the screen.
 In Parameters:  Frames
Out Parameters:  None
       Version:  1.0
        Author:  Nathan M. Abbey
***********************************************************************************/
void Animation::RunFrames() {

	int counter = 0;
	time_t startsec, oldsec, newsec;
	Frame *f = frames;

	if (f == 0) {
		cout << "No frames in the animation\n" << endl;
		return;
	}
	cout << "Run the Animation\n" << endl;
	startsec = oldsec = time(NULL);

	while (f) {
		newsec = time(NULL);

		if (newsec > oldsec) {
			cout << "Frame " << counter++ << ", time = " << newsec - startsec << " sec" << endl;
			cout << "Image file name = " << f->GetfileName() << endl;
			f = f->GetpNext();
			oldsec = newsec;
		}
	}
}