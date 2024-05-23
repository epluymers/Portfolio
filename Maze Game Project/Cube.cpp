//Cube.cpp
//Elicia Pluymers
//Final Project
//Last Updated: 5/6/18
//Create a unit of a maze that returns information about it's properties

#include "Cube.h"

using namespace std;

Cube::Cube() //all defaults set for beginning of maze
{
  addedToRoute = false; //tracks to see if cube has been modified to create maze
  occupied = false;
  visible = true;
  for(int i = 0; i < 4; i++)
  {
    walls[i] = true;
    end[i] = false;
    begin[i] = false;
  }
}

void Cube::reset() //returns cube back to defaults for beginning of new maze
{
  addedToRoute = false;
  occupied = false;
  visible = true;
  for(int i = 0; i < 4; i++)
  {
    walls[i] = true;
    end[i] = false;
    begin[i] = false;
  }
}

void Cube::setOccupied(bool isOccupied) //sets whether player is occupying this cube
{
  occupied = isOccupied;
}

void Cube::removeWall(compass wallLoc) //sets direction indicated    ****poss sets added to route to true
{
  walls[wallLoc] = false;
  addedToRoute = true;
}

bool Cube::isAdded() //returns value of addedToRoute
{
  return addedToRoute;
}

void Cube::setVisible(bool visibility)
{
  visible = visibility;
}

bool Cube::isVisible()
{
  return visible;
}

void Cube::printCube(int row, displaySize cubeSize, bool cubeBelowVisible) //prints row indicated by value
{
  if(cubeSize == Big)
  {
    if (visible)
    {
      if(row == 0)
      {
        if(end[North] == true)
        {
          cout << " E N D";
        }
        else if(begin[North] == true)
        {
          cout << " B E G";
        }
        else
        {
          cout << " _ _ _";
        }
      }
      else
      {
        if(end[West] == true)
        {
          switch(row)
          {
            case 1:
              cout << "E";
              break;
            case 2:
              cout << "N";
              break;
            case 3:
              cout << "D";
              break;
            default:
              cout << "Unknown Error: Cube.cpp";
              break;
          }
        }
        else if(begin[West] == true)
        {
          switch(row)
          {
            case 1:
              cout << "B";
              break;
            case 2:
              cout << "E";
              break;
            case 3:
              cout << "G";
              break;
            default:
              cout << "Unknown Error: Cube.cpp";
              break;
          }
        }
        else if(walls[West] == true)
        {
          cout << "|";
        }
        else
        {
          cout << " ";
        }
        
        if(occupied)
        {
          switch (row)
          {
          case 1:
            cout << "  0  ";
            break;
          case 2:
            cout << " /|" << (char)92 << " ";
            break;
          case 3:
            if(walls[South] == true)
            {
              cout << "_/_" << (char)92 << "_";
            }
            else
            {
              cout << " / " << (char)92 << " ";
            }
            break;
          default:
            cout << "Unknown Error: Cube.cpp";
            break;
          }
        }
        else
        {
          switch (row)
          {
          case 1:
            cout << "     ";
            break;
          case 2:
            cout << "     ";
            break;
          case 3:
            if (walls[South] == true)
            {
              cout << "_ _ _";
            }
            else
            {
              cout << "     ";
            }
            break;
          default:
            cout << "Unknown Error: Cube.cpp";
            break;
          }
        }
      }
    }
    else
    {
      if(cubeBelowVisible && walls[South] && row == 3)
      {
        cout << (char)250 << "_" << (char)250 <<"_" << (char)250 << "_";
      }
      else if (row == 0)
      {
        cout << "      ";
      }
      else
      {
        cout << (char)250 << " " << (char)250 << " " << (char)250 << " ";
      }
    }
  }
  else
  {
    if (visible)
    {
      if(row == 0)
      {
        if(end[North] == true)
        {
          cout << " E E";
        }
        else if(begin[North] == true)
        {
          cout << " B B";
        }
        else
        {
          cout << " _ _";
        }
      }
      else
      {
        if(end[West] == true)
        {
          switch(row)
          {
            case 1:
              cout << "E";
              break;
            case 2:
              cout << "E";
              break;
            default:
              cout << "Unknown Error: Cube.cpp";
              break;
          }
        }
        else if(begin[West] == true)
        {
          switch(row)
          {
            case 1:
              cout << "B";
              break;
            case 2:
              cout << "B";
              break;
            default:
              cout << "Unknown Error: Cube.cpp";
              break;
          }
        }
        else if(walls[West] == true)
        {
          cout << "|";
        }
        else
        {
          cout << " ";
        }
        
        if(occupied)
        {
          switch (row)
          {
          case 1:
            cout << " " << (char)229 << " ";
            break;
          case 2:
            if(walls[South] == true)
            {
              cout << "_" << (char)206 << "_";
            }
            else
            {
              cout << " " << (char)206 << " ";
            }
            break;
          default:
            cout << "Unknown Error: Cube.cpp";
            break;
          }
        }
        else
        {
          switch (row)
          {
          case 1:
            cout << "   ";
            break;
          case 2:
            if (walls[South] == true)
            {
              cout << "_ _";
            }
            else
            {
              cout << "   ";
            }
            break;
          default:
            cout << "Unknown Error: Cube.cpp";
            break;
          }
        }
      }
    }
    else
    {
      if(cubeBelowVisible && walls[South] && row == 3)
      {
        cout << (char)250 << "_" << (char)250 << "_";
      }
      else if (row == 0)
      {
        cout << "    ";
      }
      else
      {
        cout << (char)250 << " " << (char)250 << " ";
      }
    }
  }
}

bool Cube::canMove(compass wallLoc) //returns true if there is no wall in direction indicated
{
  return !walls[wallLoc];
}

bool Cube::isEnd(compass endLoc) //returns true if the direction is the end of maze
{
  return end[endLoc];
}

bool Cube::isBegin(compass beginLoc) //returns true if the direction is the begining of the maze
{
  return begin[beginLoc];
}

void Cube::setEnd(compass endLoc)
{
  end[endLoc] = true;
}

void Cube::setBegin(compass beginLoc)
{
  begin[beginLoc] = true;
}