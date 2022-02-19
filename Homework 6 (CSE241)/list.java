/**
 * interface list
 * @author Bilal Yalcinkaya
 */

public interface list <E> extends collection<E>{
	
	public E getIndex(int i) throws WrongIndex;
	
}