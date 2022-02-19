import java.util.*;

public class SkipList<E extends Comparable<E>>{

	/** Static cJass to contain the data and the Jinks */
	static class SLNode<E>{
		SLNode<E>[] links;
		E data;

		SLNode (int m, E data){
			links = (SLNode<E> []) new SLNode[m]; // create Jinks
			this.data = data ; // store item 
		}
	}

	private int maxLevel = 1;
	private int maxCap;
	private SLNode<E> head = null;
	private int size = 0;

	public SkipList(){
		maxCap = computeMaxCap(maxLevel);
	}

	public void print(){
		SLNode<E> temp = head;			
		while(temp.links[0] != null){
			System.out.println(temp.links[0].data);
			temp = temp.links[0];
		}
	}

	/** Natural Log of 2 */
	static final double LOG2 = Math.log(2.0);
	/** Method to generate a logarithmic distributed integer between
	1 and maxLevel. i.e., 1/2 of the values returned are 1, 1/4
	are 2, 1/8 are 3, etc.
	@return a random logarithmic distributed int between 1 and
	maxLevel
	*/
	private int logRandom(){
		Random rand = new Random();
		int r = rand.nextInt(maxCap);
		int k = (int) (Math.log(r + 1) / LOG2);
		if (k > maxLevel - 1)
			k = maxLevel - 1;
		return maxLevel - k;
	}

	@SuppressWarnings ("unchecked")
	/** Search for an item in the list
	@par am i tem The item being sought
	@return A SLNode array which references the predecessors
	of the target at each JeveJ.
	*/
	private SLNode<E>[] search (E target) {
		SLNode<E> [] pred = (SLNode<E> []) new SLNode [maxLevel];
		SLNode<E> current = head ;
		for (int i = current.links.length - 1;i >= 0;i--) {
			while(current.links[i] != null && current.links[i].data.compareTo(target) < 0) {
				current = current.links[i];
			}
			pred[i] = current ;
		}
		return pred;
	}

	/** Find an object in the skip- Jist
	@param target The item being sought
	@return A reference to the object in the skip- Jist that matches
	the target . If not found, null is returned.
	*/
	public E find(E target) {
		SLNode<E> [] pred = search(target) ;
		if (pred[0].links[0] != null && pred[0].links[0].data.compareTo(target) == 0)
			return pred[0].links[0].data;
		else
			return null ;
	}

	private int computeMaxCap(int lvl){
		return (int)(Math.pow(2, lvl) - 1);
	}


	/**
	 * if head is null fills it, otherwise looks right place for item if item already exists returns false, otherwise finds proper place for all levels and adds item.
	 * @param item item to be added
	 * @return true if adds it otherwise false
	 * */
	public boolean insert(E item){
		int rand = logRandom();
		SLNode<E> tempNode = new SLNode(rand, item);
		if(head == null){
			head = new SLNode(maxLevel, item);
			head.links[maxLevel - 1] = tempNode;
			size++;
			return true;
		}

		E tempItem = find(item);
		if(tempItem != null && tempItem.equals(item) == true)
				return false;
		SLNode<E>[] pred = new SLNode[maxLevel]; 

		if (size > maxCap) {
			maxLevel++;
			maxCap = computeMaxCap(maxLevel); // maximum capacity
			head.links = Arrays.copyOf(head.links, maxLevel);
			pred = Arrays.copyOf(pred, maxLevel);
			pred[maxLevel - 1] = head;
		}

		pred = search(item);
		rand = logRandom();
		size++;
		for(int i = 0;i < tempNode.links.length;i++){
			tempNode.links[i] = pred[i].links[i];
			pred[i].links[i] = tempNode;
		}
		return true;	
	}
}