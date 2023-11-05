//==============================================================================
/**
 * File Name:   programLoader.java
 *
 * Written By: Elicia Pluymers
 * 2023 CS Capstone Project AiAlgorithms
 **/
//==============================================================================

package userInterface;

/*
 * Import Statements
 */
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import dataStorage.dataBuilder;
import dataStorage.imageInterface;
import dataStorage.abstractData;
import dataStorage.dataException;
import algorithmData.algorithmBuilder;
import algorithmData.algorithmInterface;

/*
 * Class: programLoader
 * Package: userInterface
 * Description: Program Main and Static methods to create windows.
 */
public class programLoader
{
	/*
	 * Program Main
	 */
	public static void main(String[] args)
	{
		createFileLoader();
	}
	
	/*
	 * Static Function: createFileLoader
	 * Description: Create instance of GUIFileLoader and calls it to create to loader screen
	 */
	public static void createFileLoader()
	{
		GUIFileLoader currentView = new GUIFileLoader();
		currentView.createLoaderScreen();
	}
	
	/*
	 * Static Function: createUserView
	 * Description: Generates data, image and algorithm and sends data to instance of GUIUserView
	 * 
	 * Input - data: File selected by user containing data
	 * Input - dataType: String containing dataType selected by user
	 * Input - algorithmType: String containing algorithmType selected by user
	 */
	public static void createUserView(File data, String dataType, String algorithmType)
	{
		dataBuilder builder;
		try
		{
			builder = new dataBuilder(data, dataType);
			abstractData currentData = builder.getDataObject();
			imageInterface dataImage = builder.getImageObject();
			algorithmBuilder algoBuilder = new algorithmBuilder(dataType, algorithmType, currentData);
			algorithmInterface currentAlgorithm = algoBuilder.getAlgorithm();
			GUIUserView currentView = new GUIUserView(dataImage, currentAlgorithm);
			currentView.createUserViewScreen();
		}
		catch (FileNotFoundException e)
		{
			errorMessage("The file you chose no longer exists. \nPlease choose and alternate file.");
			createFileLoader();
		}
		catch (dataException e)
		{
			errorMessage("There is an issue with the input data file. \nPlease review the guidelines and try again.");
		}
		catch (Exception e)
		{
			errorMessage("An issue caused the program to close unexpectedly. \nPlease try again.");
		}
	}
	
	/*
	 * Static Function: errorMessage
	 * Description: Sends error message based on the failure that caused the close.
	 * 
	 * Input - message: Error string sent from Exception
	 */
	public static void errorMessage(String message)
	{
		JFrame errorMessage = new JFrame();
		errorMessage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JLabel messageString = new JLabel(message);
		errorMessage.add(messageString);
		errorMessage.pack();
		errorMessage.setLocationRelativeTo(null);
		errorMessage.setVisible(true);
	}
}
