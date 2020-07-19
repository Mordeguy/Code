import java.util.Scanner;

public class LabAssign6 {

	public static void main(String[] args) {
		
		// Declare instance variables
		int length, width;
	

		// Create an object of the scanner class
		Scanner input = new Scanner(System.in);



		System.out.print("Enter the length: ");
		length = input.nextInt();

		while (length > 20 || length <0){
			System.out.print ("Invalid - enter a length between 1 and 20: ");
			length = input.nextInt();
		}



		System.out.print ("Enter the width: ");
		width = input.nextInt();

		while (width >20 || width < 0) {
			System.out.print ("Invalid - enter a width between 1 and 20: ");
			width = input.nextInt();
		}

		System.out.println ();

		
		for (int i= 0; i < width; ++i)	{
			for (int j = 0; j < length ; ++j ) {
				System.out.print("*");
				
			}
			System.out.println();
		}

		}
		
	
		
	}


