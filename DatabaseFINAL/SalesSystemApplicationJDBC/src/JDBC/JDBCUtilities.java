package JDBC;

/**
 * A utility class to provide a JDBC connection to the application
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtilities
{
	private static Connection conn;
	// Requires running Sql Server Configuration Manager, opening SQL Server Network Configuration
	//    And enabling TCP/IP, then restarting SQL server
	//    AND enabling mixed mode authentication.
	//    You'll have to assign the sa account a password, or better yet create an account just for this application
	private static String connectionString = "jdbc:sqlserver://localhost\\MSSQLSERVER01;DatabaseName=SalesSystem;user=sa;password=Password1234!;MultipleActiveResultSets=True"; 
	static
	{
		try
		{
			conn = DriverManager.getConnection(connectionString);
		}
		catch (SQLException ex)
		{
			conn = null;
			ex.printStackTrace();
		}		
	}
	
	public static Connection getConnection()
	{
		return conn;
	}
}
