<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="script.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>

<script type="text/javascript">
	$(document).ready(function() {
       $('#UpdateVehicle').click(function() {
			var cloneVehicle = '<form name="regform" action="LoginController" method="post" onsubmit="return regValidate()"> <div name="vehicleDiv" id=' + Math.random() + '> <br> License Number: <input type="text" name="licnum" id="licnum"> <br>Make: <input type="text" name="make" id="make"> <br>Model: <input type="text" name="model" id="model"> <br>Year: <input type="text" name="year" id="year"> <br>Color: <input type="text" name="color" id="color"> <br></div>' + '<input type="submit" name="submit" value="save" > </form>'
			$('#vehicleList').append(cloneVehicle)
		})
   })
</script>

<body>
<form name="regform" action="LoginController" method="post">
	<br>${message}<br>
	<h1>Parking Management Service</h1>
	<h2>User Registration</h2>
	<h3>User Information:</h3>
	
	UserId: <input type="text" name="userid"><br>
	Name: <input type="text" name="name"><br>
	
	Password: <input type="password" name="password" id="password"> 
	<br>
	Re-Type Password: <input type="password" name="retry-password" id="retry-password"> <br>
	Email: <input type="text" name="email" id ="email">
	<br>
	Account Type: <input type="text" name="acctype" id="acctype"> <br>
	
	<h3>Vehicle Information</h3>
    
    <div id="vehicleList">
		<button name="UpdateVehicleButton" value="Add" id="UpdateVehicle"> Add </button>
	</div>
	
	<input type="submit" name="submit" value="register" >
	<input type="reset" name="reset">
	
	</form>
	
</body>
</html>