//  Queens.cpp : Queens Problem      

//  Member function definition file for Queens Problem
//  Course: CSCI 48700
//  Programmed by: Elicia Pluymers

#include "Queens.h"
#include <cstdlib>
#include <ctime>

// Queens
Queens::Queens()
{
	srand(time(NULL));
	numitterations = 0;
	bs = 8;
	queensolution = new int[bs];
	for(int i = 0; i < bs; i++)
	{
		queensolution[i] = -1;
	}
	initializeQueens((int)(rand() % bs));
}

// findSolution
bool Queens::findSolution()
{
	int activequeen = 0;
	while(!finalStateFound())
	{
		numitterations++;
		activequeen = numitterations % bs;
		
		if(countConflicts(activequeen, queensolution[activequeen]) != 0)
		{
			queensolution[activequeen] = findMinConflict(activequeen);
		}
		if(numitterations % 100 == 0) //Correction for trap states
		{
			queensolution[activequeen]++;
		}
		if(numitterations % 1000 == 0) //If no solution can be found after 1000 itterations return false
		{
			return false;
		}
	}
	return true;
}

// printSolution
void Queens::print()
{
	cout << "Itterations: " << numitterations << endl;
	cout << "{";
	for(int i = 0; i < bs; i++)
	{
		cout << queensolution[i];
		if(i < bs - 1)
		{
			cout << ", ";
		}
		else
		{
		cout << "}" << endl;
		}
	}
}

// ~Queens
Queens::~Queens() 
{
}

// initializeQueens
void Queens::initializeQueens(int start)
{
	queensolution[0] = start;
	for(int i = 1; i < bs; i++)
	{
		queensolution[i]++;
		queensolution[i] = findMinConflict(i);
	}
}

// findMinConflict
int Queens::findMinConflict(int queenLoc)
{	
	int minconflict = countConflicts(queenLoc, queensolution[queenLoc]);
	int minconflictloc = queensolution[queenLoc];
	for(int i = 0; i < bs; i++)
	{
		int nextconflictcount = countConflicts(queenLoc, (queensolution[queenLoc]+i) % bs);
		if(minconflict >= nextconflictcount)
		{
			minconflict = nextconflictcount;
			minconflictloc = (queensolution[queenLoc] + i) % bs;
		}
	}
	return minconflictloc;
}

// countConflicts
int Queens::countConflicts(int queenLoc, int x) 
{
	int columnconflict = x;
	int diag1conflict = queenLoc - x + bs-1;
	int diag2conflict = queenLoc + x;
	int conflictcount = 0;
	
	for(int i = 0; i < bs; i++)
	{
		if(queenLoc != i && queensolution[i] != -1)
		{
			if(columnconflict == queensolution[i] || diag1conflict == (i - queensolution[i] + bs - 1) || diag2conflict == (i + queensolution[i]))
			{
				conflictcount++;
			}
		}
	}
	return conflictcount;
}

// finalStateFound
bool Queens::finalStateFound()
{
	for(int i = 0; i < bs; i++)
	{
		if(countConflicts(i, queensolution[i]) > 0)
		{
			return false;
		}
	}
	return true;
}

