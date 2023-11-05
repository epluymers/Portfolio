//==============================================================================
/**
 * File Name:   algorithmInterface.java
 *
 * Written By: Elicia Pluymers
 * 2023 CS Capstone Project AiAlgorithms
 **/
//==============================================================================

package algorithmData;

/*
 * Class: algorithmInterface
 * Package: algorithmData
 * Description: Class Interface that defines functions that can be called from main user interface
 */
public interface algorithmInterface 
{
	/*
	 * Abstract Function: nextIteration
	 * Description: Utilize the function calls available in data to run one iteration of the algorithm.
	 * 
	 * Return - int:  -1: Algorithm Failed to Find Solution
	 * 				   0: Algorithm Has Not Failed Nor Reached It's Goal
	 * 				   1: Algorithm Has Reached Goal State
	 * 
	 * Throws - AlgorithmException: Notifies User that the algorithm is has not yet been defined and still needs to be written.
	 */
	public int nextIteration() throws algorithmException;
	
	/*
	 * Abstract Function: getTotalIterations
	 * Description: Returns Number of Iterations the algorithm has gone through at the point the function has been called
	 * 
	 * Return - int:  numberOfIterations
	 */
	public int getTotalIterations();
}
