import java.util.ListIterator;
/**
 *
 *Administrator extends from user
 *@author Bilal Yalçınkaya
 *@version JDK 8 - Cygwin 64 Terminal
 *
 */

public class administrator extends user{

	static boolean message = false;

	public administrator(String name, String surname, String password){
		super(name,surname,password);
		userType = 1;
		Branches = new KWLinkedList<branch>();
	}

	/**
	*Adds a new branch
	*/

	public void addBranch(){
		Branches.add(branchNumber, new branch());
		branchNumber++;
		System.out.printf("Branch %d is created.Don't forget to add branch employee too.\n",branchNumber);
	}

	/**
	*Removes the branch by the parameter 'number'
	*/

	public void removeBranch(int number){
		number--;
		if(number < 0 || number >= branchNumber)
			throw new IndexOutOfBoundsException();
		Branches.remove(number);
		branchNumber--;
	}

	/**
	*Adds employee to last branch
	*@param Emp Branch Employee
	*/

	public void addBranchEmployee(branchemployee Emp){
		Branches.get(Emp.getBranchNo() - 1).setEmployee(Emp);
	}

	/**
	*Removes the Employee by the branch number
	*@param number Branch Number
	*/

	public void removeBranchEmployee(int number){
		Branches.get(number - 1).branchEmployee = null;
	}

	/**
	*Prints the needs of Branches
	*/

	public void seeBranchNeeds(){
		ListIterator<branch> Iter = Branches.ListIterator(0);
		while(Iter.hasNext()){
			Iter.next().getNeeded();
		}
	}

	/**
	*Prints the message that some branch needs products
	*/

	public void getMessage(){
		if(message)
			System.out.printf("THERE IS A BRANCH THAT NEEDS PRODUCTS!\n");
	}

	static void setMessage(boolean choice){
		message = choice;
	}
   
}