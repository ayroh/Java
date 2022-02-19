/**
 * interface collection
 * @author Bilal Yalcinkaya
 */

public interface collection <E>{
	
	public iterator iterator();
	public E getIndex(int i) throws WrongIndex;
	public boolean add(E e);
	public void clear();
	public boolean contains(E e);
	public boolean isEmpty();
	public void remove(E e);
	public int size();
	public boolean containsAll(collection c);
	public boolean removeAll(collection c);
	public boolean addAll(collection c);
	public boolean retainAll(collection c);
	
}