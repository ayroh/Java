import java.util.ListIterator;

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
	private int orderNumber = 0;
	public HybridList<Order> orders = null;


	public customer(String name, String surname, String password, String email, int customernumber){
		super(name,surname,password);
		setEmail(email);
		setCustomerNumber(customernumber);
		userType = 3;
		orders = new HybridList<Order>();
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
		if(orders.size() == 0)
			System.out.printf("There is no order!\n");
		else
			for(int i = 0;i < orders.size();i++){
				if(orders.get(i).getIsOnline())
					System.out.printf("Order %d: Model: %s, Color: %s, Model No: %d, Cost: %.2f tl, Phone: %s, Address: %s\n",i + 1, orders.get(i).getModel(), orders.get(i).getColor(), orders.get(i).getModelNo(), orders.get(i).getCost(), orders.get(i).getPhone(), orders.get(i).getAddress());
				else
					System.out.printf("Order %d: Model: %s, Color: %s, Model No: %d, Cost: %.2f tl\n",i + 1, orders.get(i).getModel(), orders.get(i).getColor(), orders.get(i).getModelNo(), orders.get(i).getCost());
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
		ListIterator<branch> Iter = Branches.ListIterator(0);
		boolean finded;
		boolean findedSomewhere = false;
		while(Iter.hasNext()){
			finded = false;
			branch temp = Iter.next();
			furniture furnTemp;
			if(product.equals(chair.model)){
				for(int i = 0;i < temp.Furnitures.size();i++){
					furnTemp = temp.Furnitures.get(i);
					if(furnTemp.equals(new chair(modelNo, 0.0))){
						finded = true;
						findedSomewhere = true;
						break;
					}
				}
			}
			else if(product.equals(chair.model)){
				for(int i = 0;i < temp.Furnitures.size();i++){
					furnTemp = temp.Furnitures.get(i);
					if(furnTemp.equals(new desk(modelNo, 0.0))){
						finded = true;
						findedSomewhere = true;
						break;
					}
				}
			}
			else if(product.equals(cup.model)){
				for(int i = 0;i < temp.Furnitures.size();i++){
					furnTemp = temp.Furnitures.get(i);
					if(furnTemp.equals(new cup(modelNo, 0.0))){
						finded = true;
						findedSomewhere = true;
						break;
					}
				}	
			}
			else if(product.equals(bookcase.model)){
				for(int i = 0;i < temp.Furnitures.size();i++){
					furnTemp = temp.Furnitures.get(i);
					if(furnTemp.equals(new bookcase(modelNo, 0.0))){
						finded = true;
						findedSomewhere = true;
						break;
					}
				}
			}
			
			if(finded){
				System.out.printf("Branch %d\n",temp.getBranchNumber());
			}
		}
		if(!findedSomewhere)
			System.out.printf("There isn't any!\n");
	}


	public void listOfProducts(){
		ListIterator<branch> Iter = Branches.ListIterator(0);
		while(Iter.hasNext()){
			branch temp = Iter.next();
			System.out.printf("\nBranch %d:",temp.getBranchNumber());
			for(int i = 0;i < temp.Furnitures.size();i++){
				furniture furnTemp = temp.Furnitures.get(i);
				System.out.printf("%s model %d, ",furnTemp.getModel(), furnTemp.getModelNo());	
			}
		}
	}
}