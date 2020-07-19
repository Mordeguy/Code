/*---------------------------------------------------------------------------------------------------------
    File Name:  scanner.c
     Compiler:  Visual Studio 2015
       Author:  Svillen Ranev & Nathan M. Abbey
       Course:  CST 8152 - Compilers, Lab Section: 11
   Assignment:  02
         Date:  November 30, 2017
    Professor:  Svillen Ranev
      Purpose:  This class creates a Scanner object which takes in a stream of chars as input, and
	            decides how to handle the input based on the 'PLATYPUS' Language Specification (PLS). 
				provided. This class utilizes a Finite State Machine (FSM) which takes characters in
				one-by-one and decides immediately how to handle the character based on the PLS.  
Function List:  1. scanner_init()					8. aa_func05()    
                2. malar_next_token()				9. aa_func11()  
				3. get_next_state()			       10. aa_func12()	 
				4. char_class()                    11. isKeyWord()
				5. aa_func02()				       12. atolh()
				6. aa_func03()					   13. isDigit()
				7. aa_func08()                     14. isLetter()			
----------------------------------------------------------------------------------------------------------*/

/* The #define _CRT_SECURE_NO_WARNINGS should be used in MS Visual Studio projects
* to suppress the warnings about using "unsafe" functions like fopen()
* and standard sting library functions defined in string.h.
* The define does not have any effect in Borland compiler projects.
*/
#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>   /* standard input / output */
#include <ctype.h>   /* conversion functions */
#include <stdlib.h>  /* standard library functions and constants */
#include <string.h>  /* string functions */
#include <limits.h>  /* integer types constants */
#include <float.h>   /* floating-point types constants */

/*#define NDEBUG        to suppress assert() call */
#include <assert.h>  /* assert() prototype */

/* project header files */
#include "buffer.h"
#include "token.h"
#include "table.h"

#define DEBUG  /* for conditional processing */
#undef  DEBUG


/* Global objects - variables */
/* This buffer is used as a repository for string literals.
It is defined in platy_st.c */

extern Buffer * str_LTBL; /*String literal table */
int line; /* current line number of the source code */
extern int scerrnum;     /* defined in platy_st.c - run-time error number */

/* Local(file) global objects - variables */
static Buffer *lex_buf;/*pointer to temporary lexeme buffer*/


/* scanner.c static(local) function  prototypes */
static int char_class(char c); /* character class function */
static int get_next_state(int, char, int *); /* state machine function */
static long atolh(char * lexeme); /* converts hexadecimal string to decimal value */						
static int isKeyWord(char * lexeme); /* checks to see if the lexeme is a PLATYPUS keyword*/
static int isLetter(char c); /* checks to see if the char is al alphabetic letter*/
static int isDigit(char c); /* checks to see if the char is a numerical digit 0-9*/

/*Initializes scanner */
int scanner_init(Buffer * sc_buf) {
	if (b_isempty(sc_buf)) return EXIT_FAILURE;/*1*/
											   /* in case the buffer has been read previously  */
	b_rewind(sc_buf);
	b_clear(str_LTBL);
	line = 1;
	return EXIT_SUCCESS;/*0*/
						/*   scerrnum = 0;  *//*no need - global ANSI C */
}


