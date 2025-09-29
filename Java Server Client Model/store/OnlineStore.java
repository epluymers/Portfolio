// Elicia Pluymers
// CSCI45000 Assignment 2
// OnlineStore.java - Server system main program opens server side to remote function calls

package store;

import java.rmi.*;
import java.rmi.registry.*;
import common.StoreInterface;

public class OnlineStore
{
	public static void main(String args[]) 
	{
		try //Attempt to open remote connection on server
		{
			StoreInterface remote_obj = new StoreImpl();
			Naming.rebind("//in-csci-rrpc01.cs.iupui.edu:9674/StoreInterface",remote_obj);
			System.out.println("Online Store is Running");
		}
		catch(Exception e) //handle exception if connection fails
		{
			System.out.println("Server err: " + e.getMessage());
			e.printStackTrace();
		}
	}
}