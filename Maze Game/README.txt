Readme.txt for MazeGame
Writtien By: Elicia Pluymers
Created: May 7, 2018
Updated: May 22, 2023
Course: CSCI 48700

Program was originally tested and compiled using MinGW GCC Version 6.3.0 on Windows
UPDATE 5/22/2023: Program had been recompiled and tested using MinGW GCC Version 13.2.0 on Windows 11

RUNNING PROGRAM
Run the included MazeGame.exe file
If MazeGame.exe is incompatable with your system, recompile from source code.
Command Line Compilation: c++ MazeGame.cpp Map.cpp Cube.cpp -o MazeGame

NOTES FOR PLAYING THE GAME
This program is text based and after input the Enter key must be pressed.
Note: When navigating through the maze, multiple directional commands may be typed out before pressing enter and they will be performed in order.

PROGRAM FILES
MazeGame.exe
  Compiled executable
MazeGame.cpp
  Main program that handles user input.
Map.cpp
  Class which generates and stores the map of the active maze.
Map.h
  Header file for the Map class.
Cube.cpp
  Class which maintains the individual cells of the maze and prints them to the text based interface.
Cube.h
  Header file for the Cube class.