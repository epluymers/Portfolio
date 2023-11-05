//==============================================================================
/**
 * File Name:   greedyAlgorithmQ.java
 *
 * Written By: Elicia Pluymers
 * 2023 CS Capstone Project AiAlgorithms
 **/
//==============================================================================

package algorithmData;

import java.util.Random;

/*
 * Import Statements
 */
import dataStorage.abstractData;
import dataStorage.queensData;

/*
 * Class: greedyAlgorithmQ
 * Package: algorithmData
 * Implements: algorithmInterface
 * Description: Implements Greedy Algorithm for Queens Data
 */
public class greedyAlgorithmQ implements algorithmInterface
{
	/*
	 * Local Class Variables
	 */
	private queensData myData;
	private int iterationsCount;
	private int boardSize;
	private int maxConflictQueen;
	
	/*
	 * Constructor
	 * 
	 * Input - data - abstractData type which is cast to the needed dataType and stored 
	 */
	public greedyAlgorithmQ(abstractData data)
	{
		myData = (queensData)data;
		iterationsCount = 0;
		boardSize = myData.getBoardSize();
		maxConflictQueen = 1;
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
		
		int originalQueen = maxConflictQueen;
		
		int maxConflicts = myData.countConflicts(maxConflictQueen);
		for(int i = 2; i <= boardSize; i++)
		{
			if(myData.countConflicts(i) > maxConflicts)
			{
				maxConflictQueen = i;
				maxConflicts = myData.countConflicts(i);
			}
		}
		
		if(originalQueen == maxConflictQueen)
		{
			Random rand = new Random();
			maxConflictQueen = rand.nextInt(4) + 1;
		}
		
		int originalX = myData.getQueenX(maxConflictQueen);
		
		int minConflicts = myData.countConflicts(maxConflictQueen);
		int minConflictsX = myData.getQueenX(maxConflictQueen);
		for(int i = 1; i <= boardSize; i++)
		{
			myData.moveQueen(maxConflictQueen, i, myData.getQueenY(maxConflictQueen));
			if(myData.countConflicts(maxConflictQueen) < minConflicts)
			{
				minConflicts = myData.countConflicts(maxConflictQueen);
				minConflictsX = myData.getQueenX(maxConflictQueen);
			}
		}
		if(originalX == minConflictsX)
		{
			if(minConflictsX == boardSize)
			{
				minConflictsX = 0;
			}
			minConflictsX++;
		}
		myData.moveQueen(maxConflictQueen, minConflictsX, myData.getQueenY(maxConflictQueen));
		return 0;
	}
	
	/*
	 * Function: getTotalIterations
	 * Description: Returns Number of Iterations the algorithm has gone through at the point the function has been called
	 * 
	 * Return - int:  numberOfIterations
	 */
	public int getTotalIterations()
	{
		return iterationsCount;
	}
}