/***********************************************************************************************
            Name: malar_next_token()
         Purpose: Takes buffer input one character at a time and returns an initialized Token
		          object with the appropriate data. 
          Author: Nathan M. Abbey
History/Versions: 1.0
Called Functions: - b_get c()				- aa_func02()		   			
				  - b_retract()				- aa_func03()			   
				  - b_addc()				- aa_func05()			
				  - b_getoffset				- aa_func08()			
				  - isDigit()				- aa_func11()
				  - isLetter()				- aa_func12()
				  - b_mark()				- aa_func13()
				  - get_next_state()		- b_free()
				  - b_allocate()	
      Parameters: Buffer * sc_buf - A pointer to the input buffer
    Return Value:           Token - An initialized Token object with appropriate code and/or
	                                attribute set.
       Algorithm: 1. Gets character from the input buffer and sets it to variable 'c'.
                  2. Depending on what the input is, method will go through the switch
				     statement to determine how to handle the input.
                  3. Once a token has been generated, this method returns the initialized token.
************************************************************************************************/
Token malar_next_token(Buffer * sc_buf)
{
	Token t; /* token to return after recognition */
	unsigned char c; /* input symbol */
	int state = 0; /* initial state of the FSM */
	short lexstart;  /*start offset of a lexeme in the input char buffer (array) */
	short lexend;    /*end   offset of a lexeme in the input char buffer (array)*/
	int accept = NOAS; /* type of state - initially not accepting */

	// These to variables handles the SWTICH loop and the retract number; if need be.
	int loop = 0, rewind = 0;
	
	while (loop == 0) { /* endless loop broken by token returns it will generate a warning */

		c = b_getc(sc_buf);
		switch (c) {

		// Handles the blank spaces with: continue --------------------------------------|
		case ' ':
			continue;
		case '\t':
			continue;


		// Handles new lines, and increments line number --------------------------------|
		case '\n':
			line++;
			continue;


		// Handles all of the single code tokens ----------------------------------------|
		case '#':
			t.code = SCC_OP_T;
			return t;
		case '{':
			t.code = LBR_T;
			return t;
		case '}':
			t.code = RBR_T;
			return t;
		case '(':
			t.code = LPR_T;
			return t;
		case ')':
			t.code = RPR_T;
			return t;
		case ',':
			t.code = COM_T;
			return t;
		case ';':
			t.code = EOS_T;
			return t;
		case '\0':
			t.code = SEOF_T;
			loop = -2;
			return t;

				
		// Handles single code and single attribute tokens ------------------------------|
		case '*':
			t.attribute.arr_op = MULT;
			t.code = ART_OP_T;
			return t;
		case '/':
			t.attribute.arr_op = DIV;
			t.code = ART_OP_T;
			return t;
		case '+':
			t.attribute.arr_op = PLUS;
			t.code = ART_OP_T;
			return t;
		case '-':
			t.attribute.arr_op = MINUS;
			t.code = ART_OP_T;
			return t;
		case '>':
			t.attribute.rel_op = GT;
			t.code = REL_OP_T;
			return t;
		case '<':
			c = b_getc(sc_buf);
			if (c == '>') {
				t.attribute.rel_op = NE;
				t.code = REL_OP_T;
			}
			else {
				t.attribute.rel_op = LT;
				t.code = REL_OP_T;
				b_retract(sc_buf);
			}
			return t;


		// Handles the '=' sign, could mean equals or assignment operator ---------------|
		case '=':
			c = b_getc(sc_buf);
			if (c == '=') {
				t.attribute.rel_op = EQ;
				t.code = REL_OP_T;
			}
			else {
				t.code = ASS_OP_T;
				b_retract(sc_buf);
			}
			return t;


		// Handles comments, if not correct format code returns error Token ------------|
		case '!':

			c = b_getc(sc_buf);

			if (c == '!') {
				while(c != '\n') {
					c = b_getc(sc_buf);
				} 
				line++;
				continue;
			}
			else {

				int index = 0;
				b_retract(sc_buf);
				b_retract(sc_buf);
				c = b_getc(sc_buf);
				t.attribute.err_lex[index] = c;
				index++;
				c = b_getc(sc_buf);
				t.attribute.err_lex[index] = c;
				index++;
				t.attribute.err_lex[index] = '\0';

				while (c != '\n') {
					c = b_getc(sc_buf);
				}
				t.code = ERR_T;
				return t;
			}


		// Handles Logical Operators (.AND. , .OR.) - ERROR if not used correctly ------| 
		case '.':

			c = b_getc(sc_buf);
			rewind++;

			if (c == 'O') {
				c = b_getc(sc_buf);
				rewind++;
				if (c == 'R') {
					c = b_getc(sc_buf);
					rewind++;
					if (c == '.') {
						t.attribute.log_op = OR;
						t.code = LOG_OP_T;
						return t;
					}
				}
			}

			if (c == 'A') {
				c = b_getc(sc_buf);
				rewind++;
				if (c == 'N') {
					c = b_getc(sc_buf);
					rewind++;
					if (c == 'D') {
						c = b_getc(sc_buf);
						rewind++;
						if (c == '.') {
							t.attribute.log_op = AND;
							t.code = LOG_OP_T;
							return t;
						}
					}
				}
			}

			// If the input is incorrect, rewind the Buffer back
			for (int i = 0; i < rewind; i++) {
				b_retract(sc_buf);
			}

			// Produces error token if incorrectly used
			t.code = ERR_T;
			int index = 0;
			t.attribute.err_lex[index] = '.';
			index++;
			t.attribute.err_lex[index] = '\0';
			rewind = 0;
			return t;


		// Handles String Literals and ensures correct use -----------------------------|
		case '\"':

			c = b_getc(sc_buf);
			lexstart = b_getcoffset(sc_buf);
			t.attribute.str_offset = b_limit(str_LTBL);
			

			while (c != '\"') {
				if (c == '\0') {
					
					lexend = b_getcoffset(sc_buf);
					short j = lexstart - 2;
					b_retract(sc_buf);
					int size = lexend - lexstart;
					if (size > ERR_LEN) {
						
						for (int i = 0; i < 17; i++) {
							char c1 = *(b_location(sc_buf, j));
							t.attribute.err_lex[i] = c1;
							j++;
						}
						for (int i = 17; i < 20; i++) {
							t.attribute.err_lex[i] = '.';
						}
						t.attribute.err_lex[20] = '\0';
						t.code = ERR_T;
					}
					return t;
				}

				c = b_getc(sc_buf);
			}
			lexend = b_getcoffset(sc_buf);
			int size = (lexend - lexstart) + 1;
			short start = lexstart - 1;

			for (int i = 0; i < (size - 1); i++) {
				char c2 = *(b_location(sc_buf, start));
				b_addc(str_LTBL, c2);
				start++;
			}

			b_addc(str_LTBL, '\0');		
			t.code = STR_T;
			return t;


		// Handles all other cases of character input ----------------------------------|
		default:

			// If the input is not a recognized input, program returns ERROR token
			if (isDigit(c) != 0 && isLetter(c) != 0) {
				int i = 0;
				t.attribute.err_lex[i] = c;
				i++;
				t.attribute.err_lex[i] = '\0';
				t.code = ERR_T;
				return t;
			}
				
			// Rewinds the buffer 1 char and assigns the index to lexstart
			lexstart = b_getcoffset(sc_buf) -1;
			b_mark(sc_buf, lexstart);
			
			// The following is the 'Finite State Machine' which takes 1 char at a time and
			// decides what to do with it.
			do {
				// Get the next state from the transition table 
				state = get_next_state(state, c, &accept);

				//Breaks out of loop once in an accepting state
				if (accept != NOAS) {
					break;
				}
				// Gets next character from the Buffer
				c = b_getc(sc_buf);					       	 

			} while (accept == NOAS);                        

			// Retract if in accepting state with retract
			if (accept == ASWR)
				b_retract(sc_buf);
		
			lexend = b_getcoffset(sc_buf);

			// Creates a new buffer to hold the input
			short buffSize = (lexend - lexstart) + 1;
			lex_buf = b_allocate(buffSize, 0, 'f');
			if (lex_buf == NULL) {
				free(lex_buf);
			}

			// Adds the input character by character
			for (short i = lexstart; i < lexend; i++) {
				char temp = *(b_location(sc_buf, i));
				b_addc(lex_buf, temp);
			}

			// Appends '\0' on the end, sets the mark and passes it to the aa_table  
			b_addc(lex_buf, '\0');
			b_mark(lex_buf, 0);	
			t = aa_table[state](b_location(lex_buf, 0));
		
			// Frees the memory allocated for lex_buf before returning Token
			b_free(lex_buf);
			return t;
		} 
	} 	
}



