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
	<jsp:include page="header.jsp"/>
	<form name="vehform" action="VehicleController" method="post" onsubmit="return regValidate()">
		<h4>Register Vehicle</h4>
	    
	    License Number: <input type="text" name="licnum" id="licnum"> <br>
	    Make: <input type="text" name="make" id="make"> <br>
	    Model: <input type="text" name="model" id="model"> <br>
	    Year: <input type="text" name="year" id="year"> <br>
	    Color: <input type="text" name="color" id="color"> <br>
		
		<div id="password_error"></div><br>
		<input type="submit" name="submit" value="RegisterVehicle" >
		<input type="reset" name="reset">
	
	</form>
</body>
</html>
