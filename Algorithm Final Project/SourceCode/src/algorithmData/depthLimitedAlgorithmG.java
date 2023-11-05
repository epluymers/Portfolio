//==============================================================================
/**
 * File Name:   depthLimitedAlgorithmG.java
 *
 * Written By: Elicia Pluymers
 * 2023 CS Capstone Project AiAlgorithms
 **/
//==============================================================================

package algorithmData;

/*
 * Import Statements
 */
import java.util.ArrayList;

import dataStorage.abstractData;
import dataStorage.graphData;

/*
 * Class: depthLimitedSearchG
 * Package: algorithmData
 * Implements: algorithmInterface
 * Description: Implements Depth Limited Search Algorithm for Graph Data
 */
public class depthLimitedAlgorithmG implements algorithmInterface
{
	/*
	 * Local Class Variables
	 */
	private graphData myData;
	private int iterationsCount;
	private int itDepth;
	
	/*
	 * Constructor
	 * 
	 * Input - data - abstractData type which is cast to the needed dataType and stored 
	 */
	public depthLimitedAlgorithmG(abstractData data)
	{
		myData = (graphData)data;
		iterationsCount = 0;
		itDepth = myData.getDepthLimit();
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
		//throw new algorithmException("This algorithm had not been created. Please write code for the algorithm before using.");
		iterationsCount++;
		
		if(myData.goalTest())
		{
			return 1;
		}
		if(myData.getFrontierSize() == 0)
		{
			return -1;
		}
		
		int currentNodeID = myData.getCurrentLocation();
		while(myData.getFrontierSize(currentNodeID) == 0 || myData.getSolutionSize() == itDepth + 1)
		{
			if(myData.getSolutionSize() == 1)
			{
				return -1;
			}
			myData.popSolutionSet();
			currentNodeID = myData.getCurrentLocation();
		}
		ArrayList<Integer> firstFront = myData.getFirstFrontier(currentNodeID);
		myData.addSolution(firstFront);
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
