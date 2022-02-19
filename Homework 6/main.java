import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Hashtable;
import java.util.LinkedList;

public class main{


	/**
	This main function contains menu for using program and a driver to sorts functions
	*/
	public static void main(String[] args) {

		readFile();
		Hashtable<String, user> users = user.readUsers();

		int choose = 1;
		while(choose != 0){
			System.out.printf("\n0-) Exit\n1-) Login\n2-) New Customer\n3-) New Trader\n4-) Driver Function\n");
			Scanner inp = new Scanner(System.in);
			choose = inp.nextInt();
			Scanner inpString = new Scanner(System.in);
			if(choose == 0)
				return;
			if(choose == 1){
					System.out.printf("Enter ID(-1 for end): ");
					String choose2 = inpString.nextLine();
					if(choose2.contains("-1"))
						break;
					else if(choose2.length() != 8){
						System.out.printf("Id length is not 8!\n");
						continue;
					}				
					System.out.printf("Enter Password: ");
					String choose3 = inpString.nextLine();
					if(choose3.length() != 6){
						System.out.printf("Password length is not 6!\n");
						continue;
					}
					user tempUser = users.get(choose2);
					if(tempUser != null && tempUser.getPassword().equals(choose3)){
						System.out.printf("   Welcome %s %s\n", tempUser.getType(), tempUser.getName());
						customer currentCust = null;
						trader currentTr = null;
						if(tempUser instanceof customer){
							while(true){
								currentCust = (customer)tempUser;
								System.out.printf("\n0-) Exit\n1-) Search by Name\n2-) Search by Trader\n");
								int choose4 = inp.nextInt();
								switch(choose4){
									case 0:
										break;
									case 1:
										System.out.printf("Enter name or description: ");
										String chooseSearch = inpString.nextLine();
										ArrayList<product> list = currentCust.search(chooseSearch);
										System.out.printf("\n1-) Sort by increasing name.\n2-) Sort by decreasing name.\n3-) Sort by increasing discounted price.\n4-) Sort by decreasing discounted price.\n5-) Sort by increasing discount percentage.\n6-) Sort by decreasing discount percentage.\n");
										int choose5 = inp.nextInt();
										System.out.printf("\n0-) No threshold.\n1-) Price threshold.\n");
										int upperbound = -1, lowerbound = -1;
										if(inp.nextInt() == 1){
											System.out.printf("Enter upper threshold: ");
											upperbound = inp.nextInt();
											System.out.printf("Enter lower threshold: ");
											lowerbound = inp.nextInt();
											currentCust.priceThreshold(upperbound, lowerbound, list);
										}
										switch(choose5){
											case 1:
												sorts.insertionSortIncreasing(list);
												printProducts(list);
											break;
											case 2:
												sorts.insertionSortDecreasing(list);
												printProducts(list);
											break;
											case 3:
												sorts.selectionSortIncreasing(list);
												printProducts(list);
											break;
											case 4:
												sorts.selectionSortDecreasing(list);
												printProducts(list);
											break;
											case 5:
												sorts.bubbleSortIncreasing(list);
												printProducts(list);
											break;
											case 6:
												sorts.bubbleSortDecreasing(list);
												printProducts(list);
											break;
										}
										currentCust.Categorize(list);
									break;
									case 2:
										System.out.printf("Enter trader: ");
										chooseSearch = inpString.nextLine();
										list = currentCust.searchByTrader(chooseSearch);
										System.out.printf("\n1-) Sort by increasing name.\n2-) Sort by decreasing name.\n3-) Sort by increasing discounted price.\n4-) Sort by decreasing discounted price.\n5-) Sort by increasing discount percentage.\n6-) Sort by decreasing discount percentage.\n");
										choose5 = inp.nextInt();
										System.out.printf("\n0-) No threshold.\n1-) Price threshold.\n");
										upperbound = -1;
										lowerbound = -1;
										if(inp.nextInt() == 1){
											System.out.printf("Enter upper threshold: ");
											upperbound = inp.nextInt();
											System.out.printf("Enter lower threshold: ");
											lowerbound = inp.nextInt();
											currentCust.priceThreshold(upperbound, lowerbound, list);
										}
										switch(choose5){
											case 1:
												sorts.insertionSortIncreasing(list);
												printProducts(list);
											break;
											case 2:
												sorts.insertionSortDecreasing(list);
												printProducts(list);
											break;
											case 3:
												sorts.selectionSortIncreasing(list);
												printProducts(list);
											break;
											case 4:
												sorts.selectionSortDecreasing(list);
												printProducts(list);
											break;
											case 5:
												sorts.bubbleSortIncreasing(list);
												printProducts(list);
											break;
											case 6:
												sorts.bubbleSortDecreasing(list);
												printProducts(list);
											break;
										}
										currentCust.Categorize(list);
									break;

								}
								if(choose4 == 0)
									break;
							}
						}
						else if(tempUser instanceof trader){
							while(true){
								currentTr = (trader)tempUser;
								System.out.printf("\n0-) Exit\n1-) See your orders.\n2-) Add Product.\n3-) Remove Product.\n4-) Edit Product.\n5-) Meet order.\n6-) Cancel order.\n");
								int choose7 = inp.nextInt();
								switch(choose7){
									case 0:
									break;
									case 1:
										currentTr.printOrders();
									break;
									case 2:
										System.out.printf("Enter ID(16 Digit): ");
										String itemId = inpString.nextLine();
										if(itemId.length() != 16){
											System.out.printf("Id must be 16 digit!\n");
											break;
										}
										itemId = itemId.toUpperCase();
										System.out.printf("Enter Item Name: ");
										String itemName = inpString.nextLine();
										System.out.printf("Enter Categories General to Spesific Respectively(-1 to end): ");
										LinkedList<String> itemLL = new LinkedList();
										String LLTemp = new String();
										while(true){
											LLTemp = inpString.nextLine();
											if(LLTemp.contains("-1"))
												break;
											itemLL.add(LLTemp);
										}
										System.out.printf("Enter Price: ");
										int itemPrice = inp.nextInt();
										System.out.printf("Enter Discounted Price: ");
										int itemDiscountedPrice = inp.nextInt();
										if(itemDiscountedPrice > itemPrice){
											System.out.printf("Discounted price cannot be smaller than price!\n");
											break;
										}

										System.out.printf("Enter Descriptions: ");
										String itemDescription = inpString.nextLine();
										currentTr.addProduct(new product(itemId, itemName, itemLL, itemPrice, itemDiscountedPrice, itemDescription, currentTr.getName()));

									break;
									case 3:
										System.out.printf("Enter ID(16 Digit): ");
										itemId = inpString.nextLine();
										if(itemId.length() != 16){
											System.out.printf("Id must be 16 digit!\n");
											break;
										}
										itemId = itemId.toUpperCase();
										currentTr.removeProduct(itemId);

									break;
									case 4:
										System.out.printf("Enter ID(16 Digit): ");
										itemId = inpString.nextLine();
										if(itemId.length() != 16){
											System.out.printf("Id must be 16 digit!\n");
											break;
										}
										itemId = itemId.toUpperCase();
										System.out.printf("Enter Item Name: ");
										itemName = inpString.nextLine();
										System.out.printf("Enter Categories General to Spesific Respectively(-1 to end): ");
										itemLL = new LinkedList();
										LLTemp = new String();
										while(true){
											LLTemp = inpString.nextLine();
											if(LLTemp.contains("-1"))
												break;
											itemLL.add(LLTemp);
										}
										System.out.printf("Enter Price: ");
										itemPrice = inp.nextInt();
										System.out.printf("Enter Discounted Price: ");
										itemDiscountedPrice = inp.nextInt();
										if(itemDiscountedPrice > itemPrice){
											System.out.printf("Discounted price cannot be smaller than price!\n");
											break;
										}

										System.out.printf("Enter Descriptions: ");
										itemDescription = inpString.nextLine();
										currentTr.editProduct(itemId, itemName, itemLL, itemPrice, itemDiscountedPrice, itemDescription);
									break;
									case 5:
									System.out.printf("Enter order id you want to meet: ");
									currentTr.meetOrCancelOrder(inp.nextInt(), true);
									break;
									case 6:
									System.out.printf("Enter order id you want to cancel: ");
									currentTr.meetOrCancelOrder(inp.nextInt(), false);
									break;
								}
								if(choose7 == 0)
									break;
							}
						}
					}
				}
			else if(choose == 2){
				System.out.printf("Enter Name(-1 for end): ");
				String choose1 = inpString.nextLine();
				if(choose1.contains("-1"))
					break;
				System.out.printf("Enter ID: ");
				String choose2 = inpString.nextLine();
				System.out.printf("Enter Password: ");
				String choose3 = inpString.nextLine();
				if(user.addUser(new customer(choose2, choose3, choose1)))
					users.put(choose2, new customer(choose2, choose3, choose1));
			}
			else if(choose == 3){
				System.out.printf("Enter Name(-1 for end): ");
				String choose1 = inpString.nextLine();
				if(choose1.contains("-1"))
					break;
				System.out.printf("Enter ID: ");
				String choose2 = inpString.nextLine();
				System.out.printf("Enter Password: ");
				String choose3 = inpString.nextLine();
				if(user.addUser(new trader(choose2, choose3, choose1)))
					users.put(choose2, new trader(choose2, choose3, choose1));
			}
			else if(choose == 4){
				System.out.printf("\n/////////////////////// DRIVER FUNCTION ///////////////////////\n\n");
				System.out.printf("Trader Dolphin created. ID: 12345689 | Password: 123456\n");
				trader Dolphin = new trader("12345689", "123456", "Dolphin");
				System.out.printf("Dolphin.addProduct(product) -> ID: DF10BY800TK9BC0 | Name: Potato | Category(LinkedList): Vegetables -> Nice Ones | Price: 5 | Discounted Price: 4 | Description: Best Potato in Town | Trader: Dolphin\n");
				LinkedList<String> forsortsLL = new LinkedList();
				forsortsLL.add("Vegetables");
				forsortsLL.add("Nice Ones");
				Dolphin.addProduct(new product("DF10BY800TK9BC0", "Potato", forsortsLL, 5, 4, "Best Potato in Town", "Dolphin"));
				System.out.printf("Dolphin.addProduct(product) -> ID: DF10BY800TK9NO0 | Name: Domato | Category(LinkedList): Vegetables -> Best Ones | Price: 8 | Discounted Price: 6 | Description: Best Domato in Universe | Trader: Dolphin\n");
				forsortsLL = new LinkedList();
				forsortsLL.add("Vegetables");
				forsortsLL.add("Best Ones");
				Dolphin.addProduct(new product("DF10BY800TK9NO0", "Domato", forsortsLL, 8, 6, "Best Domato in Universe", "Dolphin"));
				System.out.printf("\n");
				System.out.printf("Dolphin.editProduct(itemId, Name, Categories, Price = 5, DiscountedPrice = 3, Description) = %b\n\n",Dolphin.editProduct("DF10BY800TK9NO0", "Domato", forsortsLL, 5, 3, "Best Domato in Universe"));
				System.out.printf("Dolphin.removeProduct(itemId = DF10BY800TK9NO0) = %b\n\n\n",Dolphin.removeProduct("DF10BY800TK9NO0"));
				System.out.printf("Customer Yami created. ID: 12332145 | Password: 123456\n");
				customer Yami = new customer("12332145", "123456", "Yami");
				System.out.printf("    Yami.search(\"gentle\")\n    Yami.priceThreshold(upperbound = 900, lowerbound = 400, ArrayList)\n    SortByDiscountedPrice.selectionSortIncreasing(ArrayList):\n");
				ArrayList<product> forsortsarr = Yami.search("gentle");
				Yami.priceThreshold(900,400,forsortsarr);
				sorts.selectionSortIncreasing(forsortsarr);
				printProducts(forsortsarr);
				System.out.printf("\n\n    Yami.search(\"Pink Dress\")\n    SortByName.insertionSortDecreasing(ArrayList):\n");
				forsortsarr = Yami.search("Pink Dress");
				sorts.insertionSortDecreasing(forsortsarr);
				printProducts(forsortsarr);
				System.out.printf("\n\n    Yami.search(\"pair of heels\")\n    SortByDiscountPercentage.bubbleSortIncreasing(ArrayList):\n");
				forsortsarr = Yami.search("pair of heels");
				sorts.bubbleSortIncreasing(forsortsarr);
				printProducts(forsortsarr);
				System.out.printf("\n\n    Yami.searchByTrader(\"Dolphin\"):                                                         ///////  Type 1, 1 ,1 Respectively to use categories then purchase  /////////\n");
				forsortsarr = Yami.searchByTrader("Dolphin");
				printProducts(forsortsarr);
				Yami.Categorize(forsortsarr);
				System.out.printf("\n\n    Dolphin.printOrders(): \n");
				Dolphin.printOrders();
				System.out.printf("\nDolphin.meetOrCancelOrder(OrderId = 1, Meet = true):\n");
				Dolphin.meetOrCancelOrder(1, true);
				System.out.printf("\n    Dolphin.printOrders(): \n");
				Dolphin.printOrders();
				System.out.printf("\n///////////////////////////////////////////////////////////////\n");
			}
		}
    }

    /**
	This helper function used for transfering csv file to txt
    */

    static public void readFile(){
    	try {
    	FileReader myObj = new FileReader("e-commerce-samples.csv");
    	BufferedReader myReader = new BufferedReader(myObj);
    	FileWriter myFile = new FileWriter("file.txt");
    	int count = 0;
    	PriorityQueue<String> temp = new PriorityQueue(new user.traderComparator());
    	String firstLine = myReader.readLine();
    	while (myReader.ready()){
    		temp.add(myReader.readLine());
        }
        while(temp.peek() != null){
        	myFile.write(temp.poll());
        	myFile.write("\n");
        }
      	myReader.close();
      	myFile.close();
    	} catch (FileNotFoundException e) {
      		System.out.println("An error occurred.");
      		e.printStackTrace();
    	} catch (IOException e) {
	     	System.out.println("An error occurred.");
	      	e.printStackTrace();
	    }
	    System.out.printf("\nCSV file transfered to file.txt successfully!\n");
    }

    /**
	This helper function used for print current list of products respectively
    */

    static public void printProducts(ArrayList<product> list){
    	for(int i = 0;i < list.size();i++){
			System.out.printf("%d-) ",i + 1);
			list.get(i).print();
		}
    }


}