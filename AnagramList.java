import java.io.PrintWriter;
public class AnagramList
{
	private MyLinkedList list;
	private String letters;

	public static void main (String [] args)
	{
		AnagramList anagrams = new AnagramList("abcd");

		System.out.println("Letters are: "+anagrams.getLetters());
		System.out.println("abdc sorted is: "+selectionSort("cah"));
		System.out.println("Letters compareTo abdc is: "+ anagrams.getLetters().compareTo(selectionSort("abdc")));


		if (anagrams.add("abdc")) {
			System.out.println("dcab was added successfully");
		} else {
			System.out.println("Something is wrong, dcab should have been added!!!!!");
		}

		if (!anagrams.add("asff")) {
			System.out.println("asff was not added");
		}
	}
	public void print(PrintWriter writer)
	{
		list.print(writer);
	}

	public String getLetters()
	{
		return letters;
	}

	public AnagramList (String word)
	{
		list = new MyLinkedList(word);
		letters = selectionSort(word);
	}

	public boolean add (String s)
	{
		if(letters.compareTo(selectionSort(s)) == 0)
		{
			//System.out.println(s+" Sent for adding at: "+letters);
			list.add(s);
			return true;
		}
		else 
		{
			//System.out.println(s+" Denied membership at "+letters);
			return false;
		}
	}

	public static String selectionSort (String s)
	{
		char [] data = s.toCharArray();
		for (int i = 0 ; i < data.length - 1; i++) 
		{
			int min = findMin(i + 1, data);
			if (data [i] > data [min]) 
			{
				char temp = data [i];
				data [i] = data [min];
				data [min] = temp;
			}
		}
		String sorted = new String (data);
		return sorted;
	}
	/**
	*Returns the index of the smalles character in a string starting
	*at the indicated index.
	*
	*/
	public static int findMin(int start, char [] data)
	{
		char min = data[start];
		int minIndex = start;
		int i;
		for (i = start; i < data.length; i++) 
		{
			if (min > data[i]) 
			{
				min = data[i];
				minIndex = i;
			}
		}
		return minIndex;
	}
	public String getFirstWord()
	{
		return list.getHeadData();
	}
}