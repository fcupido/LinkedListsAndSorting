//AnagramList.java
import java.io.PrintWriter;
/**
*The AnagramList is a class that manages a LinkedList.
*Most importantly, the anagraList will only allow new elements if they are anagrams of the first
*element that was added to the list.
*@author Felipe Cupido
*/

public class AnagramList
{
	private MyLinkedList list;
	/**
	*The linked list that will be used to store the anagrams.
	*/

	private String letters;
	/**
	*The characterestic set of letters for this list. They are the letters of the first
	*word added to the list sorted in lexicographical order.
	*New elements will only be allowed if they 
	*are composed of the same set of letters
	*/

	/**
	*The main method of the AnagramList class allows for the
	*testing of the AnagramList class and methods.
	*/
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
	public void print(PrintWriter writer)//Calls the LinkedList print() method.
	{
		list.print(writer);
	}

	public String getLetters()//Returns the characteristic set of letters.
	{
		return letters;
	}

	/**
	*Constructor of AnagramList
	*@param word The first word to be added to the list.
	*/
	public AnagramList (String word) //Creates an instance of AnagramList and sets the 
	{								 //characteristec set of letters
		list = new MyLinkedList(word);
		letters = selectionSort(word);
	}

	public boolean add (String s)//Adds a word to the list ONLY IF it is an anagram of the first word.
	{							 //IF a word was added to the list this method will return true.
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

	public static String selectionSort (String s) //Returns the characters of s sorted in aphabetic order.
	{
		char [] data = s.toCharArray(); //First we convert s into an array of chars.
		for (int i = 0 ; i < data.length - 1; i++) 
		{
			int min = findMin(i + 1, data); //we then find the index of the character with the lieast ASCII value
			if (data [i] > data [min])  // that is in the subArray comprizing the element after the one we are on untill the end
			{							//IF the minimum is less that the one we are currently on, we swap them
				char temp = data [i];
				data [i] = data [min];
				data [min] = temp;
			}
		}
		String sorted = new String (data); //We convert the array of characters back into a string
		return sorted; //We then return the string
	}
	/**
	*Returns the index of the smalles character in a string starting
	*at the indicated index.
	*@param start the first index to look at
	*@param data the array of data to look at
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
	public String getFirstWord()//Returns the word in the first node in the list.
	{
		return list.getHeadData();
	}
}