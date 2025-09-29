// Elicia Pluymers
// CSCI45000 Assignment 3
// CommandFactory.java - Creates commands that interface with the online store

package browser.commands;

import common.StoreInterface;

public class CommandFactory
{
	public static StoreInterface stub;
	
	public CommandFactory(StoreInterface s)
	{
		stub = s;
	}
	
	public CommandInterface addItemCommand(String tempN, double tempP, int tempQ, String tempD, int tempT)
	{
	  AddItem tempCommand = new AddItem(tempN, tempP, tempQ, tempD, tempT, stub);
	  return tempCommand;
	}
	
	public CommandInterface printAllUsersCommand()
	{
		PrintAllUsers tempCommand = new PrintAllUsers(stub);
		return tempCommand;
	}

	public CommandInterface addAdminCommand(String tempUN, String tempP)
	{
		AddAdmin tempCommand = new AddAdmin(tempUN, tempP, stub);
		return tempCommand;
	}
	
	public CommandInterface addCustomerCommand(String tempUN, String tempP, String tempN, String tempA, String tempCC)
	{
		AddCustomer tempCommand = new AddCustomer(tempUN, tempP, tempN, tempA, tempCC, stub);
		return tempCommand;
	}
	
	public CommandInterface printAllItemsCommand()
	{
		PrintAllItems tempCommand = new PrintAllItems(stub);
		return tempCommand;
	}
	
	public CommandInterface clearShoppingCartCommand(String un)
	{
		ClearShoppingCart tempCommand = new ClearShoppingCart(un, stub);
		return tempCommand;
	}
	
	public CommandInterface customerCheckOutCommand(String un)
	{
		CustomerCheckOut tempCommand = new CustomerCheckOut(un, stub);
		return tempCommand;
	}
	
	public CommandInterface itemDeleteCommand(String tempN)
	{
		ItemDelete tempCommand = new ItemDelete(tempN, stub);
		return tempCommand;
	}
	
	public CommandInterface updateItemQtyCommand(String tempN, int tempQ)
	{
		UpdateItemQty tempCommand = new UpdateItemQty(tempN, tempQ, stub);
		return tempCommand;
	}
	
	public CommandInterface updateItemPriceCommand(String tempN, double tempP)
	{
		UpdateItemPrice tempCommand = new UpdateItemPrice(tempN, tempP, stub);
		return tempCommand;
	}
	
	public CommandInterface deleteUserCommand(String tempUN)
	{
		DeleteUser tempCommand = new DeleteUser(tempUN, stub);
		return tempCommand;
	}
	
	public CommandInterface addToCartCommand(String tempUN, String tempN, int tempQ)
	{
		AddToCart tempCommand = new AddToCart(tempUN, tempN, tempQ, stub);
		return tempCommand;
	}
}