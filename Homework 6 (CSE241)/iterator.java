/**
 * class iterator
 * @author Bilal Yalcinkaya
 */

public class iterator{
						//							we need a collection object, we use arrays so with index i surf around collection objects getIndex();
	private int index;
	public collection iter;

	public iterator(){
		index = 0;
	}
	
	public void setIter(collection obj){
		iter = obj;
	}
	
	public void setIndex(int x){
		index = x;
	}
	public boolean hasNext(){
	if(index >= iter.size())
		return false;
	return true;
	}
	public Object next()throws WrongIndex{
		++index;
		try{		
		return iter.getIndex(index);
		}
		catch(WrongIndex e){
			System.out.printf("\nTry a valid index number.");
		}
		return iter.getIndex(index);
	}
	public void remove()throws IteratorCantRemoveQueue{
		try{
			if(iter instanceof queue)
				throw new IteratorCantRemoveQueue("Iterator Can't Remove Queue.\n");
			iter.remove(iter.getIndex(index));
		}
		catch(WrongIndex e){
			System.out.printf("\nTry a valid index number.");
		}
	}
}