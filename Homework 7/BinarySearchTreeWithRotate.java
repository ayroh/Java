public class BinarySearchTreeWithRotate<E extends Comparable<E>> extends BinarySearchTree<E>{

	public BinarySearchTreeWithRotate(){
		super();
	}

	protected Node<E> rotateRight(Node<E> tempRoot){
		Node<E> temp = tempRoot.left;
		tempRoot.left = temp.right;
		temp.right = tempRoot;
		return temp;
	}

	protected Node<E> rotateLeft(Node<E> tempRoot){
		Node<E> temp = tempRoot.right;
		tempRoot.right = temp.left;
		temp.left = tempRoot;
		return temp;
	}

}