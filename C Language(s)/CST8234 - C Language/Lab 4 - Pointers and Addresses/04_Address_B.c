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
int x= 5;
int y= 10;


int * p = &x;



/**************************************************************************/
/* Function prototypes
**************************************************************************/

int mod_var (int y, int p);

/**************************************************************************
 * Main
 *    
 **************************************************************************/
int main( void ) {

	printf("\n----------------------------------------------------\n");
	
	printf("FUNCTION main():\n");
	printf("x: &x= %p\t\tx= %d\n", (void*)&x, x);
	printf("y: &y= %p\t\ty= %d\n", (void*)&y, y);
	printf("p: &p= %p\t\tp= %p\t\t*p= %d\n", &p, p, *p);

	printf("\n----------------------------------------------------\n");

	mod_var(y, *p);
	
	printf("----------------------------------------------------\n");

	x = 45;
	printf("FUNCTION main():\n");
	printf("x: &x= %p\t\tx= %d\n", (void*)&x, x);
	printf("y: &y= %p\t\ty= %d\n", (void*)&y, y);
	printf("p: &p= %p\t\tp= %p\t\t*p= %d\n\n", &p, p, *p);


return EXIT_SUCCESS;

}


/**************************************************************************
 * Function: mod_var 
 **************************************************************************/


int mod_var (int y, int p) {

	int a = y;
	int *q = &p;
	
	printf("Calling mod_var (y,p):\n");
	printf("FUNCTION modify_var( ) -- before modifications\n");
	printf("a: &a= %p\t\ta=%d\n", &a, a);
	printf("q: &q= %p\t\tq=%p\t\t*q=%d\n\n", &q, q, *q );


	a = 100;
	*q =45;


	printf("Calling mod_var() -- after modification\n");
	printf("a: &a= %p\t\ta=%d\n", &a, a);
	printf("q: &q= %p\t\tq=%p\t\t*q=%d\n\n", &q, q, *q );

        
 
 
} 


