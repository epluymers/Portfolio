// Elicia Pluymers
// CSCI45000 Assignment 3
// DeleteUser.java - Interface for commands

package browser.commands;

import common.StoreInterface;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class DeleteUser implements CommandInterface
{
	private String username;
	public static StoreInterface stub;
	
	DeleteUser(String tempUN, StoreInterface s)
	{
		username = tempUN;
		stub = s;
	}
	
	public void execute()
	{
		try
		{
			boolean success = stub.deleteUser(username);
			if(success)
			{
				System.out.println("User Deleted Returning to Menu");
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