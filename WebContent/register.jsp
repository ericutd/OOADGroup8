<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="script.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
      <div class="container">
        <a class="navbar-brand" href="#">Parking Management Service</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        
      </div>
    </nav>
	

<form name="regform" action="LoginController" method="post" onsubmit="return regValidate()">
	<br>${message}<br>
	<h3>User Registration</h3>
	<h4>User Information:</h4>
	
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
	<br>
	<h4>Vehicle Information</h4>
    
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