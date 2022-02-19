import java.util.Scanner;


/**
 *
 *Branch Employee extends from user
 *@author Bilal Yalçınkaya
 *@version JDK 8 - Cygwin 64 Terminal
 *
 */


public class branchemployee extends user{

	private int BranchNo;
	public boolean test = false;

	public branchemployee(String name, String surname, String password, int branchNo){
		super(name, surname, password);
		userType = 2;
		setBranchNo(branchNo);
		Branches[BranchNo - 1].setEmployee(this);
		Branches[BranchNo - 1].setNeeded();
	}

	/**
	*Adds chair to the employees current branch
	*@param times Number of times you want to add
	*@param modelNo Model no of the chair
	*@param price Price of chair
	*/

	public void addChair(int times, int modelNo, double price){
		for(int i = 0;i < times;++i){
			if(Branches[BranchNo - 1].getChairNumber() + 1 == Branches[BranchNo - 1].Chairs.length){
				chair[] tempChair = new chair[Branches[BranchNo - 1].Chairs.length];
				for(int j = 0;j < Branches[BranchNo - 1].getChairNumber();++j)
					tempChair[j] = Branches[BranchNo - 1].Chairs[j];
				Branches[BranchNo - 1].Chairs = new chair[Branches[BranchNo - 1].Chairs.length * 2];
				for(int j = 0;j < tempChair.length;++j)
					Branches[BranchNo - 1].Chairs[j] = tempChair[j];
			}
			Branches[BranchNo - 1].Chairs[Branches[BranchNo - 1].getChairNumber()] = new chair(modelNo, price);
			Branches[BranchNo - 1].addChairNumber();
		}

	}

	/**
	*Adds desk to the employees current branch
	*@param times Number of times you want to add
	*@param modelNo Model no of the desk
	*@param price Price of desk
	*/

	public void addDesk(int times, int modelNo, double price){
		for(int i = 0;i < times;++i){
			if(Branches[BranchNo - 1].getDeskNumber() + 1 == Branches[BranchNo - 1].Desks.length){
				desk[] tempDesk = new desk[Branches[BranchNo - 1].Desks.length];
				for(int j = 0;j < Branches[BranchNo - 1].Desks.length;++j)
					tempDesk[j] = Branches[BranchNo - 1].Desks[j];
				Branches[BranchNo - 1].Desks = new desk[Branches[BranchNo - 1].Desks.length * 2];
				for(int j = 0;j < tempDesk.length;++j)
					Branches[BranchNo - 1].Desks[j] = tempDesk[j];
			}
			Branches[BranchNo - 1].Desks[Branches[BranchNo - 1].getDeskNumber()] = new desk(modelNo, price);
			Branches[BranchNo - 1].addDeskNumber();
		}
	}

	/**
	*Adds cup to the employees current branch
	*@param times Number of times you want to add
	*@param modelNo Model no of the cup
	*@param price Price of cup
	*/

	public void addCup(int times, int modelNo, double price){
		for(int i = 0;i < times;++i){
			if(Branches[BranchNo - 1].getCupNumber() + 1 == Branches[BranchNo - 1].Cups.length){
				cup[] tempCup = new cup[Branches[BranchNo - 1].Cups.length];
				for(int j = 0;j < Branches[BranchNo - 1].Cups.length;++j)
					tempCup[j] = Branches[BranchNo - 1].Cups[j];
				Branches[BranchNo - 1].Cups = new cup[Branches[BranchNo - 1].Cups.length * 2];
				for(int j = 0;j < tempCup.length;++j)
					Branches[BranchNo - 1].Cups[j] = tempCup[j];
			}
			Branches[BranchNo - 1].Cups[Branches[BranchNo - 1].getCupNumber()] = new cup(modelNo, price);
			Branches[BranchNo - 1].addCupNumber();
		}
	}

	/**
	*Adds bookcase to the employees current branch
	*@param times Number of times you want to add
	*@param modelNo Model no of the bookcase
	*@param price Price of bookcase
	*/

	public void addBookcase(int times, int modelNo, double price){
		for(int i = 0;i < times;++i){
			if(Branches[BranchNo - 1].getBookcaseNumber() + 1 == Branches[BranchNo - 1].Bookcases.length){
				bookcase[] tempBookcase = new bookcase[Branches[BranchNo - 1].Bookcases.length];
				for(int j = 0;j < Branches[BranchNo - 1].Bookcases.length;++j)
					tempBookcase[j] = Branches[BranchNo - 1].Bookcases[j];
				Branches[BranchNo - 1].Bookcases = new bookcase[Branches[BranchNo - 1].Bookcases.length * 2];
				for(int j = 0;j < tempBookcase.length;++j)
					Branches[BranchNo - 1].Bookcases[j] = tempBookcase[j];
			}
			Branches[BranchNo - 1].Bookcases[Branches[BranchNo - 1].getBookcaseNumber()] = new bookcase(modelNo, price);
			Branches[BranchNo - 1].addBookcaseNumber();
		}
	}

