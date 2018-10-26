<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="script.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>

</head>
<body>
<form name="regform" action="LoginController" method="post" onsubmit="return regValidate()">
	<br>${message}<br>
	<h1>Parking Management Service</h1>
	<h2>User Registration</h2>
	<h3>User Information:</h3>
	
	<div id="username_error"></div><br>
	Name: <input type="text" name="name"><br>
	
	Password: <input type="password" name="password" id="password"> 
	<br>
	Re-Type Password: <input type="password" name="retry-password" id="retry-password"> <br>
	Email: <input type="text" name="email" id ="email">
	<br>
	Account Type: <select name="dropdown" id ="dropdown">
		<option>Student</option>
		<option>Faculty/Staff</option>
		<option>Visitor</option>
	</select>
	
	<h3>Vehicle Information</h3>
    
    License Number: <input type="text" name="licnum" id="licnum"> <br>
    Make: <input type="text" name="make" id="make"> <br>
    Model: <input type="text" name="model" id="model"> <br>
    Year: <input type="text" name="year" id="year"> <br>
    Color: <input type="text" name="color" id="color"> <br>
	
	<div id="password_error"></div><br>
	<input type="submit" name="submit" value="register" >
	<input type="reset" name="reset">
	
	</form>
	
</body>
</html>