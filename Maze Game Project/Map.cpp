//Map.h
//Elicia Pluymers
//Final Project
//Last Updated: 5/6/18
//Class to generate maze of set size

#include "Map.h"

using namespace std;

Map::Map(int mapSize, difficulty newLevel, Cube::displaySize newDisplaySize)
{
  srand(time(NULL));
  size = mapSize;
  gameLevel = newLevel;
  sizeDisplayed = newDisplaySize;
  for(int x = 0; x < mapSize; x++)
  {
    vector<Cube> a;
    mapGrid.push_back(a);
    for(int y = 0; y < mapSize; y++)
    {
      mapGrid[x].push_back(Cube());
    }
  }
  generateMap();
}

bool Map::canMove(Cube::compass playerDirection)
{
  if(mapGrid[playerX][playerY].canMove(playerDirection))
  {
    return true;
  }
  else
  {
    return false;
  }
}

bool Map::move(Cube::compass moveDirection) //returns true if player reaches exit prints updated maze when move is completed
{
  if(canMove(moveDirection))
  {
    if(mapGrid[playerX][playerY].isEnd(moveDirection))
    {
      cout << "You have won!";
      return true;
    }
    else if(mapGrid[playerX][playerY].isBegin(moveDirection))
    {
      cout << "You have found the entrance again. To win the game you must find the exit.";
      return false;
    }
    else
    {
      mapGrid[playerX][playerY].setOccupied(false);
      switch(moveDirection)
      {
        case Cube::North:
          playerX--;
          break;
        case Cube::East:
          playerY++;
          break;
        case Cube::South:
          playerX++;
          break;
        case Cube::West:
          playerY--;
          break;
        default:
          cout << "Unknown Error: Map.cpp";
          break;
      }
      mapGrid[playerX][playerY].setOccupied(true);
      adjustVisibility(false);
      printMaze();
      return false;
    }
  }
  else
  {
    return false;
  }
}

void Map::resetMaze()
{
  for(int x = 0; x < size; x++)
  {
    for(int y = 0; y < size; y++)
    {
      mapGrid[x][y].reset();
    }
  }
  generateMap();
}

void Map::resetMaze(int mapSize, difficulty newLevel, Cube::displaySize newDisplaySize)
{
  gameLevel = newLevel;
  sizeDisplayed = newDisplaySize;
  size = mapSize;
  mapGrid.erase(mapGrid.begin(),mapGrid.end());
  for(int x = 0; x < mapSize; x++)
  {
    vector<Cube> a;
    mapGrid.push_back(a);
    for(int y = 0; y < mapSize; y++)
    {
      mapGrid[x].push_back(Cube());
    }
  }
  generateMap();
}

void Map::printMaze()
{
  for(int x = 0; x < size; x++)
  {
    int rowStart = 1;
    if(x == 0)
    {
      rowStart = 0;
    }
    for(int r = rowStart; r <= (sizeDisplayed + 2); r++)
    {
      for(int y = 0; y < size; y++)
      {
        if(x == size - 1)
        {
          mapGrid[x][y].printCube(r, sizeDisplayed, false);
        }
        else
        {
          mapGrid[x][y].printCube(r, sizeDisplayed, mapGrid[x+1][y].isVisible());
        }
        if(y == (size - 1) && mapGrid[x][y].isVisible())
        {
          if(mapGrid[x][y].isEnd(Cube::East))
          {
            switch(r)
            {
              case 0:
                cout << endl;
                break;
              case 1:
                cout << "E" << endl;
                break;
              case 2:
                if(sizeDisplayed == Cube::Small)
                {
                  cout << "E" << endl;
                }
                else
                {
                  cout << "N" << endl;
                }
                break;
              case 3:
                cout << "D" << endl;
                break;
              default:
                cout << "Unknown Error: Map.cpp";
                break;
            }
          }
          else if(mapGrid[x][y].isBegin(Cube::East))
          {
            switch(r)
            {
              case 0:
                cout << endl;
                break;
              case 1:
                cout << "B" << endl;
                break;
              case 2:
                if(sizeDisplayed == Cube::Small)
                {
                  cout << "B" << endl;
                }
                else
                {
                  cout << "E" << endl;
                }
                break;
              case 3:
                cout << "G" << endl;
                break;
              default:
                cout << "Unknown Error: Map.cpp";
                break;
            }
          }
          else
          {
            if(r == 0)
            {
              cout << endl;
            }
            else
            {
              cout << "|" << endl;
            }
          }
        }
        else if(y == size - 1)
        {
          if(r==0)
          {
            cout << endl;
          }
          else
          {
            cout << (char)250 << endl;
          }
        }
      }
    }
    if(x == size - 1)
    {
      for(int y = 0; y < size; y++)
      {
        if(mapGrid[x][y].isEnd(Cube::South) && mapGrid[x][y].isVisible())
        {
          if(sizeDisplayed == Cube::Big)
          {
            cout << " E N D";
          }
          else
          {
            cout << " E E";
          }
        }
        else if(mapGrid[x][y].isBegin(Cube::South) && mapGrid[x][y].isVisible())
        {
          if(sizeDisplayed == Cube::Big)
          {
            cout << " B E G";
          }
          else
          {
            cout << " B B";
          }
        }
        else
        {
          if(sizeDisplayed == Cube::Big)
          {
            cout << "      ";
          }
          else
          {
            cout << "    ";
          }
        }
      }
    }
  }
  cout << endl;
}

