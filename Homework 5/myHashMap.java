import java.util.*;
import java.util.NoSuchElementException;

public class myHashMap<K,V> extends HashMap<K,V>{
	/** Holds elements to iterate over them */
	ArrayList<K> list = new ArrayList<K>();

	/**
	Adds element to both HashMap and ArrayList
	@param key Key to access value
	@param value Value of the key
	@return Old Value
	*/
	@Override
	public V put(K key, V value){
		if(!list.contains(key))
			list.add(key);
		return super.put(key, value);
	}

	/**
	@return mapIterator from start
	*/
	public mapIterator MapIterator(){
		return new mapIterator();
	}

	/**
	@return mapIterator from an element
	*/
	public mapIterator MapIterator(K key){
		return new mapIterator(key);
	}

	/**
	Class to iterate over HashMap
	*/

	public class mapIterator{

		/** Current point */
		int index;
		/** Ending point */
		int endPoint;
		/** Check for if first time called hasNext() for evading 'index == endpoint' from start */
		boolean firstTime = true;

		public mapIterator(){
			index = 0;
			endPoint = 0;
		}

		public mapIterator(K key){
			this();
			if(list.indexOf(key) != -1)
				index = endPoint = list.indexOf(key);
			
		}

		K next(){
			if(index == endPoint && !firstTime)
				throw new NoSuchElementException();

			if(firstTime)
				firstTime = false;

			if(index + 1 == size()){
				int temp = index;
				index = 0;
				return (K)list.get(temp);
			}
			else
				return (K)list.get(index++);
		}

		K prev(){
			if(index == endPoint && !firstTime)
				throw new NoSuchElementException();

			if(firstTime)
				firstTime = false;

			if(index == 0){
				index = size() - 1;
				return list.get(0);
			}
			else
				return list.get(index--);
		}

		boolean hasNext(){
			if(firstTime)
				return true;
			return index != endPoint;
		}



	}


}