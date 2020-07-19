/* PROGRAM:  Assign1.c 
   AUTHOR:   Nathan M. Abbey
   DATE:     October 16, 2016
   TOPIC:    Basic SMS
   PURPOSE:  Assignment 1 CST8234
   NOTES:   
             
*/

/**************************************************************************/
/* Declare include files
 **************************************************************************/
#include <stdio.h>
#include <stdlib.h>

/**************************************************************************/
/* Defines
 *************************************************************************/
#define SIZE 1000
#define TOKEN 50505
#define END -999999
#define TOO_BIG -10
#define INVALID_WORD -20

/**************************************************************************/
/* Global Variables
 **************************************************************************/

int memory[SIZE];
int opCode;
int op;
int instreg;
int instctr;
int acc;
int ints;
int max;
int memo;
int on;


/**************************************************************************/
/* Function prototypes
**************************************************************************/


void init_memo (int a[]);
void dump (int a[], int accumulator, int instcounter, int intregister, int oprationcode, int operand);
void dump_memo (int a, int max);
void error_message(int a);
int load(int a[]);
int error_check (int a);
void read (int op, int a[]);

/**************************************************************************
 * Main
 *    
 **************************************************************************/
int main( void ) {

	printf("\n\n*************************START EXECUTION***************************\n\n");
	
	init_memo(memory);
	error_message(load(memory));

	printf("\n***************************END EXECUTION****************************\n");

	load(memory);
        dump(memory, acc, instctr, instreg, opCode, op);
	load(memory);

return EXIT_SUCCESS;

	

}
/**************************************************************************
 * Function: init_memo
 **************************************************************************/

void init_memo (int a[]) {
     
     int i;
    
      for (i =0; i < SIZE;i++) {

           a[i] = TOKEN;
      }
}



/**************************************************************************
 * Function: dump
 **************************************************************************/

void dump (int a[], int accumulator, int instcounter, int intregister, int oprationcode, int operand) {

      int max = 20;

     printf("\n\nREGISTERS\n");
	
     printf("accumulator \t\t\t %05d\n", accumulator);
     printf("instructioncounter \t\t   %03d\n", instcounter);
     printf("instructionregister\t\t+%05d\n", intregister); 
     printf("operationcode \t\t\t    %+02d\n", oprationcode);
     printf("operand \t\t\t   %03d\n", operand);
     printf("ValidInstructions \t\t     1\n\n");

     printf("MEMORY\n");
     
     int i;
     int count = 0;


     for (i =0; i <SIZE; i++) {

	
	
	if (i %10 == 0) {	
	printf("\n");
	printf("%02d ", count);
	count +=10;
	}
	
	if (i < 10 || i < 20) {
	printf("+%d ", a[i]);
    }
	
	}


	
     

     printf("\n\n");
}



/**************************************************************************
 * Function: dump_memo
 **************************************************************************/

void dump_memo (int a , int max) {


	

}



/**************************************************************************
 * Function: error_check
 **************************************************************************/
int error_check (int a) {

	if (a < 10001 || a >43999) {
	
		return INVALID_WORD;
	}



}


/**************************************************************************
 * Function: error_message
 **************************************************************************/
void error_message (int a) {

	if (a == TOO_BIG) { 
  
        	printf("***ABEND: prg load: prg too big***\n");
		exit(1);
     	}


	if (a == INVALID_WORD) {

		printf("%d", a);
		printf("***ABEND: prg load: prg invalid word***\n");
		exit(1);
	}	
}





/**************************************************************************
 * Function: load
 **************************************************************************/

int load (int a[]) {

	int i;  
     	int num =0;
     	char newline;
	
	for (i =0; num != END; i++) {

		fscanf(stdin, "%d%c" , &num ,&newline );
	
		error_message(error_check(num));

		instreg = num;	
	
		opCode = num / 1000;
		op = num % 1000;

		
		switch (opCode) {


			case 10:	
				read(op, a);
				break;

			case 11:
				
				break;

			case 20:
				
				break;

		}
	}
}


/**************************************************************************
 * Function: read
 **************************************************************************/

void read(int op, int a[]) {

	char newline;
	 
	fscanf(stdin, "%d%c", &a[op], &newline);

	printf("%d", a[op]);

}