/***********************************************************************************************
            Name: get_next_state()
         Purpose: Takes buffer input one character at a time and returns an initialized Token
                  object with the appropriate data.
          Author: Svillen Ranev
History/Versions: unknown
Called Functions: - char_class()
				  - st_table[][]
      Parameters:   int state - The state number the transition table is at
	                   char c - The character that the FSM is processing using the transition 
					            table.
			      int *accept - Pointer to the Accepting Table which determines if the Scanner
				                is in an accepting state or not.
    Return Value: int - Returns the state the character returns from the transition table           
       Algorithm: 1. Determines which column of the transition table is next using the state
	                 number and column number.
                  2. Ensures the state that next equals is not an illegal state.
                  3. Points the *accept pointer to the proper value in the accepting state table
				  4. Returns variable 'next', which is the next state in the transition table.
************************************************************************************************/
int get_next_state(int state, char c, int *accept)
{
	int col;
	int next;
	col = char_class(c);

	next = st_table[state][col];
#ifdef DEBUG
	printf("Input symbol: %c Row: %d Column: %d Next: %d \n", c, state, col, next);
#endif
	/*
	The assert(int test) macro can be used to add run-time diagnostic to programs
	and to "defend" from producing unexpected results.
	assert() is a macro that expands to an if statement;
	if test evaluates to false (zero) , assert aborts the program
	(by calling abort()) and sends the following message on stderr:

	Assertion failed: test, file filename, line linenum

	The filename and linenum listed in the message are the source file name
	and line number where the assert macro appears.
	If you place the #define NDEBUG directive ("no debugging")
	in the source code before the #include <assert.h> directive,
	the effect is to comment out the assert statement.
	*/
	assert(next != IS);

	/*
	The other way to include diagnostics in a program is to use
	conditional preprocessing as shown bellow. It allows the programmer
	to send more details describing the run-time problem.
	Once the program is tested thoroughly #define DEBUG is commented out
	or #undef DEBUF is used - see the top of the file.
	*/
#ifdef DEBUG
	if (next == IS) {
		printf("Scanner Error: Illegal state:\n");
		printf("Input symbol: %c Row: %d Column: %d\n", c, state, col);
		exit(1);
	}
#endif
	*accept = as_table[next];
	return next;
}




