#include <stdio.h>
#include <stdlib.h>
#include <stdio.h>
#include <stdlib.h>
#include <process.h>
#include <sys/neutrino.h>
#include <sys/netmgr.h>
#include <process.h>
#include <string.h>
#include <errno.h>
#include <pthread.h>
#include <ctype.h>
#include "assign1_header.h"


/*******************************************************************************************************************|
 * 	File Name:	assign1_controller.c
 *	  Purpose:	Runs the state machine and sends values to display, then returns values to inputs
 *     Author:  Nathan M. Abbey + Billy SaintFort
 *       Date:  October 10, 2018
 *
 *    Methods:  I. main(int argc, char* argv[])
 *
 *******************************************************************************************************************/


// Function Prototypes ----------|
typedef void *(*StateFunc)();
void *initial_stage();
void *leftSide_Scan();
void *rightSide_Scan();
void *weight_scale();
void *left_open();
void *right_open();
void *left_close();
void *right_close();
void *guardLeft_unlock();
void *guardRight_unlock();
void *guardLeft_lock();
void *guardRight_lock();
void *exit_program();


// Global Variables -------------|
int     rcvid;
int 	rcvid2;
int     chid;         				// The channel ID (server establishes a Channel for msgs)
int 	side;
int connection_id;
double weight;
char person_id[10];
StateFunc statefunc = initial_stage;
programStatus status;
programStatus receiving_status;
person personOutput;
person personInput;


/*
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
 * */

/* *************************************************************************************************|
 * 	Method:  main
 *
 *   Steps:  1. Create a StateFunc object for use with the function pointer
 * 			 2. Create a channel for communication and returns the Child ID
 * 			 3. If channel creation fails it will go to -1 and end
 * 			 4. Server begins in an infinite loop, awaiting input
 * 			 5. Uses the statefunc to point the function pointer to appropriate function
 * *************************************************************************************************/
int main(int argc, char* argv[]){

	//Grab server pid from command line to connect with server in display
	pid_t serverpid = atoi(argv[1]);

	//Establish connection with display server
	connection_id = ConnectAttach(ND_LOCAL_NODE, serverpid, 1, _NTO_SIDE_CHANNEL, 0);
	if (connection_id == -1){
		fprintf(stderr, "Couldn't connect to server\n");
		perror (NULL);
		exit (EXIT_FAILURE);
	}
	//Print PID
	printf("The controller is running as process_id: %d\n", getpid());


	//Send status to display according to state machine when rdy

	// 2. Create a channel and returns the Child ID ~~~~~~~~~~~~~~~~~~~~~~~~~
	chid = ChannelCreate (2);
					   //(0) -  changes how channel notifies the calling THREAD. For now, use 0.



	printf("%d", getpid());


	// 3. If it fails it will go to -1 and end ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	if (chid == -1) {
		perror("failed to create the channel.");
		exit (EXIT_FAILURE);
	}


	// 4. Server begins in an infinite loop, awaiting input ~~~~~~~~~~~~~~~~~
	do {

		// 4. Gets the person's id and assigns it to person_id ~~~~~~~~~~
		rcvid = MsgReceive (chid, &personInput, sizeof (personInput), NULL);
		/*              chid --> The channel ID for the comm channel
		 *         person_id --> A buffer to receive msg in
		 * sizeof(person_id) --> The size of buffer to receive in
		 *              NULL --> Don't worry about for now...
		 *
		 * Returns a receiver ID (rcvid) used to reply or -1 for ERROR.
		 *  - rcvid gets the ID of the client so you can send a msg back
		 * */

		// 5. Uses the statefunc to point the function pointer to appropriate function
		statefunc = (StateFunc)(*statefunc)();

		// 6. Send status to display
		//Send status to display
		if (MsgSend(connection_id, &status, sizeof(status), &receiving_status, sizeof(receiving_status)) == - 1)
		{
			/*                 coid --> The connection ID received in ConnectAttach()
			 *         personOutput --> Pointer to a send buffer containing Person object
			 * sizeof(personOutput) --> Size of the send buffer
			 *          personInput --> Pointer to a receive buffer filled w/ Person object
			 *  sizeof(personInput) --> Size of the receiver buffer
			 * */

			 			fprintf(stderr, "Error during MsgSend in controller to display\n");
			 			perror(NULL);
			 			exit(EXIT_FAILURE);
			 }
	    //Receive from controller
			 rcvid2 = MsgReceive (connection_id, &receiving_status, sizeof(receiving_status), NULL);


			 //Send to input	for confirmation of receiving input
			 personOutput.currentState = receiving_status.messageIndex;
			 personOutput.person_id =  receiving_status.p.person_id;
			 personOutput.weight = receiving_status.p.weight;
			 MsgReply (rcvid, EOK, &personOutput, sizeof(personOutput));
		} while (personOutput.currentState != EXIT);
	printf("\nExiting Controller!");
	ChannelDestroy(chid);
	return EXIT_SUCCESS;
}

// Initial stage will jump to left-scan or right-scan depending on input
	void *initial_stage(){

	if (personInput.currentState == RIGHT_SIDE_SCAN){
		statefunc = rightSide_Scan;
		side = RIGHT_SIDE_SCAN;
		rightSide_Scan();
	}
	else if (personInput.currentState == LEFT_SIDE_SCAN)
	{  statefunc = leftSide_Scan;
	   side = LEFT_SIDE_SCAN;
	   leftSide_Scan();
	}
	else if(personInput.currentState != 1 && personInput.currentState != 2)
	{
		status.messageIndex = EXIT;
		statefunc = exit_program;
	}
	status.p = personInput;

	return statefunc;
}
/* III ********************************************************************************************|
 *   Function:  rightSide_scan()
 *     Author:	Nathan M. Abbey
 *
 *    Purpose:  Stage that awaits the guard to unlock the right door.
 *
 * ************************************************************************************************/
