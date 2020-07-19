/*---------------------------------------------------------------------------------------------------------
 File Name: table.h
  Compiler: Visual Studio 2015
    Author: Svillen Ranev / Nathan M. Abbey
    Course: CST 8152 - Compilers, Lab Section: 11
Assignment: 02
      Date: October 30, 2017
 Professor: Svillen Ranev
   Purpose: This is the header file contains the State Transition table, Accepting State defiition table,
            Accepting Funtion Callback table, and the Keyword Table. This data is used to determine how to
			handles the buffer input in scanner.c.
----------------------------------------------------------------------------------------------------------*/
#ifndef  TABLE_H_
#define  TABLE_H_ 

#ifndef BUFFER_H_
#include "buffer.h"
#endif

#ifndef NULL
#include <_null.h> /* NULL pointer constant is defined there */
#endif

#define ES  12 /* Error state */
#define IS  13    /* Inavalid state */

/* State transition table definition -----------------------------------------------------------------*/
#define TABLE_COLUMNS 8

/*transition table - type of states defined in separate table */
int  st_table[][TABLE_COLUMNS] = {
	//            |     0    |    1    |   2    |    3    |    4    |   5    |    6    |      7     | 
	//-----------------------------------------------------------------------------------------------
	//            |    a-z   |   a-f   |   x    |    0    |   1-9   |   .    |    $    |    other   |
	// ----------------------------------------------------------------------------------------------
	/* State 0*/  {     1,        1,       1,        6,        4,      IS,       IS,          IS},
	/* State 1*/  {     1,        1,       1,        1,        1,       2,        3,           2},	
	/* State 2*/  {    IS,       IS,      IS,       IS,       IS,      IS,       IS,          IS},
	/* State 3*/  {    IS,       IS,      IS,       IS,       IS,      IS,       IS,          IS},
	/* State 4*/  {    ES,       ES,       5,        4,        4,       7,        5,           5},
	/* State 5*/  {    IS,       IS,      IS,       IS,       IS,      IS,       IS,          IS},
	/* State 6*/  {    ES,       IS,       9,       11,       ES,       7,       ES,           5},  
	/* State 7*/  {     8,       IS,      IS,        7,        7,       8,        8,           8},
	/* State 8*/  {    IS,       IS,      IS,       IS,       IS,      IS,       IS,          IS},
	/* State 9*/  {    ES,       9,       ES,        9,        9,      ES,       ES,          11},
	/*State 10*/  {    ES,       10,      ES,       10,       10,      ES,       ES,          11},
	/*State 11*/  {    IS,       IS,      IS,       IS,       IS,      IS,       IS,          IS},
	/*State 12*/  {    IS,       IS,      IS,       IS,       IS,      IS,       IS,          IS},
	/*State 13*/  {    IS,       IS,      IS,       IS,       IS,      IS,       IS,          IS}
};
/* ---------------------------------------------------------------------------------------------------*/


/* Accepting state table definition ------------------------------------------------------------------*/
#define ASWR 3  /* accepting state with retract */
#define ASNR 4  /* accepting state with no retract */
#define NOAS 5  /* not accepting state */

	int as_table[] = { NOAS, //0
					   NOAS, //1
		               ASWR, //2
		               ASNR, //3
		               NOAS, //4
		               ASWR, //5
		               NOAS, //6
		               NOAS, //7
		               ASWR, //8
		               NOAS, //9
					   NOAS, //10
		               ASWR, //11
		               ASNR, //12
					   ASWR	 //13
				     };
/* ---------------------------------------------------------------------------------------------------*/



/* Accepting action function declarations ----------------------------------------------------------- */
Token aa_func02(char *lexeme);
Token aa_func03(char *lexeme);
Token aa_func05(char *lexeme);
Token aa_func08(char *lexeme);
Token aa_func11(char *lexeme);
Token aa_func12(char *lexeme);
Token aa_func13(char *lexeme);
/* ---------------------------------------------------------------------------------------------------*/



/* defining a new type: pointer to function (of one char * argument) returning Token */
typedef Token(*PTR_AAF)(char *lexeme);



/* Accepting function (action) callback table (array) definition -------------------------------------*/

PTR_AAF aa_table[] = { NULL, //0
					   NULL, //1
				  aa_func02, //2
	              aa_func03, //3
	                   NULL, //4
	              aa_func05, //5
	                   NULL, //6
	                   NULL, //7
	              aa_func08, //8
	                   NULL, //9
	                   NULL, //10
	              aa_func11, //11
				  aa_func12, //12
				  aa_func13  //13
};
/* ---------------------------------------------------------------------------------------------------*/



/* Keyword lookup table ------------------------------------------------------------------------------*/
#define KWT_SIZE  10

char * kw_table[] =
{
	"ELSE",
	"FALSE",
	"IF",
	"PLATYPUS",
	"READ",
	"REPEAT",
	"THEN",
	"TRUE",
	"WHILE",
	"WRITE"
};
/* ---------------------------------------------------------------------------------------------------*/
#endif
