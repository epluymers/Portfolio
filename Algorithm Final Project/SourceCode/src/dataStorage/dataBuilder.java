//==============================================================================
/**
 * File Name:   dataBuilder.java
 *
 * Written By: Elicia Pluymers
 * 2023 CS Capstone Project AiAlgorithms
 **/
//==============================================================================

package dataStorage;

/*
 * Import Staments
 */
import java.io.*;
import java.util.Scanner;

import algorithmData.algorithmException;
import userInterface.programLoader;

/*
 * Class: dataBuilder
 * Package: dataStorage
 * Description: Builder Class which uses abstract data factory to generate the data for program from user defined file.
 */
public class dataBuilder {

	/*
	 * Local CLass Variables
	 */
	private dataFactory factory;
	
	/*
	 * Constructor
	 * 
	 * Input - dataFile - File selected by user containing all the information to generate the data
	 */
	public dataBuilder(File dataFile, String dataType) throws FileNotFoundException
	{
		if(dataType.equals("Graph"))
		{
			factory = new graphFactory();
		}
		else
		{
			factory = new queensFactory();
		}
		
		Scanner fileScanner = new Scanner(dataFile);
		
		boolean endFound = true;
		while (fileScanner.hasNextLine())
		{
			String line = fileScanner.nextLine();
			if(line.contains("//"))
			{
				int commentLocation = line.indexOf("//");
				line = line.substring(0, commentLocation).trim();
			}
			if(line.contains("/*") || !endFound)
			{
				if(line.contains("*/") && line.contains("/*"))
				{
					int commentLocationStart = line.indexOf("/*");
					int commentLocationEnd = line.indexOf("*/");
					line = line.substring(commentLocationStart+2, commentLocationEnd).trim();
				}
				else if(line.contains("*/"))
				{
					endFound = true;
					int commentLocation = line.indexOf("*/");
					line = line.substring(commentLocation+2).trim();
					
				}
				else if(line.contains("/*"))
				{
					endFound = false;
					int commentLocation = line.indexOf("/*");
					line = line.substring(0, commentLocation).trim();
				}
				else
				{
					line = "";
				}
				
			}
			factory.parseLine(line);
		}
		
		fileScanner.close();
	}
	
	/*
	 * Function: getDataObject
	 * Description: Returns the generated data object
	 * 
	 * Return - abstractData: Generated data object
	 */
	public abstractData getDataObject() throws dataException
	{
		abstractData data = factory.getData();
		return data;
	}
	
	/*
	 * Function: getImageObject
	 * Description: Returns the generated image object
	 * 
	 * Return - imageInterface: Generated image object
	 */
	public imageInterface getImageObject()
	{
		return factory.getImage();
	}
}
