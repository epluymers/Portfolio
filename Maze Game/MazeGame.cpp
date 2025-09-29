//MazeGame.cpp
//Elicia Pluymers
//Final Project
//Last Updated: 5/6/18
//Run a game of Maze

#include "Map.h"
#include "Cube.h"
#include <iostream>
#include <string>
#include <ctime>

using namespace std;

int main()
{
  cout << "Welcome to the maze game!" << endl;
  cout << "Rules and Instructions:" << endl;
  cout << "1. The only way to win the game is to find the exit." << endl;
  cout << "2. Use the 'W' 'A' 'S' and 'D' keys to move through the maze." << endl;
  cout << "3. There are two game board formats:" << endl;
  cout << "   Regular: We recommend a max board size of 13 cells." << endl;
  cout << "   Compact: We recommend a max board size of 19 cells." << endl;
  cout << "4. There are four levels of difficulty." << endl;
  cout << "   Easy: You are able to see the entire board from the start." << endl;
  cout << "   Medium: Your view is limited to 2 squares in any direction, but once you leave an area it will remain visible." << endl;
  cout << "   Hard: Your view is limited to 1 square in any direction, but once you leave an area it will remain visible." << endl;
  cout << "   Insane: Your view is limited to 1 square in any direction and once you leave and area it will become hidden again." << endl;
  cout << "5. Here are some additional commands you can use during the game." << endl;
  cout << "   'H' - See a list of commands again." << endl;
  cout << "   'L' - Change level of difficulty" << endl;
  cout << "   'C' - Change size of game board. Will restart game." << endl;
  cout << "   'F' - Change game board format" << endl;
  cout << "   'R' - Reset game map with same settings" << endl;
  cout << "   'N' - Start new game with different settings." << endl;
  cout << "   'E' - Exit game" << endl;
  cout << "We will now set up your game..." << endl;
  
  bool gameOver = false;
  bool winner = false;
  char gameSelect = 'N';
  Map::difficulty myLevel;
  Cube::displaySize myFormat;
  
  Map *gameMap;
  
  while(!gameOver)
  {
    switch(gameSelect)
    {
      case 'w':
        gameSelect = 'W';
        break;
      case 'a':
        gameSelect = 'A';
        break;
      case 's':
        gameSelect = 'S';
        break;
      case 'd':
        gameSelect = 'D';
        break;
      case 'h':
        gameSelect = 'H';
        break;
      case 'l':
        gameSelect = 'L';
        break;
      case 'c':
        gameSelect = 'C';
        break;
      case 'f':
        gameSelect = 'F';
        break;
      case 'r':
        gameSelect = 'R';
        break;
      case 'n':
        gameSelect = 'N';
        break;
      case 'e':
        gameSelect = 'E';
        break;
      case 'W':
        winner = gameMap->move(Cube::North);
        if(!winner) {cin >> gameSelect;}
        break;
      case 'A':
        winner = gameMap->move(Cube::West);
        if(!winner) {cin >> gameSelect;}
        break;
      case 'S':
        winner = gameMap->move(Cube::South);
        if(!winner) {cin >> gameSelect;}
        break;
      case 'D':
        winner = gameMap->move(Cube::East);
        if(!winner) {cin >> gameSelect;}
        break;
      case 'H':
        cout << "Here is the list of in game commands:" << endl;
        cout << "L-Change Level    C-Change Size    F-Change Format    R-Reset Game    N-New Game Setup    E-Exit" << endl;
        cin >> gameSelect;
        break;
      case 'L':
        char select;
        cout << "Please select new level of difficulty(E=Easy, M=Medium, H=Hard, I=Insane):";
        cin >> select;
        switch(select)
        {
          case 'e':
            gameMap->setDifficulty(Map::Easy);
            myLevel = Map::Easy;
            cin >> gameSelect;
            break;
          case 'm':
            gameMap->setDifficulty(Map::Medium);
            myLevel = Map::Medium;
            cin >> gameSelect;
            break;
          case 'h':
            gameMap->setDifficulty(Map::Hard);
            myLevel = Map::Hard;
            cin >> gameSelect;
            break;
          case 'i':
            gameMap->setDifficulty(Map::Insane);
            myLevel = Map::Insane;
            cin >> gameSelect;
            break;
          case 'E':
            gameMap->setDifficulty(Map::Easy);
            myLevel = Map::Easy;
            cin >> gameSelect;
            break;
          case 'M':
            gameMap->setDifficulty(Map::Medium);
            myLevel = Map::Medium;
            cin >> gameSelect;
            break;
          case 'H':
            gameMap->setDifficulty(Map::Hard);
            myLevel = Map::Hard;
            cin >> gameSelect;
            break;
          case 'I':
            gameMap->setDifficulty(Map::Insane);
            myLevel = Map::Insane;
            cin >> gameSelect;
            break;
          default:
            gameSelect = 'L';
            break;
        }
        break;
      case 'C':
        int newSize;
        cout << "What size would you like to change to(5 - 25)?";
        cin >> newSize;
        if(newSize < 5 || newSize > 25)
        {
          gameSelect = 'C';
        }
        else
        {
          gameMap->resetMaze(newSize, myLevel, myFormat);
          cin >> gameSelect;
        }
        break;
      case 'F':
        cout << "Please select new format(C=Compact R=Regular):";
        cin >> select;
        switch(select)
        {
          case 'c':
            gameMap->setFormat(Cube::Small);
            myFormat = Cube::Small;
            cin >> gameSelect;
            break;
          case 'r':
            gameMap->setFormat(Cube::Big);
            myFormat = Cube::Big;
            cin >> gameSelect;
            break;
          case 'C':
            gameMap->setFormat(Cube::Small);
            myFormat = Cube::Small;
            cin >> gameSelect;
            break;
          case 'R':
            gameMap->setFormat(Cube::Big);
            myFormat = Cube::Big;
            cin >> gameSelect;
            break;
          default:
            gameSelect = 'F';
            break;
        }
        break;
      case 'R':
        gameMap->resetMaze();
        cin >> gameSelect;
        break;
      case 'N':
        cout << "Please select level of difficulty(E=Easy, M=Medium, H=Hard, I=Insane):";
        cin >> select;
        switch(select)
        {
          case 'e':
            myLevel = Map::Easy;
            break;
          case 'm':
            myLevel = Map::Medium;
            break;
          case 'h':
            myLevel = Map::Hard;
            break;
          case 'i':
            myLevel = Map::Insane;
            break;
          case 'E':
            myLevel = Map::Easy;
            break;
          case 'M':
            myLevel = Map::Medium;
            break;
          case 'H':
            myLevel = Map::Hard;
            break;
          case 'I':
            myLevel = Map::Insane;
            break;
          default:
            gameSelect = 'N';
            break;
        }
        cout << "Please select new format(C=Compact R=Regular):";
        cin >> select;
        switch(select)
        {
          case 'c':
            myFormat = Cube::Small;
            break;
          case 'r':
            myFormat = Cube::Big;
            break;
          case 'C':
            myFormat = Cube::Small;
            break;
          case 'R':
            myFormat = Cube::Big;
            break;
          default:
            gameSelect = 'N';
            break;
        }
        cout << "What size would you like to change to(5 - 25)?";
        cin >> newSize;
        if(newSize < 5 || newSize > 25)
        {
          gameSelect = 'N';
        }
        else
        {
          gameMap = new Map(newSize, myLevel, myFormat);
          cin >> gameSelect;
        }
        break;
      case 'E':
        cout << "Are you sure you want to exit(y/n)?";
        cin >> select;
        switch(select)
        {
          case 'y':
            gameOver = true;
            break;
          case 'Y':
            gameOver = true;
            break;
          default:
            cin >> gameSelect;
            break;
        }
        break;
      default:
        cin >> gameSelect;
        break;
    }
    if(winner)
    {
      char select;
      cout << "Would you like to play again(y/n)?";
      cin >> select;
      switch(select)
      {
        case 'n':
          gameOver = true;
          break;
        case 'N':
          gameOver = true;
          break;
        default:
          cout << "Setting up new game board..." << endl;
          gameMap->resetMaze();
          winner = false;
          cin >> gameSelect;
          break;
      }
    }
  }
  cout << "Thank You for playing!";
  return 1;
}