/***********************************************************************************************
            Name: char_class()
         Purpose: Takes in a character and determines which column of the transition table to
		          go to.
							~ A-Z = 0			~   .   = 5
							~ A-F = 1			~   $   = 6
							~  X  = 2			~ other = 7
							~  0  = 3
							~ 1-9 = 4
          Author: Nathan M. Abbey
History/Versions: 1.0
Called Functions: isDigit()
      Parameters: char c - The character input used to determine which column of the transition
	                       table to go to.
    Return Value: int - Returns the column the character returns from the transition table
       Algorithm: 1. Checks the input variable and assigns it it's column number based on the
	                 transition table.
				  2. Returns the column number in the transition table.
************************************************************************************************/
int char_class(char c) {

	// 'val' initially set to 7 (other). hexLetters used for hex identification
	int val = 7;
	char hexLetters[] = {'a', 'b', 'c', 'd', 'e', 'f', 'A', 'B', 'C', 'D', 'E', 'F'};

	if (isLetter(c) == 0)
		val = 0;
		
	for (int i = 0; i < 12; i++) {
		if (c == hexLetters[i]) {
			val = 1;
			break;
		}
	}

	if (c == 'x' || c == 'X')
		val = 2;

	if (c == '0')
		val = 3;

	if (isDigit(c) == 0 && c != '0')
		val = 4;
	
	if (c == '.') {
		val = 5;
	}

	if (c == '$') {
		val = 6;
	}
	return val;
}



