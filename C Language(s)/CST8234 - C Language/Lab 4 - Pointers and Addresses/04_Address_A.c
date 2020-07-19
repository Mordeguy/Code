/* PROGRAM:  03_100.c 
   AUTHOR:   Nathan M. Abbey
   DATE:     September 30, 2016
   TOPIC:    Array Puzzles
   PURPOSE:  04_Lab CST8234
   NOTES:   
             
*/

/**************************************************************************/
/* Declare include files
 **************************************************************************/
#include <stdio.h>
#include <stdlib.h>

/**************************************************************************/
/* Defines
 **************************************************************************/



/**************************************************************************/
/* Global Variables
 **************************************************************************/


	



/**************************************************************************/
/* Function prototypes
**************************************************************************/



/**************************************************************************
 * Main
 *    
 **************************************************************************/
int main( void ) {

	int y = 0;
	int x = 0;

	int * p;

	printf("\n----------------------------------------------------\n");
	
	printf("STEP 1:  Printing Sizes\n");
	printf("Sizeof ( int) =  %lu\n", sizeof(int));
	printf("Sizeof ( memory address ) =  %lu\n", sizeof(int*));

	printf("----------------------------------------------------\n");

	printf("STEP 2:  Variables - Before initialization\n");
	printf("x:        &x = %p        x = %d\n", (void*)&x, x);
	printf("y:        &y = %p        y = %d\n", (void*)&y, y);
	printf("p:	  &p = %p        p = %p       \n", (void *)&p, (void *)p);

	printf("----------------------------------------------------\n");

	x = 25;
	y= 1986;
	p = &x;
		
	printf("STEP 3:  Variables - After initialization\n");
	printf("x:        &x = %p        x = %d\n", (void *)&x, x);
	printf("y:        &y = %p        y = %d\n", (void *)&y, y);
	printf("p:	  &p = %p        p = %p       *p = %d\n", (void*)&p, (void*)p, *p);

	printf("----------------------------------------------------\n");

	printf("STEP 4: Pointer content with new value\n");

	*p = 55;

	printf("x:        &x = %p        x = %d\n", (void *)&x, x);
	printf("y:        &y = %p        y = %d\n", (void *)&y, y);
	printf("p:	  &p = %p        p = %p       *p = %d\n", (void*)&p, (void*)p, *p);

	printf("----------------------------------------------------\n");
	
	printf("STEP 5: Variable assignation to pointer\n");

	x= (int)p;

	printf("x:        &x = %p        x = %d\n", (void *)&x, x);
	printf("y:        &y = %p        y = %d\n", (void *)&y, y);
	printf("p:	  &p = %p        p = %p       *p = %d\n", (void*)&p, (void*)p, *p);

	printf("----------------------------------------------------\n");





	printf("\n\n");
	

	  



return EXIT_SUCCESS;


}