	/**
	*Removes chair to the employees current branch
	*@param number Model No of the chair
	*/

	public void removeChair(int number){
		int i;
		for(i = 0;i < Branches[BranchNo - 1].getChairNumber();i++)
			if(Branches[BranchNo - 1].Chairs[i].getModelNo() == number)
				break;
		if(i != Branches[BranchNo - 1].getChairNumber()){
			for(i = i;i < Branches[BranchNo - 1].getChairNumber() - 1;i++)
				Branches[BranchNo - 1].Chairs[i] = Branches[BranchNo - 1].Chairs[i + 1];
			Branches[BranchNo - 1].Chairs[Branches[BranchNo - 1].getChairNumber()] = null;
			Branches[BranchNo - 1].removeChairNumber();
			System.out.printf("Model No %d chair removed.\n",number);
		}
		else
			System.out.printf("Wrong Model No!\n");
	}

	/**
	*Removes desk to the employees current branch
	*@param number Model No of the desk
	*/

	public void removeDesk(int number){
		int i;
		for(i = 0;i < Branches[BranchNo - 1].getDeskNumber();i++)
			if(Branches[BranchNo - 1].Desks[i].getModelNo() == number)
				break;
		if(i != Branches[BranchNo - 1].getDeskNumber()){
			for(i = i;i < Branches[BranchNo - 1].getDeskNumber() - 1;i++)
				Branches[BranchNo - 1].Desks[i] = Branches[BranchNo - 1].Desks[i + 1];
			Branches[BranchNo - 1].Desks[Branches[BranchNo - 1].getDeskNumber()] = null;
			Branches[BranchNo - 1].removeDeskNumber();
			System.out.printf("Model No %d desk removed.\n",number);
		}
		else
			System.out.printf("Wrong Model No!\n");
	}

	/**
	*Removes bookcase to the employees current branch
	*@param number Model No of the bookcase
	*/

	public void removeBookcase(int number){
		int i;
		for(i = 0;i < Branches[BranchNo - 1].getBookcaseNumber();i++)
			if(Branches[BranchNo - 1].Bookcases[i].getModelNo() == number)
				break;
		if(i != Branches[BranchNo - 1].getBookcaseNumber()){
			for(i = i;i < Branches[BranchNo - 1].getBookcaseNumber() - 1;i++)
				Branches[BranchNo - 1].Bookcases[i] = Branches[BranchNo - 1].Bookcases[i + 1];
			Branches[BranchNo - 1].Bookcases[Branches[BranchNo - 1].getBookcaseNumber()] = null;
			Branches[BranchNo - 1].removeBookcaseNumber();
			System.out.printf("Model No %d bookcase removed.\n",number);
		}
		else
			System.out.printf("Wrong Model No!\n");
	}

	/**
	*Removes cup to the employees current branch
	*@param number Model No of the cup
	*/

	public void removeCup(int number){
		int i;
		for(i = 0;i < Branches[BranchNo - 1].getCupNumber();i++)
			if(Branches[BranchNo - 1].Cups[i].getModelNo() == number)
				break;
		if(i != Branches[BranchNo - 1].getCupNumber()){
			for(i = i;i < Branches[BranchNo - 1].getCupNumber() - 1;i++)
				Branches[BranchNo - 1].Cups[i] = Branches[BranchNo - 1].Cups[i + 1];
			Branches[BranchNo - 1].Cups[Branches[BranchNo - 1].getCupNumber()] = null;
			Branches[BranchNo - 1].removeCupNumber();
			System.out.printf("Model No %d cup removed.\n",number);
		}
		else
			System.out.printf("Wrong Model No!\n");
		
	}

	public void setBranchNo(int branchNo){
		BranchNo = branchNo;
	}
	public int getBranchNo(){
		return BranchNo;
	}

	/**
	*Makes sale to all furnitures inside the branch
	*@param salePercentage Sale Percentage base of 100
	*/

	public void makeSale(int salePercentage){
		Branches[BranchNo - 1].SalePercentage = salePercentage;
	}

	/**
	*Takes a color from models color array and adds order to taken Customer
	*@param Customer Adds order to Customers 'orders' array
	*/	

