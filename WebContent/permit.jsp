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
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
      <div class="container">
        <a class="navbar-brand" href="#">Parking Management Service</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
        </div>
      </div>
    </nav>
    <br>
	<h4>Add Permit Page</h4>
	<div>
		<form id="selectReservation" action="permit" method="post" onsubmit="return validateReserve()">
			Price <input type = "text" name = "price" /> <br> 
			Permit Color <select name="color">
				  <option value="BLUE">Blue</option>
				  <option value="YELLOW">Yellow</option>
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