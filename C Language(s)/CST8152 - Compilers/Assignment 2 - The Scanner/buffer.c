/*---------------------------------------------------------------------------------------------------------
    File Name: buffer.c
     Compiler: Visual Studio 2015
       Author: Nathan M. Abbey
       Course: CST 8152 - Compilers, Lab Section: 11
   Assignment: 01
         Date: September 29, 2017
    Professor: Svillen Ranev
      Purpose: This program mimics a buffer in memory. It loads data in to the memory and allows
               the user to access it as needed. Once completed, the program deallocates the used
               memory.
Function List: 1. b_allocate()						 12. b_isempty()
			   2. b_addc()							 13. b_eob()
			   3. b_clear()						     14. b_getc()
			   4. b_free()					         15. b_print()
			   5. b_isFull()			   			 16. b_compact()
			   6. b_limit()						     17. b_rflag()
			   7. b_capacity()				         18. b_retract()
			   8. b_mark()							 19. b_reset()
			   9. b_mode()							 20. b_getoffset
			  10. b_incfactor						 21. b_rewind
			  11. b_load()			                 22. b_location()
----------------------------------------------------------------------------------------------------------*/
#ifdef _MSC_VER
#endif

#define _CRT_SECURE_NO_WARNINGS
#define MULT_MAX 100
#define ADD_MAX 255
#define _CRTDBG_MAP_ALLOC
#include "buffer.h"
#include <string.h>


/************************************************************************************************
            Name: b_allocate
         Purpose: To allocate memory in the heap for a Buffer object and a char pointer. This
                  method also checks for valid data and sets appropriate Buffer fields.
          Author: Nathan M. Abbey
History/Versions: 2.0
Called Functions: 1. calloc()
                  2. malloc()
				  3. free()
      Parameters: short init_capacity - If value exceeds SHRT_MAX or under 0 it will return
				                        NULL. This sets the capacity of the Buffer.
					  char inc_factor - Can accept a valid input and use as the amount to increase
									    the Buffer by depending on which mode it is in.
										       ->     0: Fixed Mode
										       -> 1-255: Additive Mode
										       -> 1-100: Multiplicative Mode
                          char o_mode - Accepts only 3 values and sets the Buffer memory
                                        increment mode.
										       -> f: Fixed Mode
										   	   -> a: Additive Mode
											   -> m: Multiplicative Mode
     Return Value: - Returns a pointer to a initialized Buffer structure on success.'
                   - Returns NULL if bad data is put in as parameters
        Algorithm: 1. Ensure parameters input are valid
                   2. Allocate memory for a Buffer object and a character array
                   3. Initialize Buffer object variables and point it's char pointer to the
                      character array in the heap.
                   4. Return the initialized Buffer object
************************************************************************************************/
Buffer *b_allocate(short init_capacity, char inc_factor, char o_mode) {

	
	
	/* These 2 ifs ensure the paramters being passed in are valid. */
	if (o_mode == 'f' && init_capacity <= 0) {
		return NULL;
	}

	// Checks to ensure the mode is one fo the specified ones allowed
	if (o_mode != 'a' && o_mode != 'm' && o_mode != 'f') {
		return NULL;
	}


	/*If the init_capacity is greater SHRT_MAX -1 or less than 0, returns NULL*/
	if (init_capacity > (SHRT_MAX - 1) || init_capacity < 0) {
		return NULL;
	}

	/*Allocating enough heap memory for the Buffer object and initializes values to 0, using
	the CALLOC function; returns NULL if fails.*/
	Buffer *buff = (Buffer*)calloc(1, sizeof(Buffer));
	if (buff == NULL) {
		free(buff);
		return NULL;
	}

	/*Allocating enough heap memory for the char array using the MALLOC function. Returns NULL
	if fails.*/
	char *cb_head = (char*)malloc((sizeof(char)) * init_capacity);
	if (cb_head == NULL) {
		free(cb_head);
		return NULL;
	}

	/*Checks to see if 'o_mode' equals 'f', or if 'inc_factor' is 0. If so, set
	the 'mode', 'inc_factor, to 0.*/
	if (o_mode == 'f' || inc_factor == 0) {
		if (inc_factor == 0 || inc_factor != 0) {
			buff->mode = 0;
			buff->inc_factor = 0;
			o_mode = 0;
		}
	}

	/*If o_mode is in additive mode it sets the 'mode' to 1 and 'inc_factor' to the
	inc_factor parameter.*/
	if (o_mode == 'a') {
		if ((unsigned char)inc_factor < 0 || (unsigned char)inc_factor > ADD_MAX) {
			free(buff);
			return NULL;
		}

		buff->mode = 1;
		buff->inc_factor = inc_factor;
	}


	/*If o_mode is in multiplicative mode it sets the 'mode' to -1 and 'inc_factor' to the
	inc_factor parameter.*/
	if (o_mode == 'm') {
		if (inc_factor < 0 || inc_factor > MULT_MAX) {
			return NULL;
		}
		else {
			buff->mode = -1;
			buff->inc_factor = inc_factor;
		}
	}

	/*Sets the Buff variable 'capacity' to 'init_capacity' and sets the Buff 'cb_head'
	variable to 'cb_head'.*/
	buff->capacity = init_capacity;
	buff->cb_head = cb_head;

	/*Returns the newly initiazed Buffer object*/
	return buff;
}







