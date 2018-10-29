<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
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
              <a class="nav-link" href="logout.jsp">Logout</a> 
            </li>
          </ul>
        </div>
      </div>
    </nav>
	<br>
	<button type="button" onclick='document.getElementById("lots").style.visibility = "visible"'
	>Add parking lots</button>
	<div id="lots" style="visibility: hidden">
		<form  name="insertlotform" action="ParkingLotController" method="post">
			No of Lots: <input type="text" name="lot" id="lot"> 
			<button type="submit" name="submit" >Add</button>
		</form>
	</div> <br>
	<button type="button" onclick='document.getElementById("spots").style.visibility = "visible"'>Add parking spots</button>
	<div id="spots" style="visibility: hidden">
		<form name="addSpots" action="AdminController" method="post">
			Lot Id: <select name="id">
				<c:forEach items="${listId}" var="lotId">
					<option>${lotId}</option>
				</c:forEach>
			</select> No. of Spots: <select name="number">
				<option>1</option>
				<option>5</option>
				<option>10</option>
				<option>50</option>
				<option>100</option>
			</select> Color Permit: <select name="permit">
				<option>Green</option>
				<option>Gold</option>
				<option>Orange</option>
				<option>Purple</option>
				<option>Vistor</option>
			</select>
			<button type="submit" onclick="Add()">Add</button>
		</form>
	</div>
</body>
</html>