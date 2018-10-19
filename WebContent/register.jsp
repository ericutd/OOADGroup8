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
	<h3>User Information</h3>
	Username: <input type="text" name="username"> <br>
	<div id="username_error"></div><br>
	Customer name: <input type="text" name="name"><br>
	
	Password: <input type="password" name="password" id="password"> 
	<br>
	Re-Type Password: <input type="password" name="retry-password" id="retry-password"> 
	<br>
	<div id="password_error"></div><br>
	Email: <input type="text" name="email"> <br>
	<div id="email_error"></div><br>
	Account type: <input type="text" name="accountType"> <br>
	<div id="accountType_error"></div><br>
	
	<h3>Vehicle Information</h3>
	License Number: <input type="text" name="licenseNum"> <br>
	<div id="licenseNum_error"></div><br>
	Make: <input type="text" name="make"> <br>
	<div id="make_error"></div><br>
	Model: <input type="text" name="model"> <br>
	<div id="model_error"></div><br>
	Color: <input type="text" name="color"> <br>
	<div id="color_error"></div><br>
	Year: <input type="text" name="year"> <br>
	<div id="year_error"></div><br>
	
	<input type="button" name="add another vehicle" onclick="vehicleForm()">
	<input type="submit" name="submit" value="register" >
	<input type="reset" name="reset">
	
	</form>
	
</body>
</html>