void *rightSide_Scan(){
	status.messageIndex = personInput.currentState;
	status.p = personInput;
	statefunc = guardRight_unlock;
	return statefunc;
}
/* IV *********************************************************************************************|
 * 	 Function:  leftSide_scan()
 *     Author:	Nathan M. Abbey
 *
 *    Purpose:  Stage that awaits the guard to unlock the left door.
 *
 * ************************************************************************************************/
void *leftSide_Scan(){
	status.messageIndex = personInput.currentState;
	status.p = personInput;
	statefunc = guardLeft_unlock;
	return statefunc;
}

/* VI *******************************************************************************************|
 * 	 Function:  right_open()
 *     Author:	Nathan M. Abbey
 *
 *    Purpose:  Stage that awaits the guard the right door to close.
 *
 * **********************************************************************************************/
void *right_open(){
	if(personInput.currentState != RIGHT_OPEN)
	{
		status.messageIndex = EXIT;
		statefunc = exit_program;
	}
	else
	{
		status.messageIndex = personInput.currentState;
		status.p = personInput;
		//Update function pointer
		if(side == RIGHT_SIDE_SCAN)
		{
			statefunc = weight_scale;
		}
		else
		{
			statefunc = right_close;
		}
	}

	return statefunc;
}

/* VII ******************************************************************************************|
 * 	 Function:  weight_scale()
 *     Author:	Nathan M. Abbey
 *
 *    Purpose:  Weighs and send to close the left door.
 *
 * **********************************************************************************************/
void *weight_scale()
{
	if(personInput.currentState != WEIGHT_SCALE)
	{
		status.messageIndex = EXIT;
		statefunc = exit_program;
	}
	else
	{
		status.messageIndex = personInput.currentState;
		status.p = personInput;

		if(side == RIGHT_SIDE_SCAN){
			statefunc = right_close;
		}
		else
		{
			statefunc = left_close;
		}
	}
	return statefunc;
}
void *left_open(){
	if(personInput.currentState != LEFT_OPEN)
	{
		status.messageIndex = EXIT;
		statefunc = exit_program;
	}
	else
	{
		//Update state of program and send status to display
		status.messageIndex = personInput.currentState;
		status.p = personInput;
		//Update Function pointer
		if(side == LEFT_SIDE_SCAN){
			statefunc = weight_scale;
		}
		else
		{
			statefunc = left_close;
		}
	}
	return statefunc;
}
void *left_close(){
	if(personInput.currentState != LEFT_CLOSED)
	{
		status.messageIndex = EXIT;
		statefunc = exit_program;
	}
	else
	{
		//Update state of program and send status to display
		status.messageIndex = personInput.currentState;
		status.p = personInput;
		//Update Function pointer
		statefunc = guardLeft_lock;
	}
	return statefunc;
}
void *right_close(){
	if(personInput.currentState != RIGHT_CLOSED)
	{
		status.messageIndex = EXIT;
		statefunc = exit_program;
	}
	else
	{
		//Update state of program and send status to display
		status.messageIndex = personInput.currentState;
		status.p = personInput;
		//Update Function pointer
		statefunc = guardRight_lock;
	}
	return statefunc;
}

void *guardLeft_unlock(){
	if(personInput.currentState != GUARD_LEFT_UNLOCK)
	{
		status.messageIndex = EXIT;
		statefunc = exit_program;
	}
	else
	{
		//Update state of program and send status to display
		status.messageIndex = personInput.currentState;
		status.p = personInput;
		//Update Function pointer
		statefunc = left_open;
	}
	return statefunc;
}
void *guardRight_unlock(){
	if(personInput.currentState != GUARD_RIGHT_UNLOCK)
	{
		status.messageIndex = EXIT;
		statefunc = exit_program;
	}
	else
	{
		//Update state of program and send status to display
		status.messageIndex = personInput.currentState;
		status.p = personInput;
		//Update Function pointer
		statefunc = right_open;
	}
	return statefunc;
}
void *guardLeft_lock(){
	if(personInput.currentState != GUARD_LEFT_LOCK)
	{
		status.messageIndex = EXIT;
		statefunc = exit_program;
	}
	else
	{
		//Update state of program and send status to display
		status.messageIndex = personInput.currentState;
		status.p = personInput;
		//Update Function pointer
		if(side == LEFT_SIDE_SCAN){
			statefunc = guardRight_unlock;
		}
		else{
			statefunc = initial_stage; /*Last state user can either exit or restart*/
		}
	}
	return statefunc;
}
void *guardRight_lock(){
	if(personInput.currentState != GUARD_RIGHT_LOCK)
	{
		status.messageIndex = EXIT;
		statefunc = exit_program;
	}
	else
	{
		//Update state of program and send status to display
		status.messageIndex = personInput.currentState;
		status.p = personInput;
		//Update Function pointer
		if(side == RIGHT_SIDE_SCAN){
			statefunc = guardLeft_unlock;
		}
		else{
			statefunc = initial_stage; /*Last state user can either exit or restart*/
		}
	}
	return statefunc;
}
void *exit_program(){
		status.messageIndex = personInput.currentState;
		status.p = personInput;
		return statefunc;
	}
