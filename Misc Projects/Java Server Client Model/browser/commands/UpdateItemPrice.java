// Elicia Pluymers
// CSCI45000 Assignment 3
// UpdateItemPrice.java - Interface for commands

package browser.commands;

import common.StoreInterface;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class UpdateItemPrice implements CommandInterface
{
	private String name;
	private double price;
	public static StoreInterface stub;
	
	UpdateItemPrice(String tempN, double tempP, StoreInterface s)
	{
		name = tempN;
		price = tempP;
		stub = s;
	}
	
	public void execute()
	{
		try
		{
			boolean success = stub.updateItemPrice(name, price);
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