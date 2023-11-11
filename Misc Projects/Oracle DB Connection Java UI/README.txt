Readme.txt for Course Project
Writtien By: Elicia Pluymers
Created: October 25, 2021
Updated: December 9, 2021
Course: CSCIN 31100


COMPILING THE CODE
Run all Oracle Script in Project Database Code.sql
- ProgramDriver.java relies on a table created in this script to allow user login
Compile Eclipse Project
- ProgramDriver.java
- DBConnection.java (Only modification to the original is adding of my specific user credentials)

RUNNING TO PROGRAM
Run ProgramDriver.java

NOTES FOR RUNNING PROGRAM
Program has 2 logins preset in the system.
 - Admin User
	Username: admin
	Password: adminpass
 - Member
	Username: jdoe
	Password: memberpass

Once logged a user can navigate their options based on whether they are a user or an admin.
  Admins also have the ability to login to a members account, this was mostly helpful to be able to test a variety of account senerios without creating a separate login for each member.

FILE INFORMATION
ProgramDriver.java
    	Main Program for Front End System
DBConnection.java
    	Connection between Front and Back End program, supplied by Professor
Project Datbase Code.sql
    	PL/SQL Package and Table Modification Code for the Back End System
