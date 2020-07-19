
public class BinaryTreeExample {

	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		
		System.out.println("\n              CST8130 - Lab 7 - Trees");

		System.out.println("\nNumbers will be input in this order: (6, 3, 9, 1, 15, 7)");
		System.out.println("--------------------------------------------------------");
		tree.insertInTree(4);
		tree.insertInTree(10);
		tree.insertInTree(1);
		tree.insertInTree(16);
		tree.insertInTree(22);
		tree.insertInTree(14);
		tree.insertInTree(15);
		
		System.out.print("\n    Displayed in Order: ");
		tree.displayInOrder();
		System.out.println();
		System.out.print("\n Pre-Order Transversal: ");
		tree.preOrderTransversal();
		System.out.println();
		System.out.print("\nPost-Order Transversal: ");
		tree.postOrderTransversal();


		
		
	}

}
