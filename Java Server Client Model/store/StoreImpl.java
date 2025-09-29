// Elicia Pluymers
// CSCI45000 Assignment 2
// StoreImpl.java - Implementation of a Online Store

package store;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import common.StoreInterface;
import java.lang.Math;
import java.util.Vector;

//
// StoreImpl
// Class holds basic account information and handles basic account actions
//
public class StoreImpl extends UnicastRemoteObject implements StoreInterface
{
	private Vector<StoreItem> items;
	private Vector<StoreUser> users;
	//
	// default constructor
	//
	StoreImpl() throws RemoteException
	{
		items = new Vector<StoreItem>(0);
		users = new Vector<StoreUser>(0);
		StoreUser defaultAdmin = new StoreAdmin("admin", "password");
		users.add(defaultAdmin);
	}
	
	//
	// verifyLogin
	//
	public boolean verifyLogin(String un, String pw)
	{
		for(int i = 0; i < users.size(); i++)
		{
			if(users.get(i).verify(un, pw))
			{
				return true;
			}
		}
		return false;
	}
	
	public int getUserType(String un)
	{
		return findUser(un).userType;
	}
	
	//
	// addCustomer
	//
	public void addCustomer(String un, String pw, String n, String a, String cc)
	{
		StoreUser tempC = new StoreCustomer(un, pw, n, a, cc);
		users.add(tempC);
	}
	
	//
	// addAdmin
	//
	public void addAdmin(String un, String pw)
	{
		StoreUser tempA = new StoreAdmin(un, pw);
		users.add(tempA);
	}
	
	//
	// addItem
	//
  public void addItem(String n, double p, int q, String d, int i)
	{
		StoreItem temp = new StoreItem(n,p,q,d,i);
		items.add(temp);
	}
	
  public String returnItem(int i)
	{
		return items.get(i).print();
	}
	
	public boolean deleteItem(String n)
	{
		if(items.size() == 0)
		{
			return false;
		}
		for(int i = 0; i < items.size(); i++)
		{
			if(items.get(i).name.equals(n))
			{
				items.removeElementAt(i);
				return true;
			}
		}
		return false;
	}
	
	public boolean deleteUser(String un)
	{
		if(users.size() == 0)
		{
			return false;
		}
		for(int i = 0; i < users.size(); i++)
		{
			if(users.get(i).username.equals(un))
			{
				users.removeElementAt(i);
				return true;
			}
		}
		return false;
	}
	
	public boolean updateItemPrice(String n, double p)
	{
		if(items.size() == 0)
		{
			return false;
		}
		for(int i = 0; i < items.size(); i++)
		{
			if(items.get(i).name.equals(n))
			{
				items.get(i).price = p;
				return true;
			}
		}
		return false;
	}
	
	public boolean updateItemQty(String n, int q)
	{
		if(items.size() == 0)
		{
			return false;
		}
		for(int i = 0; i < items.size(); i++)
		{
			if(items.get(i).name.equals(n))
			{
				items.get(i).quantity = q;
				return true;
			}
		}
		return false;
	}
	
	public String returnUser(int i)
	{
		return users.get(i).print();
	}
	
	public String printAllItems()
	{
		String tempS = "";
		if(items.size() == 0)
		{
			tempS = "Sorry There are No Items to Display";
		}
		else
		{
			for(int i = 0; i < items.size(); i++)
			{
				tempS += returnItem(i) + "\n";
			}
		}
	  return tempS;	
	}
	
	public String printAllUsers()
	{
		String tempS = "";
		if(users.size() == 0)
		{
			tempS = "Sorry There are No Users to Display";
		}
		else
		{
			for(int i = 0; i < users.size(); i++)
			{
				tempS += returnUser(i) + "\n";
			}
		}
	  return tempS;
	}
	
	public void clearCustomerShoppingCart(String un)
	{
		StoreCustomer tempU = (StoreCustomer)findUser(un);
		if(tempU.shoppingCart.size() != 0)
		{
			for(int i = 0; i < tempU.shoppingCart.size(); i++)
			{
				StoreItem tempS = findItem(tempU.shoppingCart.get(i).name);
				tempS.quantity += tempU.shoppingCart.get(i).quantity;
			}
			tempU.shoppingCart.clear();
		}
	}
	
	public double customerCheckOut(String un)
	{
		StoreCustomer tempU = (StoreCustomer)findUser(un);
		if(tempU.shoppingCart.size() == 0)
		{
			return 0.0;
		}
		double cartTotal = 0.0;
		for(int i = 0; i < tempU.shoppingCart.size(); i++)
		{
			cartTotal += tempU.shoppingCart.get(i).quantity * tempU.shoppingCart.get(i).price;
		}
		tempU.shoppingCart.clear();
		return cartTotal;
	}
	
	public boolean addToCart(String un, String n, int q)
	{
		StoreItem tempI = findItem(n);
		if(tempI.quantity >= q)
		{
			StoreCustomer tempU = (StoreCustomer)findUser(un);
			tempI.quantity = tempI.quantity - q;
			StoreItem tempSI = new StoreItem(tempI.name, tempI.price, q, tempI.description, tempI.itemType);
			tempU.shoppingCart.add(tempSI);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private StoreUser findUser(String un)
	{
		for(int i = 0; i < users.size(); i++)
		{
			if(users.get(i).username.equals(un))
			{
				return users.get(i);
			}
		}
		return null;
	}
	
	private StoreItem findItem(String n)
	{
		for(int i = 0; i < items.size(); i++)
		{
			if(items.get(i).name.equals(n))
			{
				return items.get(i);
			}
		}
		return null;
	}
}