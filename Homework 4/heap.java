import java.util.*;

public class heap<E extends Comparable<E>>{
	
	public PriorityQueue<E> maxHeap = new PriorityQueue<E>(Comparator.reverseOrder());

	/**
	@return Size of heap
	*/
	public int size(){
		return maxHeap.size();
	}

	/**
	Prints all elements
	*/

	public void print(){
		int size = maxHeap.size();
		PriorityQueue<E> heaptemp = new PriorityQueue<E>(Comparator.reverseOrder());
		while(maxHeap.peek() != null){
			System.out.printf("%d ",maxHeap.peek());
			heaptemp.add(maxHeap.poll());
		}
		while(heaptemp.peek() != null)
			maxHeap.add(heaptemp.poll());
		System.out.printf("\n");

	}


	/**
	Adds by the heap structure rules
	@param e data to be added
	*/
	void add(E e){
		maxHeap.add(e);
	}

	/**
	@param e data to be added
	@return true if it contains, otherwise false
	*/
	boolean contains(E e){
		return maxHeap.contains(e);
	}

	/**
	Adds all elements of other heap to this heap
	@param other Other tryheap to a added
	*/
	void merge(heap other){
		int size = other.size();
		for(int i = 0;i < size;i++){
			maxHeap.add((E)other.maxHeap.poll());

		}
	}
	
	/**
	Removes n'th biggest number in the heap
	@throws NoSuchElementException
	@param index index to be removed
	*/
	void removeIndex(int index){
		if(index < 0 || index > maxHeap.size())
			throw new NoSuchElementException();
		PriorityQueue<E> temp = new PriorityQueue<E>(Comparator.reverseOrder());
		int size = maxHeap.size();
		for(int i = 0;i < index - 1;i++)
			temp.add(maxHeap.poll());
		maxHeap.poll();
		size = temp.size();
		for(int i = 0;i < size;i++)
			maxHeap.add(temp.poll());
	}

	/**
	@return HeapIter of this heap
	*/
	HeapIter iterator(){
		return new HeapIter();
	}

	public class HeapIter implements Iterator<E>{
	
		private E nextItem = null;

		private E lastItemReturned = null;

		private int index = 0;

		public HeapIter(){
			nextItem = maxHeap.peek();
		}
		/**
		@return true if next item is not null, otherwise false
		*/	
		public boolean hasNext(){
			return nextItem != null;
		}

		/**
		@throws NoSuchElementException
		@return lastItemReturned of this heap
		*/
		public E next() {
			 if (!hasNext())
			 	throw new NoSuchElementException();
			 lastItemReturned = nextItem;
			 index++;
			 PriorityQueue<E> temp = new PriorityQueue<E>(Comparator.reverseOrder());
			 for(int i = 0;i < index;i++)
			 	temp.add(maxHeap.poll());
			 nextItem = maxHeap.peek();
			 for(int i = 0;i < index;i++)
			 	maxHeap.add(temp.poll());
			 return lastItemReturned;
		}

		/**
		Sets lastItemReturned to parameter 'e'
		@throws NoSuchElementException
		*/
		public void set(E e){
			if(lastItemReturned == null)
				throw new NoSuchElementException();
			removeIndex(index);
			add(e);
		}
	}

}