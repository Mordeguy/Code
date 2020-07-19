/* ---------------------------------------------------------------------------------------------------
     Student Name:	Nathan M. Abbey
   Student Number:	040-557-192
Assignment Number:	00 - Animation Project in C
           Course:	CST8233 - Numerical Computing
      Lab Section:	301
 Professor's Name:	Andrew Tyler
         Due Date:	Sunday, September 25 -> (23:59)
  Submission Date:  Thursday, September 21
   Source File(s):	ass0.c
   Header File(s):	none
------------------------------------------------------------------------------------------------------*/
#define _CRT_SECURE_NO_WARNINGS

#define _CRTDBG_MAP_ALLOC	// need this to get the line identification
//_CrtSetDbgFlag(_CRTDBG_ALLOC_MEM_DF|_CRTDBG_LEAK_CHECK_DF); // in main, after local declarations
//NB must be in debug build

#include <crtdbg.h>
#include <stdio.h>
#include <string.h>
#include <time.h>

typedef enum { FALSE = 0, TRUE } BOOL;

struct Frame {
	char* fileName;
	struct Frame* pNext;
};

struct Animation {
	struct Frame* frames;
};

void initAnimation(struct Animation*);
void insertFrame(struct Animation*);
void deleteFrames(struct Animation*);
void runFrames(struct Animation*);

/********************************************************************************************
          Function Name: main
                Purpose: main function
                             - Handles a Frame object and manipulates it based on user input
                               (adding/deleting/displaying) Frames to Animation linked list
 Function In Parameters: none
Function Out Parameters: 0 for successful execution
                Version: 1.0
				 Author: Andrew Tyler
*********************************************************************************************/
int main(void) {

	char response;
	BOOL RUNNING = TRUE;
	struct Animation A;
	_CrtSetDbgFlag(_CRTDBG_ALLOC_MEM_DF | _CRTDBG_LEAK_CHECK_DF); // C runtime debug program
	initAnimation(&A);

	while (RUNNING) {

		printf("MENU\n 1. Insert a Frame\n 2. Delete all the Frames\n 3. Run the Animation\n 4. Quit\n");
		scanf("%c", &response);

		switch (response) {
		case '1':insertFrame(&A); break;
		case '2':deleteFrames(&A); break;
		case '3':runFrames(&A); break;
		case '4':RUNNING = FALSE; deleteFrames(&A); break;
		default:printf("Please enter a valid option\n");
		}
		printf("\n");

		while ((response = getchar()) != '\n' && response != EOF);// clear input buffer
	}
	return 0;
}




/********************************************************************************************
          Function Name: runFrames
                Purpose: Run through and display the linked list of Frames
 Function In Parameters: Animation pointer pointed to the head of the linked list of Frames
Function Out Parameters: none
                Version: 1.0
                 Author: Andrew Tyler
*********************************************************************************************/
void runFrames(struct Animation* pA) {

	int counter = 0;
	time_t startsec, oldsec, newsec;
	struct Frame* iterator = pA->frames;

	if (pA->frames == 0) {
		printf("No frames in the animation\n");
		return;
	}

	printf("Run the Animation\n");
	startsec = oldsec = time(NULL);

	while (iterator) {
		newsec = time(NULL);

		if (newsec > oldsec) {
			printf("Frame #%d, time = %d sec\n", counter++, newsec - startsec);
			printf("Image file name = %s\n", iterator->fileName);
			iterator = iterator->pNext;
			oldsec = newsec;
		}
	}
}

/********************************************************************************************
          Function Name: initAnimation
                Purpose: Initializes the Frames object in the Animation pointer to NULL
 Function In Parameters: Animation pointer pointed to null linked list of Frames
Function Out Parameters: none
                Version: 1.0
    Author/Student Name: Nathan M. Abbey
*********************************************************************************************/
void initAnimation(struct Animation* a) {
	a->frames = NULL;
}

/*****************************************************************************************
          Function Name: insertFrame
                Purpose: Create a new Frame object and insert it in to the linked list in
                         a specific index
 Function In Parameters: Animation pointer pointed to the head of the linked list of Frames
Function Out Parameters: none
                Version: 1.0
    Author/Student Name: Nathan M. Abbey
******************************************************************************************/
void insertFrame(struct Animation* a) {

	int strLength = 0, numFrames = 0, insertNum = 0;
	char namePasser[300];
	char *tempName; 
	struct Frame *temp;
	struct Frame *newFrame = (struct Frame*)malloc(sizeof(struct Frame));

	printf("Insert a frame in the Animation\nPlease enter the Frame filename: ");
	scanf("%s", namePasser);
	
	tempName = (char*)malloc((sizeof(char)) * (strlen(namePasser) + 1));
	strcpy(tempName, namePasser);

	newFrame->fileName = tempName;

	if (a->frames == NULL) {
		printf("This is the first frame in the list.\n");
		newFrame->pNext = NULL;
		a->frames = newFrame;
	}
	else {

		temp = a->frames;
		while (temp != NULL) {
			numFrames++;
			temp = temp->pNext;
		}
		printf("There are %d Frame(s) in the list. Please specify position (<= %d) to insert at: ", numFrames, numFrames);
		scanf("%d", &insertNum);

		if (insertNum == 0) {
			newFrame->pNext = a->frames;
			a->frames = newFrame;
		}
		else {
			temp = a->frames;
			for (int i = 0; i < insertNum - 1; i++) {
				
				printf("%s\n", temp->fileName);
				temp = temp->pNext;
			}
			newFrame->pNext = temp->pNext;
			temp->pNext = newFrame;
		}	
	}	
}

/*****************************************************************************************
          Function Name: deleteFrames
                Purpose: Delete all of the frames in the list and de-allocate the memory
                         they occupied using command 'free'
 Function In Parameters: Animation pointer pointed to the head of the linked list of Frames
Function Out Parameters: none
                Version: 1.0
    Author/Student Name: Nathan M. Abbey
******************************************************************************************/
void deleteFrames(struct Animation* a) {

	struct Frame *iterator = a->frames;
	struct Frame * temp;

	if (iterator != NULL)
	printf("Delete all the Frames from the animation.\n");

	while (iterator != NULL) {
		temp = iterator;
		free(temp->fileName);
		iterator = iterator->pNext;
		free(temp);	
	}

	a->frames = NULL;
	free(a->frames);
}