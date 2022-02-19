import java.util.*;

/**
*  @author Bilal Yalcinkaya
*  testing function
*/

public class main{


	/**
	Helper function to find occurence in array
	@return int number of occurences of num
	*/
	public static int occur(int[] arr, int num){
		int times = 0;
		for(int i = 0;i < arr.length;i++){
			if(arr[i] == num)
				times++;
		}
		return times;
	}


	public static void main(String[] args){

				////////////////////////////////////////////////////			PART1
		System.out.printf("///////////////////PART 1///////////////////\n\n");
		System.out.printf("Creating heap<Integer> maxheap\n");
		heap<Integer> maxHeap = new heap<Integer>();
		System.out.printf("maxHeap.add(5)\n");
		maxHeap.add(5);
		System.out.printf("maxHeap.add(1)\n");
		maxHeap.add(1);
		System.out.printf("maxHeap.add(7)\n");
		maxHeap.add(7);
		System.out.printf("\nmaxHeap.contains(7): %b\n",maxHeap.contains(7));
		System.out.printf("maxHeap.contains(12): %b\n",maxHeap.contains(12));
		System.out.printf("\nPrinting heap: ");
		maxHeap.print();
		System.out.printf("maxHeap.removeIndex(2);\n");
		maxHeap.removeIndex(2);
		System.out.printf("Printing heap: ");
		maxHeap.print();
		System.out.printf("\nCreating heap<Integer> otherheap\n");
		heap<Integer> otherheap = new heap<Integer>();
		System.out.printf("otherheap.add(2)\n");
		otherheap.add(2);
		System.out.printf("otherheap.add(9)\n");
		otherheap.add(9);
		System.out.printf("maxHeap.merge(otherheap);\n");
		maxHeap.merge(otherheap);
		System.out.printf("\nPrinting heap with maxHeap.iterator(): ");
		int size = maxHeap.size();
		heap.HeapIter iter = maxHeap.iterator();
		while(iter.hasNext())
			System.out.printf("%d ",iter.next());
		iter = maxHeap.iterator();
		iter.next();
		iter.next();
		iter.set(12);
		System.out.printf("\n2 iter.next() then iter.set(12): ");
		maxHeap.print();


				///////////////////////////////////////////////////				PART2
		System.out.printf("\n\n///////////////////PART 2///////////////////\n\n");
		BSTHeapTree<Integer> tree = new BSTHeapTree<Integer>();
		Random rand = new Random();
		int[] arr = new int[3000];
		for(int i = 0;i < 3000;i++){
			int temp = rand.nextInt(5000) + 1;
			tree.add(temp);
			arr[i] = temp;
		}
		Arrays.sort(arr);

		System.out.printf("Adding 100 random numbers to tree:\n\n");
		for(int i = 0;i < 100;i++){
			int temp = rand.nextInt(5000) + 1;
			if(tree.find(temp) != 0){
				System.out.printf("Tree: (%d,%d), Array: (%d,%d)\n",temp,tree.find(temp),temp,occur(arr,temp));
			}
			else{
				i--;
				continue;
			}
		}
				System.out.printf("\n\n////////////////////////////////////////////\n\n");

		System.out.printf("\nFinding numbers that not in the tree:\n");
		int temp = 5001;
		for(int i = 0;i < 10;i++){
			System.out.printf("    tree.find(%d): (%d,%d)\n",temp + i,temp + i,tree.find(temp+i));
		}
		System.out.printf("\n\n////////////////////////////////////////////\n\n");
		tree.find_mode();
		System.out.printf("From Array         : (%d,%d)\n\n",tree.findModeTemp,occur(arr,tree.findModeTemp));
		System.out.printf("\n\n////////////////////////////////////////////\n\n");
		System.out.printf("Removing 100 numbers from the tree:\n\n");
		for(int i = 0;i < 100;i++){
			int temp2 = rand.nextInt(5000) + 1;
			if(tree.find(temp2) != 0){
				System.out.printf("%d removed.",temp2);
				System.out.printf("   Tree: (%d,%d), Array: (%d,%d)\n",temp2,tree.remove(temp2),temp2,occur(arr,temp2));
			}
			else{
				i--;
				continue;
			}
		}
		System.out.printf("\n\n////////////////////////////////////////////\n\n");
		temp = 5001;
		System.out.printf("\n\nTrying to remove numbers that not in the tree:\n");
		for(int i = 0;i < 10;i++){
			System.out.printf("tree.remove(%d): (%d,%d)\n",temp + i,temp + i,tree.remove(temp+i));	
		}

		System.out.printf("\n\n////////////////////////////////////////////\n\n");


    }
}