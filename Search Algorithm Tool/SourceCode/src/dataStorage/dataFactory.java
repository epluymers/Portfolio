//==============================================================================
/**
 * File Name:   dataStorage.java
 *
 * Written By: Elicia Pluymers
 * 2023 CS Capstone Project AiAlgorithms
 **/
//==============================================================================

package dataStorage;

/*
 * Class: dataFactory
 * Package: dataStorage
 * Description: Abstract Factory definition used by builder to parse data without needing to know the type of data it is handling.
 */
public abstract class dataFactory 
{
	/*
	 * Function: parseLine
	 * Details: Extracts data from a line of input text and calls functions in data to generate the data it holds
	 * 
	 * Input - nextLine: Line of text in String format
	 */
	abstract public void parseLine(String nextLine);
	
	/*
	 * Abstract Function: getData
	 * Description: Returns the generated data object
	 * 
	 * Return - abstractData: Generated data object
	 */
	abstract public abstractData getData() throws dataException;
	
	/*
	 * Function: getImage
	 * Description: Returns the generated image object
	 * 
	 * Return - imageInterface: Generated image object
	 */
	abstract public imageInterface getImage();
}
