// Elicia Pluymers
// CSCI45000 Assignment 3
// AddCustomer.java - Interface for commands

package browser.commands;

import common.StoreInterface;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class AddCustomer implements CommandInterface
{
	private String username;
	private String password;
	private String name;
	private String address;
	private String creditCard;
	public static StoreInterface stub;
	
	AddCustomer(String tempUN, String tempP, String tempN, String tempA, String tempCC, StoreInterface s)
	{
		username = tempUN;
		password = tempP;
		name = tempN;
		address = tempA;
		creditCard = tempCC;
		stub = s;
	}
	
	public void execute()
	{
		try
		{
			stub.addCustomer(username, password, name, address, creditCard);
		}
		catch(Exception e) //handle exception if connection fails
		{
			System.out.println("Client err : " + e.getMessage());
			e.printStackTrace();
		}
	}
}