	public void addOrder(customer Customer, int branchNo, int modelNo, String model, boolean isOnline){	

		if(Customer.orders.length == Customer.getOrderNumber() + 1){			// extending order array
			Order[] tempOrders = new Order[Customer.orders.length];
			for(int j = 0;j < Customer.orders.length;++j)
				tempOrders[j] = Customer.orders[j];
			Customer.orders = new Order[Customer.orders.length * 2];
			for(int j = 0;j < tempOrders.length;++j)
				Customer.orders[j] = tempOrders[j];
		}

		Scanner inp = new Scanner(System.in);
		double cost = 0;
		int i = 0;
		int color;
		if(model.equals(chair.model)){
			for(i = 0;i < Branches[branchNo - 1].getChairNumber();i++){
				if(Branches[branchNo - 1].Chairs[i].getModelNo() == modelNo){
					cost = Branches[branchNo - 1].Chairs[i].getPrice() * (100 - Branches[branchNo - 1].getSalePercentage()) / 100;
					break;
				}
			}
		}
		else if(model.equals(desk.model)){
			for(i = 0;i < Branches[branchNo - 1].getDeskNumber();i++){
				if(Branches[branchNo - 1].Desks[i].getModelNo() == modelNo){
					cost = Branches[branchNo - 1].Desks[i].getPrice() * (100 - Branches[branchNo - 1].getSalePercentage()) / 100;
					break;
				}
			}
		}
		else if(model.equals(cup.model)){
			for(i = 0;i < Branches[branchNo - 1].getCupNumber();i++){
				if(Branches[branchNo - 1].Cups[i].getModelNo() == modelNo){
					cost = Branches[branchNo - 1].Cups[i].getPrice() * (100 - Branches[branchNo - 1].getSalePercentage()) / 100;
					break;
				}
			}
		}
		else if(model.equals(bookcase.model)){
			for(i = 0;i < Branches[branchNo - 1].getBookcaseNumber();i++){
				if(Branches[branchNo - 1].Bookcases[i].getModelNo() == modelNo){
					cost = Branches[branchNo - 1].Bookcases[i].getPrice() * (100 - Branches[branchNo - 1].getSalePercentage()) / 100;
					break;
				}
			}
		}
		if(cost > 0){
			if(!test)
				System.out.printf("Choose a color:");
			if(model.equals(chair.model)){
				if(test)
					color = 1;
				else{
					Branches[branchNo - 1].Chairs[i].seeColors();
					color = inp.nextInt();
				}
				try{
					Customer.orders[Customer.getOrderNumber()] = new Order(Branches[branchNo - 1].Chairs[i].getColor(color), cost, modelNo, model, Customer.getPhone(), Customer.getAddress(), isOnline);
					removeChair(modelNo);
				}catch(ArrayIndexOutOfBoundsException e){
					System.out.printf("Wrong Index!\n");
				}
			}
			else if(model.equals(desk.model)){
				if(test)
					color = 1;
				else{
					Branches[branchNo - 1].Desks[i].seeColors();
					color = inp.nextInt();
				}
				try{
					Customer.orders[Customer.getOrderNumber()] = new Order(Branches[branchNo - 1].Desks[i].getColor(color), cost, modelNo, model, Customer.getPhone(), Customer.getAddress(), isOnline);
					removeDesk(modelNo);
				}catch(ArrayIndexOutOfBoundsException e){
					System.out.printf("Wrong Index!\n");
				}
			}
			else if(model.equals(cup.model)){
				if(test)
					color = 1;
				else{
					Branches[branchNo - 1].Cups[i].seeColors();
					color = inp.nextInt();
				}
				try{
					Customer.orders[Customer.getOrderNumber()] = new Order(Branches[branchNo - 1].Cups[i].getColor(color), cost, modelNo, model, Customer.getPhone(), Customer.getAddress(), isOnline);
					removeCup(modelNo);
				}catch(ArrayIndexOutOfBoundsException e){
					System.out.printf("Wrong Index!\n");
				}
			}
			else if(model.equals(bookcase.model)){
				if(test)
					color = 1;
				else{
					Branches[branchNo - 1].Bookcases[i].seeColors();
					color = inp.nextInt();
				}
				try{
					Customer.orders[Customer.getOrderNumber()] = new Order(Branches[branchNo - 1].Bookcases[i].getColor(color), cost, modelNo, model, Customer.getPhone(), Customer.getAddress(), isOnline);
					removeBookcase(modelNo);
				}catch(ArrayIndexOutOfBoundsException e){
					System.out.printf("Wrong Index!\n");
				}

			}
			Customer.addOrderNumber();
		}
		else{
			System.out.printf("Wrong item.");
		}
	}


	/**
	*Prints all orders of Customer by finding it through Customer Number
	*@param Customers To find customer by customer number
	*@param numberOfCustomers Size of Customer array
	*@param customerNumber Customer number to find customer
	*/

	public void seeOrders(customer[] Customers, int numberOfCustomers, int customerNumber){
		int i;
		for(i = 0;i < numberOfCustomers;++i){
			if(Customers[i].getCustomerNumber() == customerNumber){
				Customers[i].printOrders();
				break;
			}
		}
		if(i == numberOfCustomers)
			System.out.printf("Wrong Customer Number!\n");
	}

	/**
	*Prints employees current branches needs
	*/

	public void queryProducts(){
		Branches[BranchNo - 1].getNeeded();
	}

	/**
	*Informs the Company's admin that branch needs products
	*/

	public void informManager(){
		administrator.setMessage(true);
	}

}