import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;

public class customer extends user{
	public customer(String Id, String Password, String Name){
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
		setType("customer");
	}

	/**
	Searches for spesific data from file in name or description of products and returns them
	@param data data to be searched at name or description
	@return ArrayList of products
	*/
	public ArrayList<product> search(String data){
		ArrayList<product> tempProduct = new ArrayList();
		try {
    	FileReader myObj = new FileReader("file.txt");
    	BufferedReader myReader = new BufferedReader(myObj);
    	int count = 0;
    	while (myReader.ready()){
    		String line = myReader.readLine();
    		int i = 0;
    		while(line.charAt(i) != ';')
    			i++;
    		i++;
    		String temp = new String();
    		while(line.charAt(i) != ';'){
    			temp = temp.concat(Character.toString(line.charAt(i)));
    			i++;
    		}
    		i++;
    		if(temp.contains(data)){
    			i = 0;
    			String id = new String(), name = new String(), description = new String(), trader = new String();
    			int price = 0, discountedPrice = 0;
    			LinkedList<String> lltemp = new LinkedList();
    			while(line.charAt(i) != ';'){
    				id = id.concat(Character.toString(line.charAt(i)));
    				i++;
    			}
    			i++;
    			while(line.charAt(i) != ';'){
    				name = name.concat(Character.toString(line.charAt(i)));
    				i++;
    			}
				i+=5;
				while(line.charAt(i) != '\"'){
					String str = new String();
					while(line.charAt(i) != '>' && line.charAt(i) != '\"'){
						str = str.concat(Character.toString(line.charAt(i)));
						i++;
					}
					lltemp.add(new String(str));
					if(line.charAt(i) == '\"'){
						break;
					}
					i+=2;
				}
				i+=5;
    			String str = new String();
    			while(line.charAt(i) != ';'){
    				str = str.concat(Character.toString(line.charAt(i)));
    				i++;
    			}
    			price = Integer.parseInt(str);
    			i++;
    			str = new String();
    			while(line.charAt(i) != ';'){
    				str = str.concat(Character.toString(line.charAt(i)));
    				discountedPrice = Integer.parseInt(str);
    				i++;
    			}
    			i++;
    			while(line.charAt(i) != ';'){
    				description = description.concat(Character.toString(line.charAt(i)));
    				i++;
    			}
    			i++;
    			while(i != line.length()){
    				trader = trader.concat(Character.toString(line.charAt(i)));
    				i++;
    			}
    			i++;
    			tempProduct.add(new product(id, name, lltemp, price, discountedPrice, description, trader));
    		}
    		else{
    			while(line.charAt(i) != ';')
    				i++;
    			i++;
    			while(line.charAt(i) != ';')
    				i++;
    			i++;
    			while(line.charAt(i) != ';')
    				i++;
    			i++;
    			temp = new String();
    			while(line.charAt(i) != ';'){
	    			temp = temp.concat(Character.toString(line.charAt(i)));
	    			i++;
    			}
    			if(temp.contains(data)){
	    			i = 0;
	    			String id = new String(), name = new String(), description = new String(), trader = new String();
	    			int price = 0, discountedPrice = 0;
	    			LinkedList<String> lltemp = new LinkedList();
	    			while(line.charAt(i) != ';'){
	    				id = id.concat(Character.toString(line.charAt(i)));
	    				i++;
	    			}
	    			i++;
	    			while(line.charAt(i) != ';'){
	    				name = name.concat(Character.toString(line.charAt(i)));
	    				i++;
	    			}
					i+=5;
					while(line.charAt(i) != '\"'){
						String str = new String();
						while(line.charAt(i) != '>' && line.charAt(i) != ']'){
							str = str.concat(Character.toString(line.charAt(i)));
							i++;
						}
						if(line.charAt(i) == ']'){
							str = str.substring(0, str.length() - 3);
							lltemp.add(new String(str));
							break;
						}
						lltemp.add(new String(str));
						i+=2;
					}
					i+=3;
	    			String str = new String();
	    			while(line.charAt(i) != ';'){
	    				str = str.concat(Character.toString(line.charAt(i)));
	    				i++;
	    			}
	    			price = Integer.parseInt(str);
	    			i++;
	    			str = new String();
	    			while(line.charAt(i) != ';'){
	    				str = str.concat(Character.toString(line.charAt(i)));
	    				discountedPrice = Integer.parseInt(str);
	    				i++;
	    			}
	    			i++;
	    			while(line.charAt(i) != ';'){
	    				description = description.concat(Character.toString(line.charAt(i)));
	    				i++;
	    			}
	    			i++;
	    			while(i != line.length()){
	    				trader = trader.concat(Character.toString(line.charAt(i)));
	    				i++;
	    			}
	    			i++;
	    			tempProduct.add(new product(id, name, lltemp, price, discountedPrice, description, trader));
	    		}
    		}
        }
      	myReader.close();
    	} catch (FileNotFoundException e) {
      		System.out.println("An error occurred.");
      		e.printStackTrace();
    	} catch (IOException e) {
	     	System.out.println("An error occurred.");
	      	e.printStackTrace();
	    }
	    return tempProduct;
	}


