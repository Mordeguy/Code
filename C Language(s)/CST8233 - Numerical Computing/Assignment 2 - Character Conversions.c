/* FILE HEADER ------------------------------------------------------------------------------------------
             File Name:  ass2.cpp
               Version:  2.0
   Author/Student Name:  Nathan M. Abbey
        Student Number:  040 - 557 - 192
Course Name and Number:  CST8219 - C++ Programming
           Lab Section:  301
     Assignment Number:  02
       Assignment Name:  Floating Point Spy
              Due Date:  December 10, 2017 - 23:59
       Submission Date:  December 10, 2017 - 16:00
       Professors Name:  Mr. Andrew Tyler
               Purpose:  This program takes a floating point number input by the user and outputs the
						 binary format, the Big and little-endian hexadecimal format, the exponent value,
						 and the normalized mantissa (including the hidden 1) value.
-------------------------------------------------------------------------------------------------------*/
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

/* Struct inside Union used to manage the different components of a IEEE Binary representation of a Float number*/
typedef union {
	float convert;
	struct {
		unsigned int man:23;
		unsigned int exp:8;	
	} component;
} converter;


/*Function Prototypes ----------------------------------------------------------------------------*/
int toHex(char * c);
void format(char * c);
float manHiddenOne(char * c);
void toBinary(char sn, unsigned int num, unsigned int num2);



/******************************************************************************************************
 Function Name:  main
       Purpose:  This main method proivdes the user interaction with the program. It handles the menu
	             and allows the user to input the number they wish to be converted. The menu will
				 continue to loop until the user chooses 2 to exit the program.
 In Parameters:  Void
Out Parameters:  int ~ Returns 0 on successful completion of the program (user chooses 2)
       Version:  1.0
        Author:  Nathan M. Abbey
*******************************************************************************************************/
int main(void) {

	converter decToIEEE = { 0 };
	float floatChange = 0;
	char sign;
	int choice = 0;
	do {
		printf("1  =  convert float\n2  -  quit\n");
		scanf("%d", &choice);
		printf("\n");

		if (choice == 1) {
			printf("Please enter the number to convert: ");
			scanf("%f", &floatChange);
			decToIEEE.convert = floatChange;

			if (floatChange < 0)
				sign = '1';
			else
				sign = '0';

			printf("Float number is:\t %e", floatChange);
			printf("\nBinary:\t\t\t ");
			toBinary(sign, decToIEEE.component.exp, decToIEEE.component.man);
		}
	} while (choice == 1);
	if (choice == 2)
		exit(0);
}



/******************************************************************************************************
 Function Name:  toBinary
       Purpose:  Takes in the value of the signed bit, exponent bits, and mantissa bits and converts
	             the decimal value in to IEEE Binary format.
 In Parameters:  char sn ~ The signed bit value (0 for poistive, 1 for negative)
                 unsigned int num ~ The exponent value in decimal format
				 unsigned int num2 ~ The mantissa value in decimal format
Out Parameters:  None
       Version:  1.0
        Author:  Nathan M. Abbey
*******************************************************************************************************/
void toBinary(char sn, unsigned int num, unsigned int num2)
{
	char man[24];
	char mantissa[24];
	char realExp [9];
	char exp[10];
	char finalBinary[33];
	int index = 0;
	char x = 'X';
	unsigned int mask = 512;  
	unsigned int mask2 = 8388608;
	int copyIndex = 2;
	
	while (mask > 0) {
		if ((num & mask) == 0) 
			exp[index++] = '0';
		else 
			exp[index++] = '1';
		
		mask = mask >> 1;  
	}

	for (int i = 0; i < 8; i++) {
		realExp[i] = exp[copyIndex++];
	}
	realExp[8] = '\0';
	index = 0;
	
	while (mask2 > 0) {
		if ((num2 & mask2) == 0) 
			man[index++] = '0';
		else 
			man[index++] = '1';
		mask2 = mask2 >> 1;
	}

	for (int i = 0; i < 23; i++) {
		mantissa[i] = man[i + 1];
	}
	mantissa[23] = '\0';
	
	index = 0;
	finalBinary[index++] = sn;

	for (int i = 0; i < 8; i++) {
		finalBinary[index++] = realExp[i];
	}

	for (int i = 0; i < 23; i++) {
		finalBinary[index++] = mantissa[i];
	}

	finalBinary[index] = '\0';
	index = 0;

	for (index; index < 32;) {
		printf("%c" , finalBinary[index++]);
		printf("%c", finalBinary[index++]);
		printf("%c", finalBinary[index++]);
		printf("%c", finalBinary[index++]);
		printf(" ");
	}

	format(finalBinary);
	printf("exponent =  %d", num);
	float hiddenOne = manHiddenOne(mantissa);
	printf("\nnormalized mantissa (including the hidden one bit) = %.7f\n\n\n", hiddenOne);
}



/******************************************************************************************************
 Function Name:  format
       Purpose:  Takes in char array representation of a 32-bit binary number and breaks it in to
	             8-bit portions and determines the Hexadecimal value of all portions and displays the
				 value on screen.
 In Parameters:  char * c ~ A char array holding an 8-bit binary representation of a number
Out Parameters:  int ~ Returns the value of the 8-bit binary value
       Version:  1.0
        Author:  Nathan M. Abbey
*******************************************************************************************************/
void format(char * ch) {

	int a, b, c, d;
	int index = 0, i = 0;
	char temp[9];
	
	while (i < 8) {
		temp[index++] = ch[i++];
	}
	temp[index] = '\0';
	a = toHex(temp);
	index = 0;

	while (i < 16) {
		temp[index++] = ch[i++];
	}
	temp[index] = '\0';
	b = toHex(temp);
	index = 0;

	while (i < 24) {
		temp[index++] = ch[i++];
	}
	temp[index] = '\0';
	c = toHex(temp);
	index = 0;

	while (i < 32) {
		temp[index++] = ch[i++];
	}
	temp[index] = '\0';
	d = toHex(temp);

	printf("\nBig-endian Hex:\t\t %02X  %02X  %02X  %02X\n", a, b, c, d);
	printf("Little-endian Hex:\t %02X  %02X  %02X  %02X\n", d, c, b, a);
}



/******************************************************************************************************
 Function Name:  toHex
       Purpose:  Converts an 8 char long representation of an 8-bit binary number in to a decimal 
	             format.  
 In Parameters:  char * c ~ A char array holding an 8-bit binary representation of a number
Out Parameters:  int ~ Returns the value of the 8-bit binary value 
       Version:  1.0
        Author:  Nathan M. Abbey
*******************************************************************************************************/
int toHex(char * c) {

	int decimal = 0;
	int index = 0;

	for (int i = 128; i > 0; i /= 2) {
		if (c[index++] == '1')
			decimal += i;
	}
	return decimal;
}



/******************************************************************************************************
 Function Name:  manHiddenOne
       Purpose:  Takes in the char array of the mantissa value in binary and calculates the decimal
	             value, adds the hidden one, and returns the value in Float format.
 In Parameters:  char * c ~ A char array holding the mantissa value in binary format.
Out Parameters:  float ~ Returns the decimal value for the mantissa plus the hidden one.
       Version:  1.0
        Author:  Nathan M. Abbey
*******************************************************************************************************/
float manHiddenOne(char * c) {

	int a = 23, index = 0;
	float b = 0;

	for (float i = 0.5; index < 23; i /= 2) {
		if (c[index++] == '1')
			b += i;
	}
	b += 1;
	return b;
}