/*********************************************************************************************** 
            Name: b_addc
         Purpose: To take a character and add it to the character array in the Heap if it
                  is not full. If it is full then it will increment the array depending on the
                  increment mode the buffer is in. If it is full and cannot add any more it will
                  return LOAD_FAIL.
          Author: Nathan M. Abbey
History/Versions: 2.0
Called Functions: 1. realloc()
                  2. strcpy()
      Parameters:  pBuffer const pBD - A pointer to the buffer
                         char symbol - A char that will be added to the end of the array if it
                                       is possible.
    Return Value:  - Returns a pointer to a initialized Buffer structure on success.'
                   - Returns NULL if bad data is put in as parameters, or Buffer is full
       Algorithm: 1. Ensure parameters input are valid
	              2. Checks the pBuffer if it's NULL
                  3. Allocate memory for a Buffer object and character array
                  4. Initialize Buffer object variables and point it's 'cb_head' to the
                     dynamically alllocated character array.
                  5. Return the initialized Buffer object
************************************************************************************************/
pBuffer b_addc(pBuffer const pBD, char symbol) {

	/*If the Buffer object is full, or if the Buffer object equals NULL, return NULL.*/
	if (pBD->addc_offset == SHRT_MAX - 1 || pBD == NULL) {
		return NULL;
	}

	/*Resets the 'r_flag' variable to 0.*/
	pBD->r_flag = 0;

	/*If in fixed mode, the 'capacity' equals the 'addc_offset' are equal, but above zero. Return NULL
	as the Buffer is full.*/
	if (pBD->mode == 0 && pBD->capacity == pBD->addc_offset && pBD->capacity > 0) {
		return NULL;
	}

	/*If the 'capacity' and 'addc_offset' are equal, and greater than 0, it means the Buffer is full
	and calculates the new capacity. Casts 'inc_factor' to an un-signed CHAR to handle any other
	input values that would fail with a SIGNED CHAR.*/
	if (pBD->capacity == pBD->addc_offset  && pBD->addc_offset > 0 || pBD->capacity == 0) {
		short newCapacity, spaceAvailable, newIncrement;
		unsigned char a = (unsigned char)pBD->inc_factor;

		/*Passes the 'mode' variable to the SWITCH statement.*/
		switch (pBD->mode) {

			/*If the 'mode' is in additive, it adds up the new capacity continues. */
		case 1:
			newCapacity = (a + pBD->capacity);
			if (newCapacity <= 0) {
				return NULL;
			}

			// Ensures the new capacity is greater than 0 and less than SHRT_MAX -1.
			if (newCapacity > 0 && newCapacity < SHRT_MAX - 1) {

				/*REALLOCs with the new capacity.It will return NULL if the REALLOC fails.*/
				char *tempPointer = realloc(pBD->cb_head, newCapacity);
				if (tempPointer == NULL) {
					return NULL;
				}

				/*Sets the cb_head to the new memory space, updates the 'capacity' and sets
				the 'r_flag' value to SET_R_Flag (1)*/
				pBD->cb_head = tempPointer;
				pBD->capacity = newCapacity;
				pBD->r_flag = SET_R_FLAG;
			}
			else {

				/*If the size of the Buffer is attempted to be increased beyond it's possible value
				it sets the capacity to SHRT_MAX - 1.*/
				pBD->capacity = SHRT_MAX - 1;
			}
			break;


			/*If the 'mode' is in multiplicative, it calculates the new capacity continues. */
		case -1:

			spaceAvailable = (SHRT_MAX - 1) - pBD->capacity;
			newIncrement = spaceAvailable * pBD->inc_factor / 100;
			newCapacity = pBD->capacity + newIncrement;

			/*If the 'spaceAvailable' is less than 'inc_factor' then add the 'spaceAvailable'
			to the existing 'capacity' and carry on.*/
			if (spaceAvailable <= pBD->inc_factor) {
				newCapacity = pBD->capacity + spaceAvailable;
			}

			/*If 'inc_factor' is not equal 0, it means REALLOC.*/
			if (pBD->inc_factor != 0) {

				/*REALLOCs with the new capacity.It will return NULL if the REALLOC fails.*/
				char *tempPointer = realloc(pBD->cb_head, newCapacity);
				if (tempPointer == NULL) {
					return NULL;
				}
				/*Copies the old String in to the newly allocated larger memory block.*/
				strcpy(tempPointer, pBD->cb_head);
				pBD->cb_head = tempPointer;


				/*updates the 'capacity' and sets the 'r_flag' value to SET_R_Flag(1)*/
				pBD->capacity = newCapacity;
				pBD->r_flag = SET_R_FLAG;

			}
			else {
				/*If the size of the Buffer is attempted to be increased beyond it's possible value
				it sets the capacity to SHRT_MAX - 1.*/
				pBD->capacity = SHRT_MAX - 1;
			}
			break;
		}
	}

	/*Adds the 'symbol' to the 'addc_offset' index of the Buffer and increases the 'addc_offset'.*/
	pBD->cb_head[pBD->addc_offset] = symbol;
	pBD->addc_offset++;
	return pBD;
}







