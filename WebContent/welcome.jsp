<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@page import="application.vehicle.VehicleService"%>
<%@ page import="pojo.Vehicle" %>
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
	
   <nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
      <div class="container">
        <a class="navbar-brand" href="#">Parking Management Service</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link" href="">Home
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="select.jsp">Select a Spot</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="manageaccount.jsp">Manage Account</a>
            </li>
            <%
            //String acctType= (String)request.getAttribute("acctType");
            HttpSession session1= request.getSession();
    		String acctType = (String)session1.getAttribute("acctType");
            if(acctType.equals("Admin")){
            %>
              <li class="nav-item" >
              <a class="nav-link" href="admin.jsp">Manage Parking</a>
            </li>
            <% }else{ %>
              <li class="nav-item" style="display:none;">
              <a class="nav-link" href="admin.jsp">Manage Parking</a>
            </li>
            <%} %>
            <li class="nav-item">
              <a class="nav-link" href="permit.jsp">Add Permit</a> 
            </li>
            <li class="nav-item">
              <a class="nav-link" href="logout.jsp">Logout</a> 
            </li>
          </ul>
        </div>
      </div>
    </nav>

	<%   
		VehicleService service = new VehicleService();
		HttpSession session_user = request.getSession();
		String user = String.valueOf(session_user.getAttribute("userId"));
		System.out.println(user);
		List<Vehicle> vehicleList = new ArrayList<>();
		if(user != null) {
			int userId = Integer.parseInt(user);
			vehicleList = service.getVehicles(userId);
			System.out.println(vehicleList);
		}
		request.setAttribute("vehicleList", vehicleList);
	%>
	
	<div class="container">
      <div class="row">
        <div class="col-lg-12 text-center">
          <h2 class="mt-5">${message}!!</h2>
         
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