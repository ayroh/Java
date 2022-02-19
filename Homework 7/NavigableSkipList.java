import java.util.concurrent.ConcurrentSkipListSet;
import java.util.Iterator;

public class NavigableSkipList<E> extends ConcurrentSkipListSet<E>{

	public boolean add(E item){
		return super.add(item);
	}

	public boolean remove(Object item){
		return super.remove(item);
	}

	public Iterator<E> descendingIterator(){
		return super.descendingIterator();
	}
}
