public class RedBlackTree<E extends Comparable<E>> extends BinarySearchTreeWithRotate<E>{


	
	public boolean add(E item) {
		if (root == null){
			root = new Node<E>(item);
			root.isRed = false;// root is b1ack.
			return true ;
		}
		else{
			root = add(root, item) ;
			root.isRed = false; // root is a 1ways b1ack.
		}
		return addReturn; 
	}

	private Node<E> add(Node<E> localRoot, E item) {
		if (item.compareTo(localRoot.data) == 0) {
			// item a 1ready in the tree.
			addReturn = false ;
			return localRoot ;
		}
		else if(item.compareTo(localRoot.data) < 0) {
			// i tem < l ocal Root . data .
			if (localRoot.left == null) {
			// Create new 7eft chi 1d.
				localRoot.left = new Node<E>(item) ;
				addReturn = true ;
				return localRoot ; 
			}
			else { // Need to search.
				// Check for two red chi1dren, swap co1ors if found.
				moveBlackDown(localRoot) ; 
				// Recursively add on the left.
				localRoot.left = add(localRoot.left, item);
				// See whether the left child is now red
				if(localRoot.left.isRed){
					if (localRoot.left.left != null && localRoot.left.left.isRed){
						// Left- left grandchi ld is a lso red.
						// Single rotation is necessary.
						(localRoot.left).isRed = false;
						localRoot.isRed = true;
						return rotateRight(localRoot);
					}
					else if(localRoot.left.right != null && localRoot.left.right.isRed){
						// Left-right grandchild is a lso red.
						// Double rotation is necessary.

						localRoot.left = rotateLeft(localRoot.left);
						localRoot.left.isRed = false;
						localRoot.isRed = true;
						return rotateRight(localRoot);
					}
				}
			}
		}
		else{
			if (localRoot.right == null) {
			// Create new 7eft chi 1d.
				localRoot.right = new Node<E>(item) ;
				addReturn = true ;
				return localRoot ; 
			}
			else{
				// Check for two red chi1dren, swap co1ors if found.
				moveBlackDown(localRoot); 
				// Recursively add on the left.
				localRoot.right = add(localRoot.right, item); 
				// See whether the left child is now red
				if(localRoot.right.isRed){
					if (localRoot.right.right != null && localRoot.right.right.isRed){
						// Left- left grandchi ld is a lso red.
						// Single rotation is necessary.
						localRoot.right.isRed = false;
						localRoot.isRed = true;
						return rotateLeft(localRoot) ;
					}
					else if(localRoot.right.left != null && localRoot.right.left.isRed){
						// Left-right grandchild is a lso red.
						// Double rotation is necessary.
						localRoot.right = rotateRight(localRoot.right);
						localRoot.right.isRed = false;
						localRoot.isRed = true;
						return rotateLeft(localRoot);
					}
				}
			}
		}
		return localRoot;
	}

	private boolean moveBlackDown(Node<E> tempRoot){
		if(tempRoot.left != null && tempRoot.right != null && tempRoot.left.isRed && tempRoot.right.isRed){
			tempRoot.left.isRed = false;
			tempRoot.right.isRed = false;
			tempRoot.isRed = true;
			return true;
		}
		return false;
	}
}