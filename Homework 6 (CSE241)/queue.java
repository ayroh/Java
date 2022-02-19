/**
 * interface queue
 * @author Bilal Yalcinkaya
 */

public interface queue<E> extends collection<E>{
	public boolean add(E e);
	public boolean offer(E e);
	public E element();
	public E poll();
}