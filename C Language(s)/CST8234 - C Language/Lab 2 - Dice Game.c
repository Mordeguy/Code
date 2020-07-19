/* PROGRAM :  dice_02.c
    AUTHOR :  Nathan M. Abbey
      DATE :  September 30, 2016
   UPDATED :  December 11, 2016
     TOPIC :  Dice Game using rand() 
   PURPOSE :  CST8234 - C Language - Lab 2
     TASKS :   
*/

/**************************************************************************/
/* Declare include files
 **************************************************************************/
#include <stdio.h>
#include <stdlib.h>


/**************************************************************************/
/* Macro Defines
 **************************************************************************/
#define WON 0
#define LOSE 1


/**************************************************************************/
/* Function prototype
 **************************************************************************/
int rollDice (); 
int playGame();


/**************************************************************************/
/* Global variables
 **************************************************************************/
int total, pointMatch, rollNum, win, lose;
char choice;


/**************************************************************************
 * Main
 **************************************************************************/

int main (void) {

	do {
		char enter = 0;
		rollNum = 0;

		printf("\n                     ROLL THE DICE WITH [ENTER]\n\n");

		// Used to accept user input to play the game -----------------------------------|
		enter = getchar();	
		playGame();
					
		
		// Asks user if they want to play again after the game has finished -------------|
		printf ("\nAnother game? (y/n) : ");
		scanf("%c", &choice);
	
	}while (choice == 'y');

	// This displays at the end of the program -------------------------------------------|
	printf("\nThank-you for playing!\n");
	printf("You won %1d games and lost %d games!\n", win, lose);


	// Customizes the exit message based on how the user did -----------------------------|
	if (win > lose) {
		printf("What a winner!\n\n");
	} else {
		printf("Better luck next time!\n\n");
		}
}


/**************************************************************************
 * FUNCTION : rollDice
 *     TASK : Uses rand() to return a value between 1 and 6. 
 **************************************************************************/
int rollDice () {
		
	int dice = (1 + random() % 6);
	return dice;	
}
            
            
/**************************************************************************
 * FUNCTION : playGame
 *     TASK : Plays a game of craps with 2 die and returns 1 for WIN and 0
 		    for LOSS. 
 **************************************************************************/
int playGame() {
	
	int dice1, dice2, sum, pointMatch, moveOn;

	// Seeds the rand() function with a truly random number ------------------------------|
	srand(time(NULL));
	
	
	// This rolls 2 dice and sums their total --------------------------------------------|
	dice1 = rollDice();
	dice2 = rollDice();
	sum = dice1 + dice2;
	pointMatch = sum;

	printf("------------------------------------------------------------------------\n");
	printf(" ROLL NUM     DICE #1     DICE #2     TOTAL ROLL     POINT MATCH\n");
	printf("------------------------------------------------------------------------\n");


	// Displays the dice numbers, sum and the point match --------------------------------|
	printf("%9d %11d %11d              %d              %d\n",   	
	rollNum, dice1, dice2, sum, pointMatch);


	// If the first roll is 7 or 11 you WIN! Increase win number and return WON ----------|
	if (sum == 7 || sum == 11) {
		printf("Congratulations! You have won with a roll of %10d." , sum);
		win++;			
		return WON;
			
	
	// If the first roll is 2, 3, or 12 you LOSE! Increase lose number and return LOSE			
   	} else if (sum == 2 || sum == 3 || sum == 12) {
		printf("Sorry! You have lost with a roll of %10d.", sum);
		lose++;				
		return LOSE;
				
				
	} else {
				
		do {
			// Roll the dice and add up the sum; the point match is our first roll -----|
			dice1 = rollDice();
			dice2 = rollDice();
			sum = dice1 + dice2;
			rollNum++;

			// Displays the roll number, dice values etc... ----------------------------|
			printf("        %d           %d           %d              %d              %d				\n", rollNum, dice1, dice2, sum, pointMatch);
		
		
			// If anything either than first roll is 7 LOSE! ---------------------------|
			if (sum == 7) {
				printf("Sorry! You have lost with a roll of %3d.", sum);
				lose++;					
				return LOSE;
										

			// If other than first roll matches pointMatch you WIN ---------------------|
			} else if (sum == pointMatch) {
				printf("Congratulations! You have won with a roll of %3d." ,							sum);
				win++;						
				return WON;	
						

			}
		
		// Keep playing and rolling until the player either WINS or LOSES 				
		} while (moveOn != WON || moveOn != LOSE);			
				
					
	}		
}
