// Elicia Pluymers
// CSCI45000 Assignment 3
// AddItem.java - Interface for commands

package browser.commands;

import common.StoreInterface;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class AddItem implements CommandInterface
{
	private String name;
	private double price;
	private int quantity;
	private String description;
	private int type;
	public static StoreInterface stub;
	
	AddItem(String tempN, double tempP, int tempQ, String tempD, int tempT, StoreInterface s)
	{
		name = tempN;
		price = tempP;
		quantity = tempQ;
		description = tempD;
		type = tempT;
		stub = s;
	}
	public void execute()
	{
		try
		{
			stub.addItem(name, price, quantity, description, type);
		}
		catch(Exception e) //handle exception if connection fails
		{
			System.out.println("Client err : " + e.getMessage());
			e.printStackTrace();
		}
	}
}