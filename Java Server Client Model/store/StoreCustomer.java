// Elicia Pluymers
// CSCI45000 Assignment 2
// StoreCustomer.java - Online Store Customer

package store;

import java.util.Vector;

public class StoreCustomer extends StoreUser
{
	protected String name;
	protected String address;
	protected String creditCardNumber;
	protected Vector<StoreItem> shoppingCart;
	
	StoreCustomer(String un, String pw, String n, String a, String cc)
	{
		username = un;
		password = pw;
		name = n;
		address = a;
		creditCardNumber = cc;
		userType = 1;
		shoppingCart = new Vector<StoreItem>(0);
	}
	
}