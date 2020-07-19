
public class ArrayDemo {

	public void demonstrateTask1(){
		System.out.println ("Demonstrate Task 1");

		int[] numbers = null;
		numbers = new int [5];

		System.out.println ("Array of primitives before assigning values:");
		for (int i = 0; numbers.length > i; i++ ){
			System.out.print (numbers[i] + " ");
		}
		
		
		for (int i = 0; i < numbers.length; i++) {
		numbers[i] = i +1;
		}
		
		
		System.out.println ("\nArray of primitives after assigning values:");
		for (int i = 0; i < numbers.length; i++) {
			System.out.print (numbers[i] + " ");
		}
	}

	
	
	public void demonstrateTask2(){
		System.out.println ("\n\nDemonstrate Task 2");
		
		Goldfish[] fishes = new Goldfish [5];
		
		System.out.println ("\nArray of references before assigning references");
		for (int i = 0; 5 > i; i++ ){
			System.out.print (fishes[i] + " ");
		}
	
		for (int i = 0; i < fishes.length; i++){
			fishes[i] = new Goldfish();
			fishes[i].setWeight(i + 1);
		}
	
		System.out.println ("\nAn array of references after assigning references to Goldfish");
		for (int i = 0; i < fishes.length; i++){
		System.out.print (fishes[i].getWeight() + " ");
		}
	}
	
	
	public void demonstrateTask3(){
		System.out.println ("\n\nDemonstrate task 3");
		
		int [][] numbers = new int[5][5];
		
		System.out.println ("\n2D Array of primitives before assigning values");
		for (int i= 0; i < numbers.length; ++i)	{
			for (int j = 0; j < numbers.length ; ++j ) {
				System.out.print(numbers[i] [j] + " ");
			}
			
			System.out.println();
		}
	
		for (int i= 0; i < numbers.length; ++i)	{
			for (int j = 0; j < numbers.length ; ++j ) {
				numbers [i][j] = (i +1) * (j +1);
			}
		}

		System.out.println ("\n2D Array of primitives after assigning values");
		for (int i= 0; i < numbers.length; ++i)	{
			for (int j = 0; j < numbers.length ; ++j ) {
				System.out.print(numbers[i] [j] + " ");
			}
			System.out.println();
		}	
	}
	
	public void demonstrateTask4(){
		
		System.out.println ("\nDemonstrate Task 4");
		
		Goldfish[][] fishes = new Goldfish[5][5];
		
		System.out.println ("\n2d Array of references before assigning references:");
		
		for (int i= 0; i < fishes.length; ++i)	{
			for (int j = 0; j < fishes.length ; ++j ) {
				System.out.print(fishes[i] [j] + " ");
			}
			System.out.println();
		}
		
		for (int i = 0; i < fishes.length; i++){
			for (int j =0;j < fishes.length; j++){
				fishes [i][j] = new Goldfish();
				fishes [i][j].setWeight((i+1) * (j+1));		
				}
		}
		

		System.out.println ("\n2D Array of references after assigning references to Goldfish objects");
		for (int i= 0; i < fishes.length; ++i)	{
			for (int j = 0; j < fishes.length ; ++j ) {
				System.out.print(fishes[i][j].getWeight() + " ");
			}
			System.out.println();
		}
	}
}