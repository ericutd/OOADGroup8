/* Author: Zack Oldham
 * CS 6359.002
 * 09/27/2018
 * This class defines the InvalidReservationException which can be thrown to indicate issues reserving a parking spot.
 */


public class ReservationException extends Exception
{
	public ReservationException(){super();}
	
	public ReservationException(String message){super(message);}
	
}
