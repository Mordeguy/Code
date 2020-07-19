import java.util.Scanner;


public class LinkedListExample {

	public static void main(String[] args) {
		
		String choice, newData, oneToDelete;
		Scanner scn = new Scanner(System.in);
		LList list = new LList();
		
		do {
		System.out.println("Please choose an option from the menu below:");
		System.out.println("a - Add to Head");
		System.out.println("h - Delete from Head");
		System.out.println("p - Print Linked List" );
		System.out.println("d - Delete Specific Item");
		System.out.println("q - Quit");
		System.out.print("Choice --> ");
		choice = scn.nextLine();
		
		choice.toLowerCase();
		
		switch (choice.charAt(0)) {
		
		case 'a':
			
			System.out.print("Please enter the name you would like to add to Linked List: ");
			newData = scn.nextLine();
			list.addAtHead(newData);
			break;
		
		case 'd':
			
			try {
			System.out.print("Please enter name of Node to delete: ");
			oneToDelete = scn.nextLine();
			list.deleteSpecific(oneToDelete);
			break;
			
			} catch (NullPointerException ex)
			{
				System.out.println("\nThat Node does not exist!\n");
			}
			
			
			
		
		case 'h':
			
			try {
			list.deleteAtHead();
			break;
			
			} catch (NullPointerException ex)
			{
				System.out.println("\nThere is no head node to delete!\n");
			}
			
		
			
		case 'p':
			list.display();
			break;
		
		
		case 'q':
			
			System.out.println("\nGoodbye!");
			break;
		
			
		
		
		}
		} while (choice.charAt(0) != 'q');
		
		
		
		
		
		
		
	

	}

}
