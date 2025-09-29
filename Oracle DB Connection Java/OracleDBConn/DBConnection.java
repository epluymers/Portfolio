package OracleDBConn;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection 
{
	private static DBConnection instance = new DBConnection();
	
	private Connection connection;
	
	private DBConnection() 
	{
		try 
		{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Elicia", "Binx2019");
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
	}

	public static DBConnection getInstance() 
	{
		return instance;
	}
	
	public Connection getConnection() 
	{
		return connection;
	}
}
