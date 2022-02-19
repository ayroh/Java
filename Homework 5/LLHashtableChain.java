import java. util .*;


public class LLHashtableChain<K, V> implements KWHashmap <K,V>{

private LinkedList<Entry<K, V>> [] table;
private int numKeys = 0;
private static final int CAPACITY = 10;
private static final double LOAD_THRESHOLD = 3;


	public LLHashtableChain () {
		table = new LinkedList [CAPACITY];
	}

	public int size(){
		return table.length;
	}


	public boolean isEmpty(){
		return numKeys <= 0;
	}

	public void print(){
			for(int i = 0;i < table.length;i++){
				System.out.printf("\n%d: ",i);
				if(table[i] == null)
					continue;
				ListIterator<Entry<K,V>> iter = table[i].listIterator(0);
				while(iter.hasNext())
					System.out.printf("%s ",iter.next().getValue());
				
			}
			System.out.printf("\n");

		}

	private  class Entry<K , V> {
		/** The key */
		private K key;
		/** The va1ue */
		private V value;

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


	/** Method put for class HashtableChain.
	post: This key-value pair is inserted in the
	table and numKeys is incremented. If the key is already
	in the table, its va lue is changed to the argument
	va lue and numKeys is not changed.
	@param key The key of item being inserted
	@param val ue The va lue for this key
	@return The old va lue associated with this key if
	fOund; otherwise, null
	*/
	//@Override
	public V put(K key, V value) {
		int index = key.hashCode() % table.length;
		if (index < 0)
			index += table.length;
		if (table[index] == null) {
			// Create a new linked list at tabl e[i ndex] .
			table[index] = new LinkedList<Entry<K, V>>();
		}
		// Search the list at tabl e [i ndex] to find the key.
		for (Entry<K, V> nextItem : table[index]) {
		// If the search is successful, replace the old va lue.
			if (nextItem.key.equals(key)){
				// Replace value for this key.
				V oldVal = nextItem.value;
				nextItem.setValue(value);
				return oldVal;
			}
		}
		// assert: key is not in the table, add new item.
		table[index].addFirst(new Entry<K, V>(key , value)) ;
		numKeys++;
		if (numKeys > (LOAD_THRESHOLD * table.length))
			rehash() ;
		return null ;
	}

	/** 
		Extends array if numKeys is passes threshold.
	*/
	private void rehash() {
		// Save a reference to oldTable.
		LinkedList<Entry<K, V>> [] oldTable = table;
		//I Doub7e capacity of this table.
		table = new LinkedList[2 * oldTable.length + 1];
		// Reinsert all items in oldTable into expanded tab7e.
		//numKeys = 0;
		//numDeletes = 0;
		for(int i = 0; i < oldTable.length ; i++){
			if(oldTable[i] != null){
				ListIterator<Entry<K,V>> iter = oldTable[i].listIterator(0);
				// Insert entry in expanded tab7e
				while(iter.hasNext()){
					Entry<K, V> enttemp = iter.next();
					put(enttemp.key, enttemp.value);
				}
			}
		}
	}

	/**
	If index is empty then returns null, if not searches its tail if finds it then removes it otherwise returns null
	@param key Key of value to be removed
	@return Value of key
	*/
	public V remove(Object key){
		int index = key.hashCode() % table.length ;
		if (index < 0)
			index += table.length;

		if (table[index] == null)
			return null ; // key is not in the table.
		V temp = null;

		Entry entrytemp = null;
		for (Entry<K, V> nextItem: table[index]) {
			if(nextItem.key.equals(key)){
				temp = nextItem.value;
				entrytemp = nextItem;
				break;
			}
		}
		if(entrytemp != null){
			table[index].remove(entrytemp);
			if(table[index].size() == 0)
				table[index] = null;
		}
		numKeys--;
		return temp;
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

		// Search the 1ist at tabl e [i ndex] to find the key.
		for (Entry<K, V> nextItem: table[index]) {
			if(nextItem.key.equals(key))
				return nextItem.value;
		}
		// assert: key is not in the tab1e.
		return null ; 
	}

} 