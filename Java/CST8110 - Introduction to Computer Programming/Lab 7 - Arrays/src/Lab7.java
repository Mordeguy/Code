import java.util.Scanner;
public class Lab7 {

	public static void main(String[] args) {
		
		String phrase;
		int capitals = 0;
		int lowercase = 0;
		int other = 0;

		
		//Arrays for testing
		char[] capsArray = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		char[] lowerArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		
		
		// Accepts input from user
		Scanner input = new Scanner (System.in);
		System.out.println ("Enter the phrase: ");
		phrase = input.nextLine();
		
		
		//Declares a new char array
		char[] tempArray = new char [(phrase.length())];

		
		//Using the arrays for testing
		for (int i = 0; i < phrase.length(); i++){
			
			tempArray[i] = phrase.charAt(i); 
			
			for (int j = 0; j < 26; j++){
				if (tempArray[i] == capsArray[j]){
					capitals++;
				} else if (tempArray[i] == lowerArray[j]){
					lowercase++;
				} else{
					
				}

				other = phrase.length() - lowercase - capitals;
				
				}

			}
		
		System.out.println ("\nThe total number of letters is: " + phrase.length());
		System.out.println ("The number of uppercase letters is: " + capitals);
		System.out.println ("The number of lowercase case letters is: " + lowercase);
		System.out.println ("The number of other characters is: " + other);


		}
		
		
		

		
	}
		

	

