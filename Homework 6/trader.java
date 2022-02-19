import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;

public class trader extends user{

	public trader(String Id, String Password, String Name){
		if(Id.length() != 8){
			System.out.printf("Id length is not 8!\n");
			return;
		}
		if(Password.length() != 6){
			System.out.printf("Password length is not 6!\n");
			return;
		}
		setId(Id);
		setPassword(Password);
		setName(Name);
		setType("trader");
	}


	/**
	This function finds spesific order and meets or cancels it by the input and overwrites file
	@param OrderId Order to be found
	@param wannaMeet True if meet, false if cancel
	@return True if successfull, false if order couldnt found or it does not belongs to current trader
	*/
	public boolean meetOrCancelOrder(int OrderId, boolean wannaMeet){
		String temp = null;
		int intcounter = 0;
		int lineTemp = 0;
		ArrayList<String> ordersTemp = new ArrayList();
		try{
			FileReader read = new FileReader("orders.txt");
			BufferedReader reader = new BufferedReader(read);
			while(reader.ready()){
				String line = reader.readLine();
				ordersTemp.add(line);
				String counter = new String();
				int k = 0;
				while(line.charAt(k) != ';'){
					counter = counter.concat(Character.toString(line.charAt(k)));
					k++;
				}
				if(Integer.parseInt(counter) == OrderId){
					temp = new String(line);
					lineTemp = intcounter;
				}
				intcounter++;
			}
			reader.close();
			read.close();
		} catch (FileNotFoundException e) {
      		System.out.println("An error occurred.");
      		e.printStackTrace();
    	} catch (IOException e) {
	     	System.out.println("An error occurred.");
	      	e.printStackTrace();
	    }
	    if(temp == null){
	    	System.out.printf("There is no order with that id!\n");
	    	return false;
	    }

		int i = 0;
		String orderidtemp = new String();
		while(temp.charAt(i) != ';'){
			orderidtemp = orderidtemp.concat(Character.toString(temp.charAt(i)));
			i++;
		}
		i++;
		String status = new String();
		while(temp.charAt(i) != ';'){
			status = status.concat(Character.toString(temp.charAt(i)));
			i++;
		}
		i++;
		String productidtemp = new String();
		while(temp.charAt(i) != ';'){
			productidtemp = productidtemp.concat(Character.toString(temp.charAt(i)));
			i++;
		}
		i++;
		String useridtemp = new String();
		while(temp.charAt(i) != ';'){
			useridtemp = useridtemp.concat(Character.toString(temp.charAt(i)));
			i++;
		}
		i++;
		String traderName = new String();
		while(i != temp.length()){
			traderName = traderName.concat(Character.toString(temp.charAt(i)));
			i++;
		}
		if(!traderName.contains(getName())){
			System.out.printf("This order does not belong to you!\n");
			return false;
		}
		if(status.contains("MET")){
			System.out.printf("Order already met!\n");
			return false;
		}
		if(status.contains("CANCELED")){
			System.out.printf("Order already canceled!\n");
			return false;	
		}
		i = 0;
		try{
			File myObj = new File("orders.txt");
			myObj.delete();
			FileWriter writer = new FileWriter("orders.txt", true);
			while(i != ordersTemp.size()){
				if(i == lineTemp){
					writer.write(orderidtemp);
					if(wannaMeet)
						writer.write(";MET;");
					else
						writer.write(";CANCELED;");
					writer.write(productidtemp);
					writer.write(";");
					writer.write(useridtemp);
					writer.write(";");
					writer.write(getName());
					writer.write("\n");
				}
				else{
					writer.write(ordersTemp.get(i));
					writer.write("\n");
				}
				i++;
			}
			writer.close();
		} catch (FileNotFoundException e) {
      		System.out.println("An error occurred.");
      		e.printStackTrace();
    	} catch (IOException e) {
	     	System.out.println("An error occurred.");
	      	e.printStackTrace();
	    }
	    if(wannaMeet)
	    	System.out.printf("Order Met Successfully.\n");
	    else
	    	System.out.printf("Order Canceled Successfully.\n");
		return true;
	}


