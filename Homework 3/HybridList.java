import java.util.*;
import java.lang.IndexOutOfBoundsException;

public class HybridList<E>{
	private KWLinkedList<KWArrayList<E>> List = new KWLinkedList<KWArrayList<E>>();
	private final int MAX_NUMBER = 10;
	private int size = 0;
	private int index = 0;


	/**
	Returns size
	*/
	public int size(){
		return size;
	}

	/**
	Adds item to end of the last KWArrayList
	@param item item to be added
	*/

	public void add(E item){
		if(List.size() == 0){
			List.add(new KWArrayList<E>());
		}
		
		ListIterator<KWArrayList<E>> Iter = List.ListIterator(index);
		KWArrayList<E> temp = Iter.next();
		if(temp.size() < MAX_NUMBER)
			temp.add(item);
		else{
			Iter.add(new KWArrayList<E>());
			index++;
			add(item);
			return;
		}
		size++;
	}


	/**
	Adds item by the index
	@param index
	@param item item to be added
	*/
	public void add(int index, E item){
		if(index > size || index < 0)
			throw new IndexOutOfBoundsException();
		size++;
		if(index == size){
			add(item);
			return;
		}
		int i = 0;
		while(index > 9){
			i++;
			index -= 10;
		} 
		ListIterator<KWArrayList<E>> Iter = List.ListIterator(i);
		KWArrayList<E> temp = Iter.next();
		if(temp.size() != 10){
			temp.add(index, item);
			return;
		}
		E itemTemp = temp.get(9);
		temp.remove(9);
		temp.add(index, item);
		E anotherItemTemp;
		while(Iter.hasNext()){
			temp = Iter.next();
			if(temp.size() != 10){
				temp.add(index, item);
				return;		
			}
			else{
				anotherItemTemp = temp.get(9);
				temp.remove(9);
				temp.add(0, itemTemp);
				itemTemp = anotherItemTemp;
			}
		}
	}

	/**
	Removes item by the index
	@param index
	*/
	public void remove(int index){
		if(index >= size || index < 0)
			throw new IndexOutOfBoundsException();
		int i = 0;
		while(index > 9){
			i++;
			index -= 10;
		}
		ListIterator<KWArrayList<E>> Iter = List.ListIterator(i);
		KWArrayList<E> temp = Iter.next();
		temp.remove(index);
		while(Iter.hasNext()){
			KWArrayList<E> nextTemp = Iter.next();
			temp.add(nextTemp.get(0));
			nextTemp.remove(0);
			temp = nextTemp;
		}
		if(temp.size() == 0)
			List.remove(List.size() - 1);			
		size--;			
	}

	/**
	Returns the item by the index
	@param index
	@throws IndexOutOfBoundsException
	*/
	public E get(int index){
		if(List.size() == 0)
			throw new IndexOutOfBoundsException();
		ListIterator<KWArrayList<E>> Iter = List.ListIterator(0);
		while(index > 9){
			if(Iter.hasNext()){
				index -= 10;
				Iter.next();
			}
			else
				throw new IndexOutOfBoundsException();
		}
		KWArrayList<E> temp;
		if(Iter.hasNext())
			temp = Iter.next();
		else
			throw new IndexOutOfBoundsException();
		return temp.get(index);
	}



}