/***********************************************************************************************
Name: b_clear
Purpose: Re-initializes all appropriate data to 0 to set the pointer to the beginning
of the array.
Author: Nathan M. Abbey
History/Versions: 1.0
Called Functions: none
Parameters: pBuffer const pBD - A pointer to the buffer
Return Value: - Returns 0 on success.'
- Returns -1 if bad data is put in as parameters
Algorithm: 1. Ensure parameters input are valid
2. Sets intended values to 0
3. Return 0 on success
************************************************************************************************/
int b_clear(Buffer * const pBD) {

	/*If the parameter passed in is NULL, return -1*/
	if (pBD == NULL) {
		return -1;
	}

	/*Sets 'addc_offset', 'get_coffset', and 'markc_offset' to 0.*/
	pBD->addc_offset = 0;
	pBD->getc_offset = 0;
	pBD->markc_offset = 0;
	pBD->eob = 0;

	/*Returns 0 on success*/
	return 0;
}







/***********************************************************************************************
Name: b_free
Purpose: De-allocates and frees the memory in heap that the Buffer and character array
occupy.
Author: Nathan M. Abbey
History/Versions: 1.0
Called Functions: 1. free()
Parameters: pBuffer const pBD - A pointer to the buffer
Return Value: void
Algorithm: 1. Ensure parameters input are valid
2. Uses the 'free' command to de-alocate the heap memory
************************************************************************************************/
void b_free(Buffer * const pBD) {


	/*Frees the memory allocated by 'cb_head' and then sets it to NULL. Then it frees the parameter
	passed in 'pBD'*/
	pBD->cb_head = NULL;
	free(pBD->cb_head);
	free(pBD);

}






