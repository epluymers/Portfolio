// Elicia Pluymers
// CSCI45000 Assignment 3
// ItemDelete.java - Interface for commands

package browser.commands;

import common.StoreInterface;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class ItemDelete implements CommandInterface
{
	private String name;
	public static StoreInterface stub;
	
	ItemDelete(String tempN, StoreInterface s)
	{
		name = tempN;
		stub = s;
	}
	
	public void execute()
	{
		try
		{
			boolean success = stub.deleteItem(name);
			if(success)
			{
				System.out.println("Item Deleted Returning to Menu");
			}
			else
			{
				System.out.println("Sorry no Such Item Exists Returning to Menu");
			}
		}
		catch(Exception e) //handle exception if connection fails
		{
			System.out.println("Client err : " + e.getMessage());
			e.printStackTrace();
		}
	}
}