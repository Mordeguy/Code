/*******************************************************************************************
      FILE  :  Assign3.c
   PROGRAM  :  Document Encryption/Decryption Program
    AUTHOR  :  Nathan M. Abbey
  DUE DATE  :  December 10, 2016
    COURSE  :  CST8234 - C Language
   PROJECT  :  Assignment 3
     NOTES  :  This program allows a user to encrypt or decrypt a file using command line
     	     arguments. The user has to type: ./assign3 [OPTIONS] SOURCE_FILE 
     	     DESTINATION_FILE. This will either decode or encrypt a file to another file.
     	     Encrypting involves adding the KEY number to the ascii value; decrypting
     	     involves subtracting the KEY number from the ascii value of the character.
     	     The program options are '-d' for decrypt and '-e' for encrypt.
     	     EXAMPLE: /assign3 [-d OR -e] file1 file2

*Variables 

	Main:  char - a - holds a character and passes it to ascii to convert to numeric value
		  int - ascii - used to convert a character into its ascii numeric value   
		  int - c - used by getOpt to ensure the input is valid
		  int - r - used by fscanf to read a file and end at the EOF
   		  char* - optString - existing function that reads options put in by user
   		  FILE - fIn - a file object used to read data into the program from a file
   		  FILE - fOut - a file object used to write data out of the pgoram to a file
*******************************************************************************************/

/*****************************************************
 Library Imports
*****************************************************/
#include<stdio.h>
#include<stdlib.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

/*****************************************************
 Global Variable Declaration
*****************************************************/
const int KEY = 8234;

/*****************************************************
 Function Prototypes
*****************************************************/

/*****************************************************
 Main
*****************************************************/
int main (int argc, char **argv) {
  	
  	int ascii, c, r = NULL;
  	static const char *optString = "deh";
	char a;
	FILE *fIn = fopen(argv[2], "r");
	FILE *fOut = fopen(argv[3], "w");


	// Displays proper syntax example if program call is misused -------------|
  	if (argc >4 || argc < 2) {
  		printf("\n***Syntax ERROR: ./assign3 [OPTIONS] SOURCE DESTINATION***\n\n");
  		return(-1);
  	}
  	
  	// If the file in is null; displays error and returns -1 -----------------|
     if (fIn == NULL) {				
		printf("\n***File ERROR: Could Not Open the File***\n\n");
		return (-1);
     }
  	
  	
  	// Looks for letters "deh" with a "-" before it; choice does different things
  	while ((c = getopt(argc, argv, optString)) != -1) 
    		
    		switch (c) {
    		
    				case 'd':
     			
					while (r != EOF) {	
				    		r = fscanf(fIn, "%c", &a);
						ascii = a;
						ascii = ascii - KEY % 256;
						fputc(ascii, fOut);
					
					}
						printf("\nFile successfully decrypted.\n\n");
      	    			break;
      	    			
      			case 'e':
      			
      				while (r != EOF) {	
			    			r = fscanf(fIn, "%c", &a);
						ascii = a;
						ascii = ascii + KEY % 256;
						fputc(ascii, fOut);
						
					}
					printf("\nFile successfully encrypted.\n\n");
       	    			    			
            			break;
            			
            			
      			case 'h':
      	    			
      	    			break;	
      	
      			default:
      			
            			printf("\n**ERROR: OPTIONS not recognized.***\n\n");
            			return (-1);      
   		} 


	
	
	
	
return 0;



}