	/**
	Searches for a spesific trader. Since txt file sorted by trader names when trader is found it prints all of his/her products then stop instead of looking through all file
	@param trader Name of trader to be search
	@return Arraylist of products
	*/
	public ArrayList<product> searchByTrader(String trader){
		ArrayList<product> tempProduct = new ArrayList();
		try {
    	FileReader myObj = new FileReader("file.txt");
    	BufferedReader myReader = new BufferedReader(myObj);
    	int count = 0;

    	while (myReader.ready()){
    		String line = myReader.readLine();
    		int k = 0;
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
			if(itemTrader.contains(trader)){
				String id = new String(), name = new String(), description = new String();
				int price = 0, discountedPrice = 0;
	   			LinkedList<String> lltemp = new LinkedList();
	   			int i = 0;
	    		while(line.charAt(i) != ';'){
					id = id.concat(Character.toString(line.charAt(i)));
					i++;
				}
				i++;
				while(line.charAt(i) != ';'){
					name = name.concat(Character.toString(line.charAt(i)));
					i++;
				}
				i+=5;
				while(line.charAt(i) != '\"'){
					String str = new String();
					while(line.charAt(i) != '>' && line.charAt(i) != ']'){
						str = str.concat(Character.toString(line.charAt(i)));
						i++;
					}
					if(line.charAt(i) == ']'){
						str = str.substring(0, str.length() - 3);
						lltemp.add(new String(str));
						break;
					}
					lltemp.add(new String(str));
					i+=2;
				}
				i+=3;
				String str = new String();
				while(line.charAt(i) != ';'){
					str = str.concat(Character.toString(line.charAt(i)));
					i++;
				}
				price = Integer.parseInt(str);
				i++;
				str = new String();
				while(line.charAt(i) != ';'){
					str = str.concat(Character.toString(line.charAt(i)));
					discountedPrice = Integer.parseInt(str);
					i++;
				}
				i++;
				while(line.charAt(i) != ';'){
					description = description.concat(Character.toString(line.charAt(i)));
					i++;
				}
				tempProduct.add(new product(id, name, lltemp, price, discountedPrice, description, itemTrader));
			}
    	}

    	while (myReader.ready()){
    		String line = myReader.readLine();
    		int i = 0;

    		String id = new String(), name = new String(), description = new String(), tradertemp = new String();
			int price = 0, discountedPrice = 0;
   			LinkedList<String> lltemp = new LinkedList();
    		while(line.charAt(i) != ';'){
				id = id.concat(Character.toString(line.charAt(i)));
				i++;
			}
			i++;
			while(line.charAt(i) != ';'){
				name = name.concat(Character.toString(line.charAt(i)));
				i++;
			}
			i+=5;
			while(line.charAt(i) != '\"'){
				String str = new String();
				while(line.charAt(i) != '>' && line.charAt(i) != ']'){
					str = str.concat(Character.toString(line.charAt(i)));
					i++;
				}
				if(line.charAt(i) == ']'){
					str = str.substring(0, str.length() - 3);
					lltemp.add(new String(str));
					break;
				}
				lltemp.add(new String(str));
				i+=2;
			}
			i+=3;
			String str = new String();
			while(line.charAt(i) != ';'){
				str = str.concat(Character.toString(line.charAt(i)));
				i++;
			}
			price = Integer.parseInt(str);
			i++;
			str = new String();
			while(line.charAt(i) != ';'){
				str = str.concat(Character.toString(line.charAt(i)));
				discountedPrice = Integer.parseInt(str);
				i++;
			}
			i++;
			while(line.charAt(i) != ';'){
				description = description.concat(Character.toString(line.charAt(i)));
				i++;
			}
			i++;
			while(i != line.length()){
				tradertemp = tradertemp.concat(Character.toString(line.charAt(i)));
				i++;
			}
			if(!tradertemp.contains(trader))
				break;
			tempProduct.add(new product(id, name, lltemp, price, discountedPrice, description, tradertemp));
        }

      	myReader.close();
    	} catch (FileNotFoundException e) {
      		System.out.println("An error occurred.");
      		e.printStackTrace();
    	} catch (IOException e) {
	     	System.out.println("An error occurred.");
	      	e.printStackTrace();
	    }
	    return tempProduct;
	}

