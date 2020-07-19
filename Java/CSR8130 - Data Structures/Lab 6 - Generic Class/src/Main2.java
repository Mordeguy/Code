import java.util.InputMismatchException;
import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
		
		int length = 0;
		int choice = 999;
		Numbers num = null;
		Scanner scn = new Scanner(System.in);
		
		do {
		
		try {
		
		System.out.println("Welcome to Lab 6!");
		System.out.println("Please make a selection from the menu below: ");
		System.out.println("------------------------------------------------|");
		System.out.println("1 - Add Double Numbers to Array");
		System.out.println("2 - Sort the Array");
		System.out.println("3 - Display the Array");
		System.out.println("0 - Quit Program");
		System.out.println("------------------------------------------------|");
		System.out.print("Choice --> ");
		choice = scn.nextInt();
		
		switch(choice) {
		
		case 1:
		
			System.out.print("How long would you like the array to be?: ");
			length = scn.nextInt();
			num = new Numbers(length);
			
			for (int i =0; i < length; i++){
				
				double enter = 9;
				
				System.out.print("Number " + (i+1) + " -->");
				enter = scn.nextDouble();
				num.addNumber(enter);
			}
			System.out.println();
			break;
			
			
		case 2:
			num.sort();
			break;
		
		
		case 3:
			
			num.display();
			break;
		
		case 0:
			
			System.out.println("\nGOODBYE! Thanks for using this program!");
		
		}
		} catch (InputMismatchException ex){
			
			System.out.println("\nERROR: Please input a valid numerical entry!\n");
			scn.nextLine();
		}
	} while (choice != 0);
	

}
	
}	