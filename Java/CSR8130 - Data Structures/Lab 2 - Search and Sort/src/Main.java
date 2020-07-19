import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class Main {



	public static void main(String[] args) {

		int choice = 9;
		Scanner scn = new Scanner(System.in);
		Numbers num = new Numbers();
	
		Random rndm = new Random();

		do {
			System.out.println("Please make a selection from the menu below.");
			System.out.println("-------------------------------------------");
			System.out.println("1 Create Array of a New Size");
			System.out.println("2 Generate Random Numbers into an Array");
			System.out.println("3 Count a Specific Value");
			System.out.println("4 Display and Array");
			System.out.println("5 Sort");
			System.out.println("0 Quit");
			System.out.print("\nSelection --> ");

			try {
				choice = scn.nextInt();
				
				if (choice <= 0 || choice > 5) {
					
					System.out.println("\n***ERROR: Please a selection between 0-5.***\n");
				}
		


			switch(choice) {

			case 1 :

				System.out.print("\nEnter new size: ");
				Numbers num1 = new Numbers(scn.nextInt());;
				num = num1;
				System.out.println("\nArray has been generated\n\n");
				break;

			case 2 :
			
				num.generateNumbers();
				break;
				
			
			
	
			case 3 :
				
				int number;

				System.out.print("\nEnter number to search for: ");
				number = scn.nextInt();
				
				try {
				System.out.println("\nThere are " + num.count(number) + " of the number " + number +"\n\n");
				
				} catch (NullPointerException ex){
					
					System.out.println("\n***ERROR: There is no Array to search through.***\n");
				}
				break;

				

			case 4 :

				try {

					System.out.printf("\nThe numbers are:\n%S\n", num.toString());
					System.out.println();

				}catch (NullPointerException ex){

					System.out.println("\n**ERROR: There is no Array to display.**\n");
				}
				break;
				
			
				
			case 5:
				
				int temp;
				int j;
				
				for (int i = 1; i < num.num.length; i++) {
					
					temp = num.num[i];

					for (j = i-1; 0 <= j && temp < num.num[j]  ; j--){

						num.num[j +1] = num.num[j];
						
					}
					
					num.num[j+1] = temp;

				}
				break;


			}



			}catch (InputMismatchException ex) {

				System.out.println("\n***ERROR: Please enter a valid input.***\n");
				scn.nextLine();

			}


		} while (choice != 0);

	}
}
