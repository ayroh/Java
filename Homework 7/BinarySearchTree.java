import java.util.ArrayList;

public class BinarySearchTree <E extends Comparable>{

	protected Node root = null;
	private int size = 0;
	protected boolean addReturn;
	private boolean isEqual = false, NotAChance = false;

	public int size(){
		return size;
	}	

	public boolean add(E item, boolean red) {
		root = add(root, item, red);
		return addReturn;
	}

	public boolean add(E item) {
		root = add(root, item, false);
		return addReturn;
	}

 	private Node<E> add(Node<E> tempRoot, E item, boolean red){
 		if (tempRoot == null){
	 		// item is not in the tree — insert it.
	 		addReturn = true;
	 		size++;
	 		return new Node(item, red);
	 	} 
	 	else if (item.compareTo(tempRoot.data) == 0) {
	 		// item is equal to tempRoot.data
	 		addReturn = false;
	 		return tempRoot;
	 	} 
 		else if (item.compareTo(tempRoot.data) < 0) {
	 		// item is less than tempRoot.data
	 		tempRoot.left = add(tempRoot.left, item, red);
	 		return tempRoot;
	 	} 
 		else {
	 		// item is greater than tempRoot.data
	 		tempRoot.right = add(tempRoot.right, item, red);
	 		return tempRoot;
	 	}
 	}


 	/**
 	 * if root is red return false, if parents or any childs both red returns false, blacknodecount() counts black nodes for every way returns false in case otherwise returns true
 	 * */
 	public boolean isRedBlackTree(BinarySearchTree bst){
 		if(isRed(bst.root))
 			return false;
 		if(!checkRed(bst.root))
 			return false;
 		blackNodeCount(bst.root);
 		if(NotAChance)
 			return false;
		return isEqual;
 	}

 	/**
 	 * Checks if parent or child is red
 	 * */	
 	private boolean checkRed(Node<E> tempRoot){
 		if(tempRoot == null)
 			return true;
 		if(isRed(tempRoot) && (isRed(tempRoot.left) || isRed(tempRoot.right)))
 			return false;
 		return checkRed(tempRoot.left) && checkRed(tempRoot.right);
 	}

 	/**
 	 * Checks if root is red
 	 * */
 	private boolean isRed(Node<E> root){
 		if(root != null && root.isRed)
 			return true;
 		return false;
 	}

 	/**
 	 * Counts black nodes for every way and if node is not leaf and black nodes not equal for both childs then NotAChance become true and isRedBlackTree becomes false
 	 * */
 	public int blackNodeCount(Node<E> tempRoot){
 		if(tempRoot == null)
 			return 0;
 		int leftTemp = 0;
 		int rightTemp = 0;
 		if(!tempRoot.isRed){
 			leftTemp = 1 + blackNodeCount(tempRoot.left);
 			rightTemp = 1 + blackNodeCount(tempRoot.right);
 		}
 		else{
 			leftTemp = blackNodeCount(tempRoot.left);
 			rightTemp = blackNodeCount(tempRoot.right);
 		}
 		isEqual = (leftTemp == rightTemp);
 		if(!isEqual && tempRoot.left != null && tempRoot.right != null)
 			NotAChance = true;

 		return biggerOne(leftTemp,rightTemp);
 	}


 	public E find(E item){
 		return findNode(item).data;
 	}

 	public Node<E> findNode(E item){
		return find(root, item);
	}
	
	private Node <E> find(Node<E> tempRoot, E item){
		if(tempRoot == null)	return null;

		if(item.compareTo(tempRoot.data) == 0)	
			return tempRoot;
		else if(item.compareTo(tempRoot.data) < 0)	
			return find(tempRoot.left, item);
		else  
			return find(tempRoot.right, item);
	}


	 public int height(E item){
		return height(findNode(item));		
	}

	private int height(Node<E> tempRoot){
		if(tempRoot == null)
			return 0;

		int leftTemp = 0;
		int rightTemp = 0;

		leftTemp = height(tempRoot.left);
		rightTemp = height(tempRoot.right);

		return 1 + biggerOne(rightTemp, leftTemp);
	}

	private int biggerOne(int x, int y){
		if(x >= y)
			return x;
		else
			return y;
	}

	public boolean isAVLTree(){
		return isAVLTree(this);
	}

	public boolean isAVLTree(BinarySearchTree bst){
		return isAVLTree(bst.root);
	}


	/**
	 * Searches height for every node and if height between to child is more than 1 it returns false
	 * */
	private boolean isAVLTree(Node <E> node){
		if(node == null)
			return true;

		int leftTemp = 0;
		int rightTemp = 0;

		leftTemp = height(node.left);
		rightTemp = height(node.right);

		if(Math.abs(leftTemp - rightTemp) <= 1 && isAVLTree(node.left) && isAVLTree(node.right))
			return true;
		else
			return false;
	}

 	protected static class Node<E> 
 	{
		// Data Fields
		/** The information stored in this node. */
		protected E data;
		/** Reference to the left child. */
		protected Node<E> left;
		/** Reference to the right child. */
		protected Node<E> right;
		// Constructors
		/** Construct a node with given data and no children.
		@param data The data to store in this node
		*/
		/** Color indicator. True if red, false if black. */
		public boolean isRed;


		public Node(E data) 
		{
			this.data = data;
			left = null;
			right = null;
			isRed = true;
		}

		public Node(E data, boolean red) 
		{
			this.data = data;
			left = null;
			right = null;
			isRed = red;
		}

		public String toString()
		{
			if(isRed == false)
			{
				return (data + " BLACK ");
			}
			else
			{
				return(data + " RED");
			}
		}
	}
	
	/**
	 * Prints all tree
	 * */
	public void print(){
		print(root);
	}

	private void print(Node <E> node){	
		if(node == null)
			return;

		System.out.println(node);
		print(node.left);
		print(node.right);
	}

}