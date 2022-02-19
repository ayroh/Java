/**
 *
 *Customer extends from user
 *@author Bilal Yalçınkaya
 *@version JDK 8 - Cygwin 64 Terminal
 *
 */

public class customer extends user{
	private String Email, Phone, Address;
	private int customerNumber;
	private boolean readyForOnline = false;
	public Order[] orders = null;
	private int orderNumber = 0;


	public customer(String name, String surname, String password, String email, int customernumber){
		super(name,surname,password);
		setEmail(email);
		setCustomerNumber(customernumber);
		userType = 3;
		orders = new Order[5];
		System.out.printf("      Your customer number is: %d.",customerNumber);
		setPhone("");
		setAddress("");
	}


	public void setPhone(String phone){
		Phone = phone;
	}
	public void setAddress(String address){
		Address = address;
	}
	public void setEmail(String email){
		Email = email;
	}
	public void setCustomerNumber(int customernumber){
		customerNumber = customernumber;
	}
	public int getCustomerNumber(){
		return customerNumber;
	}
	public String getPhone(){
		return Phone;
	}
	public String getAddress(){
		return Address;
	}

	public void setOnline(boolean choice){
		readyForOnline = choice;
	}
	public boolean isReadyForOnline(){
		return readyForOnline;
	}


	/**
	*
	*Prints previous orders of this customer
	*/ 
	public void printOrders(){
		System.out.printf("\n");
		if(orders[0] == null)
			System.out.printf("There is no order!\n");
		else
			for(int i = 0;i < orderNumber;i++){
				if(orders[i].getIsOnline())
					System.out.printf("Order %d: Model: %s, Color: %s, Model No: %d, Cost: %.2f tl, Phone: %s, Address: %s\n",i + 1, orders[i].getModel(), orders[i].getColor(), orders[i].getModelNo(), orders[i].getCost(), orders[i].getPhone(), orders[i].getAddress());
				else
					System.out.printf("Order %d: Model: %s, Color: %s, Model No: %d, Cost: %.2f tl\n",i + 1, orders[i].getModel(), orders[i].getColor(), orders[i].getModelNo(), orders[i].getCost());
			}
	}

	public int getOrderNumber(){
		return orderNumber;
	}

	public void addOrderNumber(){
		orderNumber++;
	}

	/**
	*Searches product based on it's name and model no
	*@param product Name of the product
	*@param modelNo Model no of the product
	*/

	public void SearchProduct(String product, int modelNo){
		boolean finded = false;
		if(product.equals(chair.model)){
			for(int i = 0;i < branchNumber;++i){
				for(int j = 0;j < Branches[i].getChairNumber();++j){
					if(Branches[i].Chairs[j].getModelNo() == modelNo){
						finded = true;
						Branches[i].Chairs[j].seeProperties();
						System.out.printf("Branch %d\n",i + 1);
					}
				}
			}
		}
		else if(product.equals(desk.model)){
			for(int i = 0;i < branchNumber;++i){
				for(int j = 0;j < Branches[i].getDeskNumber();++j){
					if(Branches[i].Desks[j].getModelNo() == modelNo){
						finded = true;
						Branches[i].Desks[j].seeProperties();
						System.out.printf("Branch %d\n",i + 1);
					}
				}
			}
		}
		else if(product.equals(cup.model)){
			for(int i = 0;i < branchNumber;++i){
				for(int j = 0;j < Branches[i].getCupNumber();++j){
					if(Branches[i].Cups[j].getModelNo() == modelNo){
						finded = true;
						Branches[i].Cups[j].seeProperties();
						System.out.printf("Branch %d\n",i + 1);
					}
				}
			}
		}
		else if(product.equals(bookcase.model)){
			for(int i = 0;i < branchNumber;++i){
				for(int j = 0;j < Branches[i].getBookcaseNumber();++j){
					if(Branches[i].Bookcases[j].getModelNo() == modelNo){
						finded = true;
						Branches[i].Bookcases[j].seeProperties();
						System.out.printf("Branch %d\n",i + 1);
					}
				}
			}
		}
		if(!finded)
			System.out.printf("There isn't any!\n");

	}

	/**
	*List of all products at all branches
	*
	*/

	public void listOfProducts(){
		for(int i = 0;i < branchNumber;++i){
			System.out.printf("\nBranch %d:",i+1);

			System.out.printf("\n    chair Model No: ");
			for(int j = 0;j < Branches[i].getChairNumber();++j)
				System.out.printf(" %d,",Branches[i].Chairs[j].getModelNo());

			System.out.printf("\n    desk Model No: ");
			for(int j = 0;j < Branches[i].getDeskNumber();++j)
				System.out.printf(" %d,",Branches[i].Desks[j].getModelNo());

			System.out.printf("\n    cup Model No: ");
			for(int j = 0;j < Branches[i].getCupNumber();++j)
				System.out.printf(" %d,",Branches[i].Cups[j].getModelNo());

			System.out.printf("\n    bookcase Model No: ");
			for(int j = 0;j < Branches[i].getBookcaseNumber();++j)
				System.out.printf(" %d,",Branches[i].Bookcases[j].getModelNo());
		}
	}
}