import java.util.*;

public class KWArrayList<E> extends AbstractList<E>{
	// Data Fields
	/** The default initial capacity */
	private static final int INITIAL_CAPACITY = 10;
	/** The underlying data array */
	private E[] theData;
	/** The current size */
	private int size = 0;
	/** The current capacity */
	private int capacity = 0;

	public KWArrayList() {
		 capacity = INITIAL_CAPACITY;
		 theData = (E[]) new Object[capacity];
	}

	/**
	Returns size
	*/
	public int size(){
		return size;
	}


	/**
	Adds item to end of arraylist
	@param anEntry item to be added
	*/
	public boolean add(E anEntry) {
		 if (size == capacity)
			 reallocate();
		 theData[size] = anEntry;
		 size++;
		 return true;
	}


	/**
	Adds item to arraylist by the index
	@param index
	@param anEntry item to be added
	@throws ArrayIndexOutOfBoundsException
	*/
	public void add(int index, E anEntry) {
		if (index < 0 || index > size)
			throw new ArrayIndexOutOfBoundsException(index);
		if (size == capacity)
				reallocate();
		// Shift data in elements from index to size - 1
		for (int i = size; i > index; i--)
			theData[i] = theData[i - 1];
		// Insert the new item.
		theData[index] = anEntry;
		size++;
	}

	/**
	Returns item by the index
	@param index
	@throws ArrayIndexOutOfBoundsException
	*/
	public E get(int index) {
		if (index < 0 || index >= size)
			throw new ArrayIndexOutOfBoundsException(index);
		return theData[index];
	}

	/**
	Replaces item by the index
	@param index
	@param newValue item to be added
	@throws ArrayIndexOutOfBoundsException
	*/
	public E set(int index, E newValue) {
		if(index < 0 || index >= size)
			throw new ArrayIndexOutOfBoundsException(index);
		E oldValue = theData[index];
		theData[index] = newValue;
		return oldValue;
	}

	/**
	Removes item by the index
	@param index
	@throws ArrayIndexOutOfBoundsException
	*/
	public E remove(int index) {
		if (index < 0 || index >= size)
			throw new ArrayIndexOutOfBoundsException(index);
		E returnValue = theData[index];
		for (int i = index + 1; i < size; i++)
			theData[i - 1] = theData[i];
		size--;
	 	return returnValue;
	}


	private void reallocate() {
		capacity = 2 * capacity;
		theData = Arrays.copyOf(theData, capacity);
	}

}