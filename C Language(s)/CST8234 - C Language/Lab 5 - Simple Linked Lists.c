/* AUTHOR :  Nathan M. Abbey
    TOPIC :  Simple Linked Lists
  UPDATED :  December 11, 2016
  PURPOSE :  C Language - Lab 5
      JOB :  Managing a linked list; adding, deleting and displaying objects.
    TASKS :
    	  	      - Creating a Singly Linked List
    	  	      - Adding, displaying and deleting a specfic item in the list
			   or at the head.
*/
/**************************************************************************/
/* Declare include files
/**************************************************************************/
#include <stdio.h>
#include <stdlib.h>

/**************************************************************************/
/* Global variables
/**************************************************************************/
int in;
char choice;
int num;
struct node {
	int data;
	struct node *next;
};

/**************************************************************************/
/* Function prototype
/**************************************************************************/
int Length (struct node *head);
void Add(struct node** headRef, int value);
void PrintList( struct node* head); 
int Delete(struct node** headRef);
void ZeroList(struct node** headRef);
void DeleteSpecific(struct node ** headRef, int value);

/**************************************************************************
/* Main
/**************************************************************************/
int main () {

	struct node *new;	

	do {	
		char newline;
		
		printf("\nWelcome to Lab 5 - Simple Linked Lists\nPlease make a selection.\n");
		printf("-------------------------\n");
		printf("a - Add a New Node @ Front of the List\n");
		printf("d - Delete First Node on the List\n");
		printf("l - Print the Number of Nodes in the List\n");
		printf("p - Print the Entire List\n");
		printf("s - Delete Specific Node\n");
		printf("z - Delete the Entire List\n");
		printf("x - Exit the Program\n");
		printf("-------------------------\n");
		printf("Choice --> ");
		fscanf(stdin, "%c%c", &choice, &newline);
	
		switch (choice) {
			case 'a':
				printf("\nEnter number to add: ");
				fscanf(stdin, "%d%c", &in, &newline);	
				Add(&new, in);
				break;
			case 'd':
				Delete(&new);
				break;
			case 'p':
				PrintList(new);
				break;
			case 's':
				printf("Index of Node to Delete: ");
				fscanf(stdin, "%d%c", &num, &newline);
				DeleteSpecific(&new, num);
				break;
			case 'l':
				printf("\nNumber of Nodes: ");
				printf("%d\n", Length(new));
				break;
			case 'x':
				printf("\nThank-you for using this program. Goodbye!\n\n");
				break;
			case 'z':
				ZeroList(&new);							
				break;
			default:
				printf("\n***ERROR: Please choose a valid option.***\n");
				break;
		}	
	} while (choice != 'x');
	return(0);
}


/**************************************************************************
/* FUNCTION: Length
/**************************************************************************/
int Length(struct node *head) {
    	
    	int count = 0;
    	struct node *temp = head;
    	
    	while (temp != NULL) {
     	   count++;
     	   temp = temp->next;
     }  	
  return count;
}


/**************************************************************************
/* FUNCTION: Add
/**************************************************************************/
void Add(struct node** headRef, int input){
    
    struct node *temp = (struct node*) malloc (sizeof (struct node));
    
    temp->data = input;
    temp->next = *headRef;
    *headRef = temp;
}



/**************************************************************************
/* FUNCTION: PrintList
/**************************************************************************/
void PrintList( struct node* head) {

	struct node *temp;
	printf("The linked list is:\n\n");
	temp = head;
	
	while (temp != NULL) {
		printf("%d-->",  temp->data);
		temp = temp->next;
	}
	printf("\n");
}




/**************************************************************************
/* FUNCTION: Delete
/**************************************************************************/
int Delete (struct node** headRef) {
    
    	struct node *temp = *headRef;        
    
    	if (*headRef == NULL) {
    		printf("\n***ERROR: List is empty and cannot be deleted from.***\n");
    		return(1);
    	}
  
	*headRef = temp->next;
	free(temp);
	
	printf("\nThe Node was Deleted Successfully.\n\n");
	return(0);
}


/**************************************************************************
/* FUNCTION: ZeroList
/**************************************************************************/
void ZeroList(struct node** headRef) {
  
   struct node* temp = *headRef;
   struct node* next;
 
   while (temp != NULL) {
       	next = temp->next;
       	free(temp);
       	temp = next;
   }
   *headRef = NULL;
}


/**************************************************************************
/* FUNCTION: DeleteSpecific
/**************************************************************************/
void DeleteSpecific(struct node ** headRef, int index){


	struct node * temp = *headRef;
	struct node * prev;
	int i =0;
	
	if (temp == NULL) {
		printf("\n***ERROR: There are no nodes to delete***\n");
		return;
	}
	
	if (index == 0) {
	
		*headRef = temp->next;
		free(temp);
		return;
		
	}
	
	while (temp != NULL) {
	
		if (i == index) {
		
			prev->next = temp->next;
			free(temp);
			i--;	
			return;
		
		} else {
		
			prev = temp;
			temp = temp->next;
			i++;
		}	
	}	
}s
