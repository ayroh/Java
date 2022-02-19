import java.util.Comparator;

public class heapComparator implements Comparator<tryheap.Node>{

    /**
	 Overriding compare() method of Comparator  for descending order of Node.getData()
	 */
	@Override
    public int compare(tryheap.Node n1, tryheap.Node n2) {
    	return n2.getData().compareTo(n1.getData());
	 }

}