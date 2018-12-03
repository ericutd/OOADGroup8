<%@page import="controllers.ParkingController"%>
<%@page import="db.DbManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Parking Management</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp"/>
    <br>
	<h4>Spot Management</h4>
	<div>
		<form id="selectReservation" action="SelectionController" method="post" onsubmit="return validateReserve()">
			Lot ID<input type="text" name="parkingLotId"><br>
			SPOT ID<input type="text" name="parkingSpotId"><br>
			<input type="submit" name="reserveSubmit" value="park">
			<input type="submit" name="reserveSubmit" value="unpark">
		</form>
	</div>
</body>
</html>