	/**
	Prints all orders of current trader
	*/
	public void printOrders(){
		try{
			FileReader read = new FileReader("orders.txt");
			BufferedReader reader = new BufferedReader(read);
			while(reader.ready()){
				String line = reader.readLine();
				String counter = new String();
				int k = 0;

				int i = 0;
				String orderidtemp = new String();
				while(line.charAt(i) != ';'){
					orderidtemp = orderidtemp.concat(Character.toString(line.charAt(i)));
					i++;
				}
				i++;
				String status = new String();
				while(line.charAt(i) != ';'){
					status = status.concat(Character.toString(line.charAt(i)));
					i++;
				}
				i++;
				String productidtemp = new String();
				while(line.charAt(i) != ';'){
					productidtemp = productidtemp.concat(Character.toString(line.charAt(i)));
					i++;
				}
				i++;
				String useridtemp = new String();
				while(line.charAt(i) != ';'){
					useridtemp = useridtemp.concat(Character.toString(line.charAt(i)));
					i++;
				}
				i++;
				String traderName = new String();
				while(i != line.length()){
					traderName = traderName.concat(Character.toString(line.charAt(i)));
					i++;
				}
				if(traderName.toLowerCase().contains(getName().toLowerCase()))
					System.out.printf("Order Id: %s, Status: %s, Product Id: %s, User Id: %s, Trader: %s\n", orderidtemp, status, productidtemp, useridtemp, traderName);
			}
			reader.close();
			read.close();
		} catch (FileNotFoundException e) {
      		System.out.println("An error occurred.");
      		e.printStackTrace();
    	} catch (IOException e) {
	     	System.out.println("An error occurred.");
	      	e.printStackTrace();
	    }
	}


	/**
	Add product to file
	@param item Product to be added
	@return True if added, false if already added
	*/
	public boolean addProduct(product item){
		try{
			FileReader read = new FileReader("file.txt");
			BufferedReader reader = new BufferedReader(read);
			PriorityQueue<String> fileTemp = new PriorityQueue(new user.traderComparator());
			String str = new String();
			str = str.concat(item.getId());
			str = str.concat(";");
			str = str.concat(item.getName());
			str = str.concat(";\"[\"\"");
			ListIterator<String> iter = item.getCategoryTree().listIterator();
			while(true){
				str = str.concat(iter.next());
				if(iter.hasNext())
					str = str.concat(" >> ");
				else{
					str = str.concat("\"\"]\";");
					break;
				}
			}
			str = str.concat(String.valueOf(item.getPrice()));
			str = str.concat(";");
			str = str.concat(String.valueOf(item.getDiscountedPrice()));
			str = str.concat(";");
			str = str.concat(item.getDescription());
			str = str.concat(";");
			str = str.concat(item.getTrader());
			fileTemp.add(str);

			while(reader.ready()){
				String line = reader.readLine(); 
				String forIDControl = new String();
				int k = 0;
				while(line.charAt(k) != ';'){
					forIDControl = forIDControl.concat(Character.toString(line.charAt(k)));
					k++;
				}
				if(forIDControl.contains(item.getId())){
					reader.close();
					read.close();			
					System.out.printf("Product ID already in use!\n");
					return false;
				}
				fileTemp.add(line);
			}
			reader.close();
			read.close();
			FileWriter writer = null;
			int i;
			File myObj = new File("file.txt");
			myObj.delete();
			myObj.createNewFile();
			writer = new FileWriter("file.txt");
			Iterator<String> iterfile = fileTemp.iterator();
			while(iterfile.hasNext()){
				writer.write(iterfile.next());
				writer.write("\n");
			}
			writer.close();

		} catch (FileNotFoundException e) {
      		System.out.println("An error occurred.");
      		e.printStackTrace();
    	} catch (IOException e) {
	     	System.out.println("An error occurred.");
	      	e.printStackTrace();
	    }



		/*try{
			FileReader read = new FileReader("file.txt");
			BufferedReader reader = new BufferedReader(read);
			ArrayList<String> fileTemp = new ArrayList();
			int counter = 0;
			int lineTemp = -1;
			while(reader.ready()){
				String line = reader.readLine();
				fileTemp.add(line);
				if(lineTemp == -1){
					int i = 0;
					while(line.charAt(i) != ';')
						i++;
					i++;
					while(line.charAt(i) != ';')
						i++;
					i+=5;
					while(line.charAt(i) != ']')
						i++;
					i+=3;
					while(line.charAt(i) != ';')
						i++;
					i++;
					while(line.charAt(i) != ';')
						i++;
					i++;
					while(line.charAt(i) != ';')
						i++;
					i++;
					String tradertemp = new String();
					while(i != line.length()){
						tradertemp = tradertemp.concat(Character.toString(line.charAt(i)));
						i++;
					}
					if(item.getTrader().compareTo(tradertemp) == 0){
						lineTemp = counter;
					}
					counter++;
				}
			}
			reader.close();
			read.close();
			FileWriter writer = null;
			int i;
			if(lineTemp == -1){
				writer = new FileWriter("file.txt", true);
			}
			else{
				File myObj = new File("file.txt");
				myObj.delete();
				writer = new FileWriter("file.txt");
				for(i = 0;i < lineTemp;i++)
					writer.write(fileTemp.get(i));
			}
			writer.write(item.getId());
			writer.write(';');
			writer.write(item.getName());
			writer.write(";\"[\"\"");
			ListIterator<String> iter = item.getCategoryTree().listIterator();
			while(true){
				writer.write(iter.next());
				if(iter.hasNext())
					writer.write(" >> ");
				else{
					writer.write("\"\"]\";");
					break;
				}
			}
			writer.write(String.valueOf(item.getPrice()));
			writer.write(';');
			writer.write(String.valueOf(item.getDiscountedPrice()));
			writer.write(';');
			writer.write(item.getDescription());
			writer.write(';');
			writer.write(item.getTrader());
			writer.write('\n');
			if(lineTemp != -1)
				for(i = lineTemp;i < fileTemp.size();i++)
					writer.write(fileTemp.get(i));

			writer.close();

		} catch (FileNotFoundException e) {
      		System.out.println("An error occurred.");
      		e.printStackTrace();
    	} catch (IOException e) {
	     	System.out.println("An error occurred.");
	      	e.printStackTrace();
	    }*/
	    System.out.printf("Product added successfully!\n");
	    return true;
	}

