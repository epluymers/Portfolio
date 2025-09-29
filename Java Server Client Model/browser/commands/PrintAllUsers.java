// Elicia Pluymers
// CSCI45000 Assignment 3
// PrintAllUsers.java - Interface for commands

package browser.commands;

import common.StoreInterface;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class PrintAllUsers implements CommandInterface
{
	public static StoreInterface stub;
	
	PrintAllUsers(StoreInterface s)
	{
		stub = s;
	}
	
	public void execute()
	{
		try
		{
			System.out.println(stub.printAllUsers());
		}
		catch(Exception e) //handle exception if connection fails
		{
			System.out.println("Client err : " + e.getMessage());
			e.printStackTrace();
		}
	}
}