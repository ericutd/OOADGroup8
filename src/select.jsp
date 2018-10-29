<%@page import="Selection.SelectionController"%>
<%@page import="db.DbManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Select Reservation</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<h1>Reservation Page</h1>
	<div>
		<form id="selectReservation" action="SelectionController" method="post" onsubmit="return validateReserve()">
			Lot ID<input type="text" name="parkingLotId"><br>
			SPOT ID<input type="text" name="parkingSpotId"><br>
			<input type="submit" name="reserveSubmit" value="reserve">
		</form>
	</div>
</body>
</html>