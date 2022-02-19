import java.util.*;

public class sorts 
{

	////////////////////////////////////////////////////////////////////////////

	public static void insertionSortDecreasing(ArrayList<product> list)
	{
		for(int nextPos = 1; nextPos < list.size(); nextPos++)
		{
			product nextVal = list.get(nextPos);

			while(nextPos > 0 && nextVal.getName().toLowerCase().compareTo(list.get(nextPos - 1).getName().toLowerCase()) > 0){
				list.set(nextPos, list.get(nextPos - 1));
				nextPos--;
			}
			list.set(nextPos, nextVal);
		}
	}

	public static void insertionSortIncreasing(ArrayList<product> list)
	{
		for(int nextPos = 1; nextPos < list.size(); nextPos++)
		{
			product nextVal = list.get(nextPos);

			while(nextPos > 0 && nextVal.getName().toLowerCase().compareTo(list.get(nextPos - 1).getName().toLowerCase()) < 0){
				list.set(nextPos, list.get(nextPos - 1));
				nextPos--;
			}
			list.set(nextPos, nextVal);
		}
	}

	////////////////////////////////////////////////////////////////////////////
	
	public static void bubbleSortIncreasing(ArrayList<product> list)
	{	
		boolean extracted = true;
		
		while(extracted)
		{
			boolean check = false;

			for(int i = 0; i < list.size() - 1; i++)
			{
				if(list.get(i).getDiscountPercentage() > list.get(i + 1).getDiscountPercentage())
				{
					product temp = list.get(i);
					list.set(i, list.get(i + 1));
					list.set(i + 1, temp);
					check = true;
				}
			}
			if(check == false)	extracted = false;
		}
	}

	public static void bubbleSortDecreasing(ArrayList<product> list)
	{	
		boolean extracted = true;
		
		while(extracted)
		{
			boolean check = false;

			for(int i = 0; i < list.size() - 1; i++)
			{
				if(list.get(i).getDiscountPercentage() < list.get(i + 1).getDiscountPercentage())
				{
					product temp = list.get(i);
					list.set(i, list.get(i + 1));
					list.set(i + 1, temp);
					check = true;
				}
			}
			if(check == false)	extracted = false;
		}
	}

	////////////////////////////////////////////////////////////////////////////

	public static void selectionSortIncreasing(ArrayList<product> list)
	{
		Integer postMin;

		for(int i = 0; i < list.size() - 1; i++)
		{
			postMin = i;

			for(int j = i + 1; j < list.size(); j++){
				if(list.get(postMin).getDiscountedPrice() > list.get(j).getDiscountedPrice()){
					postMin = j;
				}
			}
			product temp = list.get(i);
			list.set(i, list.get(postMin));
			list.set(postMin, temp);
		}	
	}

	public static void selectionSortDecreasing(ArrayList<product> list)
	{
		Integer postMin;

		for(int i = 0; i < list.size() - 1; i++)
		{
			postMin = i;

			for(int j = i + 1; j < list.size(); j++){
				if(list.get(postMin).getDiscountedPrice() < list.get(j).getDiscountedPrice()){
					postMin = j;
				}
			}
			product temp = list.get(i);
			list.set(i, list.get(postMin));
			list.set(postMin, temp);
		}	
	}

}