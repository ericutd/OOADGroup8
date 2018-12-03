<%@page import="controllers.ParkingController"%>
<%@page import="db.DbManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Add Permit</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp"/>
    <br>
	<h4>Add Permit Page</h4>
	<div>
		<form id="selectReservation" action="permit" method="post" onsubmit="return validateReserve()">
			Price <input type = "text" name = "price" /> <br> 
			Permit Color <select name="color">
				  <option value="VISITOR">Visitor</option>
				  <option value="GREEN">Green</option>
				   <option value="GOLD">Gold</option>
				    <option value="ORANGE">Orange</option>
				  <option value="PURPLE">Purple</option>
				</select> <br>
			Expiration Date <input type="date" id="start" name="exp_date"
       								value="2018-01-01"
       								min="1999-01-01" max="2050-12-31"><br>
			<input type="submit" name="Add Permit" value="Add Permit">
		</form>
	</div>
</body>
</html>