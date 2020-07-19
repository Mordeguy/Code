#ifndef PROJ_H_
#define PROJ_H_



#define NUM_STATES 13



/*
 *
 * 1. ENUM data type to handle all 13 states
 * 2. ENUM data type for handling door's status
 * 3. ENUM data type used for Boolean value
 */






// 1. ENUM data type that hadles the 13 states for the function pointer. -----|
typedef enum {
	INITIAL_STATE =		 0,
	RIGHT_SIDE_SCAN = 	 1,
	LEFT_SIDE_SCAN = 	 2,
	GUARD_RIGHT_UNLOCK = 	 3,
	RIGHT_OPEN = 		 4,
	WEIGHT_SCALE = 		 5,
	RIGHT_CLOSED = 		 6,
	GUARD_RIGHT_LOCK = 	 7,
	GUARD_LEFT_UNLOCK = 	 8,
	LEFT_OPEN = 		 9,
	LEFT_CLOSE = 		 10,
	GUARD_LEFT_LOCK = 	 11,
	EXIT = 			 12
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
	TRUE,
	FALSE
};



// Struct used to send the values to assign1_display.c
struct program_status {
	int leftDoor, rightDoor;			// Variables to hold the state of the 2 doors.
	int leftLock, rightLock;			// Variables to hold the state of the 2 door locks
	int waitingLeft, waitingRight;			// Variables to indicate if someone is waiting to come in
	person p;
} typedef programStatus;



struct person_info {
	int weight, person_id				// Variables to hold person's weight and ID.
	int currentState;				// The current state of the FSM.
} typedef person;












#define NUM_INPUTS 5
typedef enum {
	LEFT_BUTTON_DOWN = 0,
	LEFT_BUTTON_UP = 1,
	RIGHT_BUTTON_DOWN = 2,
	RIGHT_BUTTON_UP = 3,
	STOP_BUTTON = 4
} Input;

#define NUM_OUTPUTS 8
typedef enum {
	START_MSG = 0,
	READY_MSG = 1,
	LEFT_DOWN_MSG = 2,
	RIGHT_DOWN_MSG = 3,
	ARMED_MSG = 4,
	PUNCH_MSG = 5,
	EXIT_MSG = 6,
	STOP_MSG = 7
} Output;

const char *outMessage[NUM_OUTPUTS] = {
		"Start Message",
		"Ready.",
		"Left Button Down = Press Right Button to Arm Press",
		"Right Button Down - Press Left Button to Arm Press",
		"DANGER - Press Armed",
		"Punching.",
		"Exiting.",
		"Stop Message."
};

const char *inMessage[NUM_INPUTS] = {
		"LD",
		"LU",
		"RD",
		"RU",
		"S"
};
#endif /* PROJ_H_ */
