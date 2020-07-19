// Name: Nathan Abbey      Section: 301     Lab Teacher: Mauricio Orozco-Trujillo

import java.util.Scanner;

public class Game {

	int potAmount = 50;
	int betAmount;

	
	Scanner input = new Scanner (System.in);
	
	Die d1 = new Die();
	Die d2 = new Die();
	Die d3 = new Die();
	
	//Constructor
	public Game(){
		 System.out.println("Welcome to the Solitaire dice game. Bet and amount - if the sum of the three die is \ngreater than 12" + 
                ", you keep your bet, if the roll doubles you win double your bet, \nif you roll triples you win triple your bet" +
		 			", otherwise you lose your bet. A bet of 0 \nends the game.");
	}
	
	

	// Prompts user for bet amount and ensures it's within the proper range. 
	public void getBetAmountFromUser(){

		System.out.println("\nYour current pot amount is " + potAmount);
		System.out.print("Enter your bet amount: ");
		betAmount = input.nextInt();
		
		while (betAmount < 0 || betAmount > potAmount){
			System.out.print ("Error - cannot be less than 0 or more than " + potAmount + "...Enter your bet amount: ");
			betAmount = input.nextInt();
		}
	}


	//Rolls, displays die and calculates pot amount based on criteria. If pot reaches 0 or bet is 0 exits game.
	public void playGame(){

		do{
		
		d1.rollDie();
		d2.rollDie();
		d3.rollDie();
		
	
		getBetAmountFromUser();
		
		if (betAmount == 0){
			System.out.println ("\nYou ended the game with a pot of " + potAmount + ". Thanks for playing!");
			break;
		}
		
		System.out.println("Your die are: " + d1.dieValue + " and " + d2.dieValue + " and " + d3.dieValue);


		if (d1.dieValue == d2.dieValue && d2.dieValue == d3.dieValue) {
			System.out.println ("You WIN...triple your bet!");
			potAmount = (potAmount-betAmount) + betAmount *3;

		} else if (d1.dieValue == d2.dieValue || d2.dieValue == d3.dieValue || d1.dieValue == d3.dieValue){
			potAmount = (potAmount-betAmount) + betAmount *2;
			System.out.println ("You WIN...double your bet!");

		} else if ((d1.dieValue + d2.dieValue + d3.dieValue) < 12){
			System.out.println ("You LOSE...your bet");
			potAmount -= betAmount;

		} else if ((d1.dieValue + d2.dieValue + d3.dieValue) >= 12){
			System.out.println ("You WIN...your bet back");

		} 
		
		if (potAmount ==0){
			System.out.println ("\nYou have run out of money. Thanks for playing!");
		}
		 }while (potAmount>0);
		
	}

}