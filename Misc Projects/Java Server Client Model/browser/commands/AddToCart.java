// Elicia Pluymers
// CSCI45000 Assignment 3
// AddToCart.java - Interface for commands

package browser.commands;

import common.StoreInterface;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class AddToCart implements CommandInterface
{
	private String username;
	private String name;
	private int quantity;
	public static StoreInterface stub;
	
	AddToCart(String tempUN, String tempN, int tempQ, StoreInterface s)
	{
		username = tempUN;
		name = tempN;
		quantity = tempQ;
		stub = s;
	}
	
	public void execute()
	{
		try
		{
			boolean success = stub.addToCart(username, name, quantity);
			if(success)
			{
				System.out.println("Item Added to Your Cart Returning to Menu");
			}
			else
			{
				System.out.println("Sorry Item or Quantity is Invalid Returning to Menu");
			}
		}
		catch(Exception e) //handle exception if connection fails
		{
			System.out.println("Client err : " + e.getMessage());
			e.printStackTrace();
		}
	}
}