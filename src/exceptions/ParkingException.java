package exceptions;
/* Author: Zack Oldham
 * CS 6359.002
 * 09/27/2018
 * This class defines the InvalidReservationException which can be thrown to indicate issues reserving a parking spot.
 */


public class ParkingException extends Exception
{
	public ParkingException(){super();}
	
	public ParkingException(String message){super(message);}
	
}
