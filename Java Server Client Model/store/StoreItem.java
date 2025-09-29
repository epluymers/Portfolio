// Elicia Pluymers
// CSCI45000 Assignment 2
// StoreItem.java - Item for Online StoreItem

package store;

public class StoreItem
{

	protected String name;
	protected double price;
	protected int quantity;
	protected String description;
	protected int itemType;	
	
	StoreItem(String n, double p, int q, String d, int i)
	{
		name = n;
		price = Math.round(p*100)/100;
		quantity = q;
		description = d;
		itemType = i;
	}
	
	public String print()
	{
		String temp = "";
		if(itemType == 1)
		{
			temp += "Product Name: ";
		}
		else
		{
			temp += "Service Name: ";
		}
		temp += name + "\n" + description + "\nPrice $" + Math.round(price*100)/100 + "\nQty: " + quantity;
		return temp;
	}
	
}