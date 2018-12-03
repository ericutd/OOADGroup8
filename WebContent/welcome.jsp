<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@page import="application.vehicle.VehicleService"%>
<%@ page import="pojo.Vehicle" %>
<%@ page import="pojo.ParkingSpot" %>
<%@page import="application.user.UserService"%>
<%@page import="application.reservation.ParkingLot"%>
<%@ page import="others.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome Page</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">

<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
</style>
</head>
<body>
	
   <jsp:include page="header.jsp"/>
   <%   
		UserService service1 = new UserService();
   		VehicleService service = new VehicleService();
   		ParkingLot parkingLotService = new ParkingLot();
		HttpSession session_user1 = request.getSession();
		String user = String.valueOf(session_user1.getAttribute("userId"));
		 
		
		List<Vehicle> vehicleList = new ArrayList<>();
		if(user != null) {
			int userId = Integer.parseInt(user);
			request.setAttribute("userdata", service1.getUser(userId));		 
			vehicleList = service.getVehicles(userId);
			request.setAttribute("vehicleList", vehicleList);
			List<ParkingSpot> spots = parkingLotService.getSpots();
			request.setAttribute("spots", spots);
		}
		
	%>
	
	<div class="container">
      <div class="row">
        <div class="col-lg-12 text-center">
          <h2 class="mt-5"> Welcome ${userdata.name}!!</h2>
         
         <h3>Your Vehicles</h3> 
			<h3>
         	<table style="width:100%">
         	  <tr>
         	  	<th>Make</th>
         	  	<th>Year</th>
         	  	<th>Model</th>
         	  	<th>Color</th>
         	  </tr>	
			  <c:forEach items="${vehicleList}" var="vehicle">
			  		<tr>
			  			<td> ${vehicle.make} </td> 
			  			<td> ${vehicle.year} </td> 
			  			<td> ${vehicle.model} </td>
			  			<td> ${vehicle.color} </td>
			  		</tr>
			  	
			  </c:forEach>
			</table>
			<br>
          </h3>
         
        </div>
      </div>
    </div>


	<div>
		<div class="col-lg-12 text-center">
			<h3>Spots</h3>
         <h3>
         	<table style="width:100%">
         	  <tr>
         	  	<th>Lot ID</th>
         	  	<th>SpotID</th>
         	  	<th>Occupied</th>
         	  	<th>License Number</th>
         	  	<th>Model</th>
         	  	<th>Color</th>
         	  </tr>	
			  <c:forEach items="${spots}" var="spots">
			  		<tr>
			  			<td> ${spots.lotId} </td>
			  			<td> ${spots.spotId} </td> 
			  			<td> ${spots.occupied} </td> 
			  			<td> ${spots.currentVehicle.licenseNum} </td>
			  			<td> ${spots.currentVehicle.model} </td>
			  			<td> ${spots.color} </td>
			  		</tr>
			  	
			  </c:forEach>
			</table>
			<br>
          </h3>
			         
		</div>
	</div>



</body>
</html>