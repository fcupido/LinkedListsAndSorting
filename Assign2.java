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
	AnagramList [] storage;
	int fillLine; // points to the next available spot;

	public static void main (String [] args)
	{
		if (args.length < 2) 
		{
			System.out.println("Not enough arguments.");
			System.exit(-1);
		}
		long startTime = System.nanoTime();

		Assign2 anagrams = new Assign2 (20000);

		try(BufferedReader br = new BufferedReader(new FileReader(args[0]))) 
		{
    		for(String line; (line = br.readLine()) != null; ) 
    		{
    			//System.out.println("Processing: "+line);
    			anagrams.add(line);
   			}
		}	catch(FileNotFoundException ex) {
			System.out.println("File Not Found.");
		}	catch(IOException ex2) {
			System.out.println("IOExeption");
		}	catch (NullPointerException ex3) {
			System.out.println("Please enter the file to read from.");
		}

		anagrams.quickSort();

		try  {
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter (args[1])));
			anagrams.print(writer);
			writer.close();
		} catch (IOException e) {
			System.out.println("IOExeption while trying to create output file.");
		}
		long endTime = System.nanoTime();
		anagrams.printTime(endTime - startTime);
	}
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
	public void print(PrintWriter writer)
	{
		for (int i = 0; i < fillLine; i++) {
			storage[i].print(writer);
		}
	}

	public Assign2 (int startingSize)
	{
		if (startingSize <= 0) {
			System.out.println("Invalid starting size");
			System.exit(-1);
		}
		storage = new AnagramList [startingSize];
		fillLine = 0;
	}

	public void add (String s)
	{
		if (fillLine >= storage.length) 
		{
			doubleStorage();
		}

		for (int i = 0; i <= fillLine; i++) 
		{
			if (i == fillLine) //no matching anagram found
			{
				fillLine++;
				storage [i] = new AnagramList(s);
				//System.out.println("No match: "+s+" created new list");
				break;
			} else if (storage[i].add(s)) {
				//System.out.println(s + " Added to: "+ storage[i].getLetters());
				break;
			}
		}
	}

	public void doubleStorage()
	{
		
		AnagramList [] temp = new AnagramList [2 * storage.length];
		for (int i = 0; i < storage.length; i++) 
		{
			temp [i] = storage [i];	
		}
		storage = temp;
		System.out.println("Doubled storage to: "+  storage.length);
	}

	public void quickSort()
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

	public void swap(int a, int b)
	{
		AnagramList temp = storage[a];
		storage[a] = storage[b];
		storage[b] = temp; 
	}

}