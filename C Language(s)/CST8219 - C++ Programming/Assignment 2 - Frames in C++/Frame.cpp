/* FILE HEADER ------------------------------------------------------------------------------------------
             File Name:  Frame.cpp
               Version:  1.0
   Author/Student Name:  Nathan M. Abbey
        Student Number:  040 - 557 - 192
Course Name and Number:  CST8233 ~ Numerical Computing
           Lab Section:  301
     Assignment Number:  01
       Assignment Name:  Animation Project C++
              Due Date:  October 14, 2017 - 23:59
       Submission Date:  October 14, 2017 - 17:00
       Professors Name:  Mr. Andrew Tyler
               Purpose:  This is a Frame object which contains a file name and a pointer to the
			             next Frame in a linked list.
-------------------------------------------------------------------------------------------------------*/
#include "Frame.h"
#include "Animation.h"


using namespace std;


/************************************************************************************
 Function Name:  Frame
       Purpose:  Default constructor; initializes a Frame object's variables
 In Parameters:  None
Out Parameters:  An initialized Frame object
       Version:  1.0
        Author:  Nathan M. Abbey
************************************************************************************/
Frame::Frame(void)
{
	fileName = nullptr;
	pNext = nullptr;	
}


/************************************************************************************
 Function Name:  Animation
       Purpose:  Default constructor that initializes the 2 Frame objects
 In Parameters:  None
Out Parameters:  An empty frame object
       Version:  1.0
        Author:  Nathan M. Abbey
************************************************************************************/
Frame::~Frame()
{
	delete[] fileName;
}