/***********************************************************************************************
Name: b_isFull
Purpose: Checks to see if the Buffer memory is full.
Author: Nathan M. Abbey
History/Versions: 1.0
Called Functions: none
Parameters: pBuffer const pBD - A pointer to the buffer
Return Value: 1 on full, 0 on not full
Algorithm: 1. Ensure parameters input are valid.
2. Compares the addc_offset to capacity to see if Buffer is full or not
3. Returns 1 or 0 depending on outcome
************************************************************************************************/
int b_isfull(Buffer * const pBD) {

	/*If the parameter is NULLm it means nothing is in the list.*/
	if (pBD == NULL) {
		return 0;
	}

	/*If the 'addc_offset' and 'capacity' is equal it means the Buffer is full, return 1*/
	if (pBD->addc_offset == pBD->capacity) {
		return 1;
	}
	else {

		/*Returns 0 if the Buffer is not full*/
		return 0;
	}
}








/***********************************************************************************************
Name: b_limit
Purpose: Returns the current limit of the character buffer
Author: Nathan M. Abbey
History/Versions: 1.0
Called Functions: none
Parameters: pBuffer const pBD - A pointer to the buffer
Return Value: The current limit being used by the Heap memory
Algorithm: 1. Ensure parameters input are valid.
2. Returns the current size of memory used by heap
************************************************************************************************/
short b_limit(Buffer * const pBD) {

	/*If pBD is NULL it means the memory used by the Heap is 0*/
	if (pBD == NULL) {
		return 0;
	}

	// Returns the 'addc_offset', which is the memory in the Heap being used.
	return pBD->addc_offset;
}







/***********************************************************************************************
Name: b_capacity
Purpose: Returns the current capacity of the character buffer
Author: Nathan M. Abbey
History/Versions: 1.0
Called Functions: none
Parameters: pBuffer const pBD - A pointer to the buffer
Return Value: The current limit being used by the Heap memory
Algorithm: 1. Ensure parameters input are valid.
2. Returns the current capacity of memory allocated in the heap
************************************************************************************************/
short b_capacity(Buffer * const pBD) {

	/*If the parameter passed in is NULL it means the 'capacity' is 0; meaning there is nothing
	in the Buffer*/
	if (pBD == NULL) {
		return 0;
	}

	/*Returns the capacity of the Buffer*/
	return pBD->capacity;
}







/***********************************************************************************************
Name: b_mark
Purpose: Sets the index number of the mark variable in the Buffer object
Author: Nathan M. Abbey
History/Versions: 1.0
Called Functions: none
Parameters: pBuffer const pBD - A pointer to the buffer
short mark - The insdex of a specified character in the array
Return Value: The index of a specified character in the array
Algorithm: 1. Ensure parameters input are valid.
2. Returns the index of a specified chcracter in the array.
************************************************************************************************/
short b_mark(Buffer * const pBD, short mark) {

	/*If the parameter passed in is NULL, set 'mark' to 0*/
	if (pBD == NULL) {
		return 0;
	}

	/*If the 'mark' value is less than 0 or greater than 'addc_offset' return -1*/
	if (mark < 0 || mark > pBD->addc_offset) {
		return -1;
	}

	/*Sets the 'markc_offset' to the 'mark' variable and return 'mark'.*/
	pBD->markc_offset = mark;
	return mark;
}









/***********************************************************************************************
Name: b_mode
Purpose: Returns the mode variable of the Buffer object
Author: Nathan M. Abbey
History/Versions: 1.0
Called Functions: none
Parameters: pBuffer const pBD - A pointer to the buffer
Return Value: The mode in numeric form (1 ~ additive, 0 ~ fixed, -1 ~ multiplicative)
Algorithm: 1. Returns the mode variable of the Buffer
************************************************************************************************/
int b_mode(Buffer * const pBD) {

	/*Returns the current 'mode' of the Buffer object*/
	return pBD->mode;
}







