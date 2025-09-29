// Elicia Pluymers
// CSCI45000 Assignment 3
// UpdateItemQty.java - Interface for commands

package browser.commands;

import common.StoreInterface;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class UpdateItemQty implements CommandInterface
{
	private String name;
	private int quantity;
	public static StoreInterface stub;
	
	UpdateItemQty(String tempN, int tempQ, StoreInterface s)
	{
		name = tempN;
		quantity = tempQ;
		stub = s;
	}
	
	public void execute()
	{
		try
		{
			boolean success = stub.updateItemQty(name, quantity);
			if(success)
			{
				System.out.println("Item Updated Returning to Menu");
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