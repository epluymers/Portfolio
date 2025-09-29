// Elicia Pluymers
// CSCI45000 Assignment 2
// StoreBrowser.java - Browser system main program calls server Store functions

package browser;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;
import common.StoreInterface;
import browser.commands.CommandFactory;
import browser.commands.CommandInterface;

public class StoreBrowser
{
	public static Scanner input = new Scanner(System.in);
	public static StoreInterface stub;
	public static CommandFactory factory;
	
	public static void main(String args[]) 
	{
		try //Attempt to call server functions
		{
			stub = (StoreInterface)Naming.lookup("//in-csci-rrpc01.cs.iupui.edu:9674/StoreInterface");
			factory = new CommandFactory(stub);
			
			boolean userExit = false;
			int userSelect = 1;
			
			while(!userExit)
			{
				System.out.print("Welcome to to Online Store.\n 1. Login\n 2. Register for and account\n 3. Exit\nPlease select an opton: ");
				userSelect = input.nextInt();
				input.nextLine();
				switch (userSelect)
				{
					case 1:
					  loginMenu();
						break;
					case 2:
					  registerMenu();
						break;
					case 3:
					  userExit = true;
						System.out.println("Exiting Online Store");
						break;
					default:
					  System.out.println("Invalid Input. Try Again.");
						break;
				}
			}
			
		} 
		catch(Exception e) //handle exception if connection fails
		{
			System.out.println("Client err : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void loginMenu()
	{
		try
		{
			boolean loginSuccess = false;
			while(!loginSuccess)
			{
				System.out.print("\nLogin Menu\n Please Enter Username: ");
				String tempUN = input.nextLine();
				if(tempUN.equals("e") || tempUN.equals("E"))
				{
					break;
				}
				else
				{
					System.out.print(" Please Enter Password: ");
					String tempP = input.nextLine();
					if(stub.verifyLogin(tempUN, tempP))
					{
						int type = stub.getUserType(tempUN);
						if(type == 0)
						{
							adminMenu();
						}
						else
						{
							customerMenu(tempUN);
						}
						loginSuccess = true;
					}
					else
					{
						System.out.println("Sorry your username and password are incorrect please try again or type 'e' to return to main menu.");
					}
				}
			}
	  } 
		catch(Exception e) //handle exception if connection fails
		{
			System.out.println("Client err : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void registerMenu()
	{
		try
		{
			System.out.print("Registration Menu\n Please Enter Username: ");
			String tempUN = input.nextLine();
			System.out.print(" Please Enter Password: ");
			String tempP = input.nextLine();
			System.out.print(" Please Enter Your Name: ");
			String tempN = input.nextLine();
			System.out.print(" Please Enter Your Address: ");
			String tempA = input.nextLine();
			System.out.print(" Please Enter Your Credit Card Number: ");
			String tempCC = input.nextLine();
			CommandInterface command = factory.addCustomerCommand(tempUN, tempP, tempN, tempA, tempCC);
			command.execute();
			System.out.print("Account created please login from main menu.");
		} 
		catch(Exception e) //handle exception is connection fails
		{
			System.out.println("Client err : " + e.getMessage());
			e.printStackTrace();
		}			
	}
	
	public static void adminMenu()
	{
			boolean userExit = false;
			int userSelect = 1;
			
			while(!userExit)
			{
				System.out.print("Admin Menu\n 1. Browse Items\n 2. Add Items \n 3. Browse Users\n 4. Add Admin Account \n5. Add Customer Account\n 6. Logout\nPlease select an opton: ");
				userSelect = input.nextInt();
				input.nextLine();
				switch (userSelect)
				{
					case 1:
					  browseItemsMenuAdmin();
						break;
					case 2:
					  addItemsMenu();
						break;
					case 3:
						browseUsers();
						break;
					case 4:
					  addAdmin();
						break;
					case 5:
						addCustomer();
						break;
					case 6:
						userExit = true;
						System.out.println("Logging Out");
						break;
					default:
					  System.out.println("Invalid Input. Try Again.");
						break;
				}
			}
	}
	
	public static void customerMenu(String username)
	{
			boolean userExit = false;
			int userSelect = 1;
			
			while(!userExit)
			{
				System.out.print("Customer Menu\n 1. Browse Items\n 2. Clear Shopping Cart\n 3. Check Out\n 4. Logout\nPlease select an opton: ");
				userSelect = input.nextInt();
				input.nextLine();
				switch (userSelect)
				{
					case 1:
					  browseItemsMenuCustomer(username);
						break;
					case 2:
					  clearShoppingCart(username);
						break;
					case 3:
					  checkOut(username);
						break;
					case 4:
						userExit = true;
						clearShoppingCart(username);
						System.out.println("Logging Out");
						break;
					default:
					  System.out.println("Invalid Input. Try Again.");
						break;
				}
			}
	}
	
	public static void browseItemsMenuAdmin()
	{
		try
		{
			boolean userExit = false;
			int userSelect = 1;
			
			while(!userExit)
			{
				CommandInterface command = factory.printAllItemsCommand();
				command.execute();
				System.out.print("Item Options\n 1. Delete Item\n 2. Update Item Quantity\n 3. Update Item Price\n 4. Return to Previous Menu\n Please select an opton: ");
				userSelect = input.nextInt();
				input.nextLine();
				switch (userSelect)
				{
					case 1:
					  itemDelete();
						break;
					case 2:
					  itemQtyUpdate();
						break;
					case 3:
						itemPriceUpdate();
						break;
					case 4:
						userExit = true;
						System.out.println("Returning to Previous Menu");
						break;
					default:
					  System.out.println("Invalid Input. Try Again.");
						break;
				}
			}
		}
		catch(Exception e) //handle exception is connection fails
		{
			System.out.println("Client err : " + e.getMessage());
			e.printStackTrace();
		}	
	}
	
	public static void addItemsMenu()
	{
		try
		{
			System.out.print("Add Item\n Please Enter Item Name: ");
			String tempN = input.nextLine();
			System.out.print(" Please Enter Price: ");
			Double tempP = input.nextDouble();
			input.nextLine();
			System.out.print(" Please Enter Description: ");
			String tempD = input.nextLine();
			System.out.print(" Please Enter Quantity: ");
			int tempQ = input.nextInt();
			input.nextLine();
			System.out.print(" Please Enter Type 1 for Product 2 for Service: ");
			int tempT = input.nextInt();
			input.nextLine();
			CommandInterface command = factory.addItemCommand(tempN, tempP, tempQ, tempD, tempT);
			command.execute();
			System.out.print("Item created returning to menu.");
		} 
		catch(Exception e) //handle exception is connection fails
		{
			System.out.println("Client err : " + e.getMessage());
			e.printStackTrace();
		}	
	}
	
	public static void browseUsers()
	{
		try
		{
			boolean userExit = false;
			int userSelect = 1;
			
			while(!userExit)
			{
				CommandInterface command = factory.printAllUsersCommand();
				command.execute();
				System.out.print("User Options\n 1. Delete User \n2. Return to Previous Menu\n Please select an opton: ");
				userSelect = input.nextInt();
				input.nextLine();
				switch (userSelect)
				{
					case 1:
					  userDelete();
						break;
					case 2:
						userExit = true;
						System.out.println("Returning to Previous Menu");
						break;
					default:
					  System.out.println("Invalid Input. Try Again.");
						break;
				}
			}
		}
		catch(Exception e) //handle exception is connection fails
		{
			System.out.println("Client err : " + e.getMessage());
			e.printStackTrace();
		}	
	}
	
	public static void addAdmin()
	{
		try
		{
			System.out.print("Add Administrator\n Please Enter Username: ");
			String tempUN = input.nextLine();
			System.out.print(" Please Enter Password: ");
			String tempP = input.nextLine();
			CommandInterface command = factory.addAdminCommand(tempUN,tempP);
			command.execute();
			System.out.println("Account created returning to menu.");
		} 
		catch(Exception e) //handle exception is connection fails
		{
			System.out.println("Client err : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void addCustomer()
	{
		try
		{
			System.out.print("Add Customer\n Please Enter Username: ");
			String tempUN = input.nextLine();
			System.out.print(" Please Enter Password: ");
			String tempP = input.nextLine();
			System.out.print(" Please Enter Name: ");
			String tempN = input.nextLine();
			System.out.print(" Please Enter Address: ");
			String tempA = input.nextLine();
			System.out.print(" Please Enter Credit Card Number: ");
			String tempCC = input.nextLine();
			CommandInterface command = factory.addCustomerCommand(tempUN, tempP, tempN, tempA, tempCC);
			command.execute();
			System.out.print("Account created returning to menu.");
		} 
		catch(Exception e) //handle exception is connection fails
		{
			System.out.println("Client err : " + e.getMessage());
			e.printStackTrace();
		}			
	}
	
	public static void browseItemsMenuCustomer(String username)
	{
		try
		{
			boolean userExit = false;
			int userSelect = 1;
			
			while(!userExit)
			{
				CommandInterface command = factory.printAllItemsCommand();
				command.execute();
				System.out.print("Item Options\n 1. Add Item to Cart 2. Return to Previous Menu\n Please select an opton: ");
				userSelect = input.nextInt();
				input.nextLine();
				switch (userSelect)
				{
					case 1:
					  addToCustomerCart(username);
						break;
					case 2:
						userExit = true;
						System.out.println("Returning to Previous Menu");
						break;
					default:
					  System.out.println("Invalid Input. Try Again.");
						break;
				}
			}
		}
		catch(Exception e) //handle exception is connection fails
		{
			System.out.println("Client err : " + e.getMessage());
			e.printStackTrace();
		}	
	}
	
	public static void clearShoppingCart(String username)
	{
		try
		{
			CommandInterface command = factory.clearShoppingCartCommand(username);
			command.execute();
		} 
		catch(Exception e) //handle exception is connection fails
		{
			System.out.println("Client err : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void checkOut(String username)
	{
		try
		{
			CommandInterface command = factory.customerCheckOutCommand(username);
			command.execute();
		} 
		catch(Exception e) //handle exception is connection fails
		{
			System.out.println("Client err : " + e.getMessage());
			e.printStackTrace();
		}
	}

  public static void itemDelete()
	{
		try
		{
			System.out.print("Delete Item\n Please Enter Item Name: ");
			String tempN = input.nextLine();
			CommandInterface command = factory.itemDeleteCommand(tempN);
			command.execute();
		} 
		catch(Exception e) //handle exception is connection fails
		{
			System.out.println("Client err : " + e.getMessage());
			e.printStackTrace();
		}		
	}
	
  public static void itemQtyUpdate()
	{
		try
		{
			System.out.print("Update Item Quantity\n Please Enter Item Name: ");
			String tempN = input.nextLine();
			System.out.print("Please Enter Updated Quantity: ");
			int tempQ = input.nextInt();
			input.nextLine();
			CommandInterface command = factory.updateItemQtyCommand(tempN, tempQ);
			command.execute();
		} 
		catch(Exception e) //handle exception is connection fails
		{
			System.out.println("Client err : " + e.getMessage());
			e.printStackTrace();
		}
	}

  public static void itemPriceUpdate()
	{
		try
		{
			System.out.print("Update Item Quantity\n Please Enter Item Name: ");
			String tempN = input.nextLine();
			System.out.print("Please Enter Updated Price: ");
			double tempP = input.nextDouble();
			input.nextLine();
			CommandInterface command = factory.updateItemPriceCommand(tempN, tempP);
			command.execute();
		} 
		catch(Exception e) //handle exception is connection fails
		{
			System.out.println("Client err : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void userDelete()
	{
		try
		{
			System.out.print("Delete User\n Please Enter User Name: ");
			String tempUN = input.nextLine();
			CommandInterface command = factory.deleteUserCommand(tempUN);
			command.execute();
		} 
		catch(Exception e) //handle exception is connection fails
		{
			System.out.println("Client err : " + e.getMessage());
			e.printStackTrace();
		}		
	}
	
	public static void addToCustomerCart(String username)
	{
		try
		{
			System.out.print("Add To Cart\n Please Enter Item Name: ");
			String tempN = input.nextLine();
			System.out.print("Add To Cart\n Please Enter Item Qty: ");
			int tempQ = input.nextInt();
			input.nextLine();
			CommandInterface command = factory.addToCartCommand(username, tempN, tempQ);
			command.execute();
		} 
		catch(Exception e) //handle exception is connection fails
		{
			System.out.println("Client err : " + e.getMessage());
			e.printStackTrace();
		}		
	}
}