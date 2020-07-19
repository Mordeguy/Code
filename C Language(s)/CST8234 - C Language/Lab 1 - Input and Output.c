/* PROGRAM:  01_Input.c 
   AUTHOR:    
   DATE:     Fall 2015
   TOPIC:    Basic C 
   PURPOSE:  01_Lab CST8234
   NOTES:   
             
*/
/**************************************************************************/
/* Declare include files
 **************************************************************************/
#include <stdio.h>
#include <stdlib.h>

/**************************************************************************/
/* Macro Defines
 **************************************************************************/
#define MIN 10
#define MAX 300

/**************************************************************************/
/* Function prototypes
 **************************************************************************/
int intGet( int , int );

/**************************************************************************/
/* Global variables
 **************************************************************************/
int errorNo;

/**************************************************************************
 * Main
 *    Reads a number in between MIN and MAX from the keyboard
 *    Reports errors encountered 
 *    Returns 0 on success
 *            1 on failure
 **************************************************************************/

int main( void ) {

	// Prompts user for input between 2 values and feed it into intGet; prints error if
	// there is a problem.	
	printf ("Enter a number in between [%d and %d]: ", MIN, MAX);
	intGet(MIN, MAX);
	
	// Displays an error message if neccessary.
	error();
	
            	        
}
/**************************************************************************
  intGet
 *      Read an integer number in between min and max
 *      Return: 
 *           number read
 *      Sets global variable errorno
 *           EFORMAT:  Incorrect format ( incorrect starts of characters )
 *           ECHARS :  Extra chars after the number
 *           ERANGE :  Number out of [ MIN, MAX ] range
 **************************************************************************/
int intGet( int min, int max ) {


	int read, choice;
	char c;	

	choice = scanf( "%d%c" , &read, &c ); 
             
         // If scanf return a 0 it means there is an input problem ------------------------|
	   if (choice == 0) {
			
			errorNo = 1;
	    } 
	    
	     // If the character after the digit inputted isn't '\n' there's wierd chars -----|
	     else if (c != '\n') {
	 		
	 		errorNo = 2;
        }
          // If the number input by user is outside the bounds ----------------------------|
	     else if (read < MIN || read > MAX) {

			errorNo = 3;

		} else  {

			// If the input is valid it prints this out and exits program --------------|
			printf("Read: %d\n", read);		
			exit(EXIT_SUCCESS);
		}
		
}
/**************************************************************************/
/* error( )
 *      Displays error message depening of errorno global variable
 *      Terminates with failure
 **************************************************************************/
int error( ){

  if (errorNo == 1) {
   
       printf("Incorrect input format\n");
	exit (EXIT_FAILURE); 
  }

  if (errorNo == 2){ 

       printf("Extra characters\n");
	exit (EXIT_FAILURE); 
   
}
  if (errorNo == 3)  {

       printf("Input number out of range\n");
	exit (EXIT_FAILURE);  
	}  
} 