/***********************************************************************************************
            Name: aa_func02()
         Purpose: Accepting function for the Arithmetic Variable Identifier (AVID_T) and 
		          Keywords (KW_T).
          Author: Nathan M. Abbey
History/Versions: 1.0
Called Functions: ~ isKeyWord()
			   	  ~ strlen()
      Parameters: char * lexeme - a buffer holding the token data to process
    Return Value: Token - A Token which will be a Keyword or an Arithmetic Variable Identifier;
                          depending on what the lexeme input contains.
       Algorithm: 1. Compares the lexeme to the Key Word table to see if input is a keyword.
                  2. If not, the lexeme is set as an arithmetic varibale identifier.
				  3. The arithmetic variable identifier will only register the first 8 characters
				     if the lexeme is any longer.
				  4. Method returns token.
************************************************************************************************/
Token aa_func02(char * lexeme) {

	Token temp;
	int a = isKeyWord(lexeme);

	if (a >= 0) {
		temp.attribute.kwt_idx = a;
		temp.code = KW_T;
		return temp;
	}

	temp.code = AVID_T;
	if (strlen(lexeme) < VID_LEN) {

		for (int i = 0; i < strlen(lexeme); i++) {
			temp.attribute.vid_lex[i] = lexeme[i];
		}
		temp.attribute.vid_lex[strlen(lexeme)] = '\0';
		return temp;
	}

	for (int i = 0; i < VID_LEN; i++) {
		temp.attribute.vid_lex[i] = lexeme[i];
	}
	
	temp.attribute.vid_lex[VID_LEN] = '\0';
	return temp;
}



/***********************************************************************************************
            Name: aa_func03()
         Purpose: Accepting function for the String Variable Identifiers (SVID)
          Author: Nathan M. Abbey
History/Versions: 1.0
Called Functions: ~ strlen()
      Parameters: char * lexeme - a buffer holding the token data to process
    Return Value: Token - A Token which will be a String Variable Identifier 
       Algorithm: 1. If the lexeme is larger than the VID_LEN (8), only process the first 8
	                 characters in the buffer and append $ as the end.
                  2. Otherwise, assign the lexeme to the String Variable Identifier
				  3. Append '\0' at the end to indicate end of string in C.
				  4. Return Token
************************************************************************************************/
Token aa_func03(char * lexeme) {

	Token temp;
	int i = 0;
	temp.code = SVID_T;

	if (strlen(lexeme) > VID_LEN) {
		while (i < VID_LEN - 1) {
			temp.attribute.vid_lex[i] = lexeme[i];
			i++;
		}
		temp.attribute.vid_lex[i] = '$';
		i++;
	}
	else {
		while (i < (signed)strlen(lexeme)) {
			temp.attribute.vid_lex[i] = lexeme[i];
			i++;
		}
	}
	temp.attribute.vid_lex[i] = '\0';
	return temp;
}



/***********************************************************************************************
            Name: aa_func08()
         Purpose: Accepting function for Floating Point Literals (FPL)
          Author: Nathan M. Abbey
History/Versions: 1.0
Called Functions: ~ strtod()
				  ~ aa_table[]()
      Parameters: char * lexeme - a buffer holding the token data to process
    Return Value: Token - A Token which will be a Floating Point Literal
       Algorithm: 1. Uses the strtod (string-to-decimal C function to convrert the char String
	                 into a decimal value.
                  2. Ensures the decimal value is within the appropriate ranges.
				  3. If the decimal is out of range function returns error.
                  3. Assigns the value to the flt_value of the Token, and set code to FPL_T.
                  4. Return Token
************************************************************************************************/
Token aa_func08(char * lexeme) {

	Token temp;
	double value = (double)strtod(lexeme, NULL);

	if (value > FLT_MAX || value < FLT_MIN && value != 0) {
		 return aa_table[ES](lexeme);
	}

	temp.attribute.flt_value = (float)value;
	temp.code = FPL_T;
	return temp;
}