/***********************************************************************************************
Name: b_incfactor
Purpose: Returns the 'inc_factor' variable of the Buffer object
Author: Nathan M. Abbey
History/Versions: 1.0
Called Functions: none
Parameters: pBuffer const pBD - A pointer to the buffer
Return Value: - Returns the inc_factor fo the Buffer object on success
- Returns number 256 on failure
Algorithm: 1. Returns the inc_factor variable of the Buffer
************************************************************************************************/
size_t b_incfactor(Buffer * const pBD) {

	/*If the 'inc_factor' is less than 0, return 256.*/
	if ((unsigned char)pBD->inc_factor < 0) {
		return 256;
	}
	else {

		/*If 'inc_factor' is valid, return 'inc_factor'.*/
		return (unsigned char)pBD->inc_factor;
	}
}







/***********************************************************************************************
Name: b_load
Purpose: Sets the index number of the mark variable in the Buffer object
Author: Nathan M. Abbey
History/Versions: 1.0
Called Functions: 1. b_addc()
2. g_getc()
Parameters: pBuffer const pBD - A pointer to the buffer
short mark - The insdex of a specified character in the array
Return Value: - The index of a specified character in the array on success
- LOAD_FAIL is returned upon failure
Algorithm: 1. Ensure parameters input are valid.
2. Ensures there is room on the buffer to add to
3. Takes a valid character and sends it to add_c() to add to the array
************************************************************************************************/
int b_load(FILE * const fi, Buffer * const pBD) {

	/*Sets a temporary char to value 'x'*/
	char tempChar = 'x';

	/*Will run through until 'tempChar' is equal to EOF (end of file).*/
	while (tempChar != EOF) {

		/*If the Buffer is full*/
		if (pBD->addc_offset == pBD->capacity) {

			/*Return LOAD_FAIL is the mode is 0(fixed) or if in additive mode and the 'inc_factor'
			is 0.*/
			if (pBD->mode == 0 || pBD->mode == 1 && pBD->inc_factor == 0) {
				return LOAD_FAIL;
			}

			/*If the 'mode' is not fixed(0) and the file reaches the end-of-file, return LOAD_FAIL.*/
			if (pBD->mode != 0 && feof(fi)) {
				return LOAD_FAIL;
			}
		}

		/*Assigns a 'char' from the file to 'tempChar'*/
		tempChar = (char)fgetc(fi);

		/*Checks if the file is at the end, breaks if is.*/
		if (feof(fi)) {
			break;
		}

		/*Jumps in to the method b_addc(), feeding in the Buffer and the char located in 'tempChar'.
		Returns LOAD_FAIL is this doesn't work for some reason.*/
		if (b_addc(pBD, tempChar) == NULL) {
			return LOAD_FAIL;
		}
	}

	/*Returns the 'addc_offset' upon successful completion.*/
	return pBD->addc_offset;
}







/***********************************************************************************************
Name: b_isempty
Purpose: Checks to see if the chacracter array of the Buffer is empty
Author: Nathan M. Abbey
History/Versions: 1.0
Called Functions: none
Parameters: pBuffer const pBD - A pointer to the buffer
Return Value: 1 if the array is full, 0 if it is not
Algorithm: 1. Ensure parameters input are valid.
2. Return 1 for empty, 0 for not
************************************************************************************************/
int b_isempty(Buffer * const pBD) {

	/*If the buffer is empty return 1*/
	if (pBD->addc_offset == 0) {
		return 1;
	}
	else {

		/*If the buffer is not empty return 0*/
		return 0;
	}
}








/***********************************************************************************************
Name: b_eob
Purpose: Checks to see if the chacracter array of the Buffer is empty
Author: Nathan M. Abbey
History/Versions: 1.0
Called Functions: The number the EOB variable is set to. (0 for not at EOF, 1 for at EOF)
Parameters: pBuffer const pBD - A pointer to the buffer
Return Value: 1 if the array is full, 0 if it is not
Algorithm: 1. Returns the EOB variable to the calling function.
************************************************************************************************/
int b_eob(Buffer * const pBD) {

	/*Returns the Buffer objects 'eob' variable to the calling method.*/
	return pBD->eob;
}






