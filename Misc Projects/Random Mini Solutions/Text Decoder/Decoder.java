import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Decoder {
	public static void main(String[] args)
	{
		File message_file = new File("message_file.txt");
		System.out.println(decode(message_file));
	}
	
	public static String decode(File message_file)
	{
		
		ArrayList<String> sortedWords = processFile(message_file);
		String decodedMessage = "";
		int row = 1;
		int index = 0;
		while(sortedWords.size() > index)
		{
			decodedMessage = sortedWords.get(index) + " ";
			row++;
			index = index + row;
		}
		
		return decodedMessage;
	}
		
	public static ArrayList<String> processFile(File message_file)
	{
		ArrayList<String> words = new ArrayList<String>();
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		try
		{
		  Scanner fileScanner = new Scanner(message_file);
		  while(fileScanner.hasNextLine())
		  {
		    String line = fileScanner.nextLine();
		    int index = line.indexOf(" ");
		    numbers.add(Integer.parseInt(line.substring(0, index)));
		    words.add(line.substring(index).trim());
		    System.out.println(line);
		  }
		  fileScanner.close();
		  
		  ArrayList<String> sortedWords = new ArrayList<String>();
		  int lowestNumber = 1;
		  while(!words.isEmpty())
		  {
			  int lowestIndex = numbers.indexOf(lowestNumber);
			  if(lowestIndex != -1)
			  {
				  sortedWords.add(words.get(lowestIndex));
				  words.remove(lowestIndex);
				  numbers.remove(lowestIndex);
			  }
		  }
		  
		  return sortedWords;
		}
		catch(FileNotFoundException e)
		{
			System.out.println("The file message_file.txt could not be found.");
			return null;
		}
	}
}
