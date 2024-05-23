//Map.h
//Elicia Pluymers
//Final Project
//Last Updated: 5/6/18
//Class to generate maze of set size

#include <iostream>
#include <cmath>
#include <random>
#include <ctime>
#include <vector>
#include <string>
#include "Cube.h"

using namespace std;

class Map
{
public:
  enum difficulty{Easy, Medium, Hard, Insane};
private:
  vector<vector<Cube>> mapGrid;
  difficulty gameLevel;
  int size;
  Cube::displaySize sizeDisplayed;
  int playerX;
  int playerY;
public:
  Map(int mapSize, difficulty, Cube::displaySize);
  bool canMove(Cube::compass);
  bool move(Cube::compass); //returns true if player reaches exit prints updated maze when move is completed
  void resetMaze();
  void resetMaze(int mapSize, difficulty, Cube::displaySize);
  void printMaze();
  void generateMap();
  void adjustVisibility(bool firstMove);
  void randomMazeRoute(int x, int y, bool solutionRoute);
  bool findUnassigned();
  void setDifficulty(difficulty);
  void setFormat(Cube::displaySize);
};
  
  