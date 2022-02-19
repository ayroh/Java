/**
 * class linkedlist
 * @author Bilal Yalcinkaya
 */

public class linkedlist<E> implements queue<E>,list<E>{
	
	private Object[] objT;
	private int used,capacity,first;
	
	public linkedlist(){
		used = capacity = first = 0;
		objT = null;
	}
	
	public iterator iterator(){		// creates an iterator and bounds it to this spesific object
		iterator myit = new iterator();
		myit.setIter(this);
		return myit;
	}
	
	public  int size(){
		return used - first;
	}
	
	public boolean add(E e){			// if capacity is full -> extends and adds the element
		if(capacity == used)
			extend();
		
		objT[used] = e;
		++used;
		return true;
	}
	
	public boolean offer(E e){			// if you use offer you can't duplicate
		for(int i = first;i < used;++i){
			if(objT[i].equals(e))
				return true;
		}
		if(capacity == used)
			extend();
			
		objT[used] = e;
		++used;
		return true;
	}
	public E element(){
		return (E)objT[first];
	}
	public E poll(){
		E x = (E)objT[first];
		objT[first] = null;
		first++;
		return x;
	}
	
	public  boolean addAll(collection c){		// tries 'add(E e)' for all of 'c's objects
		int i,j;
		for(i = 0;i < c.size();++i){
		try{		
			add((E)c.getIndex(i));
		}
		catch(WrongIndex e){
			System.out.printf("\nTry a valid index number.");
		}
		}
		return true;
	}
	
	public  void clear(){
		used = capacity = 0;
		objT = null;
	}
	
	public  boolean contains(E e){		// i used equals function of Object class
		for(int i = first;i < used;++i){
			if(objT[i].equals(e))
				return true;
		}
		return false;
	}
	
	public  boolean containsAll(collection c){
		int i,j;
		for(i = 0;i < c.size();++i){
			for(j = first;j < used;++j){
				try{		
					if(objT[j].equals(c.getIndex(i)))
						break;
				}
				catch(WrongIndex e){
					System.out.printf("\nTry a valid index number.");
				}
			}
			if(j == used)
				return false;
		}
		return true;
	}
	
	
	public  boolean isEmpty(){
		if(used == 0)
			return true;
		return false;
	}
	
	public void remove(E e){	// finds that spesific character of 'E e' , removes it and shifts all elements
		int i;
		for(i = first;i < used;++i){
			if(objT[i].equals(e))
				break;
		}
		if(i == used)
			return;
		for(int j = i + 1;j < used;j++)
			objT[j - 1] = objT[j];
		--used;
		
	}
	
	public  boolean removeAll(collection c){	// calls remove for every same character
		int i,j;
		for(i = 0;i < c.size();++i){
			for(j = first;j < used;++j){
				try{		
					if(objT[j].equals(c.getIndex(i)))
						remove((E)c.getIndex(i));
				}
				catch(WrongIndex e){
					System.out.printf("\nTry a valid index number.");
				}
			}
		}
		return true;
	}
	
	public  boolean retainAll(collection c){		// if 'c's character doesnt match with any of this objects characters then it calls remove
		int i,j;
		for(i = first;i < used;++i){
			for(j = 0;j < c.size();++j){
				try{		
					if(objT[i].equals(c.getIndex(j)))
						break;
				}
				catch(WrongIndex e){
					System.out.printf("\nTry a valid index number.");
				}
			}
			if(j == c.size()){
				remove((E)objT[i]);
				--i;
			}
		}
		return true;
	}
	
	private void extend(){				// creates a temp and stores used ones then creates new +15 for our main object and get used ones from temp
		Object[] temp = new Object[capacity];
		for(int i = 0;i < used;++i)
			temp[i] = objT[i];
		
		capacity += 15;
		objT = new Object[capacity];
		for(int i = 0;i < used;++i)
			objT[i] = temp[i];
		for(int i = used;i < capacity;++i)
			objT[i] = null;
	}
	
	public E getIndex(int i) throws WrongIndex{
		if(i >= used || i < 0)
			throw new WrongIndex("Wrong Index\n");
		return (E)objT[i];
	}
	
	public void print(){
		System.out.printf("\n");
		if(used - first == 0)
			System.out.printf("Object is empty.\n");
		else
			for(int j = 0,i = first;i < used;++i,j++){
				System.out.printf("Element %d:" + objT[i],j+1);
				System.out.printf("\n");
			}
	}
	
}