/***********************************************************************************************
Name: b_getc
Purpose: Returns a character at the current 'cb_head' offset
Author: Nathan M. Abbey
History/Versions: 1.0
Called Functions: 1. b_getoffset()
Parameters: pBuffer const pBD - A pointer to the buffer
Return Value: -2 ~ Buffer pointer is NULL, -1 ~ the Buffer is full, or the character at
index
Algorithm: 1. Ensure parameters input are valid.
2. Returns the specific character based on the circumstances
************************************************************************************************/
char b_getc(Buffer * const pBD) {

	char temp = *(pBD->cb_head + pBD->getc_offset);

	/*If the Buffer object passed in is NULL return -2*/
	if (pBD == NULL) {
		return -2;
	}

	/*If the Buffer is full, set 'EOB' to 1 and return -1*/
	if (pBD->getc_offset == pBD->addc_offset) {
		pBD->eob = 1;
		return -1;
	}

	/*Returns the CHAR at the 'getc_offset'*/
	pBD->getc_offset++;
	return temp;
}









/***********************************************************************************************
Name: b_print
Purpose: Cycles through the array and prints the contents in order, or else prints
'Buffer empty'.
Author: Nathan M. Abbey
History/Versions: 1.0
Called Functions: none
Parameters: pBuffer const pBD - A pointer to the buffer
Return Value:              -1 ~ Returned if the Buffer pointer parameter is NULL
int addc_offset ~ The number of characters printed
Algorithm: 1. Ensure parameters input are valid.
2. Print all the characters in the array sequentially
************************************************************************************************/
int b_print(Buffer * const pBD) {

	/*If the Buffer object passed in is NULL return -1*/
	if (pBD == NULL) {
		return -1;
	}

	/*If the Buffer is empty print in the output file "Empty buffer"*/
	if (pBD->addc_offset == 0) {
		printf("Empty buffer");
	}

	/*If everything works this cycles through the Buffer array and prints out its contents until
	reaching the end.*/
	for (int i = 0; i < pBD->addc_offset; i++) {
		printf("%c", pBD->cb_head[i]); \
	}

	printf("\n");

	/*Returns the number of chars printed out, the 'addc_offset'.*/
	return pBD->addc_offset;
}








/***********************************************************************************************
Name: b_compact
Purpose: Compacts or expands the buffer size depending on the buffer size currently
used.
Author: Nathan M. Abbey
History/Versions: 1.0
Called Functions: none
Parameters: pBuffer const pBD - A pointer to the buffer
Return Value: pBuffer const pBD - A pointer to the buffer
Algorithm: 1. Ensure parameters input are valid.
2. Allocate memory for the current offset plus one
3. Update the Buffer to the new variable values.
************************************************************************************************/
Buffer* b_compact(Buffer * const pBD, char symbol) {

	/*Creates the new capacity which is 'addc_offset' plus 1.*/
	short newCapacity = pBD->addc_offset + 1;

	/*REALLOCs with the new capacity.It will return NULL if the REALLOC fails.*/
	char *tempPointer = realloc(pBD->cb_head, newCapacity);
	if (tempPointer == NULL) {
		return NULL;
	}

	/*Copies the old String in to the newly allocated larger memory block.*/
	strcpy(tempPointer, pBD->cb_head);

	/*Updates the 'capacity' of the Buffer object*/
	pBD->capacity = newCapacity;

	/*Adds the symbol inputted as a parameter to the end of the Buffer and increases
	'addc_offset' by 1.*/
	pBD->cb_head[pBD->addc_offset] = symbol;
	pBD->addc_offset++;

	/*Sets the r_flag' value to indicate the memory location has changed due to REALLOC.*/
	pBD->r_flag = SET_R_FLAG;

	/*Return the Buffer object*/
	return pBD;
}






