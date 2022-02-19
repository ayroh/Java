import java.util.*;

public class CoalescedHash<K, V> implements KWHashmap <K,V>{
	/** Array of Entry's */
	public Entry<K, V> [] table;
	/** Number of elements */
	private int numKeys = 0;
	/** Starting capacity */
	private static final int CAPACITY = 10;
	/** For expanding control */
	private static final double LOAD_THRESHOLD = 3;

	CoalescedHash(){
		table = new Entry[CAPACITY];
	}

	/**
	@return Size of array
	*/
	public int size(){
		return table.length;
	}


	public boolean isEmpty(){
		return numKeys <= 0;
	}

	/**
	Prints all elements.
	*/
	public void print(){

		for(int i = 0;i < table.length;i++){
			Entry temp = table[i];
			System.out.printf("%d: ",i);
			while(temp != null){
				System.out.printf("%s ",temp.key);
				if(temp.next != null)
					temp = table[temp.next];
				else
					temp = null;
			}
			System.out.printf("\n");
		}
	}

	/**
	Adds element to table.If key exists then value will be changed,
	if elements index is full and same hash then it adds to end of tail,
	if elements index is full but hash is not same then tries quadratic probing to find empty place then adds if can't find a place then rehashes then adds.
	@return if added return value otherwise null 
	*/

	public V put(K key, V value) {
		int counter = 1;
		int index = key.hashCode() % table.length;
		if (index < 0)
			index += table.length;
		if (table[index] == null){
			// Create a new linked list at tabl e[i ndex] .
			table[index] = new Entry<K, V>(key, value);
			numKeys++;
			return value;
		}
		else if(table[index].key.hashCode() % table.length == index){	//	if found with same index
			boolean changed = false;
			Entry temp = table[index];
			
				while(temp.next != null){
					if(temp.key.equals(key)){
						V oldValue = (V)temp.getValue();
						temp.setValue(value);
						return oldValue;
					}
					counter++;
					temp = table[temp.next];
				}
				if(temp.key.equals(key)){							// if element found then changes its value
						V oldValue = (V)temp.getValue();
						temp.setValue(value);
						return oldValue;
				}
			
			if(!changed){											// if no element found then tries quadratic probing if can't find a place then extends array then tries again
				while(counter < CAPACITY){
					int numtemp = (counter * counter + temp.key.hashCode()) % table.length;
					if(table[numtemp] == null){
						temp.next = new Integer(numtemp);
						table[numtemp] = new Entry<K, V>(key, value);			
						return value;
					}
					counter++;
				}
				if(counter == CAPACITY){
					rehash();
					return put(key,value);
				}
			}
		}
		else{												// if index isn't same where it should be then tries quadratic probing to find an empty place
			Entry previousEntry = null;
			while(counter < CAPACITY){
				int numtemp = (counter * counter + index) % table.length;
				if(table[numtemp] == null){
					table[numtemp] = new Entry<K, V>(key, value);
					if(previousEntry != null)
							previousEntry.next = new Integer(numtemp);
					numKeys++;
					return value;
				}
				else if(table[numtemp].key.equals(key)){
					V oldValue = (V)table[numtemp].getValue();
					table[numtemp].setValue(value);
					return oldValue;
				}
				else if(table[numtemp].key.hashCode() % table.length == index)
						previousEntry = table[numtemp];
				counter++;
			}
			if(counter == CAPACITY){
				rehash();
				return put(key,value);
			}
		}
		return null ;
	}


	/**
	Looks for keys index. If index is null then returns null,
	 if index is not null and indexes value is equal to key then
	 removes it and bring back all next elements 1 time and equals tail to
	 null and looks for resort(), if index is not null but index is not equal
	 then tries to find a key with same index with quadratic probing
	 when finds it then tries to find key with iterator if finds it 
	 then removes it then looks for resort().
	@param key Key of the value to be removed
	@return Old Value
	*/

