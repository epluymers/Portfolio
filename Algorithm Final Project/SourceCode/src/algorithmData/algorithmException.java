//==============================================================================
/**
 * File Name:   algorithmException.java
 *
 * Written By: Elicia Pluymers
 * 2023 CS Capstone Project AiAlgorithms
 **/
//==============================================================================

package algorithmData;

/*
 * Class: algorithmException
 * Package: algorithmData
 * Extends: Exception
 * Description: custom Exception thrown by the classes in this package
 */
public class algorithmException extends Exception
{
	/*
	 * Constructor
	 * 
	 * Input - newMessage - Exception details to be passed to error handler
	 */
	public algorithmException(String newMessage)
	{
		super(newMessage);
	}
}
