Readme.txt for Queens Problem
Writtien By: Elicia Pluymers
Created: February 19, 2022
Updated: February 19, 2022
Course: CSCI 48700

Program was tested and compiled using the tesla server.

COMPILING THE CODE ON TESLA SERVER
Execute make command


RUNNING TO PROGRAM ON TESLA SERVER
Execute make run command


NOTES FOR RUNNING PROGRAM
Each time the program executes it will...
  1. Print out the start state of the queens.
	2. If a solution is found it will print out the answer otherwise it will state that the program failed.
	   Note: If the program runs over 100 itterations and it is still has not found a solution it is considered in a fail state.
					 The program will automatically attempt to get out of the trap state by moving on of the queens to a new location.
					 If this does not solve the fail state the program will automatically terminate after 1000 itterations.

FILE INFORMATION
makefile
    Contains commands to compile and run program.
driver.cpp
    Contains main for program. Utilizes Queens.cpp 
Queens.h
    Header file for Queens.cpp
Queens.cpp
    Contains code that finds the solution to the Queens Problem when called in driver.cpp.