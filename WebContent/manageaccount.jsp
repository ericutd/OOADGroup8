<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@page import="application.vehicle.VehicleService"%>
<%@ page import="pojo.Vehicle" %>
<%@page import="application.user.UserService"%>
<%@ page import="others.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="script.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ManageAccount</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<form name="updateuserform" action="VehicleController" method="post" >
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
      <div class="container">
        <a class="navbar-brand" href="#">Parking Management Service</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link" href="welcome.jsp">Home
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
              <a class="nav-link" href="logout.jsp">Logout</a> 
            </li>
          </ul>
        </div>
      </div>
    </nav>
	<br>
	<h4>Manage Account</h4>
	
	<%   
		UserService service = new UserService();
		HttpSession session_user = request.getSession();
		String user = String.valueOf(session_user.getAttribute("userId"));
		System.out.println(user);
		User u= new User();
		if(user != null) {
			int userId = Integer.parseInt(user);
			u = service.getUser(userId);
			//System.out.println(vehicleList);
		}
		request.setAttribute("userdata", u);
	%>
	
	<h6>Manage User Profile:</h6>
	
	<div id="username_error"></div><br>
	
	Name: <input type="text" name="name" value= "${userdata.name}" ><br>
	
	Password: <input type="password" name="password" id="password" value= "${userdata.password}" > 
	<br>
	Email: <input type="text" name="email" id ="email" value= "${userdata.email}" >
	<br>
	
	<input type="submit" name="submit" value="UpdateUserDetails" >	
	</form><br>
	
	<%   
		VehicleService service1 = new VehicleService();
		/* HttpSession session_user = request.getSession();
		String user = String.valueOf(session_user.getAttribute("userId"));
		System.out.println(user); */
		List<Vehicle> vehicleList = new ArrayList<>();
		if(user != null) {
			int userId = Integer.parseInt(user);
			vehicleList = service1.getVehicles(userId);
			System.out.println(vehicleList);
		}
		request.setAttribute("vehicleList", vehicleList);
	%>
	
	<h6>Manage Vehicles:</h6>
	<button type="button" name="EditVehicle" onclick='document.getElementById("editVehicle").style.visibility = "visible"'
	>Edit</button> 
	<form name="deleteVehicle" action="VehicleController" method="post"><input type="submit" name ="submit" value="Delete" 
	></form>
	<div id="editVehicle" style="visibility: hidden">  
	<form name="updatevehicelform"  action="VehicleController" method="post" >
	<c:forEach items="${vehicleList}" var="vehicle">
	    License Number: <input type="text" name="licnum" id="licnum"  value= "${vehicle.licenseNum}"> <br>
	    Make: <input type="text" name="make" id="make" value= "${vehicle.make}"> <br>
	    Model: <input type="text" name="model" id="model"  value= "${vehicle.model}"> <br>
	    Year: <input type="text" name="year" id="year"  value= "${vehicle.year}"> <br>
	    Color: <input type="text" name="color" id="color"  value= "${vehicle.color}"> <br>
		
		<input type="submit" name="submit" value="UpdateVehicleDetails" >
	</c:forEach>
	</form>
	</div>
	
</body>
</html>