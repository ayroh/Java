
/**
 *
 *Abstract base class of all users
 *@author Bilal Yalçınkaya
 *@version JDK 8 - Cygwin 64 Terminal
 *
 */

public abstract class user{

	protected String Name, Surname, Password;
	protected int userType;
	static branch[] Branches;
	static int branchNumber = 0;

	public user(String name, String surname, String password){
		setName(name);
		setSurname(surname);
		setPassword(password);
	}

	public int getUserType(){
		return userType;
	}

	public void setName(String name){
		Name = name;
	}
	public String getName(){
		return Name;
	}
	public void setSurname(String surname){
		Surname = surname;
	}
	public String getSurname(){
		return Surname;
	}
	public void setPassword(String password){
		Password = password;
	}
	public String getPassword(){
		return Password;
	}


	public class Order{
		private String Color, Model, Phone, Address;
		private int ModelNo;
		private double Cost;
		private boolean IsOnline;
		public Order(String color, double cost, int modelNo, String model, String phone, String address, boolean isOnline){
			Color = color;
			Cost = cost;
			ModelNo = modelNo;
			Model = model;
			Phone = phone;
			Address = address;
			IsOnline = isOnline;
		}

		public String getColor(){
			return Color;
		}
		public String getModel(){
			return Model;
		}
		public int getModelNo(){
			return ModelNo;
		}
		public double getCost(){
			return Cost;
		}
		public String getPhone(){
			return Phone;
		}
		public String getAddress(){
			return Address;
		}
		public boolean getIsOnline(){
			return IsOnline;
		}
	}
}