/* FILE HEADER ------------------------------------------------------------------------------------------
             File Name:  Frame.cpp
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
               Purpose:  This class handles the Frame objects. It has a copy constructor and an 
			             overloaded operator (<<) for outputing the data.
-------------------------------------------------------------------------------------------------------*/
#include <iostream>
#include <string>
#include <vector>


using namespace std;
#include "Display.h"
#include "SystemMemoryDisplay.h"
#include "GPUMemoryDisplay.h"
#include "Frame.h"




/******************************************************************************************************
 Function Name:  Frame.cpp
       Purpose:  Copy constructor; copies a Frame object and replicates it, dynamically casts it and 
	             places        
 In Parameters:  const Display &d ~ the address of a Display object that will be copied
Out Parameters:  A Display object sharing the same data as the copied Display object fed in
       Version:  2.0
        Author:  Nathan M. Abbey
*******************************************************************************************************/
Frame::Frame(const Frame& f) {

	GPUMemoryDisplay *gMD = 0;
	SystemMemoryDisplay *sMD = 0;
	vector<Display*> temp;

	vector<Display*>:: const_iterator it;
	
	for (it = f.displays.begin(); it != f.displays.end(); ++it) {

		gMD = dynamic_cast<GPUMemoryDisplay*>(*it);

		if (gMD == nullptr) {
			sMD = dynamic_cast<SystemMemoryDisplay*>(*it);
			temp.push_back(new SystemMemoryDisplay(*sMD));
		}
		else {
			temp.push_back(new GPUMemoryDisplay(*gMD));
		}
	}

	this->fileName = f.fileName;
	this->displays = temp;
}





/******************************************************************************************************
 Function Name:  operator<<
       Purpose:  Overloaded constructor for the '<<' operator. This formats the output for the Frame
                 object whenever the Frame is output with the << operator.
 In Parameters:  ostream &os ~ The address location for the output stream
                 Frame &f ~ The address of a Frame object to be used for output
Out Parameters:  ostream& ~ Returns the address to the output stream
       Version:  2.0
        Author:  Nathan M. Abbey
*******************************************************************************************************/
ostream& operator<<(ostream& os, Frame& f) {

	GPUMemoryDisplay *gMD = 0;
	SystemMemoryDisplay *sMD = 0;
	os << "\tfileName = " << f.fileName << endl;
	int i = 0;
	vector<Display*>::const_iterator it;

		for (it = f.displays.begin(); it != f.displays.end(); ++it) {

			gMD = dynamic_cast<GPUMemoryDisplay*>(*it);

			if (gMD == nullptr) {
				sMD = dynamic_cast<SystemMemoryDisplay*>(*it);
				os << "\t  Display # " << i << ": System Memory Display\n" << *sMD << endl;
			}
			else {
				os << "\t  Display # " << i << ": GPU Memory Display.  Shader = " << gMD->GetShader() << endl;
				os << *gMD << endl;
			}
			i++;
		}
	

	return os;
}


