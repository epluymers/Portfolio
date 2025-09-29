//==============================================================================
/**
 * File Name:   depthFirstSearchQ.java
 *
 * Written By: Elicia Pluymers
 * 2023 CS Capstone Project AiAlgorithms
 **/
//==============================================================================

package algorithmData;

/*
 * Import Statements
 */
import dataStorage.abstractData;
import dataStorage.queensData;

/*
 * Class: depthFirstSearchQ
 * Package: algorithmData
 * Implements: algorithmInterface
 * Description: Implements Depth First Search Algorithm for Queens Data
 */
public class depthFirstSearchQ implements algorithmInterface
{
	/*
	 * Local Class Variables
	 */
	private queensData myData;
	private int iterationsCount;
	private int boardSize;
	private int currentQueen;
	private boolean moveDown;
	
	/*
	 * Constructor
	 * 
	 * Input - data - abstractData type which is cast to the needed dataType and stored 
	 */
	public depthFirstSearchQ(abstractData data)
	{
		myData = (queensData)data;
		iterationsCount = 0;
		boardSize = myData.getBoardSize();
		currentQueen = 4;
		moveDown = false;
	}
	
	/*
	 * Function: nextIteration
	 * Description: Utilize the function calls available in queensData to run one iteration of the algorithm.
	 * 
	 * Return - int:  -1: Algorithm Failed to Find Solution
	 * 				   0: Algorithm Has Not Failed Nor Reached It's Goal
	 * 				   1: Algorithm Has Reached Goal State
	 * 
	 * Throws - AlgorithmException: Notifies User that the algorithm is has not yet been defined and still needs to be written.
	 */
	public int nextIteration() throws algorithmException
	{
		//Comment out the throw once the function has been written.
		//throw new algorithmException("This algorithm had not been created. Please write code for the algorithm before using.");
		iterationsCount++;
		
		if(myData.goalTest())
		{
			return 1;
		}
		if(iterationsCount > Math.pow(boardSize, boardSize))
		{
			return -1;
		}
		
		if(moveDown == true)
		{
			currentQueen--;
			moveDown = false;
		}
		else if(currentQueen != boardSize)
		{
			currentQueen = boardSize;
		}
		
		int xCurrent = myData.getQueenX(currentQueen);
		if(xCurrent == boardSize)
		{
			moveDown = true;
		}
		
		if(xCurrent == boardSize)
		{
			xCurrent = 0;
		}
		myData.moveQueen(currentQueen, xCurrent + 1, myData.getQueenY(currentQueen));
		return 0;
	}
	
	/*
	 * Function: getTotalIterations
	 * Description: Returns Number of Iteractions the algorithm has gone through at the point the function has been called
	 * 
	 * Return - int:  numberOfIterations
	 */
	public int getTotalIterations()
	{
		return iterationsCount;
	}
}