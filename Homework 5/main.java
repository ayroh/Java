/**
*  @author Bilal Yalcinkaya
*  testing function
*/

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.NoSuchElementException;
import java.lang.String;

public class main{

	public static void main(String[] args) {

				System.out.printf("////////////////////////////////////////////// PART 1 //////////////////////////////////////////////\n\n");	
		myHashMap<String,String> map = new myHashMap<String,String>();
		System.out.printf("6 times -> myHashMap.put(String, String)\n");
		map.put("a","4");
		map.put("a","4");
		map.put("a","4");
		map.put("b","8");
		map.put("c","15");
		map.put("d","16");
		map.put("e","23");
		map.put("f","42");
		System.out.printf("iter = myHashMap.MapIterator()\n");
		myHashMap.mapIterator iter = map.MapIterator();
		System.out.printf("while(iter.hasNext() -> iter.next():\n");
		while(iter.hasNext()){
			String xd = (String)iter.next();
			System.out.printf("%s -> %s\n",xd,map.get(xd));		
		}
		System.out.printf("\n");
		System.out.printf("previter = myHashMap.MapIterator(\"f\")\n");
		System.out.printf("while(true) -> previter.prev():\n");
		myHashMap.mapIterator previter = map.MapIterator("f");
		while(true){
			//try ekle
			try{
			String xd = (String)previter.prev();
			System.out.printf("%s -> %s\n",xd,map.get(xd));			
			}
			catch(NoSuchElementException e){
				System.out.printf("%s from MapIterator.prev()\n",e);
				break;
			}
		}


		
		System.out.printf("\n\n////////////////////////////////////////////// PART 2 //////////////////////////////////////////////");	
		double start, end, llputaverage1 = 0, llputaverage2 = 0, llputaverage3 = 0;
		double llremoveaverage1 = 0,llremoveaverage2 = 0,llremoveaverage3 = 0;
		double llgetaverage1 = 0,llgetaverage2 = 0,llgetaverage3 = 0;
		double llnongetaverage1 = 0,llnongetaverage2 = 0,llnongetaverage3 = 0;

		int[] arr = new int[5];
		int j = 0;
		Random	rnd = new Random();
		char c;
		int temp;

		System.out.printf("\n\n  ////////////////////////////  PART 2 -> Section 1:");
		System.out.printf("\n\n");
		System.out.printf(" hashLL = new LLHashtableChain<Key, Value>()\n");


		System.out.printf("\n\n");
		LLHashtableChain<Integer,Integer> hashLL = new LLHashtableChain<Integer,Integer>();
		System.out.printf(" isEmpty(): %b\n",hashLL.isEmpty());
		System.out.printf(" 20 times -> put(Key, Value)");

		arr = new int[10];
		j = 0;
		for(int i = 0;i < 20;i++){
		temp = rnd.nextInt(75);
		if(i%2 == 0){
		arr[j] = temp;
		j++;
		}
		start = System.nanoTime();
		hashLL.put(temp,temp);
		end = System.nanoTime();
		}
		System.out.printf("\n print():\n");
		hashLL.print();
		System.out.printf("\n 10 times remove(Key) and get(Key)\n");
		for(int k = 0;k < arr.length;k++){
			Integer gettemp = hashLL.get(arr[k]);
			System.out.printf("get(%d): %d",arr[k],gettemp);
			Integer inttemp = hashLL.remove(arr[k]);
			System.out.printf(" , remove(%d): %d removed",arr[k],inttemp);
			Integer nongettemp = hashLL.get(arr[k]);
			System.out.printf(",  get(%d): %d.\n",arr[k],nongettemp);
		}
		System.out.printf("\n print():\n");
		hashLL.print();
		System.out.printf("\n size(): %d\n",hashLL.size());
		System.out.printf(" isEmpty(): %b\n",hashLL.isEmpty());






		LLHashtableChain<Integer,Integer> LLhash = new LLHashtableChain<Integer,Integer>();

		arr = new int[10];
		j = 0;
		for(int i = 0;i < 10;i++){
		temp = rnd.nextInt(25);
		if(i%2 == 0){
			arr[j] = temp;
			j++;
		}
		start = System.nanoTime();
		LLhash.put(temp,temp);
		end = System.nanoTime();
		llputaverage1 += end - start;
		}
		for(int k = 0;k < arr.length;k++){
			start = System.nanoTime();
			Integer gettemp = LLhash.get(arr[k]);
			end = System.nanoTime();
			llgetaverage1 += end - start;
			start = System.nanoTime();
			Integer inttemp = LLhash.remove(arr[k]);
			end = System.nanoTime();
			llremoveaverage1 += end - start;
			start = System.nanoTime();
			Integer nongettemp = LLhash.get(arr[k]);
			end = System.nanoTime();
			llnongetaverage1 += end - start;
		}
		llputaverage1 /= 1000000000;
		llremoveaverage1 /= 1000000000;
		llgetaverage1 /= 1000000000;
		llnongetaverage1 /= 1000000000;
		//xtemp = llputaverage1 / (1000000000 * 10);
		


		LLhash = new LLHashtableChain<Integer,Integer>();
		 arr = new int[10];
		 j = 0;
		for(int i = 0;i < 100;i++){
		temp = rnd.nextInt(250);
		if(i%10 == 0){
			arr[j] = temp;
			j++;
		}
		start = System.nanoTime();
		LLhash.put(temp,temp);
		end = System.nanoTime();
		llputaverage2 += end - start;
		}
		for(int k = 0;k < arr.length;k++){
			start = System.nanoTime();
			Integer gettemp = LLhash.get(arr[k]);
			end = System.nanoTime();
			llgetaverage2 += end - start;
			start = System.nanoTime();
			Integer inttemp = LLhash.remove(arr[k]);
			end = System.nanoTime();
			llremoveaverage2 += end - start;
			start = System.nanoTime();
			Integer nongettemp = LLhash.get(arr[k]);
			end = System.nanoTime();
			llnongetaverage2 += end - start;
		}
		llputaverage2 /= 1000000000;
		llremoveaverage2 /= 1000000000;
		llgetaverage2 /= 1000000000;
		llnongetaverage2 /= 1000000000;



		LLhash = new LLHashtableChain<Integer,Integer>();
		 arr = new int[20];
		 j = 0;

		for(int i = 0;i < 1000;i++){
		temp = rnd.nextInt(2500);
		if(i%50 == 0){
			arr[j] = temp;
			j++;
		}
		start = System.nanoTime();
		LLhash.put(temp,temp);
		end = System.nanoTime();
		llputaverage3 += end - start;
		}
		for(int k = 0;k < arr.length;k++){
			start = System.nanoTime();
			Integer gettemp = LLhash.get(arr[k]);
			end = System.nanoTime();
			llgetaverage3 += end - start;
			start = System.nanoTime();
			Integer inttemp = LLhash.remove(arr[k]);
			end = System.nanoTime();
			llremoveaverage3 += end - start;
			start = System.nanoTime();
			Integer nongettemp = LLhash.get(arr[k]);
			end = System.nanoTime();
			llnongetaverage3 += end - start;
		}
		llputaverage3 /= 1000000000;
		llremoveaverage3 /= 1000000000;
		llgetaverage3 /= 1000000000;
		llnongetaverage3 /= 1000000000;


		System.out.println("\n	Linked List Hash Put Average 10 Elements:       10^-1 * " +llputaverage1);
		System.out.println("	Linked List Hash Remove Average 10 Elements:    10^-1 * " +llremoveaverage1);
		System.out.println("	Linked List Hash Get Average 10 Elements:       10^-1 * " +llgetaverage1);
		System.out.println("	Linked List Hash Nonget Average 10 Elements:    10^-1 * " +llnongetaverage1);
		System.out.println("\n	Linked List Hash Put Average 100 Elements:      10^-2 * " +llputaverage2);
		System.out.println("	Linked List Hash Remove Average 100 Elements:   10^-2 * " +llremoveaverage2);
		System.out.println("	Linked List Hash Get Average 100 Elements:      10^-2 * " +llgetaverage2);
		System.out.println("	Linked List Hash Nonget Average 100 Elements:   10^-2 * " +llnongetaverage2);
		System.out.println("\n	Linked List Hash Put Average 1000 Elements:     10^-3 * " +llputaverage3);
		System.out.println("	Linked List Hash Remove Average 1000 Elements:  10^-3 * " +llremoveaverage3);
		System.out.println("	Linked List Hash Get Average 1000 Elements:     10^-3 * " +llgetaverage3);
		System.out.println("	Linked List Hash Nonget Average 1000 Elements:  10^-3 * " +llnongetaverage3);




		double treeputaverage1 = 0, treeputaverage2 = 0, treeputaverage3 = 0;
		double treeremoveaverage1 = 0,treeremoveaverage2 = 0,treeremoveaverage3 = 0;
		double treegetaverage1 = 0,treegetaverage2 = 0,treegetaverage3 = 0;
		double treenongetaverage1 = 0,treenongetaverage2 = 0,treenongetaverage3 = 0;

		arr = new int[5];
		j = 0;

		System.out.printf("\n\n  ////////////////////////////  PART 2 -> Section 2:");
		System.out.printf("\n\n");
		System.out.printf(" hashtree = new treeHashtableChain<Key, Value>()\n");


		TreeHashtableChain<Integer,Integer> hashtree = new TreeHashtableChain<Integer,Integer>();
		System.out.printf(" isEmpty(): %b\n",hashtree.isEmpty());
		System.out.printf(" 20 times -> put(Key, Value)");

		arr = new int[10];
		j = 0;
		for(int i = 0;i < 20;i++){
		temp = rnd.nextInt(75);
		if(i%2 == 0){
		arr[j] = temp;
		j++;
		}
		start = System.nanoTime();
		hashtree.put(temp,temp);
		end = System.nanoTime();
		}
		System.out.printf("\n print():\n");
		hashtree.print();
		System.out.printf("\n 10 times remove(Key) and get(Key)\n");
		for(int k = 0;k < arr.length;k++){
			Integer gettemp = hashtree.get(arr[k]);
			System.out.printf("get(%d): %d",arr[k],gettemp);
			Integer inttemp = hashtree.remove(arr[k]);
			System.out.printf(" , remove(%d): %d removed",arr[k],inttemp);
			Integer nongettemp = hashtree.get(arr[k]);
			System.out.printf(",  get(%d): %d.\n",arr[k],nongettemp);
		}
		System.out.printf("\n print():\n");
		hashtree.print();
		System.out.printf("\n size(): %d\n",hashtree.size());
		System.out.printf(" isEmpty(): %b\n",hashtree.isEmpty());



		TreeHashtableChain<Integer,Integer> treehash = new TreeHashtableChain<Integer,Integer>();

		 arr = new int[5];
		 j = 0;
		for(int i = 0;i < 10;i++){
		temp = rnd.nextInt(25);
		if(i%2 == 0){
			arr[j] = temp;
			j++;
		}
		start = System.nanoTime();
		treehash.put(temp,temp);
		end = System.nanoTime();
		treeputaverage1 += end - start;
		}
		for(int k = 0;k < arr.length;k++){
			start = System.nanoTime();
			Integer gettemp = treehash.get(arr[k]);
			end = System.nanoTime();
			treegetaverage1 += end - start;
			start = System.nanoTime();
			Integer inttemp = treehash.remove(arr[k]);
			end = System.nanoTime();
			treeremoveaverage1 += end - start;
			start = System.nanoTime();
			Integer nongettemp = treehash.get(arr[k]);
			end = System.nanoTime();
			treenongetaverage1 += end - start;
		}
		treeputaverage1 /= 1000000000;
		treeremoveaverage1 /= 1000000000;
		treegetaverage1 /= 1000000000;
		treenongetaverage1 /= 1000000000;
		


		treehash = new TreeHashtableChain<Integer,Integer>();

		 arr = new int[10];
		 j = 0;
		for(int i = 0;i < 100;i++){
		temp = rnd.nextInt(250);
		if(i%10 == 0){
			arr[j] = temp;
			j++;
		}
		start = System.nanoTime();
		treehash.put(temp,temp);
		end = System.nanoTime();
		treeputaverage2 += end - start;
		}
		for(int k = 0;k < arr.length;k++){
			start = System.nanoTime();
			Integer gettemp = treehash.get(arr[k]);
			end = System.nanoTime();
			treegetaverage2 += end - start;
			start = System.nanoTime();
			Integer inttemp = treehash.remove(arr[k]);
			end = System.nanoTime();
			treeremoveaverage2 += end - start;
			start = System.nanoTime();
			Integer nongettemp = treehash.get(arr[k]);
			end = System.nanoTime();
			treenongetaverage2 += end - start;
		}
		treeputaverage2 /= 1000000000;
		treeremoveaverage2 /= 1000000000;
		treegetaverage2 /= 1000000000;
		treenongetaverage2 /= 1000000000;



		treehash = new TreeHashtableChain<Integer,Integer>();
		 arr = new int[20];
		 j = 0;

		for(int i = 0;i < 1000;i++){
		temp = rnd.nextInt(2500);
		if(i%50 == 0){
			arr[j] = temp;
			j++;
		}
		start = System.nanoTime();
		treehash.put(temp,temp);
		end = System.nanoTime();
		treeputaverage3 += end - start;
		}
		for(int k = 0;k < arr.length;k++){
			start = System.nanoTime();
			Integer gettemp = treehash.get(arr[k]);
			end = System.nanoTime();
			treegetaverage3 += end - start;
			start = System.nanoTime();
			Integer inttemp = treehash.remove(arr[k]);
			end = System.nanoTime();
			treeremoveaverage3 += end - start;
			start = System.nanoTime();
			Integer nongettemp = treehash.get(arr[k]);
			end = System.nanoTime();
			treenongetaverage3 += end - start;
		}
		treeputaverage3 /= 1000000000;
		treeremoveaverage3 /= 1000000000;
		treegetaverage3 /= 1000000000;
		treenongetaverage3 /= 1000000000;


		System.out.println("\n	Tree Hash Put Average 10 Elements:       10^-1 * " +treeputaverage1);
		System.out.println("	Tree Hash Remove Average 10 Elements:    10^-1 * " +treeremoveaverage1);
		System.out.println("	Tree Hash Get Average 10 Elements:       10^-1 * " +treegetaverage1);
		System.out.println("	Tree Hash Nonget Average 10 Elements:    10^-1 * " +treenongetaverage1);
		System.out.println("\n	Tree Hash Put Average 100 Elements:      10^-2 * " +treeputaverage2);
		System.out.println("	Tree Hash Remove Average 100 Elements:   10^-2 * " +treeremoveaverage2);
		System.out.println("	Tree Hash Get Average 100 Elements:      10^-2 * " +treegetaverage2);
		System.out.println("	Tree Hash Nonget Average 100 Elements:   10^-2 * " +treenongetaverage2);
		System.out.println("\n	Tree Hash Put Average 1000 Elements:     10^-3 * " +treeputaverage3);
		System.out.println("	Tree Hash Remove Average 1000 Elements:  10^-3 * " +treeremoveaverage3);
		System.out.println("	Tree Hash Get Average 1000 Elements:     10^-3 * " +treegetaverage3);
		System.out.println("	Tree Hash Nonget Average 1000 Elements:  10^-3 * " +treenongetaverage3);




		double coputaverage1 = 0, coputaverage2 = 0, coputaverage3 = 0;
		double coremoveaverage1 = 0,coremoveaverage2 = 0,coremoveaverage3 = 0;
		double cogetaverage1 = 0,cogetaverage2 = 0,cogetaverage3 = 0;
		double conongetaverage1 = 0,conongetaverage2 = 0,conongetaverage3 = 0;

		System.out.printf("\n\n  ////////////////////////////  PART 2 -> Section 3:");
		System.out.printf("\n\n");
		CoalescedHash<Integer,Integer> hashco = new CoalescedHash<Integer,Integer>();
		System.out.printf(" isEmpty(): %b\n",hashco.isEmpty());
		System.out.printf(" 20 times -> put(Key, Value)");

		arr = new int[10];
		j = 0;
		for(int i = 0;i < 20;i++){
		temp = rnd.nextInt(75);
		if(i%2 == 0){
		arr[j] = temp;
		j++;
		}
		start = System.nanoTime();
		hashco.put(temp,temp);
		end = System.nanoTime();
		}
		System.out.printf("\n print():\n");
		hashco.print();
		System.out.printf("\n 10 times remove(Key) and get(Key)\n");
		for(int k = 0;k < arr.length;k++){
			Integer gettemp = hashco.get(arr[k]);
			System.out.printf("get(%d): %d",arr[k],gettemp);
			Integer inttemp = hashco.remove(arr[k]);
			System.out.printf(" , remove(%d): %d removed",arr[k],inttemp);
			Integer nongettemp = hashco.get(arr[k]);
			System.out.printf(",  get(%d): %d.\n",arr[k],nongettemp);
		}
		System.out.printf("\n print():\n");
		hashco.print();
		System.out.printf("\n size(): %d\n",hashco.size());
		System.out.printf(" isEmpty(): %b\n",hashco.isEmpty());






		CoalescedHash<Integer,Integer> cohash = new CoalescedHash<Integer,Integer>();

		arr = new int[5];
		j = 0;
		for(int i = 0;i < 10;i++){
		temp = rnd.nextInt(25);
		if(i%2 == 0){
		arr[j] = temp;
		j++;
		}
		start = System.nanoTime();
		cohash.put(temp,temp);
		end = System.nanoTime();
		coputaverage1 += end - start;
		}
		for(int k = 0;k < arr.length;k++){
			start = System.nanoTime();
			Integer gettemp = cohash.get(arr[k]);
			end = System.nanoTime();
			cogetaverage1 += end - start;
			start = System.nanoTime();
			Integer inttemp = cohash.remove(arr[k]);
			end = System.nanoTime();
			coremoveaverage1 += end - start;
			start = System.nanoTime();
			Integer nongettemp = cohash.get(arr[k]);
			end = System.nanoTime();
			conongetaverage1 += end - start;
		}
		coputaverage1 /= 1000000000;
		coremoveaverage1 /= 1000000000;
		cogetaverage1 /= 1000000000;
		conongetaverage1 /= 1000000000;

		//System.out.printf("\n\n 100 times -> cohash.put(Key, Value)");
		cohash = new CoalescedHash<Integer,Integer>();

		arr = new int[100];
		for(int i = 0;i < 100;i++){
		temp = rnd.nextInt(250);
		arr[i] = temp;
		start = System.nanoTime();
		cohash.put(temp,temp);
		end = System.nanoTime();
		coputaverage2 += end - start;
		}
		//System.out.printf("\n cohash.print():");
		//cohash.print();
		//System.out.printf("\n 10 times remove(Key) and get(Key)\n");
		for(int k = 0;k < arr.length;k++){
			start = System.nanoTime();
			Integer gettemp = cohash.get(arr[k]);
			end = System.nanoTime();
			cogetaverage2 += end - start;
			//System.out.printf("get(%d): %d",arr[k],gettemp);
			start = System.nanoTime();
			Integer inttemp = cohash.remove(arr[k]);
			end = System.nanoTime();
			coremoveaverage2 += end - start;
			//System.out.printf(" , remove(%d): %d removed",arr[k],inttemp);
			start = System.nanoTime();
			Integer nongettemp = cohash.get(arr[k]);
			end = System.nanoTime();
			conongetaverage2 += end - start;
			//System.out.printf(",  get(%d): %d.\n",arr[k],nongettemp);
		}
		coputaverage2 /= 1000000000;
		coremoveaverage2 /= 1000000000;
		cogetaverage2 /= 1000000000;
		conongetaverage2 /= 1000000000;


		//System.out.printf("\n\n 1000 times -> cohash.put(Key, Value)");
		cohash = new CoalescedHash<Integer,Integer>();

		arr = new int[1000];
		for(int i = 0;i < 1000;i++){
		temp = rnd.nextInt(2500);
		arr[i] = temp;
		start = System.nanoTime();
		cohash.put(temp,temp);
		end = System.nanoTime();
		coputaverage3 += end - start;
		}
		//System.out.printf("\n cohash.print():");
		//cohash.print();
		//System.out.printf("\n 20 times remove(Key) and get(Key)\n");
		for(int k = 0;k < arr.length;k++){
			start = System.nanoTime();
			Integer gettemp = cohash.get(arr[k]);
			end = System.nanoTime();
			cogetaverage3 += end - start;
			//System.out.printf("get(%d): %d",arr[k],gettemp);
			start = System.nanoTime();
			Integer inttemp = cohash.remove(arr[k]);
			end = System.nanoTime();
			coremoveaverage3 += end - start;
			//System.out.printf(" , remove(%d): %d removed",arr[k],inttemp);
			start = System.nanoTime();
			Integer nongettemp = cohash.get(arr[k]);
			end = System.nanoTime();
			conongetaverage3 += end - start;
			//System.out.printf(",  get(%d): %d.\n",arr[k],nongettemp);
		}
		coputaverage3 /= 1000000000;
		coremoveaverage3 /= 1000000000;
		cogetaverage3 /= 1000000000;
		conongetaverage3 /= 1000000000;


		System.out.println("\n	Coalesced Hash Put Average 10 Elements:       10^-1 * " +coputaverage1);
		System.out.println("	Coalesced Hash Remove Average 10 Elements:    10^-1 * " +coremoveaverage1);
		System.out.println("	Coalesced Hash Get Average 10 Elements:       10^-1 * " +cogetaverage1);
		System.out.println("	Coalesced Hash Nonget Average 10 Elements:    10^-1 * " +conongetaverage1);
		System.out.println("\n	Coalesced Hash Put Average 100 Elements:      10^-2 * " +coputaverage2);
		System.out.println("	Coalesced Hash Remove Average 100 Elements:   10^-2 * " +coremoveaverage2);
		System.out.println("	Coalesced Hash Get Average 100 Elements:      10^-2 * " +cogetaverage2);
		System.out.println("	Coalesced Hash Nonget Average 100 Elements:   10^-2 * " +conongetaverage2);
		System.out.println("\n	Coalesced Hash Put Average 1000 Elements:     10^-3 * " +coputaverage3);
		System.out.println("	Coalesced Hash Remove Average 1000 Elements:  10^-3 * " +coremoveaverage3);
		System.out.println("	Coalesced Hash Get Average 1000 Elements:     10^-3 * " +cogetaverage3);
		System.out.println("	Coalesced Hash Nonget Average 1000 Elements:  10^-3 * " +conongetaverage3);

		
	}

}