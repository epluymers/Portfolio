//==============================================================================
/**
 * File Name:   queensFactory.java
 *
 * Written By: Elicia Pluymers
 * 2023 CS Capstone Project AiAlgorithms
 **/
//==============================================================================

package dataStorage;

/*
 * Class: queensFactory
 * Package: dataStorage
 * Extends: dataFactory
 * Description: Factory parses input and generates data for Queens Data
 */
public class queensFactory extends dataFactory
{
	/*
	 * Local Class Variables
	 */
	private queensData data;
	
	/*
	 * Constructor
	 */
	public queensFactory()
	{
		data = new queensData();
	}
	
	/*
	 * Function: parseLine
	 * Details: Extracts data from a line of input text and calls functions in queensData to generate the data it holds
	 * 
	 * Input - nextLine: Line of text in String format
	 */
	public void parseLine(String nextLine)
	{
		if(nextLine.contains("GridSize:"))
		{
			int loc = nextLine.indexOf("GridSize:");
			nextLine = (nextLine.substring(loc+10)).trim();
			int size = Integer.parseInt(nextLine.trim());
			setBoardSize(size);
		}
		else if(nextLine.contains("Queen:"))
		{
			int loc = nextLine.indexOf("Queen:");
			nextLine = (nextLine.substring(loc+7)).trim();
			loc = nextLine.indexOf(" ");
			int xLoc = Integer.parseInt((nextLine.substring(0, loc)).trim());
			nextLine = nextLine.substring(loc+1).trim();
			int yLoc = Integer.parseInt(nextLine.trim());
			addQueen(xLoc, yLoc);
		}
	}
	
	/*
	 * Private Function: addQueen
	 * Description: Sends data to queensData to add a new Queen
	 * 
	 * Input - x: x Location of new Queen
	 * Input - y: y Location of new Queen
	 */
	private void addQueen(int x, int y)
	{
		data.addQueen(x, y);
	}
	
	/*
	 * Private Function: setBoardSize
	 * Description: Sends data to queensData to setBoardSize
	 * 
	 * Input - size: size of board
	 */
	private void setBoardSize(int size)
	{
		data.setBoardSize(size);
	}
	
	/*
	 * Function: getData
	 * Description: Returns the generated queensData object
	 * 
	 * Return - abstractData: Generated queensData object
	 */
	public abstractData getData() throws dataException
	{
		if(!data.validateData())
		{
			throw new dataException("The queens data entered is not valid. Please review guidlines and on example data.");
		}
		return data;
	}
	
	/*
	 * Function: getImage
	 * Description: Returns the generated image object
	 * 
	 * Return - imageInterface: Generated image object
	 */
	public imageInterface getImage()
	{
		imageInterface image = new queenImage(data);
		return image;
	}
}
