/**
 * test for homework 6
 * @author Bilal Yalcinkaya
 */

import java.lang.Integer;
import java.util.Scanner;

public class hw6{
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int x = 1;
		while(x > 0){
		System.out.printf("\n\n------------------------------------\nEnter between 1-3, 0 for end.\n1 -> arraylist<String> test\n2 -> hashset<Integer> test\n3 -> linkedlist<String> test\n");
		x = sc.nextInt();
		switch(x){
			case 1:
				System.out.printf("\n\narraylist<String> Avatars created.\n");
				arraylist<String> Avatars = new arraylist<String>();
				System.out.printf("Avatars.add(\"Korra\")\n");
				Avatars.add("Korra");
				System.out.printf("Avatars.add(\"Aang\")\n");
				Avatars.add("Aang");
				System.out.printf("Avatars.add(\"Roku\")\n");
				Avatars.add("Roku");
				Avatars.print();
				System.out.printf("\nAvatars.size(): %d\n",Avatars.size());
				System.out.printf("\nAvatars.getIndex(used): ");
				try{
					 Avatars.getIndex(Avatars.size());
				}
				catch(WrongIndex e){
					System.out.printf("Try a valid index number.\n");
				}
				System.out.printf("\niterator arrit = Numbers.iterator()\nwhile(arrit.hasNext()) arrit.next():");
				iterator arrit = Avatars.iterator(); 
				try{
				while(arrit.hasNext())
					System.out.printf("\n"+ arrit.next());
				}
				catch(WrongIndex e){
					System.out.printf(" <- End of object array exception.\n");
				}
				arraylist<String> oldAvatars = new arraylist<String>();
				oldAvatars.add("Wan");
				oldAvatars.add("Kyoshi");
				System.out.printf("\n\nAvatars.addAll(oldAvatars)\n");
				Avatars.addAll(oldAvatars);
				Avatars.print();
				System.out.printf("\nAvatars.containsAll(oldAvatars): "+Avatars.containsAll(oldAvatars));
				System.out.printf("\nAvatars.contains(\"Kuruk\"): " + Avatars.contains("Kuruk"));
				System.out.printf("\nAvatars.remove(\"Korra\")\n");
				Avatars.remove("Korra");
				Avatars.print();
				System.out.printf("\nAvatars.retainAll(oldAvatars):\n");
				Avatars.retainAll(oldAvatars);
				Avatars.print();
				System.out.printf("\nAvatars.removeAll(oldAvatars):");
				Avatars.removeAll(oldAvatars);
				Avatars.print();
				Avatars.clear();
				System.out.printf("\nAvatars.clear() and Avatars.isEmpty(): "+Avatars.isEmpty());
				break;
			case 2:
				System.out.printf("\n\nhashset<Integer> Numbers created.\n");
				hashset<Integer> Numbers = new hashset<Integer>();
				System.out.printf("Numbers.add(5)\n");
				Numbers.add(5);
				System.out.printf("Numbers.add(12)\n");
				Numbers.add(12);
				System.out.printf("Numbers.add(12)\n");
				Numbers.add(12);
				System.out.printf("Numbers.add(3)\n");
				Numbers.add(3);
				Numbers.print();
				System.out.printf("\nNumbers.size(): %d\n",Numbers.size());
				System.out.printf("\niterator setit = Numbers.iterator()\nwhile(setit.hasNext()) setit.next():");
				iterator setit = Numbers.iterator(); 
				try{
				while(setit.hasNext())
					System.out.printf("\n"+ setit.next());
				}
				catch(WrongIndex e){
					System.out.printf(" <- End of object array exception.\n");
				}
				hashset<Integer> newNumbers = new hashset<Integer>();
				newNumbers.add(15);
				newNumbers.add(7);
				System.out.printf("\n\nNumbers.addAll(newNumbers)\n");
				Numbers.addAll(newNumbers);
				Numbers.print();
				System.out.printf("\nNumbers.containsAll(newNumbers): "+Numbers.containsAll(newNumbers));
				System.out.printf("\nNumbers.contains(13): " + Numbers.contains(13));
				System.out.printf("\nNumbers.remove(5)\n");
				Numbers.remove(5);
				Numbers.print();
				System.out.printf("\nNumbers.retainAll(newNumbers):\n");
				Numbers.retainAll(newNumbers);
				Numbers.print();
				System.out.printf("\nNumbers.removeAll(newNumbers):");
				Numbers.removeAll(newNumbers);
				Numbers.print();
				Numbers.clear();
				System.out.printf("\nNumbers.clear() and Numbers.isEmpty(): "+Numbers.isEmpty());
				break;
			case 3:
				System.out.printf("\n\nlinkedlist<String> NesetSongs created.\n");
				linkedlist<String> NesetSongs = new linkedlist<String>();
				System.out.printf("NesetSongs.add(\"Yolcu\")\n");
				NesetSongs.add("Yolcu");
				System.out.printf("NesetSongs.add(\"KirsehirinGulleri\")\n");
				NesetSongs.add("KirsehirinGulleri");
				System.out.printf("NesetSongs.add(\"AyasYollari\")\n");
				NesetSongs.add("AyasYollari");
				NesetSongs.print();
				System.out.printf("\nNesetSongs.size(): %d\n",NesetSongs.size());
				System.out.printf("\niterator linkit = NesetSongs.iterator()\nwhile(linkit.hasNext()) linkit.next():");
				iterator linkit = NesetSongs.iterator(); 
				try{
				while(linkit.hasNext())
					System.out.printf("\n"+ linkit.next());
				}
				catch(WrongIndex e){
					System.out.printf(" <- End of object array exception.\n");
				}
				try{
				linkit.remove();
				}
				catch(IteratorCantRemoveQueue e){
					System.out.printf("\nIterator Can't Remove Queue.");
				}
				System.out.printf(" <- Tried to iterator.remove() but since its queue it throwed an exception.\n");
				linkedlist<String> newNesetSongs = new linkedlist<String>();
				newNesetSongs.add("ArifeTarif");
				newNesetSongs.add("AsliBozukDeme");
				System.out.printf("\n\nNesetSongs.addAll(newNesetSongs)\n");
				NesetSongs.addAll(newNesetSongs);
				NesetSongs.print();
				System.out.printf("\nNesetSongs.containsAll(newNesetSongs): "+NesetSongs.containsAll(newNesetSongs));
				System.out.printf("\nNesetSongs.contains(\"BaharGelmis\"): " + NesetSongs.contains("BaharGelmis"));
				System.out.printf("\nNesetSongs.poll(): "+NesetSongs.poll());
				System.out.printf("\nNesetSongs.element(): "+NesetSongs.element());
				System.out.printf("\nNesetSongs.retainAll(newNesetSongs):\n");
				NesetSongs.retainAll(newNesetSongs);
				NesetSongs.print();
				System.out.printf("\nNesetSongs.removeAll(newNesetSongs):");
				NesetSongs.removeAll(newNesetSongs);
				NesetSongs.print();
				NesetSongs.clear();
				System.out.printf("\nNesetSongs.clear() and NesetSongs.isEmpty(): "+NesetSongs.isEmpty());
				break;
			default:
				System.out.printf("Enter between 1-3.\n");
				break;
		}
		}
		
	}
	
}