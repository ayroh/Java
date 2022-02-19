import java.lang.IndexOutOfBoundsException;
import java.lang.NullPointerException;
import java.util.Scanner;

/**
 *
 *Main of the homework 1
 *@author Bilal Yalçınkaya
 *@version JDK 8 - Cygwin 64 Terminal
 *
 */

public class main{
	public static void main(String[] args){

		Scanner inp = new Scanner(System.in);
		customer[] Customer = new customer[5];
		String name = "0", surname, password, email, phone, address;
		String product;
		int modelNo;
		int customerNumber;
		int branchNo;
		int numberOfCustomers = 0;
		branchemployee[] BranchEmployee = new branchemployee[5];
		int numberOfEmployee = 0;
		int activeUser = 0;
		int activeUserType = 0;
		int userType = 0;
		int EntryType = 0;
		administrator Admin = null;

		

		while(EntryType != 4){
			EntryType = 0;
			while(EntryType < 1 || EntryType > 4){
				System.out.printf("\n1:  Log in.\n");
				System.out.printf("2:  New Customer.\n");
				System.out.printf("3:  Test Function.\n");
				System.out.printf("4:  Exit.\n");
				EntryType = inp.nextInt();
			}
			if(EntryType == 1){
				if(Admin == null){
					System.out.printf("Create an Administrator.\n");		//			i create branches with administrator
					System.out.printf("Enter your name:  ");
					name = inp.next();
					System.out.printf("Enter your surname:  ");
					surname = inp.next();
					System.out.printf("Enter a password:  ");
					password = inp.next();
					System.out.printf("\n");
					Admin = new administrator(name, surname, password);
				}

				while(true){
					System.out.println("\nEnter your Name (0 for end): ");
					 name = inp.next();
					if(name.equals("0"))
						break;
					System.out.println("Enter your Surname");
					 surname = inp.next();
					System.out.println("Enter your password: ");
					 password = inp.next();

					for(activeUser = 0; activeUser < numberOfCustomers; ++activeUser){			                                                                   // searching through users
						if(name.equals(Customer[activeUser].getName()) && surname.equals(Customer[activeUser].getSurname()) && password.equals(Customer[activeUser].getPassword()))
							break;
					}
					if(activeUser != numberOfCustomers){
						activeUserType = 3;
						break;
					}

					for(activeUser = 0; activeUser < numberOfEmployee; ++activeUser){
						if(name.equals(BranchEmployee[activeUser].getName()) && surname.equals(BranchEmployee[activeUser].getSurname()) && password.equals(BranchEmployee[activeUser].getPassword()))
							break;	
					}
					if(activeUser != numberOfEmployee){
						activeUserType = 2;
						break;
					}

					if(name.equals(Admin.getName()) && surname.equals(Admin.getSurname()) && password.equals(Admin.getPassword())){
						activeUserType = 1;
						break;
					}
				}	
			}
			else if(EntryType == 2){						                                 	// creating a new customer
				if(numberOfCustomers + 1 == Customer.length){	      // extending array 
					customer[] tempCustomer = new customer[numberOfCustomers];
					for(int i = 0;i < numberOfCustomers;++i)
						tempCustomer[i] = Customer[i];
					Customer = new customer[Customer.length * 2];
					for(int i = 0;i < tempCustomer.length;++i)
						Customer[i] = tempCustomer[i];
				}
				System.out.println("\nEnter your name: ");
				 name = inp.next();
				System.out.println("Enter your surname: ");
				 surname = inp.next();
				System.out.println("Enter your e-mail: ");
				 email = inp.next();
				System.out.println("Enter your password: ");
				 password = inp.next();
				Customer[numberOfCustomers] = new customer(name, surname, email, password, numberOfCustomers + 1);
				activeUser = numberOfCustomers;
				numberOfCustomers++;
				activeUserType = 3;
			}
			else if(EntryType == 3){
				System.out.printf("			-----Welcome to the Test Function-----\n");
				System.out.printf("        Logged as Admin:  Name: Yunus, Surname: Ozyavuz, Password: 42\n");
				administrator testAdmin = new administrator("Yunus", "Ozyavuz", "42");

				System.out.printf("Admin.addBranch():  ");
				testAdmin.addBranch();
				System.out.printf("Admin.addBranchEmployee(): Name: Ufuk, Surname: Yikilmaz, Password: 34, Branch No: 1\n");
				branchemployee testEmployee1 = new branchemployee("Ufuk", "Yikilmaz", "34", 1);
				testEmployee1.test = true;
				testAdmin.addBranchEmployee(testEmployee1);

				System.out.printf("\n        Logged as Employee: Ufuk Yikilmaz 34\n");
				System.out.printf("Employee.addFurniture(): 1: chair, Model No: 1, Price: 10, How many: 1\n");
				testEmployee1.addChair(1,1,10.0);
				System.out.printf("Employee.addFurniture(): 1: desk, Model No: 2, Price: 5, How many: 1\n");
				testEmployee1.addDesk(1,2,5.0);
				System.out.printf("Employee.addFurniture(): 1: cup, Model No: 5, Price: 8, How many: 1\n");
				testEmployee1.addCup(1,5,8.0);
				System.out.printf("Employee.addFurniture(): 1: bookcase, Model No: 4, Price: 4, How many: 1\n");
				testEmployee1.addBookcase(1,4,4.0);
				System.out.printf("Employee.makeSale(50);\n");
				testEmployee1.makeSale(50);
				System.out.printf("Employee.queryProducts(): \n");
				testEmployee1.queryProducts();

				System.out.printf("\n        Logged as Customer: Name: Burkay, Surname: Yalniz, Password: 32, Email: agackakan@hotmail.com\n");
				customer testCustomer = new customer("Burkay", "Yalniz", "agackakan@hotmail.com", "32", 1);
				System.out.printf("\nCustomer.listOfProducts():");
				testCustomer.listOfProducts();
				System.out.printf("\n\nCustomer.SearchProduct(chair, ModelNo = 1): ");
				testCustomer.SearchProduct("chair", 1);
				System.out.printf("\nEmployee.addOrder(testCustomer, branchNo = 1, modelNo = 1, chair, isOnline = false): \n");
				testEmployee1.addOrder(testCustomer, 1, 1, "chair", false);
				System.out.printf("Employee.addOrder(testCustomer, branchNo = 1, modelNo = 2, desk, isOnline = true): \n");
				System.out.printf("Phone = 26241234, Address = Ornek Mahallesi\n");
				testCustomer.setPhone("26241234");
				testCustomer.setAddress("Ornek Mahallesi");
				testEmployee1.addOrder(testCustomer, 1, 2, "desk", true);
				System.out.printf("\nEmployee.printOrders(): ");
				testCustomer.printOrders();

				System.out.printf("\n        Logged as Employee: Ufuk Yikilmaz 34\n");
				System.out.printf("Employee.removeCup(ModelNo = 5): ");
				testEmployee1.removeCup(5);
				System.out.printf("Employee.seeOrders(allCustomers, numberOfCustomers = 1, CustomerNumber = 1): ");
				customer[] custarr = new customer[1];
				custarr[0] = testCustomer; 
				testEmployee1.seeOrders(custarr, 1, 1);

				System.out.printf("\n\n\n        Logged as Admin:  Name: Yunus, Surname: Ozyavuz, Password: 42\n");
				System.out.printf("Admin.seeBranchNeeds(): \n");
				testAdmin.seeBranchNeeds();
				System.out.printf("\nAdmin.removeBranchEmployee(BranchNo = 1): \n");
				testAdmin.removeBranchEmployee(1);
				System.out.printf("Admin.removeBranch(BranchNo = 1): \n");
				testAdmin.removeBranch(1);

				continue;
			}
			else if(EntryType == 4)
				break;
			if(name.equals("0"))	// exit previous menu
				continue;
			if(activeUserType == 1){												// user: Admin
				int AdminChoice = 0;
				while(AdminChoice != 6){
					Admin.getMessage();
					Admin.setMessage(false);
					System.out.printf("\nWelcome again %s %s\n", Admin.getName(), Admin.getSurname());
					System.out.printf("1:  Add Branch.\n");
					System.out.printf("2:  Add Branch Employee.\n");
					System.out.printf("3:  Remove Branch.\n");
					System.out.printf("4:  Remove Branch Employee.\n");
					System.out.printf("5:  Query Needed Products.\n");
					System.out.printf("6:  Exit.\n");
					AdminChoice = inp.nextInt();
					switch(AdminChoice){
						case 1:
							Admin.addBranch();
							break;
						case 2:
							if(BranchEmployee.length == numberOfEmployee + 1){										// extending array
								branchemployee[] tempEmployee = new branchemployee[BranchEmployee.length];
								for(int i = 0;i < BranchEmployee.length;++i)
									tempEmployee[i] = BranchEmployee[i];
								BranchEmployee = new branchemployee[BranchEmployee.length * 2];
								for(int i = 0;i < tempEmployee.length;++i)
									BranchEmployee[i] = tempEmployee[i];
							}
							System.out.println("\nEnter your name: ");
							 name = inp.next();
							System.out.println("Enter your surname: ");
							 surname = inp.next();
							System.out.println("Enter your password: ");
							 password = inp.next();
							System.out.println("Enter branchNo: ");
							 branchNo = inp.nextInt();
							try{
							BranchEmployee[numberOfEmployee] = new branchemployee(name, surname, password, branchNo);
							Admin.addBranchEmployee(BranchEmployee[numberOfEmployee]);
							numberOfEmployee++;
							}catch(NullPointerException e){
								System.out.printf("			Wrong Index!\n");
							}catch(ArrayIndexOutOfBoundsException f){
								System.out.printf("			Wrong Index!\n");
							}
							break;
						case 3:
							System.out.println("\nEnter Branch No: ");
							try{
							Admin.removeBranch(inp.nextInt());
							}catch(NullPointerException e){
								System.out.printf("			Wrong Index!\n");
							}catch(ArrayIndexOutOfBoundsException f){
								System.out.printf("			Wrong Index!\n");
							}
							break;
						case 4:
							System.out.println("\nEnter Branch No: ");
							try{
							Admin.removeBranchEmployee(inp.nextInt());
							}catch(NullPointerException e){
								System.out.printf("			Wrong Index!\n");
							}catch(ArrayIndexOutOfBoundsException f){
								System.out.printf("			Wrong Index!\n");
							}
							break;
						case 5:
							Admin.seeBranchNeeds();
							break;
						case 6:
							break;


					}
				}
			}
			else if(activeUserType == 2){													// user: branch employee
				int EmployeeChoice = 0;
				while(EmployeeChoice != 6){
					int FurnitureChoice;
					int ModelNo, Price;
					int times;
					System.out.printf("\nWelcome again %s %s\n",BranchEmployee[activeUser].getName(),BranchEmployee[activeUser].getSurname());
					System.out.printf("1:  Add Furniture.\n");
					System.out.printf("2:  Remove Furniture.\n");
					System.out.printf("3:  Make Sale.\n");
					System.out.printf("4:  Find Orders of a Customer.\n");
					System.out.printf("5:  Query Products.\n");
					System.out.printf("6:  Exit.\n");
					EmployeeChoice = inp.nextInt();
					switch(EmployeeChoice){
						case 1:
							System.out.printf("\n1:  chair");
							System.out.printf("\n2:  desk\n");
							System.out.printf("3:  cup\n");
							System.out.printf("4:  bookcase\n");
							FurnitureChoice = inp.nextInt();
							if(FurnitureChoice < 1 || FurnitureChoice > 4){
								System.out.printf("Wrong Choice!\n");
								break;
							}
							System.out.printf("\nEnter Model No: \n");
							ModelNo = inp.nextInt();
							System.out.printf("\nEnter Price: \n");
							Price = inp.nextInt();
							if(Price <= 0){
								System.out.printf("Price Can't Be Negative or Zero!\n");
								break;
							}
							System.out.printf("\nEnter how many you want to add: \n");
							times = inp.nextInt();
							if(times <= 0){
								System.out.printf("Wrong Enter!\n");
								break;
							}
							switch(FurnitureChoice){
								case 1:
									BranchEmployee[activeUser].addChair(times, ModelNo, Price);
									break;
								case 2:
									BranchEmployee[activeUser].addDesk(times, ModelNo, Price);
									break;
								case 3:
									BranchEmployee[activeUser].addCup(times, ModelNo, Price);
									break;
								case 4:
									BranchEmployee[activeUser].addBookcase(times, ModelNo, Price);
									break;
							}
							break;
						case 2:
							System.out.printf("\n1:  chair");
							System.out.printf("\n2:  desk\n");
							System.out.printf("3:  cup\n");
							System.out.printf("4:  bookcase\n");
							FurnitureChoice = inp.nextInt();
							System.out.printf("\nEnter Model No: ");
							ModelNo = inp.nextInt();
							switch(FurnitureChoice){
								case 1:
									BranchEmployee[activeUser].removeChair(ModelNo);
									break;
								case 2:
									BranchEmployee[activeUser].removeDesk(ModelNo);
									break;
								case 3:
									BranchEmployee[activeUser].removeCup(ModelNo);
									break;
								case 4:
									BranchEmployee[activeUser].removeBookcase(ModelNo);
									break;
							}

							break;
						case 3:
							System.out.println("\nEnter Sale Percentage(%): ");
							BranchEmployee[activeUser].makeSale(inp.nextInt());
							break;
						case 4:
							System.out.printf("Enter Customer Number: ");
							customerNumber = inp.nextInt();
							BranchEmployee[activeUser].seeOrders(Customer, numberOfCustomers, customerNumber);
							break;
						case 5:
							BranchEmployee[activeUser].queryProducts();
							break;
						case 6:
							break;
					}
				}				
			}
			else if(activeUserType == 3){							// user: Customer
				int CustomerChoice = 0;
				while(CustomerChoice != 5){
					System.out.printf("\nWelcome again %s %s\n",Customer[activeUser].getName(),Customer[activeUser].getSurname());
					System.out.printf("1:  Add List of Products.\n");
					System.out.printf("2:  Search Product.\n");
					System.out.printf("3:  Shop.\n");
					System.out.printf("4:  Print Orders.\n");
					System.out.printf("5:  Exit.\n");
					CustomerChoice = inp.nextInt();
					switch(CustomerChoice){
						case 1:
							Customer[activeUser].listOfProducts();
							break;
						case 2:
							System.out.printf("\nEnter Product Name:");
							product = inp.next();
							System.out.printf("Enter Model No:");
							modelNo = inp.nextInt();
							Customer[activeUser].SearchProduct(product, modelNo);
							break;
						case 3:
							if(user.Branches == null){
								System.out.printf("There is no Branch!\n");
								break;
							}

							int shopChoice = 0;
							while(shopChoice != 1 && shopChoice != 2){
								System.out.printf("\n1:  Online.\n");
								System.out.printf("2:  in-Store.\n");
								shopChoice = inp.nextInt();
							}

							if(!Customer[activeUser].isReadyForOnline() && shopChoice == 1){
								System.out.printf("Enter your Phone Number: ");
								phone = inp.next();
								Customer[activeUser].setPhone(phone);
								System.out.printf("Enter your Address: ");
								inp.nextLine();		// reads new line (\n) from inp.next()
								address = inp.nextLine();
								Customer[activeUser].setAddress(address);
								Customer[activeUser].setOnline(true);
							}

							System.out.printf("\nEnter Branch No:");
							branchNo = inp.nextInt();
							try{
								if(user.Branches[branchNo - 1] == null){
									System.out.printf("There is no Branch %d!\n",branchNo);
									break;
								}
							}catch(ArrayIndexOutOfBoundsException e){
								System.out.printf("			Wrong Index!\n");
							}
							if(user.Branches[branchNo - 1].branchEmployee == null){
								System.out.printf("There is no employee at that branch.\n");
								break;
							}
							System.out.printf("Enter Product Name:");
							product = inp.next();
							System.out.printf("Enter Model No:");
							modelNo = inp.nextInt();
							boolean isOnline;
							if(shopChoice == 1)
								isOnline = true;
							else
								isOnline = false;
							try{
							user.Branches[branchNo - 1].branchEmployee.addOrder(Customer[activeUser], branchNo, modelNo, product, isOnline);
							}catch(ArrayIndexOutOfBoundsException e){
								System.out.printf("			Wrong Index!\n");
							}
							break;
						case 4:
							Customer[activeUser].printOrders();
							break;
						case 5:
							break;
					}
				}
			}
		}
	}
}