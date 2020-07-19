// Name: Nathan Abbey      Section: 301     Lab Teacher: Mauricio Orozco-Trujillo

import java.util.Random;

public class Die {
	
	
	
	//Instance variable
	int dieValue;
	
	
	
	//Declare an object of the randomNumbers class
	Random randomNumbers = new Random();
	
	
	
	//Set the initial value of the die
	public void dieValue(){
		dieValue = 0;
	}
	
	
	
	//Rolls the die and returns it to the caller
	public int rollDie(){
		dieValue = randomNumbers.nextInt(6) + 1;
		return dieValue;
	}
	
	
	//Displays the die value
	public void displayDie(){
		System.out.print (dieValue);
	}
}

