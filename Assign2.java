//Assign2.java
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.NullPointerException;
import java.io.FileNotFoundException;

public class Assign2
{
	private AnagramList [] storage;
	private int fillLine; // points to the next available spot;
	public int count;

	public static void main (String [] args) //args [0] is input file. args[1] is the output file
	{
		if (args.length < 2) //There must be at least 2 Command Line Args
		{
			System.out.println("Not enough arguments.");
			System.exit(-1);
		}
		long startTime = System.nanoTime(); //Start measuring time

		Assign2 anagrams = new Assign2 (20000); // Creating an in

		try //All the file is read whitin this try block
		{
			BufferedReader br = new BufferedReader(new FileReader(args[0]));
    		for(String line; (line = br.readLine()) != null; ) 
    		{
    			//System.out.println("Processing: "+line);
    			anagrams.add(line); // Each line from the input file 
    			anagrams.count++;
    			if (anagrams.count % 1000 == 0) {
    				System.out.println("Time to: "+anagrams.count+" elements is"+((startTime - System.nanoTime())/1000000000));
    			}
   			}
		}	catch(FileNotFoundException ex) { //in case the file file was not found
			System.out.println("File Not Found.");
		}	catch(IOException ex2) {
			System.out.println("IOExeption");
		}	catch (NullPointerException ex3) {
			System.out.println("Please enter the file to read from.");
		}

		anagrams.quickSort(); // Quicksorting the array of Linked lists. NOTE Elements are
							  //Added to the lists In Order, therefore there is no need to sort
							  // the lists themselves.

		try  { //Once the array of lists is sorted the contents of each linked list is printed.
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter (args[1])));
			anagrams.print(writer);
			writer.close();
		} catch (IOException e) {
			System.out.println("IOExeption while trying to create output file.");
		}
		long endTime = System.nanoTime(); //The Finish time
		anagrams.printTime(endTime - startTime); //The difference between the start and end is passed and 
											 	 // displayed on the screen.
	}

	/**
	*Prints to the screen the equivalent of a number of nanoseconds in hours, minutes, secons etc.
	*
	*
	*/
	public double printTime(long nanoTime)
	{
		double seconds = nanoTime / 1000000000;
		long microSeconds = nanoTime / 1000;
		nanoTime %= 1000;
		long milliSecond = microSeconds / 1000;
		microSeconds %= 1000;
		int second = (int) (milliSecond / 1000);
		milliSecond %= 1000;
		int minute = (int) (second / 60);
		second %= 60;
		int hour = minute / 60;
		minute %= 60;

		System.out.println ("\nFile processing time: ");
		System.out.println("Hours: " + hour);
		System.out.println("Minutes: " + minute);
		System.out.println("Seconds: "+ second);
		System.out.println("Milliseconds: "+ milliSecond);
		System.out.println("Microseconds: "+ microSeconds);
		System.out.println("Nanoseconds: " + nanoTime);

		return seconds;
	}
	public void print(PrintWriter writer) // Calls the print() method of each list in the array of lists
	{
		for (int i = 0; i < fillLine; i++) {
			storage[i].print(writer);
		}
	}

	public Assign2 (int startingSize) //Creates an instance of Assign2, the parameter is the starting size 
	{								  //of the array of linked lists
		if (startingSize <= 0) {
			System.out.println("Invalid starting size");
			System.exit(-1);
		}
		storage = new AnagramList [startingSize];
		fillLine = 0;
		count = 0;
	}

	public void add (String s) //This method takes a string and tries to add it to each list in the array
	{						   //If no list is found with a matching set of letters, a new list is created. 
		if (fillLine >= storage.length) //This block of code calls a method to double the size of the array of lists if it 
		{								//becomes full. 
			doubleStorage();
		}

		for (int i = 0; i <= fillLine; i++) //checking if the string s fits in any of the lists
		{
			if (i == fillLine) //no matching anagram found
			{
				fillLine++;
				storage [i] = new AnagramList(s);
				//System.out.println("No match: "+s+" created new list");
				break;
			} else if (storage[i].add(s)) { //NOTE: AnagramList.add() returns true if a word was added, else it returns false
				//System.out.println(s + " Added to: "+ storage[i].getLetters());
				break;
			}
		}
	}

	public void doubleStorage() //This method creates an array of twice the size of storage,
	{							// it then copies every element to the new array and sets 
								//the field storage to this new array
		AnagramList [] temp = new AnagramList [2 * storage.length]; //allocating the new array
		for (int i = 0; i < storage.length; i++) 
		{
			temp [i] = storage [i];	//copying every element
		}
		storage = temp; //changing the reference
		//System.out.println("Doubled storage to: "+  storage.length);
	}
	//Quicksort code adapted from code given out in lectures.
	public void quickSort()//Quicksorts the array of Lists
	{
		if (storage.length < 2) {
			return;
		}
		int max = 0;
		for (int i = 1; i < fillLine; i++) {
			if (storage[max].getFirstWord().compareTo(storage[i].getFirstWord()) < 0) {
				max = i;
			}
		}
		swap(max, fillLine -1);
		quickSort(0, fillLine - 2);
	}

	public void quickSort(int first, int last)
	{
		int lower = first + 1, upper = last;
		swap(first, (first + last) / 2);
		String bound = storage[first].getFirstWord();
		while(lower <= upper)
		{
			while (storage[lower].getFirstWord().compareTo(bound) < 0)
				lower++;
			while(bound.compareTo(storage[upper].getFirstWord()) < 0)
				upper--;
			if (lower < upper)
				swap(lower++, upper--);
			else 
				lower++;
		}
		swap(upper, first);
		if (first < upper -1)
			quickSort(first, upper -1);
		if (upper + 1 < last)
			quickSort(upper + 1, last);
	}

	public void swap(int a, int b) //Swaps two elemtns in the array of Lists
	{
		AnagramList temp = storage[a];
		storage[a] = storage[b];
		storage[b] = temp; 
	}

}