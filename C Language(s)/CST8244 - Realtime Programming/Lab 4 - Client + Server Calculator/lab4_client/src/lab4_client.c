#include "calc_message.h"
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


/************************************************************************************************
 *   File:  Msg_Passing_Client.c
 *
 *  Usage:  ./Msg_Passing_Client PID_of_Server
 *
 * Method:  1.  Create an object of 'cient_send_t' defined in calc_message.h. This data struct is
 * 			    used to pass data to the server.
 * 			2.  Pulls data from the arguments entered @ the command line
 * 			         -atoi() converts String to integer  /  * used since value of pointer is needed
 * 			3.  Establish a connections using ConnectAttach()
 *			4.  Checks to see if coid is -1, if so, END.
 *			5.  Send the message using MsgSend()
 *				     - if that equals -1, error and END.
 *			6.  Print out the response depending on the error TOKEN.
 *			7.  Disconnect from the channel
 *
 ****IMPORTANT********
 *
 * Edit the Launch Configuration for the client (gear icon) > Arguments tab > enter PID of server
 *
 ************************************************************************************************/
int main(int argc, char* argv[]) {

 	char rmsg[200];				  				// Buffer to hold the server's reply message
	int  coid;						  			// Connection ID
	char left, right;							// Variables used to make sure input is numeric


	// 1. Create object of data type defined in calc_message.h ~~~~~~~~~~~~~~~~~~~~~~~~
	client_send_t clientMessage;

	// Struct used to send a message to the client
    server_response_t serverResponse;


    left =  *argv[2];
    right = *argv[4];

    if (!isdigit(left) || !isdigit(right)){
		fprintf(stderr, "CLIENT: Input two and four must be numeric.\n");
		exit(EXIT_FAILURE);
    }





	// 2. Pulls the values entered from the command line execution ~~~~~~~~~~~~~~~~~~~~
	pid_t serverpid = atoi(argv[1]);
	clientMessage.left_hand = atoi(argv[2]);
	clientMessage.operator = *argv[3];
	clientMessage.right_hand = atoi(argv[4]);





	// 3. Establish a connection ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	coid = ConnectAttach(ND_LOCAL_NODE, serverpid, 1, _NTO_SIDE_CHANNEL, 0);
	/*      ND_LOCAL_NODE --> Node descriptor running server; this for locally.
	 *          serverpid --> PID of server who owns comm channel
	 *			 	    1 --> Channel ID of the channel created; 1 for now.
	 *	_NTO_SIDE_CHANNEL --> Starting index to be used for the connection file desciptor
	 *	                0 --> FLags that contorl how connection works; 0 for now.
	 * */


	// 4. If 'coid' equals -1, something went wrong and END ~~~~~~~~~~~~~~~~~~~~~~~~~~~
	if (coid == -1) {
		fprintf(stderr, "Couldn't ConnectAttach\n");
		perror(NULL);
		exit(EXIT_FAILURE);
	}






	// 5. Send the clientMessage w/ MsgSend() ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	if (MsgSend(coid, &clientMessage, sizeof(clientMessage) + 1, &serverResponse, sizeof(serverResponse)) == -1) {
		/*             coid --> The connection ID received in ConnectAttach()
		 *             smsg --> Pointer to a send buffer containing msg you qnt to send [declared above]
		 *   strlen(smsg)+1 --> Size of the send buffer (+1 for EOF)
		 *             rmsg --> Pointer to a receive buffer filled w/ server's reply
		 *     sizeof(rmsg) --> Size of the receiver buffer
		 * */


		fprintf(stderr, "Error during MsgSend\n");
		perror(NULL);
		exit(EXIT_FAILURE);
	}


	printf("Server PID : %d\n", serverpid );

	// 6. If the string length is above 0 print it out ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	if (serverResponse.error == SRVR_OK) {
		printf("The server has calculated the result of %d %c %d as %.2f\n", clientMessage.left_hand, clientMessage.operator, clientMessage.right_hand, serverResponse.answer);
	}

	if (serverResponse.error == SRVR_UNDEFINED || serverResponse.error == SRVR_INVALID_OPERATOR) {
		printf("%s", serverResponse.errorMsg);
	}




	// 7. Disconnect from the channel ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	ConnectDetach(coid);

	return EXIT_SUCCESS;
}
