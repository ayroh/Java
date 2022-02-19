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
		Branches.get(BranchNo - 1).setEmployee(this);
		Branches.get(BranchNo - 1).setNeeded();
	}

	/**
	Adds furniture to current branches HybridList
	*/
	public void addFurniture(int times, int modelNo, String model, double price){
		if(model.equals(chair.model)){
			for(int i = 0;i < times;i++){
				Branches.get(BranchNo - 1).Furnitures.add(new chair(modelNo, price));
				Branches.get(BranchNo - 1).addChairNumber();
			}
		}
		else if(model.equals(desk.model)){
			for(int i = 0;i < times;i++){
				Branches.get(BranchNo - 1).Furnitures.add(new desk(modelNo, price));
				Branches.get(BranchNo - 1).addDeskNumber();
			}
		}
		else if(model.equals(cup.model)){
			for(int i = 0;i < times;i++){
				Branches.get(BranchNo - 1).Furnitures.add(new cup(modelNo, price));
				Branches.get(BranchNo - 1).addCupNumber();
			}
		}
		else if(model.equals(bookcase.model)){
			for(int i = 0;i < times;i++){
				Branches.get(BranchNo - 1).Furnitures.add(new bookcase(modelNo, price));
				Branches.get(BranchNo - 1).addBookcaseNumber();
			}
		}
		else{
			System.out.printf("Wrong Item!\n");
		}
	}


	/**
	Removes current branches furniture by the model and modelno
	*/
	public void removeFurniture(int modelno, String model){
		double price = 0.0;
		int index = -1;
		if(model.equals(chair.model)){
			for(int i = 0;i < Branches.get(BranchNo - 1).Furnitures.size();i++){
				if(Branches.get(BranchNo - 1).Furnitures.get(i).equals(new chair(modelno, 0.0))){
					index = i;
					break;
				}
			}
			Branches.get(BranchNo - 1).Furnitures.remove(index);
			Branches.get(BranchNo - 1).removeChairNumber();
		}
		else if(model.equals(desk.model)){
			for(int i = 0;i < Branches.get(BranchNo - 1).Furnitures.size();i++){
				if(Branches.get(BranchNo - 1).Furnitures.get(i).equals(new desk(modelno, 0.0))){
					index = i;
					break;
				}
			}
			Branches.get(BranchNo - 1).Furnitures.remove(index);
			Branches.get(BranchNo - 1).removeDeskNumber();
		}
		else if(model.equals(cup.model)){
			for(int i = 0;i < Branches.get(BranchNo - 1).Furnitures.size();i++){
				if(Branches.get(BranchNo - 1).Furnitures.get(i).equals(new cup(modelno, 0.0))){
					index = i;
					break;
				}
			}
			Branches.get(BranchNo - 1).Furnitures.remove(index);
			Branches.get(BranchNo - 1).removeCupNumber();
		}
		else if(model.equals(bookcase.model)){
			for(int i = 0;i < Branches.get(BranchNo - 1).Furnitures.size();i++){
				if(Branches.get(BranchNo - 1).Furnitures.get(i).equals(new bookcase(modelno, 0.0))){
					index = i;
					break;
				}
			}
			Branches.get(BranchNo - 1).Furnitures.remove(index);
			Branches.get(BranchNo - 1).removeBookcaseNumber();
		}
		else{
			System.out.printf("Wrong Item!\n");
		}
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
		Branches.get(BranchNo - 1).SalePercentage = salePercentage;
	}

	/**
	*Takes a color from models color array and adds order to taken Customer
	*@param Customer Adds order to Customers 'orders' array
	*/	

	public void addOrder(customer Customer, int branchNo, int modelNo, String model, boolean isOnline){	

		Scanner inp = new Scanner(System.in);
		double cost = 0.0;
		int color;
		int index = -2;

		if(model.equals(chair.model)){
			for(int i = 0;i < Branches.get(BranchNo - 1).Furnitures.size();i++){
				if(Branches.get(BranchNo - 1).Furnitures.get(i).equals(new chair(modelNo, 0.0))){
					index = i;
					break;
				}
			}
		}
		else if(model.equals(desk.model)){
			for(int i = 0;i < Branches.get(BranchNo - 1).Furnitures.size();i++){
				if(Branches.get(BranchNo - 1).Furnitures.get(i).equals(new desk(modelNo, 0.0))){
					index = i;
					break;
				}
			}
		}
		else if(model.equals(cup.model)){
			for(int i = 0;i < Branches.get(BranchNo - 1).Furnitures.size();i++){
				if(Branches.get(BranchNo - 1).Furnitures.get(i).equals(new cup(modelNo, 0.0))){
					index = i;
					break;
				}
			}	
		}
		else if(model.equals(bookcase.model)){
			for(int i = 0;i < Branches.get(BranchNo - 1).Furnitures.size();i++){
				if(Branches.get(BranchNo - 1).Furnitures.get(i).equals(new bookcase(modelNo, 0.0))){
					index = i;
					break;
				}
			}
		}

		if(index >= 0){
			if(!test)
				System.out.printf("Choose a color:");
			if(test)
				color = 1;
			else{
				Branches.get(BranchNo - 1).Furnitures.get(index).seeColors();
				color = inp.nextInt();
			}
			try{
				//Customer.orders[Customer.getOrderNumber()] = new Order(Branches.get(BranchNo - 1).Chairs[i].getColor(color), cost, modelNo, model, Customer.getPhone(), Customer.getAddress(), isOnline);
				Customer.orders.add(new Order(Branches.get(BranchNo - 1).Furnitures.get(index).getColor(color), Branches.get(BranchNo - 1).Furnitures.get(index).getPrice() * (100 - Branches.get(BranchNo - 1).getSalePercentage()) / 100, modelNo, model, Customer.getPhone(), Customer.getAddress(), isOnline));
				removeFurniture(modelNo, model);
				//removeChair(modelNo);
			}catch(ArrayIndexOutOfBoundsException e){
				System.out.printf("Wrong Index!\n");
			}
		}
		else
			System.out.printf("Wrong Item\n");


	}


	/**
	*Prints all orders of Customer by finding it through Customer Number
	*@param Customers To find customer by customer number
	*@param customerNumber Customer number to find customer
	*/

	public void seeOrders(KWArrayList<customer> Customers, int customerNumber){
		int i;
		for(i = 0;i < Customers.size();++i){
			if(Customers.get(i).getCustomerNumber() == customerNumber){
				Customers.get(i).printOrders();
				break;
			}
		}
		if(i == Customers.size())
			System.out.printf("Wrong Customer Number!\n");
	}

	/**
	*Prints employees current branches needs
	*/

	public void queryProducts(){
		Branches.get(BranchNo - 1).getNeeded();
	}

	/**
	*Informs the Company's admin that branch needs products
	*/

	public void informManager(){
		administrator.setMessage(true);
	}

}