	/**
	Remove product from file
	@param itemId Item to be removed
	@return True if removed, false if it doesn't exists or it does not belongs to current trader
	*/
	public boolean removeProduct(String itemId){
		boolean isFound = false;
		try{
			FileReader read = new FileReader("file.txt");
			BufferedReader reader = new BufferedReader(read);
			ArrayList<String> fileTemp = new ArrayList();
			String str = new String();
			while(reader.ready()){
				str = new String();
				String line = reader.readLine();
				int k = 0;
				while(line.charAt(k) != ';'){
					str = str.concat(Character.toString(line.charAt(k)));
					k++;
				}
				if(!itemId.contains(str))
					fileTemp.add(line);
				else
					isFound = true;

			}
			reader.close();
			read.close();
			if(!isFound)
				return false;
			int i;
			File myObj = new File("file.txt");
			myObj.delete();
			myObj.createNewFile();
			FileWriter writer = new FileWriter("file.txt");
			Iterator<String> iterfile = fileTemp.iterator();
			while(iterfile.hasNext()){
				writer.write(iterfile.next());
				writer.write("\n");
			}
			writer.close();
		} catch (FileNotFoundException e) {
      		System.out.println("An error occurred.");
      		e.printStackTrace();
    	} catch (IOException e) {
	     	System.out.println("An error occurred.");
	      	e.printStackTrace();
	    }
	    System.out.printf("Product removed successfully!\n");
	    return true;
	}

	/**
	Edit product inside the file
	@return True if edited, false if it doesn't exists or it does not belongs to current trader
	*/
	public boolean editProduct(String itemId, String itemName, LinkedList<String> itemCategory, int itemPrice, int itemDiscountedPrice, String itemDescription){
		boolean isFound = false;
		try{
			FileReader read = new FileReader("file.txt");
			BufferedReader reader = new BufferedReader(read);
			ArrayList<String> fileTemp = new ArrayList();
			String str = new String();
			while(reader.ready()){
				str = new String();
				String line = reader.readLine();
				int k = 0;
				while(line.charAt(k) != ';'){
					str = str.concat(Character.toString(line.charAt(k)));
					k++;
				}
				while(line.length() != k)
					k++;
				k--;
				while(line.charAt(k) != ';')
					k--;
				k++;
				String itemTrader = new String();
				while(line.length() != k){
					itemTrader = itemTrader.concat(Character.toString(line.charAt(k)));
					k++;
				}

				if(!itemId.contains(str) || !itemTrader.contains(getName()))
					fileTemp.add(line);
				else{
					isFound = true;
					str = new String();
					str = str.concat(itemId);
					str = str.concat(";");
					str = str.concat(itemName);
					str = str.concat(";\"[\"\"");
					ListIterator<String> iter = itemCategory.listIterator();
					while(true){
						str = str.concat(iter.next());
						if(iter.hasNext())
							str = str.concat(" >> ");
						else{
							str = str.concat("\"\"]\";");
							break;
						}
					}
					str = str.concat(String.valueOf(itemPrice));
					str = str.concat(";");
					str = str.concat(String.valueOf(itemDiscountedPrice));
					str = str.concat(";");
					str = str.concat(itemDescription);
					str = str.concat(";");
					str = str.concat(getName());
					fileTemp.add(str);
				}
			}
			reader.close();
			read.close();
			if(!isFound){
				System.out.printf("Product could not be edited!\n");
				return false;
			}
			int i;
			File myObj = new File("file.txt");
			myObj.delete();
			myObj.createNewFile();
			FileWriter writer = new FileWriter("file.txt");
			Iterator<String> iterfile = fileTemp.iterator();
			while(iterfile.hasNext()){
				writer.write(iterfile.next());
				writer.write("\n");
			}
			writer.close();
		} catch (FileNotFoundException e) {
      		System.out.println("An error occurred.");
      		e.printStackTrace();
    	} catch (IOException e) {
	     	System.out.println("An error occurred.");
	      	e.printStackTrace();
	    }
	    System.out.printf("Product edited successfully!\n");
	    return true;
	}

}