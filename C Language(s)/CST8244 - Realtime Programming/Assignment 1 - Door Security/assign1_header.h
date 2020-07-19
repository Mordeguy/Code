#ifndef PROJ_H_
#define PROJ_H_

/*********************************************************************************************|
 * 	File Name:	assign1.h
 *	  Purpose:  Hold and define the data structures tp be used for Assignment 1.
 *     Author:  Nathan M. Abbey + Billy Saint-Fort
 *       Date:  October 10, 2018
 *      Steps:
 *   			1.  ENUM data type to handle all 13 states
 *   			2.  ENUM data type for handling door's status
 *   			3.  ENUM data type used for Boolean value
 *   			4.  Struct used to send the values to assign1_display.c
 *   			5.  Struct to hold person's information when scanning
 *   			6.  Holds a pointer to a char array holding the output messages
 *   			7.  Holds a pointer to a char array holding input commands
 *
 ********************************************************************************************/


// Defines for simpifying code
#define NUM_STATES 			13
#define NUM_DOOR_STATES 	4
#define NUM_BOOLEAN 		2
#define NUM_OUTPUTS			15
#define NUM_INPUTS			13



// 1. ENUM data type that handles the 13 states for the function pointer. -----|
typedef enum {
	INITIAL_STATE =		 0,
	RIGHT_SIDE_SCAN = 	 1,
	LEFT_SIDE_SCAN = 	 2,
	GUARD_RIGHT_UNLOCK = 3,
	RIGHT_OPEN = 		 4,
	WEIGHT_SCALE = 		 5,
	RIGHT_CLOSED = 		 6,
	GUARD_RIGHT_LOCK = 	 7,
	GUARD_LEFT_UNLOCK =  8,
	LEFT_OPEN = 		 9,
	LEFT_CLOSED= 		10,
	GUARD_LEFT_LOCK = 	11,
	EXIT = 			    12
} State;



// 2. ENUM data type for handling the current door's status ------------------|
enum door_states {
	LOCKED = 		 0,
	UNLOCKED =		 1,
	OPEN = 			 2,
	CLOSED = 		 3
};



// 3. ENUM type for for using BOOLEAN ----------------------------------------|
enum boolean {
	TRUE =			 0,
	FALSE=			 1
};



// 4. Struct to hold person's information when scanning ----------------------|
struct person_info {
	int weight, person_id;				// Variables to hold person's weight and ID.
	int currentState;					// The current state of the FSM.
} typedef person;



// 5. Struct used to send the values to assign1_display.c --------------------|
struct program_status {
	int messageIndex;
	person p;							// Variable to hold the person info
} typedef programStatus;



// 6. Holds a pointer to a char array holding the output messages ------------|
const char *outMessage[NUM_OUTPUTS] = {
		"Waiting for Person...",			// 0
		"Person scanned ID, ID = ",			// 1
		"Person scanned ID, ID = ",			// 2
		"Right door unlocked by guard",		// 3
		"Person opened right door",			// 4
		"Person weighed, Weight = ",		// 5
		"Person closed right door.",		// 6
		"Right door locked by guard",		// 7
		"Left door unlocked by guard",		// 8
		"Person opened left door",			// 9
		"Person closed left door",			// 10
		"Left door locked by guard",		// 11
		"Exiting..."						// 12
};



// 7. Holds a pointer to a char array holding input commands ----------------|
const char *inMessage[NUM_INPUTS] = {
		"E",	// 0
		"rs",	// 1
		"ls",	// 2
		"gru",	// 3
		"ro",	// 4
		"ws",	// 5
		"rc",	// 6
		"grl",  // 7
		"glu",  // 8
		"lo",   // 9
		"lc",   // 10
		"gll",  // 11
		"exit"  // 12
};



#endif /* PROJ_H_ */
