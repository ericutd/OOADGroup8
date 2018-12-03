<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Parking</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp"/>
	<br>
	<h4>Manage Parking</h4>
	Add parking Lots
	<div id="lots" >
		<form  name="insertlotform" action="AdminController" method="post">
			No of Lots: <input type="text" name="lot" id="lot"> 
			No. of Spots: <select name="spotnumber">
				<option>1</option>
				<option>5</option>
				<option>10</option>
				<option>50</option>
				<option>100</option>
			</select> 
			Color Permit: <select name="permit">
				<option>Green</option>
				<option>Gold</option>
				<option>Orange</option>
				<option>Purple</option>
				<option>Vistor</option>
			</select>
			<button type="submit" name="submitlots" >Add</button>
		</form>
	</div> <br>
	
</body>
</html>