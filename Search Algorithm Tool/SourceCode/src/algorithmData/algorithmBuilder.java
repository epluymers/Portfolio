//==============================================================================
/**
 * File Name:   algorithmBuilder.java
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

/*
 * Class: algorithmBuilder
 * Package: algorithmData
 * Description: Builder class which creates an algorithm instance based on inputs given by user and returns an object that the user can use without knowing it's specific type.
 */
public class algorithmBuilder
{
	private algorithmInterface currentAlgorithm;
	
	/*
	 * Constructor
	 * 
	 * Input - dataType - String with data type details
	 * Input - algorithm - String with algorithm type details
	 * Input - data - abstractData object containing data
	 */
	public algorithmBuilder(String dataType, String algorithmType, abstractData data)
	{
		switch (dataType)
		{
			case "Graph":
				switch (algorithmType)
				{
					case "aStartSearch":
						currentAlgorithm = new aStarSearchG(data);
						break;
					case "bidirectionalSearchAlgorithm":
						currentAlgorithm = new bidirectionalSearchAlgorithmG(data);
						break;
					case "depthFirstSearch":
						currentAlgorithm = new depthFirstSearchG(data);
						break;
					case "depthLimitedAlgorithm":
						currentAlgorithm = new depthLimitedAlgorithmG(data);
						break;
					case "greedyAlgorithm":
						currentAlgorithm = new greedyAlgorithmG(data);
						break;
					case "hillClimbingSearch":
						currentAlgorithm = new hillClimbingSearchG(data);
						break;
					case "iterativeDeepeningSearch":
						currentAlgorithm = new iterativeDeepeningSearchG(data);
						break;
					case "localBeamSearch":
						currentAlgorithm = new localBeamSearchG(data);
						break;
					case "simulatedAnnealing":
						currentAlgorithm = new simulatedAnnealingG(data);
						break;
					case "uniformCostAlgorithm":
						currentAlgorithm = new uniformCostAlgorithmG(data);
						break;
					case "widthFirstSearch":
						currentAlgorithm = new widthFirstSearchG(data);
						break;
					default:
						//todo
				}
				break;
			case "NQueens":
				switch (algorithmType)
				{
					case "aStartSearch":
						currentAlgorithm = new aStarSearchQ(data);
						break;
					case "bidirectionalSearchAlgorithm":
						currentAlgorithm = new bidirectionalSearchAlgorithmQ(data);
						break;
					case "depthFirstSearch":
						currentAlgorithm = new depthFirstSearchQ(data);
						break;
					case "depthLimitedAlgorithm":
						currentAlgorithm = new depthLimitedAlgorithmQ(data);
						break;
					case "greedyAlgorithm":
						currentAlgorithm = new greedyAlgorithmQ(data);
						break;
					case "hillClimbingSearch":
						currentAlgorithm = new hillClimbingSearchQ(data);
						break;
					case "iterativeDeepeningSearch":
						currentAlgorithm = new iterativeDeepeningSearchQ(data);
						break;
					case "localBeamSearch":
						currentAlgorithm = new localBeamSearchQ(data);
						break;
					case "simulatedAnnealing":
						currentAlgorithm = new simulatedAnnealingQ(data);
						break;
					case "uniformCostAlgorithm":
						currentAlgorithm = new uniformCostAlgorithmQ(data);
						break;
					case "widthFirstSearch":
						currentAlgorithm = new widthFirstSearchQ(data);
						break;
					default:
						//todo
				}
				break;
			default:
		}
	}
	
	/*
	 * Function: getAlgorithm
	 * Description: Returns the Object created by the builder specific to data type and algorithm.
	 * 
	 * Return - algorithmInterface: abstract object constructed with specific data type and algorithm
	 */
	public algorithmInterface getAlgorithm()
	{
		return currentAlgorithm;
	}
}
