/* Testing the functionalities of Reservation and ReservationAssisstant
 * 
 */

//this scenario pretends that user bobby is already logged in and is performing some operations


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test 
{
	
	static Connection conn;
	static PreparedStatement ps;
	static DbManager db = new DbManager();
	
	public static void main(String args[])
	{
		
		Reservation R1 = new Reservation(12345, 4, 57, "2018-09-29");
		Reservation R2 = new Reservation(58981, 2, 31, "2018-10-12");
		Reservation R3 = new Reservation(58981, 3, 76, "2018-12-04");
		ReservationAssistant RA = new ReservationAssistant();
		
		
		String[][] output = new String[5][6];
		int status = 0;
		
		try
		{
			RA.createReservation(R1);
			RA.createReservation(R2);
			
			RA.cancelReservation(R3);
		}
		catch(ReservationException ex)
		{
			System.out.println(ex);
		}
		
		System.out.println("status: " + status);
		
		//System.out.println("Reservation made! " + status + " rows updated.");
		
		
		/*
		try
		{
			conn = db.getConnection();
			if (conn == null)
			{
				System.out.println("big oof");
				return;
			}
			ps = conn.prepareStatement("select * from parkingSpot");
			ResultSet rs = ps.executeQuery();
			
			if(rs == null)
			{
				System.out.println("rs is null");
			}
			else if(!rs.next())
			{
				System.out.println("rs not null, but no rows");
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		*/
		
		System.out.println("all done!");
		
	}
		
		
}
