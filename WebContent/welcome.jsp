<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome Page</title>
</head>
<body>
<h1>Parking Management Service</h1>
<h2> ${message} !!! </h2>

<h3> Registered Vehicles:</h3>

	<h4> ${vehicleDetails}</h4>
	<h4>License Number: ${licenseNum }</h4>
	<a href="reservation.jsp">Reserve a Spot</a> </br>
	<a href="logout.jsp">logout</a>

</body>
</html>