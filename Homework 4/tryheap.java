import java.util.*;

public class tryheap<E extends Comparable<E>>{
	
	public PriorityQueue<Node<E>> heap = new PriorityQueue<Node<E>>(new heapComparator());

	private final int MAX_SIZE = 7;

	/**
	Prints all elements
	*/
	
	public void print(){
		int size = heap.size();
		PriorityQueue<Node<E>> heaptemp = new PriorityQueue<Node<E>>(new heapComparator());
		while(heap.peek() != null){
			System.out.printf("(%d,%d)",heap.peek().getData(), heap.peek().size());
			heaptemp.add(heap.poll());
		}
		while(heaptemp.peek() != null)
			heap.add(heaptemp.poll());
		System.out.printf("\n");

	}

	/**
	@return Root Node
	*/

	public Node<E> getRoot(){
		return heap.peek();
	}


	/**
	@return Size of heap
	*/
	public int size(){
		return heap.size();
	}
/**
	Adds by the heap structure rules
	@param e data to be added
	@return true if added, false if size == MAX_SIZE
	*/
	boolean add(E e){
		Iterator<Node<E>> iter = heap.iterator();
		while(iter.hasNext()){
			Node<E> temp = iter.next();
			if(temp.getData().compareTo(e) == 0){
				temp.incrementSize();
				return true;
			}
		}
		if(size() == MAX_SIZE)
			return false;
		Node<E> f = new Node<E>(e);
		heap.add(f);
		return true;
	}

	public Node<E> poll(){
		if(heap.size() == 0)
			return null;
		if(heap.peek().size() == 1)
			return heap.poll();
		else{
			heap.peek().decrementSize();
			return heap.peek();
		}

	}

	public Node<E> peek(){
		if(heap.size() == 0)
			return null;
		return heap.peek();
	}

	/**
	@param e data to be added
	@return true if it contains, otherwise false
	*/
	boolean contains(E e){
		Node<E> f = new Node<E>(e);
		int size = heap.size(), i;
		PriorityQueue<Node<E>> temp = new PriorityQueue<Node<E>>(new heapComparator());
		for(i = 0;i < size;i++){
			if(f.compareTo(heap.peek()) == 0)
				break;
			temp.add(heap.poll());
		}
		if(i == size){
			for(i = 0;i < size;i++)
				heap.add(temp.poll());
			return false;
		}
		else{
			for(i = size - i;i < size;i++)
				heap.add(temp.poll());
			return true;
		}
	}

	/**
	Adds all elements of other heap  to this heap
	@param other Other tryheap to a added
	*/

	void merge(tryheap other){
		int size = other.size();
		for(int i = 0;i < size;i++){
			heap.add((Node<E>)other.heap.poll());

		}
	}
	
	/**
	Removes n'th biggest number in the heap
	@throws NoSuchElementException
	@param index index to be removed
	*/
	void removeIndex(int index){
		if(index < 1 || index > heap.size())
			throw new NoSuchElementException();
		PriorityQueue<Node<E>> temp = new PriorityQueue<Node<E>>(new heapComparator());
		int size = heap.size();
		for(int i = 0;i < index - 1;i++)
			temp.add(heap.poll());
		heap.poll();
		size = temp.size();
		for(int i = 0;i < size;i++)
			heap.add(temp.poll());
	}
	

	public static class Node<E extends Comparable<E>>{
		
		private E data;

		
		private int size;

		public Node(E e){
			data = e;
			size = 1;
		}
		public int size(){
			return size;
		}

		public E getData(){
			return data;
		}

		private void incrementSize(){
			size++;
		}

		private void decrementSize(){
			size--;
		}

		public int compareTo(Node<E> other) {
	        return data.compareTo(other.getData());
	    }

	}


}