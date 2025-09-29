//==============================================================================
/**
 * File Name:   queensData.java
 *
 * Written By: Elicia Pluymers
 * 2023 CS Capstone Project AiAlgorithms
 **/
//==============================================================================

package dataStorage;

/*
 * Import Statements
 */
import java.util.ArrayList;

/*
 * Class: queensData
 * Package: dataStorage
 * Extends: abstractData
 * Description: Class for Queens Data Storage and Manipulation. Handles data maintenance tasks for algorithm.
 */
public class queensData extends abstractData
{
	/*
	 * Local Class Variables
	 */
	private ArrayList<Integer> queenX;
	private ArrayList<Integer> queenY;
	private int boardSize;
	
	/*
	 * Constructor
	 */
	public queensData()
	{
		queenX = new ArrayList<Integer>();
		queenY = new ArrayList<Integer>();
		boardSize = 0;
	}
	
	public void setBoardSize(int size)
	{
		boardSize = size;
	}
	
	public int getBoardSize()
	{
		return boardSize;
	}
	
	public void addQueen(int x, int y)
	{
		queenX.add(x);
		queenY.add(y);
	}
	
	public boolean goalTest()
	{
		for(int i = 1; i <= boardSize; i++)
		{
			if(countConflicts(i) != 0)
			{
				return false;
			}
		}
		return true;
	}
	
	public int countConflicts(int queen)
	{
		int xLoc = getQueenX(queen);
		int yLoc = getQueenY(queen);
		int conflictCount = 0;
		for(int i = 0; i < boardSize; i++)
		{
			if(i != queen - 1)
			{
				if(xLoc == queenX.get(i))
				{
					conflictCount++;
				}
				else if(yLoc == queenY.get(i))
				{
					conflictCount++;
				}
				else if(Math.abs(xLoc - queenX.get(i)) == Math.abs(yLoc - queenY.get(i)))
				{
					conflictCount++;
				}
			}
		}
		return conflictCount;
	}
	
	public void moveQueen(int queen, int x, int y)
	{
		queenX.set(queen - 1, x);
		queenY.set(queen - 1, y);
	}
	
	public int getQueenX(int queen)
	{
		return queenX.get(queen-1);
	}
	
	public int getQueenY(int queen)
	{
		return queenY.get(queen-1);
	}
	
	public boolean validateData()
	{
		return true;
		//todo
	}
}
