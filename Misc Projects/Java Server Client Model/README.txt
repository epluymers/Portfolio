Elicia Pluymers
CSCI 45000 Assignment 3

Files
Domain Model.jpg
- Image of Domain Model

Use Case Diagram.jpg
- Image of Domain Model

Program Run Print Out.txt
- Sample Run of code running on server

store/OnlineStore.java
- Runs the Server connection
- Set to run on in-csci-rrpc01.cs.iupui.edu

store/StoreImpl.java
- Class that allows store interaction such as purchasing items and modifing them

store/StoreItem.java
- Class contains item data used in StoreImpl.java

store/StoreUser.java
- Class contains user data used in StoreImpl.java

store/StoreAdmin.java
- Class extended from StoreUser.java containing user data specific to Admin users

store/StoreCustomer.java
- Class extended from StoreUser.java containing user data specific to Customers

browser/StoreInterface.java
- Interface for customers and administrators to access data

browser/StoreBrowser.java
- Class that runs user interface
- Set to interact with a server on in-csci-rrpc01.cs.iupui.edu

browser/commands/CommandFactory.java
- Class that produces the commands for the user interface
- Acts as a Front Controller for the user interface

browser/commands/CommandInterface.java
- Interface that implements the execute function for all command classes

browser/commands/AddAdmin.java
browser/commands/AddCustomer.java
browser/commands/AddItem.java 
browser/commands/AddToCart.java 
browser/commands/ClearShoppingCart.java 
browser/commands/CustomerCheckOut.java
browser/commands/DeleteUser.java
browser/commands/ItemDelete.java
browser/commands/PrintAllItems.java
browser/commands/PrintAllUsers.java
browser/commands/UpdateItemPrice.java
browser/commands/UpdateItemQty.java
- Command classes produced by the Command Factory
- Executes processes with the server side 

makefile
- Contains running commands to compile and start the program steps for using are below
1. execute "make" on both server and client systems to compile code
2. execute "make startreg" on server
	- perform a manual check for open rmiregistry prior to running command
		1. execute ps eaf | grep rmiregistry
		2. if one is currently running execute kill -9 <pid>
3. execute "make runserver" on server system
4. execute "make runclient" on client system
5. after finished manually close rmiregistry using steps detailed in step 2

Assignment3Server.jar
- Executable jar file for the server side of program
- RMI Registry needs to be started prior to running the jar file.
	- perform a manual check for open rmiregistry prior to running command
		1. execute ps eaf | grep rmiregistry
		2. if one is currently running execute kill -9 <pid>
	- start registry by either typing "make startreg" or by typing "rmiregistry 9674 &"
- Run jar file by typing java -jar Assignment3Server.jar
- After finished manually close rmiregistry using steps detailed in step 2 above

Assignment3Client.jar
- Executable jar file for the client side of program
- Run jar file by typing java -jar Assignment3Client.jar