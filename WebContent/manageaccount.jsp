<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="script.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script>
$(document).ready(function(){
	//alert('Hiii');
	$.ajax({
		url: 'VehicleController',
		type: 'GET',
		dataType:'json',
		data: {msg:'Hello'},
		success: function(data){
			//$('licnum').html(data);
			alert('Hello');
			//alert(typeof(data));

		}
	});
	return false;
});
</script>
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
              <a class="nav-link" href="logout.jsp">Logout</a> 
            </li>
          </ul>
        </div>
      </div>
    </nav>
	<br>
	<h4>Manage Account</h4>
	<h6>Manage User Profile:</h6>
	
	<div id="username_error"></div><br>
	Name: <input type="text" name="name" ><br>
	
	Password: <input type="password" name="password" id="password" > 
	<br>
	Email: <input type="text" name="email" id ="email" >
	<br>
	
	<input type="submit" name="submit" value="UpdateUserDetails" >	
	</form><br>
	
	
	<h6>Manage Vehicles:</h6>
	<button type="button" name="EditVehicle" onclick='document.getElementById("editVehicle").style.visibility = "visible"'
	>Edit</button> 
	<form name="deleteVehicle" action="VehicleController" method="post"><input type="submit" name ="submit" value="Delete" 
	></form>
	<div id="editVehicle" style="visibility: hidden">  
	<form name="updatevehicelform"  action="VehicleController" method="post" >
	  
    License Number: <input type="text" name="licnum" id="licnum"> <br>
    Make: <input type="text" name="make" id="make"> <br>
    Model: <input type="text" name="model" id="model"> <br>
    Year: <input type="text" name="year" id="year"> <br>
    Color: <input type="text" name="color" id="color"> <br>
	
	<input type="submit" name="submit" value="UpdateVehicleDetails" >
	
	</form>
	</div>
	
</body>
</html>