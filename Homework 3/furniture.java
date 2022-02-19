/**
 *
 *interface of all furnitures
 *@author Bilal Yalçınkaya
 *@version JDK 8 - Cygwin 64 Terminal
 *
 */

public interface furniture{
	/**
	*Prints Model No and Colors of a Furniture
	*/
	public void seeProperties();
	/**
	*Prints Colors of a Furniture
	*/
	public void seeColors();

	public int getModelNo();

	public String getModel();

	public String getColor(int choice);

	public double getPrice();

	public boolean equals(furniture obj);
}