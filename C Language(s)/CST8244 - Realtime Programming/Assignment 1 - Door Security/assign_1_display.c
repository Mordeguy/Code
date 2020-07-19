
#include <stdio.h>
#include <stdlib.h>
#include <process.h>
#include <errno.h>
#include <sys/neutrino.h>
#include <sys/netmgr.h>
#include <string.h>
#include "assign1_header.h"

int main(int argc, char* argv[])
{
	//Holds channel id
	int channel_id;
	//Holds status of received message
	int status_message_received;
	//Sends status back to controller
	programStatus outgoing_status;
	//Create a channel
	channel_id = ChannelCreate(0);
	//Verify if channel was created successfully
	if (channel_id == -1)
	{
		perror("Error couldn't create channel");
		exit(EXIT_FAILURE);
	}
	//Server creation successful
	printf("The display is running as process_id %d\n", getpid());

	//Holds status message coming from controller
	programStatus status_message;
	//Server is constantly listening for messages from the controller and is running forever
	do
	{
		//Status message received from controller
		status_message_received = MsgReceive(channel_id, &status_message, sizeof(status_message), NULL);

		if (status_message.messageIndex == LEFT_SIDE_SCAN || status_message.messageIndex == RIGHT_SIDE_SCAN)
			printf("%s%d\n", outMessage[status_message.messageIndex], status_message.p.person_id);

		else if (status_message.messageIndex != LEFT_CLOSED || status_message.messageIndex != RIGHT_CLOSED)
			printf("%s\n", outMessage[status_message.messageIndex]);

		outgoing_status.p = status_message.p;
		outgoing_status.messageIndex = status_message.messageIndex;

		MsgReply (status_message_received, EOK, &outgoing_status, sizeof(outgoing_status));

	} while (outgoing_status.messageIndex != EXIT);
	printf("\nExit Display!");
	ChannelDestroy(channel_id);
	return EXIT_SUCCESS;
}

