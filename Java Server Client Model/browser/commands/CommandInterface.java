// Elicia Pluymers
// CSCI45000 Assignment 3
// CommandInterface.java - Interface for commands

package browser.commands;

import java.rmi.*;

public interface CommandInterface extends Remote
{
	public void execute() throws RemoteException;
}