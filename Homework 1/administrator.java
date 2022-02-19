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
		Branches = new branch[5];
		for(int i = 0;i < 5;i++)
			Branches[i] = null;
	}

	/**
	*Adds a new branch
	*/

	public void addBranch(){
		if(branchNumber + 1 == Branches.length){
			branch[] tempBranches = new branch[Branches.length];
			for(int j = 0;j < Branches.length;++j)
				tempBranches[j] = Branches[j];
			Branches = new branch[Branches.length * 2];
			for(int j = 0;j < tempBranches.length;++j)
				Branches[j] = tempBranches[j];
		}
		Branches[branchNumber] = new branch();
		branchNumber++;
		System.out.printf("Branch %d is created.Don't forget to add branch employee too.\n",branchNumber);
	}

	/**
	*Removes the branch by the parameter 'number'
	*/

	public void removeBranch(int number){
		Branches[number - 1].branchEmployee = null;
		for(int i = number - 1;i < branchNumber;i++){
			Branches[i] = Branches[i + 1];
		}
		branchNumber--;
	}

	/**
	*Adds employee to last branch
	*@param Emp Branch Employee
	*/

	public void addBranchEmployee(branchemployee Emp){
		Branches[Emp.getBranchNo() - 1].setEmployee(Emp);
	}

	/**
	*Removes the Employee by the branch number
	*@param number Branch Number
	*/

	public void removeBranchEmployee(int number){
		Branches[number - 1].branchEmployee = null;
	}

	/**
	*Prints the needs of Branches
	*/

	public void seeBranchNeeds(){
		for(int i = 0;i < branchNumber;++i){
			if(Branches[i] != null)
				Branches[i].getNeeded();
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