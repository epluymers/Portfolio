package COURSEPROJECTELICIAPLUYMERS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection 
{
	private static DBConnection instance = new DBConnection();
	
	private Connection connection;
	
	private DBConnection() 
	{
		try 
		{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Elicia","DH1");
		
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
