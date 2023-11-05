//==============================================================================
/**
 * File Name:   simulatedAnnealingG.java
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
import dataStorage.graphData;

/*
 * Class: simulatedAnnealingG
 * Package: algorithmData
 * Implements: algorithmInterface
 * Description: Implements Simulated Annealing for Graph Data
 */
public class simulatedAnnealingG implements algorithmInterface
{
	/*
	 * Local Class Variables
	 */
	private graphData myData;
	private int iterationsCount;
	
	/*
	 * Constructor
	 * 
	 * Input - data - abstractData type which is cast to the needed dataType and stored 
	 */
	public simulatedAnnealingG(abstractData data)
	{
		myData = (graphData)data;
		iterationsCount = 0;
	}
	
	/*
	 * Function: nextIteration
	 * Description: Utilize the function calls available in graphData to run one iteration of the algorithm.
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
		throw new algorithmException("This algorithm had not been created. Please write code for the algorithm before using.");
		//iterationsCount++;
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
