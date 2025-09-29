//==============================================================================
/**
 * File Name:   dataException.java
 *
 * Written By: Elicia Pluymers
 * 2023 CS Capstone Project AiAlgorithms
 **/
//==============================================================================

package dataStorage;

/*
 * Class: dataException
 * Package: dataStorage
 * Extends: Exception
 * Description: custom Exception thrown by the classes in this package
 */
public class dataException extends Exception
{
	/*
	 * Constructor
	 * 
	 * Input - newMessage - Exception details to be passed to error handler
	 */
	public dataException(String newMessage)
	{
		super(newMessage);
	}
}
