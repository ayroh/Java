public class BSTHeapTree<E extends Comparable<E>>{

	public treeNode<E> tree = new treeNode<E>();
	private int findTemp = 0;
	private int findModeSize = 0;
	public E findModeTemp;
	private tryheap.Node<E> temp = null;

	/**
	If there is already 'e' then increments nodes size, otherwise adds to BSTHeapTree
	@param e data to be added
	@return number of occurences
	*/

	public int add(E e){
		if(find(e) == 0){		// there is no 'e'
			int sizetemp = addRecursive(tree, e);
			removeBSTrecursive(tree);
			sortAll();
			return sizetemp;
		}
		else{					// there is an 'e' so increment it
			incrementRecursive(tree, e);
			int tempRet = findTemp;
			findTemp = 0;
			return tempRet;
		}

	}


	/**
	Finds proper treeNode recursively and adds 'e' to it
	@param current Current treeNode
	@param e data to be added
	@return number of occurences
	*/

	public int addRecursive(treeNode<E> current, E e){
		if(current.head.add(e) == true)	
			return find(e);
		int temp = -1;
		if(current.head.getRoot().compareTo(new tryheap.Node<E>(e)) == 1){		// if 'e' is smaller
			if(current.left == null)
				current.left = new treeNode<E>();
			temp = addRecursive(current.left, e);
		}
		else if(current.head.getRoot().compareTo(new tryheap.Node<E>(e)) == -1){ // if 'e' is bigger
			if(current.right == null)
				current.right = new treeNode<E>();
			temp = addRecursive(current.right, e);
		}
		return temp;
	}

	/**
	Find Node<E> of 'e' from all treeNodes and increments already added 'e's size
	@param current Current treeNode
	@param e data to be added
	*/

	public void incrementRecursive(treeNode<E> current, E e){
		if(current.head.contains(e)){
			current.head.add(e);
			findHeapRecurrence(current, e);
		}
		if(current.left != null)
			incrementRecursive(current.left, e);
		if(current.right != null)
			incrementRecursive(current.right, e);
	}

	/**
	Removes 'e' from tree and sorts tree, if not does nothing
	@param e data to be added
	@return number of occurences
	*/

	public int remove(E e){
		removeRecursive(tree, e);
		sortAll();
		temp = null;
		return find(e);	
	}

	/**
	Finds treeNode of 'e' then removes it from there, if treeNode becomes empty removes it too
	@param current Current treeNode
	@param e data to be added
	*/

	public void removeRecursive(treeNode<E> current, E e){
		tryheap<E> heaptemp = new tryheap<E>();
		E rootTemp = current.head.getRoot().getData();
		while(current.head.peek() != null){
			if(current.head.peek().getData().compareTo(e) == 0){
				if(current.head.peek().getData().compareTo(rootTemp) == 0 && current.head.peek().size() == 1){
					temp = current.head.poll();
					removeBSTrecursive(current);
					if(current.head.size() == 0)
						eraseEmpty();
				}
				else
					temp = current.head.poll();
				break;
			}
			heaptemp.add(current.head.poll().getData());
		}
		while(heaptemp.peek() != null)
			current.head.add(heaptemp.poll().getData());
		if(temp == null){
			if(current.left != null)
				removeRecursive(current.left, e);
			if(current.right != null)
				removeRecursive(current.right, e);
		}
	}


	/**
	Sorts tree
	*/

	public void sortAll(){
		sortAllRecursive(tree);
	}

	/**
	Tries to sort every element at the tree
	@param current Current treeNode
	*/

	public void sortAllRecursive(treeNode<E> current){
		removeBSTrecursive(current);
		if(current.left != null)
			removeBSTrecursive(current.left);
		if(current.right != null)
			removeBSTrecursive(current.right);
	}

	/**
	Erases empty treeNodes
	*/

	public void eraseEmpty(){
		eraseEmptyRecursive(tree);
	}

	/**
	Controls every treeNode if there is empty and removes it recursively by pushing it far left or far right of its current branch then makes it null
	@param current Current treeNode
	*/

	public void eraseEmptyRecursive(treeNode<E> current){
		if(current.left != null && current.left.head.size() == 0){
			treeNode<E> treetemp = current;
			while(treetemp.left.left != null){
				while(treetemp.left.left.head.peek() != null){
					treetemp.left.head.add(treetemp.left.left.head.poll().getData());
				}
				treetemp = treetemp.left;
			}
			treetemp.left = null;
		}
		if(current.right != null && current.right.head.size() == 0){
			treeNode<E> treetemp = current;
			while(treetemp.right.right != null){
				while(treetemp.right.right.head.peek() != null){
					treetemp.right.head.add(treetemp.right.right.head.poll().getData());
				}
				treetemp = treetemp.right;
			}
			treetemp.right = null;
		}
		if(current.left != null)
			eraseEmptyRecursive(current.left);
		if(current.right != null)
			eraseEmptyRecursive(current.right);
	}

	/**
	Removes empty treeNodes recursively
	@param current Current treeNode
	*/

	public void removeBSTrecursive(treeNode<E> current){
		if(current.left != null && current.head.getRoot().getData().compareTo(current.left.head.getRoot().getData()) == -1){	// looks to left and swaps current.left and current.left.left to push to far left
			tryheap<E> heaptemp = new tryheap<E>();
			while(current.head.peek() != null)
				heaptemp.add(current.head.poll().getData());
			while(current.left.head.peek() != null)
				current.head.add(current.left.head.poll().getData());
			while(heaptemp.peek() != null)
				current.left.head.add(heaptemp.poll().getData());
			removeBSTrecursive(current.left);
		}
		else if(current.right != null && current.head.getRoot().getData().compareTo(current.right.head.getRoot().getData()) == 1){   // looks to right and swaps current.right and current.right.right to push to far right
			tryheap<E> heaptemp = new tryheap<E>();
			while(current.head.peek() != null)
				heaptemp.add(current.head.poll().getData());
			while(current.right.head.peek() != null)
				current.head.add(current.right.head.poll().getData());
			while(heaptemp.peek() != null)
				current.right.head.add(heaptemp.poll().getData());
			removeBSTrecursive(current.right); 
		}
	}

	/**
	Prints all elements
	*/

	public void print(){
		printRecursive(tree);
	}

	/**
	Prints all nodes recursively
	@param current Current treeNode
	*/

	public void printRecursive(treeNode<E> current){
		current.head.print();
		if(current.left != null)
			printRecursive(current.left);
		if(current.right != null)
			printRecursive(current.right);
	}

	/**
	Finds spesific 'e's number of recurrences
	@param e data to be added
	@return number of recurrences
	*/

	public int find(E e){
		findHeapRecurrence(tree, e);
		int tempRet = findTemp;
		findTemp = 0;
		return tempRet;
	}

	/**
	Finds number of recurrences recursively and stores it to 'findTemp'
	@param current Current treeNode
	@param e data to be added
	*/

	public void findHeapRecurrence(treeNode<E> current, E e){
		tryheap.Node<E> f = new tryheap.Node<E>(e);
		int size = current.head.size();
		tryheap<E> temp = new tryheap<E>();
		while(current.head.peek() != null){
			if(current.head.peek().compareTo(f) == 0){
				findTemp = current.head.peek().size();
				break;
			}
			temp.add(current.head.poll().getData());
		}
		while(temp.peek() != null){
			current.head.add(temp.poll().getData());
		}
		if(findTemp != 0)
			return;
		if(current.left != null)
			findHeapRecurrence(current.left, e);
		if(current.right != null)
			findHeapRecurrence(current.right, e);
	}

	/**
	Finds mode of tree
	*/

	public void find_mode(){
		findModeRecurrence(tree);
		System.out.printf("Mode of BSTHeapTree: (%d,%d)\n", findModeTemp,findModeSize);
		findModeSize = 0;
	}

	/**
	Finds mode of tree recursively
	@param current Current treeNode
	*/

	public void findModeRecurrence(treeNode<E> current){
		tryheap<E> heaptemp = new tryheap<E>();
		
		while(current.head.peek() != null){
			if(current.head.peek().size() > findModeSize){
				findModeSize = current.head.peek().size();
				findModeTemp = current.head.peek().getData();
			}
			heaptemp.add(current.head.poll().getData());
		}
		while(heaptemp.peek() != null)
			current.head.add(heaptemp.poll().getData());
		if(current.left != null)
			findModeRecurrence(current.left);
		if(current.right != null)
			findModeRecurrence(current.right);
	}
	

	/**
	Stores a tryheap and 2 references to treeNode<E>
	*/
	public static class treeNode<E extends Comparable<E>>{
		public tryheap<E> head = new tryheap<E>();
		public treeNode<E> left = null, right = null;
	}

}