void Map::generateMap()
{
  int randDir = rand()%4;
  int beginX;
  int beginY;
  switch((Cube::compass)randDir)
  {
    case Cube::North:
      beginX = 0;
      beginY = rand()%size;
      mapGrid[beginX][beginY].removeWall(Cube::North);
      mapGrid[beginX][beginY].setBegin(Cube::North);
      break;
    case Cube::East:
      beginX = rand()%size;
      beginY = size - 1;
      mapGrid[beginX][beginY].removeWall(Cube::East);
      mapGrid[beginX][beginY].setBegin(Cube::East);
      break;
    case Cube::South:
      beginX = size - 1;
      beginY = rand()%size;
      mapGrid[beginX][beginY].removeWall(Cube::South);
      mapGrid[beginX][beginY].setBegin(Cube::South);
      break;
    case Cube::West:
      beginX = rand()%size;
      beginY = 0;
      mapGrid[beginX][beginY].removeWall(Cube::West);
      mapGrid[beginX][beginY].setBegin(Cube::West);
      break;
    default:
      cout << "Unknown Error: Map.cpp";
      break;
  }
  playerX = beginX;
  playerY = beginY;
    
  mapGrid[playerX][playerY].setOccupied(true);
  
  randomMazeRoute(beginX, beginY, true);
  
  bool unassignedCubes = true;
  
  while(unassignedCubes)
  {
    unassignedCubes = findUnassigned();
  }
  
  adjustVisibility(true);
  
  printMaze();
}

void Map::adjustVisibility(bool firstMove)
{
  int xStart;
  int xEnd;
  int yStart;
  int yEnd;
  
  if((firstMove && gameLevel != Easy) || gameLevel == Insane)
  {
    for(int x = 0; x < size; x++)
    {
      for(int y = 0; y < size; y++)
      {
        mapGrid[x][y].setVisible(false);
      }
    }
  }
  
  switch(gameLevel)
  {
    case Easy:
      break;
    case Medium:
      if(playerX < 2)
      {
        xStart = 0;
      }
      else
      {
        xStart = playerX - 2;
      }
      if(playerX >= size - 2)
      {
        xEnd = size - 1;
      }
      else
      {
        xEnd = playerX + 2;
      }
      
      if(playerY < 2)
      {
        yStart = 0;
      }
      else
      {
        yStart = playerY - 2;
      }
      if(playerY >= size - 2)
      {
        yEnd = size - 1;
      }
      else
      {
        yEnd = playerY + 2;
      }
      for(int x = xStart; x <= xEnd; x++)
      {
        for(int y = yStart; y <= yEnd; y++)
        {
          mapGrid[x][y].setVisible(true);
        }
      }
      break;
    case Insane:
      //Same as hard except for if loop above which makes everything invisible again
    case Hard:
      if(playerX < 1)
      {
        xStart = 0;
      }
      else
      {
        xStart = playerX - 1;
      }
      if(playerX >= size - 1)
      {
        xEnd = size - 1;
      }
      else
      {
        xEnd = playerX + 1;
      }
      
      if(playerY < 1)
      {
        yStart = 0;
      }
      else
      {
        yStart = playerY - 1;
      }
      if(playerY >= size - 1)
      {
        yEnd = size - 1;
      }
      else
      {
        yEnd = playerY + 1;
      }
      for(int x = xStart; x <= xEnd; x++)
      {
        for(int y = yStart; y <= yEnd; y++)
        {
          mapGrid[x][y].setVisible(true);
        }
      }
      break;
    default:
      cout << "Unknown Error: Map.cpp";
      break;
  }
}

