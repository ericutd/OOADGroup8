//package domain.login;
///* Author: Zack Oldham
// * CS 6359.002
// * 09/30/2018
// * This class defines the reservationController which allows the user to create/cancel/display reservations using a GUI 
// */
//
//
//
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@WebServlet("/ReservationController")
//public class ReservationController extends HttpServlet
//{
//	private static final long serialVersionUID = 1L;
//	private Reservation R;
//	private ReservationAssistant ra = new ReservationAssistant();
//	
//	
//	
//	public ReservationController(){}
//	
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
//	{
//		int parkingLotId = Integer.parseInt(request.getParameter("lotId"));
//		int parkingSpotId = Integer.parseInt(request.getParameter("spotId"));
//		String date = request.getParameter("reservationDate");
//		HttpSession session = request.getSession();
//		int userId = (int)session.getAttribute("userId");
//		R = new Reservation(userId, parkingLotId, parkingSpotId, date);
//		
//		try
//	    {
//			ra.createReservation(R);
//		}
//		catch(Exception ex)
//		{
//			request.setAttribute("message", " " + ex);
//			//request.getRequestDispatcher("reservation.jsp").forward(request, response);
//		}
//			
//		request.setAttribute("message", " Success! You have reserved spot number " + parkingSpotId + "in lot number " + parkingLotId + "on " + date); 
//		request.getRequestDispatcher("reservation.jsp").forward(request, response);
//	}
//}