/***********************************************************************************************
            Name: aa_func05()
         Purpose: Accepting function for Integer Literal (IL) - Decimal Constant (DIL)
          Author: Nathan M. Abbey
History/Versions: 1.0
Called Functions: ~ atoi()
      Parameters: char * lexeme - a buffer holding the token data to process
    Return Value: Token - A Token which will be a Floating Point Literal
       Algorithm: 1. Function converts the lexeme into an integer using C language function 
	                 atoi().
                  2. Ensures the integer value is within the appropriate ranges.
                  3. If the integer is out of range function returns error.
                  4. Assigns the value to the int_value of the Token, and set code to INL_T.
                  5. Return Token
************************************************************************************************/
Token aa_func05(char * lexeme) {

	Token temp;
	int  lexDecimal = atoi(lexeme);

	if (lexDecimal < SHRT_MIN || lexDecimal > SHRT_MAX)
		return aa_table[ES](lexeme);

	temp.attribute.int_value = lexDecimal;
	temp.code = INL_T;
	return temp;
}



/***********************************************************************************************
            Name: aa_func11()
         Purpose: Accepting function for Integer Literal (IL) - Hexadecimal Constant (HIL)
          Author: Nathan M. Abbey
History/Versions: 1.0
Called Functions: ~ atolh()
                  ~ strlen()
      Parameters: char * lexeme - a buffer holding the token data to process
    Return Value: Token - A Token which will be a Floating Point Literal
       Algorithm: 1. Converts the lexeme into a long integer using local function atolh(). 
	              2. Checks for Hexadecimal format; error if not correct syntax 
				  3. Ensures the value is within bounds
				  4. Set the int_value and code.
				  5. Return Token
************************************************************************************************/
Token aa_func11(char * lexeme) {

	Token temp;
	double hexDec = atolh(lexeme);
	int a = strlen(lexeme);

	// If the input is in HEX format (0x...) it cannot be shorter than 3 in length. 
	if (lexeme[1] == 'x' || lexeme[1] == 'X')
		if (a < 3)
			return aa_table[ES](lexeme);

	if (hexDec > SHRT_MAX || hexDec < SHRT_MIN)
		return aa_table[ES](lexeme);

	temp.attribute.int_value = (int)hexDec;
	temp.code = INL_T;
	return temp;
}



/***********************************************************************************************
            Name: aa_func12()
         Purpose: Accepting function for Error Token (ERR_T)
          Author: Nathan M. Abbey
History/Versions: 1.0
Called Functions: ~ strlen()
      Parameters: char * lexeme - a buffer holding the token data to process
    Return Value: Token - A Token which will be a Floating Point Literal
       Algorithm: 1. Sets the Token coxe to ER_T
				  2. If the lexeme is larger than ERR_LEN (20), only store the first 17
				     characters followed by 3 periods (.).
				  3. Otherwise, set the err_lex to be the same as th lexeme.
				  4. Append '\0' to the end of the string
				  5. Return Token
************************************************************************************************/
Token aa_func12(char * lexeme) {

	Token temp;
	int i = 0;
	temp.code = ERR_T;

	if (strlen(lexeme) > ERR_LEN) {

		for (i ; i < 17; i++) {
			temp.attribute.err_lex[i] = lexeme[i];
		}
		for (i ; i < 20; i++) {
			temp.attribute.err_lex[i] = '.';
		}
		temp.attribute.err_lex[20] = '\0';
	}
	else {
		for (i ; i < strlen(lexeme); i++) {
			temp.attribute.err_lex[i] = lexeme[i];
		}
	}
	temp.attribute.err_lex[i] = '\0';
	return temp;
}


/***********************************************************************************************
             Name: aa_func13()
          Purpose: Accepting function for Error Token wuth no restract (ERR_T)
           Author: Nathan M. Abbey
 History/Versions: 1.0
 Called Functions: ~ strlen()
       Parameters: char * lexeme - a buffer holding the token data to process
     Return Value: Token - A Token which will be a Floating Point Literal
        Algorithm: 1. Sets the Token coxe to ER_T
                   2. If the lexeme is larger than ERR_LEN (20), only store the first 17
                      characters followed by 3 periods (.).
                   3. Otherwise, set the err_lex to be the same as th lexeme.
                   4. Append '\0' to the end of the string
                   5. Return Token
************************************************************************************************/
Token aa_func13(char * lexeme) {

	Token temp;
	int i = 0;
	temp.code = ERR_T;

	if (strlen(lexeme) > ERR_LEN) {

		for (i; i < 17; i++) {
			temp.attribute.err_lex[i] = lexeme[i];
		}
		for (i; i < 20; i++) {
			temp.attribute.err_lex[i] = '.';
		}
	}
	else {
		for (i; i < strlen(lexeme); i++) {
			temp.attribute.err_lex[i] = lexeme[i];
		}
	}
	temp.attribute.err_lex[20] = '\0';
	return temp;
}



