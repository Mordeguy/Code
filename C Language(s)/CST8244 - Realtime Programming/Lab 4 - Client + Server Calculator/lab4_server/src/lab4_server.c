#include "calc_message.h"
#include <float.h>
#include <stdio.h>
#include <stdlib.h>
#include <process.h>
#include <string.h>
#include <errno.h>
#include <sys/neutrino.h>
#include <pthread.h>


/***********************************************************************************
 *   File:  Msg_Passing_Server.c
 *
 *  Usage:  ./Msg_Passing_Server
 *
 * Method:  1. Create a channel and assign the ID to 'chid'.
 *          2. If the 'chid' equals -1, process failed and end program.
 *          3. Print out the thread's and server's PID and a msg
 *          4. Server begins an infinite loop; typical of servers
 *          5. Gets the msg using MsgReceived()
 *          6. Assign the 2 integers in to doubles for proper arithmetic
 *          7. Takes the operator to determine how to handle the two numbers, and calculates
 *          8. Prepare and send reply using MsgReply()
 *          9. Destroy the channel when done
 *
 * Note the server's PID (required for client).
 *
 ***********************************************************************************/

int main (int argc, char* argv[])
{

	int     rcvid;        							// Indicates who we should reply to (client ID)
	int     chid;         							// The channel ID (server establishes a Channel for msgs)
	double leftSide, rightSide, tempAnswer;  		// Double values to handle the arithmetic involved in the calculations
	server_response_t serverResponse;				// Struct used to send a message to the client
	client_send_t clientMessage;					// Temporary holder for the client message received from the client



	// 1. Create a channel and returns the Child ID ~~~~~~~~~~~~~~~~~~~~~~~~~
	chid = ChannelCreate (0);
	//(0) -  changes how channel notifies the calling THREAD. For now, use 0.



	// 2. If it fails it will go to -1 and end ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	if (chid == -1)
	{
		perror("failed to create the channel.");
		exit (EXIT_FAILURE);
	}


	printf("%d\n", getpid());

	// 3, Prints out the THREAD id and SERVER's PID ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//printf("[%d] %d\n", pthread_self(), getpid());



	// 4. Server begins in an infinite loop ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	while (1) {


		// 5. Gets the message and assigns it to clientMesage ~~~~~~~~~~~~~~~
		rcvid = MsgReceive (chid, &clientMessage, sizeof (clientMessage), NULL);
		/*              chid --> The channel ID for the comm channel
		 *           message --> A buffer to receive msg in
		 *   sizeof(message) --> The size of buffer to receive in
		 *              NULL --> Don't worry about for now...
		 *
		 * Returns a receiver ID (rcvid) used to reply or -1 for ERROR.
		 *  - rcvid gets the ID of the client so you can send a msg back
		 * */


		// 6. Assign the 2 integers in to doubles for proper arithmetic
		leftSide = clientMessage.left_hand;
		rightSide = clientMessage.right_hand;



		// 7. Takes the operator to determine how to handle the two numbers, and calculates
		switch (clientMessage.operator) {

		case '+':
			tempAnswer= leftSide + rightSide;
			serverResponse.answer = tempAnswer;
			serverResponse.error = SRVR_OK;
			break;

		case '-':
			tempAnswer = leftSide - rightSide;
			serverResponse.answer = tempAnswer;
			serverResponse.error = SRVR_OK;
			break;

		case 'x':
			tempAnswer = leftSide * rightSide;
			serverResponse.answer = tempAnswer;
			serverResponse.error = SRVR_OK;
			break;

		case '/':
			if (rightSide == 0) {
				serverResponse.error = SRVR_UNDEFINED;
				strcpy(serverResponse.errorMsg, "The server cannot calculate the answer as you cannot divide by ZERO.\n");
				break;
			}
			tempAnswer = leftSide / rightSide;
			serverResponse.answer = tempAnswer;
			serverResponse.error = SRVR_OK;
			break;


			// If the operator is none of the above; it is an error.
		default:
			serverResponse.error = SRVR_INVALID_OPERATOR;
			strcpy(serverResponse.errorMsg, "The server cannot calculate the answer as the operator is INVALID.\n");
			break;
		}




		// 8. Prepare the reply.  We reuse send the serverResponse struct, and send using MsgReply()
		MsgReply (rcvid, EOK, &serverResponse, sizeof (serverResponse));
		/*  rcvid --> Receiver's ID (client ID)
		 *    EOK --> STATUS
		 *    msg --> The buffer you want to send
		 *   size --> Size of the buffer being sent
		 * */
	}


	// 9. Destroy the channel when done ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	ChannelDestroy(chid);

	return EXIT_SUCCESS;
}