	/**
	This function provides surfing through categories then adds order by inputs from the user
	@param list list to be searched
	*/
	public void Categorize(ArrayList<product> list){
		int j = 0;
		while(true){
			System.out.printf("\n");
			ArrayList<String> temp = new ArrayList<String>();
			boolean end = false;
			for(int i = 0;i < list.size();i++){
				try{
					if(!temp.contains(list.get(i).getCategoryTree().get(j)))
						temp.add(list.get(i).getCategoryTree().get(j));
				}
				catch(IndexOutOfBoundsException e){
					System.out.printf("  Last Category!");
					end = true;
					break;
				}
			}
			System.out.printf("\n-1 )  END\n");
			if(!end)
				System.out.printf("0-)  CHOOSE FROM LIST TO PURCHASE\n");
			for(int i = 0;i < temp.size();i++)
				System.out.printf("%d-) %s\n", i + 1, temp.get(i));
			Scanner inp = new Scanner(System.in);
			int intTemp = 0;
			if(!end){
				intTemp = inp.nextInt();
				if(intTemp < 0 || intTemp > temp.size())
					break;
			}
			if(intTemp == -1)
				break;
			if(intTemp == 0){
				try {
					System.out.printf("\nCHOOSE NUMBER TO PURCHASE: ");
					intTemp = inp.nextInt();
					if(intTemp < 0 || intTemp > list.size())
						break;
					intTemp--;
					File myObj = new File("orders.txt");
					myObj.createNewFile();
					FileReader read = new FileReader("orders.txt");
					BufferedReader reader = new BufferedReader(read);
					String countertemp = null;
					while(reader.ready()){
						countertemp = reader.readLine();
					}
					String counter = new String();
					if(countertemp != null){
						int k = 0;
						while(countertemp.charAt(k) != ';'){
							counter = counter.concat(Character.toString(countertemp.charAt(k)));
							k++;
						}
						counter = String.valueOf(Integer.parseInt(counter) + 1);
					}
					reader.close();
					read.close();
					FileWriter writer = new FileWriter("orders.txt", true);
					if(countertemp != null)
						writer.write(counter);
					else
						writer.write("1");
					writer.write(";PENDING;");
					writer.write(list.get(intTemp).getId());
					writer.write(";");
					writer.write(getId());
					writer.write(";");
					writer.write(list.get(intTemp).getTrader());
					writer.write("\n");
					writer.close();
				} catch (FileNotFoundException e) {
		      		System.out.println("An error occurred.");
		      		e.printStackTrace();
		    	} catch (IOException e) {
			     	System.out.println("An error occurred.");
			      	e.printStackTrace();
			    }
			    System.out.printf("%s purchased successfully from %s!\n",list.get(intTemp).getName(), list.get(intTemp).getTrader());
				break;
			}
			else if (intTemp > 0)
				intTemp--;
			for(int i = 0;i < list.size();i++){
				if(!list.get(i).getCategoryTree().get(j).equals(temp.get(intTemp))){
					list.remove(i);
					i--;
				}					
			}
			System.out.printf("\n");
			for(int i = 0;i < list.size();i++){
				System.out.printf("%d-) ",i + 1);
				list.get(i).print();
			}
			j++;
		}
	}

	/**
	This function provides an upperbound/lowerbound by discounted prices of list of products
	@param upperbound Upperbound
	@param lowerbound Lowerbound
	@param list list to be bounded
	*/
	public void priceThreshold(int upperbound, int lowerbound, ArrayList<product> list){
		for(int i = 0;i < list.size();i++){
			if(list.get(i).getDiscountedPrice() < lowerbound || list.get(i).getDiscountedPrice() > upperbound){		//  fiyat threshold
				list.remove(i);
				i--;
			}
		}
	}

}