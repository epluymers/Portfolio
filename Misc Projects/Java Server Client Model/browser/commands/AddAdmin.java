// Elicia Pluymers
// CSCI45000 Assignment 3
// AddAdmin.java - Interface for commands

package browser.commands;

import common.StoreInterface;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class AddAdmin implements CommandInterface
{
	private String username;
	private String password;
	public static StoreInterface stub;
	
	AddAdmin(String tempUN, String tempP, StoreInterface s)
	{
		username = tempUN;
		password = tempP;
		stub = s;
	}
	
	public void execute()
	{
		try
		{
			stub.addAdmin(username, password);
		}
		catch(Exception e) //handle exception if connection fails
		{
			System.out.println("Client err : " + e.getMessage());
			e.printStackTrace();
		}
	}
}