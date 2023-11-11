// Elicia Pluymers
// CSCI45000 Assignment 2
// StoreUser.java - Online Store User

package store;

public abstract class StoreUser
{
	protected String username;
	protected String password;
	protected int userType;
	
	public boolean verify(String un, String pw)
	{
		if(username.equals(un) && password.equals(pw))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String print()
	{
		String temp = "";
		if(userType == 0)
		{
			temp += "Admin User \n";
		}
		else
		{
			temp += "Customer \n";
		}
		temp += username;
		return temp;
	}
}