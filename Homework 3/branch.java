/**
 *
 *
 *@author Bilal Yalçınkaya
 *@version JDK 8 - Cygwin 64 Terminal
 *
 */

public class branch{
	public administrator Administrator = null;
	public branchemployee branchEmployee = null;
	private String Needs = "";
	private int branchNumber;
	public int minimumRequested = 4;
	public int SalePercentage = 0;
	private int cupNumber = 0;
	private int bookcaseNumber = 0;
	private int chairNumber = 0;
	private int deskNumber = 0;
	public HybridList<furniture> Furnitures = new HybridList<furniture>();


	/**
	*Creates new furnitures 5 each,  and increments branchNumber of 'user' class
	*/

	public branch(){
		branchNumber = user.branchNumber + 1;			// +1 because index starts at 0
	}	

	public int getBranchNumber(){
		return branchNumber;
	}

	public int getChairNumber(){
		return chairNumber;
	}
	public void addChairNumber(){
		chairNumber++;
		setNeeded();
	}
	public void removeChairNumber(){
		chairNumber--;
		setNeeded();
	}

	public int getDeskNumber(){
		return deskNumber;
	}
	public void addDeskNumber(){
		deskNumber++;
		setNeeded();
	}
	public void removeDeskNumber(){
		deskNumber--;
		setNeeded();
	}

	public int getBookcaseNumber(){
		return bookcaseNumber;
	}
	public void addBookcaseNumber(){
		bookcaseNumber++;
		setNeeded();
	}
	public void removeBookcaseNumber(){
		bookcaseNumber--;
		setNeeded();
	}

	public int getCupNumber(){
		return cupNumber;
	}
	public void addCupNumber(){
		cupNumber++;
		setNeeded();
	}
	public void removeCupNumber(){
		cupNumber--;
		setNeeded();
	}

	public void setEmployee(branchemployee Emp){
		branchEmployee = Emp;
	}

	public int getSalePercentage(){
		return SalePercentage;
	}

	/**
	*Set needs to String, based on Minimum Requested of branch
	*/

	public void setNeeded(){
		Needs = "";
		String[] str = new String[5];
		for(int i = 0;i < 5;++i)
			str[i] = null;
		str[0] = String.format("Branch %d, Minimum Requested = %d.\n", branchNumber, minimumRequested);

		if(getChairNumber() < minimumRequested){
			str[1] = String.format("     Chair: %d available, %d needed.\n", getChairNumber(), minimumRequested - getChairNumber());
			branchEmployee.informManager();
		}
		else
			str[1] = String.format("     Chair: %d available, no needed.\n", getChairNumber());

		if(getDeskNumber() < minimumRequested){
			str[2] = String.format("     Desk: %d available, %d needed.\n", getDeskNumber(), minimumRequested - getDeskNumber());
			branchEmployee.informManager();
		}
		else
			str[2] = String.format("     Desk: %d available, no needed.\n", getDeskNumber());

		if(getCupNumber() < minimumRequested){
			str[3] = String.format("     Cup: %d available, %d needed.\n", getCupNumber(), minimumRequested - getCupNumber());
			branchEmployee.informManager();
		}
		else
			str[3] = String.format("     Cup: %d available, no needed.\n", getCupNumber());

		if(getBookcaseNumber() < minimumRequested){
			str[4] = String.format("     Bookcase: %d available, %d needed.\n", getBookcaseNumber(), minimumRequested - getBookcaseNumber());
			branchEmployee.informManager();
		}
		else
			str[4] = String.format("     Bookcase: %d available, no needed.\n", getBookcaseNumber());

		for(int i = 0;i < 5;++i)
			if(str[i] != null)
				Needs = Needs.concat(str[i]);
	}

	/**
	*Prints needs of branch
	*/

	public void getNeeded(){
		if(!Needs.equals(""))
			System.out.printf(Needs);
	}

}