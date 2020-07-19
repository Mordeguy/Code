import java.util.Scanner;

public class BinaryTreeExample {

	
	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		Scanner scn = new Scanner(System.in);
		char display;
		int height;
		
		System.out.println("\n           CST8130 - Lab 8 - Tree Height");

		System.out.println("\nNumbers will be input in this order: (6, 3, 9, 1, 15, 7)");
		System.out.println("--------------------------------------------------------");
		tree.insertInTree(20);
		tree.insertInTree(10);
		tree.insertInTree(5);
		tree.insertInTree(15);
		tree.insertInTree(30);
		tree.insertInTree(25);
		tree.insertInTree(40);
		tree.insertInTree(17);
		tree.insertInTree(7);
		tree.insertInTree(50);
		tree.insertInTree(24);
		

		
		System.out.print("\n    Displayed in Order: ");
		tree.displayInOrder();
		System.out.println();
		System.out.print("\n Pre-Order Transversal: ");
		tree.preOrderTransversal();
		System.out.println();
		System.out.print("\nPost-Order Transversal: ");
		tree.postOrderTransversal();
		System.out.println("\n\n--------------------------------------------------------");
		
		System.out.println("\n\t           v - to view the height of the tree");
		System.out.println("    ANY OTHER BUTTON - to quit the program\n");
		System.out.print("\t   Choice--> ");
		display = scn.next().toLowerCase().charAt(0);
		System.out.println("\n--------------------------------------------------------");

		if (display == 'v'){
			
		
		System.out.println("\n\nThe height of the tree is " + tree.calcHeight(tree.root));
		
			
		} else {
			
			System.out.println("\nThanks for using Lab 8 program!");
		}

		
		
	}

}