void Map::randomMazeRoute(int x, int y, bool solutionRoute)
{
  bool routeComplete = false;
  int solutionLength = 0;
  int randDir = rand()%4;
  int allDirectionsChecked = 0;
  while(!routeComplete)
  {
    if(solutionRoute)
    {
      switch((Cube::compass)randDir)
      {
        case Cube::North:
          if(x == 0)
          {
            if(!mapGrid[x][y].isBegin(Cube::North) && solutionLength > size)
            {
              routeComplete = true;
              mapGrid[x][y].removeWall(Cube::North);
              mapGrid[x][y].setEnd(Cube::North);
            }
            else
            {
              randDir = (randDir + 1)%4;
              allDirectionsChecked++;
            }
          }
          else if((mapGrid[x-1][y].isAdded() && allDirectionsChecked < 4) || (allDirectionsChecked >= 4 && !mapGrid[x][y].canMove(Cube::North)))
          {
            randDir = (randDir + 1)%4;
            allDirectionsChecked++;
          }
          else
          {
            if(mapGrid[x][y].canMove(Cube::North))
            {
              solutionLength--;
            }
            mapGrid[x][y].removeWall(Cube::North);
            x--;
            mapGrid[x][y].removeWall(Cube::South);
            allDirectionsChecked = 0;
            randDir = rand()%4;
            solutionLength++;
          }
          break;
        case Cube::South:
          if(x == size - 1)
          {
            if(!mapGrid[x][y].isBegin(Cube::South) && solutionLength > size*3)
            {
              routeComplete = true;
              mapGrid[x][y].removeWall(Cube::South);
              mapGrid[x][y].setEnd(Cube::South);
            }
            else
            {
              randDir = (randDir + 1)%4;
              allDirectionsChecked++;
            }
          }
          else if((mapGrid[x+1][y].isAdded() && allDirectionsChecked < 4) || (allDirectionsChecked >= 4 && !mapGrid[x][y].canMove(Cube::South)))
          {
            randDir = (randDir + 1)%4;
            allDirectionsChecked++;
          }
          else
          {
            if(mapGrid[x][y].canMove(Cube::South))
            {
              solutionLength--;
            }
            mapGrid[x][y].removeWall(Cube::South);
            x++;
            mapGrid[x][y].removeWall(Cube::North);
            allDirectionsChecked = 0;
            randDir = rand()%4;
            solutionLength++;
          }
          break;
        case Cube::East:
          if(y == size - 1)
          {
            if(!mapGrid[x][y].isBegin(Cube::East) && solutionLength > size)
            {
              routeComplete = true;
              mapGrid[x][y].removeWall(Cube::East);
              mapGrid[x][y].setEnd(Cube::East);
            }
            else
            {
              randDir = (randDir + 1)%4;
              allDirectionsChecked++;
            }
          }
          else if((mapGrid[x][y+1].isAdded() && allDirectionsChecked < 4) || (allDirectionsChecked >= 4 && !mapGrid[x][y].canMove(Cube::East)))
          {
            randDir = (randDir + 1)%4;
            allDirectionsChecked++;
          }
          else
          {
            if(mapGrid[x][y].canMove(Cube::East))
            {
              solutionLength--;
            }
            mapGrid[x][y].removeWall(Cube::East);
            y++;
            mapGrid[x][y].removeWall(Cube::West);
            allDirectionsChecked = 0;
            randDir = rand()%4;
            solutionLength++;
          }
          break;
        case Cube::West:
          if(y == 0)
          {
            if(!mapGrid[x][y].isBegin(Cube::West) && solutionLength > size)
            {
              routeComplete = true;
              mapGrid[x][y].removeWall(Cube::West);
              mapGrid[x][y].setEnd(Cube::West);
            }
            else
            {
              randDir = (randDir + 1)%4;
              allDirectionsChecked++;
            }
          }
          else if((mapGrid[x][y-1].isAdded() && allDirectionsChecked < 4) || (allDirectionsChecked >= 4 && !mapGrid[x][y].canMove(Cube::West)))
          {
            randDir = (randDir + 1)%4;
            allDirectionsChecked++;
          }
          else
          {
            if(mapGrid[x][y].canMove(Cube::West))
            {
              solutionLength--;
            }
            mapGrid[x][y].removeWall(Cube::West);
            y--;
            mapGrid[x][y].removeWall(Cube::East);
            allDirectionsChecked = 0;
            randDir = rand()%4;
            solutionLength++;
          }
          break;
        default:
          cout << "Unknown Error: Map.cpp";
          break;
      }
    }
    else
    {
      switch((Cube::compass)randDir)
      {
        case Cube::North:
          if(x == 0 || mapGrid[x][y].canMove(Cube::North))
          {
            randDir = (randDir + 1)%4;
          }
          else if(mapGrid[x-1][y].isAdded())
          {
            routeComplete = true;
            mapGrid[x][y].removeWall(Cube::North);
            x--;
            mapGrid[x][y].removeWall(Cube::South);
          }
          else
          {
            mapGrid[x][y].removeWall(Cube::North);
            x--;
            mapGrid[x][y].removeWall(Cube::South);
          }
          break;
        case Cube::South:
          if(x == size - 1 || mapGrid[x][y].canMove(Cube::South))
          {
            randDir = (randDir + 1)%4;
          }
          else if(mapGrid[x+1][y].isAdded())
          {
            routeComplete = true;
            mapGrid[x][y].removeWall(Cube::South);
            x++;
            mapGrid[x][y].removeWall(Cube::North);
          }
          else
          {
            mapGrid[x][y].removeWall(Cube::South);
            x++;
            mapGrid[x][y].removeWall(Cube::North);
          }
          break;
        case Cube::East:
          if(y == size - 1 || mapGrid[x][y].canMove(Cube::East))
          {
            randDir = (randDir + 1)%4;
          }
          else if(mapGrid[x][y+1].isAdded())
          {
            routeComplete = true;
            mapGrid[x][y].removeWall(Cube::East);
            y++;
            mapGrid[x][y].removeWall(Cube::West);
          }
          else
          {
            mapGrid[x][y].removeWall(Cube::East);
            y++;
            mapGrid[x][y].removeWall(Cube::West);
          }
          break;
        case Cube::West:
          if(y == 0 || mapGrid[x][y].canMove(Cube::West))
          {
            randDir = (randDir + 1)%4;
          }
          else if(mapGrid[x][y-1].isAdded())
          {
            routeComplete = true;
            mapGrid[x][y].removeWall(Cube::West);
            y--;
            mapGrid[x][y].removeWall(Cube::East);
          }
          else
          {
            mapGrid[x][y].removeWall(Cube::West);
            y--;
            mapGrid[x][y].removeWall(Cube::East);
          }
          break;
        default:
          cout << "Unknown Error: Map.cpp";
          break;
      }
    }
  }
}

bool Map::findUnassigned()
{
  bool cubeFound = false;
  int cubesChecked = 0;
  int randX;
  int randY;
  randX = rand()%size;
  randY = rand()%size;
  while(!cubeFound && cubesChecked <= (size * size))
  {
    if(mapGrid[randX][randY].isAdded())
    {
      randX = (randX + 1)%size;
      if(randX == 0)
      {
        randY = (randY + 1)%size;
      } 
      cubesChecked++;
    }
    else
    {
      cubeFound = true;
      randomMazeRoute(randX, randY, false);
    }
  }
  return cubeFound;
}

void Map::setDifficulty(difficulty newLevel)
{
  gameLevel = newLevel;
  if(gameLevel == Easy)
  {
    for(int x = 0; x < size; x++)
    {
      for(int y = 0; y < size; y++)
      {
        mapGrid[x][y].setVisible(true);
      }
    }
  }
  else
  {
    adjustVisibility(true);
  }
  printMaze();
}

void Map::setFormat(Cube::displaySize newDisplaySize)
{
  sizeDisplayed = newDisplaySize;
  printMaze();
}