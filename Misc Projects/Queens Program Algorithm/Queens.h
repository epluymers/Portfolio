//  Queens.h : Queens Problem

//  Class defintion for Queens Problem 
//  Course: CSCI 48700
//  Programmed by: Elicia Pluymers

#if ! defined (QUEENS_H)
#define QUEENS_H

#include <iostream>
#include <sstream>

using namespace std;

class Queens
{
private:
  int bs;
	int numitterations;
	int *queensolution; //Used to store solution and current position
	void initializeQueens(int start); //creates starting positions based on
	int findMinConflict(int queen); //returns location of least conflict for queen
	int countConflicts(int queen, int x); //Will run a check to see if the queens current row and column conflict with other queens
	bool finalStateFound(); //returns true if there are no conflicts for queens on board
	
public:
  Queens(); //Default constructor
  Queens (int n, bool vb, bool d); //Constructor takes in N Value, boolean(true = visual representation), and boolean (true = show each move)
	bool findSolution(); //Runs algorithm to fins solution
	void print(); //Function to print solution of Queens
	~Queens(); //Destructor to delete pointer variables created when program finishes
};

#endif