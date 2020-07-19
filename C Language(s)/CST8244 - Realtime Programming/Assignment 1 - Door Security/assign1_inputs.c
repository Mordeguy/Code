#include "assign1_header.h"
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

/*******************************************************************************************************************|
 * 	File Name:	assign1_inputs.c
 *	  Purpose:
 *     Author:  Nathan M. Abbey
 *       Date:  October 10, 2018
 *      Steps:
 *
 *    Methods:  I. main(int argc, char* argv[])
 *
 *******************************************************************************************************************/



/* I-----------------------------------------------------------------------------------/
 *    Method: Main
 *   Purpose: Runs the mainline of the program. Presents a menu and sends messages to
 *            the server based on user input to menu.
 *   Returns: EXIT_SUCCESS on success, EXIT_FAILURE on error
 *
 * Variables:      char  choice[4]	-->   Used to accept menu input
 * 			             int  coid	-->   Connection ID
 * 			  person  personOutput	-->   Person object to hold variables for output
 * 			   person  personInput	-->   Person object to hold incoming variables
 * 			      pid_t  serverpid	-->   Used to obtain Server PID from command line
 * -----------------------------------------------------------------------------------*/
int main(int argc, char* argv[]) {

	char choice[4];
	int  coid;
	int rcvid;
	person personOutput;
	person personInput;
	pid_t serverpid = atoi(argv[1]);


	// 1. Establish a connection ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
	coid = ConnectAttach(ND_LOCAL_NODE, serverpid, 2, _NTO_SIDE_CHANNEL, 0);
	/*      ND_LOCAL_NODE --> Node descriptor running server; this for locally.
	 *          serverpid --> PID of server who owns comm channel
	 *			 	    1 --> Channel ID of the channel created; 1 for now.
	 *	_NTO_SIDE_CHANNEL --> Starting index to be used for the connection file desciptor
	 *	                0 --> FLags that contorl how connection works; 0 for now.
	 * */


	// 2. If 'coid' equals -1, something went wrong and END ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
	if (coid == -1) {
		fprintf(stderr, "Couldn't ConnectAttach\n");
		perror(NULL);
		exit(EXIT_FAILURE);
	}


	// 3. WHILE loop for keeping the program running before exiting. ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
	do {

		// 4. Prints out choice options and accepts input ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
		printf("Enter the event type\n(ls = left scan, rs = right scan, ws = weight scale, lo = left open,\n"
				"ro = right open, lc = left closed, rc = right closed, gru = guard right unlock, grl = guard \n"
				"right lock, gll = guard lock left, glu = guard left unlock, exit = exit the program\n" );

		scanf("%s", &choice);


		// 5. If input is "rs" accept person_id, set weigh to 0, and set currentState to 1 ~~~~~~~~~~~~~~|
		if (strcmp(choice, inMessage[1]) == 0){

			setvbuf (stdout, NULL, _IONBF, 0);
			printf("\nEnter the person ID: ");
			scanf("%d", &personOutput.person_id);
			personOutput.currentState = 1;
			personOutput.weight = 0;
		}

		// 6. If input is "ls" accept person_id, set weight to 0, and set currentState to 2 ~~~~~~~~~~~~~|
		else if (strcmp(choice, inMessage[2]) == 0)
		{
			setvbuf (stdout, NULL, _IONBF, 0);
			printf("\nEnter the person ID: ");
			scanf("%d", &personOutput.person_id);
			personOutput.currentState = 2;
			personOutput.weight = 0;
		}


		// 7. If input is "glu" set currentState to 3 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
		else if (strcmp(choice, inMessage[3]) == 0){
			personOutput.currentState = 3;
		}


		// 8. If input is "ro" accept person_id, sets currentState to 4 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
		else if (strcmp(choice, inMessage[4]) == 0){
			personOutput.currentState = 4;
		}


		// 9. If input is "ws" accept person's weight and set currentState to 5 ~~~~~~~~~~~~~~~~~~~~~~~~~|
		else if (strcmp(choice, inMessage[5]) == 0){
			setvbuf (stdout, NULL, _IONBF, 0);
			printf("\nEnter the person's weight: ");
			scanf("%d", &personOutput.weight);
			personOutput.currentState = 5;
		}


		// 10. If input is "rc" sets currentState to 6 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
		else if (strcmp(choice, inMessage[6]) == 0){
			personOutput.currentState = 6;
		}


		// 11. If input is "grl" set currentState to 7 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
		else if (strcmp(choice, inMessage[7]) == 0){
			personOutput.currentState = 7;
		}


		// 12. If input is "glu" set currentState to 8 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
		else if (strcmp(choice, inMessage[8]) == 0){
			personOutput.currentState = 8;
		}


		// 13. If input is "lo" set currentState to 9 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
		else if (strcmp(choice, inMessage[9]) == 0){
			personOutput.currentState = 9;
		}


		// 14. If input is "lc" set currentState to 10 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
		else if (strcmp(choice, inMessage[10]) == 0){
			personOutput.currentState = 10;
		}


		// 15. If input is "gll" set currentState to 11 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
		else if (strcmp(choice, inMessage[11]) == 0){
			personOutput.currentState = 11;
		}


		// 16. If input is "exit" set currentState to 12 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
		else if (strcmp(choice, inMessage[12]) == 0){
			personOutput.currentState = 12;
		}

		// 17. Send the personOutput w/ MsgSend() ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
		if (MsgSend(coid, &personOutput, sizeof(personOutput), &personInput, sizeof(personInput)) == - 1) {
			/*                 coid --> The connection ID received in ConnectAttach()
			 *         personOutput --> Pointer to a send buffer containing Person object
			 * sizeof(personOutput) --> Size of the send buffer
			 *          personInput --> Pointer to a receive buffer filled w/ Person object
			 *  sizeof(personInput) --> Size of the receiver buffer
			 * */

			fprintf(stderr, "Error during MsgSend\n");
			perror(NULL);
			exit(EXIT_FAILURE);
		}
		//Receiving confirmation from controller
		rcvid = MsgReceive(coid, &personInput, sizeof(personInput), NULL);
	} while (strcmp(choice, inMessage[12]) != 0 && personInput.currentState != EXIT);
	// 18. Detaches the channel when finished ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
	ConnectDetach(coid);
	return EXIT_SUCCESS;
} // End of assign1_inputs.c
