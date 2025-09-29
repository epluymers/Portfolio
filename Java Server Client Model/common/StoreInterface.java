// Elicia Pluymers
// CSCI45000 Assignment 2
// StoreInterface.java - Interface for Online Store

package common;

import java.rmi.*;

public interface StoreInterface extends Remote
{
	public void addItem(String n, double p, int q, String d, int i) throws RemoteException;
	public String returnItem(int i) throws RemoteException;
	public boolean deleteItem(String n) throws RemoteException;
	public boolean deleteUser(String un) throws RemoteException;
	public boolean updateItemPrice(String n, double p) throws RemoteException;
	public boolean updateItemQty(String n, int q) throws RemoteException;
	public String returnUser(int i) throws RemoteException;
	public String printAllItems() throws RemoteException;
	public String printAllUsers() throws RemoteException;
	public boolean verifyLogin(String un, String pw) throws RemoteException;
	public int getUserType(String un) throws RemoteException;
	public void addCustomer(String un, String pw, String n, String a, String cc) throws RemoteException;
	public void addAdmin(String un, String pw) throws RemoteException;
	public void clearCustomerShoppingCart(String un) throws RemoteException;
	public double customerCheckOut(String un) throws RemoteException;
	public boolean addToCart(String un, String n, int q) throws RemoteException;
}