/***********************************************************************************************
Name: b_rflag
Purpose: Returns the 'r_flag' variable to the calling function.
Author: Nathan M. Abbey
History/Versions: 1.0
Called Functions: none
Parameters: pBuffer const pBD - A pointer to the buffer
Return Value: Returns the 'r_flag' variable to the calling function
Algorithm: 1.  Return the 'r_flag' variable to the function.
************************************************************************************************/
char b_rflag(Buffer * const pBD) {

	/*Returns the 'r_flag' variable of the Buffer object.*/
	return pBD->r_flag;
}







/***********************************************************************************************
Name: b_retract
Purpose: Decreases the getc_offset and sends it back to the caller
Author: Nathan M. Abbey
History/Versions: 1.0
Called Functions: none
Parameters: pBuffer const pBD - A pointer to the buffer
Return Value: Returns the cetc_offset decremented by one.
Algorithm: 1. Ensure parameters input are valid.
2. Decrement the 'getc_offset' variable of the Buffer
3. Return the new value
************************************************************************************************/
short b_retract(Buffer * const pBD) {

	/*Decrement the 'getc_offset by 1 and return the value.*/
	pBD->getc_offset = pBD->getc_offset -1;

	return pBD->getc_offset;
}





/***********************************************************************************************
Name: b_reset
Purpose: Function sets 'getc_offset' to the value of the current 'markc_offset'
Author: Nathan M. Abbey
History/Versions: 1.0
Called Functions: none
Parameters: pBuffer const pBD - A pointer to the buffer
Return Value:         -1 ~ Returned if the parameter is NULL
get_offset ~ On success returns the get offset
Algorithm: 1. Ensure parameters input are valid.
2. Returns the proper value
************************************************************************************************/
short b_reset(Buffer * const pBD) {

	/*If the Buffer object passed in is NULL return -1.*/
	if (pBD == NULL) {
		return -1;
	}

	/*Sets the 'getc_offset' to the 'markc_offset' and return the 'getc_offset'.*/
	pBD->getc_offset = pBD->markc_offset;
	return pBD->getc_offset;
}









/***********************************************************************************************
            Name: b_getcoffset
         Purpose: Function sets returns the 'getc_offset' plus 1.
          Author: Nathan M. Abbey
History/Versions: 2.0
Called Functions: none
      Parameters: pBuffer const pBD - A pointer to the buffer
    Return Value:             0 ~ Returned if the parameter is NULL
                  getc_offset++ ~ Returns the 'get_offset' plus 1.
       Algorithm: 1. Ensure parameters input are valid.
                  2. Returns the getc_offset
************************************************************************************************/
short b_getcoffset(Buffer* const pBD) {

	/*If the Buffer object is NULL then the 'getc_offset' will equal 0*/
	if (pBD == NULL) {
		return 0;
	}

	/*Returns getc_offset*/
	return 	pBD->getc_offset;
}







/***********************************************************************************************
Name: b_rewind
Purpose: Resets 'getc_offset' and 'marc_offset' to 0.
Author: Nathan M. Abbey
History/Versions: 1.0
Called Functions: none
Parameters: pBuffer const pBD - A pointer to the buffer
Return Value:     0 ~ On success
Algorithm: 1. Returns 0 on success
************************************************************************************************/
int b_rewind(Buffer * const pBD) {

	/*Sets the 'getc_offset' and 'markc_offset' to 0 and return 0 on success.*/
	pBD->getc_offset = 0;
	pBD->markc_offset = 0;
	return 0;
}






/***********************************************************************************************
Name: b_location
Purpose: Returns the char, from the Heap, at the specified index array
Author: Nathan M. Abbey
History/Versions: 1.0
Called Functions: none
Parameters: pBuffer const pBD - A pointer to the buffer
short loc_offset - The index of the location of the char to be returned.
Return Value:  The char value at the index indicated.
Algorithm: 1. A char value from the specified index.
************************************************************************************************/
char * b_location(Buffer * const pBD, short loc_offset) {

	char * temp = &(pBD->cb_head[loc_offset]);

	/*Returns the pointer to the char specified by the 'loc_offset'*/
	return temp;
}