/***********************************************************************************************
            Name: isKeyWord()
         Purpose: Returns the index number of the keyword table if there is a match.
          Author: Nathan M. Abbey
History/Versions: 1.0
Called Functions: ~ strcmp()
      Parameters: char * lexeme - a buffer holding the token data to process
    Return Value: int - Returns index number of keyword table, or -1 on no match.
       Algorithm: 1. Sets the isKeyWord variable to -1 (false)
                  2. Compares the lexeme to the entries in the Keyword Table. 
				  3. If there, set isKeyWord to the index number and return.
				  4. Otherwise, return -1.
************************************************************************************************/
int isKeyWord(char * lexeme) {

	int isKeyWord =-1;

	for (int i = 0; i < KWT_SIZE; i++) {
		if (strcmp(kw_table[i], lexeme) == 0) {
			isKeyWord = 0;
			return i;
		}
	}
	return isKeyWord;
}



/***********************************************************************************************
            Name: atolh()
         Purpose: Returns the index number of the keyword table if there is a match.
          Author: Nathan M. Abbey
History/Versions: 1.0
Called Functions: ~ strcmp()
      Parameters: char * lexeme - a buffer holding the token data to process
    Return Value: long - Returns long integer representation of the lexeme.
       Algorithm: 1. Converts the lexeme to a long integer using the C Language Function 
	                 strtol().
				  2. Returns the long integer represntation.
************************************************************************************************/
long atolh(char * lexeme) {
	long hexValue = strtol(lexeme, NULL, 16);
	return hexValue;
}



/***********************************************************************************************
            Name: isDigit()
         Purpose: Returns 0 if the character is a digit (0-9), -1 returned on no match.
          Author: Nathan M. Abbey
History/Versions: 1.0
Called Functions: none
      Parameters: char c - Character input to determine if it is a digit or not.
    Return Value: int - Returns 0 on match and -1 on no match
       Algorithm: 1. Sets the isDigit to -1 (false)
	              2. Compares the character input to the digits[] array looking for a match.
				  3. If there is a match isDigit is set to 0, otherweise it is set to -1.
				  4. Return the isDigit variable.
************************************************************************************************/
int isDigit(char c) {

	int isDigit = -1;
	char digits[] = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };

	for (int i = 0; i < 10; i++) {
		if (c == digits[i]) {
			isDigit = 0;
			break;
		}
	}
	return isDigit;
}



/***********************************************************************************************
            Name: isLetter()
         Purpose: Returns 0 if the character is a digit (0-9), -1 returned on no match.
          Author: Nathan M. Abbey
History/Versions: 1.0
Called Functions: none
      Parameters: char c - Character input to determine if it is a letter or not.
    Return Value: int - Returns 0 on match and -1 on no match
       Algorithm: 1. Sets the isDigit to -1 (false).
	              2. Compares the character to char1[](lowercase) and char2[](uppercase) arrays.
				  3. If there is a match set isLetter to 0, otherwise it stays at -1.
				  4. Return isLetter value.
************************************************************************************************/
int isLetter(char c) {

	int isLetter = -1;
	char char1[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
	char char2[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	int length = sizeof(char) * 26;

	for (int i = 0; i < length; i++) {
		if (c == char1[i]) {
			isLetter = 0;
			break;
		}
	}
	for (int i = 0; i < length; i++) {
		if (c == char2[i]) {
			isLetter = 0;
			break;
		}
	}
	return isLetter;
}
