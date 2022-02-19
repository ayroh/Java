
/**
 *
 *
 *@author Bilal Yalçınkaya
 *@version JDK 8 - Cygwin 64 Terminal
 *
 */

public class chair implements furniture{
	public static String model = "chair";
	private int modelNo;
	private double Price;
	private String[] colors;
	private int colorNumber;

	chair(int modelno, double price){
		modelNo = modelno;
		setPrice(price);
		colors = new String[4];
		colors[0] = "Red";
		colors[1] = "Green";
		colors[2] = "Black";
		colors[3] = "White";
		colorNumber = 4;
	}

	public int getModelNo(){
		return modelNo;
	}

	public String getModel(){
		return model;
	}

	public void setPrice(double price){
		Price = price;
	}

	public double getPrice(){
		return Price;
	}

	public boolean equals(furniture obj){
		if(obj.getModelNo() == modelNo && obj.getModel().equals(model))
			return true;
		return false;
	}

	public void seeProperties(){
		System.out.printf("\n%s Model No: %d\n",model, modelNo);
		System.out.printf("Colors: ");
		for (String var : colors){ 
			System.out.printf(" %s ",var);    
		}
		System.out.printf("\n");
	}

	public String getColor(int choice){
		return colors[choice - 1];
	}

	public void seeColors(){
		System.out.printf("\n");
		for(int i = 0;i < colorNumber;i++)
			System.out.printf("%d:   %s\n",i+1,colors[i]);
	}
}