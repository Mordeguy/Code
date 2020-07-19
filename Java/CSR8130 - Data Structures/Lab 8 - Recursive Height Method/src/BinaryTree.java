public class BinaryTree {
	
	BinaryTreeNode root = null;

	
	// Inserts a new node if root is null; inserts at root if not
	public void insertInTree (int newData) {
		if (root == null) 
			root = new BinaryTreeNode(newData);
		else 
			root.insert(newData);
	}

	
	// Displays the tree in order ------------------------------|
	public void displayInOrder () {
		displayInOrder (root);
	}
	public void displayInOrder (BinaryTreeNode subRoot){
		if (subRoot == null)   
			return;
		displayInOrder (subRoot.getLeft());
		System.out.print(" " + subRoot.getData() + " ");
		displayInOrder (subRoot.getRight());
	}



	
	// Displays the tree Pre-Order Transversal ----------------|
	public void preOrderTransversal(){
		preOrderTransversal(root);
	}
	public void preOrderTransversal(BinaryTreeNode subRoot){
		if (subRoot == null)   
			return;
		System.out.print(" " + subRoot.getData() + " ");
		preOrderTransversal(subRoot.getLeft());
		preOrderTransversal(subRoot.getRight());	
	}



	// Post Order Transversal --------------------------------|
	public void postOrderTransversal(){
		postOrderTransversal(root);
	}
	public void postOrderTransversal(BinaryTreeNode subRoot){
		if (subRoot == null)   
			return;
		postOrderTransversal(subRoot.getLeft());
		postOrderTransversal(subRoot.getRight());
		System.out.print(" " + subRoot.getData() + " ");

	}
	
	

	public int calcHeight(BinaryTreeNode subRoot){
		
		
		if (subRoot == null)
			return 0;
		
		int lHeight = calcHeight(subRoot.getLeft());
		int rHeight = calcHeight(subRoot.getRight());

		if( lHeight > rHeight )
			
				return lHeight +1;
		else
				return rHeight +1;
		
	}
	

	
	
	
}