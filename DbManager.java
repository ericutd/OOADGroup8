/*Author: Zack Oldham 
 * CS 6359.002
 * 09/28/2018
 * This program defines the DBmanager class which creates a connection to the parkingManagementSystem database.
 */


import java.sql.Connection;
import java.sql.DriverManager;

public class DbManager implements MyDB
{

	public Connection getConnection()
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection myConnection = DriverManager.getConnection(CONN_URL,USER,PASS);
			return myConnection;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
