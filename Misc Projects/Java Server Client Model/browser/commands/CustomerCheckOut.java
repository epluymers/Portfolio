// Elicia Pluymers
// CSCI45000 Assignment 3
// CustomerCheckOut.java - Interface for commands

package browser.commands;

import common.StoreInterface;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class CustomerCheckOut implements CommandInterface
{
	private String username;
	public static StoreInterface stub;
	
	CustomerCheckOut(String un, StoreInterface s)
	{
		username = un;
		stub = s;
	}
	
	public void execute()
	{
		try
		{
			double totalP = stub.customerCheckOut(username);
			System.out.println("Checking out your total purchase was $" + Math.round(totalP*100)/100);
		}
		catch(Exception e) //handle exception if connection fails
		{
			System.out.println("Client err : " + e.getMessage());
			e.printStackTrace();
		}
	}
}