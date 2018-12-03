<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@page import="application.vehicle.VehicleService"%>
<%@ page import="pojo.Vehicle" %>
<%@page import="application.user.UserService"%>
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
		HttpSession session_user1 = request.getSession();
		int userId = Integer.parseInt(session_user1.getAttribute("userId").toString());
		request.setAttribute("userdata", service1.getUser(userId));		
	%>

	<%   
		VehicleService service = new VehicleService();
		List<Vehicle> vehicleList = new ArrayList<>();
		vehicleList = service.getVehicles(userId);
		System.out.println(vehicleList.get(0).getMake());
		request.setAttribute("vehicleList", vehicleList);
	%>
	
	<div class="container">
      <div class="row">
        <div class="col-lg-12 text-center">
          <h2 class="mt-5"> ${userdata.name}!!</h2>
         
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
          </h3>
        </div>
      </div>
    </div>

</body>
</html>