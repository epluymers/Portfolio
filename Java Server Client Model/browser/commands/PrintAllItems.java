// Elicia Pluymers
// CSCI45000 Assignment 3
// PrintAllItems.java - Interface for commands

package browser.commands;

import common.StoreInterface;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class PrintAllItems implements CommandInterface
{
	public static StoreInterface stub;
	
	PrintAllItems(StoreInterface s)
	{
		stub = s;
	}
	
	public void execute()
	{
		try
		{
			System.out.println(stub.printAllItems());
		}
		catch(Exception e) //handle exception if connection fails
		{
			System.out.println("Client err : " + e.getMessage());
			e.printStackTrace();
		}
	}
}