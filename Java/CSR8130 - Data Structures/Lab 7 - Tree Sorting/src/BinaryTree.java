public class BinaryTree {
	BinaryTreeNode root = null;

	public void insertInTree (int newData) {

		if (root == null) 
			root = new BinaryTreeNode(newData);
		else 
			root.insert(newData);

	}


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
}