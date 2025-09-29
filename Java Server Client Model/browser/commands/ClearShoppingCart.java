// Elicia Pluymers
// CSCI45000 Assignment 3
// ClearShoppingCart.java - Interface for commands

package browser.commands;

import common.StoreInterface;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class ClearShoppingCart implements CommandInterface
{
	private String username;
	public static StoreInterface stub;
	
	ClearShoppingCart(String un, StoreInterface s)
	{
		username = un;
		stub = s;
	}
	
	public void execute()
	{
		try
		{
			stub.clearCustomerShoppingCart(username);
		}
		catch(Exception e) //handle exception if connection fails
		{
			System.out.println("Client err : " + e.getMessage());
			e.printStackTrace();
		}
	}
}