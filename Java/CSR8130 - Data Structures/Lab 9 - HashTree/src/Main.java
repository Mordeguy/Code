import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		final int SIZE = 100;
		Scanner scn = new Scanner(System.in);
		String dataItems[] = new String[SIZE];
		int choice = 999;

		for (int i = 0; i < SIZE; i++)
			dataItems[i] = " ";

		do {

			String input;
			int index;

			try {
				System.out.println("\nLab 9: HashTrees\n");
				System.out.println("-----------------------------------------|");
				System.out.println("1. Enter a String");
				System.out.println("2. Search for a String");
				System.out.println("3. Quit");
				System.out.println("-----------------------------------------|");
				System.out.print("Choice--> ");
				choice = scn.nextInt();

				switch (choice) {

				case 1:
					System.out.print("Enter string to insert: ");
					input = scn.next();
					index = hash(input);

					for (int i = index; i < SIZE; i++) {

						if (!dataItems[i].equals(" ")) {
							index++;
						} else {
							break;
						}
					}
					if (index == SIZE && !dataItems[SIZE - 1].equals(" ")) {

						System.out.println(
								"\n***ERROR: Could not insert in the Array; index and all above are full***\n");

					} else {
						dataItems[index] = input;
					}
					break;

				case 2:
					System.out.print("Enter the String to look for: ");
					input = scn.next();

					index = hash(input);

					while (index <= 99) {

						if (search(dataItems, input, index)) {

							System.out.println("\nString " + input + " is in array at index " + index);
							break;

						} else {
							
							
							index++;
						}

					}
					break;
				case 3:

					System.out.println("\n\nGOODBYE!!\n\n");
					break;

				default:
					System.out.println("\nPlease enter a valid entry...or 3 to quit.");

				}

			} catch (InputMismatchException ex) {

				System.out.println("\n***ERROR: Selection must be a numeric value***");
				scn.nextLine();

			}

		} while (choice != 3);

	}

	public static int hash(String input) {
		int total = 0;
		int j;

		if (input.length() == 1){
			
			j = 1;
			
		} else {
			
			j = 2;
		}
		
		for (int i = 0; i < j; i++)
			total += input.charAt(i);

		return total % 100;
	}

	public static boolean search(String dataItems[], String temp, int index) {
		if (dataItems[index].contains(temp))
			return true;
		else
			return false;

	}

}