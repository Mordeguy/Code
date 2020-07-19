/* PROGRAM :  Lab_3.c
    AUTHOR :  Nathan M. Abbey
   UPDATED :  December 11, 2016
     TOPIC :  100 Doors - OPEN(1) CLOSED(0) 
   PURPOSE :  CST8234 - C Language - Lab 3
     TASKS :   
*/

/***********************************************************/
/* Declare include files
/***********************************************************/
#include <stdio.h>

/***********************************************************/
/* Macros
/***********************************************************/
#define SIZE 100


/***********************************************************/
/* Function Prototypes
/***********************************************************/
int toggle_door ( char a[]);
int doors_state(char a[]);


/***********************************************************/
/* Global Variables
/***********************************************************/


/***********************************************************/
/* Main
/***********************************************************/

int main() {

	int i;
	char array[SIZE] = {0}; 
	
	for(i = 0; i < SIZE; i++) {
		array[i] = '0';
	} 
	
	
	toggle_door(array);
	doors_state(array);
	
	return(0);
}





/***********************************************************/
/* Function: toggle_door
/*     Task: Toggles "doors" 0 to 1; or back again.
/***********************************************************/
int toggle_door ( char a[]){
	
	int i;
	int j;
	
	for (i = 0; i < SIZE; i++) {
		
		for (j = i; j < SIZE; j++) {
			
			if((j + 1) % (i + 1) == 0) {

				if(a[j] =='0') {
					a[j] = '1';
				}

				else {
					a[j] = '0';
				}			

		  	}

		}
		
	}	
	return 0;
	}




/***********************************************************/
/* Function: doors_state
/*     Task: Prints out all doors int the array.
/***********************************************************/
int doors_state(char a[]){
	
	int i;
	int printNo = 0;
	
	for(i= 0; i < SIZE; i++) {
		
		
		if (i % 10 == 0) {
			
			printf("\n");
			printf("%2d:  ", printNo);
			printNo = printNo + 10;
		}
	
		printf("%d   ", a[i] );
		
		
	}
	printf("\n\n");
	return(0);
}

