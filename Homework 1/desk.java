public class desk implements furniture{
	public static String model = "desk";
	private int modelNo;
	private double Price;
	private String[] colors;
	private int colorNumber;

	desk(int modelno, double price){
		modelNo = modelno;
		setPrice(price);
		colors = new String[3];
		colors[0] = "Orange";
		colors[1] = "Green";
		colors[2] = "White";
		colorNumber = 3;
	}

	public int getModelNo(){
		return modelNo;
	}

	public void setPrice(double price){
		Price = price;
	}

	public double getPrice(){
		return Price;
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