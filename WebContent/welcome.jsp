<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome Page</title>
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
              <a class="nav-link" href="#">Home
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="reservation.jsp">Reserve a Spot</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="manageaccount.jsp">Manage Account</a>
            </li>
            <%
            String acctType= (String)request.getAttribute("acctType");
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

	<%  ArrayList<String> vDetails = new ArrayList<>();
		vDetails=(ArrayList)request.getAttribute("vehicleDetails"); %>
	<%  ArrayList<String> licnum = new ArrayList<>();
		licnum=(ArrayList)request.getAttribute("licenseNum"); %>
	<div class="container">
      <div class="row">
        <div class="col-lg-12 text-center">
          <h2 class="mt-5">${message} !!</h2>
         
          <p class="lead"> Registered Vehicles:</p>
           <% for(int i=0;i<vDetails.size();i++){%>
	          <ol class="list-unstyled">
	            <li><%=vDetails.get(i)%></li>
	            <li>License Number: <%=licnum.get(i)%></li>
	          </ol>
          <%} %>
        </div>
      </div>
    </div>

</body>
</html>