	public V remove(Object key){
		int index = key.hashCode() % table.length ;
		if (index < 0)
			index += table.length;

		for(int i = 0;i < CAPACITY;i++){					// starts from 0 with quadratic probing try to goes forward 100 elements
			int numtemp = (i * i + index) % table.length;
			if(table[numtemp] == null)																		// if index is null returns null
				return null;
			else if(table[numtemp].next != null && table[table[numtemp].next].getKey().equals(key)){		// if next element of table[index] is equal to key then removes it and and bring back all next elements 1 time and equals last Entry to null
				V Value = table[numtemp].getValue();
				Entry temp = table[numtemp];
				while(table[temp.next].next != null){
					temp = table[temp.next];
					temp.setValue(table[temp.next].getValue());
					temp.key = table[temp.next].key;
				}
				table[temp.next] = null;
				reSort(temp.next);
				temp.next = null;
				numKeys--;
				return Value;
			}
			else if(table[numtemp].getKey().equals(key)){													// if current index is equal to key it means it is head of its tail currently so if next is empty table[numtemp] = null is enough otherwise bring back all next elements 1 time
				V Value = table[numtemp].getValue();
				Integer sorttemp = numtemp;
				Entry temp = table[numtemp];
				if(temp.next == null)
					table[numtemp] = null;
				else{
					while(table[temp.next].next != null){
						temp.key = table[temp.next].key;
						temp.setValue(table[temp.next].getValue());
						temp = table[temp.next];
					}
					temp.key = table[temp.next].key;
						temp.setValue(table[temp.next].getValue());
					table[temp.next] = null;
					sorttemp = temp.next;
					temp.next = null;
				}
				reSort(sorttemp);
				numKeys--;
				return Value;
			}
		}
		return null;
	}


	/**
	If any element removed from Hash this function is called because every remove equals null an element. That means some place is empty now and in some cases where quadrating probing is used an element and its tails needs to be somewhere else.
	@param numtemp starting index
	*/
	private void reSort(int numtemp){
		Entry temp;
		for(int j = 1;j < table.length;j++){
			int numtemp2 = (j * j + numtemp) % table.length;
			if(table[numtemp2] == null)
				continue;
			//System.out.printf("numtemp2: %d, numtemp: %d\n",table[numtemp2].getKey().hashCode() % table.length,numtemp);
			if(table[numtemp2].getKey().hashCode() % table.length == numtemp){
				table[numtemp] = new Entry(table[numtemp2].getKey(), table[numtemp2].getValue());
				if(table[numtemp2] != null)
					table[numtemp].next = numtemp2;
					//System.out.printf("%s numtemp2\n",numtemp2);
				//print();
				temp = table[numtemp];
				Entry prevtemp = temp;

				while(temp.next != null){
					temp.key = table[temp.next].key;
					temp.setValue(table[temp.next].getValue());
					prevtemp = temp;
					temp = table[temp.next];
				}
				Integer sorttemp = prevtemp.next;
				table[prevtemp.next] = null;
				prevtemp.next = null;
				//System.out.printf("\n\n\n\n");
				reSort(sorttemp);
				break;
			}
		}
	}

	/**
	Extends array if quadratic probing can't finds any place to add element
	*/
	private void rehash() {
		// Save a reference to oldTable.
		Entry<K, V> [] oldTable = table;
		//I Doub7e capacity of this table.
		table = new Entry[2 * oldTable.length + 1];
		// Reinsert all items in oldTable into expanded table.
		//numKeys = 0;
		//numDeletes = 0;
		for(int i = 0; i < oldTable.length ; i++){
			if(oldTable[i] != null){
				put(oldTable[i].getKey(), oldTable[i].getValue());
			}
		}
		
	}

	/** Method get for c1ass Hashtabl eChai n.
	@param key The key being sought
	@return The va1ue associated with this key if found;
	otherwise, null
	*/
	//@Override
	public V get(Object key) {
		int index = key.hashCode() % table.length ;
		if (index < 0)
			index += table.length;

		if (table[index] == null)
			return null ; // key is not in the table.
		Entry temp = table[index];
		while(temp.next != null){
			if(temp.key.equals(key))
				return (V)temp.getValue();
			temp = table[temp.next];
		}
		if(temp.key.equals(key))
				return (V)temp.getValue();
			

		// assert: key is not in the tab1e.
		return null ; 
	}



	private  class Entry<K , V> {
		/** The key */
		private K key;
		/** The va1ue */
		private V value;
		/** The next */
		public Integer next = null;

		/** Creates a new key-va 1ue pair.
		@param key The key
		@param value The va1ue
		*/
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		/** Retrieves the key.
		@return The key
		*/
		public K getKey() {
			return key;
		}

		/** Retrieves the va1ue.
		@return The va1ue
		*/
		public V getValue() {
			return value;
		}

		/** Sets the va 1ue.
		@param val The new va1ue
		@return The o1d va1ue
		*/
		public V setValue(V val) {
			V oldVal = value ;
			value = val ;
			return oldVal ;
		}
	}

}