#include <stdio.h>


int main(void) {

	char enter = 'a';
	printf("The size of type CHAR is: %u\n", sizeof(char));
	printf("The size of type SHORT is: %u\n", sizeof(short));
	printf("The size of type LONG is: %u\n", sizeof(long));
	printf("The size of type INT is: %u\n", sizeof(int));
	printf("The size of type SIGNED INT is: %u\n", sizeof(signed int));
	printf("The size of type UNSIGNED INT is: %u\n", sizeof(unsigned int));
	printf("The size of type FLOAT is: %u\n", sizeof(float));

	int *intPointer = (int*)sizeof(int);
	printf("The size of type INT POINTER is: %u\n", sizeof(intPointer));
	char *charPointer = (char*)sizeof(char);
	printf("The size of type CHAR POINTER is: %u\n", sizeof(charPointer));
	

	
	fscanf_s(stdin, "%c", enter);
	return 0;
}