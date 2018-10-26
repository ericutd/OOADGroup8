<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<button type="button" onclick='document.getElementById("lots").style.visibility = "visible"'
	>Add parking lots</button>
	<div id="lots" style="visibility: hidden">
		<form  name="insertlotform" action="ParkingLotController" method="post">
			No of Lots: <input type="text" name="lot" id="lot"> 
			<button type="submit" name="submit" >Add</button>
		</form>
	</div>
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