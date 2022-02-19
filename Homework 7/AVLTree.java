import java.util.*;

public class AVLTree<E extends Comparable<E>> extends BinarySearchTreeWithRotate<E>{

	protected static class AVLNode<E> extends BinarySearchTree.Node<E>{
		public static final int LEFT_HEAVY = -1;
		/** Constant to indicate ba lanced */
		public static final int BALANCED = 0;
		/** Constant to indicate right-heavy */
		public static final int RIGHT_HEAVY = 1;
		/** bal ance is right subtree height - left subtree height */
		private int balance;

		public AVLNode(E item) {
			super(item);
			balance = BALANCED ;
		}
 	}

 	private boolean increase;

 	public AVLTree(){
 		super();
 	}

 	public boolean insert(E item){
 		increase = false;
 		root = add((AVLNode<E>)root, item);
 		return addReturn;
 	}

 	private AVLNode<E> add(AVLNode<E> tempRoot, E item){
 		if(tempRoot == null){
			addReturn = true;
			increase = true;
			return new AVLNode<E>(item);
		}
		if(item.compareTo(tempRoot.data) == 0) {
			// Item is a lready in the tree.
			increase = false;
			addReturn = false;
			return tempRoot;
		}
		else if(item.compareTo(tempRoot.data) < 0){
			// item < data
			tempRoot.left = add((AVLNode<E>)tempRoot.left, item);
			if(increase)
				decrementBalance(tempRoot);
			if(tempRoot.balance < AVLNode.LEFT_HEAVY) {
				increase = false ;
				return rebalanceleft(tempRoot) ;
			}
		}
		else{
            // item > data
            tempRoot.right = add((AVLNode<E>) tempRoot.right, item);
            if(increase){
                incrementBalance(tempRoot);
                if(tempRoot.balance > AVLNode.RIGHT_HEAVY) {
                    increase = false;
                    return rebalanceRight(tempRoot);
                }
            }
        }
		return tempRoot ;
 	}

 	private AVLNode<E> rebalanceleft(AVLNode<E> tempRoot) {
		// Obta1n reference to left ch1 1d.
		AVLNode<E> leftChild = (AVLNode<E>)tempRoot.left ;
		// See whether 1eft-r1ght hea�.
		if (leftChild.balance > AVLNode.BALANCED) {
			// Obta1n reference to 1eft-r1ght ch1 1d.
			AVLNode<E> leftRightChild = (AVLNode<E>)leftChild.right;
			/** Adjust the ba lances to be the1r new va lues after
			the rotat7ons are performed.
			*/
			if (leftRightChild.balance < AVLNode .BALANCED) {
				leftChild.balance = AVLNode.BALANCED;
				leftRightChild.balance = AVLNode.BALANCED;
				tempRoot.balance = AVLNode.RIGHT_HEAVY;
			}
			else {
				leftChild.balance = AVLNode.LEFT_HEAVY;
				leftRightChild.balance = AVLNode.BALANCED;
				tempRoot.balance = AVLNode.BALANCED;
			}
			// Perform left rotat7on.
			tempRoot.left = rotateLeft(leftChild);
		}
		else{ // left left
			leftChild.balance = AVLNode.BALANCED;
			tempRoot.balance = AVLNode.BALANCED;
		}
		return (AVLNode<E>) rotateRight(tempRoot) ;
	}


	private AVLNode<E> rebalanceRight(AVLNode<E> tempRoot) {
        // Obtain reference to right child.
        AVLNode<E> rightChild = (AVLNode<E>) tempRoot.right;
        // See whether right left heavy.
        if (rightChild.balance < AVLNode.BALANCED) 
        {
            // Obtain reference to leftright child.
            AVLNode<E> rightLeftChild = (AVLNode<E>) rightChild.left;

            if (rightLeftChild.balance > AVLNode.BALANCED){
                rightChild.balance = AVLNode.BALANCED;
                rightLeftChild.balance = AVLNode.BALANCED;
                tempRoot.balance = AVLNode.LEFT_HEAVY;
            } 
            else {
                rightChild.balance = AVLNode.RIGHT_HEAVY;
                rightLeftChild.balance = AVLNode.BALANCED;
                tempRoot.balance = AVLNode.BALANCED;
            }
            // Perform left rotation.
            tempRoot.right = rotateRight(rightChild);
        } 
        else{ 
            // Right Right case
            rightChild.balance = AVLNode.BALANCED;
            tempRoot.balance = AVLNode.BALANCED;
        }
            // Now rotate the local root right.
            return (AVLNode<E>) rotateLeft(tempRoot);
    }

	private void decrementBalance(AVLNode<E> node){
		// Decrement the balance.
		node.balance--;
			/** If now balanced, overall height has not increased. */
		if (node.balance == AVLNode.BALANCED)
			increase = false; 
	}

	private void incrementBalance(AVLNode<E> node){
        //Decrement the balance.
        node.balance++;
        /**	If now balanced, overall height has not increased. */
        if (node.balance == AVLNode.BALANCED) 
            increase = true;
    }

    public AVLTree<E> headSet(E item){
    	AVLTree<E> tempTree = new AVLTree<E>();
    	Iterator<E> iter = iterator();
    	while(iter.hasNext()){
    		E temp = iter.next();
    		if(temp.compareTo(item) < 0){
    			tempTree.insert(temp);
    		}
    	}
    	return tempTree;
    }

    public AVLTree<E> tailSet(E item){
    	AVLTree<E> tempTree = new AVLTree<E>();
    	Iterator<E> iter = iterator();
    	while(iter.hasNext()){
    		E temp = iter.next();
    		if(temp.compareTo(item) > 0){
    			tempTree.insert(temp);
    		}
    	}
    	return tempTree;
    }

    public Iterator<E> iterator() {
        return new AVLIterator();
    }

    private class AVLIterator<E> implements Iterator<E> {
        AVLNode<E> localNode = (AVLNode<E>) root;
        Stack<AVLNode<E>> stack;

        public AVLIterator() {
            stack = new Stack<AVLNode<E>>();

            while (localNode != null) {
                stack.push(localNode);
                localNode = (AVLNode<E>)localNode.left;
            }
        }

        public boolean hasNext() {
            return !stack.empty();
        }

        public E next() {
            AVLNode<E> node = stack.pop();
            E e = node.data;
            if (node.right != null) {
                node = (AVLNode<E>)node.right;
                while (node != null) {
                    stack.push(node);
                    node = (AVLNode<E>)node.left;
                }
            }
            return e;
        }


    }



}