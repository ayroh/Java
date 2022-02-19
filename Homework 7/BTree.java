import java.util.*;

public class BTree<E extends Comparable<E>> extends BinarySearchTreeWithRotate<E>{
	/** A Node represents a node in a 8-tree. */
		public static class BNode<E> {
			// Data Fie1ds
			/** The number of data items in this node */
			private int size = 0;
			/** The information */
			public E[] data;
			/** The Jinks to the children. child[i] refers to
			the subtree of chi ldren < data [i] for i < si ze
			and to the subtree of children > data[si ze-1]
			for i == size */
			public BNode<E> [] child = null;
			/** Create an empty node of size order
			@param order The size of a node
			*/
			@SuppressWarnings ("unchecked")
			public BNode(int order) {
				data = (E[]) new Comparable[order - 1] ;
				child = (BNode<E>[]) new BNode [order] ;
				size = 0; 
			}
			public void print(){
				for(int i = 0;i < size;i++)
					System.out.printf("%d ",data[i]);
				System.out.printf("\n");
			}
		}

		public BNode<E> newChild = null;
		public E newParent = null;

		/** The root node. */
		public BNode<E> root = null ;
		/** The maximum number of chi 1dren of a node */
		private int order;
		/** Construct a 8-tree with node size order
		@param order the size of a node
		*/

		public void print(){

		}

		public BTree(int order) {
			this.order = order;
			root = null;
		}

		/**
		 * This function is completely from the book
		 * */
		public boolean insert(E item){
	        if(root == null){
	            root = new BNode<E>(order);
	            root.data[0] = item;
	            (root.size)++;
	            return true;
	        }
	        return insert(root, item);
    	}

		private boolean insert(BNode<E> root, E item) {
			//int index = binarySearch(item, root.data, 0, root.size) ;
			int index = Arrays.binarySearch(root.data, 0, root.size, item);
			if(index < 0)
				index = (index + 1) * (-1);
			root.print();
			System.out.println("INDEX: "+ index + " ITEM: " + item + " CURRENT_SIZE: " + root.size);
			if (index != root.size && item.compareTo(root. data[index]) == 0)
				return false ;
			if(root.child[index] == null) {
				if(root.size < order - 1) {
					insertintoNode(root, index, item, null);
					newChild = null;
				}
				else
					splitNode(root, index, item , null);
				return true;
			} 
			else{
				System.out.printf("                           yyyyyyyyyyyyyyyyyyyyyyyyy\n");
				boolean result = insert(root.child[index], item);
				if (newChild != null) {
					if(root.size < order - 1){
						insertintoNode(root , index , newParent , newChild);
						newChild = null;
					}
					else
						splitNode(root, index, newParent, newChild) ;
				}
				return result;
			}
		}

		private void insertintoNode(BNode<E> node, int index, E obj, BNode<E> child){
			for (int i = node.size;i > index;i--) {
				node.data[i] = node.data[i - 1];
				node.child[i + 1] = node.child[i];
			}
			node.data[index] = obj;
			node.child[index + 1] = child;
			node.size++;
		}

		private void splitNode(BNode<E> node, int index, E item, BNode<E> child) {
			// Create new chi 7d
			newChild = new BNode<E>(order) ;
			// Determine number of items to move
			int numToMove = (order - 1) - ((order - 1) / 2);
			// If insertion point is in the right ha 7f, move one Jess item
			if (index > (order - 1) / 2)
				numToMove--;
			// MOve items and their children
			System.arraycopy(node.data, order - numToMove - 1, newChild.data, 0, numToMove);
			System.arraycopy(node.child, order - numToMove, newChild.child, 1, numToMove); 
			node.size = order - numToMove - 1;
			newChild.size = numToMove;
			// Insert new item
			if (index == ((order - 1) / 2)) { // Insert as midd1e item
				newParent = item;
				newChild.child[0] = child;
			}
			else {
				if (index < ((order - 1) / 2)) // Insert into the 7eft
					insertintoNode(node , index , item , child) ;
				else
					insertintoNode(newChild, index - ((order - 1) / 2) - 1, item, child);
				// The rightmost item of the node is the new parent
				newParent = node.data[node.size - 1];

				// Its chi1d is the 7eft chi1d of the sp7it-off node
				newChild.child[0] = node.child[node.size];
				System.out.printf("\n");
				node.print();
				System.out.printf("\n");
				node.size--;
			}
			// Remove items and references to moved items
			for (int i = node.size;i < node.data.length;i ++) {
				node.data[i] = null;
				node.child[i + 1] = null;
			}
		}



}