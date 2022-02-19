import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class main{

	public static void main(String[] args) {


		System.out.printf("/////////////////////////////////////Driver Function/////////////////////////////////////\n\n");
		System.out.printf("\\\\\\\\\\\\\\\\\\\\\\\\\\PART 1\n\n");

		NavigableSkipList<Integer> navskiplist = new NavigableSkipList<Integer>();
		System.out.printf("                      NavigableSkipList\n\n");
		System.out.printf("NavigableSkipList.add(): 15,12,7,37,23,5,86,123,45,665\n");
		System.out.printf("\nNavigableSkipList.remove(): 5,123,86,15,4(there is no 4 currently)\n");
		navskiplist.add(15);
		navskiplist.add(12);
		navskiplist.add(7);
		navskiplist.add(37);
		navskiplist.add(23);
		navskiplist.add(5);
		navskiplist.add(86);
		navskiplist.add(123);
		navskiplist.add(45);
		navskiplist.add(665);
		navskiplist.remove(5);
		navskiplist.remove(123);
		navskiplist.remove(86);
		navskiplist.remove(4);
		navskiplist.remove(15);
		System.out.printf("\niter = NavigableSkipList.descendingIterator():\n");
		Iterator<Integer> iter = navskiplist.descendingIterator();
		while(iter.hasNext())
			System.out.printf("%d ",iter.next());

		AVLTree<Integer> navAVLTree = new AVLTree<Integer>();
		System.out.printf("\n\n                         AVLTree");
		System.out.printf("\n\nAVLTree.add(): 15,12,7,37,5,123,45,665\n");
		navAVLTree.insert(15);
		navAVLTree.insert(12);
		navAVLTree.insert(7);
		navAVLTree.insert(37);
		navAVLTree.insert(5);
		navAVLTree.insert(123);
		navAVLTree.insert(45);
		navAVLTree.insert(665);
		System.out.printf("\niter = AVLTree.Iterator():\n");
		Iterator<Integer> avliter = navAVLTree.iterator();
		while(avliter.hasNext())
			System.out.printf("%d ",avliter.next());

		System.out.printf("\n\nAVLTree.headSet(30): \n");
		avliter = navAVLTree.headSet(30).iterator();
		while(avliter.hasNext())
			System.out.printf("%d ",avliter.next());

		System.out.printf("\n\nAVLTree.tailSet(30): \n");
		avliter = navAVLTree.tailSet(30).iterator();
		while(avliter.hasNext())
			System.out.printf("%d ",avliter.next());


		System.out.printf("\n\n\n\\\\\\\\\\\\\\\\\\\\\\\\\\PART 2\n\n");
		System.out.printf("From part 1    --->    isAVLTree(AVLTree): %b\n",navAVLTree.isAVLTree());





		BinarySearchTree<Integer> xd = new BinarySearchTree();
		xd.add(12,false);
		xd.add(8,false);
		xd.add(5,true);
		xd.add(4,false);
		xd.add(14,false);
		System.out.printf("BinarySearchTree.add(12,8,5,4,14);     --->     isAVLTree(BinarySearchTree): %b\n",xd.isAVLTree(xd));


		xd = new BinarySearchTree();
		xd.add(11);
		xd.add(2,true);
		xd.add(14,true);
		xd.add(4,false);
		xd.add(15,false);
		System.out.printf("\nBinarySearchTree.add(black 11, red 2, red 14, black 4, black 15): isRedBlack: %b\n",xd.isRedBlackTree(xd));
		System.out.printf("\n                 11 Black\n");
		System.out.printf("         2 Red           14 Red\n");
		System.out.printf("             4 Black          15 Black\n\n");

		System.out.printf("\n\nWhen we change '15' to red");
		xd = new BinarySearchTree();
		xd.add(11);
		xd.add(2,true);
		xd.add(14,true);
		xd.add(4,false);
		xd.add(15,true);
		System.out.printf("\nBinarySearchTree.add(black 11, red 2, red 14, black 4, red 15): isRedBlack: %b\n",xd.isRedBlackTree(xd));
		System.out.printf("\n                 11 Black\n");
		System.out.printf("         2 Red           14 Red\n");
		System.out.printf("             4 Black          15 Red\n\n");

		System.out.printf("\n\nWhen we remove 15");
		xd = new BinarySearchTree();
		xd.add(11);
		xd.add(2,true);
		xd.add(14,true);
		xd.add(4,false);
		System.out.printf("\nBinarySearchTree.add(black 11, red 2, red 14, black 4): isRedBlack: %b\n",xd.isRedBlackTree(xd));
		System.out.printf("\n                 11 Black\n");
		System.out.printf("         2 Red           14 Red\n");
		System.out.printf("             4 Black          \n\n");

		System.out.printf("\n\nWhen make root red");
		xd = new BinarySearchTree();
		xd.add(11,true);
		xd.add(2,true);
		xd.add(14,true);
		xd.add(4,false);
		xd.add(15,false);
		System.out.printf("\nBinarySearchTree.add(red 11, red 2, red 14, black 4, black 15): isRedBlack: %b\n",xd.isRedBlackTree(xd));
		System.out.printf("\n                 11 Red\n");
		System.out.printf("         2 Red           14 Red\n");
		System.out.printf("             4 Black          15 Black\n\n");


		System.out.printf("\n\n\\\\\\\\\\\\\\\\\\\\\\\\\\PART 3\n\n");

		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();

		Random rand = new Random();
		int tempint;
		System.out.printf("Filling arraylist with random numbers. Please wait...\n");
		for(int i = 0;i < 1000000;i+=100000){
			ArrayList<Integer> temp = new ArrayList();
			for(int j = i;j < i + 80000;j++){
				tempint = rand.nextInt(i + 100000);
				if(temp.contains(tempint))
					j--;
				else
					temp.add(tempint);
			}
			list.add(temp);
		}
		System.out.printf("Completed!");
		double start, end;

		double timetemp = 0;
		System.out.printf("\n\n\n     BinarySearchTree(seconds): ");
		BinarySearchTree<Integer> bintest;

		timetemp = 0;
		for(int i = 0;i < 10;i++){
			bintest = new BinarySearchTree();
			for(int j = 0;j < 10000;j++){
				bintest.add(list.get(i).get(j));
			}
			for(int j = 0;j < 200;j++){
				tempint = rand.nextInt((i + 1) * 100000);
				if(bintest.findNode(tempint) != null)
					j--;
				else{
					start = System.nanoTime();
					bintest.add(tempint);
					end = System.nanoTime();
					timetemp += end - start;
				}
			}
		}
		timetemp /= 1000000000; // 1 kere için şu an
		timetemp /= 2000;
		System.out.printf("\n");
		System.out.printf("10000 elements: ");
		System.out.println(timetemp);


		timetemp = 0;
		for(int i = 0;i < 10;i++){
			bintest = new BinarySearchTree();
			for(int j = 0;j < 20000;j++){
				bintest.add(list.get(i).get(j));
			}
			for(int j = 0;j < 200;j++){
				tempint = rand.nextInt((i + 1) * 100000);
				if(bintest.findNode(tempint) != null)
					j--;
				else{
					start = System.nanoTime();
					bintest.add(tempint);
					end = System.nanoTime();
					timetemp += end - start;
				}
			}
		}
		timetemp /= 1000000000; // 1 kere için şu an
		timetemp /= 2000;
		System.out.printf("20000 elements: ");
		System.out.println(timetemp);


		timetemp = 0;
		for(int i = 0;i < 10;i++){
			bintest = new BinarySearchTree();
			for(int j = 0;j < 40000;j++){
				bintest.add(list.get(i).get(j));
			}
			for(int j = 0;j < 200;j++){
				tempint = rand.nextInt((i + 1) * 100000);
				if(bintest.findNode(tempint) != null)
					j--;
				else{
					start = System.nanoTime();
					bintest.add(tempint);
					end = System.nanoTime();
					timetemp += end - start;
				}
			}
		}
		timetemp /= 1000000000; // 1 kere için şu an
		timetemp /= 2000;
		System.out.printf("40000 elements: ");
		System.out.println(timetemp);


		timetemp = 0;
		for(int i = 0;i < 10;i++){
			bintest = new BinarySearchTree();
			for(int j = 0;j < 80000;j++){
				bintest.add(list.get(i).get(j));
			}
			for(int j = 0;j < 200;j++){
				tempint = rand.nextInt((i + 1) * 100000);
				if(bintest.findNode(tempint) != null)
					j--;
				else{
					start = System.nanoTime();
					bintest.add(tempint);
					end = System.nanoTime();
					timetemp += end - start;
				}
			}
		}
		timetemp /= 1000000000; // 1 kere için şu an
		timetemp /= 2000;
		System.out.printf("80000 elements: ");
		System.out.println(timetemp);



		System.out.printf("\n\n\n     RedBlackTree(seconds): ");
		RedBlackTree<Integer> redtest;

		timetemp = 0;
		for(int i = 0;i < 10;i++){
			redtest = new RedBlackTree();
			for(int j = 0;j < 10000;j++){
				redtest.add(list.get(i).get(j));
			}
			for(int j = 0;j < 200;j++){
				tempint = rand.nextInt((i + 1) * 100000);
				if(redtest.findNode(tempint) != null)
					j--;
				else{
					start = System.nanoTime();
					redtest.add(tempint);
					end = System.nanoTime();
					timetemp += end - start;
				}
			}
		}
		timetemp /= 1000000000; // 1 kere için şu an
		timetemp /= 2000;
		System.out.printf("\n");
		System.out.printf("10000 elements: ");
		System.out.println(timetemp);


		timetemp = 0;
		for(int i = 0;i < 10;i++){
			redtest = new RedBlackTree();
			for(int j = 0;j < 20000;j++){
				redtest.add(list.get(i).get(j));
			}
			for(int j = 0;j < 200;j++){
				tempint = rand.nextInt((i + 1) * 100000);
				if(redtest.findNode(tempint) != null)
					j--;
				else{
					start = System.nanoTime();
					redtest.add(tempint);
					end = System.nanoTime();
					timetemp += end - start;
				}
			}
		}
		timetemp /= 1000000000; // 1 kere için şu an
		timetemp /= 2000;
		System.out.printf("20000 elements: ");
		System.out.println(timetemp);


		timetemp = 0;
		for(int i = 0;i < 10;i++){
			redtest = new RedBlackTree();
			for(int j = 0;j < 40000;j++){
				redtest.add(list.get(i).get(j));
			}
			for(int j = 0;j < 200;j++){
				tempint = rand.nextInt((i + 1) * 100000);
				if(redtest.findNode(tempint) != null)
					j--;
				else{
					start = System.nanoTime();
					redtest.add(tempint);
					end = System.nanoTime();
					timetemp += end - start;
				}
			}
		}
		timetemp /= 1000000000; // 1 kere için şu an
		timetemp /= 2000;
		System.out.printf("40000 elements: ");
		System.out.println(timetemp);



		timetemp = 0;
		for(int i = 0;i < 10;i++){
			redtest = new RedBlackTree();
			for(int j = 0;j < 80000;j++){
				redtest.add(list.get(i).get(j));
			}
			for(int j = 0;j < 200;j++){
				tempint = rand.nextInt((i + 1) * 100000);
				if(redtest.findNode(tempint) != null)
					j--;
				else{
					start = System.nanoTime();
					redtest.add(tempint);
					end = System.nanoTime();
					timetemp += end - start;
				}
			}
		}
		timetemp /= 1000000000; // 1 kere için şu an
		timetemp /= 2000;
		System.out.printf("80000 elements: ");
		System.out.println(timetemp);


		System.out.printf("\n\n\n     2-3 Tree(seconds): ");
		BTree<Integer> test23;

		timetemp = 0;
		for(int i = 0;i < 10;i++){
			test23 = new BTree(3);
			for(int j = 0;j < 10000;j++){
				test23.add(list.get(i).get(j));
			}
			for(int j = 0;j < 200;j++){
				tempint = rand.nextInt((i + 1) * 100000);
				if(test23.findNode(tempint) != null)
					j--;
				else{
					start = System.nanoTime();
					test23.add(tempint);
					end = System.nanoTime();
					timetemp += end - start;
				}
			}
		}
		timetemp /= 1000000000; // 1 kere için şu an
		timetemp /= 2000;
		System.out.printf("\n");
		System.out.printf("10000 elements: ");
		System.out.println(timetemp);


		timetemp = 0;
		for(int i = 0;i < 10;i++){
			test23 = new BTree(3);
			for(int j = 0;j < 20000;j++){
				test23.add(list.get(i).get(j));
			}
			for(int j = 0;j < 200;j++){
				tempint = rand.nextInt((i + 1) * 100000);
				if(test23.findNode(tempint) != null)
					j--;
				else{
					start = System.nanoTime();
					test23.add(tempint);
					end = System.nanoTime();
					timetemp += end - start;
				}
			}
		}
		timetemp /= 1000000000; // 1 kere için şu an
		timetemp /= 2000;
		System.out.printf("20000 elements: ");
		System.out.println(timetemp);

		timetemp = 0;
		for(int i = 0;i < 10;i++){
			test23 = new BTree(3);
			for(int j = 0;j < 40000;j++){
				test23.add(list.get(i).get(j));
			}
			for(int j = 0;j < 200;j++){
				tempint = rand.nextInt((i + 1) * 100000);
				if(test23.findNode(tempint) != null)
					j--;
				else{
					start = System.nanoTime();
					test23.add(tempint);
					end = System.nanoTime();
					timetemp += end - start;
				}
			}
		}
		timetemp /= 1000000000; // 1 kere için şu an
		timetemp /= 2000;
		System.out.printf("40000 elements: ");
		System.out.println(timetemp);


		timetemp = 0;
		for(int i = 0;i < 10;i++){
			test23 = new BTree(3);
			for(int j = 0;j < 80000;j++){
				test23.add(list.get(i).get(j));
			}
			for(int j = 0;j < 200;j++){
				tempint = rand.nextInt((i + 1) * 100000);
				if(test23.findNode(tempint) != null)
					j--;
				else{
					start = System.nanoTime();
					test23.add(tempint);
					end = System.nanoTime();
					timetemp += end - start;
				}
			}
		}
		timetemp /= 1000000000; // 1 kere için şu an
		timetemp /= 2000;
		System.out.printf("80000 elements: ");
		System.out.println(timetemp);



		System.out.printf("\n\n\n     BTree(seconds): ");
		BTree<Integer> btest;

		timetemp = 0;
		for(int i = 0;i < 10;i++){
			btest = new BTree(6);
			for(int j = 0;j < 10000;j++){
				btest.add(list.get(i).get(j));
			}
			for(int j = 0;j < 200;j++){
				tempint = rand.nextInt((i + 1) * 100000);
				if(btest.findNode(tempint) != null)
					j--;
				else{
					start = System.nanoTime();
					btest.add(tempint);
					end = System.nanoTime();
					timetemp += end - start;
				}
			}
		}
		timetemp /= 1000000000; // 1 kere için şu an
		timetemp /= 2000;
		System.out.printf("\n");
		System.out.printf("10000 elements: ");
		System.out.println(timetemp);


		timetemp = 0;
		for(int i = 0;i < 10;i++){
			btest = new BTree(6);
			for(int j = 0;j < 20000;j++){
				btest.add(list.get(i).get(j));
			}
			for(int j = 0;j < 200;j++){
				tempint = rand.nextInt((i + 1) * 100000);
				if(btest.findNode(tempint) != null)
					j--;
				else{
					start = System.nanoTime();
					btest.add(tempint);
					end = System.nanoTime();
					timetemp += end - start;
				}
			}
		}
		timetemp /= 1000000000; // 1 kere için şu an
		timetemp /= 2000;
		System.out.printf("20000 elements: ");
		System.out.println(timetemp);





		timetemp = 0;
		for(int i = 0;i < 10;i++){
			btest = new BTree(6);
			for(int j = 0;j < 40000;j++){
				btest.add(list.get(i).get(j));
			}
			for(int j = 0;j < 200;j++){
				tempint = rand.nextInt((i + 1) * 100000);
				if(btest.findNode(tempint) != null)
					j--;
				else{
					start = System.nanoTime();
					btest.add(tempint);
					end = System.nanoTime();
					timetemp += end - start;
				}
			}
		}
		timetemp /= 1000000000; // 1 kere için şu an
		timetemp /= 2000;
		System.out.printf("40000 elements: ");
		System.out.println(timetemp);





		timetemp = 0;
		for(int i = 0;i < 10;i++){
			btest = new BTree(6);
			for(int j = 0;j < 80000;j++){
				btest.add(list.get(i).get(j));
			}
			for(int j = 0;j < 200;j++){
				tempint = rand.nextInt((i + 1) * 100000);
				if(btest.findNode(tempint) != null)
					j--;
				else{
					start = System.nanoTime();
					btest.add(tempint);
					end = System.nanoTime();
					timetemp += end - start;
				}
			}
		}
		timetemp /= 1000000000; // 1 kere için şu an
		timetemp /= 2000;
		System.out.printf("80000 elements: ");
		System.out.println(timetemp);




		System.out.printf("\n\n\n     SkipList(seconds): ");
		SkipList<Integer> SkipList;

		timetemp = 0;
		for(int i = 0;i < 10;i++){
			SkipList = new SkipList();
			for(int j = 0;j < 10000;j++){
				SkipList.insert(list.get(i).get(j));
			}
			for(int j = 0;j < 200;j++){
				tempint = rand.nextInt((i + 1) * 100000);
				if(SkipList.find(tempint) != null)
					j--;
				else{
					start = System.nanoTime();
					SkipList.insert(tempint);
					end = System.nanoTime();
					timetemp += end - start;
				}
			}
		}
		timetemp /= 1000000000; // 1 kere için şu an
		timetemp /= 2000;
		System.out.printf("\n");
		System.out.printf("10000 elements: ");
		System.out.println(timetemp);



		timetemp = 0;
		for(int i = 0;i < 10;i++){
			SkipList = new SkipList();
			for(int j = 0;j < 20000;j++){
				SkipList.insert(list.get(i).get(j));
			}
			for(int j = 0;j < 200;j++){
				tempint = rand.nextInt((i + 1) * 100000);
				if(SkipList.find(tempint) != null)
					j--;
				else{
					start = System.nanoTime();
					SkipList.insert(tempint);
					end = System.nanoTime();
					timetemp += end - start;
				}
			}
		}
		timetemp /= 1000000000; // 1 kere için şu an
		timetemp /= 2000;
		System.out.printf("20000 elements: ");
		System.out.println(timetemp);




		timetemp = 0;
		for(int i = 0;i < 10;i++){
			SkipList = new SkipList();
			for(int j = 0;j < 40000;j++){
				SkipList.insert(list.get(i).get(j));
			}
			for(int j = 0;j < 200;j++){
				tempint = rand.nextInt((i + 1) * 100000);
				if(SkipList.find(tempint) != null)
					j--;
				else{
					start = System.nanoTime();
					SkipList.insert(tempint);
					end = System.nanoTime();
					timetemp += end - start;
				}
			}
		}
		timetemp /= 1000000000; // 1 kere için şu an
		timetemp /= 2000;
		System.out.printf("40000 elements: ");
		System.out.println(timetemp);




		timetemp = 0;
		for(int i = 0;i < 10;i++){
			SkipList = new SkipList();
			for(int j = 0;j < 80000;j++){
				SkipList.insert(list.get(i).get(j));
			}
			for(int j = 0;j < 200;j++){
				tempint = rand.nextInt((i + 1) * 100000);
				if(SkipList.find(tempint) != null)
					j--;
				else{
					start = System.nanoTime();
					SkipList.insert(tempint);
					end = System.nanoTime();
					timetemp += end - start;
				}
			}
		}
		timetemp /= 1000000000; // 1 kere için şu an
		timetemp /= 2000;
		System.out.printf("80000 elements: ");
		System.out.println(timetemp);

	}

}