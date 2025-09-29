//Cube.h
//Elicia Pluymers
//Final Project
//Last Updated: 5/6/18
//Create a unit of a maze that returns information about it's properties

#ifndef CUBE_H
#define CUBE_H

#include <iostream>
#include <string>

using namespace std;

class Cube
{
private:
  bool addedToRoute; //tracks to see if cube has been modified to create maze
  bool walls[4];//tracks which wall exist and don't false = no wall
  bool occupied;
  bool end[4];
  bool begin[4];
  bool visible;
public:
  enum compass{North, East, South, West};
  enum displaySize{Small, Big};
  Cube(); //all defaults set for beginning of maze
  void reset(); //returns cube back to defaults for beginning of new maze
  void setOccupied(bool); //sets whether player is occupying this cube
  void removeWall(compass); //sets direction indicated    ****poss sets added to route to true
  bool isAdded(); //returns value of addedToRoute
  void printCube(int row, displaySize, bool); //prints row indicated by value
  bool canMove(compass); //returns true if there is no wall in direction indicated
  bool isEnd(compass); //returns true if the direction is the end of maze
  bool isBegin(compass); //returns true if the direction is the begining of the maze
  void setVisible(bool); //sets whether cube is visible
  void setEnd(compass);
  void setBegin(compass);
  bool isVisible(); // returns true if cube is visible to player
};

#endif