import java.util.*;

public class product{
	private String id, name, description, trader;
	private int price, discountedPrice;
	private LinkedList<String> categoryTree;
	private double discountPercentage;

	public product(String Id, String Name, LinkedList<String> CategoryTree, int Price, int DiscountedPrice, String Description, String Trader){
		id = new String(Id);
		name = new String(Name);
		description = new String(Description);
		trader = new String(Trader);
		price = Price;
		discountedPrice = DiscountedPrice;
		categoryTree = CategoryTree;
		discountPercentage = (double)(price - discountedPrice) / (double)price * 100;
	}

	/**
	Prints basic datas of current product
	*/
	public void print(){
		System.out.printf("ID: %s, Name: %s, Trader: %s, Discounted Price: %d, Discount Percentage: %.1f\n",id,name,trader,discountedPrice,discountPercentage);
	}

	public double getDiscountPercentage(){
		return discountPercentage;
	}

	public String getId(){
		return id;
	}

	public String getName(){
		return name;
	}

	public String getDescription(){
		return description;
	}

	public String getTrader(){
		return trader;
	}

	public int getPrice(){
		return price;
	}

	public int getDiscountedPrice(){
		return discountedPrice;
	}

	public LinkedList<String> getCategoryTree(){
		return categoryTree;
	}

	public void setId(String temp){
		id = new String(temp);
	}

	public void setName(String temp){
		name = new String(temp);
	}

	public void setDescription(String temp){
		description = new String(temp);
	}

	public void setTrader(String temp){
		trader = new String(temp);
	}

	public void setPrice(int temp){
		price = temp;
	}

	public void setDiscountedPrice(int temp){
		discountedPrice = temp;
	}
}