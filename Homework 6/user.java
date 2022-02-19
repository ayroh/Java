import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Comparator;


public abstract class user{
	private String userId = null, password = null, name = null,type = null;

	public String getId(){
		return userId;
	}

	public String getPassword(){
		return password;
	}

	public String getName(){
		return name;
	}

	public String getType(){
		return type;
	}

	public void setId(String userID){
		userId = new String(userID);
	}

	public void setPassword(String Password){
		password = new String(Password);
	}

	public void setName(String Name){
		name = new String(Name);
	}

	public void setType(String Type){
		type = new String(Type);
	}

	/**
	This static function reads users.txt and returns Hashtable of it
	@return Hashtable of users at users.txt
	*/
	static public Hashtable<String, user> readUsers(){
		Hashtable<String, user> tempMap = new Hashtable();
    	try {
    		File myObj = new File("users.txt");
			myObj.createNewFile();
	    	FileReader myObje = new FileReader("users.txt");
	    	BufferedReader myReader = new BufferedReader(myObje);
	    	int count = 0;
			int i;
			while(myReader.ready()){
				String custId = new String(), custPass = new String(), custType = new String(), custName = new String();
		    	String line = myReader.readLine();
		    	for(i = 0;line.charAt(i) != ';';i++)
		    		custType = custType.concat(Character.toString(line.charAt(i)));
		    	i++;
		    	for(;line.charAt(i) != ';';i++)
		    		custName = custName.concat(Character.toString(line.charAt(i)));
		    	i++;
		    	for(;line.charAt(i) != ';';i++)
		    		custId = custId.concat(Character.toString(line.charAt(i)));
		    	i++;
		    	for(;i != line.length();i++)
		    		custPass = custPass.concat(Character.toString(line.charAt(i)));
		    	if(custType.contains("customer"))
		    		tempMap.put(custId, new customer(custId, custPass, custName));
		    	else if(custType.contains("trader"))
		    		tempMap.put(custId, new trader(custId, custPass, custName));
		    }
	      	myReader.close();
	      	myObje.close();
    	} catch (FileNotFoundException e) {
      		System.out.println("An error occurred.");
      		e.printStackTrace();
    	} catch (IOException e) {
	     	System.out.println("An error occurred.");
	      	e.printStackTrace();
	    }
	    System.out.printf("Users read successfully from users.txt!\n\n");
	    return tempMap;
    }


    /**
	This static function adds user to users.txt
	@param myUser user to be added
	@return True if added, false if that ID already in use
    */
    static public boolean addUser(user myUser){
    	if(myUser.getType() == null)
    		return false;
    	try {
	    	FileReader myObje = new FileReader("users.txt");
		    BufferedReader myReader = new BufferedReader(myObje);
		    int i;
		    while(myReader.ready()){
		    	String line = myReader.readLine();
		    	String custId = new String();
		    	for(i = 0;line.charAt(i) != ';';i++){}
		    	i++;
		    	for(;line.charAt(i) != ';';i++){}
		    	i++;
		    	for(;line.charAt(i) != ';';i++)
		    		custId = custId.concat(Character.toString(line.charAt(i)));
		    	if(custId.equals(myUser.getId()))
		    		return false;
		    }
		    myReader.close();
		    myObje.close();
		} catch (FileNotFoundException e) {
      		System.out.println("An error occurred.");
      		e.printStackTrace();
    	} catch (IOException e) {
	     	System.out.println("An error occurred.");
	      	e.printStackTrace();
	    }
    	try {
    		FileWriter writer = new FileWriter("users.txt", true);
			writer.write(myUser.getType());
			writer.write(';');
			writer.write(myUser.getName());
			writer.write(';');
			writer.write(myUser.getId());
			writer.write(';');
			writer.write(myUser.getPassword());
			writer.write('\n');
			writer.close();
    	} catch (IOException e) {
	     	System.out.println("An error occurred.");
	      	e.printStackTrace();
	    }
	    return true;
    }

    /**
	This Comparator is for PriorityQueue to sort file.txt by trader names
    */
	static public class traderComparator implements Comparator<String>{

	    /**
		 Overriding compare() method of Comparator for ascending order of Names
		 */
		@Override
	    public int compare(String s1, String s2) {

    		int i = 0;
    		String tradertemp1 = new String();
    		while(s1.charAt(i) != ';')
				i++;
			i++;
			while(s1.charAt(i) != ';')
				i++;
			i+=5;
			while(s1.charAt(i) != ']')
				i++;
			i+=3;
			while(s1.charAt(i) != ';')
				i++;
			i++;
			while(s1.charAt(i) != ';')
				i++;
			i++;
			while(s1.charAt(i) != ';')
				i++;
			i++;
			while(i != s1.length()){
				tradertemp1 = tradertemp1.concat(Character.toString(s1.charAt(i)));
				i++;
			}

			i = 0;
			String tradertemp2 = new String();
    		while(s2.charAt(i) != ';')
				i++;
			i++;
			while(s2.charAt(i) != ';')
				i++;
			i+=5;
			while(s2.charAt(i) != ']')
				i++;
			i+=3;
			while(s2.charAt(i) != ';')
				i++;
			i++;
			while(s2.charAt(i) != ';')
				i++;
			i++;
			while(s2.charAt(i) != ';')
				i++;
			i++;
			while(i != s2.length()){
				tradertemp2 = tradertemp2.concat(Character.toString(s2.charAt(i)));
				i++;
			}

	    	return tradertemp1.toLowerCase().compareTo(tradertemp2.toLowerCase());
		 }

	}

}